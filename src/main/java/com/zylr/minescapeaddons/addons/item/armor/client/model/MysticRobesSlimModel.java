package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MysticRobesSlimModel extends ArmorModel {

	public MysticRobesSlimModel(ModelPart root) {
		super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 3.0F;
		this.zAdjustment = -0.0F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 29).addBox(-5.0F, -0.25F, -3.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.15F, 1.75F, -3.05F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.15F, -0.35F, -3.05F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.4F, -0.351F, 3.049F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-0.6F, -0.351F, 3.049F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 5).mirror().addBox(6.9978F, -3.1154F, -2.15F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 3).mirror().addBox(6.2261F, -3.1154F, -2.9981F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 3).mirror().addBox(6.2261F, -3.1154F, -1.9916F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 5).mirror().addBox(6.9978F, -3.1154F, -0.9397F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 5).addBox(-8.9978F, -3.1154F, -0.9397F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(4, 5).addBox(-8.9978F, -3.1154F, -2.15F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(4, 3).addBox(-7.2261F, -3.1154F, -2.9981F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(4, 3).addBox(-7.2261F, -3.1154F, -1.9916F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.4725F, 0.2013F, -3.05F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(2.15F, -0.35F, -3.05F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(2.15F, 1.75F, -3.05F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 5).addBox(0.0F, 0.0F, -1.6948F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(4, 5).addBox(0.0F, 0.0F, -0.7698F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(4, 5).addBox(-1.0F, 0.0F, -1.1948F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.9978F, -2.5296F, -0.2552F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(7, 7).addBox(-0.8911F, -0.1025F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5873F, -2.4837F, 0.0052F, 1.5708F, -0.7854F, 0.2618F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(7, 7).addBox(-0.8911F, -0.1025F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.6873F, -2.4837F, 0.0052F, 1.5708F, -0.7854F, 0.2967F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(-0.1089F, -0.1025F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.6873F, -2.4837F, 0.0052F, 1.5708F, 0.7854F, -0.2967F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(-0.1089F, -0.1025F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(7.5873F, -2.4837F, 0.0052F, 1.5708F, 0.7854F, -0.2618F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 5).mirror().addBox(-1.0F, 0.0F, -0.7698F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 5).mirror().addBox(-1.0F, 0.0F, -1.6948F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 5).mirror().addBox(0.0F, 0.0F, -1.1948F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.9978F, -2.5296F, -0.2552F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(-1, 0).mirror().addBox(0.0F, -4.1F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3576F, 4.1425F, -3.05F, 0.0F, 0.0F, -0.2793F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -3.5F, 0.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.8709F, 4.1487F, -3.05F, 0.0F, 0.0F, -0.2793F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-1, 0).addBox(-2.0F, -4.1F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3576F, 4.1425F, -3.05F, 0.0F, 0.0F, 0.2793F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -3.5F, 0.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8709F, 4.1487F, -3.05F, 0.0F, 0.0F, 0.2793F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(4, 7).addBox(-0.5F, -1.0F, -2.55F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-0.5F, -1.0F, -2.4436F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-0.5F, -0.6F, -2.4436F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(4, 7).addBox(-0.5F, -0.6F, -2.55F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5797F, -2.0547F, -0.498F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(6, 7).addBox(-0.8911F, -1.1025F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4123F, -2.6337F, 0.0052F, 1.5708F, -0.7854F, 0.5236F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(5, 6).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 7).addBox(-1.475F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 6).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.9898F, -3.1493F, 0.367F, 0.0718F, -0.3864F, -0.1886F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(5, 6).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(5, 6).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.9898F, -3.1493F, -0.3567F, -0.0718F, 0.3864F, -0.1886F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(6, 7).addBox(-2.45F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.1027F, -3.3186F, -0.7241F, -0.0718F, 0.3864F, -0.1886F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(5, 6).addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.6151F, -3.2285F, 0.5222F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(5, 6).addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.6151F, -3.2285F, -0.5119F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(7, 7).addBox(-0.8911F, -0.1025F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.1873F, -2.9337F, 0.0052F, 1.5708F, -0.7854F, -0.0785F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(6, 7).addBox(-2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 7).addBox(-2.0F, -1.575F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6222F, -2.3329F, -0.9773F, -1.5708F, 1.1781F, 0.0F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(7, 7).addBox(-2.0F, -1.575F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 7).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.6222F, -2.3329F, 0.9877F, 1.5708F, -1.1781F, 0.0F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(6, 6).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.2332F, -2.8329F, 1.0946F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(6, 6).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.2332F, -2.8329F, -1.0843F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(5, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.4242F, -1.7241F, 1.0155F, -0.247F, -0.3085F, 0.6931F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(5, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.4242F, -1.7241F, -1.0052F, 0.247F, 0.3085F, 0.6931F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(5, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.1912F, -2.4219F, 1.112F, -0.1572F, -0.3614F, 0.4215F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(5, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.1912F, -2.4219F, -1.1017F, 0.1572F, 0.3614F, 0.4215F));

		PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(5, 6).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.8434F, -2.1154F, -2.0742F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(5, 6).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.8434F, -2.1154F, 2.0845F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(7, 7).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0731F, -2.3329F, 1.4239F, 1.5708F, -0.7854F, 0.0F));

		PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(6, 7).mirror().addBox(-1.1089F, -1.1025F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.4123F, -2.6337F, 0.0052F, 1.5708F, 0.7854F, -0.5236F));

		PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(-0.1089F, -0.1025F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.1873F, -2.9337F, 0.0052F, 1.5708F, 0.7854F, 0.0785F));

		PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(6, 6).mirror().addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.2332F, -2.8329F, 1.0946F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r33 = body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(1.0F, -1.575F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(6, 7).mirror().addBox(0.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.6222F, -2.3329F, -0.9773F, -1.5708F, -1.1781F, 0.0F));

		PartDefinition cube_r34 = body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(1.0F, -1.575F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(6, 7).mirror().addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(11.6222F, -2.3329F, 0.9877F, 1.5708F, 1.1781F, 0.0F));

		PartDefinition cube_r35 = body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.0731F, -2.3329F, 1.4239F, 1.5708F, 0.7854F, 0.0F));

		PartDefinition cube_r36 = body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-3.5F, -1.0F, -3.45F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 7).mirror().addBox(-4.5F, -1.0F, -3.5564F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 2).mirror().addBox(-3.5F, -0.6F, -3.45F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 7).mirror().addBox(-4.5F, -0.6F, -3.5564F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.5797F, -2.0547F, 0.5084F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r37 = body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.1912F, -2.4219F, 1.112F, -0.1572F, 0.3614F, -0.4215F));

		PartDefinition cube_r38 = body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.4242F, -1.7241F, 1.0155F, -0.247F, 0.3085F, -0.6931F));

		PartDefinition cube_r39 = body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(6, 6).mirror().addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.2332F, -2.8329F, -1.0843F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r40 = body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.4242F, -1.7241F, -1.0052F, 0.247F, -0.3085F, -0.6931F));

		PartDefinition cube_r41 = body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.6151F, -3.2285F, 0.5222F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r42 = body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(6, 7).mirror().addBox(1.45F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.1027F, -3.3186F, -0.7241F, -0.0718F, -0.3864F, 0.1886F));

		PartDefinition cube_r43 = body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(6, 7).mirror().addBox(0.475F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 6).mirror().addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 6).mirror().addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.9898F, -3.1493F, 0.367F, 0.0718F, 0.3864F, 0.1886F));

		PartDefinition cube_r44 = body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 6).mirror().addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.9898F, -3.1493F, -0.3567F, -0.0718F, -0.3864F, 0.1886F));

		PartDefinition cube_r45 = body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.6151F, -3.2285F, -0.5119F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r46 = body.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.1912F, -2.4219F, -1.1017F, 0.1572F, -0.3614F, -0.4215F));

		PartDefinition cube_r47 = body.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.8434F, -2.1154F, -2.0742F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r48 = body.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(5, 6).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.8434F, -2.1154F, 2.0845F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r49 = body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-1.0F, -4.5F, 0.0F, 2.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 0).addBox(-7.3F, -4.5F, 0.0F, 2.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.15F, -0.35F, -1.55F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r50 = body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, 0.5F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(-7.3F, 0.5F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.15F, -0.45F, 1.65F, 1.5708F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(28, 54).addBox(-4.5F, 7.75F, -2.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(1, 59).addBox(-1.4F, -1.25F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(41, 46).addBox(0.6F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(43, 50).addBox(-2.4F, 5.6892F, -3.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(43, 50).addBox(-2.4F, 5.6892F, 2.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r51 = right_arm.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(42, 46).addBox(0.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0946F, 3.6892F, -0.5F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition cube_r52 = right_arm.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(41, 47).mirror().addBox(-1.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.6F, 3.6892F, -0.9446F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r53 = right_arm.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(43, 50).addBox(-2.15F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r54 = right_arm.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(42, 46).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7625F, 3.8933F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r55 = right_arm.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(43, 50).addBox(-2.15F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(1, 59).mirror().addBox(-0.6F, -1.25F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false)
		.texOffs(42, 50).mirror().addBox(-1.6F, 5.6892F, 2.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(42, 50).mirror().addBox(-1.6F, 5.6892F, -3.1946F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 46).mirror().addBox(-1.6F, 1.75F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r56 = left_arm.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(42, 46).mirror().addBox(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0946F, 3.6892F, -0.5F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r57 = left_arm.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(42, 50).mirror().addBox(-1.85F, -2.0F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.25F, 3.8933F, -1.8625F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r58 = left_arm.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(42, 46).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.7625F, 3.8933F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r59 = left_arm.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(42, 50).mirror().addBox(-1.85F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.25F, 3.8933F, 1.8625F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r60 = left_arm.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(41, 47).addBox(0.0F, 2.0F, -4.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6F, 3.6892F, -0.9446F, -3.1416F, 0.0F, -3.1416F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 57).addBox(-2.6F, 2.546F, 2.3321F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r61 = right_leg.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-0.8F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.1675F, 10.563F, -3.9266F, 1.2053F, 1.0059F, 0.5299F));

		PartDefinition cube_r62 = right_leg.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-1.3F, 0.0F, 0.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.1675F, 10.563F, -3.9266F, 0.5989F, 0.4802F, -0.31F));

		PartDefinition cube_r63 = right_leg.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-0.8F, 0.0F, -0.7F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.1675F, 10.563F, 3.9266F, -1.2053F, -1.0059F, 0.5299F));

		PartDefinition cube_r64 = right_leg.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-1.3F, 0.0F, -1.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.1675F, 10.563F, 3.9266F, -0.5989F, -0.4802F, -0.31F));

		PartDefinition cube_r65 = right_leg.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-1.3F, 0.0F, 0.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.2175F, 10.563F, 0.8984F, 0.0F, 1.1781F, -0.7854F));

		PartDefinition cube_r66 = right_leg.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-0.8F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.2175F, 10.563F, 0.8984F, -3.1416F, 1.1781F, 2.3562F));

		PartDefinition cube_r67 = right_leg.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-0.8F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.2175F, 10.563F, -0.0516F, -3.1416F, 0.7854F, 2.3562F));

		PartDefinition cube_r68 = right_leg.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-1.3F, 0.0F, 0.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.2175F, 10.563F, -2.0016F, 0.0F, 1.1781F, -0.7854F));

		PartDefinition cube_r69 = right_leg.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(20, 14).mirror().addBox(-0.8F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.2175F, 10.563F, -2.0016F, -3.1416F, 1.1781F, 2.3562F));

		PartDefinition cube_r70 = right_leg.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(45, 52).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.6164F, 3.92F, -0.5236F, 0.0F, -3.1416F));

		PartDefinition cube_r71 = right_leg.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(45, 52).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 9.6164F, -3.92F, 0.5236F, 0.0F, -3.1416F));

		PartDefinition cube_r72 = right_leg.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(0, 57).addBox(-2.15F, -2.0F, 0.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.45F, 7.6892F, 2.6946F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r73 = right_leg.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(0, 57).addBox(-3.15F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r74 = right_leg.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(45, 48).addBox(-0.5F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.02F, 9.6164F, 0.0F, 0.0F, 0.0F, -2.618F));

		PartDefinition cube_r75 = right_leg.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(45, 46).mirror().addBox(-0.5F, 1.7F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 44).mirror().addBox(-0.5F, -2.3F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0002F, 7.11F, 0.0F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r76 = right_leg.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(43, 44).mirror().addBox(-1.0F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 55).addBox(-6.6642F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2321F, 3.546F, -0.5F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r77 = right_leg.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(43, 44).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.9F, 0.75F, -0.5F, -3.1416F, 0.0F, 2.9671F));

		PartDefinition cube_r78 = right_leg.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(13, 56).addBox(0.0F, -1.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7794F, 7.5156F, -1.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r79 = right_leg.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(13, 55).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, 0.1745F));

		PartDefinition cube_r80 = right_leg.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(0, 57).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition cube_r81 = right_leg.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(0, 57).addBox(-2.5F, -3.5F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.046F, -2.8321F, 0.0F, -3.1416F, 0.0F));

		PartDefinition cube_r82 = right_leg.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(46, 47).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3116F, 8.546F, 2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r83 = right_leg.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(48, 49).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 47).addBox(-0.525F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4366F, 8.546F, 2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r84 = right_leg.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(48, 47).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4366F, 8.546F, -2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r85 = right_leg.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(46, 47).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3116F, 8.546F, -2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r86 = right_leg.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5116F, 8.546F, -2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r87 = right_leg.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6366F, 8.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r88 = right_leg.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(44, 47).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 5.5004F, 2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r89 = right_leg.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(44, 47).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2504F, 5.5004F, 1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r90 = right_leg.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(44, 52).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.243F, 5.5324F, 1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r91 = right_leg.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(45, 51).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1702F, 5.5324F, 1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r92 = right_leg.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(45, 52).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3702F, 5.5324F, 1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r93 = right_leg.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(45, 51).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.443F, 5.5324F, 1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r94 = right_leg.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(45, 47).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4505F, 5.5004F, 1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r95 = right_leg.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(45, 47).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8045F, 5.5004F, 2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r96 = right_leg.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5116F, 5.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r97 = right_leg.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(7, 57).mirror().addBox(-0.475F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 57).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6366F, 5.546F, 2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r98 = right_leg.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(45, 52).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3702F, 5.5324F, -1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r99 = right_leg.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(45, 51).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.443F, 5.5324F, -1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r100 = right_leg.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(45, 47).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8045F, 5.5004F, -2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r101 = right_leg.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(45, 47).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4505F, 5.5004F, -1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r102 = right_leg.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(44, 47).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6045F, 5.5004F, -2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r103 = right_leg.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(44, 52).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1702F, 5.5324F, -1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r104 = right_leg.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(45, 51).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.243F, 5.5324F, -1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r105 = right_leg.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(44, 47).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2504F, 5.5004F, -1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r106 = right_leg.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(0, 57).addBox(3.5F, -2.5F, -0.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9F, 8.0948F, -3.2738F, -2.9671F, 0.0F, -3.1416F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.4F, 2.546F, 2.3321F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r107 = left_leg.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(47, 46).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(47, 46).mirror().addBox(-0.475F, 0.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 5.546F, -2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r108 = left_leg.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(47, 46).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 5.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r109 = left_leg.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(47, 46).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 5.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r110 = left_leg.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(45, 52).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.6164F, 3.92F, -0.5236F, 0.0F, 3.1416F));

		PartDefinition cube_r111 = left_leg.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(45, 52).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 9.6164F, -3.92F, 0.5236F, 0.0F, 3.1416F));

		PartDefinition cube_r112 = left_leg.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.85F, -2.0F, 0.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.45F, 7.6892F, 2.6946F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r113 = left_leg.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-1.85F, -2.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.55F, 0.75F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r114 = left_leg.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(45, 48).mirror().addBox(-0.5F, -1.0F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.02F, 9.6164F, 0.0F, 0.0F, 0.0F, 2.618F));

		PartDefinition cube_r115 = left_leg.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(44, 46).addBox(-0.5F, 1.7F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(43, 43).addBox(-0.5F, -2.3F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0002F, 7.11F, 0.0F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r116 = left_leg.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(43, 44).addBox(0.0F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(13, 55).mirror().addBox(5.6642F, -3.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.2321F, 3.546F, -0.5F, -3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r117 = left_leg.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(43, 44).addBox(0.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 0.75F, -0.5F, -3.1416F, 0.0F, -2.9671F));

		PartDefinition cube_r118 = left_leg.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(13, 56).mirror().addBox(-1.0F, -1.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7794F, 7.5156F, -1.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r119 = left_leg.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(13, 55).mirror().addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0848F, 0.5763F, -0.5F, 3.1416F, 0.0F, -0.1745F));

		PartDefinition cube_r120 = left_leg.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.5F, -2.0F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.6632F, -2.4924F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition cube_r121 = left_leg.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-2.5F, -3.5F, -0.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1F, 6.046F, -2.8321F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r122 = left_leg.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(47, 46).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.3116F, 8.546F, 2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r123 = left_leg.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(47, 46).mirror().addBox(-0.475F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(47, 46).mirror().addBox(-0.475F, -6.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 8.546F, 2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r124 = left_leg.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(47, 46).mirror().addBox(-0.475F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.4366F, 8.546F, -2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r125 = left_leg.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5116F, 8.546F, -2.5013F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r126 = left_leg.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, -3.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -6.0F, -0.65F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6366F, 8.546F, -2.3763F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r127 = left_leg.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(45, 46).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6045F, 5.5004F, 2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r128 = left_leg.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(45, 46).mirror().addBox(-0.475F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2504F, 5.5004F, 1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r129 = left_leg.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(45, 51).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.243F, 5.5324F, 1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r130 = left_leg.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(47, 49).mirror().addBox(-0.475F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.1702F, 5.5324F, 1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r131 = left_leg.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(45, 51).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3702F, 5.5324F, 1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r132 = left_leg.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(46, 49).addBox(-1.525F, 4.2F, 0.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.443F, 5.5324F, 1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r133 = left_leg.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(45, 46).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4505F, 5.5004F, 1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r134 = left_leg.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(45, 46).addBox(-0.525F, 3.0F, 0.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8045F, 5.5004F, 2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r135 = left_leg.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5116F, 5.546F, 2.5013F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r136 = left_leg.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(7, 57).addBox(-0.525F, 0.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 57).addBox(-0.525F, -3.0F, -0.35F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6366F, 5.546F, 2.3763F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r137 = left_leg.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(20, 14).addBox(-0.2F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2175F, 10.563F, -0.0516F, -3.1416F, -0.7854F, -2.3562F));

		PartDefinition cube_r138 = left_leg.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(20, 14).addBox(-0.2F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2175F, 10.563F, 0.8984F, -3.1416F, -1.1781F, -2.3562F));

		PartDefinition cube_r139 = left_leg.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(20, 14).addBox(0.3F, 0.0F, 0.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2175F, 10.563F, 0.8984F, 0.0F, -1.1781F, 0.7854F));

		PartDefinition cube_r140 = left_leg.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(20, 14).addBox(-0.2F, 0.0F, -0.7F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1675F, 10.563F, 3.9266F, -1.2053F, 1.0059F, -0.5299F));

		PartDefinition cube_r141 = left_leg.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(20, 14).addBox(0.3F, 0.0F, -1.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1675F, 10.563F, 3.9266F, -0.5989F, 0.4802F, 0.31F));

		PartDefinition cube_r142 = left_leg.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(20, 14).addBox(0.3F, 0.0F, 0.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1675F, 10.563F, -3.9266F, 0.5989F, -0.4802F, 0.31F));

		PartDefinition cube_r143 = left_leg.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(20, 14).addBox(-0.2F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1675F, 10.563F, -3.9266F, 1.2053F, -1.0059F, -0.5299F));

		PartDefinition cube_r144 = left_leg.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(20, 14).addBox(0.3F, 0.0F, 0.1F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2175F, 10.563F, -2.0016F, 0.0F, -1.1781F, 0.7854F));

		PartDefinition cube_r145 = left_leg.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(20, 14).addBox(-0.2F, 0.0F, -0.3F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2175F, 10.563F, -2.0016F, -3.1416F, -1.1781F, -2.3562F));

		PartDefinition cube_r146 = left_leg.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(45, 51).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3702F, 5.5324F, -1.6005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r147 = left_leg.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(45, 49).addBox(-1.525F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.443F, 5.5324F, -1.5277F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r148 = left_leg.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(45, 46).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8045F, 5.5004F, -2.3151F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r149 = left_leg.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(45, 46).addBox(-0.525F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4505F, 5.5004F, -1.6691F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r150 = left_leg.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(45, 46).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.6045F, 5.5004F, -2.3151F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r151 = left_leg.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(45, 51).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.1702F, 5.5324F, -1.6005F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r152 = left_leg.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(44, 50).mirror().addBox(-0.475F, 4.2F, -1.85F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.243F, 5.5324F, -1.5277F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r153 = left_leg.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(45, 46).mirror().addBox(-0.475F, 3.0F, -1.65F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2504F, 5.5004F, -1.6691F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r154 = left_leg.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(0, 57).mirror().addBox(-8.5F, -2.5F, -0.5F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.9F, 8.0948F, -3.2738F, -2.9671F, 0.0F, 3.1416F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}