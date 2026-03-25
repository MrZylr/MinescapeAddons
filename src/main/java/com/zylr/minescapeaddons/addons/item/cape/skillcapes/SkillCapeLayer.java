package com.zylr.minescapeaddons.addons.item.cape.skillcapes;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.item.cape.CapeModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SkillCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final CapeModel SKILLCAPE_MODEL;
    public static final Map<Integer, ResourceLocation> SKILLCAPE_PICKAXE_CAPE_RESOURCES;
    public static final Map<Integer, ResourceLocation> SKILLCAPE_SHOVEL_CAPE_RESOURCES;

    static {
        // Cape textures mapped to their corresponding damage values
        SKILLCAPE_SHOVEL_CAPE_RESOURCES = new HashMap<>();
                SKILLCAPE_SHOVEL_CAPE_RESOURCES.put(277, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/attack.png"));
                SKILLCAPE_SHOVEL_CAPE_RESOURCES.put(279, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/attack_trimmed.png"));
                SKILLCAPE_SHOVEL_CAPE_RESOURCES.put(1160, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/defence.png"));
                SKILLCAPE_SHOVEL_CAPE_RESOURCES.put(1162, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/defence_trimmed.png"));
        SKILLCAPE_PICKAXE_CAPE_RESOURCES = new HashMap<>();
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1078, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/agility.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1080, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/agility_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1072, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/construction.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1074, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/construction_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1108, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/cooking.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1110, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/cooking_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1087, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/crafting.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1089, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/crafting_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1117, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/farming.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1119, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/farming_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1111, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/firemaking.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1113, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/firemaking_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1105, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/fishing.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1107, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/fishing_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1090, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/fletching.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1092, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/fletching_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1081, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/herblore.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1083, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/herblore_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1075, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/hitpoints.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1077, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/hitpoints_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1096, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/hunter.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1098, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/hunter_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1066, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/magic.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1068, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/magic_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1099, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/mining.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1101, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/mining_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1063, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/prayer.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1065, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/prayer_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1060, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/ranged.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1062, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/ranged_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1069, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/runecraft.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1071, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/runecraft_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1093, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/slayer.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1095, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/slayer_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1102, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/smithing.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1104, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/smithing_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(415, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/strength.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(417, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/strength_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1084, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/thieving.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1086, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/thieving_trimmed.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1114, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/woodcutting.png"));
                SKILLCAPE_PICKAXE_CAPE_RESOURCES.put(1116, ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/woodcutting_trimmed.png"));
    }

    public SkillCapeLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
                          EntityModelSet modelSet) {
        super(renderer);
        this.SKILLCAPE_MODEL = new SkillCapeModel(modelSet.bakeLayer(createModelLayer()));
    }

    public static ModelLayerLocation createModelLayer() {
        return new ModelLayerLocation(
                ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "skill_cape"), "main");
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       AbstractClientPlayer livingEntity, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (!shouldRenderCape(livingEntity) || !Config.getCustomCapes()) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.125F);
        double d0 = Mth.lerp((double)partialTicks, livingEntity.xCloakO, livingEntity.xCloak) - Mth.lerp((double)partialTicks, livingEntity.xo, livingEntity.getX());
        double d1 = Mth.lerp((double)partialTicks, livingEntity.yCloakO, livingEntity.yCloak) - Mth.lerp((double)partialTicks, livingEntity.yo, livingEntity.getY());
        double d2 = Mth.lerp((double)partialTicks, livingEntity.zCloakO, livingEntity.zCloak) - Mth.lerp((double)partialTicks, livingEntity.zo, livingEntity.getZ());
        float f = Mth.rotLerp(partialTicks, livingEntity.yBodyRotO, livingEntity.yBodyRot);
        double d3 = (double)Mth.sin(f * (float) (Math.PI / 180.0));
        double d4 = (double)(-Mth.cos(f * (float) (Math.PI / 180.0)));
        float f1 = (float)d1 * 10.0F;
        f1 = Mth.clamp(f1, -6.0F, 32.0F);
        float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
        f2 = Mth.clamp(f2, 0.0F, 150.0F);
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
        f3 = Mth.clamp(f3, -20.0F, 20.0F);
        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        float f4 = Mth.lerp(partialTicks, livingEntity.oBob, livingEntity.bob);
        f1 += Mth.sin(Mth.lerp(partialTicks, livingEntity.walkDistO, livingEntity.walkDist) * 6.0F) * 32.0F * f4;
        if (livingEntity.isCrouching()) {
            f1 += 25.0F;
        }

        poseStack.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
        poseStack.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entitySolid(Objects.requireNonNull(getCapeTextureFromArmorStand(livingEntity))));
        CapeModel capeModel = this.getCapeModel();
        if (capeModel != null) {
            this.getCapeModel().renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        }
        poseStack.popPose();

    }

    private boolean shouldRenderCape(AbstractClientPlayer player) {
        return getCapeTextureFromArmorStand(player) != null;
    }

    private CapeModel getCapeModel() {
        // Define a bounding box around a position
        Vec3 center = Minecraft.getInstance().player.position();
        AABB boundingBox = new AABB(
                center.x - 30, center.y - 30, center.z - 30,
                center.x + 30, center.y + 30, center.z + 30
        );

        List<Entity> nearbyEntities = Minecraft.getInstance().level.getEntities(
                null, // Entity to exclude (null for none)
                boundingBox
        );

        for (Entity entity : nearbyEntities) {
            List<Entity> Passengers = entity.getPassengers();
            for (Entity e : Passengers) {
                if (e instanceof ArmorStand armorStand) {
                    ItemStack headItem = armorStand.getItemBySlot(EquipmentSlot.HEAD);
                    // Check for Skill capes
                    if (headItem.getItem() == Items.DIAMOND_PICKAXE) {
                        // Skill cape
                        if (SKILLCAPE_PICKAXE_CAPE_RESOURCES.containsKey(headItem.getDamageValue()))
                            return SKILLCAPE_MODEL;
                    }
                    if (headItem.getItem() == Items.DIAMOND_SHOVEL) {
                        // Skill cape
                        if (SKILLCAPE_SHOVEL_CAPE_RESOURCES.containsKey(headItem.getDamageValue()))
                            return SKILLCAPE_MODEL;
                    }
                }
            }
        }
        return null;
    }

    private ResourceLocation getCapeTextureFromArmorStand(AbstractClientPlayer player) {
        List<Entity> passengers = player.getPassengers();
        for (Entity e : passengers) {
            if (e instanceof ArmorStand armorStand) {
                ItemStack headItem = armorStand.getItemBySlot(EquipmentSlot.HEAD);
                if (headItem.getItem() == Items.DIAMOND_PICKAXE) {
                    // Skill cape
                    if (SKILLCAPE_PICKAXE_CAPE_RESOURCES.containsKey(headItem.getDamageValue()))
                        return SKILLCAPE_PICKAXE_CAPE_RESOURCES.get(headItem.getDamageValue());
                }
                if (headItem.getItem() == Items.DIAMOND_SHOVEL) {
                    // Skill cape
                    if (SKILLCAPE_SHOVEL_CAPE_RESOURCES.containsKey(headItem.getDamageValue()))
                        return SKILLCAPE_SHOVEL_CAPE_RESOURCES.get(headItem.getDamageValue());
                }
            }
        }
        return null;
    }
}
