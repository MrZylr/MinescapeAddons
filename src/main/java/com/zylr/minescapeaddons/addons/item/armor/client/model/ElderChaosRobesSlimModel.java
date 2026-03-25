package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ElderChaosRobesSlimModel extends ArmorModel {

	public ElderChaosRobesSlimModel(ModelPart root) {
		super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 2.0F;
		this.zAdjustment = 5.5F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 29).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(29, 56).addBox(-4.5F, 7.75F, -2.5F, 9.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(-1, 0).addBox(-3.0F, 9.0F, -4.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(3, 20).addBox(-4.5F, -3.25F, -2.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(41, 56).addBox(-1.5F, -1.25F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(14, 60).addBox(-2.5F, 5.6892F, 2.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 60).addBox(-2.5F, 5.6892F, -3.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 23).addBox(0.5F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r1 = right_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(41, 56).addBox(-2.15F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r2 = right_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 23).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8625F, 3.8933F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r3 = right_arm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(41, 56).addBox(-2.15F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 49).addBox(0.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1946F, 3.6892F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r5 = right_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(40, 48).mirror().addBox(-1.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 3.6892F, -0.9446F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-0.5F, -1.25F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false)
		.texOffs(13, 60).mirror().addBox(-1.5F, 5.6892F, 2.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 60).mirror().addBox(-1.5F, 5.6892F, -3.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 23).mirror().addBox(-1.5F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r6 = left_arm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-1.85F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.35F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r7 = left_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 23).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.8625F, 3.8933F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r8 = left_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-1.85F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.35F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r9 = left_arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(41, 49).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.1946F, 3.6892F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r10 = left_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(40, 48).addBox(0.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.6892F, -0.9446F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40, 56).addBox(-2.6F, 2.546F, 2.3321F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r11 = right_leg.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(60, 13).addBox(-1.0F, 2.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 0.15F, -1.55F, -0.5556F, 0.0779F, 0.2956F));

		PartDefinition cube_r12 = right_leg.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(58, 13).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.65F, -2.25F, -0.3811F, 0.0779F, 0.2956F));

		PartDefinition cube_r13 = right_leg.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(60, 7).addBox(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, 3.05F, 0.6F, 0.7795F, -0.409F, -0.8397F));

		PartDefinition cube_r14 = right_leg.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(58, 0).addBox(-2.0F, -2.0F, 1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.05F, 1.0F, 2.7F, 0.2618F, -0.829F, 0.0F));

		PartDefinition cube_r15 = right_leg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(60, 7).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, 3.05F, -0.6F, -0.7795F, 0.409F, -0.8397F));

		PartDefinition cube_r16 = right_leg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(58, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.05F, 1.0F, -2.7F, -0.2618F, 0.829F, 0.0F));

		PartDefinition cube_r17 = right_leg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(58, 0).addBox(-2.0F, -2.0F, 1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.0F, 2.35F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r18 = right_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(60, 7).addBox(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9F, 3.1F, 2.85F, 0.1872F, 0.1841F, -0.7681F));

		PartDefinition cube_r19 = right_leg.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(60, 7).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9F, 3.1F, -2.85F, -0.1872F, -0.1841F, -0.7681F));

		PartDefinition cube_r20 = right_leg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(58, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, 1.0F, -2.35F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r21 = right_leg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(13, 60).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.6164F, 3.92F, -0.5236F, 0.0F, -3.1416F));

		PartDefinition cube_r22 = right_leg.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(13, 60).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.6164F, -3.92F, 0.5236F, 0.0F, -3.1416F));

		PartDefinition cube_r23 = right_leg.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(40, 56).addBox(-2.15F, -2.0F, 0.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, 7.6892F, 2.6946F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r24 = right_leg.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(40, 56).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r25 = right_leg.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(41, 49).addBox(-0.5F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.02F, 9.6164F, 0.0F, 0.0F, 0.0F, -2.618F));

		PartDefinition cube_r26 = right_leg.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(13, 52).mirror().addBox(-0.5F, -2.3F, -2.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 54).mirror().addBox(-0.5F, -2.3F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0002F, 7.11F, 0.0F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r27 = right_leg.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 23).addBox(-6.6642F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2321F, 3.546F, -0.5F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r28 = right_leg.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(13, 52).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.9F, 0.75F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r29 = right_leg.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(52, 23).addBox(0.0F, -1.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7794F, 7.5156F, -1.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r30 = right_leg.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(52, 23).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r31 = right_leg.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(52, 32).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition cube_r32 = right_leg.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(52, 37).addBox(-2.5F, -3.5F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.046F, -2.8321F, 0.0F, -3.1416F, 0.0F));

		PartDefinition cube_r33 = right_leg.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 56).addBox(-0.525F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3116F, 8.546F, 2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r34 = right_leg.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 56).addBox(-0.525F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4366F, 8.546F, 2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r35 = right_leg.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4366F, 8.546F, -2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r36 = right_leg.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3116F, 8.546F, -2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r37 = right_leg.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5116F, 8.546F, -2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r38 = right_leg.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6366F, 8.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r39 = right_leg.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 5.5004F, 2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r40 = right_leg.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2504F, 5.5004F, 1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r41 = right_leg.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.243F, 5.5324F, 1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r42 = right_leg.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1702F, 5.5324F, 1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r43 = right_leg.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3702F, 5.5324F, 1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r44 = right_leg.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.443F, 5.5324F, 1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r45 = right_leg.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4505F, 5.5004F, 1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r46 = right_leg.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8045F, 5.5004F, 2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r47 = right_leg.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5116F, 5.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r48 = right_leg.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6366F, 5.546F, 2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r49 = right_leg.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3702F, 5.5324F, -1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r50 = right_leg.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.443F, 5.5324F, -1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r51 = right_leg.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8045F, 5.5004F, -2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r52 = right_leg.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4505F, 5.5004F, -1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r53 = right_leg.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 5.5004F, -2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r54 = right_leg.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1702F, 5.5324F, -1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r55 = right_leg.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.243F, 5.5324F, -1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r56 = right_leg.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2504F, 5.5004F, -1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r57 = right_leg.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(52, 42).addBox(3.5F, -2.5F, -0.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9F, 8.0948F, -3.2738F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-2.4F, 2.546F, 2.3321F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r58 = left_leg.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(60, 13).mirror().addBox(0.0F, 2.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2F, 0.35F, -1.55F, -0.5556F, -0.0779F, -0.2956F));

		PartDefinition cube_r59 = left_leg.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(58, 13).mirror().addBox(0.0F, -1.0F, -1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.85F, -2.25F, -0.3811F, -0.0779F, -0.2956F));

		PartDefinition cube_r60 = left_leg.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-1.0F, -2.0F, -1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.05F, 1.0F, -2.6F, -0.2618F, -0.829F, 0.0F));

		PartDefinition cube_r61 = left_leg.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(60, 7).mirror().addBox(-1.0F, 3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.7F, 3.05F, -0.5F, -0.7795F, -0.409F, 0.8397F));

		PartDefinition cube_r62 = left_leg.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-1.0F, -2.0F, 1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.05F, 1.0F, 2.7F, 0.2618F, 0.829F, 0.0F));

		PartDefinition cube_r63 = left_leg.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(60, 7).mirror().addBox(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.7F, 3.05F, 0.6F, 0.7795F, 0.409F, 0.8397F));

		PartDefinition cube_r64 = left_leg.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(60, 7).addBox(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, 3.1F, 2.75F, 0.1872F, 0.1841F, -0.7681F));

		PartDefinition cube_r65 = left_leg.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(58, 0).addBox(-2.0F, -2.0F, 1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 1.0F, 2.25F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r66 = left_leg.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(60, 7).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, 3.1F, -2.75F, -0.1872F, -0.1841F, -0.7681F));

		PartDefinition cube_r67 = left_leg.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(58, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 1.0F, -2.25F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r68 = left_leg.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(13, 60).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.6164F, 3.92F, -0.5236F, 0.0F, 3.1416F));

		PartDefinition cube_r69 = left_leg.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(13, 60).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.6164F, -3.92F, 0.5236F, 0.0F, 3.1416F));

		PartDefinition cube_r70 = left_leg.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-2.85F, -2.0F, 0.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.45F, 7.6892F, 2.6946F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r71 = left_leg.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r72 = left_leg.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(41, 49).mirror().addBox(-0.5F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.02F, 9.6164F, 0.0F, 0.0F, 0.0F, 2.618F));

		PartDefinition cube_r73 = left_leg.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(13, 52).addBox(-0.5F, -2.3F, -2.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(13, 53).addBox(-0.5F, -2.3F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0002F, 7.11F, 0.0F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r74 = left_leg.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(52, 23).mirror().addBox(5.6642F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.2321F, 3.546F, -0.5F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r75 = left_leg.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(13, 52).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.75F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r76 = left_leg.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(52, 23).mirror().addBox(-1.0F, -1.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7794F, 7.5156F, -1.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r77 = left_leg.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(52, 23).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r78 = left_leg.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(52, 32).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition cube_r79 = left_leg.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(52, 37).mirror().addBox(-2.5F, -3.5F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 6.046F, -2.8321F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r80 = left_leg.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(46, 56).mirror().addBox(-0.475F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 8.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r81 = left_leg.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(46, 56).mirror().addBox(-0.475F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 8.546F, 2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r82 = left_leg.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 8.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r83 = left_leg.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 8.546F, -2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r84 = left_leg.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5116F, 8.546F, -2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r85 = left_leg.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6366F, 8.546F, -2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r86 = left_leg.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6045F, 5.5004F, 2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r87 = left_leg.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2504F, 5.5004F, 1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r88 = left_leg.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.243F, 5.5324F, 1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r89 = left_leg.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.1702F, 5.5324F, 1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r90 = left_leg.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3702F, 5.5324F, 1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r91 = left_leg.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.443F, 5.5324F, 1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r92 = left_leg.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4505F, 5.5004F, 1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r93 = left_leg.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8045F, 5.5004F, 2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r94 = left_leg.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5116F, 5.546F, 2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r95 = left_leg.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6366F, 5.546F, 2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r96 = left_leg.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3702F, 5.5324F, -1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r97 = left_leg.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(0, 62).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.443F, 5.5324F, -1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r98 = left_leg.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8045F, 5.5004F, -2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r99 = left_leg.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(60, 43).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4505F, 5.5004F, -1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r100 = left_leg.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6045F, 5.5004F, -2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r101 = left_leg.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.1702F, 5.5324F, -1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r102 = left_leg.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(0, 62).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.243F, 5.5324F, -1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r103 = left_leg.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(60, 43).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2504F, 5.5004F, -1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r104 = left_leg.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(52, 42).mirror().addBox(-8.5F, -2.5F, -0.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.9F, 8.0948F, -3.2738F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}