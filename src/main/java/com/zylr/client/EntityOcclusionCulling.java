package com.zylr.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.client.hud.HudManager;
import com.zylr.client.items.armor.client.ArmorClientExtension;
import com.zylr.client.items.armor.client.model.ArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EntityOcclusionCulling {
    private static final double EPSILON = 1.0E-7D;
    private static final double MIN_CULL_DISTANCE = 6.0D;
    private static final double THIRD_PERSON_CULL_DISTANCE_BONUS = 5.0D;
    private static final double MAX_HEAD_ITEM_MODEL_CULL_DISTANCE = 30.0D;
    private static final double MIN_HEAD_ITEM_MODEL_VOLUME_MULTIPLIER = 6.0D;
    private static final double MIN_HEAD_ITEM_MODEL_SIZE = 2.0D;
    private static final int COARSE_MODEL_SAMPLE_POINTS = 3;
    private static final int MAX_MODEL_SAMPLE_POINTS = 16;
    private static final int HEAD_ITEM_BOUNDS_SAMPLE_POINTS = 15;
    private static final double HEAD_ITEM_PROXY_EXPANSION = 0.75D;
    private static final long MODEL_SAMPLE_CACHE_MAX_AGE_TICKS = 10L;
    private static final double MODEL_SAMPLE_CACHE_MOVE_TOLERANCE_SQR = 0.0004D;
    private static final float MODEL_SAMPLE_CACHE_YAW_TOLERANCE = 1.5F;
    private static final int OCCLUSION_CONFIRM_TICKS = 2;
    private static final Map<Integer, Boolean> RESULT_CACHE = new HashMap<>();
    private static final Map<Integer, CachedModelSamples> MODEL_SAMPLE_CACHE = new HashMap<>();
    private static final Map<Integer, CachedSuppressionResult> SUPPRESSION_CACHE = new HashMap<>();
    private static final Map<Integer, OcclusionHistory> OCCLUSION_HISTORY = new HashMap<>();
    private static final Map<BlockPos, Boolean> SOLID_BLOCK_CACHE = new HashMap<>();
    private static final long SUPPRESSION_CACHE_MAX_AGE_TICKS = 2L;
    private static final double SUPPRESSION_CACHE_ENTITY_MOVE_TOLERANCE_SQR = 0.0004D;
    private static final double SUPPRESSION_CACHE_CAMERA_MOVE_TOLERANCE_SQR = 0.0004D;
    private static long lastCacheGameTime = Long.MIN_VALUE;
    private static long lastModelCacheCleanupGameTime = Long.MIN_VALUE;
    private static long lastSolidBlockCacheGameTime = Long.MIN_VALUE;
    private static BlockPos lastCameraBlockPos = BlockPos.ZERO;

    private EntityOcclusionCulling() {
    }

    public static boolean shouldSuppress(Entity entity) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null || minecraft.gameRenderer == null) {
            return false;
        }

        if (!HudManager.getInstance().isEntityOcclusionCullingEnabled()) {
            return false;
        }

        if (entity == minecraft.player || entity.isPassengerOfSameVehicle(minecraft.player)) {
            return false;
        }

        Vec3 from = minecraft.gameRenderer.getMainCamera().position();
        if (!isFinite(from) || !isFinite(entity.position())) {
            return false;
        }

        double minCullDistance = MIN_CULL_DISTANCE;
        if (!minecraft.options.getCameraType().isFirstPerson()) {
            minCullDistance += THIRD_PERSON_CULL_DISTANCE_BONUS;
        }

        long gameTime = minecraft.level.getGameTime();
        BlockPos cameraBlockPos = BlockPos.containing(from);
        if (gameTime != lastCacheGameTime || !cameraBlockPos.equals(lastCameraBlockPos)) {
            RESULT_CACHE.clear();
            lastCacheGameTime = gameTime;
            lastCameraBlockPos = cameraBlockPos;
        }

        SampleSet sampleSet = null;
        if (HudManager.getInstance().isEntityOcclusionCullingEnabled()
            && entity instanceof ArmorStand armorStand
            && !armorStand.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            sampleSet = samplePoints(entity, minecraft);
            if (sampleSet.modelBased()) {
                if (closestSampleDistanceSqr(from, sampleSet) < minCullDistance * minCullDistance) {
                    return false;
                }
            } else {
                double entityDistanceSqr = from.distanceToSqr(entity.position());
                if (entityDistanceSqr < minCullDistance * minCullDistance) {
                    return false;
                }
            }
        } else {
            double entityDistanceSqr = from.distanceToSqr(entity.position());
            if (entityDistanceSqr < minCullDistance * minCullDistance) {
                return false;
            }
        }

        CachedSuppressionResult cachedSuppression = SUPPRESSION_CACHE.get(entity.getId());
        if (cachedSuppression != null && cachedSuppression.canReuse(entity, from, gameTime, cameraBlockPos)) {
            return cachedSuppression.suppressed();
        }

        Boolean cachedResult = RESULT_CACHE.get(entity.getId());
        if (cachedResult != null) {
            return cachedResult;
        }

        if (sampleSet == null) {
            sampleSet = samplePoints(entity, minecraft);
        }
        boolean fullyOccluded = areAllSamplesOccluded(from, sampleSet.coarseSamples(), entity)
            && areAllSamplesOccluded(from, sampleSet.fullSamples(), entity);
        boolean suppressed = updateOcclusionHistory(entity, fullyOccluded, gameTime);

        RESULT_CACHE.put(entity.getId(), suppressed);
        if (!suppressed) {
            SUPPRESSION_CACHE.put(entity.getId(), new CachedSuppressionResult(gameTime, entity.position(), from, cameraBlockPos, false));
        } else {
            SUPPRESSION_CACHE.remove(entity.getId());
        }
        return suppressed;
    }

    public static boolean shouldForceVisible(Entity entity, Frustum frustum) {
        if (frustum == null || !(entity instanceof ArmorStand armorStand)) {
            return false;
        }
        if (!HudManager.getInstance().isEntityOcclusionCullingEnabled() || armorStand.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            return false;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null || minecraft.gameRenderer == null) {
            return false;
        }

        CachedModelSamples cachedSamples = MODEL_SAMPLE_CACHE.get(entity.getId());
        if (cachedSamples == null) {
            return false;
        }
        if (!cachedSamples.modelOcclusionEligible()) {
            return false;
        }

        AABB bounds = cachedSamples.bounds();
        return bounds != null && frustum.isVisible(bounds);
    }

    private static boolean areAllSamplesOccluded(Vec3 from, Vec3[] samples, Entity entity) {
        for (Vec3 sample : samples) {
            if (!isFinite(sample)) {
                return false;
            }
            if (!isOccluded(from, sample, entity)) {
                return false;
            }
        }
        return true;
    }

    private static double closestSampleDistanceSqr(Vec3 from, SampleSet sampleSet) {
        double closest = Double.POSITIVE_INFINITY;
        for (Vec3 sample : sampleSet.coarseSamples()) {
            if (isFinite(sample)) {
                closest = Math.min(closest, from.distanceToSqr(sample));
            }
        }
        for (Vec3 sample : sampleSet.fullSamples()) {
            if (isFinite(sample)) {
                closest = Math.min(closest, from.distanceToSqr(sample));
            }
        }
        return closest;
    }

    private static boolean isOccluded(Vec3 from, Vec3 to, Entity entity) {
        Minecraft minecraft = Minecraft.getInstance();
        Vec3 delta = to.subtract(from);
        double distance = delta.length();
        if (!Double.isFinite(distance) || distance <= 1.0E-4D) {
            return false;
        }

        int x = BlockPos.containing(from).getX();
        int y = BlockPos.containing(from).getY();
        int z = BlockPos.containing(from).getZ();
        int endX = BlockPos.containing(to).getX();
        int endY = BlockPos.containing(to).getY();
        int endZ = BlockPos.containing(to).getZ();

        int stepX = Integer.compare(endX, x);
        int stepY = Integer.compare(endY, y);
        int stepZ = Integer.compare(endZ, z);

        double nextBoundaryX = stepX > 0 ? x + 1.0D : x;
        double nextBoundaryY = stepY > 0 ? y + 1.0D : y;
        double nextBoundaryZ = stepZ > 0 ? z + 1.0D : z;

        double tMaxX = rayT(from.x, delta.x, nextBoundaryX, stepX);
        double tMaxY = rayT(from.y, delta.y, nextBoundaryY, stepY);
        double tMaxZ = rayT(from.z, delta.z, nextBoundaryZ, stepZ);
        double tDeltaX = rayStep(delta.x);
        double tDeltaY = rayStep(delta.y);
        double tDeltaZ = rayStep(delta.z);
        int maxIterations = Math.max(1, (int) Math.ceil(distance * 2.0D) + 4);

        for (int iteration = 0; iteration < maxIterations && (x != endX || y != endY || z != endZ); iteration++) {
            int previousX = x;
            int previousY = y;
            int previousZ = z;
            if (tMaxX <= tMaxY && tMaxX <= tMaxZ) {
                x += stepX;
                tMaxX += tDeltaX;
            } else if (tMaxY <= tMaxZ) {
                y += stepY;
                tMaxY += tDeltaY;
            } else {
                z += stepZ;
                tMaxZ += tDeltaZ;
            }

            if (x == endX && y == endY && z == endZ) {
                return false;
            }
            if (x == previousX && y == previousY && z == previousZ) {
                return false;
            }

            BlockPos pos = new BlockPos(x, y, z);
            if (isSolidRender(minecraft, pos)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSolidRender(Minecraft minecraft, BlockPos pos) {
        long gameTime = minecraft.level.getGameTime();
        if (gameTime != lastSolidBlockCacheGameTime) {
            SOLID_BLOCK_CACHE.clear();
            lastSolidBlockCacheGameTime = gameTime;
        }

        Boolean cached = SOLID_BLOCK_CACHE.get(pos);
        if (cached != null) {
            return cached;
        }

        BlockState state = minecraft.level.getBlockState(pos);
        boolean solid = state.isSolidRender();
        SOLID_BLOCK_CACHE.put(pos, solid);
        return solid;
    }

    private static SampleSet samplePoints(Entity entity, Minecraft minecraft) {
        if (HudManager.getInstance().isEntityOcclusionCullingEnabled()
            && entity instanceof ArmorStand armorStand
            && !armorStand.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            Vec3 cameraPos = minecraft.gameRenderer != null ? minecraft.gameRenderer.getMainCamera().position() : null;
            if (cameraPos != null && cameraPos.distanceToSqr(entity.position()) > MAX_HEAD_ITEM_MODEL_CULL_DISTANCE * MAX_HEAD_ITEM_MODEL_CULL_DISTANCE) {
                Vec3[] hitboxSamples = sampleHitboxPoints(entity);
                return new SampleSet(hitboxSamples, new Vec3[0], false);
            }
            CachedModelSamples modelSamples = sampleModelPoints(entity, minecraft);
            if (modelSamples != null
                && modelSamples.modelOcclusionEligible()
                && (modelSamples.samples().coarseSamples().length > 0 || modelSamples.samples().fullSamples().length > 0)) {
                return modelSamples.samples();
            }
        }
        Vec3[] hitboxSamples = sampleHitboxPoints(entity);
        return new SampleSet(hitboxSamples, new Vec3[0], false);
    }

    private static boolean shouldUseModelSamples(Entity entity, AABB modelBounds) {
        if (modelBounds == null) {
            return false;
        }

        AABB entityBounds = entity.getBoundingBox();
        double entityVolume = Math.max(entityBounds.getXsize() * entityBounds.getYsize() * entityBounds.getZsize(), 1.0E-4D);
        double modelVolume = Math.max(modelBounds.getXsize() * modelBounds.getYsize() * modelBounds.getZsize(), 0.0D);
        double maxModelSize = Math.max(modelBounds.getXsize(), Math.max(modelBounds.getYsize(), modelBounds.getZsize()));
        return maxModelSize >= MIN_HEAD_ITEM_MODEL_SIZE
            && modelVolume >= entityVolume * MIN_HEAD_ITEM_MODEL_VOLUME_MULTIPLIER;
    }

    private static Vec3[] sampleHitboxPoints(Entity entity) {
        AABB box = entity.getBoundingBox();
        double minX = box.minX;
        double minY = box.minY;
        double minZ = box.minZ;
        double maxX = box.maxX;
        double maxY = box.maxY;
        double maxZ = box.maxZ;
        double midX = (minX + maxX) * 0.5D;
        double midY = (minY + maxY) * 0.5D;
        double midZ = (minZ + maxZ) * 0.5D;

        return new Vec3[] {
            new Vec3(midX, midY, midZ),
            new Vec3(midX, maxY, midZ),
            new Vec3(minX, midY, midZ),
            new Vec3(maxX, midY, midZ),
            new Vec3(midX, minY, midZ)
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static CachedModelSamples sampleModelPoints(Entity entity, Minecraft minecraft) {
        if (!(entity instanceof LivingEntity)) {
            return null;
        }

        long gameTime = minecraft.level.getGameTime();
        CachedModelSamples cachedSamples = MODEL_SAMPLE_CACHE.get(entity.getId());
        if (cachedSamples != null && cachedSamples.canReuse(entity, gameTime)) {
            return cachedSamples;
        }
        pruneModelSampleCache(gameTime);

        EntityRenderDispatcher dispatcher = minecraft.getEntityRenderDispatcher();
        EntityRenderer renderer = dispatcher.getRenderer(entity);
        if (!(renderer instanceof LivingEntityRenderer livingRenderer)) {
            return null;
        }

        EntityRenderState renderState = dispatcher.extractEntity(entity, minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(true));
        if (!(renderState instanceof LivingEntityRenderState livingState)) {
            return null;
        }

        EntityModel model = (EntityModel) livingRenderer.getModel();
        model.resetPose();
        model.setupAnim(livingState);

        if (entity instanceof ArmorStand armorStand && !armorStand.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            ModelSampleResult headItemSamples = sampleArmorStandHeadItemExtents(model, livingState, entity.position(), resolveModelYaw(entity, livingState), livingState.scale);
            if (headItemSamples != null) {
                AABB bounds = boundsFromSamples(headItemSamples.samples());
                CachedModelSamples cached = new CachedModelSamples(
                    gameTime,
                    entity.position(),
                    resolveModelYaw(entity, livingState),
                    headItemSamples.samples(),
                    bounds,
                    shouldUseModelSamples(entity, headItemSamples.eligibilityBounds())
                );
                MODEL_SAMPLE_CACHE.put(entity.getId(), cached);
                return cached;
            }
        }

        List<Vector3f> rawPoints = new ArrayList<>();
        collectModelSamplePoints(rawPoints, model.root());
        collectArmorLayerSamplePoints(rawPoints, entity, model, livingState);
        collectHeadItemSamplePoints(rawPoints, model, livingState);
        if (rawPoints.isEmpty()) {
            return null;
        }

        float modelYaw = resolveModelYaw(entity, livingState);
        SampleSet sampleSet = transformModelSamplesToWorldSpace(rawPoints, entity.position(), modelYaw, livingState.scale);
        AABB bounds = boundsFromSamples(sampleSet);
        CachedModelSamples cached = new CachedModelSamples(gameTime, entity.position(), modelYaw, sampleSet, bounds, shouldUseModelSamples(entity, bounds));
        MODEL_SAMPLE_CACHE.put(entity.getId(), cached);
        return cached;
    }

    private static ModelSampleResult sampleArmorStandHeadItemExtents(EntityModel model, LivingEntityRenderState livingState, Vec3 origin, float yawDegrees, float scale) {
        if (!(model instanceof HeadedModel headedModel) || livingState.headItem.isEmpty()) {
            return null;
        }

        PoseStack poseStack = new PoseStack();
        CustomHeadLayer.Transforms transforms = CustomHeadLayer.Transforms.DEFAULT;
        poseStack.scale(transforms.horizontalScale(), 1.0F, transforms.horizontalScale());
        model.root().translateAndRotate(poseStack);
        headedModel.translateToHead(poseStack);
        CustomHeadLayer.translateToHead(poseStack, transforms);

        List<Vector3f> rawPoints = new ArrayList<>(24);
        PoseStack.Pose pose = poseStack.last();
        livingState.headItem.visitExtents(extent -> rawPoints.add(
            pose.pose().transformPosition(extent.x(), extent.y(), extent.z(), new Vector3f())
        ));
        if (rawPoints.isEmpty()) {
            AABB headItemBox = livingState.headItem.getModelBoundingBox();
            if (headItemBox == null) {
                return null;
            }
            addBoundingBoxSamplePoints(rawPoints, pose, headItemBox);
        }

        AABB eligibilityLocalBounds = localBoundsFromPoints(rawPoints, 0.0D);
        if (eligibilityLocalBounds == null) {
            return null;
        }
        AABB eligibilityBounds = transformLocalBoundsToWorldSpace(eligibilityLocalBounds, origin, yawDegrees, scale);
        AABB localBounds = eligibilityLocalBounds.inflate(HEAD_ITEM_PROXY_EXPANSION);

        List<Vector3f> boundsSamples = new ArrayList<>(HEAD_ITEM_BOUNDS_SAMPLE_POINTS);
        addLocalBoundingBoxProxySamples(boundsSamples, localBounds);
        return new ModelSampleResult(transformModelSamplesToWorldSpace(boundsSamples, origin, yawDegrees, scale), eligibilityBounds);
    }

    private static void inflateSampleExtents(List<Vector3f> rawPoints, double expansion) {
        if (rawPoints.isEmpty() || expansion <= 0.0D) {
            return;
        }

        float minX = Float.POSITIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY;
        float minZ = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY;
        float maxY = Float.NEGATIVE_INFINITY;
        float maxZ = Float.NEGATIVE_INFINITY;
        for (Vector3f point : rawPoints) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            minZ = Math.min(minZ, point.z);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
            maxZ = Math.max(maxZ, point.z);
        }

        float expand = (float) expansion;
        rawPoints.add(new Vector3f(minX - expand, minY, minZ));
        rawPoints.add(new Vector3f(maxX + expand, maxY, maxZ));
        rawPoints.add(new Vector3f(minX, minY - expand, maxZ));
        rawPoints.add(new Vector3f(maxX, maxY + expand, minZ));
    }

    private static AABB localBoundsFromPoints(List<Vector3f> rawPoints, double expansion) {
        if (rawPoints.isEmpty()) {
            return null;
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (Vector3f point : rawPoints) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            minZ = Math.min(minZ, point.z);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
            maxZ = Math.max(maxZ, point.z);
        }

        return new AABB(minX, minY, minZ, maxX, maxY, maxZ).inflate(expansion);
    }

    private static AABB transformLocalBoundsToWorldSpace(AABB localBounds, Vec3 origin, float yawDegrees, float scale) {
        float radians = -yawDegrees * ((float) Math.PI / 180.0F);
        float cos = Mth.cos(radians);
        float sin = Mth.sin(radians);
        float effectiveScale = Float.isFinite(scale) && scale > 0.0F ? scale : 1.0F;

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (int xIndex = 0; xIndex < 2; xIndex++) {
            double localX = (xIndex == 0 ? localBounds.minX : localBounds.maxX) * effectiveScale;
            for (int yIndex = 0; yIndex < 2; yIndex++) {
                double localY = (yIndex == 0 ? localBounds.minY : localBounds.maxY) * effectiveScale;
                for (int zIndex = 0; zIndex < 2; zIndex++) {
                    double localZ = (zIndex == 0 ? localBounds.minZ : localBounds.maxZ) * effectiveScale;
                    double worldX = origin.x + (localX * cos - localZ * sin);
                    double worldY = origin.y + localY;
                    double worldZ = origin.z + (localX * sin + localZ * cos);
                    minX = Math.min(minX, worldX);
                    minY = Math.min(minY, worldY);
                    minZ = Math.min(minZ, worldZ);
                    maxX = Math.max(maxX, worldX);
                    maxY = Math.max(maxY, worldY);
                    maxZ = Math.max(maxZ, worldZ);
                }
            }
        }

        return new AABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private static void addLocalBoundingBoxProxySamples(List<Vector3f> rawPoints, AABB box) {
        float minX = (float) box.minX;
        float minY = (float) box.minY;
        float minZ = (float) box.minZ;
        float maxX = (float) box.maxX;
        float maxY = (float) box.maxY;
        float maxZ = (float) box.maxZ;
        float midX = (minX + maxX) * 0.5F;
        float midY = (minY + maxY) * 0.5F;
        float midZ = (minZ + maxZ) * 0.5F;

        rawPoints.add(new Vector3f(midX, midY, midZ));
        rawPoints.add(new Vector3f(minX, midY, midZ));
        rawPoints.add(new Vector3f(maxX, midY, midZ));
        rawPoints.add(new Vector3f(midX, minY, midZ));
        rawPoints.add(new Vector3f(midX, maxY, midZ));
        rawPoints.add(new Vector3f(midX, midY, minZ));
        rawPoints.add(new Vector3f(midX, midY, maxZ));
        rawPoints.add(new Vector3f(minX, maxY, minZ));
        rawPoints.add(new Vector3f(maxX, minY, maxZ));
        rawPoints.add(new Vector3f(minX, minY, minZ));
        rawPoints.add(new Vector3f(minX, minY, maxZ));
        rawPoints.add(new Vector3f(minX, maxY, maxZ));
        rawPoints.add(new Vector3f(maxX, minY, minZ));
        rawPoints.add(new Vector3f(maxX, maxY, minZ));
        rawPoints.add(new Vector3f(maxX, maxY, maxZ));
    }

    private static void pruneModelSampleCache(long gameTime) {
        if (gameTime == lastModelCacheCleanupGameTime) {
            return;
        }
        lastModelCacheCleanupGameTime = gameTime;
        MODEL_SAMPLE_CACHE.entrySet().removeIf(entry -> gameTime - entry.getValue().gameTime() > MODEL_SAMPLE_CACHE_MAX_AGE_TICKS);
        SUPPRESSION_CACHE.entrySet().removeIf(entry -> gameTime - entry.getValue().gameTime() > SUPPRESSION_CACHE_MAX_AGE_TICKS);
        OCCLUSION_HISTORY.entrySet().removeIf(entry -> gameTime - entry.getValue().lastGameTime() > MODEL_SAMPLE_CACHE_MAX_AGE_TICKS);
    }

    private static boolean updateOcclusionHistory(Entity entity, boolean fullyOccluded, long gameTime) {
        if (!fullyOccluded) {
            OCCLUSION_HISTORY.remove(entity.getId());
            return false;
        }

        OcclusionHistory history = OCCLUSION_HISTORY.get(entity.getId());
        if (history == null || gameTime - history.lastGameTime() > 1L) {
            history = new OcclusionHistory(1, gameTime);
        } else {
            history = new OcclusionHistory(history.occludedTicks() + 1, gameTime);
        }
        OCCLUSION_HISTORY.put(entity.getId(), history);
        return history.occludedTicks() >= OCCLUSION_CONFIRM_TICKS;
    }

    private static void collectModelSamplePoints(List<Vector3f> rawPoints, ModelPart root) {
        PoseStack poseStack = new PoseStack();
        root.visit(poseStack, (pose, path, cubeIndex, cube) -> addCubeSamplePoints(rawPoints, pose, cube));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void collectArmorLayerSamplePoints(List<Vector3f> rawPoints, Entity entity, EntityModel baseModel, LivingEntityRenderState livingState) {
        if (!(entity instanceof LivingEntity livingEntity)
            || !(baseModel instanceof HumanoidModel humanoidModel)
            || !(livingState instanceof HumanoidRenderState humanoidState)) {
            return;
        }

        collectArmorSlotSamplePoints(rawPoints, livingEntity, humanoidModel, humanoidState, EquipmentSlot.HEAD, humanoidState.headEquipment);
        collectArmorSlotSamplePoints(rawPoints, livingEntity, humanoidModel, humanoidState, EquipmentSlot.CHEST, humanoidState.chestEquipment);
        collectArmorSlotSamplePoints(rawPoints, livingEntity, humanoidModel, humanoidState, EquipmentSlot.LEGS, humanoidState.legsEquipment);
        collectArmorSlotSamplePoints(rawPoints, livingEntity, humanoidModel, humanoidState, EquipmentSlot.FEET, humanoidState.feetEquipment);
    }

    private static void collectArmorSlotSamplePoints(
        List<Vector3f> rawPoints,
        LivingEntity livingEntity,
        HumanoidModel<?> humanoidModel,
        HumanoidRenderState humanoidState,
        EquipmentSlot slot,
        ItemStack originalStack
    ) {
        ItemStack renderStack = resolveArmorRenderStack(originalStack, slot, humanoidState);
        if (renderStack == null || renderStack.isEmpty()) {
            return;
        }

        ArmorClientExtension.providerFor(renderStack).ifPresent(provider -> {
            ArmorModel armorModel = provider.getModel(livingEntity, renderStack, slot);
            armorModel.partVisible(slot);
            copyModelProperties(humanoidModel, armorModel);
            armorModel.setupAnim(humanoidState);
            collectModelSamplePoints(rawPoints, armorModel.root());
        });
    }

    private static void collectHeadItemSamplePoints(List<Vector3f> rawPoints, EntityModel model, LivingEntityRenderState livingState) {
        if (!(model instanceof HeadedModel headedModel) || livingState.headItem.isEmpty()) {
            return;
        }

        PoseStack poseStack = new PoseStack();
        CustomHeadLayer.Transforms transforms = CustomHeadLayer.Transforms.DEFAULT;
        poseStack.scale(transforms.horizontalScale(), 1.0F, transforms.horizontalScale());
        model.root().translateAndRotate(poseStack);
        headedModel.translateToHead(poseStack);
        CustomHeadLayer.translateToHead(poseStack, transforms);

        PoseStack.Pose pose = poseStack.last();
        livingState.headItem.visitExtents(extent -> rawPoints.add(
            pose.pose().transformPosition(extent.x(), extent.y(), extent.z(), new Vector3f())
        ));
    }

    private static ItemStack resolveArmorRenderStack(ItemStack originalStack, EquipmentSlot slot, HumanoidRenderState humanoidState) {
        if (originalStack == null || originalStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        Item overrideItem = ArmorOverrideResolver.resolveOverride(originalStack, slot, humanoidState);
        if (overrideItem != null) {
            return new ItemStack(overrideItem);
        }
        return originalStack;
    }

    private static void copyModelProperties(HumanoidModel<?> source, ArmorModel target) {
        copyPose(source.head, target.head);
        copyPose(source.hat, target.hat);
        copyPose(source.body, target.body);
        copyPose(source.rightArm, target.rightArm);
        copyPose(source.leftArm, target.leftArm);
        copyPose(source.rightLeg, target.rightLeg);
        copyPose(source.leftLeg, target.leftLeg);
        copyPose(source.rightLeg, target.rightBoot);
        copyPose(source.leftLeg, target.leftBoot);
    }

    private static void copyPose(ModelPart source, ModelPart target) {
        target.x = source.x;
        target.y = source.y;
        target.z = source.z;
        target.xRot = source.xRot;
        target.yRot = source.yRot;
        target.zRot = source.zRot;
        target.xScale = source.xScale;
        target.yScale = source.yScale;
        target.zScale = source.zScale;
    }

    private static void addCubeSamplePoints(List<Vector3f> rawPoints, PoseStack.Pose pose, ModelPart.Cube cube) {
        addModelPoint(rawPoints, pose, cube.minX, cube.minY, cube.minZ);
        addModelPoint(rawPoints, pose, cube.maxX, cube.maxY, cube.maxZ);
        addModelPoint(rawPoints, pose, cube.minX, cube.maxY, cube.minZ);
        addModelPoint(rawPoints, pose, (cube.minX + cube.maxX) * 0.5F, (cube.minY + cube.maxY) * 0.5F, (cube.minZ + cube.maxZ) * 0.5F);
    }

    private static void addBoundingBoxSamplePoints(List<Vector3f> rawPoints, PoseStack.Pose pose, AABB box) {
        float minX = (float) box.minX;
        float minY = (float) box.minY;
        float minZ = (float) box.minZ;
        float maxX = (float) box.maxX;
        float maxY = (float) box.maxY;
        float maxZ = (float) box.maxZ;
        float midX = (minX + maxX) * 0.5F;
        float midY = (minY + maxY) * 0.5F;
        float midZ = (minZ + maxZ) * 0.5F;

        addModelPoint(rawPoints, pose, minX, minY, minZ);
        addModelPoint(rawPoints, pose, minX, minY, maxZ);
        addModelPoint(rawPoints, pose, minX, maxY, minZ);
        addModelPoint(rawPoints, pose, minX, maxY, maxZ);
        addModelPoint(rawPoints, pose, maxX, minY, minZ);
        addModelPoint(rawPoints, pose, maxX, minY, maxZ);
        addModelPoint(rawPoints, pose, maxX, maxY, minZ);
        addModelPoint(rawPoints, pose, maxX, maxY, maxZ);
        addModelPoint(rawPoints, pose, midX, midY, midZ);
        addModelPoint(rawPoints, pose, minX, midY, midZ);
        addModelPoint(rawPoints, pose, maxX, midY, midZ);
        addModelPoint(rawPoints, pose, midX, maxY, midZ);
        addModelPoint(rawPoints, pose, midX, midY, minZ);
    }

    private static AABB boundsFromSamples(SampleSet sampleSet) {
        Vec3[] coarse = sampleSet.coarseSamples();
        Vec3[] full = sampleSet.fullSamples();
        if (coarse.length == 0 && full.length == 0) {
            return null;
        }

        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;

        for (Vec3 point : coarse) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            minZ = Math.min(minZ, point.z);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
            maxZ = Math.max(maxZ, point.z);
        }
        for (Vec3 point : full) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            minZ = Math.min(minZ, point.z);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
            maxZ = Math.max(maxZ, point.z);
        }

        return new AABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    private static void addModelPoint(List<Vector3f> rawPoints, PoseStack.Pose pose, float x, float y, float z) {
        rawPoints.add(pose.pose().transformPosition(x / 16.0F, y / 16.0F, z / 16.0F, new Vector3f()));
    }

    private static float resolveModelYaw(Entity entity, LivingEntityRenderState livingState) {
        if (livingState instanceof HumanoidRenderState) {
            return livingState.bodyRot;
        }
        return entity.getYRot();
    }

    private static SampleSet transformModelSamplesToWorldSpace(List<Vector3f> rawPoints, Vec3 origin, float yawDegrees, float scale) {
        List<Vector3f> selectedPoints = selectDistributedModelSamples(rawPoints, MAX_MODEL_SAMPLE_POINTS);
        int sampleCount = selectedPoints.size();
        Vec3[] samples = new Vec3[sampleCount];
        float radians = -yawDegrees * ((float) Math.PI / 180.0F);
        float cos = Mth.cos(radians);
        float sin = Mth.sin(radians);
        float effectiveScale = Float.isFinite(scale) && scale > 0.0F ? scale : 1.0F;

        for (int index = 0; index < sampleCount; index++) {
            Vector3f point = selectedPoints.get(index);
            double localX = point.x * effectiveScale;
            double localY = point.y * effectiveScale;
            double localZ = point.z * effectiveScale;
            double worldX = origin.x + (localX * cos - localZ * sin);
            double worldY = origin.y + localY;
            double worldZ = origin.z + (localX * sin + localZ * cos);
            samples[index] = new Vec3(worldX, worldY, worldZ);
        }

        int coarseSampleCount = Math.min(COARSE_MODEL_SAMPLE_POINTS, samples.length);
        Vec3[] coarseSamples = new Vec3[coarseSampleCount];
        System.arraycopy(samples, 0, coarseSamples, 0, coarseSampleCount);

        int fullSampleCount = Math.max(0, samples.length - coarseSampleCount);
        Vec3[] fullSamples = new Vec3[fullSampleCount];
        if (fullSampleCount > 0) {
            System.arraycopy(samples, coarseSampleCount, fullSamples, 0, fullSampleCount);
        }

        return new SampleSet(coarseSamples, fullSamples, true);
    }

    private static List<Vector3f> selectDistributedModelSamples(List<Vector3f> rawPoints, int maxSamples) {
        if (rawPoints.isEmpty()) {
            return List.of();
        }
        if (rawPoints.size() <= maxSamples) {
            return rawPoints;
        }

        List<Vector3f> selected = new ArrayList<>(maxSamples);
        boolean[] used = new boolean[rawPoints.size()];

        addExtremePoint(rawPoints, selected, used, Axis.MIN_X);
        addExtremePoint(rawPoints, selected, used, Axis.MAX_X);
        addExtremePoint(rawPoints, selected, used, Axis.MIN_Y);
        addExtremePoint(rawPoints, selected, used, Axis.MAX_Y);
        addExtremePoint(rawPoints, selected, used, Axis.MIN_Z);
        addExtremePoint(rawPoints, selected, used, Axis.MAX_Z);

        while (selected.size() < maxSamples) {
            int nextIndex = findFarthestPointIndex(rawPoints, selected, used);
            if (nextIndex < 0) {
                break;
            }
            used[nextIndex] = true;
            selected.add(rawPoints.get(nextIndex));
        }

        return selected;
    }

    private static void addExtremePoint(List<Vector3f> rawPoints, List<Vector3f> selected, boolean[] used, Axis axis) {
        int bestIndex = -1;
        float bestValue = axis.isMin ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        for (int index = 0; index < rawPoints.size(); index++) {
            if (used[index]) {
                continue;
            }
            Vector3f point = rawPoints.get(index);
            float value = switch (axis.coordinate) {
                case X -> point.x;
                case Y -> point.y;
                case Z -> point.z;
            };
            if ((axis.isMin && value < bestValue) || (!axis.isMin && value > bestValue)) {
                bestValue = value;
                bestIndex = index;
            }
        }
        if (bestIndex >= 0) {
            used[bestIndex] = true;
            selected.add(rawPoints.get(bestIndex));
        }
    }

    private static int findFarthestPointIndex(List<Vector3f> rawPoints, List<Vector3f> selected, boolean[] used) {
        int bestIndex = -1;
        float bestDistance = Float.NEGATIVE_INFINITY;
        for (int candidateIndex = 0; candidateIndex < rawPoints.size(); candidateIndex++) {
            if (used[candidateIndex]) {
                continue;
            }
            Vector3f candidate = rawPoints.get(candidateIndex);
            float nearestDistance = Float.POSITIVE_INFINITY;
            for (Vector3f existing : selected) {
                float distance = candidate.distanceSquared(existing);
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                }
            }
            if (nearestDistance > bestDistance) {
                bestDistance = nearestDistance;
                bestIndex = candidateIndex;
            }
        }
        return bestIndex;
    }

    private enum CoordinateAxis {
        X,
        Y,
        Z
    }

    private enum Axis {
        MIN_X(CoordinateAxis.X, true),
        MAX_X(CoordinateAxis.X, false),
        MIN_Y(CoordinateAxis.Y, true),
        MAX_Y(CoordinateAxis.Y, false),
        MIN_Z(CoordinateAxis.Z, true),
        MAX_Z(CoordinateAxis.Z, false);

        private final CoordinateAxis coordinate;
        private final boolean isMin;

        Axis(CoordinateAxis coordinate, boolean isMin) {
            this.coordinate = coordinate;
            this.isMin = isMin;
        }
    }

    private record SampleSet(Vec3[] coarseSamples, Vec3[] fullSamples, boolean modelBased) {}

    private record ModelSampleResult(SampleSet samples, AABB eligibilityBounds) {}

    private record CachedModelSamples(long gameTime, Vec3 origin, float yaw, SampleSet samples, AABB bounds, boolean modelOcclusionEligible) {
        private boolean canReuse(Entity entity, long currentGameTime) {
            if (currentGameTime - this.gameTime > MODEL_SAMPLE_CACHE_MAX_AGE_TICKS) {
                return false;
            }
            if (this.origin.distanceToSqr(entity.position()) > MODEL_SAMPLE_CACHE_MOVE_TOLERANCE_SQR) {
                return false;
            }
            return Math.abs(Mth.wrapDegrees(entity.getYRot() - this.yaw)) <= MODEL_SAMPLE_CACHE_YAW_TOLERANCE;
        }
    }

    private record CachedSuppressionResult(long gameTime, Vec3 entityOrigin, Vec3 cameraOrigin, BlockPos cameraBlockPos, boolean suppressed) {
        private boolean canReuse(Entity entity, Vec3 currentCameraOrigin, long currentGameTime, BlockPos currentCameraBlockPos) {
            if (currentGameTime - this.gameTime > SUPPRESSION_CACHE_MAX_AGE_TICKS) {
                return false;
            }
            if (!this.cameraBlockPos.equals(currentCameraBlockPos)) {
                return false;
            }
            if (this.entityOrigin.distanceToSqr(entity.position()) > SUPPRESSION_CACHE_ENTITY_MOVE_TOLERANCE_SQR) {
                return false;
            }
            return this.cameraOrigin.distanceToSqr(currentCameraOrigin) <= SUPPRESSION_CACHE_CAMERA_MOVE_TOLERANCE_SQR;
        }
    }

    private record OcclusionHistory(int occludedTicks, long lastGameTime) {}

    private static double rayT(double origin, double delta, double boundary, int step) {
        if (step == 0 || Math.abs(delta) < EPSILON) {
            return Double.POSITIVE_INFINITY;
        }
        return (boundary - origin) / delta;
    }

    private static double rayStep(double delta) {
        if (Math.abs(delta) < EPSILON) {
            return Double.POSITIVE_INFINITY;
        }
        return Math.abs(1.0D / delta);
    }

    private static boolean isFinite(Vec3 vec) {
        return vec != null
            && Double.isFinite(vec.x)
            && Double.isFinite(vec.y)
            && Double.isFinite(vec.z);
    }
}
