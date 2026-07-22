package com.zylr.client.screen.overridescreens;

import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import org.lwjgl.glfw.GLFW;

abstract class AbstractGrandExchangeScreen extends Screen implements HudTabRestoringScreen {
	protected static final Identifier BACKGROUND = texture("dialog/background.png");
	protected static final Identifier RIVET_TOP_LEFT = texture("dialog/iron_rivets_corner_top_left.png");
	protected static final Identifier RIVET_TOP_RIGHT = texture("dialog/iron_rivets_corner_top_right.png");
	protected static final Identifier RIVET_BOTTOM_LEFT = texture("dialog/iron_rivets_corner_bottom_left.png");
	protected static final Identifier RIVET_BOTTOM_RIGHT = texture("dialog/iron_rivets_corner_bottom_right.png");
	protected static final Identifier RIVET_TOP = texture("dialog/iron_rivets_edge_top.png");
	protected static final Identifier RIVET_BOTTOM = texture("dialog/iron_rivets_bottom.png");
	protected static final Identifier RIVET_LEFT = texture("dialog/iron_rivets_vertical.png");
	protected static final Identifier RIVET_RIGHT = texture("dialog/iron_rivets_edge_right.png");
	protected static final Identifier WINDOW_CLOSE_BUTTON = texture("other/window_close_button.png");
	protected static final Identifier WINDOW_CLOSE_BUTTON_HOVERED = texture("other/window_close_button_hovered.png");
	protected static final int SCREEN_WIDTH = 365;
	protected static final int SCREEN_HEIGHT = 260;
	protected static final int OUTER_EDGE = 36;
	protected static final int EDGE_OUTSET = 15;
	protected static final int HEADER_TEXT_Y = 10;
	protected static final int HEADER_CLOSE_X = SCREEN_WIDTH - 31;
	protected static final int HEADER_CLOSE_Y = 7;
	protected static final int HEADER_CLOSE_W = 22;
	protected static final int HEADER_CLOSE_H = 19;
	protected static final int HEADER_DIVIDER_Y = 12;
	protected static final int HEADER_DIVIDER_H = OUTER_EDGE;
	protected static final int SUBTITLE_Y = 38;
	protected static final int CHEST_ROW_SLOT_COUNT = 9;
	protected static final int TITLE_TEXT_COLOR = 0xFFCB8B2B;
	protected static final int SUBTITLE_TEXT_COLOR = 0xFFCB8B2B;
	protected static final float MENU_TITLE_SCALE = 1.5F;
	protected static final int DOUBLE_LINE_OUTER_COLOR = 0xFF3F3728;
	protected static final int DOUBLE_LINE_INNER_COLOR = 0xFF50493B;

	protected final ChestMenu menu;
	protected final Inventory inventory;
	protected int leftPos;
	protected int topPos;
	private HudTab previousHudTab;

	protected AbstractGrandExchangeScreen(ChestMenu menu, Inventory inventory, Component title) {
		super(title);
		this.menu = menu;
		this.inventory = inventory;
	}

