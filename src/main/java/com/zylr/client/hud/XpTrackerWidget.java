package com.zylr.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.Locale;

public final class XpTrackerWidget extends HudWidget {
	private static final int BASE_WIDTH = 92;
	private static final int BASE_HEIGHT = 42;
	private int computedBaseWidth = BASE_WIDTH;
	private int computedBaseHeight = BASE_HEIGHT;
	private int lastRenderedHeight = BASE_HEIGHT;
	private String cachedLabel = "";
	private String cachedGainedText = "0";
	private String cachedPerHourText = "0";
	private String cachedSessionTime = "00:00:00";
	private double cachedGained = Double.NaN;
	private long cachedElapsedSecond = Long.MIN_VALUE;
	private String cachedSessionLabel = "";

	XpTrackerWidget(double defaultX, double defaultY, double defaultScale) {
		super("xpTrackerWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return this.computedBaseWidth; }

	@Override
	protected int baseHeight() { return this.computedBaseHeight; }

	@Override
	protected int extraBottomBounds(int sw, int sh) {
		return Math.max(0, this.lastRenderedHeight - this.pixelHeight());
	}

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isXpTrackerEnabled();
	}

	@Override
	protected int activeLeftBound(int screenWidth, int screenHeight) {
		return super.activeLeftBound(screenWidth, screenHeight);
	}

	@Override
	protected int clampExtraLeftBounds(int screenWidth, int screenHeight) {
		return 0;
	}

	@Override
	protected int activeTopBound(int screenWidth, int screenHeight) {
		return super.activeTopBound(screenWidth, screenHeight);
	}

	@Override
	protected int clampExtraTopBounds(int screenWidth, int screenHeight) {
		return 0;
	}

	@Override
	protected int activeRightBound(int screenWidth, int screenHeight) {
		return super.activeRightBound(screenWidth, screenHeight) - DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected int clampExtraRightBounds(int screenWidth, int screenHeight) {
		return -DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected int activeBottomBound(int screenWidth, int screenHeight) {
		return super.activeBottomBound(screenWidth, screenHeight) - DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected int clampExtraBottomBounds(int screenWidth, int screenHeight) {
		return -DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		if (!HudManager.getInstance().isXpTrackerEnabled()) return;

		HudManager manager = HudManager.getInstance();
		HudManager.XpTrackerSession session = manager.getTrackedXpSession();
		double gained = Math.max(0.0D, session.gained());
		long elapsedMillis = session.elapsedMillis();
		this.updateCachedText(session, gained, elapsedMillis);
		String titleText = this.cachedLabel;

		int padding = DialogFrameRenderer.innerPadding(this.scale());
		float textScale = (float) this.scale();
		int lineHeight = HudManager.scaledTextHeight(minecraft, textScale);
		int leftInset = DialogFrameRenderer.horizontalInset(this.scale());
		int topInset = DialogFrameRenderer.topInset(this.scale());
		int titleHeight = lineHeight + padding * 2;
		int contentWidth = Math.max(
				HudManager.scaledTextWidth(minecraft, titleText, textScale),
				Math.max(
					HudManager.scaledTextWidth(minecraft, "Gained: " + this.cachedGainedText, textScale),
					Math.max(
						HudManager.scaledTextWidth(minecraft, "XP/hr: " + this.cachedPerHourText, textScale),
						HudManager.scaledTextWidth(minecraft, "Session: " + this.cachedSessionTime, textScale)
					)
				)
		);
		int width = contentWidth + padding * 2 + leftInset * 2;
		int height = DialogFrameRenderer.topInset(this.scale()) + titleHeight + padding * 2 + lineHeight * 3 + DialogFrameRenderer.bottomInset(this.scale());
		int baseWidth = Math.max(10, (int) Math.ceil(width / this.scale()));
		int baseHeight = Math.max(10, (int) Math.ceil(height / this.scale()));
		if (this.computedBaseWidth != baseWidth || this.computedBaseHeight != baseHeight) {
			this.computedBaseWidth = baseWidth;
			this.computedBaseHeight = baseHeight;
			this.invalidateBoundsCache();
		}
		this.lastRenderedHeight = height;

		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		int x = this.pixelX(minecraft.getWindow().getGuiScaledWidth());
		int y = this.pixelY(minecraft.getWindow().getGuiScaledHeight());
		width = this.pixelWidth();
		height = this.pixelHeight();
		x -= inset;
		y -= inset;

		DialogFrameRenderer.draw(graphics, x, y, width, height, titleHeight, this.scale());

		int innerWidth = width - leftInset * 2;
		int titleX = x + leftInset + (innerWidth - HudManager.scaledTextWidth(minecraft, titleText, textScale)) / 2;
		HudManager.drawScaledText(graphics, minecraft, titleText, titleX, y + topInset + padding, 0xFFFFFF00, textScale);

		int textY = y + topInset + titleHeight + padding;
		HudManager.drawScaledText(graphics, minecraft, "Gained: " + this.cachedGainedText, x + leftInset + padding, textY, 0xFFFFFFFF, textScale);
		textY += lineHeight;
		HudManager.drawScaledText(graphics, minecraft, "XP/hr: " + this.cachedPerHourText, x + leftInset + padding, textY, 0xFFFFFFFF, textScale);
		textY += lineHeight;
		HudManager.drawScaledText(graphics, minecraft, "Session: " + this.cachedSessionTime, x + leftInset + padding, textY, 0xFFFFFFFF, textScale);
	}

	private void updateCachedText(HudManager.XpTrackerSession session, double gained, long elapsedMillis) {
		long elapsedSecond = elapsedMillis / 1000L;
		if (
			Double.compare(gained, this.cachedGained) == 0
				&& elapsedSecond == this.cachedElapsedSecond
				&& session.label().equals(this.cachedSessionLabel)
		) return;

		this.cachedGained = (int)gained;
		this.cachedElapsedSecond = elapsedSecond;
		this.cachedSessionLabel = session.label();
		this.cachedLabel = session.label();
		double xpPerHour = elapsedMillis > 0L ? gained * 3_600_000.0D / elapsedMillis : 0.0D;
		this.cachedSessionTime = String.format(Locale.US, "%02d:%02d:%02d", elapsedSecond / 3600L, (elapsedSecond % 3600L) / 60L, elapsedSecond % 60L);
		this.cachedGainedText = String.format(Locale.US, "%,.0f", gained);
		this.cachedPerHourText = String.format(Locale.US, "%,.0f", xpPerHour);
	}
}

