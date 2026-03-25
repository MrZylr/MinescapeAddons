package com.zylr.minescapeaddons.addons.item.cape.skillcapes;

import com.zylr.minescapeaddons.addons.item.cape.CapeModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;

public class SkillCapeModel extends CapeModel {
    private final ModelPart cape;

    public SkillCapeModel(ModelPart root) {
        super(root);
        this.cape = root.getChild("cape");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition cape = partdefinition.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -24.0F, -1.0F, 10.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 1).addBox(3.0F, -24.0F, -1.15F, 2.0F, 22.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(23, 1).mirror().addBox(-5.0F, -24.0F, -1.15F, 2.0F, 22.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = cape.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 30).mirror().addBox(-1.0F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(1.05F, -0.5F, -0.3F, 0.0F, 0.0F, -0.3491F));

        PartDefinition cube_r2 = cape.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 30).addBox(-4.0F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-1.05F, -0.5F, -0.3F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r3 = cape.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition cube_r4 = cape.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -1.0F, -1.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -24.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition cube_r5 = cape.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 27).addBox(-4.0F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-1.05F, -21.2F, -1.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r6 = cape.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 27).mirror().addBox(-1.0F, -2.0F, 0.0F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(1.05F, -21.2F, -1.0F, 0.0F, 0.0F, -0.3491F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
}