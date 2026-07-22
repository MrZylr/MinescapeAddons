package com.zylr.client.items.armor.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SnakskinModel extends ArmorModel {

	public SnakskinModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 3).addBox(-5.0F, -0.4F, -0.25F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 3).addBox(-5.0F, -0.4F, -2.75F, 10.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 5.5F, 0.35F, 0.3491F, -0.0017F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 4.4F, -0.2F, 0.0F, -0.0101F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -0.49F, -0.52F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 5.95F, -0.3F, 0.5672F, 0.0033F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -0.49F, -0.48F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 5.95F, 0.3F, -0.5672F, -0.0033F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 5.5F, -0.35F, -0.3491F, 0.0017F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(61, 44).addBox(0.008F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 7.96F, 0.82F, 0.0F, 0.0297F, -0.0262F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(61, 44).addBox(0.0063F, -0.45F, -0.485F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9983F, 7.885F, -0.98F, 0.3019F, -0.0251F, 0.0157F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(61, 44).addBox(0.0036F, -0.505F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9983F, 7.835F, -1.43F, 0.3019F, -0.0251F, 0.0157F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(61, 44).addBox(0.003F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 7.89F, -1.18F, 0.0F, -0.0058F, 0.0087F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(61, 44).addBox(0.0036F, -0.504F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.76F, -0.77F, 0.0F, 0.004F, -0.0087F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(61, 44).addBox(0.0035F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.91F, -1.12F, 0.0F, 0.004F, -0.0087F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(61, 44).addBox(0.0058F, -0.485F, -0.49F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 7.74F, -0.53F, 0.0F, 0.0201F, 0.0175F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(61, 44).addBox(0.008F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 7.96F, -0.82F, 0.0F, -0.0297F, -0.0262F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(61, 44).addBox(0.0058F, -0.485F, -0.51F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 7.74F, 0.53F, 0.0F, -0.0201F, 0.0175F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(61, 44).addBox(0.0035F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.91F, 1.12F, 0.0F, -0.004F, -0.0087F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(61, 44).addBox(0.0036F, -0.504F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.76F, 0.77F, 0.0F, -0.004F, -0.0087F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(61, 44).addBox(0.003F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 7.89F, 1.18F, 0.0F, 0.0058F, 0.0087F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(61, 44).addBox(0.0036F, -0.505F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9983F, 7.835F, 1.43F, -0.3019F, 0.0251F, 0.0157F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(61, 44).addBox(0.0063F, -0.45F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9983F, 7.885F, 0.98F, -0.3019F, 0.0251F, 0.0157F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.007F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 7.96F, -0.82F, 0.0F, 0.0297F, 0.0262F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0053F, -0.45F, -0.515F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9983F, 7.885F, 0.98F, -0.3019F, -0.0251F, -0.0157F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.002F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 7.89F, 1.18F, 0.0F, -0.0058F, -0.0087F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0026F, -0.505F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9983F, 7.835F, 1.43F, -0.3019F, -0.0251F, -0.0157F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0026F, -0.504F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 8.76F, 0.77F, 0.0F, 0.004F, 0.0087F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0025F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 8.91F, 1.12F, 0.0F, 0.004F, 0.0087F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0048F, -0.485F, -0.51F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 7.74F, 0.53F, 0.0F, 0.0201F, -0.0175F));

		PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.007F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 7.96F, 0.82F, 0.0F, -0.0297F, 0.0262F));

		PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0048F, -0.485F, -0.49F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 7.74F, -0.53F, 0.0F, -0.0201F, -0.0175F));

		PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0025F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 8.91F, -1.12F, 0.0F, -0.004F, 0.0087F));

		PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0026F, -0.504F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 8.76F, -0.77F, 0.0F, -0.004F, 0.0087F));

		PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.002F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 7.89F, -1.18F, 0.0F, 0.0058F, -0.0087F));

		PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0026F, -0.505F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9983F, 7.835F, -1.43F, 0.3019F, 0.0251F, -0.0157F));

		PartDefinition cube_r33 = body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(-0.0053F, -0.45F, -0.485F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.9983F, 7.885F, -0.98F, 0.3019F, 0.0251F, -0.0157F));

		PartDefinition cube_r34 = body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 4.4F, -0.2F, 0.0F, 0.0101F, 0.0F));

		PartDefinition cube_r35 = body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -0.49F, -0.48F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 5.95F, 0.3F, -0.5672F, 0.0033F, 0.0F));

		PartDefinition cube_r36 = body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 5.5F, 0.35F, 0.3491F, 0.0017F, 0.0F));

		PartDefinition cube_r37 = body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 5.5F, -0.35F, -0.3491F, -0.0017F, 0.0F));

		PartDefinition cube_r38 = body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -0.49F, -0.52F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 5.95F, -0.3F, 0.5672F, -0.0033F, 0.0F));

		PartDefinition cube_r39 = body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.45F, -0.445F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.1F, 2.0F, -2.76F, -0.0131F, 0.0F, -0.1745F));

		PartDefinition cube_r40 = body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.5F, -0.5F, 0.0077F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.1F, 1.8F, -2.76F, -0.0314F, 0.0F, 0.1309F));

		PartDefinition cube_r41 = body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.5F, -0.5F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.4F, 1.7F, -2.76F, -0.0314F, 0.0F, 0.1309F));

		PartDefinition cube_r42 = body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-0.5F, -1.5F, -0.017F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 2.5F, -2.76F, -0.0489F, -0.0436F, -0.4363F));

		PartDefinition cube_r43 = body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(51, 51).addBox(-0.55F, -0.445F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 2.0F, -2.76F, -0.0131F, 0.0F, 0.1745F));

		PartDefinition cube_r44 = body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(51, 51).addBox(-0.5F, -0.5F, 0.0077F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 1.8F, -2.76F, -0.0314F, 0.0F, -0.1309F));

		PartDefinition cube_r45 = body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(51, 51).addBox(-0.5F, -0.5F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, 1.7F, -2.76F, -0.0314F, 0.0F, -0.1309F));

		PartDefinition cube_r46 = body.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(51, 51).addBox(-0.51F, -1.48F, -0.006F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, 6.1F, -2.76F, -0.0304F, 0.0087F, -0.3194F));

		PartDefinition cube_r47 = body.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(51, 51).addBox(-0.52F, -0.54F, 0.076F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.55F, 5.725F, -2.835F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r48 = body.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(50, 51).addBox(-1.0F, -1.0F, -0.0149F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4568F, 6.2293F, -2.7262F, 0.014F, -0.0175F, 0.3054F));

		PartDefinition cube_r49 = body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-0.48F, -1.52F, 0.031F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.65F, 7.0F, -2.76F, 0.0086F, 0.0175F, 0.5192F));

		PartDefinition cube_r50 = body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.5F, -1.0F, 0.0202F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.4606F, 6.2666F, -2.77F, -0.0035F, -0.0087F, 0.9599F));

		PartDefinition cube_r51 = body.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(51, 51).addBox(-1.55F, -1.52F, 0.0705F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 7.45F, -2.76F, 0.0204F, -0.0441F, -0.24F));

		PartDefinition cube_r52 = body.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(50, 51).addBox(-1.52F, -1.52F, 0.031F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.65F, 7.0F, -2.76F, 0.0086F, -0.0175F, -0.5192F));

		PartDefinition cube_r53 = body.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.49F, -1.48F, -0.006F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.2F, 6.1F, -2.76F, -0.0304F, -0.0087F, 0.3194F));

		PartDefinition cube_r54 = body.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-0.48F, -1.52F, 0.031F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 7.0F, -2.76F, 0.0087F, 0.0175F, 0.5192F));

		PartDefinition cube_r55 = body.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-1.0F, -1.0F, -0.0149F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.4568F, 6.2293F, -2.7262F, 0.014F, 0.0175F, -0.3054F));

		PartDefinition cube_r56 = body.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(51, 51).addBox(-0.5F, -1.0F, 0.0202F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4606F, 6.2666F, -2.77F, -0.0035F, 0.0087F, -0.9599F));

		PartDefinition cube_r57 = body.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.48F, -0.54F, 0.076F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.55F, 5.725F, -2.835F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r58 = body.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(0.55F, -1.52F, 0.0705F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 7.45F, -2.76F, 0.0204F, 0.0441F, 0.24F));

		PartDefinition cube_r59 = body.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(51, 51).addBox(-0.55F, -0.55F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(51, 51).addBox(-0.5F, -0.55F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 8.45F, -2.76F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r60 = body.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.45F, -0.55F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(51, 51).mirror().addBox(-0.5F, -0.55F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 8.45F, -2.76F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r61 = body.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(51, 51).addBox(-0.6F, -0.48F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.2F, -2.76F, 0.0F, 0.0087F, 0.0F));

		PartDefinition cube_r62 = body.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.4F, -0.48F, 0.008F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.2F, -2.76F, 0.0F, -0.0087F, 0.0F));

		PartDefinition cube_r63 = body.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(50, 51).addBox(-1.52F, -1.52F, 0.031F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 7.0F, -2.76F, 0.0087F, -0.0175F, -0.5192F));

		PartDefinition cube_r64 = body.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(50, 51).addBox(-1.5F, -1.5F, -0.017F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.5F, -2.76F, -0.0489F, 0.0436F, 0.4363F));

		PartDefinition cube_r65 = body.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(61, 51).addBox(-0.416F, -0.3005F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.239F, 3.1305F, -2.76F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r66 = body.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(61, 51).addBox(-0.561F, -0.7305F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(61, 51).addBox(-0.561F, -0.7305F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.339F, 3.3305F, -2.76F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r67 = body.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(60, 51).mirror().addBox(-0.584F, -1.2305F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.639F, 4.0305F, -2.76F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r68 = body.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.669F, -0.2105F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.739F, 3.0805F, -2.76F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r69 = body.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.729F, -0.2805F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(61, 51).mirror().addBox(-0.729F, -0.0005F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.939F, 3.2805F, -2.76F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r70 = body.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(60, 51).addBox(-1.416F, -1.2305F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.639F, 4.0305F, -2.76F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r71 = body.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(61, 51).addBox(-0.271F, -0.0005F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(61, 51).addBox(-0.271F, -0.2805F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.939F, 3.2805F, -2.76F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r72 = body.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(61, 51).addBox(-0.331F, -0.2105F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.739F, 3.0805F, -2.76F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r73 = body.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.584F, -0.2305F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.239F, 8.2305F, -2.76F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r74 = body.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.5419F, -0.5058F, 0.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0719F, 8.0888F, -2.76F, 0.0F, 0.0436F, -0.4276F));

		PartDefinition cube_r75 = body.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(61, 51).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.78F, 7.94F, -2.7502F, 0.0F, 0.0023F, 0.0F));

		PartDefinition cube_r76 = body.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(61, 51).addBox(-0.4781F, -0.4988F, 0.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9281F, 8.0888F, -2.76F, 0.0F, -0.0436F, 0.4276F));

		PartDefinition cube_r77 = body.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(61, 51).addBox(-0.416F, -0.2305F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.239F, 8.2305F, -2.76F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r78 = body.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(61, 51).addBox(-0.416F, -0.2305F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.239F, 3.6305F, -2.76F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r79 = body.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.439F, -0.7305F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.339F, 3.3305F, -2.76F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r80 = body.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.5319F, -0.5078F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.6219F, 2.9388F, -2.76F, 0.0F, 0.0F, -0.4276F));

		PartDefinition cube_r81 = body.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.5F, -0.5F, 0.02F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.439F, 1.8305F, -2.78F, -0.0436F, 0.0052F, 0.1222F));

		PartDefinition cube_r82 = body.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(61, 51).addBox(-0.481F, -0.4995F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.039F, 2.3305F, -2.76F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r83 = body.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.78F, 7.94F, -2.7502F, 0.0F, -0.0023F, 0.0F));

		PartDefinition cube_r84 = body.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.5219F, -0.4988F, 0.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.9281F, 8.0888F, -2.76F, 0.0F, 0.0436F, -0.4276F));

		PartDefinition cube_r85 = body.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(61, 51).addBox(-0.4581F, -0.5058F, 0.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0719F, 8.0888F, -2.76F, 0.0F, -0.0436F, 0.4276F));

		PartDefinition cube_r86 = body.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(61, 51).addBox(-0.4681F, -0.5078F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6219F, 2.9388F, -2.76F, 0.0F, 0.0F, 0.4276F));

		PartDefinition cube_r87 = body.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(61, 51).addBox(-0.5F, -0.5F, 0.02F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.439F, 1.8305F, -2.78F, -0.0436F, -0.0052F, -0.1222F));

		PartDefinition waist = body.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 20).addBox(-9.0F, 5.25F, 0.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(4.5F, 2.5F, -2.5F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 54).addBox(-3.5F, -3.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 54).addBox(-3.5F, 3.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 54).addBox(-0.5F, -3.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition cube_r88 = right_leg.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.584F, -0.2305F, -0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.489F, 8.7305F, 2.56F, -0.0436F, 0.0436F, 0.0873F));

		PartDefinition cube_r89 = right_leg.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.591F, -0.3505F, -0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.189F, 8.9305F, 2.56F, -0.0105F, -0.0122F, -0.2182F));

		PartDefinition cube_r90 = right_leg.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.574F, -0.2205F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.214F, 8.7805F, 2.56F, -0.0105F, -0.0105F, 1.0036F));

		PartDefinition cube_r91 = right_leg.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.011F, 9.1805F, 2.56F, -0.007F, -0.007F, -0.7854F));

		PartDefinition cube_r92 = right_leg.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(61, 46).addBox(-0.433F, -0.2135F, -0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.911F, 9.2805F, 2.56F, -0.0052F, -0.0052F, -0.6981F));

		PartDefinition cube_r93 = right_leg.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.564F, -0.2235F, -0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.264F, 8.3305F, 2.56F, 0.0087F, 0.0087F, -0.5672F));

		PartDefinition cube_r94 = right_leg.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, -0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.636F, 9.2805F, 2.56F, -0.0175F, -0.0175F, -0.1309F));

		PartDefinition cube_r95 = right_leg.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(61, 46).addBox(-0.416F, -0.2305F, -0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.164F, 8.4055F, 2.56F, -0.0436F, -0.0436F, -0.0873F));

		PartDefinition cube_r96 = right_leg.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, -0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.311F, 8.9555F, 2.56F, -0.0175F, 0.0175F, 0.1309F));

		PartDefinition cube_r97 = right_leg.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.567F, -0.2135F, -0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.586F, 8.9555F, 2.56F, -0.0052F, 0.0052F, 0.6981F));

		PartDefinition cube_r98 = right_leg.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.686F, 8.8555F, 2.56F, -0.007F, 0.007F, 0.7854F));

		PartDefinition cube_r99 = right_leg.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(61, 46).addBox(-0.409F, -0.3505F, -0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.864F, 8.6055F, 2.56F, -0.0105F, 0.0122F, 0.2182F));

		PartDefinition cube_r100 = right_leg.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(61, 46).addBox(-0.426F, -0.2205F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.889F, 8.4555F, 2.56F, -0.0105F, 0.0105F, -1.0036F));

		PartDefinition cube_r101 = right_leg.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(61, 46).addBox(-0.436F, -0.2235F, -0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.939F, 8.0055F, 2.56F, 0.0087F, -0.0087F, 0.5672F));

		PartDefinition cube_r102 = right_leg.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(50, 51).addBox(0.0669F, -2.7232F, -0.0263F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.975F, 8.5F, 2.51F, -0.014F, 0.0175F, 0.3054F));

		PartDefinition cube_r103 = right_leg.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(51, 51).addBox(0.3141F, -2.7166F, -0.001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.975F, 8.5F, 2.51F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r104 = right_leg.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(51, 51).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6649F, 6.5975F, 2.4993F, 0.0304F, -0.0087F, -0.3194F));

		PartDefinition cube_r105 = right_leg.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(50, 51).addBox(0.9213F, -0.7535F, 0.0159F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.975F, 8.5F, 2.51F, -0.0087F, 0.0175F, -0.5236F));

		PartDefinition cube_r106 = right_leg.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-0.6684F, -2.9971F, -0.007F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.975F, 8.5F, 2.51F, 0.0035F, 0.0087F, 0.9599F));

		PartDefinition cube_r107 = right_leg.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(0.5263F, -1.6171F, -0.0729F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.975F, 8.5F, 2.51F, -0.0204F, -0.0441F, 0.24F));

		PartDefinition cube_r108 = right_leg.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-0.459F, -2.1888F, -0.0394F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.975F, 8.5F, 2.51F, -0.0086F, -0.0175F, 0.5306F));

		PartDefinition cube_r109 = right_leg.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(58, 52).addBox(-1.5263F, -1.6171F, -0.0729F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, -0.0204F, 0.0441F, -0.24F));

		PartDefinition cube_r110 = right_leg.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(57, 52).addBox(-1.541F, -2.1888F, -0.0394F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, -0.0086F, 0.0175F, -0.5306F));

		PartDefinition cube_r111 = right_leg.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(57, 52).mirror().addBox(-2.9213F, -0.7535F, 0.0159F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, -0.0087F, -0.0175F, 0.5236F));

		PartDefinition cube_r112 = right_leg.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(58, 52).mirror().addBox(-2.6542F, -2.2917F, 0.0098F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, 0.0304F, 0.0087F, 0.3194F));

		PartDefinition cube_r113 = right_leg.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(58, 52).addBox(-0.3316F, -2.9971F, -0.007F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, 0.0035F, -0.0087F, -0.9599F));

		PartDefinition cube_r114 = right_leg.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(58, 52).mirror().addBox(-1.3141F, -2.7166F, -0.001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r115 = right_leg.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(57, 52).mirror().addBox(-2.0669F, -2.7232F, -0.0263F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, 5.85F, 2.51F, -0.014F, -0.0175F, -0.3054F));

		PartDefinition cube_r116 = right_leg.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(58, 46).mirror().addBox(0.5262F, -1.6171F, -0.0729F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, -0.0204F, -0.0441F, 0.24F));

		PartDefinition cube_r117 = right_leg.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(57, 46).mirror().addBox(-0.459F, -2.1888F, -0.0394F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, -0.0086F, -0.0175F, 0.5306F));

		PartDefinition cube_r118 = right_leg.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(57, 46).addBox(0.9213F, -0.7535F, 0.0159F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, -0.0087F, 0.0175F, -0.5236F));

		PartDefinition cube_r119 = right_leg.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(58, 46).addBox(1.6542F, -2.2917F, 0.0098F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, 0.0304F, -0.0087F, -0.3194F));

		PartDefinition cube_r120 = right_leg.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(58, 46).mirror().addBox(-0.6684F, -2.9971F, -0.007F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, 0.0035F, 0.0087F, 0.9599F));

		PartDefinition cube_r121 = right_leg.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(58, 46).addBox(0.3141F, -2.7166F, -0.001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r122 = right_leg.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(57, 46).addBox(0.0669F, -2.7232F, -0.0263F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3F, 2.5F, 2.51F, -0.014F, 0.0175F, 0.3054F));

		PartDefinition cube_r123 = right_leg.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(61, 50).addBox(0.0025F, -0.4962F, -0.5077F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 8.1142F, 0.7827F, 0.0F, 0.0192F, -0.0136F));

		PartDefinition cube_r124 = right_leg.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(61, 50).addBox(-0.0001F, -0.5F, -0.4997F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 7.2642F, 0.1577F, 0.2574F, -0.0086F, 0.0052F));

		PartDefinition cube_r125 = right_leg.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(61, 50).addBox(0.0033F, -0.4666F, -0.4962F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 7.6876F, 0.8742F, 0.2574F, 0.0087F, -0.0052F));

		PartDefinition cube_r126 = right_leg.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(61, 50).addBox(0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 7.8626F, 0.7742F, 0.0F, 0.0175F, -0.007F));

		PartDefinition cube_r127 = right_leg.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(54, 52).addBox(0.0025F, -0.4962F, -0.4923F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 8.5642F, -1.0077F, 0.0F, -0.0192F, -0.0136F));

		PartDefinition cube_r128 = right_leg.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(54, 52).addBox(-0.0001F, -0.5F, -0.5003F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 7.7142F, -0.3827F, -0.2574F, 0.0086F, 0.0052F));

		PartDefinition cube_r129 = right_leg.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(54, 52).addBox(0.0033F, -0.4666F, -0.5038F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 8.1376F, -1.0992F, -0.2574F, -0.0087F, -0.0052F));

		PartDefinition cube_r130 = right_leg.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(54, 52).addBox(0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 8.3126F, -0.9992F, 0.0F, -0.0175F, -0.007F));

		PartDefinition cube_r131 = right_leg.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(54, 52).addBox(0.0025F, -0.4962F, -0.4923F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 5.4642F, -0.7827F, 0.0F, -0.0192F, -0.0136F));

		PartDefinition cube_r132 = right_leg.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(54, 52).addBox(-0.0001F, -0.5F, -0.5003F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 4.6142F, -0.1577F, -0.2574F, 0.0086F, 0.0052F));

		PartDefinition cube_r133 = right_leg.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(54, 52).addBox(0.0033F, -0.4666F, -0.5038F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 5.0376F, -0.8742F, -0.2574F, -0.0087F, -0.0052F));

		PartDefinition cube_r134 = right_leg.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(54, 52).addBox(0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 5.2126F, -0.7742F, 0.0F, -0.0175F, -0.007F));

		PartDefinition cube_r135 = right_leg.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(54, 52).addBox(0.0025F, -0.4962F, -0.5077F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 5.4642F, 0.7827F, 0.0F, 0.0192F, -0.0136F));

		PartDefinition cube_r136 = right_leg.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(54, 52).addBox(-0.0001F, -0.5F, -0.4997F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 4.6142F, 0.1577F, 0.2574F, -0.0086F, 0.0052F));

		PartDefinition cube_r137 = right_leg.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(54, 52).addBox(0.0033F, -0.4666F, -0.4962F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 5.0376F, 0.8742F, 0.2574F, 0.0087F, -0.0052F));

		PartDefinition cube_r138 = right_leg.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(54, 52).addBox(0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 5.2126F, 0.7742F, 0.0F, 0.0175F, -0.007F));

		PartDefinition cube_r139 = right_leg.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(61, 50).addBox(0.0025F, -0.4962F, -0.4923F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 4.3142F, 0.7173F, 0.0F, -0.0192F, -0.0136F));

		PartDefinition cube_r140 = right_leg.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(61, 50).addBox(-0.0001F, -0.5F, -0.5003F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 3.4642F, 1.3423F, -0.2574F, 0.0086F, 0.0052F));

		PartDefinition cube_r141 = right_leg.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(61, 50).addBox(0.0033F, -0.4666F, -0.5038F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 3.8876F, 0.6258F, -0.2574F, -0.0087F, -0.0052F));

		PartDefinition cube_r142 = right_leg.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(61, 50).addBox(0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 4.0626F, 0.7258F, 0.0F, -0.0175F, -0.007F));

		PartDefinition cube_r143 = right_leg.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(61, 50).addBox(0.0025F, -0.4962F, -0.5077F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 4.3142F, -0.7173F, 0.0F, 0.0192F, -0.0136F));

		PartDefinition cube_r144 = right_leg.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(61, 50).addBox(-0.0001F, -0.5F, -0.4997F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4945F, 3.4642F, -1.3423F, 0.2574F, -0.0086F, 0.0052F));

		PartDefinition cube_r145 = right_leg.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(61, 50).addBox(0.0033F, -0.4666F, -0.4962F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 3.8876F, -0.6258F, 0.2574F, 0.0087F, -0.0052F));

		PartDefinition cube_r146 = right_leg.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(61, 50).addBox(0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4983F, 4.0626F, -0.7258F, 0.0F, 0.0175F, -0.007F));

		PartDefinition cube_r147 = right_leg.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 1.825F, 0.35F, 0.3491F, -0.0017F, 0.0F));

		PartDefinition cube_r148 = right_leg.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.725F, -0.2F, 0.0F, -0.0101F, 0.0F));

		PartDefinition cube_r149 = right_leg.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 1.825F, -0.35F, -0.3491F, 0.0017F, 0.0F));

		PartDefinition cube_r150 = right_leg.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -0.49F, -0.52F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 2.275F, -0.3F, 0.5672F, 0.0033F, 0.0F));

		PartDefinition cube_r151 = right_leg.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(61, 44).addBox(0.0F, -0.49F, -0.48F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 2.275F, 0.3F, -0.5672F, -0.0033F, 0.0F));

		PartDefinition cube_r152 = right_leg.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.564F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.789F, 7.4055F, -2.56F, -0.0087F, -0.0087F, -0.5672F));

		PartDefinition cube_r153 = right_leg.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.536F, 8.2555F, -2.56F, 0.007F, 0.007F, -0.7854F));

		PartDefinition cube_r154 = right_leg.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(61, 46).addBox(-0.433F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.436F, 8.3555F, -2.56F, 0.0052F, 0.0052F, -0.6981F));

		PartDefinition cube_r155 = right_leg.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.161F, 8.3555F, -2.56F, 0.0175F, 0.0175F, -0.1309F));

		PartDefinition cube_r156 = right_leg.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.574F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.739F, 7.8555F, -2.56F, 0.0105F, 0.0105F, 1.0036F));

		PartDefinition cube_r157 = right_leg.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.591F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.714F, 8.0055F, -2.56F, 0.0105F, 0.0122F, -0.2182F));

		PartDefinition cube_r158 = right_leg.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.584F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.014F, 7.8055F, -2.56F, 0.0436F, -0.0436F, 0.0873F));

		PartDefinition cube_r159 = right_leg.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(61, 46).addBox(-0.436F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.939F, 7.5305F, -2.56F, -0.0087F, 0.0087F, 0.5672F));

		PartDefinition cube_r160 = right_leg.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.686F, 8.3805F, -2.56F, 0.007F, -0.007F, 0.7854F));

		PartDefinition cube_r161 = right_leg.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.567F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.586F, 8.4805F, -2.56F, 0.0052F, -0.0052F, 0.6981F));

		PartDefinition cube_r162 = right_leg.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.311F, 8.4805F, -2.56F, 0.0175F, -0.0175F, 0.1309F));

		PartDefinition cube_r163 = right_leg.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(61, 46).addBox(-0.426F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.889F, 7.9805F, -2.56F, 0.0105F, -0.0105F, -1.0036F));

		PartDefinition cube_r164 = right_leg.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(61, 46).addBox(-0.409F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.864F, 8.1305F, -2.56F, 0.0105F, -0.0122F, 0.2182F));

		PartDefinition cube_r165 = right_leg.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(61, 46).addBox(-0.416F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.164F, 7.9305F, -2.56F, 0.0436F, 0.0436F, -0.0873F));

		PartDefinition cube_r166 = right_leg.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5002F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0638F, 4.2576F, -2.5003F, 0.0052F, -0.0053F, -0.2618F));

		PartDefinition cube_r167 = right_leg.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -0.5F, -0.0035F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2112F, 3.4576F, -2.5003F, -0.0035F, -0.0087F, 1.2957F));

		PartDefinition cube_r168 = right_leg.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -1.0085F, 0.0051F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0594F, 3.3185F, -2.4961F, 0.017F, 0.0244F, -1.431F));

		PartDefinition cube_r169 = right_leg.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(58, 51).addBox(-0.3362F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.6888F, 3.4576F, -2.5003F, 0.0072F, 0.0236F, -1.2957F));

		PartDefinition cube_r170 = right_leg.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.4948F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.8362F, 4.2576F, -2.5003F, 0.0052F, 0.0053F, 0.2618F));

		PartDefinition cube_r171 = right_leg.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.6258F, 4.0576F, -2.4945F, 0.0044F, 0.0175F, 0.5192F));

		PartDefinition cube_r172 = right_leg.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5029F, 3.5925F, -2.5032F, 0.0035F, -0.0035F, -0.5192F));

		PartDefinition cube_r173 = right_leg.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(57, 51).addBox(-1.0052F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1652F, 3.8064F, -2.4881F, 0.0086F, -0.007F, -0.5192F));

		PartDefinition cube_r174 = right_leg.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(57, 51).mirror().addBox(-0.9948F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5652F, 6.7314F, -2.4881F, 0.0086F, 0.007F, 0.5192F));

		PartDefinition cube_r175 = right_leg.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1029F, 6.5175F, -2.5032F, 0.0035F, 0.0035F, 0.5192F));

		PartDefinition cube_r176 = right_leg.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2257F, 6.9826F, -2.4945F, 0.0044F, -0.0175F, -0.5192F));

		PartDefinition cube_r177 = right_leg.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5052F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4362F, 7.1826F, -2.5003F, 0.0052F, -0.0053F, -0.2618F));

		PartDefinition cube_r178 = right_leg.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.6638F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.2888F, 6.3826F, -2.5003F, 0.0072F, -0.0236F, 1.2957F));

		PartDefinition cube_r179 = right_leg.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -1.0085F, 0.0051F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3406F, 6.2435F, -2.4961F, 0.017F, -0.0244F, 1.431F));

		PartDefinition cube_r180 = right_leg.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -0.5F, -0.0035F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1888F, 6.3826F, -2.5003F, -0.0035F, 0.0087F, -1.2957F));

		PartDefinition cube_r181 = right_leg.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.4998F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3362F, 7.1826F, -2.5003F, 0.0052F, 0.0053F, 0.2618F));

		PartDefinition cube_r182 = right_leg.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(61, 51).addBox(-0.437F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.086F, 2.6555F, -2.56F, 0.007F, 0.007F, -0.7854F));

		PartDefinition cube_r183 = right_leg.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.564F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.339F, 1.8055F, -2.56F, -0.0087F, -0.0087F, -0.5672F));

		PartDefinition cube_r184 = right_leg.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(61, 51).addBox(-0.433F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.014F, 2.7555F, -2.56F, 0.0052F, 0.0052F, -0.6981F));

		PartDefinition cube_r185 = right_leg.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(61, 51).addBox(-0.437F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.289F, 2.7555F, -2.56F, 0.0175F, 0.0175F, -0.1309F));

		PartDefinition cube_r186 = right_leg.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.574F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.289F, 2.2555F, -2.56F, 0.0105F, 0.0105F, 1.0036F));

		PartDefinition cube_r187 = right_leg.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.591F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.264F, 2.4055F, -2.56F, 0.0105F, 0.0122F, -0.2182F));

		PartDefinition cube_r188 = right_leg.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.584F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.564F, 2.2055F, -2.56F, 0.0436F, -0.0436F, 0.0873F));

		PartDefinition cube_r189 = right_leg.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(61, 51).addBox(-0.436F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.239F, 0.8305F, -2.56F, -0.0087F, 0.0087F, 0.5672F));

		PartDefinition cube_r190 = right_leg.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.563F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.986F, 1.6805F, -2.56F, 0.007F, -0.007F, 0.7854F));

		PartDefinition cube_r191 = right_leg.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.567F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.886F, 1.7805F, -2.56F, 0.0052F, -0.0052F, 0.6981F));

		PartDefinition cube_r192 = right_leg.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.563F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.611F, 1.7805F, -2.56F, 0.0175F, -0.0175F, 0.1309F));

		PartDefinition cube_r193 = right_leg.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(61, 51).addBox(-0.416F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.464F, 1.2305F, -2.56F, 0.0436F, 0.0436F, -0.0873F));

		PartDefinition cube_r194 = right_leg.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(61, 51).addBox(-0.426F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.189F, 1.2805F, -2.56F, 0.0105F, -0.0105F, -1.0036F));

		PartDefinition cube_r195 = right_leg.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(61, 51).addBox(-0.409F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.164F, 1.4305F, -2.56F, 0.0105F, -0.0122F, 0.2182F));

		PartDefinition cube_r196 = right_leg.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(61, 46).addBox(-0.416F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.164F, 2.0805F, -2.56F, 0.0436F, 0.0436F, -0.0873F));

		PartDefinition cube_r197 = right_leg.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(61, 46).addBox(-0.409F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.864F, 2.2805F, -2.56F, 0.0105F, -0.0122F, 0.2182F));

		PartDefinition cube_r198 = right_leg.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(61, 46).addBox(-0.426F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.889F, 2.1305F, -2.56F, 0.0105F, -0.0105F, -1.0036F));

		PartDefinition cube_r199 = right_leg.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.311F, 2.6305F, -2.56F, 0.0175F, -0.0175F, 0.1309F));

		PartDefinition cube_r200 = right_leg.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.567F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.586F, 2.6305F, -2.56F, 0.0052F, -0.0052F, 0.6981F));

		PartDefinition cube_r201 = right_leg.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.686F, 2.5305F, -2.56F, 0.007F, -0.007F, 0.7854F));

		PartDefinition cube_r202 = right_leg.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(61, 46).addBox(-0.436F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.939F, 1.6805F, -2.56F, -0.0087F, 0.0087F, 0.5672F));

		PartDefinition cube_r203 = right_leg.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.584F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.989F, 1.4055F, -2.56F, 0.0436F, -0.0436F, 0.0873F));

		PartDefinition cube_r204 = right_leg.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.591F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.689F, 1.6055F, -2.56F, 0.0105F, 0.0122F, -0.2182F));

		PartDefinition cube_r205 = right_leg.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.574F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.714F, 1.4555F, -2.56F, 0.0105F, 0.0105F, 1.0036F));

		PartDefinition cube_r206 = right_leg.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(61, 51).addBox(-0.437F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.136F, 1.9555F, -2.56F, 0.0175F, 0.0175F, -0.1309F));

		PartDefinition cube_r207 = right_leg.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(61, 51).addBox(-0.433F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.411F, 1.9555F, -2.56F, 0.0052F, 0.0052F, -0.6981F));

		PartDefinition cube_r208 = right_leg.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.564F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.764F, 1.0055F, -2.56F, -0.0087F, -0.0087F, -0.5672F));

		PartDefinition cube_r209 = right_leg.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(61, 51).addBox(-0.437F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.511F, 1.8555F, -2.56F, 0.007F, 0.007F, -0.7854F));

		PartDefinition cube_r210 = right_leg.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(61, 45).mirror().addBox(-0.5F, -0.5F, 0.05F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.105F, 0.19F, -2.5502F, 0.0F, -0.0023F, 0.0F));

		PartDefinition cube_r211 = right_leg.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(61, 45).addBox(-0.4581F, -0.5058F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3969F, 0.3388F, -2.56F, -0.0175F, -0.0436F, 0.4276F));

		PartDefinition cube_r212 = right_leg.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(61, 45).addBox(-0.416F, -0.2305F, 0.05F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.564F, 0.4805F, -2.56F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r213 = right_leg.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(61, 45).mirror().addBox(-0.5219F, -0.4988F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2531F, 0.3388F, -2.56F, -0.0175F, 0.0436F, -0.4276F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition cube_r214 = left_leg.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(61, 46).addBox(-0.416F, -0.2305F, -0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.489F, 8.7305F, 2.56F, -0.0436F, -0.0436F, -0.0873F));

		PartDefinition cube_r215 = left_leg.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(61, 46).addBox(-0.409F, -0.3505F, -0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.189F, 8.9305F, 2.56F, -0.0105F, 0.0122F, 0.2182F));

		PartDefinition cube_r216 = left_leg.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(61, 46).addBox(-0.426F, -0.2205F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.214F, 8.7805F, 2.56F, -0.0105F, 0.0105F, -1.0036F));

		PartDefinition cube_r217 = left_leg.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.011F, 9.1805F, 2.56F, -0.007F, 0.007F, 0.7854F));

		PartDefinition cube_r218 = left_leg.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.567F, -0.2135F, -0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.911F, 9.2805F, 2.56F, -0.0052F, 0.0052F, 0.6981F));

		PartDefinition cube_r219 = left_leg.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(61, 46).addBox(-0.436F, -0.2235F, -0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.264F, 8.3305F, 2.56F, 0.0087F, -0.0087F, 0.5672F));

		PartDefinition cube_r220 = left_leg.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, -0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.636F, 9.2805F, 2.56F, -0.0175F, 0.0175F, 0.1309F));

		PartDefinition cube_r221 = left_leg.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.584F, -0.2305F, -0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.164F, 8.4055F, 2.56F, -0.0436F, 0.0436F, 0.0873F));

		PartDefinition cube_r222 = left_leg.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.591F, -0.3505F, -0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.864F, 8.6055F, 2.56F, -0.0105F, -0.0122F, -0.2182F));

		PartDefinition cube_r223 = left_leg.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.686F, 8.8555F, 2.56F, -0.007F, -0.007F, -0.7854F));

		PartDefinition cube_r224 = left_leg.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(61, 46).addBox(-0.433F, -0.2135F, -0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.586F, 8.9555F, 2.56F, -0.0052F, -0.0052F, -0.6981F));

		PartDefinition cube_r225 = left_leg.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.564F, -0.2235F, -0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.939F, 8.0055F, 2.56F, 0.0087F, 0.0087F, -0.5672F));

		PartDefinition cube_r226 = left_leg.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, -0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.311F, 8.9555F, 2.56F, -0.0175F, -0.0175F, -0.1309F));

		PartDefinition cube_r227 = left_leg.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.574F, -0.2205F, -0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.889F, 8.4555F, 2.56F, -0.0105F, -0.0105F, 1.0036F));

		PartDefinition cube_r228 = left_leg.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(50, 51).addBox(-1.541F, -2.1888F, -0.0394F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.975F, 8.5F, 2.51F, -0.0086F, 0.0175F, -0.5306F));

		PartDefinition cube_r229 = left_leg.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(51, 51).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1705F, 8.1436F, 2.495F, -0.0204F, 0.0441F, -0.24F));

		PartDefinition cube_r230 = left_leg.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(51, 51).addBox(-0.3316F, -2.9971F, -0.007F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.975F, 8.5F, 2.51F, 0.0035F, -0.0087F, -0.9599F));

		PartDefinition cube_r231 = left_leg.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-2.9213F, -0.7535F, 0.0159F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.975F, 8.5F, 2.51F, -0.0087F, -0.0175F, 0.5236F));

		PartDefinition cube_r232 = left_leg.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-2.6542F, -2.2917F, 0.0098F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.975F, 8.5F, 2.51F, 0.0304F, 0.0087F, 0.3194F));

		PartDefinition cube_r233 = left_leg.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(51, 51).mirror().addBox(-1.3141F, -2.7166F, -0.001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.975F, 8.5F, 2.51F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r234 = left_leg.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(50, 51).mirror().addBox(-2.0669F, -2.7232F, -0.0263F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.975F, 8.5F, 2.51F, -0.014F, -0.0175F, -0.3054F));

		PartDefinition cube_r235 = left_leg.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(58, 52).mirror().addBox(0.5262F, -1.6171F, -0.0729F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, -0.0204F, -0.0441F, 0.24F));

		PartDefinition cube_r236 = left_leg.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(57, 52).mirror().addBox(-0.459F, -2.1888F, -0.0394F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, -0.0086F, -0.0175F, 0.5306F));

		PartDefinition cube_r237 = left_leg.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(57, 52).addBox(0.9213F, -0.7535F, 0.0159F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, -0.0087F, 0.0175F, -0.5236F));

		PartDefinition cube_r238 = left_leg.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(58, 52).addBox(1.6542F, -2.2917F, 0.0098F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, 0.0304F, -0.0087F, -0.3194F));

		PartDefinition cube_r239 = left_leg.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(58, 52).mirror().addBox(-0.6684F, -2.9971F, -0.007F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, 0.0035F, 0.0087F, 0.9599F));

		PartDefinition cube_r240 = left_leg.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(58, 52).addBox(0.3141F, -2.7166F, -0.001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r241 = left_leg.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(57, 52).addBox(0.0669F, -2.7232F, -0.0263F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.775F, 5.85F, 2.51F, -0.014F, 0.0175F, 0.3054F));

		PartDefinition cube_r242 = left_leg.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(58, 46).addBox(-1.5262F, -1.6171F, -0.0729F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, -0.0204F, 0.0441F, -0.24F));

		PartDefinition cube_r243 = left_leg.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(57, 46).addBox(-1.541F, -2.1888F, -0.0394F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, -0.0086F, 0.0175F, -0.5306F));

		PartDefinition cube_r244 = left_leg.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(57, 46).mirror().addBox(-2.9213F, -0.7535F, 0.0159F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, -0.0087F, -0.0175F, 0.5236F));

		PartDefinition cube_r245 = left_leg.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(58, 46).mirror().addBox(-2.6542F, -2.2917F, 0.0098F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, 0.0304F, 0.0087F, 0.3194F));

		PartDefinition cube_r246 = left_leg.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(58, 46).addBox(-0.3316F, -2.9971F, -0.007F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, 0.0035F, -0.0087F, -0.9599F));

		PartDefinition cube_r247 = left_leg.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(58, 46).mirror().addBox(-1.3141F, -2.7166F, -0.001F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r248 = left_leg.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(57, 46).mirror().addBox(-2.0669F, -2.7232F, -0.0263F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3F, 2.5F, 2.51F, -0.014F, -0.0175F, -0.3054F));

		PartDefinition cube_r249 = left_leg.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0025F, -0.4962F, -0.5077F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 8.1142F, 0.7827F, 0.0F, -0.0192F, 0.0136F));

		PartDefinition cube_r250 = left_leg.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 7.8626F, 0.7742F, 0.0F, -0.0175F, 0.007F));

		PartDefinition cube_r251 = left_leg.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0026F, -0.4666F, -0.4962F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 7.6876F, 0.8742F, 0.2574F, -0.0086F, 0.0052F));

		PartDefinition cube_r252 = left_leg.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(0.001F, -0.504F, -0.4997F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 7.2642F, 0.1077F, 0.2574F, 0.0086F, -0.0052F));

		PartDefinition cube_r253 = left_leg.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 4.0626F, 0.7258F, 0.0F, 0.0175F, 0.007F));

		PartDefinition cube_r254 = left_leg.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0026F, -0.4666F, -0.5038F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 3.8876F, 0.6258F, -0.2574F, 0.0086F, 0.0052F));

		PartDefinition cube_r255 = left_leg.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(0.001F, -0.504F, -0.5003F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 3.4642F, 1.3923F, -0.2574F, -0.0086F, -0.0052F));

		PartDefinition cube_r256 = left_leg.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0025F, -0.4962F, -0.5077F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 4.3142F, -0.7173F, 0.0F, -0.0192F, 0.0136F));

		PartDefinition cube_r257 = left_leg.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(0.001F, -0.504F, -0.4997F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 3.4642F, -1.3923F, 0.2574F, 0.0086F, -0.0052F));

		PartDefinition cube_r258 = left_leg.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0026F, -0.4666F, -0.4962F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 3.8876F, -0.6258F, 0.2574F, -0.0086F, 0.0052F));

		PartDefinition cube_r259 = left_leg.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 4.0626F, -0.7258F, 0.0F, -0.0175F, 0.007F));

		PartDefinition cube_r260 = left_leg.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0025F, -0.4962F, -0.4923F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 8.5642F, -1.0077F, 0.0F, 0.0192F, 0.0136F));

		PartDefinition cube_r261 = left_leg.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 8.3126F, -0.9992F, 0.0F, 0.0175F, 0.007F));

		PartDefinition cube_r262 = left_leg.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0026F, -0.4666F, -0.5038F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 8.1376F, -1.0992F, -0.2574F, 0.0086F, 0.0052F));

		PartDefinition cube_r263 = left_leg.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(0.001F, -0.504F, -0.5003F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 7.7142F, -0.3327F, -0.2574F, -0.0086F, -0.0052F));

		PartDefinition cube_r264 = left_leg.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0025F, -0.4962F, -0.4923F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 5.4642F, -0.7827F, 0.0F, 0.0192F, 0.0136F));

		PartDefinition cube_r265 = left_leg.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(0.001F, -0.504F, -0.5003F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 4.6142F, -0.1077F, -0.2574F, -0.0086F, -0.0052F));

		PartDefinition cube_r266 = left_leg.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0026F, -0.4666F, -0.5038F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 5.0376F, -0.8742F, -0.2574F, 0.0086F, 0.0052F));

		PartDefinition cube_r267 = left_leg.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 5.2126F, 0.7742F, 0.0F, -0.0175F, 0.007F));

		PartDefinition cube_r268 = left_leg.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0026F, -0.4666F, -0.4962F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 5.0376F, 0.8742F, 0.2574F, -0.0086F, 0.0052F));

		PartDefinition cube_r269 = left_leg.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(0.001F, -0.504F, -0.4997F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 4.6142F, 0.1077F, 0.2574F, 0.0086F, -0.0052F));

		PartDefinition cube_r270 = left_leg.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0025F, -0.4962F, -0.5077F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 5.4642F, 0.7827F, 0.0F, -0.0192F, 0.0136F));

		PartDefinition cube_r271 = left_leg.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(54, 51).mirror().addBox(-0.0053F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4983F, 5.2126F, -0.7742F, 0.0F, 0.0175F, 0.007F));

		PartDefinition cube_r272 = left_leg.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(61, 50).mirror().addBox(-0.0025F, -0.4962F, -0.4923F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.4945F, 4.3142F, 0.7173F, 0.0F, 0.0192F, 0.0136F));

		PartDefinition cube_r273 = left_leg.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 1.825F, 0.35F, 0.3491F, 0.0017F, 0.0F));

		PartDefinition cube_r274 = left_leg.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -0.48F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 0.725F, -0.2F, 0.0F, 0.0101F, 0.0F));

		PartDefinition cube_r275 = left_leg.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 1.825F, -0.35F, -0.3491F, -0.0017F, 0.0F));

		PartDefinition cube_r276 = left_leg.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -0.49F, -0.52F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 2.275F, -0.3F, 0.5672F, -0.0033F, 0.0F));

		PartDefinition cube_r277 = left_leg.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(61, 44).mirror().addBox(0.001F, -0.49F, -0.48F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, 2.275F, 0.3F, -0.5672F, 0.0033F, 0.0F));

		PartDefinition cube_r278 = left_leg.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(61, 46).addBox(-0.436F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.789F, 7.4055F, -2.56F, -0.0087F, 0.0087F, 0.5672F));

		PartDefinition cube_r279 = left_leg.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(61, 45).addBox(-0.4781F, -0.4988F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2531F, 0.3388F, -2.56F, -0.0175F, -0.0436F, 0.4276F));

		PartDefinition cube_r280 = left_leg.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(61, 45).mirror().addBox(-0.584F, -0.2305F, 0.05F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.564F, 0.4805F, -2.56F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r281 = left_leg.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(61, 45).mirror().addBox(-0.5419F, -0.5058F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.3969F, 0.3388F, -2.56F, -0.0175F, 0.0436F, -0.4276F));

		PartDefinition cube_r282 = left_leg.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(61, 45).addBox(-0.5F, -0.5F, 0.05F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.105F, 0.19F, -2.5502F, 0.0F, 0.0023F, 0.0F));

		PartDefinition cube_r283 = left_leg.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.563F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.511F, 1.8555F, -2.56F, 0.007F, -0.007F, 0.7854F));

		PartDefinition cube_r284 = left_leg.addOrReplaceChild("cube_r284", CubeListBuilder.create().texOffs(61, 51).addBox(-0.436F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.764F, 1.0055F, -2.56F, -0.0087F, 0.0087F, 0.5672F));

		PartDefinition cube_r285 = left_leg.addOrReplaceChild("cube_r285", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.567F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.411F, 1.9555F, -2.56F, 0.0052F, -0.0052F, 0.6981F));

		PartDefinition cube_r286 = left_leg.addOrReplaceChild("cube_r286", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.563F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.136F, 1.9555F, -2.56F, 0.0175F, -0.0175F, 0.1309F));

		PartDefinition cube_r287 = left_leg.addOrReplaceChild("cube_r287", CubeListBuilder.create().texOffs(61, 51).addBox(-0.426F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.714F, 1.4555F, -2.56F, 0.0105F, -0.0105F, -1.0036F));

		PartDefinition cube_r288 = left_leg.addOrReplaceChild("cube_r288", CubeListBuilder.create().texOffs(61, 51).addBox(-0.409F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.689F, 1.6055F, -2.56F, 0.0105F, -0.0122F, 0.2182F));

		PartDefinition cube_r289 = left_leg.addOrReplaceChild("cube_r289", CubeListBuilder.create().texOffs(61, 51).addBox(-0.416F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.989F, 1.4055F, -2.56F, 0.0436F, 0.0436F, -0.0873F));

		PartDefinition cube_r290 = left_leg.addOrReplaceChild("cube_r290", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.564F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.939F, 1.6805F, -2.56F, -0.0087F, -0.0087F, -0.5672F));

		PartDefinition cube_r291 = left_leg.addOrReplaceChild("cube_r291", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.686F, 2.5305F, -2.56F, 0.007F, 0.007F, -0.7854F));

		PartDefinition cube_r292 = left_leg.addOrReplaceChild("cube_r292", CubeListBuilder.create().texOffs(61, 46).addBox(-0.433F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.586F, 2.6305F, -2.56F, 0.0052F, 0.0052F, -0.6981F));

		PartDefinition cube_r293 = left_leg.addOrReplaceChild("cube_r293", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.311F, 2.6305F, -2.56F, 0.0175F, 0.0175F, -0.1309F));

		PartDefinition cube_r294 = left_leg.addOrReplaceChild("cube_r294", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.574F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.889F, 2.1305F, -2.56F, 0.0105F, 0.0105F, 1.0036F));

		PartDefinition cube_r295 = left_leg.addOrReplaceChild("cube_r295", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.591F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.864F, 2.2805F, -2.56F, 0.0105F, 0.0122F, -0.2182F));

		PartDefinition cube_r296 = left_leg.addOrReplaceChild("cube_r296", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.584F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.164F, 2.0805F, -2.56F, 0.0436F, -0.0436F, 0.0873F));

		PartDefinition cube_r297 = left_leg.addOrReplaceChild("cube_r297", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.591F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.164F, 1.4305F, -2.56F, 0.0105F, 0.0122F, -0.2182F));

		PartDefinition cube_r298 = left_leg.addOrReplaceChild("cube_r298", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.574F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.189F, 1.2805F, -2.56F, 0.0105F, 0.0105F, 1.0036F));

		PartDefinition cube_r299 = left_leg.addOrReplaceChild("cube_r299", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.584F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.464F, 1.2305F, -2.56F, 0.0436F, -0.0436F, 0.0873F));

		PartDefinition cube_r300 = left_leg.addOrReplaceChild("cube_r300", CubeListBuilder.create().texOffs(61, 51).addBox(-0.437F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.611F, 1.7805F, -2.56F, 0.0175F, 0.0175F, -0.1309F));

		PartDefinition cube_r301 = left_leg.addOrReplaceChild("cube_r301", CubeListBuilder.create().texOffs(61, 51).addBox(-0.433F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.886F, 1.7805F, -2.56F, 0.0052F, 0.0052F, -0.6981F));

		PartDefinition cube_r302 = left_leg.addOrReplaceChild("cube_r302", CubeListBuilder.create().texOffs(61, 51).addBox(-0.437F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.986F, 1.6805F, -2.56F, 0.007F, 0.007F, -0.7854F));

		PartDefinition cube_r303 = left_leg.addOrReplaceChild("cube_r303", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.564F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.239F, 0.8305F, -2.56F, -0.0087F, -0.0087F, -0.5672F));

		PartDefinition cube_r304 = left_leg.addOrReplaceChild("cube_r304", CubeListBuilder.create().texOffs(61, 51).addBox(-0.416F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.564F, 2.2055F, -2.56F, 0.0436F, 0.0436F, -0.0873F));

		PartDefinition cube_r305 = left_leg.addOrReplaceChild("cube_r305", CubeListBuilder.create().texOffs(61, 51).addBox(-0.409F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.264F, 2.4055F, -2.56F, 0.0105F, -0.0122F, 0.2182F));

		PartDefinition cube_r306 = left_leg.addOrReplaceChild("cube_r306", CubeListBuilder.create().texOffs(61, 51).addBox(-0.426F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.289F, 2.2555F, -2.56F, 0.0105F, -0.0105F, -1.0036F));

		PartDefinition cube_r307 = left_leg.addOrReplaceChild("cube_r307", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.563F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.289F, 2.7555F, -2.56F, 0.0175F, -0.0175F, 0.1309F));

		PartDefinition cube_r308 = left_leg.addOrReplaceChild("cube_r308", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.567F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.014F, 2.7555F, -2.56F, 0.0052F, -0.0052F, 0.6981F));

		PartDefinition cube_r309 = left_leg.addOrReplaceChild("cube_r309", CubeListBuilder.create().texOffs(61, 51).addBox(-0.436F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.339F, 1.8055F, -2.56F, -0.0087F, 0.0087F, 0.5672F));

		PartDefinition cube_r310 = left_leg.addOrReplaceChild("cube_r310", CubeListBuilder.create().texOffs(61, 51).mirror().addBox(-0.563F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.086F, 2.6555F, -2.56F, 0.007F, -0.007F, 0.7854F));

		PartDefinition cube_r311 = left_leg.addOrReplaceChild("cube_r311", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5002F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3362F, 7.1826F, -2.5003F, 0.0052F, -0.0053F, -0.2618F));

		PartDefinition cube_r312 = left_leg.addOrReplaceChild("cube_r312", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -0.5F, -0.0035F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.1888F, 6.3826F, -2.5003F, -0.0035F, -0.0087F, 1.2957F));

		PartDefinition cube_r313 = left_leg.addOrReplaceChild("cube_r313", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -1.0085F, 0.0051F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3406F, 6.2435F, -2.4961F, 0.017F, 0.0244F, -1.431F));

		PartDefinition cube_r314 = left_leg.addOrReplaceChild("cube_r314", CubeListBuilder.create().texOffs(58, 51).addBox(-0.3362F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2888F, 6.3826F, -2.5003F, 0.0072F, 0.0236F, -1.2957F));

		PartDefinition cube_r315 = left_leg.addOrReplaceChild("cube_r315", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.4948F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4362F, 7.1826F, -2.5003F, 0.0052F, 0.0053F, 0.2618F));

		PartDefinition cube_r316 = left_leg.addOrReplaceChild("cube_r316", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.2257F, 6.9826F, -2.4945F, 0.0044F, 0.0175F, 0.5192F));

		PartDefinition cube_r317 = left_leg.addOrReplaceChild("cube_r317", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1029F, 6.5175F, -2.5032F, 0.0035F, -0.0035F, -0.5192F));

		PartDefinition cube_r318 = left_leg.addOrReplaceChild("cube_r318", CubeListBuilder.create().texOffs(57, 51).addBox(-1.0052F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5652F, 6.7314F, -2.4881F, 0.0086F, -0.007F, -0.5192F));

		PartDefinition cube_r319 = left_leg.addOrReplaceChild("cube_r319", CubeListBuilder.create().texOffs(57, 51).mirror().addBox(-0.9948F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.1652F, 3.8064F, -2.4881F, 0.0086F, 0.007F, 0.5192F));

		PartDefinition cube_r320 = left_leg.addOrReplaceChild("cube_r320", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5029F, 3.5925F, -2.5032F, 0.0035F, 0.0035F, 0.5192F));

		PartDefinition cube_r321 = left_leg.addOrReplaceChild("cube_r321", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6258F, 4.0576F, -2.4945F, 0.0044F, -0.0175F, -0.5192F));

		PartDefinition cube_r322 = left_leg.addOrReplaceChild("cube_r322", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5052F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8362F, 4.2576F, -2.5003F, 0.0052F, -0.0053F, -0.2618F));

		PartDefinition cube_r323 = left_leg.addOrReplaceChild("cube_r323", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.6638F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.6888F, 3.4576F, -2.5003F, 0.0072F, -0.0236F, 1.2957F));

		PartDefinition cube_r324 = left_leg.addOrReplaceChild("cube_r324", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.5F, -1.0085F, 0.0051F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0594F, 3.3185F, -2.4961F, 0.017F, -0.0244F, 1.431F));

		PartDefinition cube_r325 = left_leg.addOrReplaceChild("cube_r325", CubeListBuilder.create().texOffs(58, 51).addBox(-0.5F, -0.5F, -0.0035F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2112F, 3.4576F, -2.5003F, -0.0035F, 0.0087F, -1.2957F));

		PartDefinition cube_r326 = left_leg.addOrReplaceChild("cube_r326", CubeListBuilder.create().texOffs(58, 51).mirror().addBox(-0.4998F, -0.5576F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0638F, 4.2576F, -2.5003F, 0.0052F, 0.0053F, 0.2618F));

		PartDefinition cube_r327 = left_leg.addOrReplaceChild("cube_r327", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.584F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.164F, 7.9305F, -2.56F, 0.0436F, -0.0436F, 0.0873F));

		PartDefinition cube_r328 = left_leg.addOrReplaceChild("cube_r328", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.591F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.864F, 8.1305F, -2.56F, 0.0105F, 0.0122F, -0.2182F));

		PartDefinition cube_r329 = left_leg.addOrReplaceChild("cube_r329", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.574F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.889F, 7.9805F, -2.56F, 0.0105F, 0.0105F, 1.0036F));

		PartDefinition cube_r330 = left_leg.addOrReplaceChild("cube_r330", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.311F, 8.4805F, -2.56F, 0.0175F, 0.0175F, -0.1309F));

		PartDefinition cube_r331 = left_leg.addOrReplaceChild("cube_r331", CubeListBuilder.create().texOffs(61, 46).addBox(-0.433F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.586F, 8.4805F, -2.56F, 0.0052F, 0.0052F, -0.6981F));

		PartDefinition cube_r332 = left_leg.addOrReplaceChild("cube_r332", CubeListBuilder.create().texOffs(61, 46).addBox(-0.437F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.686F, 8.3805F, -2.56F, 0.007F, 0.007F, -0.7854F));

		PartDefinition cube_r333 = left_leg.addOrReplaceChild("cube_r333", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.564F, -0.2235F, 0.0675F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.939F, 7.5305F, -2.56F, -0.0087F, -0.0087F, -0.5672F));

		PartDefinition cube_r334 = left_leg.addOrReplaceChild("cube_r334", CubeListBuilder.create().texOffs(61, 46).addBox(-0.416F, -0.2305F, 0.07F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.014F, 7.8055F, -2.56F, 0.0436F, 0.0436F, -0.0873F));

		PartDefinition cube_r335 = left_leg.addOrReplaceChild("cube_r335", CubeListBuilder.create().texOffs(61, 46).addBox(-0.409F, -0.3505F, 0.0631F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.714F, 8.0055F, -2.56F, 0.0105F, -0.0122F, 0.2182F));

		PartDefinition cube_r336 = left_leg.addOrReplaceChild("cube_r336", CubeListBuilder.create().texOffs(61, 46).addBox(-0.426F, -0.2205F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.739F, 7.8555F, -2.56F, 0.0105F, -0.0105F, -1.0036F));

		PartDefinition cube_r337 = left_leg.addOrReplaceChild("cube_r337", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, 0.0684F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.161F, 8.3555F, -2.56F, 0.0175F, -0.0175F, 0.1309F));

		PartDefinition cube_r338 = left_leg.addOrReplaceChild("cube_r338", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.567F, -0.2135F, 0.0618F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.436F, 8.3555F, -2.56F, 0.0052F, -0.0052F, 0.6981F));

		PartDefinition cube_r339 = left_leg.addOrReplaceChild("cube_r339", CubeListBuilder.create().texOffs(61, 46).mirror().addBox(-0.563F, -0.2135F, 0.0625F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.536F, 8.2555F, -2.56F, 0.007F, -0.007F, 0.7854F));

		PartDefinition right_boot = partdefinition.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(48, 56).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition left_boot = partdefinition.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(48, 56).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}