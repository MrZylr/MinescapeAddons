package com.zylr.mixin;

import com.zylr.client.screen.HudEditScreen;
import com.zylr.client.screen.HudSettingsScreen;
import com.zylr.client.screen.FarmingTimersScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Desktop;
import java.net.URI;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
	private static final String DARK_MODE_CHAT_LINK = "https://github.com/MrZylr/MineScape-Addons-Resource-Pack";

	@Shadow
	private Button disconnectButton;

	protected PauseScreenMixin(Component title) {
		super(title);
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void minescapeaddon$addEditHudButton(CallbackInfo info) {
		if (this.disconnectButton == null) return;
		int buttonX = this.disconnectButton.getX();
		int buttonY = this.disconnectButton.getY();
		int buttonWidth = this.disconnectButton.getWidth();
		int halfGap = 4;
		int halfWidth = (buttonWidth - halfGap) / 2;

		this.disconnectButton.setY(buttonY + 48);
		this.addRenderableWidget(Button.builder(Component.literal("Edit HUD"), button -> this.minecraft.setScreen(new HudEditScreen((Screen) (Object) this)))
			.bounds(buttonX, buttonY, halfWidth, 20)
			.build());
		this.addRenderableWidget(Button.builder(Component.literal("Minescape Addon Settings"), button -> this.minecraft.setScreen(new HudSettingsScreen((Screen) (Object) this)))
			.bounds(buttonX + halfWidth + halfGap, buttonY, buttonWidth - halfWidth - halfGap, 20)
			.build());
		this.addRenderableWidget(Button.builder(Component.literal("Farming Timers"), button -> this.minecraft.setScreen(new FarmingTimersScreen((Screen) (Object) this)))
			.bounds(buttonX, buttonY + 24, halfWidth, 20)
			.build());
		this.addRenderableWidget(Button.builder(Component.literal("Dark Mode Chat Link"), button -> this.minescapeaddon$openExternal(DARK_MODE_CHAT_LINK))
			.bounds(buttonX + halfWidth + halfGap, buttonY + 24, buttonWidth - halfWidth - halfGap, 20)
			.build());
	}

	private void minescapeaddon$openExternal(String url) {
		try {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(URI.create(url));
				return;
			}
		} catch (Exception ignored) {
		}

		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				new ProcessBuilder("cmd", "/c", "start", "", url).start();
			} else if (os.contains("mac")) {
				new ProcessBuilder("open", url).start();
			} else {
				new ProcessBuilder("xdg-open", url).start();
			}
		} catch (Exception ignored) {
		}
	}
}
