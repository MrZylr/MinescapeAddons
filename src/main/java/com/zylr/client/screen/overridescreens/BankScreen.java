package com.zylr.client.screen.overridescreens;

import com.zylr.MinescapeAddon;
import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudTab;
import com.zylr.client.hud.StackSizeOverlay;
import com.zylr.mixin.SlotAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public final class BankScreen extends Screen implements HudTabRestoringScreen {
	private static final Identifier BACKGROUND = texture("dialog/background.png");
	private static final Identifier RIVET_TOP_LEFT = texture("dialog/iron_rivets_corner_top_left.png");
	private static final Identifier RIVET_TOP_RIGHT = texture("dialog/iron_rivets_corner_top_right.png");
	private static final Identifier RIVET_BOTTOM_LEFT = texture("dialog/iron_rivets_corner_bottom_left.png");
	private static final Identifier RIVET_BOTTOM_RIGHT = texture("dialog/iron_rivets_corner_bottom_right.png");
	private static final Identifier RIVET_TOP = texture("dialog/iron_rivets_edge_top.png");
	private static final Identifier RIVET_BOTTOM = texture("dialog/iron_rivets_bottom.png");
	private static final Identifier RIVET_LEFT = texture("dialog/iron_rivets_vertical.png");
	private static final Identifier RIVET_RIGHT = texture("dialog/iron_rivets_edge_right.png");
	private static final Identifier FOOTER_BUTTON_BG = texture("equipment/slot_tile.png");
	private static final Identifier FOOTER_BUTTON_BG_SELECTED = texture("equipment/slot_selected.png");
	private static final Identifier TAB = texture("bank/tab.png");
	private static final Identifier TAB_SELECTED = texture("bank/tab_selected.png");
	private static final Identifier TAB_HOVERED = texture("bank/tab_hovered.png");
	private static final Identifier TAB_EMPTY = texture("bank/tab.png");
	private static final Identifier TAB_ALL_ITEMS_ICON = texture("bank/tab_all_items_icon.png");
	private static final Identifier SEARCH_ICON = texture("bank/search.png");
	private static final Identifier DEPOSIT_INVENTORY_ICON = texture("bank/deposit_inventory.png");
	private static final Identifier DEPOSIT_EQUIPMENT_ICON = texture("bank/deposit_equipment.png");
	private static final Identifier INSERT_ITEMS_ICON = texture("bank/insert_items.png");
	private static final Identifier NOTED_ITEMS_ICON = texture("bank/noted_items.png");
	private static final Identifier PLACEHOLDERS_LOCK_ICON = texture("bank/placeholders_lock.png");
	private static final Identifier COAL_BAG_ICON = texture("minescape/coal_bag.png");
	private static final Identifier LARGE_POUCH_ICON = texture("minescape/large_pouch.png");
	private static final Identifier HERB_SACK_ICON = texture("minescape/herb_sack.png");
	private static final Identifier RED_DOWN_DOUBLE_CHEVRON = texture("chevron/red_down_double.png");
	private static final Identifier GREEN_UP_DOUBLE_CHEVRON = texture("chevron/green_up_double.png");
	private static final Identifier WINDOW_CLOSE_BUTTON = texture("other/window_close_button.png");
	private static final Identifier WINDOW_CLOSE_BUTTON_HOVERED = texture("other/window_close_button_hovered.png");
	private static final Identifier TUTORIAL_BUTTON = texture("button/tutorial.png");
	private static final Identifier TUTORIAL_BUTTON_HOVERED = texture("button/tutorial_hovered.png");
	private static final Identifier ARROW_UP = texture("scrollbar/arrow_up.png");
	private static final Identifier ARROW_DOWN = texture("scrollbar/arrow_down.png");
	private static final int SCREEN_WIDTH = 365;
	private static final int SCREEN_HEIGHT = 260;
	private static final int OUTER_EDGE = 36;
	private static final int EDGE_OUTSET = 15;
	private static final int TAB_START_X = 16;
	private static final int TAB_START_Y = 26;
	private static final int TAB_WIDTH = 37;
	private static final int TAB_HEIGHT = 36;
	private static final int TAB_SPACING = 37;
	private static final int TAB_COUNT = 9;
	private static final int HIDDEN_TAB_SLOT_COUNT = 9;
	private static final int HIDDEN_TRAILING_SLOT_COUNT = 9;
	private static final int CLICKABLE_TAB_SLOT_COUNT = 9;
	private static final int DEPOSIT_EQUIPMENT_SLOT_ID = 45;
	private static final int DEPOSIT_INVENTORY_SLOT_ID = 46;
	private static final int SEARCH_SLOT_ID = 47;
	private static final int INSERT_ITEMS_SLOT_ID = 48;
	private static final int PLACEHOLDERS_SLOT_ID = 50;
	private static final int NOTED_ITEMS_SLOT_ID = 51;
	private static final int FOOTER_PANEL_X = 18;
	private static final int FOOTER_PANEL_Y = 254;
	private static final int FOOTER_PANEL_W = 469;
	private static final int FOOTER_PANEL_H = 49;
	private static final int BANK_SLOT_X = 29;
	private static final int BANK_SLOT_Y = 77;
	private static final int BANK_SLOT_COLS = 9;
	private static final int SLOT_STEP = 36;
	private static final int BANK_ITEM_DRAW_SIZE = 32;
	private static final int BANK_FOOTER_BUTTON_Y = 215;
	private static final int BUTTON_SIZE = 29;
	private static final int BUTTON_BG_SIZE = 36;
	private static final int BUTTON_SPACING = 18;
	private static final int BUTTON_OVERLAY_SIZE = 8;
	private static final int TEXT_COLOR = 0xFFD4C39B;
	private static final int HEADER_TEXT_Y = 10;
	private static final int HEADER_CLOSE_X = SCREEN_WIDTH - 31;
	private static final int HEADER_CLOSE_Y = 7;
	private static final int HEADER_CLOSE_W = 22;
	private static final int HEADER_CLOSE_H = 19;
	private static final int HEADER_TUTORIAL_X = SCREEN_WIDTH - 53;
	private static final int HEADER_TUTORIAL_Y = 8;
	private static final int HEADER_TUTORIAL_W = 17;
	private static final int HEADER_TUTORIAL_H = 17;
	private static final int TUTORIAL_TOOLTIP_SLOT_ID = 49;
	private static final int SCROLL_BUTTON_X = SCREEN_WIDTH - 22;
	private static final int SCROLL_UP_Y = 82;
	private static final int SCROLL_DOWN_Y = 188;
	private static final int SCROLL_BUTTON_W = 14;
	private static final int SCROLL_BUTTON_H = 14;

	private final ChestMenu menu;
	private final Inventory inventory;
	private final List<BankButton> buttons = new ArrayList<>();
	private int leftPos;
	private int topPos;
	private int selectedTab;
	private boolean searchActive;
	private boolean insertMode;
	private boolean withdrawAsNote;
	private boolean placeholdersEnabled;
	private HudTab previousHudTab;

	public BankScreen(ChestMenu menu, Inventory inventory, Component title) {
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
		this.selectedTab = this.resolveActiveTab();
		this.rebuildButtons();
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
		this.selectedTab = this.resolveActiveTab();
		if (this.minecraft != null) {
			HudManager.getInstance().render(graphics, this.minecraft, mouseX, mouseY, delta, false, null);
			HudManager.getInstance().renderRuntimeContextMenu(graphics, this.minecraft, mouseX, mouseY);
		}
		this.drawOuterFrame(graphics);
		this.drawTitle(graphics);
		this.drawTabRow(graphics, mouseX, mouseY);
		this.drawBankItems(graphics);
		this.drawButtons(graphics, mouseX, mouseY);
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
		if (this.handleTabClick(mouseX, mouseY) || this.handleButtonClick(mouseX, mouseY, event.button(), shift)) {
			return true;
		}
		Slot slot = this.findSlot(mouseX, mouseY);
		if (slot != null) {
			int slotId = this.slotId(slot);
			if (slotId >= 0 && slot.hasItem()) {
				ContainerInput clickType = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;
				this.sendMenuClick(slotId, event.button(), clickType, false);
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

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		if (verticalAmount != 0.0 && this.clickNamedItem(verticalAmount > 0.0 ? 52 : 53)) {
			return true;
		}
		return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
	}

	private void layoutSlots() {
		int bankSlots = this.bankSlotCount();
		int lastVisibleBankSlot = Math.max(HIDDEN_TAB_SLOT_COUNT, bankSlots - HIDDEN_TRAILING_SLOT_COUNT);
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slotId < HIDDEN_TAB_SLOT_COUNT) {
				((SlotAccessor) slot).minescapeaddon$setX(-1000);
				((SlotAccessor) slot).minescapeaddon$setY(-1000);
			} else if (slotId < lastVisibleBankSlot) {
				int visibleIndex = slotId - HIDDEN_TAB_SLOT_COUNT;
				int col = visibleIndex % BANK_SLOT_COLS;
				int row = visibleIndex / BANK_SLOT_COLS;
				((SlotAccessor) slot).minescapeaddon$setX(BANK_SLOT_X + col * SLOT_STEP);
				((SlotAccessor) slot).minescapeaddon$setY(BANK_SLOT_Y + row * SLOT_STEP);
			} else {
				((SlotAccessor) slot).minescapeaddon$setX(-1000);
				((SlotAccessor) slot).minescapeaddon$setY(-1000);
			}
		}
	}

	private void rebuildButtons() {
		this.buttons.clear();
		int buttonCount = 6;
		int totalWidth = buttonCount * BUTTON_BG_SIZE + (buttonCount - 1) * BUTTON_SPACING;
		int x = (SCREEN_WIDTH - totalWidth) / 2 + 4;
		this.buttons.add(new BankButton(x, BANK_FOOTER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE-6, SEARCH_ICON, SEARCH_SLOT_ID, Component.literal("Search"), ButtonBackgroundMode.STATIC));
		x += BUTTON_BG_SIZE + BUTTON_SPACING;
		this.buttons.add(new BankButton(x, BANK_FOOTER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE-6, DEPOSIT_INVENTORY_ICON, DEPOSIT_INVENTORY_SLOT_ID, Component.literal("Deposit inventory"), ButtonBackgroundMode.STATIC));
		x += BUTTON_BG_SIZE + BUTTON_SPACING;
		this.buttons.add(new BankButton(x, BANK_FOOTER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE-6, DEPOSIT_EQUIPMENT_ICON, DEPOSIT_EQUIPMENT_SLOT_ID, Component.literal("Deposit equipment"), ButtonBackgroundMode.STATIC));
		x += BUTTON_BG_SIZE + BUTTON_SPACING;
		this.buttons.add(new BankButton(x, BANK_FOOTER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE-6, INSERT_ITEMS_ICON, INSERT_ITEMS_SLOT_ID, Component.literal("Insert rearrange mode"), ButtonBackgroundMode.INSERT_ITEMS));
		x += BUTTON_BG_SIZE + BUTTON_SPACING;
		this.buttons.add(new BankButton(x, BANK_FOOTER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE-6, NOTED_ITEMS_ICON, NOTED_ITEMS_SLOT_ID, Component.literal("Withdraw as note"), ButtonBackgroundMode.NOTED_ITEMS));
		x += BUTTON_BG_SIZE + BUTTON_SPACING;
		this.buttons.add(new BankButton(x, BANK_FOOTER_BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE-6, PLACEHOLDERS_LOCK_ICON, PLACEHOLDERS_SLOT_ID, Component.literal("Placeholders"), ButtonBackgroundMode.PLACEHOLDERS));
	}

	private void drawTitle(GuiGraphicsExtractor graphics) {
		String menuTitle = "Bank of Gielinor";
		int titleX = (SCREEN_WIDTH - this.font.width(menuTitle)) / 2;
		graphics.text(this.font, menuTitle, this.leftPos + titleX, this.topPos + HEADER_TEXT_Y, TEXT_COLOR, false);
	}

	private void drawTabRow(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		for (int tab = 0; tab < TAB_COUNT; tab++) {
			int tabX = this.leftPos + TAB_START_X + tab * TAB_SPACING;
			boolean hovered = contains(relativeX, relativeY, TAB_START_X + tab * TAB_SPACING, TAB_START_Y, TAB_WIDTH, TAB_HEIGHT);
			Identifier texture = TAB_EMPTY;
			if (tab == this.selectedTab) {
				texture = TAB_SELECTED;
			} else if (hovered) {
				texture = TAB_HOVERED;
			} else if (tab == 0) {
				texture = TAB;
			}
			graphics.blit(RenderPipelines.GUI_TEXTURED, texture, tabX, this.topPos + TAB_START_Y, 0.0F, 0.0F, TAB_WIDTH, TAB_HEIGHT, TAB_WIDTH, TAB_HEIGHT, TAB_WIDTH, TAB_HEIGHT);
			if (tab == 0) {
				graphics.blit(RenderPipelines.GUI_TEXTURED, TAB_ALL_ITEMS_ICON, tabX + 8, this.topPos + TAB_START_Y + 8, 0.0F, 0.0F, 24, 24, 24, 24);
				continue;
			}

			Slot iconSlot = this.controlSlot(tab);
			if (iconSlot != null && iconSlot.hasItem()) {
				this.drawTabItemIcon(graphics, iconSlot.getItem(), tabX + 12, this.topPos + TAB_START_Y + 12);
			}
		}
	}

	private void drawTabItemIcon(GuiGraphicsExtractor graphics, net.minecraft.world.item.ItemStack stack, int x, int y) {
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(1.25F, 1.25F);
		graphics.item(stack, -2, -2);
		graphics.pose().popMatrix();
	}

	private void drawBankItems(GuiGraphicsExtractor graphics) {
		for (int slotId = HIDDEN_TAB_SLOT_COUNT; slotId < this.lastVisibleBankSlot(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slot == null || !slot.hasItem()) {
				continue;
			}
			int drawX = this.leftPos + slot.x;
			int drawY = this.topPos + slot.y;
			this.drawScaledBankItem(graphics, slot.getItem(), drawX, drawY, BANK_ITEM_DRAW_SIZE);
		}
	}

	private void drawScaledBankItem(GuiGraphicsExtractor graphics, net.minecraft.world.item.ItemStack stack, int x, int y, int drawSize) {
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

	private void drawButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		this.drawHeaderButtons(graphics, mouseX, mouseY);
		for (BankButton button : this.buttons) {
			button.draw(
				graphics,
				this.leftPos,
				this.topPos,
				mouseX,
				mouseY,
				this.font,
				this.footerButtonBackground(button),
				this.footerButtonIcon(button),
				this.footerButtonOverlay(button)
			);
		}
	}

	private void drawTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		Slot slot = this.findSlot(mouseX, mouseY);
		if (slot != null && slot.hasItem()) {
			graphics.setTooltipForNextFrame(this.font, slot.getItem(), mouseX, mouseY);
			return;
		}

		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		if (contains(relativeX, relativeY, HEADER_TUTORIAL_X, HEADER_TUTORIAL_Y, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H)) {
			Slot tutorialSlot = this.controlSlot(TUTORIAL_TOOLTIP_SLOT_ID);
			if (tutorialSlot != null && tutorialSlot.hasItem()) {
				graphics.setTooltipForNextFrame(this.font, tutorialSlot.getItem(), mouseX, mouseY);
			}
			return;
		}
		for (int tab = 0; tab < TAB_COUNT; tab++) {
			if (contains(relativeX, relativeY, TAB_START_X + tab * TAB_SPACING, TAB_START_Y, TAB_WIDTH, TAB_HEIGHT)) {
				graphics.setTooltipForNextFrame(this.font, Component.literal("View tab " + (tab + 1)), mouseX, mouseY);
				return;
			}
		}
		for (BankButton button : this.buttons) {
			if (button.contains(relativeX, relativeY)) {
				Slot controlSlot = this.controlSlot(button.slotId());
				if (controlSlot != null && controlSlot.hasItem()) {
					graphics.setTooltipForNextFrame(this.font, controlSlot.getItem(), mouseX, mouseY);
				} else {
					graphics.setTooltipForNextFrame(this.font, button.fallbackTooltip(), mouseX, mouseY);
				}
				return;
			}
		}
	}

	private boolean handleTabClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		for (int tab = 0; tab < TAB_COUNT && tab < CLICKABLE_TAB_SLOT_COUNT; tab++) {
			int tabX = TAB_START_X + tab * TAB_SPACING;
			if (contains(relativeX, relativeY, tabX, TAB_START_Y, TAB_WIDTH, TAB_HEIGHT)) {
				this.sendMenuClick(tab, 0, ContainerInput.PICKUP, false);
				return true;
			}
		}
		return false;
	}

	private boolean handleButtonClick(int mouseX, int mouseY, int mouseButton, boolean shift) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		if (contains(relativeX, relativeY, HEADER_CLOSE_X, HEADER_CLOSE_Y, HEADER_CLOSE_W, HEADER_CLOSE_H)) {
			this.onClose();
			return true;
		}
		if (contains(relativeX, relativeY, HEADER_TUTORIAL_X, HEADER_TUTORIAL_Y, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H)) {
			return true;
		}
		if (contains(relativeX, relativeY, SCROLL_BUTTON_X, SCROLL_UP_Y, SCROLL_BUTTON_W, SCROLL_BUTTON_H)) {
			return this.clickNamedItem(52);
		}
		if (contains(relativeX, relativeY, SCROLL_BUTTON_X, SCROLL_DOWN_Y, SCROLL_BUTTON_W, SCROLL_BUTTON_H)) {
			return this.clickNamedItem(53);
		}
		for (BankButton button : this.buttons) {
			if (button.contains(relativeX, relativeY)) {
				if (button.slotId() == DEPOSIT_INVENTORY_SLOT_ID) {
					Slot controlSlot = this.controlSlot(button.slotId());
					String itemType = controlSlot != null && controlSlot.hasItem() ? controlSlot.getItem().getItem().toString() : "<missing>";
					int damageValue = controlSlot != null && controlSlot.hasItem() ? controlSlot.getItem().getDamageValue() : -1;
					MinescapeAddon.LOGGER.info("deposit inventory button item={} damage={}", itemType, damageValue);
				}
				ContainerInput clickType = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;
				this.sendMenuClick(button.slotId(), mouseButton, clickType, false);
				return true;
			}
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
		this.sendMenuClick(menuSlotId, button, clickType, false);
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

	private void sendMenuClick(int slotId, int button, ContainerInput clickType, boolean isScroll) {
		Minecraft minecraft = this.minecraft;
		if (minecraft == null || minecraft.player == null) {
			return;
		}
		if (minecraft.gameMode == null) {
			return;
		}
		if (!MenuInteractionGate.allowMenuClick(this.menu.containerId, slotId, button, clickType) && !isScroll) {
			return;
		}
		minecraft.gameMode.handleContainerInput(this.menu.containerId, slotId, button, clickType, minecraft.player);
	}

	private boolean clickNamedItem(int slotId) {//52, 53
		if (slotId == 53 || slotId == 52) {
			if (!MenuInteractionGate.allowScrollAction(this.menu.containerId, menu.slots.get(slotId).getItem().getDisplayName().getString())) {
				return false;
			}
			this.sendMenuClick(slotId, 0, ContainerInput.PICKUP, true);
			return true;
		}
		return false;
	}

	private Slot findSlot(int mouseX, int mouseY) {
		for (int slotId = HIDDEN_TAB_SLOT_COUNT; slotId < this.lastVisibleBankSlot(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			int x = this.leftPos + slot.x - Math.round((BANK_ITEM_DRAW_SIZE - 16) / 2.0F);
			int y = this.topPos + slot.y - Math.round((BANK_ITEM_DRAW_SIZE - 16) / 2.0F);
			if (contains(mouseX, mouseY, x, y, BANK_ITEM_DRAW_SIZE, BANK_ITEM_DRAW_SIZE)) {
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

	private int bankSlotCount() {
		return this.menu.getRowCount() * 9;
	}

	private int menuSlotIdForPlayerInventory(int inventorySlot) {
		int bankSlots = this.bankSlotCount();
		if (inventorySlot >= 9 && inventorySlot <= 35) {
			return bankSlots + (inventorySlot - 9);
		}
		if (inventorySlot >= 0 && inventorySlot <= 8) {
			return bankSlots + 27 + inventorySlot;
		}
		return -1;
	}

	private int lastVisibleBankSlot() {
		return Math.max(HIDDEN_TAB_SLOT_COUNT, this.bankSlotCount() - HIDDEN_TRAILING_SLOT_COUNT);
	}

	private Identifier footerButtonBackground(BankButton button) {
		return switch (button.backgroundMode()) {
			case STATIC -> FOOTER_BUTTON_BG;
			case PLACEHOLDERS -> this.damageBasedButtonBackground(PLACEHOLDERS_SLOT_ID, 121, 122);
			case NOTED_ITEMS -> this.damageBasedButtonBackground(NOTED_ITEMS_SLOT_ID, 125, 126);
			case INSERT_ITEMS -> this.damageBasedButtonBackground(INSERT_ITEMS_SLOT_ID, 123, 124);
		};
	}

	private void drawHeaderButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		Identifier closeTexture = contains(relativeX, relativeY, HEADER_CLOSE_X, HEADER_CLOSE_Y, HEADER_CLOSE_W, HEADER_CLOSE_H)
			? WINDOW_CLOSE_BUTTON_HOVERED
			: WINDOW_CLOSE_BUTTON;
		Identifier tutorialTexture = contains(relativeX, relativeY, HEADER_TUTORIAL_X, HEADER_TUTORIAL_Y, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H)
			? TUTORIAL_BUTTON_HOVERED
			: TUTORIAL_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, tutorialTexture, this.leftPos + HEADER_TUTORIAL_X, this.topPos + HEADER_TUTORIAL_Y, 0.0F, 0.0F, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, closeTexture, this.leftPos + HEADER_CLOSE_X, this.topPos + HEADER_CLOSE_Y, 0.0F, 0.0F, HEADER_CLOSE_W, HEADER_CLOSE_H, HEADER_CLOSE_W, HEADER_CLOSE_H, HEADER_CLOSE_W, HEADER_CLOSE_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_UP, this.leftPos + SCROLL_BUTTON_X, this.topPos + SCROLL_UP_Y, 0.0F, 0.0F, SCROLL_BUTTON_W, SCROLL_BUTTON_H, SCROLL_BUTTON_W, SCROLL_BUTTON_H, SCROLL_BUTTON_W, SCROLL_BUTTON_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_DOWN, this.leftPos + SCROLL_BUTTON_X, this.topPos + SCROLL_DOWN_Y, 0.0F, 0.0F, SCROLL_BUTTON_W, SCROLL_BUTTON_H, SCROLL_BUTTON_W, SCROLL_BUTTON_H, SCROLL_BUTTON_W, SCROLL_BUTTON_H);
	}

	private Identifier footerButtonIcon(BankButton button) {
		if (button.slotId() != DEPOSIT_INVENTORY_SLOT_ID && button.slotId() != DEPOSIT_EQUIPMENT_SLOT_ID) {
			return button.icon();
		}

		Slot slot = this.controlSlot(button.slotId());
		if (slot == null || !slot.hasItem()) {
			return button.icon();
		}

		String itemPath = BuiltInRegistries.ITEM.getKey(slot.getItem().getItem()).getPath();
		int damageValue = slot.getItem().getDamageValue();
		if ("wooden_shovel".equals(itemPath)) {
			if (damageValue == 34) {
				return DEPOSIT_INVENTORY_ICON;
			}
			if (damageValue == 36) {
				return DEPOSIT_EQUIPMENT_ICON;
			}
		}
		if ("stone_hoe".equals(itemPath)) {
			if (damageValue == 127 || damageValue == 128) {
				return COAL_BAG_ICON;
			}
			if (damageValue == 129 || damageValue == 130) {
				return LARGE_POUCH_ICON;
			}
			if (damageValue == 131) {
				return HERB_SACK_ICON;
			}
		}
		return button.icon();
	}

	private Identifier footerButtonOverlay(BankButton button) {
		if (button.slotId() != DEPOSIT_INVENTORY_SLOT_ID && button.slotId() != DEPOSIT_EQUIPMENT_SLOT_ID) {
			return null;
		}

		Slot slot = this.controlSlot(button.slotId());
		if (slot == null || !slot.hasItem()) {
			return null;
		}

		String itemPath = BuiltInRegistries.ITEM.getKey(slot.getItem().getItem()).getPath();
		if (!"stone_hoe".equals(itemPath)) {
			return null;
		}

		return switch (slot.getItem().getDamageValue()) {
			case 127, 129 -> GREEN_UP_DOUBLE_CHEVRON;
			case 128, 130 -> RED_DOWN_DOUBLE_CHEVRON;
			default -> null;
		};
	}

	private Identifier damageBasedButtonBackground(int slotId, int tileDamage, int selectedDamage) {
		Slot slot = this.controlSlot(slotId);
		if (slot == null || !slot.hasItem()) {
			return FOOTER_BUTTON_BG;
		}

		int damageValue = slot.getItem().getDamageValue();
		if (damageValue == selectedDamage) {
			return FOOTER_BUTTON_BG_SELECTED;
		}
		if (damageValue == tileDamage) {
			return FOOTER_BUTTON_BG;
		}
		return FOOTER_BUTTON_BG;
	}

	private Slot controlSlot(int slotId) {
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return null;
		}
		return this.menu.slots.get(slotId);
	}

	private int resolveActiveTab() {
		for (int slotId = 0; slotId < Math.min(HIDDEN_TAB_SLOT_COUNT, this.menu.slots.size()); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slot != null && slot.hasItem() && slot.getItem().hasFoil()) {
				return slotId;
			}
		}
		return this.selectedTab;
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

	private record BankButton(int x, int y, int width, int height, Identifier icon, int slotId, Component fallbackTooltip, ButtonBackgroundMode backgroundMode) {
		private boolean contains(int mouseX, int mouseY) {
			return BankScreen.contains(mouseX, mouseY, this.x - 4, this.y - 4, BUTTON_BG_SIZE, BUTTON_BG_SIZE);
		}

		private void draw(GuiGraphicsExtractor graphics, int leftPos, int topPos, int mouseX, int mouseY, Font font, Identifier background, Identifier icon, Identifier overlay) {
			int drawX = leftPos + this.x;
			int drawY = topPos + this.y;
			graphics.blit(RenderPipelines.GUI_TEXTURED, background, drawX - 4, drawY - 4, 0.0F, 0.0F, BUTTON_BG_SIZE, BUTTON_BG_SIZE, BUTTON_BG_SIZE, BUTTON_BG_SIZE, BUTTON_BG_SIZE, BUTTON_BG_SIZE);
			graphics.blit(RenderPipelines.GUI_TEXTURED, icon, drawX, drawY+3, 0.0F, 0.0F, this.width, this.height, this.width, this.height, this.width, this.height);
			if (overlay != null) {
				graphics.blit(
					RenderPipelines.GUI_TEXTURED,
					overlay,
					drawX + this.width - BUTTON_OVERLAY_SIZE,
					drawY + 3,
					0.0F,
					0.0F,
					BUTTON_OVERLAY_SIZE,
					BUTTON_OVERLAY_SIZE,
					BUTTON_OVERLAY_SIZE,
					BUTTON_OVERLAY_SIZE,
					BUTTON_OVERLAY_SIZE,
					BUTTON_OVERLAY_SIZE
				);
			}
		}
	}

	private enum ButtonBackgroundMode {
		STATIC,
		PLACEHOLDERS,
		NOTED_ITEMS,
		INSERT_ITEMS
	}
}
