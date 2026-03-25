package com.zylr.minescapeaddons.addons.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.CommandSuggestions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CommandSuggestions.class)
public interface CommandSuggestionsAccessor {
    @Accessor("suggestionLineLimit")
    int minescapeaddons$getSuggestionLineLimit();

    @Accessor("fillColor")
    int minescapeaddons$getFillColor();

    @Accessor("font")
    Font minescapeaddons$getFont();
}