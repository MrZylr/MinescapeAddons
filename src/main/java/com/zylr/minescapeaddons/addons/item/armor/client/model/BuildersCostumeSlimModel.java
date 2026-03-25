package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BuildersCostumeSlimModel extends ArmorModel {

	public BuildersCostumeSlimModel(ModelPart root) {
        super(root);
		this.xRotAdjustment = 0.5F;
		this.yAdjustment = 1.5F;
		this.zAdjustment = 12.75F;
    }

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(1.01F))
		.texOffs(0, 0).addBox(-5.5F, -1.35F, -3.1F, 11.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-5.5F, -1.35F, -0.8F, 11.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(37, 12).addBox(-4.0F, 3.0F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 12).mirror().addBox(3.0F, 3.0F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(44, 49).addBox(-4.0F, -17.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(1.1F))
		.texOffs(36, 46).addBox(-5.0F, -17.75F, -2.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.5F, 25.5F, -0.5F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(34, 0).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-6.25F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(34, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(6.25F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.5F))
		.texOffs(40, 59).mirror().addBox(-1.0F, 5.4F, -2.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(48, 0).mirror().addBox(-2.0F, 7.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(40, 59).addBox(0.65F, 1.0F, -2.65F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-2.0F, 7.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(48, 56).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F))
		.texOffs(41, 12).mirror().addBox(1.0F, 12.0F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 12).mirror().addBox(-2.0F, 8.75F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 12).mirror().addBox(-2.0F, 10.55F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 12).mirror().addBox(-2.0F, 12.0F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 12).mirror().addBox(1.0F, 10.55F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(41, 12).mirror().addBox(1.0F, 8.75F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(48, 56).mirror().addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false)
		.texOffs(41, 12).addBox(-2.0F, 12.0F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(41, 12).addBox(1.0F, 8.75F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(41, 12).addBox(1.0F, 10.55F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(41, 12).addBox(1.0F, 12.0F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(41, 12).addBox(-2.0F, 10.55F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(41, 12).addBox(-2.0F, 8.75F, -3.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}