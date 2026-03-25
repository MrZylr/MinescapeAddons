package com.zylr.minescapeaddons.addons.item.armor.client.model;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ChainbodyModel extends ArmorModel {

	public ChainbodyModel(ModelPart root) {
        super(root);
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

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-6.25F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(6.25F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}