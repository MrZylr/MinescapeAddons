package com.zylr.mixin;

import com.zylr.client.PerfDebug;
import com.zylr.client.hud.ChatWidget;
import com.zylr.client.hud.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
	@Shadow protected EditBox input;
	@Shadow private String initial;
	@Shadow private ChatComponent.DisplayMode displayMode;
	@Shadow private CommandSuggestions commandSuggestions;
	@Shadow private boolean insertionClickMode() { return false; }
	@Shadow private boolean handleComponentClicked(Style style, boolean insertionClickMode) { return false; }

	protected ChatScreenMixin(Minecraft minecraft, Font font, Component title) {
		super(minecraft, font, title);
	}

	@Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$extractChatWidget(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo info) {
		if (!HudManager.getInstance().isCustomChatEnabled()) {
			return;
		}
		ChatWidget chatWidget = HudManager.getInstance().chatWidget();
		ChatWidget.Layout layout = chatWidget.layout(this.minecraft, true);
		long frameStart = PerfDebug.start();
		ChatWidget.renderFrame(graphics, layout);
		PerfDebug.record("chat.frame", frameStart);
		long chatStart = PerfDebug.start();
		ChatWidget.renderChat(graphics, this.minecraft, layout, mouseX, mouseY, this.displayMode, this.insertionClickMode());
		PerfDebug.record("chat.messages", chatStart);

		this.minescapeaddon$positionChatInput(layout);
		long inputStart = PerfDebug.start();
		super.extractRenderState(graphics, mouseX, mouseY, delta);
		PerfDebug.record("chat.input", inputStart);
		long suggestionsStart = PerfDebug.start();
		this.commandSuggestions.extractRenderState(graphics, mouseX, mouseY);
		PerfDebug.record("chat.suggestions", suggestionsStart);
		info.cancel();
	}

	@Inject(method = "keyPressed", at = @At("HEAD"))
	private void minescapeaddon$positionChatInputBeforeKeyPressed(net.minecraft.client.input.KeyEvent event, CallbackInfoReturnable<Boolean> info) {
		if (!HudManager.getInstance().isCustomChatEnabled()) {
			return;
		}
		this.minescapeaddon$positionChatInput(HudManager.getInstance().chatWidget().layout(this.minecraft, true));
	}

	@Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$clickTranslatedChatWidget(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> info) {
		if (!HudManager.getInstance().isCustomChatEnabled() || event.button() != 0) {
			return;
		}
		if (this.commandSuggestions.mouseClicked(event)) {
			info.setReturnValue(true);
			return;
		}

		ChatWidget.Layout layout = HudManager.getInstance().chatWidget().layout(this.minecraft, true);
		if (!ChatWidget.isWithinChatContent(layout, event.x(), event.y())) {
			return;
		}
		ActiveTextCollector.ClickableStyleFinder finder = new ActiveTextCollector.ClickableStyleFinder(
			this.getFont(),
			ChatWidget.toVanillaChatMouseX(layout, event.x()),
			ChatWidget.toVanillaChatMouseY(this.minecraft, layout, event.y())
		).includeInsertions(this.insertionClickMode());
		this.minecraft.gui.getChat().captureClickableText(
			finder,
			this.minecraft.getWindow().getGuiScaledHeight(),
			this.minecraft.gui.getGuiTicks(),
			this.displayMode
		);
		Style style = finder.result();
		if (style != null && this.handleComponentClicked(style, this.insertionClickMode())) {
			this.initial = this.input.getValue();
			info.setReturnValue(true);
			return;
		}

		this.minescapeaddon$positionChatInput(layout);
		info.setReturnValue(super.mouseClicked(event, doubleClick));
	}

	private void minescapeaddon$positionChatInput(ChatWidget.Layout layout) {
		this.input.setX(layout.inputX());
		this.input.setY(layout.inputY() + 1);
		this.input.setWidth(layout.inputWidth());
		this.input.setHeight(Math.max(12, layout.inputHeight() - 2));
	}
}
