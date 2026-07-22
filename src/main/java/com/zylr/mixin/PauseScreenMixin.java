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

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
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
			.bounds(buttonX, buttonY + 24, buttonWidth, 20)
			.build());
	}
}
