package com.zylr.client.items.armor.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DragonArmorModel extends ArmorModel {

	public DragonArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(1.01F))
		.texOffs(59, 42).addBox(-0.537F, 2.0876F, -3.011F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(59, 42).mirror().addBox(-0.013F, -0.1624F, -0.0001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.675F, 1.3F, -3.0099F, -0.0019F, -0.0037F, 0.2138F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(59, 42).addBox(-1.013F, 0.005F, -0.0002F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.675F, 1.3F, -3.0099F, -0.0009F, 0.0035F, 0.0087F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(59, 42).mirror().addBox(-0.013F, -0.1624F, -0.0001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.65F, 1.45F, -3.0099F, -0.0019F, -0.0037F, 0.2138F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(59, 42).mirror().addBox(0.013F, 0.005F, -0.0002F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.65F, 1.3F, -3.0099F, -0.0009F, -0.0035F, -0.0087F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(59, 42).addBox(-0.987F, -0.1624F, -0.0001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.65F, 1.45F, -3.0099F, -0.0019F, 0.0037F, -0.2138F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(59, 42).addBox(-0.987F, -0.1624F, -0.0001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.675F, 1.3F, -3.0099F, -0.0019F, 0.0037F, -0.2138F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(59, 42).mirror().addBox(0.013F, 0.005F, -0.0002F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.675F, 1.3F, -3.0099F, -0.0009F, -0.0035F, -0.0087F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(59, 42).addBox(-1.013F, 0.005F, -0.0002F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.65F, 1.3F, -3.0099F, -0.0009F, 0.0035F, 0.0087F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(59, 42).mirror().addBox(-0.5978F, -3.1862F, 0.0033F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5978F, 5.4862F, -3.0117F, 0.0009F, -0.0035F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(59, 42).addBox(-0.4022F, -3.1862F, 0.0033F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5978F, 5.4862F, -3.0117F, 0.0009F, 0.0035F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(53, 43).mirror().addBox(-1.5F, -1.49F, 0.019F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.95F, 3.44F, -3.03F, 0.0052F, -0.0052F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(53, 43).mirror().addBox(-1.0F, -1.0F, 0.0478F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.45F, 0.95F, -3.05F, -0.0157F, -0.0035F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(53, 43).mirror().addBox(-1.5F, -1.49F, 0.019F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.675F, 4.14F, -3.03F, -0.0069F, 0.007F, -0.432F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(55, 43).mirror().addBox(-0.5F, -1.5F, 0.0019F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.775F, 4.7F, -3.013F, 0.0001F, -0.0005F, 0.2269F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(55, 43).mirror().addBox(0.0555F, -1.0257F, -0.0007F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.4173F, 6.6119F, -3.011F, 0.0087F, -0.0175F, 0.5655F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(54, 43).mirror().addBox(-2.0F, -1.0003F, -0.002F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5603F, 5.3898F, -3.009F, -0.0087F, 0.0067F, -0.192F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(54, 43).mirror().addBox(-1.0F, -0.5F, -0.0016F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.45F, 2.05F, -3.0114F, -0.0035F, -0.0017F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(54, 43).addBox(-1.0F, -0.5F, -0.0016F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.45F, 2.05F, -3.0114F, -0.0035F, 0.0017F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(54, 43).addBox(0.0F, -1.0003F, -0.002F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5603F, 5.3898F, -3.009F, -0.0087F, -0.0067F, 0.192F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(55, 43).addBox(-1.0555F, -1.0257F, -0.0007F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4173F, 6.6119F, -3.011F, 0.0087F, 0.0175F, -0.5655F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(55, 43).addBox(-0.5F, -1.5F, 0.0019F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.775F, 4.7F, -3.013F, 0.0001F, 0.0005F, -0.2269F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(53, 43).addBox(-1.5F, -1.49F, 0.019F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.675F, 4.14F, -3.03F, -0.0069F, -0.007F, 0.432F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(53, 43).addBox(-1.5F, -1.49F, 0.019F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.95F, 3.44F, -3.03F, 0.0052F, 0.0052F, 0.0F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(53, 43).addBox(-2.0F, -1.0F, 0.0478F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.45F, 0.95F, -3.05F, -0.0157F, 0.0035F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 38).addBox(-4.5F, -16.25F, -2.5F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(1.0F))
		.texOffs(0, 52).addBox(-5.0F, -4.0F, -4.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(21, 57).mirror().addBox(-4.0F, 3.0F, 3.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.25F, 2.0F, 0.0F));

		PartDefinition cube_r25 = right_arm.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(52, 50).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(-2.825F, -2.8F, 0.0F, -0.7773F, -0.0062F, 0.6014F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false)
		.texOffs(0, 52).mirror().addBox(-1.0F, -4.0F, -4.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 57).addBox(-2.0F, 3.0F, 3.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.25F, 2.0F, 0.0F));

		PartDefinition cube_r26 = left_arm.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(52, 50).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.8F)).mirror(false), PartPose.offsetAndRotation(2.825F, -2.8F, 0.0F, -0.7773F, 0.0062F, -0.6014F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 18).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F))
		.texOffs(48, 56).addBox(-2.0F, 6.775F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.7F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 18).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(48, 56).mirror().addBox(-2.0F, 6.775F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.7F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(48, 56).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r27 = right_boot.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(52, 50).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, -2.5F, -0.9954F, 0.4964F, -0.641F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(48, 56).mirror().addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition cube_r28 = left_boot.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(52, 50).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.0F, -2.475F, -0.9954F, -0.4964F, 0.641F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}