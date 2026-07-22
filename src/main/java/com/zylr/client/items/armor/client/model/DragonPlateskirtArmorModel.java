package com.zylr.client.items.armor.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DragonPlateskirtArmorModel extends ArmorModel {

	public DragonPlateskirtArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(36, 0).addBox(-4.5F, 7.75F, -2.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(1, 44).addBox(1.375F, 0.498F, -2.675F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(14, 55).addBox(1.4F, -1.502F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 57).addBox(-2.6F, 2.296F, -3.3321F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 57).addBox(-2.6F, 2.296F, 2.3321F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 48).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(-3.25F, 7.275F, -2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 48).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(-3.25F, 7.275F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(54, 48).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(-3.25F, 7.275F, 2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(54, 49).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F))
		.texOffs(54, 49).addBox(-0.5F, -0.5F, -6.35F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(-0.1F, 7.275F, 3.175F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r5 = right_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(54, 49).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F))
		.texOffs(54, 49).addBox(-0.5F, -0.5F, -6.35F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(-2.1F, 7.275F, 3.175F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r6 = right_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.4071F, 0.296F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r7 = right_leg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, 2.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4071F, 0.296F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r8 = right_leg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 46).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 0.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r9 = right_leg.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(12, 42).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.075F, 0.5F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r10 = right_leg.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 46).addBox(-3.15F, -2.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 0.5F, -2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(1, 44).mirror().addBox(-2.375F, 0.498F, -2.675F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 56).mirror().addBox(-2.4F, -1.502F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 57).mirror().addBox(-2.4F, 2.296F, -3.3321F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 57).mirror().addBox(-2.4F, 2.296F, 2.3321F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r11 = left_leg.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(54, 48).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(3.25F, 7.275F, 2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r12 = left_leg.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(54, 48).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(3.25F, 7.275F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r13 = left_leg.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(54, 48).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(3.25F, 7.275F, -2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r14 = left_leg.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(54, 49).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F))
		.texOffs(54, 49).addBox(-0.5F, -0.5F, -6.35F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(2.1F, 7.275F, 3.175F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r15 = left_leg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(54, 49).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F))
		.texOffs(54, 49).addBox(-0.5F, -0.5F, -6.35F, 1.0F, 1.0F, 0.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.1F, 7.275F, 3.175F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r16 = left_leg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, 2.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4071F, 0.296F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r17 = left_leg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4071F, 0.296F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r18 = left_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 0.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r19 = left_leg.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(12, 42).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.075F, 0.5F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r20 = left_leg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-1.85F, -2.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 0.5F, -2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}