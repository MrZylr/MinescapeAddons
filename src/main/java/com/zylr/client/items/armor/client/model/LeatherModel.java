package com.zylr.client.items.armor.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.client.items.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class LeatherModel extends ArmorModel {

	public LeatherModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 3).addBox(-5.0F, -0.4F, -0.25F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 3).addBox(-5.0F, -0.4F, -2.75F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 20).addBox(-9.0F, 5.25F, 0.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(4.5F, 2.5F, -2.5F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition bone = left_leg.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(52, 17).addBox(8.0F, 10.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.3F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.7F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(8.0F, 10.0F, 1.2F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(55, 24).addBox(8.95F, 8.0F, 2.9F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(55, 24).addBox(8.975F, 7.5F, 1.2F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(55, 24).addBox(8.95F, 8.0F, -0.6F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.3F, 1.2F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(3.75F, 10.7F, 1.2F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.4F, -7.5F, -1.6F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5057F, 11.8693F, 4.199F, 0.0F, 1.5708F, 0.0873F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5929F, 11.1269F, 4.199F, 0.0F, 1.5708F, -0.0873F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(55, 30).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 13.6464F, 3.0393F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(55, 30).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 13.1464F, 1.3393F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(55, 30).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 13.6464F, -0.4607F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, -0.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r7 = bone.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, 0.3F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.95F, 8.4F, 1.3F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r9 = bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.95F, 8.4F, 2.1F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r10 = bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, 3.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(54, 23).addBox(0.0F, -1.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.925F, 8.9F, 3.8F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5057F, 11.8693F, -0.999F, 0.0F, -1.5708F, 0.0873F));

		PartDefinition cube_r13 = bone.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, -1.0F, -1.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5929F, 11.1269F, -0.999F, 0.0F, -1.5708F, -0.0873F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(48, 56).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(48, 56).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}