package com.zylr.mixin;

import com.zylr.client.hud.ChatWidget;
import com.zylr.client.hud.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommandSuggestions.class)
public abstract class CommandSuggestionsMixin {
	@Redirect(
		method = "showSuggestions",
		at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/Screen;height:I")
	)
	private int minescapeaddon$chatWidgetSuggestionAnchor(Screen screen) {
		return this.minescapeaddon$customChatAnchorHeight(screen.height);
	}

	@Redirect(
		method = "extractUsage",
		at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/Screen;height:I")
	)
	private int minescapeaddon$chatWidgetUsageAnchor(Screen screen) {
		return this.minescapeaddon$customChatAnchorHeight(screen.height);
	}

	private int minescapeaddon$customChatAnchorHeight(int fallback) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || !HudManager.getInstance().isCustomChatEnabled()) {
			return fallback;
		}
		return ChatWidget.commandSuggestionsAnchorScreenHeight(minecraft);
	}
}
