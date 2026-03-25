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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MaxCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final CapeModel MAXCAPE_MODEL;
    public static boolean isRendering = false;



    public MaxCapeLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
                          EntityModelSet modelSet) {
        super(renderer);
        this.MAXCAPE_MODEL = new MaxCapeModel(modelSet.bakeLayer(createModelLayer()));
    }

    public static ModelLayerLocation createModelLayer() {
        return new ModelLayerLocation(
                ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "max_cape"), "main");
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
                    // Check for Max capes
                    if (headItem.getItem() == Items.DIAMOND_PICKAXE) {
                        // Max cape
                        if (headItem.getDamageValue() == 12480) {
                            return MAXCAPE_MODEL;
                        }
                    }
                    if (headItem.getItem() == Items.DIAMOND_SHOVEL) {
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
                    // Max cape
                    if (headItem.getDamageValue() == 12480) {
                        isRendering = true;
                        return ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/capes/skillcapes/max_cape.png");
                    }
                }
               if (headItem.getItem() == Items.DIAMOND_SHOVEL) {
                }
            }
        }
        isRendering = false;
        return null;
    }
}
