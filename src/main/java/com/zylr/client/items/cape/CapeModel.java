package com.zylr.client.items.cape;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class CapeModel extends Model<AvatarRenderState> {
    protected final ModelPart cape;

    public CapeModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
        this.cape = root.getChild("cape");
    }

    @Override
    public void setupAnim(AvatarRenderState renderState) {
        super.setupAnim(renderState);
        this.cape.yRot = (float) Math.PI;
        this.cape.xRot = (float) -Math.toRadians(6.0F + renderState.capeLean / 2.0F + renderState.capeFlap);
        this.cape.zRot = (float) Math.toRadians(renderState.capeLean2 / 2.0F);
        this.cape.yRot += (float) Math.toRadians(-renderState.capeLean2 / 2.0F);
    }
}
