package com.zylr.client.screen.overridescreens;

import com.zylr.mixin.SlotAccessor;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class GrandExchangeMainScreen extends AbstractGrandExchangeScreen {
	private static final Identifier GE_MAKE_OFFER_BUY = texture("ge/make_offer_buy.png");
	private static final Identifier GE_MAKE_OFFER_BUY_HOVERED = texture("ge/make_offer_buy_hovered.png");
	private static final Identifier GE_MAKE_OFFER_SELL = texture("ge/make_offer_sell.png");
	private static final Identifier GE_MAKE_OFFER_SELL_HOVERED = texture("ge/make_offer_sell_hovered.png");
	private static final Identifier GE_BORDER_CORNER_TOP_LEFT = texture("ge/border_offer_corner_top_left.png");
	private static final Identifier GE_BORDER_CORNER_TOP_RIGHT = texture("ge/border_offer_corner_top_right.png");
	private static final Identifier GE_BORDER_CORNER_BOTTOM_LEFT = texture("ge/border_offer_corner_bottom_left.png");
	private static final Identifier GE_BORDER_CORNER_BOTTOM_RIGHT = texture("ge/border_offer_corner_bottom_right.png");
	private static final Identifier GE_BORDER_TOP = texture("ge/border_offer_top.png");
	private static final Identifier GE_BORDER_BOTTOM = texture("ge/border_offer_bottom.png");
	private static final Identifier GE_BORDER_LEFT = texture("ge/border_offer_left.png");
	private static final Identifier GE_BORDER_RIGHT = texture("ge/border_offer_right.png");
	private static final Identifier GE_BORDER_INTERSECTION_LEFT = texture("ge/border_offer_intersection_left.png");
	private static final Identifier GE_BORDER_INTERSECTION_RIGHT = texture("ge/border_offer_intersection_right.png");
	private static final Identifier COLLECT_BUTTON = texture("button/combat_style_thin.png");
	private static final Identifier COLLECT_BUTTON_HOVERED = texture("button/combat_style_thin_selected.png");
	private static final Identifier BACK_ARROW = texture("button/back_arrow.png");
	private static final Identifier BACK_ARROW_HOVERED = texture("button/back_arrow_hovered.png");
	private static final Identifier FORWARD_ARROW = texture("button/forward_arrow.png");
	private static final Identifier FORWARD_ARROW_HOVERED = texture("button/forward_arrow_hovered.png");
	private static final Identifier TUTORIAL_BUTTON = texture("button/tutorial.png");
	private static final Identifier TUTORIAL_BUTTON_HOVERED = texture("button/tutorial_hovered.png");
	private static final Identifier WORN_ITEMS = texture("minescape/rune_armour_set_lg.png");
	private static final int OFFER_GRID_X = 8;
	private static final int OFFER_GRID_Y = 52;
	private static final int OFFER_COLS = 4;
	private static final int OFFER_W = 86;
	private static final int OFFER_H = 82;
	private static final int OFFER_BORDER_TILE = 32;
	private static final int OFFER_BORDER_THICKNESS = 3;
	private static final int OFFER_TOP_EDGE_OFFSET_Y = 13;
	private static final int OFFER_BOTTOM_EDGE_OFFSET_Y = 17;
	private static final int OFFER_LEFT_EDGE_OFFSET_X = 13;
	private static final int OFFER_RIGHT_EDGE_OFFSET_X = 17;
	private static final int OFFER_GAP_X = 2;
	private static final int OFFER_GAP_Y = 6;
	private static final int OFFER_TITLE_Y = 10;
	private static final int OFFER_DIVIDER_Y = 24;
	private static final int OFFER_BOX_Y = 33;
	private static final int OFFER_BOX_W = 30;
	private static final int OFFER_BOX_H = 31;
	private static final int OFFER_LEFT_BOX_X = 10;
	private static final int OFFER_RIGHT_BOX_X = 46;
	private static final int SELL_BUY_BOX_X = 10;
	private static final int SELL_BUY_BOX_Y = 54;
	private static final int SELL_BUY_BOX_W = 67;
	private static final int SELL_BUY_BOX_H = 12;
	private static final int ACTIVE_ITEM_X = 10;
	private static final int ACTIVE_ITEM_Y = 32;
	private static final int ACTIVE_ITEM_NAME_X = 31;
	private static final int ACTIVE_ITEM_NAME_Y = 36;
	private static final int OFFER_ICON_DRAW_X = 3;
	private static final int OFFER_ICON_DRAW_Y = 3;
	private static final int HEADER_TUTORIAL_X = HEADER_CLOSE_X - 22;
	private static final int HEADER_TUTORIAL_Y = HEADER_CLOSE_Y + 1;
	private static final int HEADER_TUTORIAL_W = 17;
	private static final int HEADER_TUTORIAL_H = 17;
	private static final int COLLECT_BUTTON_X = SCREEN_WIDTH - 82;
	private static final int COLLECT_BUTTON_Y = 29;
	private static final int COLLECT_BUTTON_W = 72;
	private static final int COLLECT_BUTTON_H = 26;
	private static final int BACK_ARROW_W = 32;
	private static final int BACK_ARROW_H = 21;
	private static final int BACK_ARROW_X = 12;
	private static final int BACK_ARROW_Y = SCREEN_HEIGHT - BACK_ARROW_H - 8;
	private static final int ENTER_ARROW_W = 32;
	private static final int ENTER_ARROW_H = 21;
	private static final int ENTER_ARROW_X = SCREEN_WIDTH - ENTER_ARROW_W - 12;
	private static final int ENTER_ARROW_Y = SCREEN_HEIGHT - ENTER_ARROW_H - 8;
	private static final int WORN_ITEMS_W = 18;
	private static final int WORN_ITEMS_H = 18;
	private static final int WORN_ITEMS_X = ENTER_ARROW_X - WORN_ITEMS_W - 4;
	private static final int WORN_ITEMS_Y = SCREEN_HEIGHT - WORN_ITEMS_H - 9;
	private static final int SLOT_COUNT = 8;
	private static final int[] OFFER_PANEL_SLOT_IDS = {0, 3, 6, 18, 21, 24, 36, 39};
	private static final int SLOT_COLLECT = 42;
	private static final int SLOT_WORN_ITEMS = 44;
	private static final int SLOT_BACK_ARROW = 51;
	private static final int SLOT_TUTORIAL = 52;
	private static final int SLOT_ENTER_ARROW = 53;
	private static final int OFFER_TEXT_COLOR = 0xFFF6A230;
	private static final int SELL_BUY_BOX_FILL_COLOR = 0xFF171411;
	private static final int SELL_BUY_BOX_INNER_FILL_COLOR = 0xFF383128;
	private static final int ITEM_NAME_TEXT_COLOR = 0xFFD7C6A3;
	private static final int PROGRESS_YELLOW_COLOR = 0xFFD88020;
	private static final int PROGRESS_GREEN_COLOR = 0xFF005F00;
	private static final int COLLECT_REFRESH_POLL_TICKS = 20;
	private static final int PANEL_TRANSITION_FREEZE_TICKS = 20;
	private int pendingOfferRefreshTicks;
	private String pendingOfferRefreshSignature;
	private final boolean[] forceEmptyOfferPanels = new boolean[SLOT_COUNT];
	private final boolean[] pendingCollectedOfferPanels = new boolean[SLOT_COUNT];
	private int pendingPanelTransitionFreezeTicks;
	private final ItemStack[] frozenOfferPanelStacks = new ItemStack[SLOT_COUNT];
	private final int[] frozenOfferPanelStateDamage = new int[SLOT_COUNT];
	private final int[] frozenOfferPanelProgressDamage = new int[SLOT_COUNT];
	private final boolean[] frozenOfferPanelEmpty = new boolean[SLOT_COUNT];

	public GrandExchangeMainScreen(ChestMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
		for (int panelIndex = 0; panelIndex < SLOT_COUNT; panelIndex++) {
			this.frozenOfferPanelStacks[panelIndex] = ItemStack.EMPTY;
			this.frozenOfferPanelStateDamage[panelIndex] = -1;
			this.frozenOfferPanelProgressDamage[panelIndex] = -1;
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.syncToActiveGrandExchangeMenuDuringCollectRefresh()) {
			return;
		}
		if (this.pendingPanelTransitionFreezeTicks > 0) {
			this.pendingPanelTransitionFreezeTicks--;
		}
		if (this.pendingOfferRefreshTicks > 0) {
			this.pendingOfferRefreshTicks--;
			String currentSignature = this.chestContentsSignature();
			if (!currentSignature.equals(this.pendingOfferRefreshSignature)) {
				this.applyPendingCollectedOfferPanels();
				this.pendingOfferRefreshTicks = 0;
				this.pendingOfferRefreshSignature = null;
				this.layoutSlots();
				return;
			}
			if (this.pendingOfferRefreshTicks == 0) {
				this.pendingOfferRefreshSignature = null;
				this.clearPendingCollectedOfferPanels();
			}
		}
	}

	@Override
	protected void layoutSlots() {
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			int panelIndex = this.panelIndexForSlotId(slotId);
			if (panelIndex >= 0) {
				int col = panelIndex % OFFER_COLS;
				int row = panelIndex / OFFER_COLS;
				int slotCenterX = OFFER_GRID_X + col * (OFFER_W + OFFER_GAP_X) + OFFER_W / 2;
				int slotCenterY = OFFER_GRID_Y + row * (OFFER_H + OFFER_GAP_Y) + OFFER_H / 2 + 4;
				((SlotAccessor) slot).minescapeaddon$setX(slotCenterX);
				((SlotAccessor) slot).minescapeaddon$setY(slotCenterY);
			} else {
				((SlotAccessor) slot).minescapeaddon$setX(-1000);
				((SlotAccessor) slot).minescapeaddon$setY(-1000);
			}
		}
	}

	@Override
	protected void drawContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		this.drawHeaderButtons(graphics, mouseX, mouseY);
		for (int index = 0; index < SLOT_COUNT; index++) {
			int x = this.leftPos + this.offerX(index);
			int y = this.topPos + this.offerY(index);
			this.drawOfferPanel(graphics, x, y, index, mouseX, mouseY);
		}
		this.drawHoverTooltips(graphics, mouseX, mouseY);
	}

	@Override
	protected Slot findInteractiveSlot(int mouseX, int mouseY) {
		for (int panelIndex = 0; panelIndex < SLOT_COUNT; panelIndex++) {
			int x = this.leftPos + this.offerX(panelIndex);
			int y = this.topPos + this.offerY(panelIndex);
			if (contains(mouseX, mouseY, x, y, OFFER_W, OFFER_H)) {
				if (this.isOfferPanelEmpty(panelIndex)
					&& contains(mouseX, mouseY, x + OFFER_RIGHT_BOX_X, y + OFFER_BOX_Y, OFFER_BOX_W, OFFER_BOX_H)) {
					return this.offerStateSlot(panelIndex);
				}
				return this.offerPanelSlot(panelIndex);
			}
		}
		return null;
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		int mouseX = (int) event.x();
		int mouseY = (int) event.y();
		int button = event.button();
		if (this.clickCollectButton(mouseX, mouseY, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + BACK_ARROW_X, this.topPos + BACK_ARROW_Y, BACK_ARROW_W, BACK_ARROW_H, SLOT_BACK_ARROW, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + WORN_ITEMS_X, this.topPos + WORN_ITEMS_Y, WORN_ITEMS_W, WORN_ITEMS_H, SLOT_WORN_ITEMS, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + ENTER_ARROW_X, this.topPos + ENTER_ARROW_Y, ENTER_ARROW_W, ENTER_ARROW_H, SLOT_ENTER_ARROW, button)) {
			return true;
		}
		Slot panelSlot = this.findInteractiveSlot(mouseX, mouseY);
		if (panelSlot != null) {
			this.freezeOfferPanelsForTransition();
			long window = this.minecraft != null ? this.minecraft.getWindow().handle() : 0L;
			boolean shift = window != 0L && (
				GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
					|| GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS
			);
			int slotId = this.slotId(panelSlot);
			if (slotId >= 0) {
				ContainerInput clickType = shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP;
				this.sendMenuClick(slotId, button, clickType);
				return true;
			}
		}
		return super.mouseClicked(event, doubleClick);
	}

	private boolean clickCollectButton(int mouseX, int mouseY, int button) {
		if (!this.clickMappedSlot(mouseX, mouseY, this.leftPos + COLLECT_BUTTON_X, this.topPos + COLLECT_BUTTON_Y, COLLECT_BUTTON_W, COLLECT_BUTTON_H, SLOT_COLLECT, button)) {
			return false;
		}
		this.pendingOfferRefreshSignature = this.chestContentsSignature();
		this.capturePendingCollectedOfferPanels();
		this.pendingOfferRefreshTicks = COLLECT_REFRESH_POLL_TICKS;
		return true;
	}

	private boolean syncToActiveGrandExchangeMenuDuringCollectRefresh() {
		if (this.pendingOfferRefreshTicks <= 0) {
			return false;
		}
		if (this.minecraft == null || this.minecraft.player == null) {
			return false;
		}
		if (!(this.minecraft.player.containerMenu instanceof ChestMenu chestMenu)) {
			return false;
		}
		if (chestMenu == this.menu) {
			return false;
		}
		if (!this.title.getString().toLowerCase().contains("grand exchange")) {
			return false;
		}
		this.minecraft.setScreen(new GrandExchangeMainScreen(chestMenu, this.inventory, this.title));
		return true;
	}

	private String chestContentsSignature() {
		StringBuilder signature = new StringBuilder();
		int slotCount = Math.min(this.chestSlotCount(), this.menu.slots.size());
		for (int slotId = 0; slotId < slotCount; slotId++) {
			ItemStack stack = this.menu.slots.get(slotId).getItem();
			if (stack.isEmpty()) {
				signature.append(slotId).append(":empty;");
				continue;
			}
			signature.append(slotId)
				.append(':')
				.append(stack.getItem())
				.append(':')
				.append(stack.getCount())
				.append(':')
				.append(stack.getDamageValue())
				.append(';');
		}
		return signature.toString();
	}

	private void drawOfferPanel(GuiGraphicsExtractor graphics, int x, int y, int index, int mouseX, int mouseY) {
		if (this.pendingPanelTransitionFreezeTicks > 0) {
			this.drawFrozenOfferPanel(graphics, x, y, index, mouseX, mouseY);
			return;
		}
		if (this.isOfferPanelEmpty(index)) {
			this.drawEmptyOfferPanel(graphics, x, y, mouseX, mouseY);
			return;
		}

		Slot offerSlot = this.offerPanelSlot(index);
		Slot stateSlot = this.offerStateSlot(index);
		int damageValue = stateSlot != null && stateSlot.hasItem() ? stateSlot.getItem().getDamageValue() : -1;
		Slot progressSlot = this.offerProgressSlot(index);
		ItemStack displayStack = offerSlot.getItem().copy();
		if (damageValue == 2) {
			this.drawSellOfferPanel(graphics, x, y, displayStack, progressSlot);
			return;
		}
		if (damageValue == 1) {
			this.drawBuyOfferPanel(graphics, x, y, displayStack, progressSlot);
			return;
		}

		this.drawEmptyOfferPanel(graphics, x, y, mouseX, mouseY);
	}

	private void drawEmptyOfferPanel(GuiGraphicsExtractor graphics, int x, int y, int mouseX, int mouseY) {
		this.drawOfferBorder(graphics, x, y, OFFER_W, OFFER_H);
		String label = "Empty";
		int titleX = x + (OFFER_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, titleX, y + OFFER_TITLE_Y, OFFER_TEXT_COLOR, false);
		this.drawOfferDivider(graphics, x, y + OFFER_DIVIDER_Y, OFFER_W);

		int leftBoxX = x + OFFER_LEFT_BOX_X;
		int rightBoxX = x + OFFER_RIGHT_BOX_X;
		int boxY = y + OFFER_BOX_Y;
		boolean leftHovered = contains(mouseX, mouseY, leftBoxX, boxY, OFFER_BOX_W, OFFER_BOX_H);
		boolean rightHovered = contains(mouseX, mouseY, rightBoxX, boxY, OFFER_BOX_W, OFFER_BOX_H);

		this.drawOfferBox(graphics, leftHovered ? GE_MAKE_OFFER_BUY_HOVERED : GE_MAKE_OFFER_BUY, leftBoxX, boxY);
		this.drawOfferBox(graphics, rightHovered ? GE_MAKE_OFFER_SELL_HOVERED : GE_MAKE_OFFER_SELL, rightBoxX, boxY);
	}

	private void drawSellOfferPanel(GuiGraphicsExtractor graphics, int x, int y, ItemStack stack, Slot progressSlot) {
		int progressDamage = progressSlot != null && progressSlot.hasItem() ? progressSlot.getItem().getDamageValue() : -1;
		this.drawSellOfferPanel(graphics, x, y, stack, progressDamage);
	}

	private void drawSellOfferPanel(GuiGraphicsExtractor graphics, int x, int y, ItemStack stack, int progressDamage) {
		this.drawOfferBorder(graphics, x, y, OFFER_W, OFFER_H);
		String label = "Sell";
		int titleX = x + (OFFER_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, titleX, y + OFFER_TITLE_Y, OFFER_TEXT_COLOR, false);
		this.drawOfferDivider(graphics, x, y + OFFER_DIVIDER_Y, OFFER_W);
		this.drawOfferPanelItem(graphics, stack, x + ACTIVE_ITEM_X, y + ACTIVE_ITEM_Y);
		this.drawOfferPanelItemName(graphics, stack, x + ACTIVE_ITEM_NAME_X, y + ACTIVE_ITEM_NAME_Y);
		this.drawWideGreyBox(graphics, x + SELL_BUY_BOX_X, y + SELL_BUY_BOX_Y, SELL_BUY_BOX_W, SELL_BUY_BOX_H, progressDamage);
	}

	private void drawBuyOfferPanel(GuiGraphicsExtractor graphics, int x, int y, ItemStack stack, Slot progressSlot) {
		int progressDamage = progressSlot != null && progressSlot.hasItem() ? progressSlot.getItem().getDamageValue() : -1;
		this.drawBuyOfferPanel(graphics, x, y, stack, progressDamage);
	}

	private void drawBuyOfferPanel(GuiGraphicsExtractor graphics, int x, int y, ItemStack stack, int progressDamage) {
		this.drawOfferBorder(graphics, x, y, OFFER_W, OFFER_H);
		String label = "Buy";
		int titleX = x + (OFFER_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, titleX, y + OFFER_TITLE_Y, OFFER_TEXT_COLOR, false);
		this.drawOfferDivider(graphics, x, y + OFFER_DIVIDER_Y, OFFER_W);
		this.drawOfferPanelItem(graphics, stack, x + ACTIVE_ITEM_X, y + ACTIVE_ITEM_Y);
		this.drawOfferPanelItemName(graphics, stack, x + ACTIVE_ITEM_NAME_X, y + ACTIVE_ITEM_NAME_Y);
		this.drawWideGreyBox(graphics, x + SELL_BUY_BOX_X, y + SELL_BUY_BOX_Y, SELL_BUY_BOX_W, SELL_BUY_BOX_H, progressDamage);
	}

	private void drawOfferBox(GuiGraphicsExtractor graphics, Identifier iconTexture, int x, int y) {
		this.drawDoubleLineBox(graphics, x, y, OFFER_BOX_W, OFFER_BOX_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, iconTexture, x + OFFER_ICON_DRAW_X, y + OFFER_ICON_DRAW_Y, 0.0F, 0.0F, 25, 25, 25, 25, 25, 25);
	}

	private void drawWideGreyBox(GuiGraphicsExtractor graphics, int x, int y, int width, int height, Slot progressSlot) {
		graphics.fill(x, y, x + width, y + height, SELL_BUY_BOX_FILL_COLOR);
		graphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, SELL_BUY_BOX_INNER_FILL_COLOR);
		int damageValue = progressSlot != null && progressSlot.hasItem() ? progressSlot.getItem().getDamageValue() : -1;
		this.drawProgressFill(graphics, x, y, width, height, damageValue);
		this.drawDoubleLineBox(graphics, x, y, width, height);
	}

	private void drawWideGreyBox(GuiGraphicsExtractor graphics, int x, int y, int width, int height, int progressDamage) {
		graphics.fill(x, y, x + width, y + height, SELL_BUY_BOX_FILL_COLOR);
		graphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, SELL_BUY_BOX_INNER_FILL_COLOR);
		this.drawProgressFill(graphics, x, y, width, height, progressDamage);
		this.drawDoubleLineBox(graphics, x, y, width, height);
	}

	private void drawProgressFill(GuiGraphicsExtractor graphics, int x, int y, int width, int height, int damageValue) {
		float percent = switch (damageValue) {
			case 14 -> 0.0F;
			case 13 -> 0.1F;
			case 12 -> 0.2F;
			case 11 -> 0.3F;
			case 10 -> 0.4F;
			case 9 -> 0.5F;
			case 8 -> 0.6F;
			case 7 -> 0.7F;
			case 6 -> 0.8F;
			case 5 -> 0.9F;
			case 4 -> 1.0F;
			default -> -1.0F;
		};
		if (percent < 0.0F) {
			return;
		}

		int innerWidth = Math.max(0, width - 2);
		int fillWidth = Math.round(innerWidth * percent);
		if (fillWidth <= 0) {
			return;
		}

		int color = damageValue == 4 ? PROGRESS_GREEN_COLOR : PROGRESS_YELLOW_COLOR;
		graphics.fill(x + 1, y + 1, x + 1 + fillWidth, y + height - 1, color);
	}

	private void drawFrozenOfferPanel(GuiGraphicsExtractor graphics, int x, int y, int index, int mouseX, int mouseY) {
		if (index < 0 || index >= SLOT_COUNT || this.frozenOfferPanelEmpty[index] || this.frozenOfferPanelStacks[index].isEmpty()) {
			this.drawEmptyOfferPanel(graphics, x, y, mouseX, mouseY);
			return;
		}
		ItemStack displayStack = this.frozenOfferPanelStacks[index];
		int stateDamage = this.frozenOfferPanelStateDamage[index];
		int progressDamage = this.frozenOfferPanelProgressDamage[index];
		if (stateDamage == 2) {
			this.drawSellOfferPanel(graphics, x, y, displayStack, progressDamage);
			return;
		}
		if (stateDamage == 1) {
			this.drawBuyOfferPanel(graphics, x, y, displayStack, progressDamage);
			return;
		}
		this.drawEmptyOfferPanel(graphics, x, y, mouseX, mouseY);
	}

	private void drawOfferPanelItem(GuiGraphicsExtractor graphics, ItemStack stack, int x, int y) {
		graphics.item(stack, x, y);
	}

	private void drawOfferPanelItemName(GuiGraphicsExtractor graphics, ItemStack stack, int x, int y) {
		if (stack.isEmpty()) {
			return;
		}
		int maxWidth = Math.max(0, OFFER_W - ACTIVE_ITEM_NAME_X - 2);
		String text = this.font.plainSubstrByWidth(stack.getHoverName().getString(), maxWidth);
		if (!text.isEmpty()) {
			graphics.text(this.font, text, x, y, ITEM_NAME_TEXT_COLOR, false);
		}
	}

	private void drawOfferDivider(GuiGraphicsExtractor graphics, int x, int y, int width) {
		this.tileFill(graphics, GE_BORDER_TOP, x, y - OFFER_TOP_EDGE_OFFSET_Y - 1, Math.max(0, width), OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, GE_BORDER_INTERSECTION_LEFT, x, y - OFFER_BORDER_TILE / 2, 0.0F, 0.0F, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, GE_BORDER_INTERSECTION_RIGHT, x + width - OFFER_BORDER_TILE, y - OFFER_BORDER_TILE / 2, 0.0F, 0.0F, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
	}

	private void drawOfferBorder(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		int horizontalSpan = Math.max(0, width - OFFER_BORDER_TILE * 2);
		int verticalSpan = Math.max(0, height - OFFER_BORDER_TILE * 2);
		this.tileFill(graphics, GE_BORDER_TOP, x + OFFER_BORDER_TILE, y - OFFER_TOP_EDGE_OFFSET_Y, horizontalSpan, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		this.tileFill(graphics, GE_BORDER_BOTTOM, x + OFFER_BORDER_TILE, y + height - OFFER_BOTTOM_EDGE_OFFSET_Y - OFFER_BORDER_THICKNESS, horizontalSpan, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		this.tileFill(graphics, GE_BORDER_LEFT, x - OFFER_LEFT_EDGE_OFFSET_X, y + OFFER_BORDER_TILE, OFFER_BORDER_TILE, verticalSpan, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		this.tileFill(graphics, GE_BORDER_RIGHT, x + width - OFFER_RIGHT_EDGE_OFFSET_X - OFFER_BORDER_THICKNESS, y + OFFER_BORDER_TILE, OFFER_BORDER_TILE, verticalSpan, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, GE_BORDER_CORNER_TOP_LEFT, x, y, 0.0F, 0.0F, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, GE_BORDER_CORNER_TOP_RIGHT, x + width - OFFER_BORDER_TILE, y, 0.0F, 0.0F, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, GE_BORDER_CORNER_BOTTOM_LEFT, x, y + height - OFFER_BORDER_TILE, 0.0F, 0.0F, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, GE_BORDER_CORNER_BOTTOM_RIGHT, x + width - OFFER_BORDER_TILE, y + height - OFFER_BORDER_TILE, 0.0F, 0.0F, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE, OFFER_BORDER_TILE);
	}

	private void drawHeaderButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int tutorialX = this.leftPos + HEADER_TUTORIAL_X;
		int tutorialY = this.topPos + HEADER_TUTORIAL_Y;
		Identifier tutorialTexture = contains(mouseX, mouseY, tutorialX, tutorialY, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H)
			? TUTORIAL_BUTTON_HOVERED
			: TUTORIAL_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, tutorialTexture, tutorialX, tutorialY, 0.0F, 0.0F, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H);

		int collectX = this.leftPos + COLLECT_BUTTON_X;
		int collectY = this.topPos + COLLECT_BUTTON_Y;
		Identifier collectTexture = contains(mouseX, mouseY, collectX, collectY, COLLECT_BUTTON_W, COLLECT_BUTTON_H)
			? COLLECT_BUTTON_HOVERED
			: COLLECT_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, collectTexture, collectX, collectY, 0.0F, 0.0F, COLLECT_BUTTON_W, COLLECT_BUTTON_H, COLLECT_BUTTON_W, COLLECT_BUTTON_H, COLLECT_BUTTON_W, COLLECT_BUTTON_H);
		int collectTextX = collectX + (COLLECT_BUTTON_W - this.font.width("Collect")) / 2;
		graphics.text(this.font, "Collect", collectTextX, collectY + 9, OFFER_TEXT_COLOR, false);

		int wornItemsX = this.leftPos + WORN_ITEMS_X;
		int wornItemsY = this.topPos + WORN_ITEMS_Y;
		graphics.blit(RenderPipelines.GUI_TEXTURED, WORN_ITEMS, wornItemsX, wornItemsY, 0.0F, 0.0F, WORN_ITEMS_W, WORN_ITEMS_H, WORN_ITEMS_W, WORN_ITEMS_H, WORN_ITEMS_W, WORN_ITEMS_H);

		int backArrowX = this.leftPos + BACK_ARROW_X;
		int backArrowY = this.topPos + BACK_ARROW_Y;
		Identifier backArrowTexture = contains(mouseX, mouseY, backArrowX, backArrowY, BACK_ARROW_W, BACK_ARROW_H)
			? BACK_ARROW_HOVERED
			: BACK_ARROW;
		graphics.blit(RenderPipelines.GUI_TEXTURED, backArrowTexture, backArrowX, backArrowY, 0.0F, 0.0F, BACK_ARROW_W, BACK_ARROW_H, BACK_ARROW_W, BACK_ARROW_H, BACK_ARROW_W, BACK_ARROW_H);

		int enterArrowX = this.leftPos + ENTER_ARROW_X;
		int enterArrowY = this.topPos + ENTER_ARROW_Y;
		Identifier enterArrowTexture = contains(mouseX, mouseY, enterArrowX, enterArrowY, ENTER_ARROW_W, ENTER_ARROW_H)
			? FORWARD_ARROW_HOVERED
			: FORWARD_ARROW;
		graphics.blit(RenderPipelines.GUI_TEXTURED, enterArrowTexture, enterArrowX, enterArrowY, 0.0F, 0.0F, ENTER_ARROW_W, ENTER_ARROW_H, ENTER_ARROW_W, ENTER_ARROW_H, ENTER_ARROW_W, ENTER_ARROW_H);
	}

	private void drawHoverTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		if (contains(mouseX, mouseY, this.leftPos + HEADER_TUTORIAL_X, this.topPos + HEADER_TUTORIAL_Y, HEADER_TUTORIAL_W, HEADER_TUTORIAL_H)) {
			Slot tutorialSlot = this.slotById(SLOT_TUTORIAL);
			if (tutorialSlot != null && tutorialSlot.hasItem()) {
				graphics.setTooltipForNextFrame(this.font, tutorialSlot.getItem(), mouseX, mouseY);
			}
			return;
		}
		if (contains(mouseX, mouseY, this.leftPos + WORN_ITEMS_X, this.topPos + WORN_ITEMS_Y, WORN_ITEMS_W, WORN_ITEMS_H)) {
			Slot wornItemsSlot = this.slotById(SLOT_WORN_ITEMS);
			if (wornItemsSlot != null && wornItemsSlot.hasItem()) {
				graphics.setTooltipForNextFrame(this.font, wornItemsSlot.getItem(), mouseX, mouseY);
			}
		}
	}

	private boolean clickMappedSlot(int mouseX, int mouseY, int x, int y, int width, int height, int slotId, int button) {
		if (!contains(mouseX, mouseY, x, y, width, height)) {
			return false;
		}
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return true;
		}
		this.sendMenuClick(slotId, button, ContainerInput.PICKUP);
		return true;
	}

	private Slot slotById(int slotId) {
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return null;
		}
		return this.menu.slots.get(slotId);
	}

	private int offerX(int panelIndex) {
		return OFFER_GRID_X + (panelIndex % OFFER_COLS) * (OFFER_W + OFFER_GAP_X);
	}

	private int offerY(int panelIndex) {
		return OFFER_GRID_Y + (panelIndex / OFFER_COLS) * (OFFER_H + OFFER_GAP_Y);
	}

	private int panelIndexForSlotId(int slotId) {
		for (int index = 0; index < OFFER_PANEL_SLOT_IDS.length; index++) {
			if (OFFER_PANEL_SLOT_IDS[index] == slotId) {
				return index;
			}
		}
		return -1;
	}

	private Slot offerPanelSlot(int panelIndex) {
		if (panelIndex < 0 || panelIndex >= OFFER_PANEL_SLOT_IDS.length) {
			return null;
		}
		int slotId = OFFER_PANEL_SLOT_IDS[panelIndex];
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return null;
		}
		return this.menu.slots.get(slotId);
	}

	private Slot offerStateSlot(int panelIndex) {
		if (panelIndex < 0 || panelIndex >= OFFER_PANEL_SLOT_IDS.length) {
			return null;
		}
		int slotId = OFFER_PANEL_SLOT_IDS[panelIndex] + 11;
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return null;
		}
		return this.menu.slots.get(slotId);
	}

	private Slot offerProgressSlot(int panelIndex) {
		if (panelIndex < 0 || panelIndex >= OFFER_PANEL_SLOT_IDS.length) {
			return null;
		}
		int slotId = OFFER_PANEL_SLOT_IDS[panelIndex] + 10;
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return null;
		}
		return this.menu.slots.get(slotId);
	}

	private boolean isOfferPanelEmpty(int panelIndex) {
		if (panelIndex < 0 || panelIndex >= SLOT_COUNT) {
			return true;
		}
		if (this.forceEmptyOfferPanels[panelIndex]) {
			return true;
		}
		Slot offerSlot = this.offerPanelSlot(panelIndex);
		return offerSlot == null || !offerSlot.hasItem();
	}

	private void capturePendingCollectedOfferPanels() {
		this.clearPendingCollectedOfferPanels();
		for (int panelIndex = 0; panelIndex < SLOT_COUNT; panelIndex++) {
			Slot offerSlot = this.offerPanelSlot(panelIndex);
			Slot progressSlot = this.offerProgressSlot(panelIndex);
			if (offerSlot == null || !offerSlot.hasItem() || progressSlot == null || !progressSlot.hasItem()) {
				continue;
			}
			if (progressSlot.getItem().getDamageValue() == 4) {
				this.pendingCollectedOfferPanels[panelIndex] = true;
			}
		}
	}

	private void applyPendingCollectedOfferPanels() {
		for (int panelIndex = 0; panelIndex < SLOT_COUNT; panelIndex++) {
			if (this.pendingCollectedOfferPanels[panelIndex]) {
				this.forceEmptyOfferPanels[panelIndex] = true;
			}
		}
		this.clearPendingCollectedOfferPanels();
	}

	private void clearPendingCollectedOfferPanels() {
		for (int panelIndex = 0; panelIndex < SLOT_COUNT; panelIndex++) {
			this.pendingCollectedOfferPanels[panelIndex] = false;
		}
	}

	private void freezeOfferPanelsForTransition() {
		this.pendingPanelTransitionFreezeTicks = PANEL_TRANSITION_FREEZE_TICKS;
		for (int panelIndex = 0; panelIndex < SLOT_COUNT; panelIndex++) {
			this.frozenOfferPanelEmpty[panelIndex] = this.isOfferPanelEmpty(panelIndex);
			Slot offerSlot = this.offerPanelSlot(panelIndex);
			this.frozenOfferPanelStacks[panelIndex] = offerSlot != null && offerSlot.hasItem() ? offerSlot.getItem().copy() : ItemStack.EMPTY;
			Slot stateSlot = this.offerStateSlot(panelIndex);
			this.frozenOfferPanelStateDamage[panelIndex] = stateSlot != null && stateSlot.hasItem() ? stateSlot.getItem().getDamageValue() : -1;
			Slot progressSlot = this.offerProgressSlot(panelIndex);
			this.frozenOfferPanelProgressDamage[panelIndex] = progressSlot != null && progressSlot.hasItem() ? progressSlot.getItem().getDamageValue() : -1;
		}
	}
}
