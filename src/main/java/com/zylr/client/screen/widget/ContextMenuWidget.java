package com.zylr.client.screen.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.List;

public final class ContextMenuWidget {
	public static final class MenuItem {
		private final Component label;
		private final Runnable action;

		private MenuItem(Component label, Runnable action) {
			this.label = label;
			this.action = action;
		}

		public static MenuItem of(Component label, Runnable action) {
			return new MenuItem(label, action);
		}
	}

	private static final int PADDING = 4;
	private static final int BORDER_COLOR = 0xFFBFA882;
	private static final int BG_COLOR = 0xEE1C140E;
	private static final int HOVER_COLOR = 0x553A2B1E;
	private static final int TEXT_COLOR = 0xFFF4DEB5;

	private final List<MenuItem> items = new ArrayList<>();
	private boolean open;
	private int x;
	private int y;
	private int width;
	private int height;
	private int itemHeight;

	public boolean isOpen() { return this.open; }

	public boolean contains(double mouseX, double mouseY) {
		return this.open
			&& mouseX >= this.x
			&& mouseX < this.x + this.width
			&& mouseY >= this.y
			&& mouseY < this.y + this.height;
	}

	public void close() {
		this.open = false;
		this.items.clear();
	}

	public void open(int anchorX, int anchorY, int screenWidth, int screenHeight, Font font, List<MenuItem> menuItems) {
		this.items.clear();
		this.items.addAll(menuItems);
		if (this.items.isEmpty()) {
			this.open = false;
			return;
		}
		this.itemHeight = font.lineHeight + 4;
		int maxTextWidth = 0;
		for (MenuItem item : this.items) {
			maxTextWidth = Math.max(maxTextWidth, font.width(item.label));
		}
		this.width = maxTextWidth + PADDING * 2;
		this.height = this.items.size() * this.itemHeight + PADDING;
		this.x = Mth.clamp(anchorX, 2, Math.max(2, screenWidth - this.width - 2));
		this.y = Mth.clamp(anchorY, 2, Math.max(2, screenHeight - this.height - 2));
		this.open = true;
	}

	public boolean handleClick(double mouseX, double mouseY, int button) {
		if (!this.open) return false;
		if (button != 0 && button != 1) {
			this.close();
			return true;
		}
		int idx = this.itemIndexAt(mouseX, mouseY);
		if (button == 0 && idx >= 0) {
			Runnable action = this.items.get(idx).action;
			this.close();
			action.run();
			return true;
		}
		this.close();
		return true;
	}

	public void render(GuiGraphicsExtractor graphics, Font font, int mouseX, int mouseY) {
		if (!this.open) return;
		graphics.fill(this.x, this.y, this.x + this.width, this.y + this.height, BG_COLOR);
		graphics.fill(this.x, this.y, this.x + this.width, this.y + 1, BORDER_COLOR);
		graphics.fill(this.x, this.y + this.height - 1, this.x + this.width, this.y + this.height, BORDER_COLOR);
		graphics.fill(this.x, this.y, this.x + 1, this.y + this.height, BORDER_COLOR);
		graphics.fill(this.x + this.width - 1, this.y, this.x + this.width, this.y + this.height, BORDER_COLOR);
		for (int i = 0; i < this.items.size(); i++) {
			int itemY = this.y + PADDING / 2 + i * this.itemHeight;
			int itemBottom = itemY + this.itemHeight;
			if (mouseX >= this.x + 1 && mouseX < this.x + this.width - 1 && mouseY >= itemY && mouseY < itemBottom) {
				graphics.fill(this.x + 1, itemY, this.x + this.width - 1, itemBottom, HOVER_COLOR);
			}
			graphics.text(font, this.items.get(i).label, this.x + PADDING, itemY + 2, TEXT_COLOR, false);
		}
	}

	private int itemIndexAt(double mouseX, double mouseY) {
		if (mouseX < this.x || mouseX >= this.x + this.width || mouseY < this.y || mouseY >= this.y + this.height) return -1;
		int relativeY = (int) mouseY - this.y - PADDING / 2;
		if (relativeY < 0) return -1;
		int idx = relativeY / this.itemHeight;
		if (idx < 0 || idx >= this.items.size()) return -1;
		return idx;
	}
}

