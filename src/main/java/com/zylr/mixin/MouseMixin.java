package com.zylr.mixin;

import com.zylr.client.hud.HudManager;
import com.zylr.client.screen.HudInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseMixin {
	@Inject(method = "onMove", at = @At("HEAD"))
	private void minescapeaddon$updateHudCursor(long window, double x, double y, CallbackInfo info) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.getWindow().getWidth() <= 0 || minecraft.getWindow().getHeight() <= 0) {
			return;
		}
		if (minecraft.screen != null && !(minecraft.screen instanceof HudInventoryScreen)) {
			return;
		}

		HudManager manager = HudManager.getInstance();
		double scaledX = x * minecraft.getWindow().getGuiScaledWidth() / (double) minecraft.getWindow().getWidth();
		double scaledY = y * minecraft.getWindow().getGuiScaledHeight() / (double) minecraft.getWindow().getHeight();
		manager.updateCursor(scaledX, scaledY);
	}

	@Inject(method = "onButton", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$clickHud(long window, MouseButtonInfo buttonInfo, int action, CallbackInfo info) {
		if (action != GLFW.GLFW_PRESS) {
			return;
		}

		HudManager.getInstance().recordActivity();

		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.screen != null) {
			return;
		}

		HudManager manager = HudManager.getInstance();
		if (manager.clickHud(
			manager.cursorX(),
			manager.cursorY(),
			minecraft.getWindow().getGuiScaledWidth(),
			minecraft.getWindow().getGuiScaledHeight(),
			buttonInfo.button(),
			false
		)) {
			info.cancel();
		}
	}

	@Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$scrollHud(long window, double horizontalAmount, double verticalAmount, CallbackInfo info) {
		HudManager.getInstance().recordActivity();

		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.screen != null) {
			return;
		}

		HudManager manager = HudManager.getInstance();
		if (manager.scrollHud(
			manager.cursorX(),
			manager.cursorY(),
			minecraft.getWindow().getGuiScaledWidth(),
			minecraft.getWindow().getGuiScaledHeight(),
			verticalAmount
		)) {
			info.cancel();
		}
	}
}



