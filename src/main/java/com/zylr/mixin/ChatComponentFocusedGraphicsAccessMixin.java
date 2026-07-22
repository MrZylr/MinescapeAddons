package com.zylr.mixin;

import com.zylr.client.hud.ChatWidget;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.components.ChatComponent$DrawingFocusedGraphicsAccess")
public abstract class ChatComponentFocusedGraphicsAccessMixin implements ChatComponent.ChatGraphicsAccess {
	@Inject(method = "fill", at = @At("HEAD"), cancellable = true)
	private void minescapeaddon$skipCustomChatBackgroundFill(int x0, int y0, int x1, int y1, int color, CallbackInfo info) {
		if (ChatWidget.isRenderingCustomChat()) {
			info.cancel();
		}
	}
}
