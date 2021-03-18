package com.zylr.minescapeaddons.addons.armor.model.mystic;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class MysticRobeModel extends BipedModel<LivingEntity> {

    public MysticRobeModel(float modelSize) {
        super(modelSize, 0.0F, 64, 64);

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(0, 0).addBox(-5.0F, -2.0F, -3.0F, 2.0F, 1.0F, 7.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(3.0F, -2.0F, -3.0F, 2.0F, 1.0F, 7.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(-5.0F, -3.0F, -3.0F, 2.0F, 1.0F, 6.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(3.0F, -3.0F, -3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
        bipedBody.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -3.0F, 10.0F, 1.0F, 7.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(-3.0F, 2.0F, -3.0F, 2.0F, 9.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(1.0F, 2.0F, -3.0F, 2.0F, 9.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(-5.0F, -4.0F, -3.0F, 1.0F, 2.0F, 7.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(4.0F, -4.0F, -3.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
        bipedBody.setTextureOffset(36, 0).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 13.0F, 4.0F, 0.25F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-10.9102F, -3.4697F, 0.0F);
        bipedBody.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -0.6109F);
        cube_r1.setTextureOffset(0, 0).addBox(-2.0F, -0.5F, -3.0F, 4.0F, 1.0F, 7.0F, 0.0F, true);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(10.9102F, -3.4697F, 0.0F);
        bipedBody.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.6109F);
        cube_r2.setTextureOffset(0, 0).addBox(-2.0F, -0.5F, -3.0F, 4.0F, 1.0F, 7.0F, 0.0F, false);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-8.8309F, -3.3771F, 0.0F);
        bipedBody.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.5672F);
        cube_r3.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -3.0F, 4.0F, 2.0F, 7.0F, 0.0F, true);

        ModelRenderer cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(8.8309F, -3.3771F, 0.0F);
        bipedBody.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.5672F);
        cube_r4.setTextureOffset(0, 0).addBox(-2.5F, -1.0F, -3.0F, 4.0F, 2.0F, 7.0F, 0.0F, false);

        ModelRenderer cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-6.1299F, -2.5952F, 0.0F);
        bipedBody.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.3054F);
        cube_r5.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 7.0F, 0.0F, true);

        ModelRenderer cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(6.1299F, -2.5952F, 0.0F);
        bipedBody.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.3054F);
        cube_r6.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 7.0F, 0.0F, false);

        ModelRenderer cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(1.0F, 2.5F, -3.0F);
        bipedBody.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, 1.1345F);
        cube_r7.setTextureOffset(0, 0).addBox(-1.0F, -0.5F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-1.0F, 2.5F, -3.0F);
        bipedBody.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, -1.1345F);
        cube_r8.setTextureOffset(0, 0).addBox(-2.0F, -0.5F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, true);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedRightArm.setTextureOffset(42, 19).addBox(-3.0F, -3.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.25F, false);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedLeftArm.setTextureOffset(42, 19).addBox(-1.0F, -3.0F, -2.0F, 4.0F, 11.0F, 4.0F, 0.25F, false);

        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        bipedRightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        bipedLeftLeg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
