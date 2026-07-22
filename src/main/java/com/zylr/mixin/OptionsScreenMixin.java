package com.zylr.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
	protected OptionsScreenMixin(Component title) {
		super(title);
	}
}



