package com.zylr.client.hud;

import com.zylr.client.PerfDebug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;

public abstract class HudWidget {
	public enum HorizontalScreenSide {
		LEFT,
		RIGHT
	}

	public enum VerticalScreenSide {
		TOP,
		BOTTOM
	}

	private final String id;
	private final double defaultX;
	private final double defaultY;
	private final double defaultScale;
	private double x;
	private double y;
	private double scale;
	private int cachedPixelX = -1;
	private int cachedPixelY = -1;
	private int cachedPixelWidth = -1;
	private int cachedPixelHeight = -1;
	private int lastScreenWidth = -1;
	private int lastScreenHeight = -1;
	private boolean cacheValid = false;
	private boolean currentRenderEditMode = false;

	protected HudWidget(String id, double defaultX, double defaultY, double defaultScale) {
		this.id = id;
		this.defaultX = defaultX;
		this.defaultY = defaultY;
		this.defaultScale = defaultScale;
		this.x = defaultX;
		this.y = defaultY;
		this.scale = defaultScale;
	}

	public final String id() { return this.id; }

	public final void reset() {
		this.x = this.defaultX;
		this.y = this.defaultY;
		this.scale = this.defaultScale;
		this.cacheValid = false;
	}

	public final void apply(WidgetState state) {
		if (state.x != null) this.x = Mth.clamp(state.x, 0.0D, 1.0D);
		if (state.y != null) this.y = Mth.clamp(state.y, 0.0D, 1.0D);
		if (state.scale != null) this.scale = Mth.clamp(state.scale, HudManager.MIN_SCALE, HudManager.MAX_SCALE);
		this.cacheValid = false;
		if (this.lastScreenWidth > 0 && this.lastScreenHeight > 0) this.clampToScreen(this.lastScreenWidth, this.lastScreenHeight);
	}

	public final WidgetState snapshot() {
		WidgetState state = new WidgetState();
		state.x = this.x;
		state.y = this.y;
		state.scale = this.scale;
		return state;
	}

	public final void moveBy(double deltaX, double deltaY, int screenWidth, int screenHeight) {
		if (screenWidth <= 0 || screenHeight <= 0) return;
		this.x = Mth.clamp(this.x + deltaX / screenWidth, 0.0D, 1.0D);
		this.y = Mth.clamp(this.y + deltaY / screenHeight, 0.0D, 1.0D);
		this.clampToScreen(screenWidth, screenHeight);
	}

	public final void resizeBy(double deltaScale, int screenWidth, int screenHeight) {
		this.scale = Mth.clamp(this.scale + deltaScale, HudManager.MIN_SCALE, HudManager.MAX_SCALE);
		this.clampToScreen(screenWidth, screenHeight);
	}

	public final void resizeBy(double deltaScale) {
		this.scale = Mth.clamp(this.scale + deltaScale, HudManager.MIN_SCALE, HudManager.MAX_SCALE);
		this.cacheValid = false;
		if (this.lastScreenWidth > 0 && this.lastScreenHeight > 0) this.clampToScreen(this.lastScreenWidth, this.lastScreenHeight);
	}

	public final double scale() { return this.scale; }
	public final HorizontalScreenSide horizontalScreenSide() { return this.x < 0.5D ? HorizontalScreenSide.LEFT : HorizontalScreenSide.RIGHT; }
	public final VerticalScreenSide verticalScreenSide() { return this.y < 0.5D ? VerticalScreenSide.TOP : VerticalScreenSide.BOTTOM; }
	public final boolean isOnLeftSide() { return this.horizontalScreenSide() == HorizontalScreenSide.LEFT; }
	public final boolean isOnRightSide() { return this.horizontalScreenSide() == HorizontalScreenSide.RIGHT; }
	public final boolean isOnTopSide() { return this.verticalScreenSide() == VerticalScreenSide.TOP; }
	public final boolean isOnBottomSide() { return this.verticalScreenSide() == VerticalScreenSide.BOTTOM; }

	protected int extraTopBounds(int screenWidth, int screenHeight) { return 0; }
	protected int extraLeftBounds(int screenWidth, int screenHeight) { return 0; }
	protected int extraRightBounds(int screenWidth, int screenHeight) { return 0; }
	protected int extraBottomBounds(int screenWidth, int screenHeight) { return 0; }
	protected int clampExtraTopBounds(int screenWidth, int screenHeight) { return this.extraTopBounds(screenWidth, screenHeight); }
	protected int clampExtraLeftBounds(int screenWidth, int screenHeight) { return this.extraLeftBounds(screenWidth, screenHeight); }
	protected int clampExtraRightBounds(int screenWidth, int screenHeight) { return this.extraRightBounds(screenWidth, screenHeight); }
	protected int clampExtraBottomBounds(int screenWidth, int screenHeight) { return this.extraBottomBounds(screenWidth, screenHeight); }
	protected boolean anchorRightWhenOnRightSide() { return false; }
	protected boolean anchorBottomWhenOnBottomSide() { return false; }

