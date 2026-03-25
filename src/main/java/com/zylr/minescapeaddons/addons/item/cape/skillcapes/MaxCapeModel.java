package com.zylr.minescapeaddons.addons.item.cape.skillcapes;// Made with Blockbench 5.0.7


import com.zylr.minescapeaddons.addons.item.cape.CapeModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MaxCapeModel extends CapeModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart cape;

    public MaxCapeModel(ModelPart root) {
        super(root);
        this.cape = root.getChild("cape");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition cape = partdefinition.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -24.0F, -1.025F, 10.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).addBox(-5.025F, -24.15F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(30, 10).addBox(-4.25F, -24.4F, -0.95F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).mirror().addBox(3.025F, -24.15F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(30, 10).mirror().addBox(2.25F, -24.4F, -0.975F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 14).addBox(-4.5F, -24.35F, -1.225F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 20).addBox(-3.0F, -13.0F, -1.0F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = cape.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 21).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.825F, -12.0F, 0.075F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r2 = cape.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -22.0F, 0.025F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r3 = cape.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 11).mirror().addBox(-1.0F, 7.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.575F, -12.3F, 0.075F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r4 = cape.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 11).addBox(-1.0F, 7.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.575F, -12.3F, 0.075F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r5 = cape.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 7).mirror().addBox(-2.0F, 2.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.175F, -7.15F, 0.275F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r6 = cape.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 7).addBox(-1.0F, 2.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.175F, -7.15F, 0.275F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r7 = cape.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(22, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.725F, -6.15F, 0.05F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r8 = cape.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.25F, -12.0F, 0.075F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r9 = cape.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.775F, -6.15F, 0.05F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r10 = cape.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(58, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -12.0F, 0.075F, 0.0F, 0.0F, 0.0873F));

        PartDefinition cube_r11 = cape.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(22, 29).mirror().addBox(-1.0F, 7.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.05F, -10.95F, 0.1F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r12 = cape.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(22, 29).addBox(-1.0F, 7.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.05F, -10.95F, 0.35F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r13 = cape.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(6, 21).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.825F, -12.0F, 0.325F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r14 = cape.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(12, 30).mirror().addBox(-3.0F, 10.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.025F, -21.275F, 0.05F, 0.0F, 0.0F, 0.0611F));

        PartDefinition cube_r15 = cape.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(12, 30).addBox(-1.0F, 10.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.025F, -21.275F, 0.05F, 0.0F, 0.0F, -0.0611F));

        PartDefinition cube_r16 = cape.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -22.0F, 0.025F, 0.0F, 0.0F, 0.1309F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}