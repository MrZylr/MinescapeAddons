package com.zylr.minescapeaddons.addons.armor.model.barrows;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class GuthansArmorModel extends BipedModel<LivingEntity> {

    public GuthansArmorModel(float modelSize) {
        super(modelSize, 0.0F, 64, 64);

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
        bipedBody.setTextureOffset(0, 0).addBox(-4.0F, 2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(0.75F, 2.0F, -2.75F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(2.75F, 2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(0, 0).addBox(-1.75F, 2.0F, -2.75F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedRightArm.setTextureOffset(24, 0).addBox(-3.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.25F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-2.0F, 7.0F, -0.25F);
        bipedRightArm.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.7854F, -0.7854F, -0.0873F);
        cube_r1.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-2.0F, 5.0F, -0.25F);
        bipedRightArm.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.7854F, -0.7854F, 0.0F);
        cube_r2.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedLeftArm.setTextureOffset(24, 0).addBox(-1.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.25F, false);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(2.0F, 5.0F, -0.25F);
        bipedLeftArm.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.7854F, 0.7854F, 0.0F);
        cube_r3.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(2.0F, 7.0F, -0.25F);
        bipedLeftArm.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.7854F, 0.7854F, 0.0873F);
        cube_r4.setTextureOffset(0, 0).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
