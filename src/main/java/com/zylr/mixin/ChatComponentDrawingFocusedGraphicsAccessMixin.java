package com.zylr.mixin;

import com.zylr.client.hud.ChatWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.chat.GuiMessageTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.components.ChatComponent$DrawingFocusedGraphicsAccess")
public abstract class ChatComponentDrawingFocusedGraphicsAccessMixin {
	@ModifyArg(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;textRenderer(Lnet/minecraft/client/gui/GuiGraphicsExtractor$HoveredTextEffects;Ljava/util/function/Consumer;)Lnet/minecraft/client/gui/ActiveTextCollector;"
		),
		index = 0
	)
	private GuiGraphicsExtractor.HoveredTextEffects minescapeaddon$suppressCustomChatTextHover(GuiGraphicsExtractor.HoveredTextEffects effects) {
		return ChatWidget.isSuppressingCustomChatHover() ? GuiGraphicsExtractor.HoveredTextEffects.NONE : effects;
	}

	@Inject(method = "showTooltip", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$suppressCustomChatTagTooltip(GuiMessageTag tag, CallbackInfo info) {
		if (ChatWidget.isSuppressingCustomChatHover()) {
			info.cancel();
		}
	}
}
