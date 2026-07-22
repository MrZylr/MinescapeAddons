package com.zylr.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerScoreEntry;
import net.minecraft.world.scores.PlayerTeam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ScoreboardWidget extends HudWidget {
	private static final long CACHE_TTL_MS = 250L;
	private int computedBaseWidth = 80;
	private int computedBaseHeight = 20;
	private long lastCacheUpdateMillis;
	private Objective cachedObjective;
	private boolean cachedBrandingRemoval;
	private float cachedTextScale = Float.NaN;
	private String cachedTitle = "";
	private List<Component> cachedLines = List.of();
	private int cachedRenderWidth = 80;
	private int cachedRenderHeight = 20;

	ScoreboardWidget(double defaultX, double defaultY, double defaultScale) {
		super("scoreboardWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return this.computedBaseWidth; }

	@Override
	protected int baseHeight() { return this.computedBaseHeight; }

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isCustomScoreboardEnabled();
	}

	@Override
	protected boolean anchorRightWhenOnRightSide() {
		return true;
	}

	@Override
	protected int activeLeftBound(int screenWidth, int screenHeight) {
		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		return super.activeLeftBound(screenWidth, screenHeight) + (this.isOnRightSide() ? inset * 2 : 0);
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
		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		return super.activeRightBound(screenWidth, screenHeight) - (this.isOnRightSide() ? 0 : inset * 2);
	}

	@Override
	protected int clampExtraRightBounds(int screenWidth, int screenHeight) {
		return this.isOnRightSide() ? 0 : -DialogFrameRenderer.visibleFrameInset(this.scale()) * 2;
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
		if (!HudManager.getInstance().isCustomScoreboardEnabled()) return;
		if (minecraft.player == null || minecraft.level == null) return;
		net.minecraft.world.scores.Scoreboard scoreboard = minecraft.level.getScoreboard();
		Objective objective = scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR);
		if (objective == null) return;

		float textScale = (float) this.scale();
		boolean removeBranding = HudManager.getInstance().isScoreboardBrandingRemovalEnabled();
		this.updateCache(minecraft, scoreboard, objective, textScale, removeBranding);
		if (this.cachedTitle.isEmpty() && this.cachedLines.isEmpty()) return;

		int padding = DialogFrameRenderer.innerPadding(this.scale());
		int rowHeight = HudManager.scaledTextHeight(minecraft, textScale) + Math.max(1, (int) Math.round(1 * this.scale()));
		Font scoreFont = Minecraft.getInstance().font;
		int leftInset = DialogFrameRenderer.horizontalInset(this.scale());
		int topInset = DialogFrameRenderer.topInset(this.scale());
		int headerHeight = HudManager.scaledTextHeight(minecraft, textScale) + padding * 2;

		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		int x = this.pixelX(minecraft.getWindow().getGuiScaledWidth());
		int y = this.pixelY(minecraft.getWindow().getGuiScaledHeight());
		int renderW = this.pixelWidth();
		int renderH = this.pixelHeight();
		x += this.isOnRightSide() ? inset : -inset;
		y -= inset;
		DialogFrameRenderer.draw(graphics, x, y, renderW, renderH, headerHeight, this.scale());

		int titleWidth = HudManager.scaledTextWidth(minecraft, this.cachedTitle, textScale);
		int innerWidth = renderW - leftInset * 2;
		int titleX = x + leftInset + Math.max(0, (innerWidth - titleWidth) / 2);
		int textY = y + topInset + Math.max(0, (headerHeight - HudManager.scaledTextHeight(minecraft, textScale)) / 2);
		HudManager.drawScaledText(graphics, minecraft, this.cachedTitle, titleX, textY, 0xFFFFFF00, textScale);
		textY = y + topInset + headerHeight + padding;
		for (Component comp : this.cachedLines) {
			if (Math.abs(textScale - 1.0F) < 0.001F) {
				graphics.text(scoreFont, comp, x + leftInset + padding, textY, 0xFFE0E0E0, false);
			} else {
				graphics.pose().pushMatrix();
				graphics.pose().translate(x + leftInset + padding, textY);
				graphics.pose().scale(textScale, textScale);
				graphics.text(scoreFont, comp, 0, 0, 0xFFE0E0E0, false);
				graphics.pose().popMatrix();
			}
			textY += rowHeight;
		}
	}

	private void updateCache(
		Minecraft minecraft,
		net.minecraft.world.scores.Scoreboard scoreboard,
		Objective objective,
		float textScale,
		boolean removeBranding
	) {
		long now = System.currentTimeMillis();
		if (
			objective == this.cachedObjective
				&& removeBranding == this.cachedBrandingRemoval
				&& Float.compare(textScale, this.cachedTextScale) == 0
				&& now - this.lastCacheUpdateMillis < CACHE_TTL_MS
		) return;

		this.lastCacheUpdateMillis = now;
		this.cachedObjective = objective;
		this.cachedBrandingRemoval = removeBranding;
		this.cachedTextScale = textScale;

		ArrayList<PlayerScoreEntry> entries = new ArrayList<>(scoreboard.listPlayerScores(objective));
		entries.removeIf(PlayerScoreEntry::isHidden);
		entries.sort(Comparator.comparingInt(PlayerScoreEntry::value).reversed().thenComparing(PlayerScoreEntry::owner, String.CASE_INSENSITIVE_ORDER));
		if (entries.size() > 15) entries = new ArrayList<>(entries.subList(0, 15));

		Font scoreFont = HudManager.resolveHudTextFont(minecraft, textScale);
		int padding = DialogFrameRenderer.innerPadding(this.scale());
		int rowHeight = HudManager.scaledTextHeight(minecraft, textScale) + Math.max(1, (int) Math.round(1 * this.scale()));
		int leftInset = DialogFrameRenderer.horizontalInset(this.scale());
		int rightInset = DialogFrameRenderer.horizontalInset(this.scale());
		int topInset = DialogFrameRenderer.topInset(this.scale());
		int bottomInset = DialogFrameRenderer.bottomInset(this.scale());

		List<Component> lineComps = new ArrayList<>();
		for (PlayerScoreEntry entry : entries) {
			Component comp = entry.display() != null ? entry.display() : null;
			if (comp == null) {
				PlayerTeam team = scoreboard.getPlayersTeam(entry.owner());
				comp = team != null
					? PlayerTeam.formatNameForTeam(team, Component.literal(entry.owner()))
					: Component.literal(entry.owner());
			}
			if (HudManager.shouldRemoveScoreboardLine(comp)) {
				continue;
			}
			if (removeBranding && HudManager.shouldRemoveScoreboardBranding(comp)) {
				continue;
			}
			lineComps.add(comp);
		}

		String title = objective.getDisplayName().getString();
		int maxTextW = Math.round(scoreFont.width(title) * textScale);
		for (Component comp : lineComps) {
			int w = Math.round(scoreFont.width(comp) * textScale);
			if (w > maxTextW) maxTextW = w;
		}

		int headerHeight = HudManager.scaledTextHeight(minecraft, textScale) + padding * 2;
		int bodyHeight = padding * 2 + rowHeight * lineComps.size();
		int renderW = maxTextW + padding * 2 + leftInset + rightInset;
		int renderH = topInset + headerHeight + bodyHeight + bottomInset;
		boolean sizeChanged = renderW != this.cachedRenderWidth
			|| renderH != this.cachedRenderHeight
			|| this.computedBaseWidth != Math.max(10, (int) Math.ceil(renderW / this.scale()))
			|| this.computedBaseHeight != Math.max(10, (int) Math.ceil(renderH / this.scale()));
		this.cachedTitle = title;
		this.cachedLines = List.copyOf(lineComps);
		this.cachedRenderWidth = renderW;
		this.cachedRenderHeight = renderH;
		this.computedBaseWidth = Math.max(10, (int) Math.ceil(renderW / this.scale()));
		this.computedBaseHeight = Math.max(10, (int) Math.ceil(renderH / this.scale()));
		if (sizeChanged) this.invalidateBoundsCache();
	}
}