	private void clampToScreen(int screenWidth, int screenHeight) {
		if (screenWidth <= 0 || screenHeight <= 0) return;
		double fitScaleX = (double) screenWidth / Math.max(1, this.baseWidth());
		double fitScaleY = (double) screenHeight / Math.max(1, this.baseHeight());
		double fitMaxScale = Math.min(HudManager.MAX_SCALE, Math.min(fitScaleX, fitScaleY));
		double effectiveMinScale = Math.min(HudManager.MIN_SCALE, fitMaxScale);
		double effectiveMaxScale = Math.max(effectiveMinScale, fitMaxScale);
		this.scale = Mth.clamp(this.scale, effectiveMinScale, effectiveMaxScale);
		int pixelWidth = (int) Math.round(this.baseWidth() * this.scale);
		int pixelHeight = (int) Math.round(this.baseHeight() * this.scale);
		int extraLeft = this.clampExtraLeftBounds(screenWidth, screenHeight);
		int extraRight = this.clampExtraRightBounds(screenWidth, screenHeight);
		int extraTop = this.clampExtraTopBounds(screenWidth, screenHeight);
		int extraBottom = this.clampExtraBottomBounds(screenWidth, screenHeight);
		boolean rightAnchored = this.anchorRightWhenOnRightSide() && this.isOnRightSide();
		boolean bottomAnchored = this.anchorBottomWhenOnBottomSide() && this.isOnBottomSide();
		double minX = rightAnchored
			? (double) (pixelWidth + extraLeft) / screenWidth
			: (double) extraLeft / screenWidth;
		double maxX = rightAnchored
			? Math.max(minX, (double) (screenWidth - extraRight) / screenWidth)
			: Math.max(minX, (double) (screenWidth - pixelWidth - extraRight) / screenWidth);
		double minY = bottomAnchored
			? (double) (pixelHeight + extraTop) / screenHeight
			: (double) extraTop / screenHeight;
		double maxY = bottomAnchored
			? Math.max(minY, (double) (screenHeight - extraBottom) / screenHeight)
			: Math.max(minY, (double) (screenHeight - pixelHeight - extraBottom) / screenHeight);
		this.x = Mth.clamp(this.x, minX, maxX);
		this.y = Mth.clamp(this.y, minY, maxY);
		this.cacheValid = false;
		this.lastScreenWidth = screenWidth;
		this.lastScreenHeight = screenHeight;
	}

	public final int pixelX(int screenWidth) {
		if (screenWidth > 0 && (this.lastScreenWidth != screenWidth || !this.cacheValid)) {
			int anchorX = (int) Math.round(this.x * screenWidth);
			this.cachedPixelX = this.anchorRightWhenOnRightSide() && this.isOnRightSide() ? anchorX - this.pixelWidth() : anchorX;
			this.lastScreenWidth = screenWidth;
		}
		return this.cachedPixelX;
	}

	public final int pixelY(int screenHeight) {
		if (screenHeight > 0 && (this.lastScreenHeight != screenHeight || !this.cacheValid)) {
			int anchorY = (int) Math.round(this.y * screenHeight);
			this.cachedPixelY = this.anchorBottomWhenOnBottomSide() && this.isOnBottomSide() ? anchorY - this.pixelHeight() : anchorY;
			this.lastScreenHeight = screenHeight;
		}
		return this.cachedPixelY;
	}

	public final int pixelWidth() {
		if (!this.cacheValid) this.cachedPixelWidth = (int) Math.round(this.baseWidth() * this.scale);
		return this.cachedPixelWidth;
	}

	public final int pixelHeight() {
		if (!this.cacheValid) this.cachedPixelHeight = (int) Math.round(this.baseHeight() * this.scale);
		return this.cachedPixelHeight;
	}

	protected final boolean isRenderingInEditMode() {
		return this.currentRenderEditMode;
	}

	protected final void invalidateBoundsCache() {
		this.cacheValid = false;
	}

	public final boolean contains(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		int left = this.leftBound(screenWidth, screenHeight);
		int top = this.topBound(screenWidth, screenHeight);
		int right = this.rightBound(screenWidth, screenHeight);
		int bottom = this.bottomBound(screenWidth, screenHeight);
		return mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom;
	}

	public final boolean isOnScaleHandle(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		int right = this.rightBound(screenWidth, screenHeight);
		int bottom = this.bottomBound(screenWidth, screenHeight);
		int handleSize = Math.max(8, (int) Math.round(8 * this.scale));
		return mouseX >= right - handleSize && mouseX <= right && mouseY >= bottom - handleSize && mouseY <= bottom;
	}

