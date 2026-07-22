package com.zylr.mixin;

import com.zylr.client.PerfDebug;
import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.LowHealthVignetteOverlay;
import com.zylr.client.screen.overridescreens.categories.CustomContainerScreenRegistry;
import com.zylr.client.screen.HudEditScreen;
import com.zylr.client.screen.HudInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.DeltaTracker;
import net.minecraft.world.scores.Objective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class InGameHudMixin {
	@Unique
	private boolean minescapeaddon$vanillaBottomHudShifted;

	@Inject(method = "extractRenderState", at = @At("TAIL"))
	private void minescapeaddon$renderHud(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo info) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.player == null || minecraft.screen instanceof HudEditScreen || minecraft.screen instanceof HudInventoryScreen || CustomContainerScreenRegistry.isCustomContainerScreen(minecraft.screen)) {
			return;
		}

		HudManager manager = HudManager.getInstance();
		int cursorX = (int) manager.cursorX();
		int cursorY = (int) manager.cursorY();
		long hudStart = PerfDebug.start();
		manager.render(graphics, minecraft, cursorX, cursorY, 0.0F, false, null);
		PerfDebug.record("hud.root", hudStart);
		long menuStart = PerfDebug.start();
		manager.renderRuntimeContextMenu(graphics, minecraft, cursorX, cursorY);
		PerfDebug.record("hud.contextMenu", menuStart);
		long vignetteStart = PerfDebug.start();
		LowHealthVignetteOverlay.render(graphics, minecraft);
		PerfDebug.record("hud.vignette", vignetteStart);
	}

	@Inject(method = "extractHotbarAndDecorations", at = @At("HEAD"))
	private void minescapeaddon$attachVanillaBottomHudToContentPanel(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo info) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.player == null || minecraft.screen instanceof HudEditScreen || minecraft.screen instanceof HudInventoryScreen || CustomContainerScreenRegistry.isCustomContainerScreen(minecraft.screen)) {
			this.minescapeaddon$vanillaBottomHudShifted = false;
			return;
		}

		HudManager manager = HudManager.getInstance();
		int screenWidth = graphics.guiWidth();
		int screenHeight = graphics.guiHeight();
		int offsetX = manager.vanillaBottomHudOffsetX(screenWidth, screenHeight);
		int offsetY = manager.vanillaBottomHudOffsetY(screenWidth, screenHeight);
		this.minescapeaddon$vanillaBottomHudShifted = offsetX != 0 || offsetY != 0;
		if (this.minescapeaddon$vanillaBottomHudShifted) {
			graphics.pose().pushMatrix();
			graphics.pose().translate(offsetX, offsetY);
		}
	}

	@Inject(method = "extractHotbarAndDecorations", at = @At("RETURN"))
	private void minescapeaddon$restoreVanillaBottomHudPose(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo info) {
		if (this.minescapeaddon$vanillaBottomHudShifted) {
			graphics.pose().popMatrix();
			this.minescapeaddon$vanillaBottomHudShifted = false;
		}
	}

	@Inject(method = "extractChat", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideVanillaChat(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo info) {
		if (HudManager.getInstance().isCustomChatEnabled()) {
			info.cancel();
		}
	}

	@Inject(method = "extractOverlayMessage", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideVanillaOverlayMessage(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo info) {
		info.cancel();
	}

	@Inject(method = "displayScoreboardSidebar", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$hideVanillaSidebar(GuiGraphicsExtractor graphics, Objective objective, CallbackInfo info) {
		if (HudManager.getInstance().shouldHideVanillaScoreboard()) {
			info.cancel();
		}
	}
}


