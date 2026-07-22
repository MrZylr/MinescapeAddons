package com.zylr.client.items.cape.skillcapes;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.MinescapeAddon;
import com.zylr.client.items.cape.CapeModel;
import com.zylr.client.items.cape.CustomCape;
import com.zylr.client.items.cape.CustomCapeRenderState;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MaxCapeLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    public static final Identifier TYPE = Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "max_cape");
    private static final int MAX_CAPE_DAMAGE = 0;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
        MinescapeAddon.MOD_ID,
        "textures/capes/skillcapes/max_cape.png"
    );
    private static final CustomCape CAPE = new CustomCape(TYPE, TEXTURE);

    private final CapeModel model = new MaxCapeModel(MaxCapeModel.createBodyLayer().bakeRoot());

    public MaxCapeLayer(RenderLayerParent<AvatarRenderState, PlayerModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, AvatarRenderState renderState, float yRot, float xRot) {
        CustomCapeRenderState capeState = (CustomCapeRenderState) renderState;
        Identifier texture = capeState.minescapeaddon$getCapeTexture();
        if (texture == null || !TYPE.equals(capeState.minescapeaddon$getCapeType()) || renderState.isInvisible) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.1875F);
        submitNodeCollector.submitModel(
            this.model,
            renderState,
            poseStack,
            RenderTypes.entitySolid(texture),
            packedLight,
            OverlayTexture.NO_OVERLAY,
            renderState.outlineColor,
            null
        );
        poseStack.popPose();
    }

    public static CustomCape resolve(ItemStack stack) {
        if (stack.getItem() == Items.FLINT && stack.getDamageValue() == MAX_CAPE_DAMAGE) {
            return CAPE;
        }
        return null;
    }
}