	public final void render(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta, boolean editMode, boolean selected) {
		if (!this.shouldRenderWidget(minecraft, editMode)) return;
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		this.clampToScreen(screenWidth, screenHeight);
		int x = this.pixelX(screenWidth);
		int y = this.pixelY(screenHeight);
		int width = this.pixelWidth();
		int height = this.pixelHeight();
		int top = y - this.extraTopBounds(screenWidth, screenHeight);
		int bottom = y + height + this.extraBottomBounds(screenWidth, screenHeight);
		if (!editMode && (x + width < 0 || x > screenWidth || bottom < 0 || top > screenHeight)) return;
		long start = PerfDebug.start();
		this.currentRenderEditMode = editMode;
		try {
			this.renderWidget(graphics, minecraft, mouseX, mouseY, delta);
		} finally {
			this.currentRenderEditMode = false;
		}
		PerfDebug.record("hud." + this.id, start);
		if (editMode || selected) this.renderEditChrome(graphics, minecraft, mouseX, mouseY, selected);
	}

	protected abstract int baseWidth();
	protected abstract int baseHeight();
	protected abstract void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta);
	protected boolean shouldRenderWidget(Minecraft minecraft, boolean editMode) { return true; }
	protected boolean shouldHighlightInEditModeWarning() { return false; }
	protected int activeLeftBound(int screenWidth, int screenHeight) { return this.pixelX(screenWidth) - this.extraLeftBounds(screenWidth, screenHeight); }
	protected int activeTopBound(int screenWidth, int screenHeight) { return this.pixelY(screenHeight) - this.extraTopBounds(screenWidth, screenHeight); }
	protected int activeRightBound(int screenWidth, int screenHeight) { return this.pixelX(screenWidth) + this.pixelWidth() + this.extraRightBounds(screenWidth, screenHeight); }
	protected int activeBottomBound(int screenWidth, int screenHeight) { return this.pixelY(screenHeight) + this.pixelHeight() + this.extraBottomBounds(screenWidth, screenHeight); }

	public final int leftBound(int screenWidth, int screenHeight) {
		return this.activeLeftBound(screenWidth, screenHeight);
	}

	public final int topBound(int screenWidth, int screenHeight) {
		return this.activeTopBound(screenWidth, screenHeight);
	}

	public final int rightBound(int screenWidth, int screenHeight) {
		return this.activeRightBound(screenWidth, screenHeight);
	}

	public final int bottomBound(int screenWidth, int screenHeight) {
		return this.activeBottomBound(screenWidth, screenHeight);
	}

	public final boolean overlaps(HudWidget other, int screenWidth, int screenHeight) {
		return this.leftBound(screenWidth, screenHeight) < other.rightBound(screenWidth, screenHeight)
			&& this.rightBound(screenWidth, screenHeight) > other.leftBound(screenWidth, screenHeight)
			&& this.topBound(screenWidth, screenHeight) < other.bottomBound(screenWidth, screenHeight)
			&& this.bottomBound(screenWidth, screenHeight) > other.topBound(screenWidth, screenHeight);
	}

	public final int overlapArea(HudWidget other, int screenWidth, int screenHeight) {
		int left = Math.max(this.leftBound(screenWidth, screenHeight), other.leftBound(screenWidth, screenHeight));
		int right = Math.min(this.rightBound(screenWidth, screenHeight), other.rightBound(screenWidth, screenHeight));
		int top = Math.max(this.topBound(screenWidth, screenHeight), other.topBound(screenWidth, screenHeight));
		int bottom = Math.min(this.bottomBound(screenWidth, screenHeight), other.bottomBound(screenWidth, screenHeight));
		if (left >= right || top >= bottom) return 0;
		return (right - left) * (bottom - top);
	}

	protected void renderEditChrome(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, boolean selected) {
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int x = this.leftBound(screenWidth, screenHeight);
		int y = this.topBound(screenWidth, screenHeight);
		int right = this.rightBound(screenWidth, screenHeight);
		int bottom = this.bottomBound(screenWidth, screenHeight);
		boolean warning = this.shouldHighlightInEditModeWarning();
		int border = warning ? (selected ? 0xFFFF8A80 : 0xFFFF5252) : (selected ? 0xFFFFD54A : 0xFF6BD4FF);
		int fill = warning ? (selected ? HudManager.rgba(64, 255, 82, 82) : HudManager.rgba(44, 255, 82, 82)) : (selected ? HudManager.rgba(48, 255, 213, 74) : HudManager.rgba(32, 107, 212, 255));
		graphics.fill(x, y, right, y + 1, border);
		graphics.fill(x, bottom - 1, right, bottom, border);
		graphics.fill(x, y, x + 1, bottom, border);
		graphics.fill(right - 1, y, right, bottom, border);
		graphics.fill(x + 1, y + 1, right - 1, bottom - 1, fill);
		int handleSize = Math.max(8, (int) Math.round(8 * this.scale));
		int handleColor = this.isOnScaleHandle(mouseX, mouseY, screenWidth, screenHeight) ? 0xFFFFFFFF : 0xFFE0E0E0;
		graphics.fill(right - handleSize, bottom - handleSize, right, bottom, handleColor);
	}
}

