package com.zylr.client.items.cape;

import net.minecraft.resources.Identifier;

public interface CustomCapeRenderState {
    Identifier minescapeaddon$getCapeType();

    void minescapeaddon$setCapeType(Identifier type);

    Identifier minescapeaddon$getCapeTexture();

    void minescapeaddon$setCapeTexture(Identifier texture);
}
