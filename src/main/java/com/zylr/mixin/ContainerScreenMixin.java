package com.zylr.mixin;

import com.zylr.client.screen.overridescreens.categories.CustomContainerScreenRegistry;
import com.zylr.client.screen.overridescreens.GrandExchangePollingState;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerScreen.class)
public abstract class ContainerScreenMixin {
	@Inject(method = "extractBackground", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideGrandExchangeBackgroundWhilePolling(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo info) {
		if (!((Object) this instanceof AbstractContainerScreen<?> screen)) {
			return;
		}
		if (!CustomContainerScreenRegistry.shouldUseGrandExchangeScreen(screen.getTitle().getString())) {
			return;
		}
		if (!(screen.getMenu() instanceof net.minecraft.world.inventory.ChestMenu chestMenu)) {
			return;
		}
		if (!GrandExchangePollingState.isPending(chestMenu)) {
			return;
		}
		info.cancel();
	}
}
