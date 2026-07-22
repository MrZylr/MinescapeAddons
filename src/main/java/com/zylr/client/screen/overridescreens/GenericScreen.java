package com.zylr.client.screen.overridescreens;

import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudTab;
import com.zylr.client.hud.StackSizeOverlay;
import com.zylr.mixin.SlotAccessor;
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

public final class GenericScreen extends Screen implements HudTabRestoringScreen {
	private static final Identifier BACKGROUND = texture("dialog/background.png");
	private static final Identifier RIVET_TOP_LEFT = texture("dialog/iron_rivets_corner_top_left.png");
	private static final Identifier RIVET_TOP_RIGHT = texture("dialog/iron_rivets_corner_top_right.png");
	private static final Identifier RIVET_BOTTOM_LEFT = texture("dialog/iron_rivets_corner_bottom_left.png");
	private static final Identifier RIVET_BOTTOM_RIGHT = texture("dialog/iron_rivets_corner_bottom_right.png");
	private static final Identifier RIVET_TOP = texture("dialog/iron_rivets_edge_top.png");
	private static final Identifier RIVET_BOTTOM = texture("dialog/iron_rivets_bottom.png");
	private static final Identifier RIVET_LEFT = texture("dialog/iron_rivets_vertical.png");
	private static final Identifier RIVET_RIGHT = texture("dialog/iron_rivets_edge_right.png");
	private static final Identifier WINDOW_CLOSE_BUTTON = texture("other/window_close_button.png");
	private static final Identifier WINDOW_CLOSE_BUTTON_HOVERED = texture("other/window_close_button_hovered.png");
	private static final int SCREEN_WIDTH = 365;
	private static final int SCREEN_HEIGHT = 260;
	private static final int OUTER_EDGE = 36;
	private static final int EDGE_OUTSET = 15;
	private static final int HEADER_TEXT_Y = 10;
	private static final int HEADER_CLOSE_X = SCREEN_WIDTH - 31;
	private static final int HEADER_CLOSE_Y = 7;
	private static final int HEADER_CLOSE_W = 22;
	private static final int HEADER_CLOSE_H = 19;
	private static final int SLOT_START_X = 23;
	private static final int SLOT_START_Y = 38;
	private static final int SLOT_COLS = 9;
	private static final int SLOT_STEP = 36;
	private static final int ITEM_DRAW_SIZE = 32;
	private static final int CHEST_ROW_SLOT_COUNT = 9;
	private static final int TEXT_COLOR = 0xFFD4C39B;

	private final ChestMenu menu;
	private final Inventory inventory;
	private final boolean focusInventoryOnOpen;
	private int leftPos;
	private int topPos;
	private HudTab previousHudTab;

	public GenericScreen(ChestMenu menu, Inventory inventory, Component title, boolean focusInventoryOnOpen) {
		super(title);
		this.menu = menu;
		this.inventory = inventory;
		this.focusInventoryOnOpen = focusInventoryOnOpen;
	}

	@Override
	protected void init() {
		this.leftPos = (this.width - SCREEN_WIDTH) / 2;
		this.topPos = (this.height - SCREEN_HEIGHT) / 2;
		if (this.focusInventoryOnOpen) {
			this.enterInventoryHudTab();
		}
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

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		if (this.minecraft != null) {
			HudManager.getInstance().render(graphics, this.minecraft, mouseX, mouseY, delta, false, null);
			HudManager.getInstance().renderRuntimeContextMenu(graphics, this.minecraft, mouseX, mouseY);
		}
		this.drawOuterFrame(graphics);
		this.drawTitle(graphics);
		this.drawHeaderButtons(graphics, mouseX, mouseY);
		this.drawChestItems(graphics);
		this.drawTooltips(graphics, mouseX, mouseY);
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
		Slot slot = this.findSlot(mouseX, mouseY);
		if (slot != null) {
			int slotId = this.slotId(slot);
			if (slotId >= 0 && slot.hasItem()) {
				ContainerInput clickType = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;
				this.sendMenuClick(slotId, event.button(), clickType);
				return true;
			}
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
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

	private void layoutSlots() {
		int chestSlotCount = this.chestSlotCount();
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slotId < chestSlotCount) {
				int row = slotId / CHEST_ROW_SLOT_COUNT;
				int col = slotId % CHEST_ROW_SLOT_COUNT;
				((SlotAccessor) slot).minescapeaddon$setX(SLOT_START_X + col * SLOT_STEP);
				((SlotAccessor) slot).minescapeaddon$setY(SLOT_START_Y + row * SLOT_STEP);
			} else {
				((SlotAccessor) slot).minescapeaddon$setX(-1000);
				((SlotAccessor) slot).minescapeaddon$setY(-1000);
			}
		}
	}

