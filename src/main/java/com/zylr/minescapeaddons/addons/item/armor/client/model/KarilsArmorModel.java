package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class KarilsArmorModel extends ArmorModel {

	public KarilsArmorModel(ModelPart root) {
		super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 1.0F;
		this.zAdjustment = 6.0F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 29).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 59).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0016F, 4.52F, -3.4665F, 1.5708F, -0.829F, 1.5708F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 59).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1016F, 2.27F, -3.4665F, 1.5708F, -0.829F, 1.5708F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.9688F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.1016F, 6.77F, -3.4665F, 1.5708F, 0.829F, -1.5708F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(58, 59).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1016F, 6.77F, -3.4665F, 1.5708F, -0.829F, 1.5708F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.9688F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1016F, 2.27F, -3.4665F, 1.5708F, 0.829F, -1.5708F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(28, 54).addBox(-3.15F, -4.75F, -4.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.35F, 12.5F, 2.0F));

		PartDefinition cube_r6 = waist.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1402F, -2.984F, 0.4987F, 0.3142F, 0.3011F, -0.7421F));

		PartDefinition cube_r7 = waist.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1402F, -2.984F, -4.4987F, -0.3142F, -0.3011F, -0.7421F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 44).addBox(-2.45F, -1.15F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(44, 46).addBox(-3.45F, 5.6892F, 2.1946F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 46).addBox(-3.45F, 5.6892F, -3.1946F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 40).addBox(0.55F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(40, 29).addBox(-4.25F, -2.25F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(58, 59).addBox(-0.731F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.23F, -2.2165F, 1.5708F, 0.3491F, 1.5708F));

		PartDefinition cube_r9 = right_arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(58, 59).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.23F, -2.2165F, 1.5708F, -0.829F, 1.5708F));

		PartDefinition cube_r10 = right_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(58, 59).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.205F, 2.2165F, -1.5708F, 0.829F, 1.5708F));

		PartDefinition cube_r11 = right_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(58, 59).addBox(-0.731F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.205F, 2.2165F, -1.5708F, -0.3491F, 1.5708F));

		PartDefinition cube_r12 = right_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(58, 59).addBox(-0.1786F, -0.7489F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.73F, 0.1F, -1.5708F, -0.3927F, 1.5708F));

		PartDefinition cube_r13 = right_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(58, 59).addBox(-0.8784F, -0.7603F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.73F, 0.1F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r14 = right_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(58, 59).addBox(-0.1147F, -0.0967F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.73F, 0.1F, -1.5708F, 0.3927F, 1.5708F));

		PartDefinition cube_r15 = right_arm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(58, 59).addBox(-0.2729F, -0.63F, -0.5685F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 59).addBox(-0.2729F, -0.23F, -0.5685F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.73F, 0.1F, -1.5708F, 0.0F, 1.309F));

		PartDefinition cube_r16 = right_arm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(58, 59).addBox(-0.2729F, -0.63F, -0.4315F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 59).addBox(-0.2729F, -0.23F, -0.4315F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3516F, -2.73F, 0.1F, -1.5708F, 0.0F, 1.8326F));

		PartDefinition cube_r17 = right_arm.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(44, 44).addBox(-3.15F, -2.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r18 = right_arm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(44, 40).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8125F, 3.8933F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(44, 44).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r20 = right_arm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(44, 46).addBox(0.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1446F, 3.6892F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r21 = right_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(44, 45).mirror().addBox(-1.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.55F, 3.6892F, -0.9446F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 46).mirror().addBox(-1.55F, 5.6892F, 2.1946F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 46).mirror().addBox(-1.55F, 5.6892F, -3.1946F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 40).mirror().addBox(-1.55F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 29).mirror().addBox(-1.75F, -2.25F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 44).mirror().addBox(-0.55F, -1.15F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r22 = left_arm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.9688F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.23F, -2.2165F, 1.5708F, 0.829F, -1.5708F));

		PartDefinition cube_r23 = left_arm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.9688F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.205F, 2.2165F, -1.5708F, -0.829F, -1.5708F));

		PartDefinition cube_r24 = left_arm.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.269F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.205F, 2.2165F, -1.5708F, 0.3491F, -1.5708F));

		PartDefinition cube_r25 = left_arm.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.8214F, -0.7489F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.73F, 0.1F, -1.5708F, 0.3927F, -1.5708F));

		PartDefinition cube_r26 = left_arm.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.1216F, -0.7603F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.73F, 0.1F, -1.5708F, 0.7854F, -1.5708F));

		PartDefinition cube_r27 = left_arm.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.8853F, -0.0967F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.73F, 0.1F, -1.5708F, -0.3927F, -1.5708F));

		PartDefinition cube_r28 = left_arm.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.7271F, -0.63F, -0.5685F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(58, 59).mirror().addBox(-0.7271F, -0.23F, -0.5685F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.73F, 0.1F, -1.5708F, 0.0F, -1.309F));

		PartDefinition cube_r29 = left_arm.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.7271F, -0.63F, -0.4315F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(58, 59).mirror().addBox(-0.7271F, -0.23F, -0.4315F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.73F, 0.1F, -1.5708F, 0.0F, -1.8326F));

		PartDefinition cube_r30 = left_arm.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(58, 59).mirror().addBox(-0.269F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3516F, -2.23F, -2.2165F, 1.5708F, -0.3491F, -1.5708F));

		PartDefinition cube_r31 = left_arm.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(44, 44).mirror().addBox(-1.85F, -2.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.3F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r32 = left_arm.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(44, 40).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.8125F, 3.8933F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r33 = left_arm.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(44, 44).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.3F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r34 = left_arm.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(44, 46).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.1446F, 3.6892F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r35 = left_arm.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(44, 45).addBox(0.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.55F, 3.6892F, -0.9446F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 54).addBox(-2.6F, 3.5308F, 2.5058F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 39).mirror().addBox(-2.6F, 1.25F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r36 = right_leg.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 54).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9058F, 4.0308F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r37 = right_leg.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 54).addBox(-2.5F, -0.5F, 2.5058F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 4.0308F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r38 = right_leg.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 54).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 5.5308F, -3.0058F, 3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r39 = right_leg.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3098F, -0.184F, 2.8487F, 0.3142F, 0.3011F, -0.7421F));

		PartDefinition cube_r40 = right_leg.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4902F, 1.291F, 3.0737F, 0.3142F, 0.3011F, -0.7421F));

		PartDefinition cube_r41 = right_leg.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4902F, 1.291F, -3.0737F, -0.3142F, -0.3011F, -0.7421F));

		PartDefinition cube_r42 = right_leg.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3098F, -0.184F, -2.8487F, -0.3142F, -0.3011F, -0.7421F));

		PartDefinition cube_r43 = right_leg.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 57).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r44 = right_leg.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, -3.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.8848F, 0.5763F, -0.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r45 = right_leg.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, -3.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r46 = right_leg.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(0, 57).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-2.4F, 3.5308F, 2.5058F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 39).addBox(-2.4F, 1.25F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r47 = left_leg.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8902F, 1.616F, 3.1237F, 0.3142F, 0.3011F, -0.7421F));

		PartDefinition cube_r48 = left_leg.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3598F, -0.734F, 2.7487F, 0.3142F, 0.3011F, -0.7421F));

		PartDefinition cube_r49 = left_leg.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3598F, -0.734F, -2.7487F, -0.3142F, -0.3011F, -0.7421F));

		PartDefinition cube_r50 = left_leg.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8902F, 1.616F, -3.1237F, -0.3142F, -0.3011F, -0.7421F));

		PartDefinition cube_r51 = left_leg.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r52 = left_leg.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, -3.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8848F, 0.5763F, -0.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r53 = left_leg.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, -3.0F, -3.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r54 = left_leg.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 57).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition cube_r55 = left_leg.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-2.5F, -0.5F, 2.5058F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 4.0308F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r56 = left_leg.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.9058F, 4.0308F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r57 = left_leg.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 5.5308F, -3.0058F, 3.1416F, 0.0F, 3.1416F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}