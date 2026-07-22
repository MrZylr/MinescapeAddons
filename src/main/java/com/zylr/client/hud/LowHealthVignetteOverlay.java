package com.zylr.client.hud;

import com.zylr.client.PerfDebug;
import com.zylr.client.skills.SkillType;
import com.zylr.client.skills.Skills;
import com.zylr.player.PlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;

public final class LowHealthVignetteOverlay {
	private static final float HEALTH_THRESHOLD = 0.15F;
	private static final int MIN_ALPHA = 35;
	private static final int MAX_ALPHA = 150;
	private static final int LAYERS = 24;

	private LowHealthVignetteOverlay() {
	}

	public static void render(GuiGraphicsExtractor graphics, Minecraft minecraft) {
		long start = PerfDebug.start();
		if (minecraft == null || minecraft.player == null) return;
		if (!HudManager.getInstance().isLowHealthVignetteEnabled()) return;

		int maxHealth = Math.max(1, Skills.getInstance().getLevel(SkillType.HITPOINTS));
		int health = Mth.clamp(PlayerStats.getHealth(), 0, maxHealth);
		if (health == 0)
			return;
		float healthPercent = health / (float) maxHealth;
		if (healthPercent >= HEALTH_THRESHOLD) return;

		int width = graphics.guiWidth();
		int height = graphics.guiHeight();
		if (width <= 0 || height <= 0) return;

		float danger = 1.0F - healthPercent / HEALTH_THRESHOLD;
		int edgeWidth = Mth.clamp(Math.min(width, height) / 5, 32, 96);
		for (int layer = 0; layer < LAYERS; layer++) {
			float edgeProgress = 1.0F - layer / (float) LAYERS;
			int inset = layer * edgeWidth / LAYERS;
			int nextInset = Math.max(inset + 1, (layer + 1) * edgeWidth / LAYERS);
			int alpha = Math.round((MIN_ALPHA + (MAX_ALPHA - MIN_ALPHA) * danger) * edgeProgress * edgeProgress);
			if (alpha <= 0) continue;

			int color = (Mth.clamp(alpha, 0, 255) << 24) | 0x00FF0000;
			graphics.fill(inset, inset, width - inset, Math.min(height - inset, nextInset), color);
			graphics.fill(inset, Math.max(inset, height - nextInset), width - inset, height - inset, color);
			graphics.fill(inset, nextInset, Math.min(width - inset, nextInset), height - nextInset, color);
			graphics.fill(Math.max(inset, width - nextInset), nextInset, width - inset, height - nextInset, color);
		}
		PerfDebug.record("overlay.lowHealth", start);
	}
}
