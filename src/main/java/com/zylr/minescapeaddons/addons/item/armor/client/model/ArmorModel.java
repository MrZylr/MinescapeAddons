package com.zylr.minescapeaddons.addons.item.armor.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * pre-made model for armors.
 * <br> extend to make custom armors models
 */
public class ArmorModel extends HumanoidModel<LivingEntity> {
    public final ModelPart leftBoot;
    public final ModelPart rightBoot;
    public final ModelPart waist;


    public float xRotAdjustment;
    public float yAdjustment;
    public float zAdjustment;
    private boolean hasAdjusted;

    public ArmorModel(ModelPart pRoot) {
        super(pRoot);
        this.leftBoot = pRoot.getChild("left_boot");
        this.rightBoot = pRoot.getChild("right_boot");
        this.waist = this.body.getChild("waist");

        this.hasAdjusted = false;
    }

    public ArmorModel(ModelPart pRoot, Function<ResourceLocation, RenderType> pRenderType) {
        super(pRoot, pRenderType);
        this.leftBoot = pRoot.getChild("left_boot");
        this.rightBoot = pRoot.getChild("right_boot");
        this.waist = this.body.getChild("waist");
    }

    @Override
    public void setAllVisible(boolean pVisible) {
        super.setAllVisible(pVisible);
        this.leftBoot.visible = pVisible;
        this.rightBoot.visible = pVisible;
        this.waist.visible = pVisible;
    }

    /**
     * makes only one part visible for rendering
     */
    public void partVisible(EquipmentSlot slot) {
        this.setAllVisible(false);
        switch (slot) {
            case HEAD:
                this.head.visible = true;
                this.hat.visible = true;
                break;
            case CHEST:
                this.body.visible = true;
                this.rightArm.visible = true;
                this.leftArm.visible = true;
                break;
            case LEGS:
                if (this.crouching) {
                    if (!this.hasAdjusted) {
                        this.waist.xRot = this.waist.xRot + this.xRotAdjustment;
                        this.waist.y = this.waist.y + this.yAdjustment;
                        this.waist.z = this.waist.z + this.zAdjustment;
                        this.hasAdjusted = true;
                    }
                }else {
                    if (this.hasAdjusted) {
                        this.waist.xRot = this.waist.xRot - this.xRotAdjustment;
                        this.waist.y = this.waist.y - this.yAdjustment;
                        this.waist.z = this.waist.z - this.zAdjustment;
                        this.hasAdjusted = false;
                    }
                }
                this.waist.visible = true;
                this.leftLeg.visible = true;
                this.rightLeg.visible = true;
                break;
            case FEET:
                this.leftBoot.visible = true;
                this.rightBoot.visible = true;
        }
    }

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

        float v = 1.0F;
        if (pEntity.getFallFlyingTicks() > 4) {
            v = (float)pEntity.getDeltaMovement().lengthSqr();
            v /= 0.2F;
            v *= v * v;
        }

        if (v < 1.0F) {
            v = 1.0F;
        }

        this.rightBoot.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount / v;
        this.leftBoot.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount / v;
        this.rightBoot.yRot = 0.005F;
        this.leftBoot.yRot = -0.005F;
        this.rightBoot.zRot = 0.005F;
        this.leftBoot.zRot = -0.005F;
        if (this.riding) {
            this.rightBoot.xRot = -1.4137167F;
            this.rightBoot.yRot = ((float)Math.PI / 10F);
            this.rightBoot.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        }
        this.waist.copyFrom(this.body);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack stack, @NotNull VertexConsumer consumer, int packedLight, int packedOverlay, int color) {
        super.renderToBuffer(stack, consumer, packedLight, packedOverlay, color);
        this.leftBoot.render(stack, consumer, packedLight, packedOverlay, color);
        this.rightBoot.render(stack, consumer, packedLight, packedOverlay, color);
        this.waist.render(stack, consumer, packedLight, packedOverlay, color);
    }
}
