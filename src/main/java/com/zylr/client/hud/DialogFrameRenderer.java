package com.zylr.client.hud;

import com.zylr.MinescapeAddon;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
final class DialogFrameRenderer {
	private static final Identifier BACKGROUND = texture("dialog/background.png");
	private static final Identifier BACKGROUND_BRIGHTER = texture("dialog/background_brighter.png");
	private static final Identifier RIVET_TOP_LEFT = texture("dialog/iron_rivets_corner_top_left.png");
	private static final Identifier RIVET_TOP_RIGHT = texture("dialog/iron_rivets_corner_top_right.png");
	private static final Identifier RIVET_BOTTOM_LEFT = texture("dialog/iron_rivets_corner_bottom_left.png");
	private static final Identifier RIVET_BOTTOM_RIGHT = texture("dialog/iron_rivets_corner_bottom_right.png");
	private static final Identifier RIVET_TOP = texture("dialog/iron_rivets_edge_top.png");
	private static final Identifier RIVET_BOTTOM = texture("dialog/iron_rivets_bottom.png");
	private static final Identifier RIVET_LEFT = texture("dialog/iron_rivets_vertical.png");
	private static final Identifier RIVET_RIGHT = texture("dialog/iron_rivets_edge_right.png");
	private static final int BACKGROUND_TILE_W = 88;
	private static final int BACKGROUND_TILE_H = 60;
	private static final int PANEL_OUTSET = 15;
	private static final int EDGE_TILE_W = 36;
	private static final int EDGE_TILE_H = 36;
	private static final int CORNER_TILE_W = 25;
	private static final int CORNER_TILE_H = 30;

	private DialogFrameRenderer() {
	}

	static int horizontalInset(double widgetScale) {
		return PANEL_OUTSET;
	}

	static int visibleFrameInset(double widgetScale) {
		return PANEL_OUTSET;
	}

	static int topInset(double widgetScale) {
		return PANEL_OUTSET;
	}

	static int bottomInset(double widgetScale) {
		return PANEL_OUTSET;
	}

	static int innerPadding(double widgetScale) {
		return Math.max(4, (int) Math.round(5 * widgetScale));
	}

	static void draw(GuiGraphicsExtractor graphics, int x, int y, int width, int height, int headerHeight, double widgetScale) {
		if (width <= 0 || height <= 0) return;

		int panelX = x + PANEL_OUTSET;
		int panelY = y + PANEL_OUTSET;
		int panelWidth = Math.max(1, width - PANEL_OUTSET * 2);
		int panelHeight = Math.max(1, height - PANEL_OUTSET * 2);
		int clampedHeaderHeight = Math.max(0, Math.min(headerHeight, panelHeight));
		int bodyHeight = panelHeight - clampedHeaderHeight;

		if (clampedHeaderHeight > 0) {
			tileFill(graphics, BACKGROUND, panelX, panelY, panelWidth, clampedHeaderHeight, BACKGROUND_TILE_W, BACKGROUND_TILE_H);
		}
		if (bodyHeight > 0) {
			tileFill(graphics, BACKGROUND_BRIGHTER, panelX, panelY + clampedHeaderHeight, panelWidth, bodyHeight, BACKGROUND_TILE_W, BACKGROUND_TILE_H);
		}

		tileFill(graphics, RIVET_TOP, panelX, y, panelWidth, EDGE_TILE_H, EDGE_TILE_W, EDGE_TILE_H);
		tileFill(graphics, RIVET_BOTTOM, panelX, panelY + panelHeight - EDGE_TILE_H + PANEL_OUTSET, panelWidth, EDGE_TILE_H, EDGE_TILE_W, EDGE_TILE_H);
		tileFill(graphics, RIVET_LEFT, x, panelY, EDGE_TILE_W, panelHeight, EDGE_TILE_W, EDGE_TILE_H);
		tileFill(graphics, RIVET_RIGHT, panelX + panelWidth - EDGE_TILE_W + PANEL_OUTSET, panelY, EDGE_TILE_W, panelHeight, EDGE_TILE_W, EDGE_TILE_H);

		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_TOP_LEFT, panelX, panelY, 0.0F, 0.0F, CORNER_TILE_W, CORNER_TILE_H, CORNER_TILE_W, CORNER_TILE_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_TOP_RIGHT, panelX + panelWidth - CORNER_TILE_W, panelY, 0.0F, 0.0F, CORNER_TILE_W, CORNER_TILE_H, CORNER_TILE_W, CORNER_TILE_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_BOTTOM_LEFT, panelX, panelY + panelHeight - CORNER_TILE_H, 0.0F, 0.0F, CORNER_TILE_W, CORNER_TILE_H, CORNER_TILE_W, CORNER_TILE_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_BOTTOM_RIGHT, panelX + panelWidth - CORNER_TILE_W, panelY + panelHeight - CORNER_TILE_H, 0.0F, 0.0F, CORNER_TILE_W, CORNER_TILE_H, CORNER_TILE_W, CORNER_TILE_H);
	}

	private static void tileFill(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height, int tileW, int tileH) {
		for (int drawY = 0; drawY < height; drawY += tileH) {
			for (int drawX = 0; drawX < width; drawX += tileW) {
				int pieceW = Math.min(tileW, width - drawX);
				int pieceH = Math.min(tileH, height - drawY);
				graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x + drawX, y + drawY, 0.0F, 0.0F, pieceW, pieceH, tileW, tileH, tileW, tileH);
			}
		}
	}

	private static Identifier texture(String path) {
		return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/" + path);
	}
}
