package com.zylr.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import static com.zylr.client.hud.HudManager.TAB_ROW_BOTTOM;
import static com.zylr.client.hud.HudManager.TAB_ROW_TOP;
import static com.zylr.client.hud.HudManager.TAB_STONE;
import static com.zylr.client.hud.HudManager.TAB_STONE_SELECTED;
import static com.zylr.client.hud.HudManager.blitTexture;

public final class TabStripWidget extends HudWidget {
	private static final int ICON_WIDTH = 33;
	private static final int ICON_HEIGHT = 36;
	private static final int BUTTON_GAP = 1;
	private static final int ROW_GAP = 1;
	private static final int PADDING = 4;
	private HudTab lastSelectedTab = null;

	TabStripWidget(double defaultX, double defaultY, double defaultScale) {
		super("tabStrip", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() {
		int n = Math.max(TAB_ROW_TOP.length, TAB_ROW_BOTTOM.length);
		return ICON_WIDTH * n + BUTTON_GAP * (n - 1) + PADDING * 2;
	}

	@Override
	protected int baseHeight() { return ICON_HEIGHT * 2 + ROW_GAP + PADDING * 2; }

	private int rowWidth(int bw, int gap, TabSlot[] row) { return bw * row.length + gap * (row.length - 1); }
	private int attachedTopY(int panelY, int bh) { return panelY - bh; }
	private int attachedBottomY(int panelY, int ph) { return panelY + ph; }

	private HudTab tabAtInRow(double mx, double my, int bx, int by, int bw, int bh, int gap, TabSlot[] row) {
		int cx = bx;
		for (TabSlot slot : row) {
			if (mx >= cx && mx <= cx + bw && my >= by && my <= by + bh) return slot.linkedTab;
			cx += bw + gap;
		}
		return null;
	}

	public HudTab tabAt(double mouseX, double mouseY, int screenWidth, int screenHeight) {
		ContentPanelWidget panel = HudManager.getInstance().contentPanelWidget();
		int panelX = panel.pixelX(screenWidth);
		int panelY = panel.pixelY(screenHeight);
		int panelWidth = panel.pixelWidth();
		int panelHeight = panel.pixelHeight();
		double scale = panel.scale();
		int bw = (int) Math.round(ICON_WIDTH * scale);
		int bh = (int) Math.round(ICON_HEIGHT * scale);
		int gap = (int) Math.round(BUTTON_GAP * scale);
		int topY = attachedTopY(panelY, bh);
		int bottomY = attachedBottomY(panelY, panelHeight);
		int topX = panelX + (panelWidth - rowWidth(bw, gap, TAB_ROW_TOP)) / 2;
		int bottomX = panelX + (panelWidth - rowWidth(bw, gap, TAB_ROW_BOTTOM)) / 2;
		HudTab h = tabAtInRow(mouseX, mouseY, topX, topY, bw, bh, gap, TAB_ROW_TOP);
		if (h != null) return h;
		return tabAtInRow(mouseX, mouseY, bottomX, bottomY, bw, bh, gap, TAB_ROW_BOTTOM);
	}

	private TabSlot hoveredSlot(double mx, double my, int wx, int wy, int ww, int bw, int bh, int gap, int rowGap) {
		int topY = attachedTopY(wy, bh);
		int bottomY = attachedBottomY(wy, rowGap);
		int topX = wx + (ww - rowWidth(bw, gap, TAB_ROW_TOP)) / 2;
		int bottomX = wx + (ww - rowWidth(bw, gap, TAB_ROW_BOTTOM)) / 2;
		int cx = topX;
		for (TabSlot slot : TAB_ROW_TOP) {
			if (mx >= cx && mx <= cx + bw && my >= topY && my <= topY + bh) return slot;
			cx += bw + gap;
		}
		cx = bottomX;
		for (TabSlot slot : TAB_ROW_BOTTOM) {
			if (mx >= cx && mx <= cx + bw && my >= bottomY && my <= bottomY + bh) return slot;
			cx += bw + gap;
		}
		return null;
	}

	private void drawRow(GuiGraphicsExtractor graphics, int startX, int by, int bw, int bh, int gap, TabSlot[] row, TabSlot hoveredSlot) {
		int bx = startX;
		for (TabSlot slot : row) {
			boolean clickable = slot.linkedTab != null;
			boolean active = clickable && slot.linkedTab == HudManager.getInstance().getSelectedTab();
			boolean hovered = slot == hoveredSlot;
			blitTexture(graphics, active ? TAB_STONE_SELECTED : TAB_STONE, bx, by, bw, bh, 33, 36);
			blitTexture(graphics, slot.icon, bx, by, bw, bh, ICON_WIDTH, ICON_HEIGHT);
			if (hovered && clickable && !active) {
				graphics.fill(bx, by, bx + bw, by + 1, 0xFFE7D98A);
				graphics.fill(bx, by + bh - 1, bx + bw, by + bh, 0xFFE7D98A);
				graphics.fill(bx, by, bx + 1, by + bh, 0xFFE7D98A);
				graphics.fill(bx + bw - 1, by, bx + bw, by + bh, 0xFFE7D98A);
			}
			bx += bw + gap;
		}
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		int sw = minecraft.getWindow().getGuiScaledWidth();
		int sh = minecraft.getWindow().getGuiScaledHeight();
		ContentPanelWidget panel = HudManager.getInstance().contentPanelWidget();
		int px = panel.pixelX(sw);
		int py = panel.pixelY(sh);
		int pw = panel.pixelWidth();
		int ph = panel.pixelHeight();
		double scale = panel.scale();
		this.lastSelectedTab = HudManager.getInstance().getSelectedTab();
		int gap = (int) Math.round(BUTTON_GAP * scale);
		int bw = (int) Math.round(ICON_WIDTH * scale);
		int bh = (int) Math.round(ICON_HEIGHT * scale);
		int topY = attachedTopY(py, bh);
		int bottomY = attachedBottomY(py, ph);
		int topX = px + (pw - rowWidth(bw, gap, TAB_ROW_TOP)) / 2;
		int bottomX = px + (pw - rowWidth(bw, gap, TAB_ROW_BOTTOM)) / 2;
		TabSlot hovered = hoveredSlot(mouseX, mouseY, px, py, pw, bw, bh, gap, ph);
		drawRow(graphics, topX, topY, bw, bh, gap, TAB_ROW_TOP, hovered);
		drawRow(graphics, bottomX, bottomY, bw, bh, gap, TAB_ROW_BOTTOM, hovered);
	}
}

