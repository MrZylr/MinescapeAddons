package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DharoksArmorSlimModel extends ArmorModel {

	public DharoksArmorSlimModel(ModelPart root) {
		super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 3.5F;
		this.zAdjustment = -0.0F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -0.25F, -3.0F, 10.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0033F, 8.4865F, -2.95F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0033F, 6.4865F, -2.95F, 0.7854F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 36).addBox(-4.5F, 7.75F, -2.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(51, 2).addBox(-1.5F, 3.75F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(52, 57).addBox(-2.7076F, -3.8945F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 31).addBox(-0.0107F, -2.7732F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(42, 16).addBox(-2.5F, -2.25F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r3 = right_arm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 31).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(46, 31).addBox(-0.5F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6035F, -2.1991F, -2.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r4 = right_arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(46, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3787F, -1.5429F, -2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r5 = right_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(52, 57).addBox(-0.2328F, -0.8618F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8223F, -1.0092F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r6 = right_arm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 57).addBox(-0.8853F, -0.8853F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8223F, -1.0092F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r7 = right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 57).addBox(-0.2328F, -0.1382F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8223F, -1.0092F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(52, 57).addBox(-0.363F, -0.7135F, -0.5927F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.363F, -0.3135F, -0.5927F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8223F, -1.0092F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r9 = right_arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(52, 57).addBox(-0.363F, -0.7135F, -0.4073F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.363F, -0.3135F, -0.4073F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.8223F, -1.0092F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r10 = right_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.7783F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9766F, -3.1635F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r11 = right_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9766F, -3.1635F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r12 = right_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.7135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9766F, -3.1635F, 0.0F, 0.0F, 0.2618F, 0.7854F));

		PartDefinition cube_r13 = right_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.7135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9766F, -3.1635F, 0.0F, 0.0F, -0.2618F, 0.7854F));

		PartDefinition cube_r14 = right_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.7783F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2266F, -4.6635F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r15 = right_arm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(52, 57).addBox(-0.731F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2266F, -4.6635F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r16 = right_arm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2266F, -4.6635F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r17 = right_arm.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.7135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2266F, -4.6635F, 0.0F, 0.0F, 0.2618F, 1.1781F));

		PartDefinition cube_r18 = right_arm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.7135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2266F, -4.6635F, 0.0F, 0.0F, -0.2618F, 1.1781F));

		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.4033F, 3.9865F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r20 = right_arm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, 4.423F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r21 = right_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.5F, -0.1F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, 3.773F, -0.125F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r22 = right_arm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.5F, -0.1F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, 3.773F, 0.125F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r23 = right_arm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5033F, 5.9865F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r24 = right_arm.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(52, 57).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, 3.55F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(41, 16).mirror().addBox(-1.5F, -2.25F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(50, 2).mirror().addBox(-0.5F, 3.75F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r25 = left_arm.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5033F, 5.9865F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r26 = left_arm.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.4033F, 3.9865F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r27 = left_arm.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 57).mirror().addBox(-0.5F, -0.1F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, 3.773F, 0.125F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r28 = left_arm.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 57).mirror().addBox(-0.5F, -0.9F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, 4.173F, -0.125F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r29 = left_arm.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, 4.423F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r30 = left_arm.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, 3.55F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 36).addBox(-2.6F, -0.7F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(-2.6F, 4.3F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(50, 61).addBox(-1.1978F, 4.3F, -4.156F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(50, 61).addBox(-0.0176F, 4.3F, -4.156F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 54).addBox(-2.6F, 5.3F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r31 = right_leg.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(54, 57).addBox(1.0F, -1.0F, -3.925F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0978F, 5.8412F, -5.4626F, 3.1416F, -0.3927F, -1.5708F));

		PartDefinition cube_r32 = right_leg.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(55, 62).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.114F, 4.8F, -4.4494F, 3.1416F, 0.0F, -1.5708F));

		PartDefinition cube_r33 = right_leg.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(53, 60).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.114F, 5.0001F, -3.3969F, 3.1416F, -0.1309F, -1.5708F));

		PartDefinition cube_r34 = right_leg.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(55, 57).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.114F, 4.5706F, -4.2961F, 3.1416F, 0.3927F, -1.5708F));

		PartDefinition cube_r35 = right_leg.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(56, 59).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 5.29F, -2.8631F, -3.1416F, 1.0908F, 1.5708F));

		PartDefinition cube_r36 = right_leg.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(56, 59).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 4.31F, -2.8631F, 3.1416F, 1.0908F, -1.5708F));

		PartDefinition cube_r37 = right_leg.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(55, 58).addBox(1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 5.3F, -2.0F, 3.1416F, 0.6545F, -1.5708F));

		PartDefinition cube_r38 = right_leg.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(51, 62).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2813F, 4.8F, -4.2483F, 0.0F, -0.9163F, 0.0F));

		PartDefinition cube_r39 = right_leg.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(53, 59).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1923F, 4.8F, -4.645F, 0.0F, -0.6545F, 0.0F));

		PartDefinition cube_r40 = right_leg.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(52, 58).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2673F, 4.8F, -2.4668F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r41 = right_leg.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(52, 58).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 4.8F, -1.5429F, 0.0F, 0.3927F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-2.4F, -0.7F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 47).mirror().addBox(-2.4F, 4.3F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 54).mirror().addBox(-2.4F, 5.3F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(51, 58).mirror().addBox(0.1978F, 4.3F, -4.156F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(51, 58).mirror().addBox(-0.9824F, 4.3F, -4.156F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r42 = left_leg.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(54, 57).mirror().addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.114F, 5.0001F, -3.3969F, 3.1416F, 0.1309F, 1.5708F));

		PartDefinition cube_r43 = left_leg.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(56, 59).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.114F, 4.8F, -4.4494F, 3.1416F, 0.0F, 1.5708F));

		PartDefinition cube_r44 = left_leg.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(55, 59).mirror().addBox(0.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 5.29F, -2.8631F, -3.1416F, -1.0908F, -1.5708F));

		PartDefinition cube_r45 = left_leg.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(55, 59).mirror().addBox(0.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 4.31F, -2.8631F, 3.1416F, -1.0908F, 1.5708F));

		PartDefinition cube_r46 = left_leg.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(54, 57).mirror().addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.114F, 4.5706F, -4.2961F, 3.1416F, -0.3927F, 1.5708F));

		PartDefinition cube_r47 = left_leg.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(52, 59).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2813F, 4.8F, -4.2483F, 0.0F, 0.9163F, 0.0F));

		PartDefinition cube_r48 = left_leg.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(52, 59).mirror().addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1923F, 4.8F, -4.645F, 0.0F, 0.6545F, 0.0F));

		PartDefinition cube_r49 = left_leg.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(53, 57).mirror().addBox(-2.0F, -1.0F, -3.925F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0978F, 5.8412F, -5.4626F, 3.1416F, 0.3927F, 1.5708F));

		PartDefinition cube_r50 = left_leg.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(51, 58).mirror().addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2673F, 4.8F, -2.4668F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r51 = left_leg.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(54, 58).mirror().addBox(-2.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 5.3F, -2.0F, 3.1416F, -0.6545F, 1.5708F));

		PartDefinition cube_r52 = left_leg.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(51, 58).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 4.8F, -1.5429F, 0.0F, -0.3927F, 0.0F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}