package com.zylr.minescapeaddons.addons.armor.model.barrows;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class VeracsArmorModel extends BipedModel<LivingEntity> {
    public VeracsArmorModel(float modelSize) {
        super(modelSize, 0.0F, 64, 64);

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(0, 0).addBox(-3.9982F, 7.9002F, -2.0F, 1.0F, 1.0F, 1.0F, 0.25F, true);
        bipedBody.setTextureOffset(0, 0).addBox(2.0F, 2.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.25F, false);
        bipedBody.setTextureOffset(0, 0).addBox(-3.9982F, 7.9002F, -1.0F, 1.0F, 1.0F, 3.0F, 0.25F, true);
        bipedBody.setTextureOffset(0, 0).addBox(2.0F, 2.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.25F, false);
        bipedBody.setTextureOffset(34, 32).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 13.0F, 4.0F, 0.25F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(3.5F, 2.5F, 1.5F);
        bipedBody.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -1.5708F);
        cube_r1.setTextureOffset(0, 0).addBox(-1.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.25F, true);
        cube_r1.setTextureOffset(0, 0).addBox(-1.5F, -0.5F, -3.5F, 1.0F, 1.0F, 1.0F, 0.25F, true);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(3.3097F, 4.153F, 1.5F);
        bipedBody.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, -1.0036F);
        cube_r2.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.25F, true);
        cube_r2.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -3.5F, 3.0F, 1.0F, 1.0F, 0.25F, true);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.6438F, 2.1872F, 1.5F);
        bipedBody.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2182F);
        cube_r3.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.25F, false);
        cube_r3.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -3.5F, 2.0F, 1.0F, 1.0F, 0.25F, false);

        ModelRenderer cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-0.8945F, 1.2972F, 1.5F);
        bipedBody.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.6109F);
        cube_r4.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.25F, false);
        cube_r4.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -3.5F, 2.0F, 1.0F, 1.0F, 0.25F, false);

        ModelRenderer cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-1.9751F, -0.1138F, 1.5F);
        bipedBody.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, 1.0036F);
        cube_r5.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.25F, false);
        cube_r5.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -3.5F, 2.0F, 1.0F, 1.0F, 0.25F, false);

        ModelRenderer cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-2.1655F, -1.7668F, 1.5F);
        bipedBody.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 1.5708F);
        cube_r6.setTextureOffset(0, 0).addBox(0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.25F, false);
        cube_r6.setTextureOffset(0, 0).addBox(0.5F, -0.5F, -3.5F, 1.0F, 1.0F, 1.0F, 0.25F, false);

        ModelRenderer cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(1.6917F, 6.4074F, 1.5F);
        bipedBody.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.6109F);
        cube_r7.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.25F, true);
        cube_r7.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -3.5F, 3.0F, 1.0F, 1.0F, 0.25F, true);

        ModelRenderer cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-0.6657F, 7.871F, 1.5F);
        bipedBody.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, -0.2182F);
        cube_r8.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.25F, true);
        cube_r8.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -3.5F, 3.0F, 1.0F, 1.0F, 0.25F, true);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedRightArm.setTextureOffset(40, 16).addBox(-3.0F, -3.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.25F, false);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedLeftArm.setTextureOffset(6, 29).addBox(-1.0F, -3.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);
        bipedLeftArm.setTextureOffset(0, 0).addBox(-1.0F, -2.9727F, -4.0273F, 1.0F, 3.0F, 8.0F, 0.0F, false);
        bipedLeftArm.setTextureOffset(0, 0).addBox(0.0F, -4.0F, -3.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);
        bipedLeftArm.setTextureOffset(18, 10).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.25F, false);

        ModelRenderer cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-0.5F, 2.5547F, -0.5F);
        bipedLeftArm.addChild(cube_r9);
        setRotationAngle(cube_r9, 1.5708F, 0.0F, 0.0F);
        cube_r9.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(-0.5F, -6.5F, -0.5F);
        bipedLeftArm.addChild(cube_r10);
        setRotationAngle(cube_r10, -1.5708F, 0.0F, 0.0F);
        cube_r10.setTextureOffset(0, 0).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 8.0F, 0.0F, false);

        ModelRenderer cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(-0.5F, -0.7021F, -4.3741F);
        bipedLeftArm.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.3927F, 0.0F, 0.0F);
        cube_r11.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, 3.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);

        ModelRenderer cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(-0.5F, -3.2433F, -4.3741F);
        bipedLeftArm.addChild(cube_r12);
        setRotationAngle(cube_r12, -0.3927F, 0.0F, 0.0F);
        cube_r12.setTextureOffset(0, 0).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);

        ModelRenderer cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(-0.5F, 0.8751F, -3.5549F);
        bipedLeftArm.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.7854F, 0.0F, 0.0F);
        cube_r13.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, 3.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);

        ModelRenderer cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(-0.5F, -4.8204F, -3.5549F);
        bipedLeftArm.addChild(cube_r14);
        setRotationAngle(cube_r14, -0.7854F, 0.0F, 0.0F);
        cube_r14.setTextureOffset(0, 0).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);

        ModelRenderer cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(-0.5F, 2.0187F, -2.1945F);
        bipedLeftArm.addChild(cube_r15);
        setRotationAngle(cube_r15, 1.1781F, 0.0F, 0.0F);
        cube_r15.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, 2.5F, 1.0F, 2.0F, 6.0F, 0.0F, false);

        ModelRenderer cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(-0.5F, -5.964F, -2.1945F);
        bipedLeftArm.addChild(cube_r16);
        setRotationAngle(cube_r16, -1.1781F, 0.0F, 0.0F);
        cube_r16.setTextureOffset(0, 0).addBox(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 4.0F, 0.0F, false);

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
