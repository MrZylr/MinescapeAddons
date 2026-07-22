package com.zylr.mixin;

import com.zylr.client.hud.HudManager;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Mixin(BossHealthOverlay.class)
public abstract class BossHealthOverlayMixin {
	@Redirect(
		method = "extractRenderState",
		at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;")
	)
	private Collection<LerpingBossEvent> minescapeaddon$filterXpDropBossBars(Map<?, LerpingBossEvent> events) {
		HudManager hudManager = HudManager.getInstance();
		if (!hudManager.isXpDropOrbsEnabled()) {
			return events.values();
		}

		ArrayList<LerpingBossEvent> filtered = new ArrayList<>(events.size());
		for (LerpingBossEvent event : events.values()) {
			if (!HudManager.isXpDropBossBar(event.getName())) {
				filtered.add(event);
			}
		}
		return filtered;
	}

	@Redirect(
		method = "extractRenderState",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"
		)
	)
	private void minescapeaddon$hideBrandingBossBarTitle(
		GuiGraphicsExtractor graphics,
		net.minecraft.client.gui.Font font,
		net.minecraft.network.chat.Component text,
		int x,
		int y,
		int color
	) {
		if (HudManager.getInstance().isScoreboardBrandingRemovalEnabled() && HudManager.shouldRemoveBossBarTitle(text)) {
			return;
		}
		graphics.text(font, text, x, y, color);
	}
}