	@Override
	protected void init() {
		this.leftPos = (this.width - SCREEN_WIDTH) / 2;
		this.topPos = (this.height - SCREEN_HEIGHT) / 2;
		this.enterInventoryHudTab();
		this.layoutSlots();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void onClose() {
		this.restoreHudTab();
		if (this.minecraft != null && this.minecraft.player != null) {
			this.minecraft.player.closeContainer();
		}
		super.onClose();
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		if (this.minecraft != null) {
			HudManager.getInstance().render(graphics, this.minecraft, mouseX, mouseY, delta, false, null);
			HudManager.getInstance().renderRuntimeContextMenu(graphics, this.minecraft, mouseX, mouseY);
		}
		this.drawOuterFrame(graphics);
		this.drawHeader(graphics, mouseX, mouseY);
		this.drawContent(graphics, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		int mouseX = (int) event.x();
		int mouseY = (int) event.y();
		HudManager.getInstance().updateCursor(mouseX, mouseY);
		long window = this.minecraft != null ? this.minecraft.getWindow().handle() : 0L;
		boolean shift = window != 0L && (
			GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
				|| GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS
		);
		if (this.handleCustomInventoryClick(mouseX, mouseY, event.button(), shift)) {
			return true;
		}
		if (this.minecraft != null && HudManager.getInstance().isContentPanelTabHit(mouseX, mouseY, this.width, this.height)) {
			return true;
		}
		if (this.minecraft != null && HudManager.getInstance().clickHud(mouseX, mouseY, this.width, this.height, event.button(), shift)) {
			return true;
		}
		if (this.handleHeaderClick(mouseX, mouseY)) {
			return true;
		}
		Slot slot = this.findInteractiveSlot(mouseX, mouseY);
		if (slot != null) {
			int slotId = this.slotId(slot);
			if (slotId >= 0) {
				ContainerInput clickType = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;
				this.sendMenuClick(slotId, event.button(), clickType);
				return true;
			}
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (event.key() == GLFW.GLFW_KEY_ESCAPE) {
			this.onClose();
			return true;
		}
		if (super.keyPressed(event)) {
			return true;
		}
		if (this.minecraft != null && this.minecraft.options.keyInventory.matches(event)) {
			this.onClose();
			return true;
		}
		if (CustomScreenInventoryKeyHandler.handleDropHoveredInventorySlot(event, this.minecraft, this.menu, this::menuSlotIdForPlayerInventory)) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		HudManager.getInstance().updateCursor(mouseX, mouseY);
		super.mouseMoved(mouseX, mouseY);
	}

	protected abstract void layoutSlots();

	protected abstract void drawContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY);

	protected abstract Slot findInteractiveSlot(int mouseX, int mouseY);

	protected void hideAllSlots() {
		for (Slot slot : this.menu.slots) {
			com.zylr.mixin.SlotAccessor accessor = (com.zylr.mixin.SlotAccessor) slot;
			accessor.minescapeaddon$setX(-1000);
			accessor.minescapeaddon$setY(-1000);
		}
	}

	protected final int chestSlotCount() {
		return this.menu.getRowCount() * CHEST_ROW_SLOT_COUNT;
	}

	protected final int menuSlotIdForPlayerInventory(int inventorySlot) {
		int chestSlots = this.chestSlotCount();
		if (inventorySlot >= 9 && inventorySlot <= 35) {
			return chestSlots + (inventorySlot - 9);
		}
		if (inventorySlot >= 0 && inventorySlot <= 8) {
			return chestSlots + 27 + inventorySlot;
		}
		return -1;
	}

	protected final int slotId(Slot target) {
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			if (this.menu.slots.get(slotId) == target) {
				return slotId;
			}
		}
		return -1;
	}

	protected final void drawHeader(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		this.drawTitle(graphics);
		this.drawHeaderButtons(graphics, mouseX, mouseY);
	}

	protected final void drawTitle(GuiGraphicsExtractor graphics) {
		String menuTitle = this.getMenuTitle();
		int scaledTitleWidth = Math.round(this.font.width(menuTitle) * MENU_TITLE_SCALE);
		int titleX = this.leftPos + (SCREEN_WIDTH - scaledTitleWidth) / 2;
		graphics.pose().pushMatrix();
		graphics.pose().translate(titleX, this.topPos + HEADER_TEXT_Y);
		graphics.pose().scale(MENU_TITLE_SCALE, MENU_TITLE_SCALE);
		graphics.text(this.font, menuTitle, 0, 0, TITLE_TEXT_COLOR, false);
		graphics.pose().popMatrix();
		String subtitle = this.getSubtitle();
		int subtitleX = (SCREEN_WIDTH - this.font.width(subtitle)) / 2;
		graphics.text(this.font, subtitle, this.leftPos + subtitleX, this.topPos + SUBTITLE_Y, SUBTITLE_TEXT_COLOR, false);
	}

	protected String getMenuTitle() {
		return "Grand Exchange";
	}

	protected String getSubtitle() {
		return "Select an offer slot to set up or view an offer.";
	}

	protected final void drawOuterFrame(GuiGraphicsExtractor graphics) {
		int x = this.leftPos;
		int y = this.topPos;
		this.tileFill(graphics, BACKGROUND, x, y, SCREEN_WIDTH, SCREEN_HEIGHT, 88, 60);
		this.tileFill(graphics, RIVET_TOP, x, y + HEADER_DIVIDER_Y, SCREEN_WIDTH, HEADER_DIVIDER_H, 36, 36);
		this.tileFill(graphics, RIVET_TOP, x, y - EDGE_OUTSET, SCREEN_WIDTH, OUTER_EDGE, 36, 36);
		this.tileFill(graphics, RIVET_BOTTOM, x, y + SCREEN_HEIGHT - OUTER_EDGE + EDGE_OUTSET, SCREEN_WIDTH, OUTER_EDGE, 36, 36);
		this.tileFill(graphics, RIVET_LEFT, x - EDGE_OUTSET, y, OUTER_EDGE, SCREEN_HEIGHT, 36, 36);
		this.tileFill(graphics, RIVET_RIGHT, x + SCREEN_WIDTH - OUTER_EDGE + EDGE_OUTSET, y, OUTER_EDGE, SCREEN_HEIGHT, 36, 36);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_TOP_LEFT, x, y, 0.0F, 0.0F, 25, 30, 25, 30);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_TOP_RIGHT, x + SCREEN_WIDTH - 25, y, 0.0F, 0.0F, 25, 30, 25, 30);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_BOTTOM_LEFT, x, y + SCREEN_HEIGHT - 30, 0.0F, 0.0F, 25, 30, 25, 30);
		graphics.blit(RenderPipelines.GUI_TEXTURED, RIVET_BOTTOM_RIGHT, x + SCREEN_WIDTH - 25, y + SCREEN_HEIGHT - 30, 0.0F, 0.0F, 25, 30, 25, 30);
	}

	private void drawHeaderButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		Identifier closeTexture = contains(relativeX, relativeY, HEADER_CLOSE_X, HEADER_CLOSE_Y, HEADER_CLOSE_W, HEADER_CLOSE_H)
			? WINDOW_CLOSE_BUTTON_HOVERED
			: WINDOW_CLOSE_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, closeTexture, this.leftPos + HEADER_CLOSE_X, this.topPos + HEADER_CLOSE_Y, 0.0F, 0.0F, HEADER_CLOSE_W, HEADER_CLOSE_H, HEADER_CLOSE_W, HEADER_CLOSE_H, HEADER_CLOSE_W, HEADER_CLOSE_H);
	}

	private boolean handleHeaderClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		if (contains(relativeX, relativeY, HEADER_CLOSE_X, HEADER_CLOSE_Y, HEADER_CLOSE_W, HEADER_CLOSE_H)) {
			this.minescapeaddon$closeLikeEscape();
			return true;
		}
		return false;
	}

	private boolean handleCustomInventoryClick(int mouseX, int mouseY, int button, boolean shift) {
		if (HudManager.getInstance().getSelectedTab() != HudTab.INVENTORY) {
			return false;
		}

		int inventorySlot = HudManager.getInstance().inventoryPanelSlotIndexForBank(mouseX, mouseY);
		if (inventorySlot < 0) {
			return false;
		}

		int menuSlotId = this.menuSlotIdForPlayerInventory(inventorySlot);
		if (menuSlotId < 0 || menuSlotId >= this.menu.slots.size()) {
			return false;
		}

		ContainerInput clickType = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;
		this.sendMenuClick(menuSlotId, button, clickType);
		return true;
	}

	protected final void sendMenuClick(int slotId, int button, ContainerInput clickType) {
		Minecraft minecraft = this.minecraft;
		if (minecraft == null || minecraft.player == null) {
			return;
		}
		if (minecraft.gameMode == null) {
			return;
		}
		if (!MenuInteractionGate.allowMenuClick(this.menu.containerId, slotId, button, clickType)) {
			return;
		}
		minecraft.gameMode.handleContainerInput(this.menu.containerId, slotId, button, clickType, minecraft.player);
	}

	protected final void tileFill(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height, int tileW, int tileH) {
		for (int drawY = 0; drawY < height; drawY += tileH) {
			for (int drawX = 0; drawX < width; drawX += tileW) {
				int pieceW = Math.min(tileW, width - drawX);
				int pieceH = Math.min(tileH, height - drawY);
				graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x + drawX, y + drawY, 0.0F, 0.0F, pieceW, pieceH, tileW, tileH, tileW, tileH);
			}
		}
	}

	protected final void drawDoubleLineBox(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.fill(x + 1, y + 1, x + width + 1, y + 2, DOUBLE_LINE_INNER_COLOR);
		graphics.fill(x + 1, y + height, x + width + 1, y + height + 1, DOUBLE_LINE_INNER_COLOR);
		graphics.fill(x + 1, y + 1, x + 2, y + height - 1, DOUBLE_LINE_INNER_COLOR);
		graphics.fill(x + width, y + 1, x + width + 1, y + height + 1, DOUBLE_LINE_INNER_COLOR);
		graphics.fill(x, y, x + width, y + 1, DOUBLE_LINE_OUTER_COLOR);
		graphics.fill(x, y + height - 1, x + width, y + height, DOUBLE_LINE_OUTER_COLOR);
		graphics.fill(x, y, x + 1, y + height, DOUBLE_LINE_OUTER_COLOR);
		graphics.fill(x + width - 1, y, x + width, y + height, DOUBLE_LINE_OUTER_COLOR);
	}

	private void enterInventoryHudTab() {
		if (this.previousHudTab != null) {
			return;
		}
		HudManager hudManager = HudManager.getInstance();
		this.previousHudTab = hudManager.getSelectedTab();
		hudManager.selectTab(HudTab.INVENTORY);
	}

	private void restoreHudTab() {
		if (this.previousHudTab == null) {
			return;
		}
		HudManager.getInstance().selectTab(this.previousHudTab);
		this.previousHudTab = null;
	}

	@Override
	public void minescapeaddon$restoreHudTabForRemoval() {
		this.restoreHudTab();
	}

	private void minescapeaddon$closeLikeEscape() {
		this.onClose();
	}

	protected static boolean contains(int mouseX, int mouseY, int x, int y, int width, int height) {
		return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
	}

	protected static Identifier texture(String path) {
		return Identifier.parse("minescapeaddon:textures/gui/runescape/" + path);
	}
}
