package com.zylr.client.hud;

import com.zylr.client.clue.ClueHelper;
import com.zylr.client.clue.ClueScrollClue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public final class ClueHelperWidget extends HudWidget {
	private static final int BASE_WIDTH = 340;
	private static final int BASE_HEIGHT = 78;
	private static final int TITLE = 0xFFFFFF00;
	private static final int TEXT = 0xFFFFFFFF;
	private static final int COORDS = 0xFFFF981F;

	ClueHelperWidget(double defaultX, double defaultY, double defaultScale) {
		super("clueHelperWidget", defaultX, defaultY, defaultScale);
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
		return editMode || ClueHelper.activeClue(minecraft) != null;
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		ClueScrollClue clue = ClueHelper.activeClue(minecraft);
		if (clue == null) {
			if (minecraft == null || minecraft.screen == null) return;
			clue = ClueScrollClue.CLUE_1;
		}

		int inset = DialogFrameRenderer.visibleFrameInset(this.scale());
		int frameInset = DialogFrameRenderer.horizontalInset(this.scale());
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int x = this.pixelX(screenWidth) - inset;
		int y = this.pixelY(screenHeight) - inset;
		int width = this.pixelWidth();
		int height = this.pixelHeight();
		int padding = DialogFrameRenderer.innerPadding(this.scale());
		float textScale = Math.max(0.65F, (float) this.scale());
		int lineHeight = HudManager.scaledTextHeight(minecraft, textScale);
		int titleHeight = lineHeight + padding * 2;
		int contentWidth = width - frameInset * 2 - padding * 2;
		int textStartY = y + DialogFrameRenderer.topInset(this.scale()) + titleHeight + Math.max(2, padding / 2);

		DialogFrameRenderer.draw(graphics, x, y, width, height, titleHeight, this.scale());

		String id = "Clue " + clue.displayId();
		drawCentered(graphics, minecraft, id, x + frameInset, y + DialogFrameRenderer.topInset(this.scale()) + padding, width - frameInset * 2, TITLE, textScale);
		drawCentered(graphics, minecraft, fitText(minecraft, clue.answer(), contentWidth, textScale), x + frameInset + padding, textStartY, width - frameInset * 2 - padding * 2, TEXT, textScale);
		drawCentered(graphics, minecraft, clue.coords(), x + frameInset + padding, textStartY + lineHeight, width - frameInset * 2 - padding * 2, COORDS, textScale);
	}

	private static void drawCentered(GuiGraphicsExtractor graphics, Minecraft minecraft, String text, int x, int y, int width, int color, float scale) {
		int textX = x + (width - HudManager.scaledTextWidth(minecraft, text, scale)) / 2;
		HudManager.drawScaledText(graphics, minecraft, text, textX, y, color, scale);
	}

	private static String fitText(Minecraft minecraft, String text, int maxWidth, float scale) {
		if (HudManager.scaledTextWidth(minecraft, text, scale) <= maxWidth) return text;
		String ellipsis = "...";
		int max = Math.max(0, text.length() - 1);
		while (max > 0) {
			String candidate = text.substring(0, max).trim() + ellipsis;
			if (HudManager.scaledTextWidth(minecraft, candidate, scale) <= maxWidth) return candidate;
			max--;
		}
		return ellipsis;
	}
}
