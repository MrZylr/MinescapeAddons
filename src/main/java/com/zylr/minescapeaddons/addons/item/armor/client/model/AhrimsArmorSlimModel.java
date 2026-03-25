package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class AhrimsArmorSlimModel extends ArmorModel {

	public AhrimsArmorSlimModel(ModelPart root) {
		super(root);

		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 1.5F;
		this.zAdjustment = 6.0F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 6).addBox(-5.11F, 3.26F, -1.36F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-5.11F, 2.51F, -0.61F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-5.11F, 2.51F, -3.39F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-5.11F, 3.26F, -2.64F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-5.11F, 3.76F, -1.99F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-4.39F, 0.26F, -3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-5.14F, -0.49F, -3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(-4, 0).mirror().addBox(1.111F, -0.49F, -0.61F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-4, 0).addBox(-5.111F, -0.49F, -0.61F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(5, 0).mirror().addBox(1.111F, -0.49F, -3.39F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 0).addBox(-5.111F, -0.49F, -3.39F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-3.0F, 0.26F, -3.39F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-3.64F, 0.76F, -3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-0.27F, 0.76F, -3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(0.48F, 0.26F, -3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(1.11F, -0.49F, -3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-5.14F, -0.49F, 3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-3.0F, 0.26F, 3.39F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-3.64F, 0.76F, 3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-0.27F, 0.76F, 3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(0.48F, 0.26F, 3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(1.11F, -0.49F, 3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-4.39F, 0.26F, 3.39F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).mirror().addBox(5.11F, 3.26F, -2.64F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(5.11F, 2.51F, -3.39F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(5.11F, 2.51F, -0.61F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(5.11F, 3.26F, -1.36F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(5.11F, 3.76F, -2.01F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(28, 54).addBox(-3.15F, -4.75F, -4.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.35F, 12.5F, 2.0F));

		PartDefinition cube_r1 = waist.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 9).mirror().addBox(0.75F, -0.375F, -1.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(0.75F, -0.375F, 0.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(0.75F, -1.125F, -1.995F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.119F, -2.9044F, -2.015F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r2 = waist.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 9).mirror().addBox(0.75F, 0.125F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.119F, -3.1544F, -2.015F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r3 = waist.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 10).addBox(0.265F, -0.5F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-3.73F, -1.25F, -0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-2.21F, -0.25F, -0.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-2.99F, -0.5F, -0.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-1.46F, -0.5F, -0.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(1.27F, -1.25F, -0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.59F, -2.74F, 0.99F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r4 = waist.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(8, 9).addBox(-0.75F, -0.375F, 0.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-0.75F, -1.125F, -1.995F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(-0.75F, -0.375F, -1.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.819F, -2.9044F, -2.015F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r5 = waist.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(8, 9).addBox(-0.75F, 0.125F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.819F, -3.1544F, -2.015F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r6 = waist.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(3, 10).addBox(-6.265F, -0.5F, 0.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-3.01F, -0.5F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-7.005F, -1.25F, 0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-2.005F, -1.25F, 0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-4.735F, -0.5F, 0.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(3, 10).addBox(-5.485F, -0.25F, 0.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.865F, -2.74F, -4.99F, -0.1745F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(1, 59).addBox(-1.5F, -1.25F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(1, 61).addBox(-2.5F, 5.6892F, 2.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(1, 61).addBox(-2.5F, 5.6892F, -3.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 53).addBox(0.5F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r7 = right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(1, 59).addBox(-2.15F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(14, 53).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8625F, 3.8933F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r9 = right_arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(1, 59).addBox(-2.15F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r10 = right_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(13, 53).addBox(0.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1946F, 3.6892F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r11 = right_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(12, 53).mirror().addBox(-1.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 3.6892F, -0.9446F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 59).mirror().addBox(-0.5F, -1.25F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false)
		.texOffs(0, 61).mirror().addBox(-1.5F, 5.6892F, 2.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 61).mirror().addBox(-1.5F, 5.6892F, -3.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(14, 53).mirror().addBox(-1.5F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r12 = left_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 59).mirror().addBox(-1.85F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.35F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r13 = left_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.8625F, 3.8933F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r14 = left_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 59).mirror().addBox(-1.85F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.35F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r15 = left_arm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(13, 53).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.1946F, 3.6892F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r16 = left_arm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(12, 53).addBox(0.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.6892F, -0.9446F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-2.36F, 3.4706F, 3.369F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.1F, 4.2206F, 3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.88F, 4.7206F, 3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-1.63F, 4.2206F, 3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(-3.469F, 3.4706F, -2.01F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(-3.469F, 4.2206F, 0.25F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(-3.469F, 4.7206F, -0.53F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(-3.469F, 4.2206F, -1.28F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.1F, 4.2206F, -3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-2.36F, 3.4706F, -3.369F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.88F, 4.7206F, -3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-1.63F, 4.2206F, -3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).addBox(-2.6F, 2.546F, 2.3321F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r17 = right_leg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(13, 61).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.6164F, 3.92F, -0.5236F, 0.0F, -3.1416F));

		PartDefinition cube_r18 = right_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(13, 61).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.6164F, -3.92F, 0.5236F, 0.0F, -3.1416F));

		PartDefinition cube_r19 = right_leg.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 57).addBox(-2.15F, -2.0F, 0.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, 7.6892F, 2.6946F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r20 = right_leg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 57).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r21 = right_leg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(13, 57).addBox(-0.5F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.02F, 9.6164F, 0.0F, 0.0F, 0.0F, -2.618F));

		PartDefinition cube_r22 = right_leg.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(13, 52).mirror().addBox(-0.5F, -2.3F, -2.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 53).mirror().addBox(-0.5F, -2.3F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0002F, 7.11F, 0.0F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r23 = right_leg.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 55).addBox(-6.6642F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2321F, 3.546F, -0.5F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r24 = right_leg.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.9F, 0.75F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r25 = right_leg.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(13, 56).addBox(0.0F, -1.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7794F, 7.5156F, -1.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r26 = right_leg.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(13, 55).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r27 = right_leg.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 57).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition cube_r28 = right_leg.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 57).addBox(-2.5F, -3.5F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.046F, -2.8321F, 0.0F, -3.1416F, 0.0F));

		PartDefinition cube_r29 = right_leg.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.525F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3116F, 8.546F, 2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r30 = right_leg.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.525F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4366F, 8.546F, 2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r31 = right_leg.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4366F, 8.546F, -2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r32 = right_leg.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3116F, 8.546F, -2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r33 = right_leg.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5116F, 8.546F, -2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r34 = right_leg.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6366F, 8.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r35 = right_leg.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 5.5004F, 2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r36 = right_leg.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2504F, 5.5004F, 1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r37 = right_leg.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.243F, 5.5324F, 1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r38 = right_leg.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1702F, 5.5324F, 1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r39 = right_leg.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3702F, 5.5324F, 1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r40 = right_leg.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.443F, 5.5324F, 1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r41 = right_leg.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4505F, 5.5004F, 1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r42 = right_leg.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8045F, 5.5004F, 2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r43 = right_leg.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5116F, 5.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r44 = right_leg.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6366F, 5.546F, 2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r45 = right_leg.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3702F, 5.5324F, -1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r46 = right_leg.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.443F, 5.5324F, -1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r47 = right_leg.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8045F, 5.5004F, -2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r48 = right_leg.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4505F, 5.5004F, -1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r49 = right_leg.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 5.5004F, -2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r50 = right_leg.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1702F, 5.5324F, -1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r51 = right_leg.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.243F, 5.5324F, -1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r52 = right_leg.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2504F, 5.5004F, -1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r53 = right_leg.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(0, 57).addBox(3.5F, -2.5F, -0.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9F, 8.0948F, -3.2738F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition cube_r54 = right_leg.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(8, 10).mirror().addBox(-1.265F, -0.25F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.515F, 0.5F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(0.265F, -0.25F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-1.995F, -1.25F, 0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-1.265F, 6.25F, 0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.515F, 6.75F, 0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(0.265F, 6.25F, 0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-1.995F, 5.5F, 0.7F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.365F, 0.71F, -3.29F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r55 = right_leg.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(8, 9).mirror().addBox(0.25F, 3.0F, -1.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(0.25F, 3.0F, 0.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(0.25F, 3.5F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(0.25F, 2.25F, -1.995F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(-0.25F, -3.5F, 0.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(-0.25F, -2.75F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 9).mirror().addBox(-0.25F, -3.5F, -1.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 6).mirror().addBox(-0.25F, -4.5F, -1.995F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5112F, 3.9888F, -0.015F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r56 = right_leg.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(8, 10).mirror().addBox(0.265F, 6.25F, -0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-1.995F, 5.5F, -0.7F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-1.265F, 6.25F, -0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.515F, 6.75F, -0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-1.995F, -1.25F, -0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-1.265F, -0.25F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(-0.515F, 0.5F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).mirror().addBox(0.265F, -0.25F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.365F, 0.71F, 3.29F, 0.1745F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.4F, 2.546F, 2.3321F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 10).addBox(0.63F, 4.2206F, -3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.12F, 4.7206F, -3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.64F, 3.4706F, -3.369F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.9F, 4.2206F, -3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(3.469F, 4.2206F, -1.28F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(3.469F, 4.7206F, -0.53F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(3.469F, 4.2206F, 0.25F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(3.469F, 3.4706F, -2.01F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(0.63F, 4.2206F, 3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.12F, 4.7206F, 3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.9F, 4.2206F, 3.369F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.64F, 3.4706F, 3.369F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r57 = left_leg.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(8, 10).addBox(-1.265F, -0.25F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.485F, 0.5F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(0.265F, -0.25F, -0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-2.005F, -1.25F, -0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.485F, 6.75F, -0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(0.265F, 6.25F, -0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-2.005F, 5.5F, -0.7F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-1.265F, 6.25F, -0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.365F, 0.71F, 3.29F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r58 = left_leg.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(0, 6).addBox(0.25F, -4.5F, -1.995F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(0.25F, -3.5F, -1.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(0.25F, -2.75F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(0.25F, -3.5F, 0.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-0.25F, 2.25F, -1.995F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(-0.25F, 3.5F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(-0.25F, 3.0F, 0.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 9).addBox(-0.25F, 3.0F, -1.265F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5112F, 3.9888F, -0.015F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r59 = left_leg.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(0, 10).addBox(-2.005F, 5.5F, 0.7F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-1.265F, 6.25F, 0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.485F, 6.75F, 0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(0.265F, 6.25F, 0.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-2.005F, -1.25F, 0.2F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-1.265F, -0.25F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(-0.485F, 0.5F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 10).addBox(0.265F, -0.25F, 0.2F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.365F, 0.71F, -3.29F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r60 = left_leg.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(13, 61).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.6164F, 3.92F, -0.5236F, 0.0F, 3.1416F));

		PartDefinition cube_r61 = left_leg.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(13, 61).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.6164F, -3.92F, 0.5236F, 0.0F, 3.1416F));

		PartDefinition cube_r62 = left_leg.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.85F, -2.0F, 0.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.45F, 7.6892F, 2.6946F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r63 = left_leg.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r64 = left_leg.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(13, 57).mirror().addBox(-0.5F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.02F, 9.6164F, 0.0F, 0.0F, 0.0F, 2.618F));

		PartDefinition cube_r65 = left_leg.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(13, 52).addBox(-0.5F, -2.3F, -2.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(13, 53).addBox(-0.5F, -2.3F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0002F, 7.11F, 0.0F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r66 = left_leg.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(13, 55).mirror().addBox(5.6642F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.2321F, 3.546F, -0.5F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r67 = left_leg.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.75F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r68 = left_leg.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(13, 56).mirror().addBox(-1.0F, -1.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7794F, 7.5156F, -1.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r69 = left_leg.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(13, 55).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r70 = left_leg.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition cube_r71 = left_leg.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.5F, -3.5F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 6.046F, -2.8321F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r72 = left_leg.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-0.475F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 8.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r73 = left_leg.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-0.475F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 8.546F, 2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r74 = left_leg.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 8.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r75 = left_leg.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 8.546F, -2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r76 = left_leg.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5116F, 8.546F, -2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r77 = left_leg.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6366F, 8.546F, -2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r78 = left_leg.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6045F, 5.5004F, 2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r79 = left_leg.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2504F, 5.5004F, 1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r80 = left_leg.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.243F, 5.5324F, 1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r81 = left_leg.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.1702F, 5.5324F, 1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r82 = left_leg.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3702F, 5.5324F, 1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r83 = left_leg.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.443F, 5.5324F, 1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r84 = left_leg.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4505F, 5.5004F, 1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r85 = left_leg.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8045F, 5.5004F, 2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r86 = left_leg.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5116F, 5.546F, 2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r87 = left_leg.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6366F, 5.546F, 2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r88 = left_leg.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3702F, 5.5324F, -1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r89 = left_leg.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.443F, 5.5324F, -1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r90 = left_leg.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8045F, 5.5004F, -2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r91 = left_leg.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4505F, 5.5004F, -1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r92 = left_leg.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6045F, 5.5004F, -2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r93 = left_leg.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.1702F, 5.5324F, -1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r94 = left_leg.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.243F, 5.5324F, -1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r95 = left_leg.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2504F, 5.5004F, -1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r96 = left_leg.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-8.5F, -2.5F, -0.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.9F, 8.0948F, -3.2738F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}