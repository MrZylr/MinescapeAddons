package com.zylr.client.hud;

import com.zylr.utils.util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.OptionalInt;

public final class TargetInfoWidget extends HudWidget {
	private static final int BASE_WIDTH = 132;
	private static final int BASE_HEIGHT = 70;

	TargetInfoWidget(double defaultX, double defaultY, double defaultScale) {
		super("targetInfoWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return BASE_WIDTH; }

	@Override
	protected int baseHeight() { return BASE_HEIGHT; }

	@Override
	protected int activeRightBound(int screenWidth, int screenHeight) {
		return super.activeRightBound(screenWidth, screenHeight) - DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected int activeBottomBound(int screenWidth, int screenHeight) {
		return super.activeBottomBound(screenWidth, screenHeight) - DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected int clampExtraRightBounds(int screenWidth, int screenHeight) {
		return -DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected int clampExtraBottomBounds(int screenWidth, int screenHeight) {
		return -DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected boolean shouldRenderWidget(Minecraft minecraft, boolean editMode) {
		HudManager manager = HudManager.getInstance();
		return editMode || (manager.isTargetInfoEnabled() && manager.hasTarget());
	}

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isTargetInfoEnabled();
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		HudManager manager = HudManager.getInstance();
		if (!manager.isTargetInfoEnabled()) return;
		HudManager.TargetInfo target = manager.getTargetInfo(minecraft);
		if (target == null) {
			return;
		}

		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		int frameInset = DialogFrameRenderer.horizontalInset(this.scale());
		int x = this.pixelX(minecraft.getWindow().getGuiScaledWidth()) - inset;
		int y = this.pixelY(minecraft.getWindow().getGuiScaledHeight()) - inset;
		int width = this.pixelWidth();
		int height = this.pixelHeight();
		int padding = DialogFrameRenderer.innerPadding(this.scale());
		float textScale = Math.max(0.5F, (float) this.scale());
		int lineHeight = HudManager.scaledTextHeight(minecraft, textScale);
		int titleHeight = lineHeight + padding * 2;
		int innerX = x + frameInset + padding;
		int innerWidth = width - frameInset * 2 - padding * 2;

		DialogFrameRenderer.draw(graphics, x, y, width, height, titleHeight, this.scale());

		String name = fitText(minecraft, target.name(), textScale, innerWidth);
		int nameX = innerX + (innerWidth - HudManager.scaledTextWidth(minecraft, name, textScale)) / 2;
		HudManager.drawScaledText(graphics, minecraft, name, nameX, y + DialogFrameRenderer.topInset(this.scale()) + padding, 0xFFFFFF00, textScale);

		String hpText = target.currentHp() + "/" + target.totalHp();
		int hpTextX = innerX + (innerWidth - HudManager.scaledTextWidth(minecraft, hpText, textScale)) / 2;
		int hpTextY = y + Math.max(DialogFrameRenderer.topInset(this.scale()) + titleHeight + padding, height - DialogFrameRenderer.bottomInset(this.scale()) - padding - lineHeight - Math.max(5, Math.round(6 * (float) this.scale())));
		HudManager.drawScaledText(graphics, minecraft, hpText, hpTextX, hpTextY, 0xFFFFFFFF, textScale);

		int barHeight = Math.max(5, (int) Math.round(6 * this.scale()));
		int barX = innerX;
		int barY = y + height - DialogFrameRenderer.bottomInset(this.scale()) - padding - barHeight;
		int barWidth = Math.max(1, innerWidth);
		int greenWidth = Mth.clamp((int) Math.round(barWidth * target.hpPercent()), 0, barWidth);

		graphics.fill(barX, barY, barX + barWidth, barY + barHeight, 0xFF8A1717);
		graphics.fill(barX, barY, barX + greenWidth, barY + barHeight, 0xFF2FB43A);
		graphics.fill(barX, barY, barX + barWidth, barY + 1, 0xFF1A1A1A);
		graphics.fill(barX, barY + barHeight - 1, barX + barWidth, barY + barHeight, 0xFF1A1A1A);
		graphics.fill(barX, barY, barX + 1, barY + barHeight, 0xFF1A1A1A);
		graphics.fill(barX + barWidth - 1, barY, barX + barWidth, barY + barHeight, 0xFF1A1A1A);
	}

	private static String fitText(Minecraft minecraft, String text, float scale, int maxWidth) {
		if (HudManager.scaledTextWidth(minecraft, text, scale) <= maxWidth) return text;
		String ellipsis = "...";
		String trimmed = text;
		while (trimmed.length() > 1 && HudManager.scaledTextWidth(minecraft, trimmed + ellipsis, scale) > maxWidth) {
			trimmed = trimmed.substring(0, trimmed.length() - 1);
		}
		return trimmed + ellipsis;
	}

	static HudManager.TargetInfo resolve(Minecraft minecraft, Entity entity, int totalHp) {
		if (minecraft == null || entity == null || totalHp <= 0 || !entity.isAlive()) return null;
		String displayName = entity.getDisplayName().getString();

		OptionalInt currentHp = util.getHpFromName(displayName);
		if (currentHp.isEmpty() || currentHp.getAsInt() <= 0) return null;
		Optional<String> name = util.getEntityName(displayName);
		return new HudManager.TargetInfo(name.orElse(entity.getName().getString()), currentHp.getAsInt(), totalHp);
	}
}
