package com.zylr.mixin;

import com.zylr.client.items.cape.CustomCapeRenderState;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AvatarRenderState.class)
public class AvatarRenderStateMixin implements CustomCapeRenderState {
    private Identifier minescapeaddon$capeType;
    private Identifier minescapeaddon$capeTexture;

    @Override
    public Identifier minescapeaddon$getCapeType() {
        return this.minescapeaddon$capeType;
    }

    @Override
    public void minescapeaddon$setCapeType(Identifier type) {
        this.minescapeaddon$capeType = type;
    }

    @Override
    public Identifier minescapeaddon$getCapeTexture() {
        return this.minescapeaddon$capeTexture;
    }

    @Override
    public void minescapeaddon$setCapeTexture(Identifier texture) {
        this.minescapeaddon$capeTexture = texture;
    }
}