	private void drawTitle(GuiGraphicsExtractor graphics) {
		String menuTitle = this.title.getString();
		int titleX = (SCREEN_WIDTH - this.font.width(menuTitle)) / 2;
		graphics.text(this.font, menuTitle, this.leftPos + titleX, this.topPos + HEADER_TEXT_Y, TEXT_COLOR, false);
	}

	private void drawChestItems(GuiGraphicsExtractor graphics) {
		int chestSlotCount = this.chestSlotCount();
		for (int slotId = 0; slotId < chestSlotCount; slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slot == null || !slot.hasItem()) {
				continue;
			}
			int drawX = this.leftPos + slot.x;
			int drawY = this.topPos + slot.y;
			this.drawScaledItem(graphics, slot.getItem(), drawX, drawY, ITEM_DRAW_SIZE);
		}
	}

	private void drawScaledItem(GuiGraphicsExtractor graphics, net.minecraft.world.item.ItemStack stack, int x, int y, int drawSize) {
		float scale = drawSize / 16.0F;
		int centeredX = x - Math.round((drawSize - 16) / 2.0F);
		int centeredY = y - Math.round((drawSize - 16) / 2.0F);
		graphics.pose().pushMatrix();
		graphics.pose().translate(centeredX, centeredY);
		graphics.pose().scale(scale, scale);
		graphics.item(stack, 0, 0);
		graphics.pose().popMatrix();
		StackSizeOverlay.renderLightweightStackSizeOrCount(graphics, this.font, stack, centeredX, centeredY, drawSize);
	}

	private void drawTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		Slot slot = this.findSlot(mouseX, mouseY);
		if (slot != null && slot.hasItem()) {
			graphics.setTooltipForNextFrame(this.font, slot.getItem(), mouseX, mouseY);
		}
	}

	private boolean handleHeaderClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		if (contains(relativeX, relativeY, HEADER_CLOSE_X, HEADER_CLOSE_Y, HEADER_CLOSE_W, HEADER_CLOSE_H)) {
			this.onClose();
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

	private void drawOuterFrame(GuiGraphicsExtractor graphics) {
		int x = this.leftPos;
		int y = this.topPos;
		this.tileFill(graphics, BACKGROUND, x, y, SCREEN_WIDTH, SCREEN_HEIGHT, 88, 60);
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

	private void sendMenuClick(int slotId, int button, ContainerInput clickType) {
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

	private Slot findSlot(int mouseX, int mouseY) {
		int chestSlotCount = this.chestSlotCount();
		for (int slotId = 0; slotId < chestSlotCount; slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			int x = this.leftPos + slot.x - Math.round((ITEM_DRAW_SIZE - 16) / 2.0F);
			int y = this.topPos + slot.y - Math.round((ITEM_DRAW_SIZE - 16) / 2.0F);
			if (contains(mouseX, mouseY, x, y, ITEM_DRAW_SIZE, ITEM_DRAW_SIZE)) {
				return slot;
			}
		}
		return null;
	}

	private int slotId(Slot target) {
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			if (this.menu.slots.get(slotId) == target) {
				return slotId;
			}
		}
		return -1;
	}

	private int chestSlotCount() {
		return this.menu.getRowCount() * CHEST_ROW_SLOT_COUNT;
	}

	private int menuSlotIdForPlayerInventory(int inventorySlot) {
		int chestSlots = this.chestSlotCount();
		if (inventorySlot >= 9 && inventorySlot <= 35) {
			return chestSlots + (inventorySlot - 9);
		}
		if (inventorySlot >= 0 && inventorySlot <= 8) {
			return chestSlots + 27 + inventorySlot;
		}
		return -1;
	}

	private void tileFill(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height, int tileW, int tileH) {
		for (int drawY = 0; drawY < height; drawY += tileH) {
			for (int drawX = 0; drawX < width; drawX += tileW) {
				int pieceW = Math.min(tileW, width - drawX);
				int pieceH = Math.min(tileH, height - drawY);
				graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x + drawX, y + drawY, 0.0F, 0.0F, pieceW, pieceH, tileW, tileH, tileW, tileH);
			}
		}
	}

	private static boolean contains(int mouseX, int mouseY, int x, int y, int width, int height) {
		return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
	}

	private static Identifier texture(String path) {
		return Identifier.parse("minescapeaddon:textures/gui/runescape/" + path);
	}
}
