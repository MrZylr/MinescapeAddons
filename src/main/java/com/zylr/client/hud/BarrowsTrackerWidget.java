package com.zylr.client.hud;

import com.zylr.MinescapeAddon;
import com.zylr.client.screen.HudInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public final class BarrowsTrackerWidget extends HudWidget {
	private static final int BASE_WIDTH = 112;
	private static final int BASE_HEIGHT = 122;
	private static final int MAX_POTENTIAL_POINTS = 1012;
	private static final Identifier REFRESH_ICON = texture("other/refresh_icon.png");
	private static final Map<String, Integer> BROTHER_POINTS = new LinkedHashMap<>();
	private static final Map<String, Integer> CRYPT_MOB_POINTS = new LinkedHashMap<>();

	static {
		BROTHER_POINTS.put("Ahrim the Blighted", 100);
		BROTHER_POINTS.put("Dharok the Wretched", 117);
		BROTHER_POINTS.put("Guthan the Infested", 117);
		BROTHER_POINTS.put("Karil the Tainted", 100);
		BROTHER_POINTS.put("Torag the Corrupted", 117);
		BROTHER_POINTS.put("Verac the Defiled", 117);

		CRYPT_MOB_POINTS.put("Bloodworm", 52);
		CRYPT_MOB_POINTS.put("Crypt Rat", 43);
		CRYPT_MOB_POINTS.put("Giant Crypt Rat", 76);
		CRYPT_MOB_POINTS.put("Crypt Spider", 56);
		CRYPT_MOB_POINTS.put("Giant Crypt Spider", 79);
		CRYPT_MOB_POINTS.put("Skeleton", 77);
	}

	private final Map<String, Boolean> killedBrothers = new LinkedHashMap<>();
	private int cryptMobPoints;

	BarrowsTrackerWidget(double defaultX, double defaultY, double defaultScale) {
		super("barrowsTrackerWidget", defaultX, defaultY, defaultScale);
		this.resetTracker();
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

	public void recordKill(String entityName) {
		String normalized = normalizeName(entityName);
		if (normalized.isEmpty()) return;
		String brother = matchingKey(BROTHER_POINTS, normalized);
		if (brother != null) {
			this.killedBrothers.put(brother, true);
			return;
		}
		String cryptMob = matchingKey(CRYPT_MOB_POINTS, normalized);
		if (cryptMob != null) {
			this.cryptMobPoints += CRYPT_MOB_POINTS.get(cryptMob);
		}
	}

	public void resetTracker() {
		this.killedBrothers.clear();
		for (String brother : BROTHER_POINTS.keySet()) {
			this.killedBrothers.put(brother, false);
		}
		this.cryptMobPoints = 0;
	}

	public boolean clickResetButton(Minecraft minecraft, double mouseX, double mouseY, int screenWidth, int screenHeight) {
		if (!this.shouldShowResetButton(minecraft)) return false;
		int[] bounds = this.resetButtonBounds(screenWidth, screenHeight);
		if (mouseX < bounds[0] || mouseX >= bounds[0] + bounds[2] || mouseY < bounds[1] || mouseY >= bounds[1] + bounds[3]) {
			return false;
		}
		this.resetTracker();
		return true;
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		if (!this.isPlayerInBarrowsArea(minecraft)) return;

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
		int contentX = x + frameInset + padding;
		int contentWidth = width - frameInset * 2 - padding * 2;

		DialogFrameRenderer.draw(graphics, x, y, width, height, titleHeight, this.scale());

		String title = "Barrows";
		int titleX = x + frameInset + (width - frameInset * 2 - HudManager.scaledTextWidth(minecraft, title, textScale)) / 2;
		HudManager.drawScaledText(graphics, minecraft, title, titleX, y + DialogFrameRenderer.topInset(this.scale()) + padding, 0xFFFFFF00, textScale);
		if (this.shouldShowResetButton(minecraft)) {
			int[] bounds = this.resetButtonBounds(minecraft.getWindow().getGuiScaledWidth(), minecraft.getWindow().getGuiScaledHeight());
			HudManager.blitTexture(graphics, REFRESH_ICON, bounds[0], bounds[1], bounds[2], bounds[3], 16, 16);
		}

		int rowY = y + DialogFrameRenderer.topInset(this.scale()) + titleHeight + padding;
		for (String brother : BROTHER_POINTS.keySet()) {
			boolean killed = this.killedBrothers.getOrDefault(brother, false);
			String shortName = brother.substring(0, brother.indexOf(' '));
			HudManager.drawScaledText(graphics, minecraft, shortName, contentX, rowY, 0xFFFFFFFF, textScale);
			HudManager.drawScaledText(graphics, minecraft, killed ? "✓" : "X", contentX + contentWidth - Math.round(8 * textScale), rowY, killed ? 0xFF4CFF4C : 0xFFFF5555, textScale);
			rowY += lineHeight;
		}

		int dividerY = Math.min(y + height - DialogFrameRenderer.bottomInset(this.scale()) - lineHeight - padding - 1, rowY + Math.max(1, padding / 2));
		graphics.fill(contentX, dividerY, contentX + contentWidth, dividerY + 1, 0x66BFA882);
		String potential = String.format(Locale.US, "Potential: %.1f%%", this.potentialPercent());
		HudManager.drawScaledText(graphics, minecraft, potential, contentX, y + height - DialogFrameRenderer.bottomInset(this.scale()) - lineHeight - Math.max(2, padding / 2), 0xFFFF981F, textScale);
	}

	private double potentialPercent() {
		return Mth.clamp((double) this.totalPoints() * 100.0D / MAX_POTENTIAL_POINTS, 0.0D, 100.0D);
	}

	private int totalPoints() {
		int total = this.cryptMobPoints;
		for (Map.Entry<String, Boolean> entry : this.killedBrothers.entrySet()) {
			if (entry.getValue()) total += BROTHER_POINTS.getOrDefault(entry.getKey(), 0);
		}
		return total;
	}

	private boolean isPlayerInBarrowsArea(Minecraft minecraft) {
		if (minecraft == null || minecraft.player == null) return false;
		double x = minecraft.player.getX();
		double z = minecraft.player.getZ();
		return x >= 1580.0D && x <= 1710.0D && z >= -80.0D && z <= 50.0D;
	}

	private static String matchingKey(Map<String, Integer> values, String normalizedName) {
		for (String key : values.keySet()) {
			if (normalizedName.equals(normalizeName(key)) || normalizedName.contains(normalizeName(key))) {
				return key;
			}
		}
		return null;
	}

	private static String normalizeName(String name) {
		if (name == null) return "";
		return name.replaceAll("(?i)\\u00A7[0-9A-FK-OR]", "").trim().toLowerCase(Locale.ROOT);
	}

	private boolean shouldShowResetButton(Minecraft minecraft) {
		return minecraft != null && minecraft.screen instanceof HudInventoryScreen;
	}

	private int[] resetButtonBounds(int screenWidth, int screenHeight) {
		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		int frameInset = DialogFrameRenderer.horizontalInset(this.scale());
		int x = this.pixelX(screenWidth) - inset;
		int y = this.pixelY(screenHeight) - inset;
		int width = this.pixelWidth();
		int padding = DialogFrameRenderer.innerPadding(this.scale());
		int buttonSize = Math.max(10, Math.round(12.0F * (float) this.scale()));
		int buttonX = x + width - frameInset - padding - buttonSize;
		int buttonY = y + DialogFrameRenderer.topInset(this.scale()) + Math.max(1, (padding - 1) / 2);
		return new int[] {buttonX, buttonY+5, buttonSize, buttonSize};
	}

	private static Identifier texture(String path) {
		return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/" + path);
	}
}
