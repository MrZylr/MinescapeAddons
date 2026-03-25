package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DhideArmorModel extends ArmorModel {

	public DhideArmorModel(ModelPart root) {
		super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 4.0F;
		this.zAdjustment = 1.5F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 3).addBox(-5.0F, -0.4F, -0.25F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 3).addBox(-5.0F, -0.4F, -2.75F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.55F, 8.9F, 1.825F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.55F, 8.9F, 1.825F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 8.875F, 1.825F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 7.7F, 1.825F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.15F, 7.7F, 1.825F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 3.35F, 1.9F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 6.35F, 1.9F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 6.35F, 1.9F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 4.9F, 1.9F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 3.35F, 1.9F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 4.9F, -1.9F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 3.35F, -1.9F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 3.35F, -1.9F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.55F, 8.9F, -1.825F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.55F, 8.9F, -1.825F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.15F, 7.7F, -1.825F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 7.7F, -1.825F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(53, 53).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 8.875F, -1.825F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 6.35F, -1.9F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(52, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 6.35F, -1.9F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 20).addBox(-9.0F, 5.25F, 0.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(4.5F, 2.5F, -2.5F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition cube_r21 = right_leg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.15F, 12.125F, -1.7F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r22 = right_leg.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.15F, 12.125F, -1.7F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r23 = right_leg.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.95F, 9.65F, -1.675F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r24 = right_leg.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.95F, 7.35F, -1.675F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r25 = right_leg.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.75F, 5.975F, 1.7F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r26 = right_leg.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3F, 5.975F, 1.7F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r27 = right_leg.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3F, 5.975F, -1.7F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r28 = right_leg.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.75F, 5.975F, -1.7F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r29 = right_leg.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.15F, 4.875F, -1.7F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r30 = right_leg.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.15F, 4.875F, -1.7F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r31 = right_leg.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-2.0F, -3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.95F, 2.9F, -1.675F, -0.0436F, 0.0436F, 0.7854F));

		PartDefinition cube_r32 = right_leg.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.15F, 12.125F, 1.7F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r33 = right_leg.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.15F, 12.125F, 1.7F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r34 = right_leg.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.95F, 9.65F, 1.675F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r35 = right_leg.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.95F, 7.35F, 1.675F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r36 = right_leg.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.15F, 4.875F, 1.7F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r37 = right_leg.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(54, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.15F, 4.875F, 1.7F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition cube_r38 = right_leg.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(53, 53).mirror().addBox(-2.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.95F, 2.9F, 1.675F, 0.0436F, -0.0436F, 0.7854F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition cube_r39 = left_leg.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 12.125F, 1.7F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r40 = left_leg.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, 12.125F, 1.7F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r41 = left_leg.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(53, 53).addBox(0.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.95F, 9.65F, 1.675F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r42 = left_leg.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(53, 53).addBox(0.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.95F, 7.35F, 1.675F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r43 = left_leg.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 4.875F, 1.7F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r44 = left_leg.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, 4.875F, 1.7F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r45 = left_leg.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(53, 53).addBox(0.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.95F, 2.9F, 1.675F, 0.0436F, 0.0436F, -0.7854F));

		PartDefinition cube_r46 = left_leg.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, 12.125F, -1.7F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r47 = left_leg.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(53, 53).addBox(0.0F, -3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.95F, 9.65F, -1.675F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r48 = left_leg.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 12.125F, -1.7F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r49 = left_leg.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.15F, 4.875F, -1.7F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r50 = left_leg.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(54, 53).addBox(1.0F, -3.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 4.875F, -1.7F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r51 = left_leg.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(53, 53).addBox(0.0F, -3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.95F, 7.35F, -1.675F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition cube_r52 = left_leg.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(53, 53).addBox(0.0F, -3.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.95F, 2.9F, -1.675F, -0.0436F, -0.0436F, -0.7854F));

		PartDefinition bone = left_leg.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(52, 17).addBox(8.0F, 10.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.3F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.7F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(8.0F, 10.0F, 1.2F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(55, 24).addBox(8.95F, 8.0F, 2.9F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(55, 24).addBox(8.975F, 7.5F, 1.2F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(55, 24).addBox(8.95F, 8.0F, -0.6F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.3F, 1.2F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.7F, 1.2F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.4F, -7.5F, -1.6F));

		PartDefinition cube_r53 = bone.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5057F, 11.8693F, 4.199F, 0.0F, 1.5708F, 0.0873F));

		PartDefinition cube_r54 = bone.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5929F, 11.1269F, 4.199F, 0.0F, 1.5708F, -0.0873F));

		PartDefinition cube_r55 = bone.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(55, 30).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 13.6464F, 3.0393F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r56 = bone.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(55, 30).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 13.1464F, 1.3393F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r57 = bone.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(55, 30).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 13.6464F, -0.4607F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r58 = bone.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, -0.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r59 = bone.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, 0.3F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r60 = bone.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.95F, 8.4F, 1.3F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r61 = bone.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.95F, 8.4F, 2.1F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r62 = bone.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, 3.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r63 = bone.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, 3.8F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r64 = bone.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5057F, 11.8693F, -0.999F, 0.0F, -1.5708F, 0.0873F));

		PartDefinition cube_r65 = bone.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5929F, 11.1269F, -0.999F, 0.0F, -1.5708F, -0.0873F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}