package com.zylr.minescapeaddons.addons.armor.model;


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class MetalArmorModel extends BipedModel<LivingEntity> {

	public MetalArmorModel(float modelSize) {
		super(modelSize, 0.0F, 64, 64);

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		ModelRenderer baseSkirt = new ModelRenderer(this);
		baseSkirt.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(baseSkirt);
		baseSkirt.setTextureOffset(36, 38).addBox(-4.5F, 10.0F, -2.5F, 9.0F, 7.0F, 5.0F, 0.0F, false);

		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		bipedRightArm.setTextureOffset(24, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.25F, false);

		ModelRenderer rightShoulderPad = new ModelRenderer(this);
		rightShoulderPad.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(rightShoulderPad);
		rightShoulderPad.setTextureOffset(22, 19).addBox(-3.5F, -3.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);

		ModelRenderer rightElbowPad = new ModelRenderer(this);
		rightElbowPad.setRotationPoint(10.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(rightElbowPad);
		rightElbowPad.setTextureOffset(0, 16).addBox(-13.5F, 4.0F, 1.5F, 5.0F, 2.0F, 1.0F, 0.0F, false);

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		bipedLeftArm.setTextureOffset(24, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.25F, false);

		ModelRenderer leftShoulderPad = new ModelRenderer(this);
		leftShoulderPad.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(leftShoulderPad);
		leftShoulderPad.setTextureOffset(0, 19).addBox(-1.5F, -3.0F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);

		ModelRenderer leftElbowPad = new ModelRenderer(this);
		leftElbowPad.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(leftElbowPad);
		leftElbowPad.setTextureOffset(0, 16).addBox(-1.5F, 4.0F, 1.5F, 5.0F, 2.0F, 1.0F, 0.0F, false);

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		bipedRightLeg.setTextureOffset(40, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		ModelRenderer rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(3.8F, 0.0F, 0.0F);
		bipedRightLeg.addChild(rightLeg);
		rightLeg.setTextureOffset(44, 20).addBox(-6.05F, -2.0F, -2.25F, 4.5F, 12.0F, 4.3F, 0.0F, false);

		ModelRenderer rightKneepad = new ModelRenderer(this);
		rightKneepad.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(rightKneepad);
		rightKneepad.setTextureOffset(0, 20).addBox(-2.45F, 4.0F, -2.5F, 5.0F, 3.0F, 1.0F, 0.0F, false);

		ModelRenderer rightSkirt = new ModelRenderer(this);
		rightSkirt.setRotationPoint(3.8F, 0.0F, 0.0F);
		bipedRightLeg.addChild(rightSkirt);
		rightSkirt.setTextureOffset(38, 50).addBox(-6.275F, -0.975F, -2.25F, 4.75F, 5.0F, 4.5F, 0.0F, false);

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		bipedLeftLeg.setTextureOffset(40, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		ModelRenderer leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(leftLeg);
		leftLeg.setTextureOffset(44, 20).addBox(-2.25F, -2.0F, -2.25F, 4.5F, 12.0F, 4.3F, 0.0F, false);

		ModelRenderer leftSkirt = new ModelRenderer(this);
		leftSkirt.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(leftSkirt);
		leftSkirt.setTextureOffset(38, 50).addBox(-2.25F, -0.975F, -2.25F, 4.75F, 5.0F, 4.5F, 0.0F, false);

		ModelRenderer leftKneepad = new ModelRenderer(this);
		leftKneepad.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(leftKneepad);
		leftKneepad.setTextureOffset(0, 20).addBox(-2.5F, 4.0F, -2.5F, 5.0F, 3.0F, 1.0F, 0.0F, false);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}