package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GuthansArmorModel extends ArmorModel {

	public GuthansArmorModel(ModelPart root) {
		super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 0.0F;
		this.zAdjustment = 12.0F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(1.01F))
		.texOffs(1, 53).addBox(-5.0F, 2.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 53).addBox(-2.0F, 2.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 53).addBox(1.0F, 2.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 53).addBox(4.0F, 2.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(36, 46).addBox(-4.5F, -16.25F, -2.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(33, 1).addBox(-3.0F, 2.75F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(0, 53).addBox(-5.0F, 2.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 53).addBox(-5.0F, 4.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(34, 1).mirror().addBox(0.0F, 2.75F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false)
		.texOffs(0, 53).mirror().addBox(4.0F, 2.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 53).mirror().addBox(4.0F, 4.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(26, 56).addBox(-2.7F, 8.0F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(14, 53).addBox(1.375F, 1.498F, -2.675F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(15, 54).addBox(1.4F, -0.502F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 57).addBox(-2.6F, 3.296F, -3.3321F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 57).addBox(-2.6F, 3.296F, 2.3321F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.4071F, 1.296F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4071F, 1.296F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(2, 57).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 1.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.075F, 1.5F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r5 = right_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 57).addBox(-3.15F, -2.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 1.5F, -2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(26, 56).mirror().addBox(-2.3F, 8.0F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(14, 53).mirror().addBox(-2.375F, 1.498F, -2.675F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(15, 54).mirror().addBox(-2.4F, -0.502F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 57).mirror().addBox(-2.4F, 3.296F, -3.3321F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 57).mirror().addBox(-2.4F, 3.296F, 2.3321F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r6 = left_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4071F, 1.296F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r7 = left_leg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4071F, 1.296F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r8 = left_leg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(2, 57).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 1.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r9 = left_leg.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.075F, 1.5F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r10 = left_leg.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(2, 57).mirror().addBox(-1.85F, -2.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 1.5F, -2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}