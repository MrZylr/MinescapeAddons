package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ToragsArmorSlimModel extends ArmorModel {

	public ToragsArmorSlimModel(ModelPart root) {
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

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -0.25F, -3.0F, 10.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.8479F, -0.7135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 57).mirror().addBox(-0.8479F, -0.3135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5373F, 8.1529F, -3.3053F, 3.1416F, 1.4399F, 3.1416F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.9688F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5373F, 8.1529F, -3.3053F, -2.3957F, 1.0228F, -2.3166F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.269F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5373F, 8.1529F, -3.3053F, 2.1007F, 0.7119F, 1.9363F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.9688F, -0.7783F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5373F, 8.1529F, -3.3053F, 2.3957F, 1.0228F, 2.3166F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(52, 57).mirror().addBox(-0.8479F, -0.7135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 57).mirror().addBox(-0.8479F, -0.3135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5373F, 8.1529F, -3.3053F, -3.1416F, 0.9163F, -3.1416F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.7783F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5373F, 8.1529F, -3.3053F, 2.3957F, -1.0228F, -2.3166F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.7135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5373F, 8.1529F, -3.3053F, -3.1416F, -0.9163F, 3.1416F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1521F, -0.7135F, -0.5362F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5373F, 8.1529F, -3.3053F, 3.1416F, -1.4399F, -3.1416F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.2217F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5373F, 8.1529F, -3.3053F, -2.3957F, -1.0228F, 2.3166F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(52, 57).addBox(-0.731F, -0.731F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5373F, 8.1529F, -3.3053F, 2.1007F, -0.7119F, -1.9363F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -4.0F, -2.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(3.5F, 11.75F, -0.5F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(51, 2).addBox(-1.5F, 3.75F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F))
		.texOffs(42, 16).addBox(-2.5F, -2.25F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(41, 16).mirror().addBox(-1.5F, -2.25F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(50, 2).mirror().addBox(-0.5F, 3.75F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r11 = left_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0312F, -0.7783F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2094F, -2.7592F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition cube_r12 = left_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(52, 57).addBox(-0.6658F, -0.8036F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1123F, -2.7496F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition cube_r13 = left_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 57).addBox(-0.0734F, -0.3098F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1123F, -2.7496F, 0.0F, 0.0F, 0.0F, 1.9199F));

		PartDefinition cube_r14 = left_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1572F, -0.811F, -0.5376F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-0.1572F, -0.411F, -0.5376F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1123F, -2.7496F, 0.0F, 0.0F, 0.2618F, 1.5272F));

		PartDefinition cube_r15 = left_arm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1572F, -0.811F, -0.4624F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1123F, -2.7496F, 0.0F, 0.0F, -0.2618F, 1.5272F));

		PartDefinition cube_r16 = left_arm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(52, 57).addBox(-0.1521F, -0.3135F, -0.4638F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2094F, -2.7592F, 0.0F, 0.0F, -0.2618F, 1.5272F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 36).addBox(-2.75F, -0.7F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(-2.75F, 4.3F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 54).addBox(-2.75F, 5.3F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 12.0F, 0.0F));

		PartDefinition cube_r17 = right_leg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(32, 53).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 5.79F, -2.8631F, -3.1416F, 1.0908F, 1.5708F));

		PartDefinition cube_r18 = right_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(31, 52).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 4.81F, -2.6131F, 0.0F, 1.5708F, 1.5708F));

		PartDefinition cube_r19 = right_leg.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(32, 53).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 3.81F, -2.8631F, 3.1416F, 1.0908F, -1.5708F));

		PartDefinition cube_r20 = right_leg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(31, 52).mirror().addBox(-2.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.6173F, 4.8F, -2.4668F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r21 = right_leg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(31, 52).addBox(1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1173F, 4.8F, -2.4668F, 0.0F, -0.3927F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-2.25F, -0.7F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 47).mirror().addBox(-2.25F, 4.3F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 54).mirror().addBox(-2.25F, 5.3F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.75F, 12.0F, 0.0F));

		PartDefinition cube_r22 = left_leg.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(31, 52).mirror().addBox(-2.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.1173F, 4.8F, -2.4668F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r23 = left_leg.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(32, 53).mirror().addBox(0.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.25F, 3.81F, -2.8631F, 3.1416F, -1.0908F, 1.5708F));

		PartDefinition cube_r24 = left_leg.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(31, 52).addBox(1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6173F, 4.8F, -2.4668F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r25 = left_leg.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(31, 52).mirror().addBox(-0.5F, -2.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.25F, 4.81F, -2.6131F, 0.0F, -1.5708F, -1.5708F));

		PartDefinition cube_r26 = left_leg.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(32, 53).mirror().addBox(0.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.25F, 5.79F, -2.8631F, -3.1416F, -1.0908F, -1.5708F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}