package com.zylr.client.screen.overridescreens;

import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudTab;
import com.zylr.mixin.SlotAccessor;
import com.zylr.utils.PrivateUseAsciiDecoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.TooltipFlag;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SlayerScreen extends Screen implements HudTabRestoringScreen {
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
	private static final Identifier WINDOW_CLOSE_BUTTON = texture("other/window_close_button.png");
	private static final Identifier WINDOW_CLOSE_BUTTON_HOVERED = texture("other/window_close_button_hovered.png");
	private static final Identifier THIN_BUTTON = texture("button/combat_style_thin.png");
	private static final Identifier THIN_BUTTON_SELECTED = texture("button/combat_style_thin_selected.png");
	private static final Identifier ARROW_UP = texture("scrollbar/arrow_up.png");
	private static final Identifier ARROW_DOWN = texture("scrollbar/arrow_down.png");
	private static final Identifier SCROLLBAR_TRACK = texture("scrollbar/thumb_middle_dark.png");
	private static final Identifier SCROLLBAR_THUMB_TOP = texture("scrollbar/thumb_top.png");
	private static final Identifier SCROLLBAR_THUMB_MIDDLE = texture("scrollbar/thumb_middle.png");
	private static final Identifier SCROLLBAR_THUMB_BOTTOM = texture("scrollbar/thumb_bottom.png");
	private static final Identifier ROUND_CHECK_BOX = texture("options/round_check_box.png");
	private static final Identifier ROUND_CHECK_BOX_CHECKED_GREEN = texture("options/round_check_box_checked_green.png");
	private static final int SCREEN_WIDTH = 365;
	private static final int SCREEN_HEIGHT = 260;
	private static final int SCREEN_Y_OFFSET = -16;
	private static final int OUTER_EDGE = 36;
	private static final int EDGE_OUTSET = 15;
	private static final int HEADER_TEXT_Y = 10;
	private static final int HEADER_CLOSE_X = SCREEN_WIDTH - 31;
	private static final int HEADER_CLOSE_Y = 7;
	private static final int HEADER_CLOSE_W = 22;
	private static final int HEADER_CLOSE_H = 19;
	private static final int HEADER_DIVIDER_Y = 12;
	private static final int HEADER_DIVIDER_H = OUTER_EDGE;
	private static final int TAB_Y = 32;
	private static final int TAB_WIDTH = 52;
	private static final int TAB_HEIGHT = 22;
	private static final int BUY_SLOT_START_X = 23;
	private static final int BUY_SLOT_START_Y = 65;
	private static final int BUY_SLOT_COLS = 9;
	private static final int BUY_SLOT_STEP = 36;
	private static final int BUY_ITEM_DRAW_SIZE = 32;
	private static final int BUY_HIDDEN_ROWS = 1;
	private static final int UNLOCK_LIST_TOP = 54;
	private static final int UNLOCK_LIST_LEFT = 6;
	private static final int UNLOCK_LIST_RIGHT = 347;
	private static final int UNLOCK_LIST_BOTTOM = 247;
	private static final int UNLOCK_CARD_WIDTH = 170;
	private static final int UNLOCK_CARD_HEIGHT = 64;
	private static final int UNLOCK_CARD_GAP_X = 2;
	private static final int UNLOCK_CARD_GAP_Y = 2;
	private static final int UNLOCK_SCROLL_X = 349;
	private static final int UNLOCK_SCROLL_Y = 75;
	private static final int UNLOCK_SCROLL_W = 12;
	private static final int UNLOCK_SCROLL_H = 145;
	private static final int UNLOCK_SCROLL_BUTTON_W = 12;
	private static final int UNLOCK_SCROLL_BUTTON_H = 13;
	private static final int UNLOCK_SCROLL_THUMB_H = 14;
	private static final int UNLOCK_SCROLL_STEP = 40;
	private static final int THIN_BUTTON_HITBOX_Y_INSET = 5;
	private static final int TASK_UNBLOCK_BUTTON_HITBOX_H = 12;
	private static final int TASK_ACTION_BUTTON_WIDTH = 51;
	private static final int TASK_ACTION_BUTTON_TEXTURE_HEIGHT = 22;
	private static final int TASK_ACTION_BUTTON_Y = 94;
	private static final int UNLOCK_ITEM_X = 6;
	private static final int UNLOCK_ITEM_Y = 6;
	private static final int UNLOCK_ITEM_SIZE = 20;
	private static final int UNLOCK_CHECKBOX_X = 30;
	private static final int UNLOCK_CHECKBOX_Y = 6;
	private static final int UNLOCK_CHECKBOX_SIZE = 13;
	private static final int UNLOCK_DETAIL_X = 79;
	private static final int UNLOCK_DETAIL_Y = 67;
	private static final int UNLOCK_DETAIL_W = 204;
	private static final int UNLOCK_DETAIL_H = 151;
	private static final int UNLOCK_DETAIL_BUTTON_W = 58;
	private static final int UNLOCK_DETAIL_BUTTON_H = 22;
	private static final int UNLOCK_DETAIL_BACK_X = 140;
	private static final int UNLOCK_DETAIL_CONFIRM_X = 223;
	private static final int UNLOCK_DETAIL_BUTTON_Y = 192;
	private static final int TOP_TEXT = 0xFFFFAC44;
	private static final int ORANGE_TEXT = 0xFFFFA73B;
	private static final int WHITE_TEXT = 0xFFFFFFFF;
	private static final int RED_TEXT = 0xFFFF4C4C;
	private static final int GREEN_TEXT = 0xFF7BCB63;
	private static final float ENTRY_TITLE_SCALE = 1.10F;
	private static final int WARNING_TEXT = 0xFFFFA73B;
	private static final int BORDER = 0xFF5E5647;
	private static final int UNLOCK_OUTER_BORDER = 0xFF2F2F2F;
	private static final int PANEL_OUTER_BORDER = 0xFF2F2F2F;
	private static final int PANEL_FILL = 0xCC4D4235;
	private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d[\\d,]*)");

	private static final List<TabSpec> TASK_TABS = List.of(
		new TabSpec("Unlock", false, 8, 1),
		new TabSpec("Extend", false, 60, 2),
		new TabSpec("Buy", false, 112, 3),
		new TabSpec("Tasks", true, 164, 4),
		new TabSpec("Shop", false, 216, 5)
	);

	private static final List<BlockedTaskRow> TASK_ROWS = List.of(
		new BlockedTaskRow("Slot 1:"),
		new BlockedTaskRow("Slot 2:"),
		new BlockedTaskRow("Slot 3:"),
		new BlockedTaskRow("Slot 4:"),
		new BlockedTaskRow("Slot 5:"),
		new BlockedTaskRow("Slot 6:"),
		new BlockedTaskRow("Slot 7:")
	);

	private static final List<UnlockCategory> UNLOCK_CATEGORIES = List.of(
		new UnlockCategory("Gargoyle Smasher", "Automatically smash Gargoyles when they're on critical health, if you have the right tool.", "120 points", false),
		new UnlockCategory("Slug Salter", "Automatically salt Rockslugs when they're on critical health, if you have salt.", "10 points", false),
		new UnlockCategory("Reptile Freezer", "Automatically freeze Desert Lizards when they're on critical health, if you have ice water.", "10 points", false),
		new UnlockCategory("'Shroom Sprayer", "Automatically spray Mutated Zygomites when they're on critical health, if you have fungicide.", "110 points", false),
		new UnlockCategory("Broader Fletching", "Learn to fletch broad arrows (with level 52 Fletching), broad bolts (with level 55 Fletching) and amethyst broad bolts (with level 76 Fletching).", "300 points", false),
		new UnlockCategory("Malevolent Masquerade", "Learn to combine the protective Slayer headgear and Slayer gem into one universal helmet, with level 55 Crafting.", "400 points", false),
		new UnlockCategory("Ring Bling", "Learn to craft your own Slayer Rings, with level 75 Crafting.", "150 points", false),
		new UnlockCategory("Seeing Red", "Konar, Duradel and Nieve will be able to assign Red Dragons as your task.", "50 points", false),
		new UnlockCategory("Watch the Birdie", "Konar, Duradel, Nieve, Chaeldar and Krystilia will be able to assign Aviansies as your task.", "80 points", false),
		new UnlockCategory("Hot Stuff", "Duradel, Nieve and Chaeldar will be able to assign TzHaar as your task. You may also be offered a chance to slay TzTok-Jad or TzKal-Zuk.", "100 points", false),
		new UnlockCategory("Reptile Got Ripped", "Konar, Duradel, Nieve and Chaeldar will be able to assign you Lizardmen.", "75 points", false),
		new UnlockCategory("Like a Boss", "Konar, Duradel, Nieve and Krystilia will be able to assign boss monsters as your task. They will choose which boss you must kill.", "200 points", false),
		new UnlockCategory("Bigger and Badder", "Increase the risk against certain Slayer monsters with the chance of a superior version spawning whilst on a Slayer task.", "150 points", false),
		new UnlockCategory("King Black Bonnet", "Learn how to combine a KBD head with your Slayer helmet to colour it black.", "1000 points", false),
		new UnlockCategory("Kalphite Khat", "Learn how to combine a Kalphite Queen head with your Slayer helmet to colour it green.", "1000 points", false),
		new UnlockCategory("Unholy Helmet", "Learn how to combine an Abyssal Demon head with your Slayer helmet to colour it red.", "1000 points", false),
		new UnlockCategory("Dark Mantle", "Learn how to combine a Dark Claw head with your Slayer helmet to colour it purple.", "1000 points", false),
		new UnlockCategory("Undead Head", "Learn how to combine Vorkath's head with your Slayer helmet to colour it turquoise.", "1000 points", false),
		new UnlockCategory("Use More Head", "Learn how to combine a Hydra head with your Slayer helmet to theme it like the Alchemical Hydra.", "1000 points", false),
		new UnlockCategory("Twisted Vision", "Learn how to combine Twisted Horns with your Slayer helmet to theme it like the Great Olm.", "1000 points", false),
		new UnlockCategory("Duly Noted", "Mithril Dragons drop mithril bars in banknote form while killed on assignment.", "200 points", false),
		new UnlockCategory("Stop the Wyvern", "Stops you getting Fossil Island Wyvern tasks, without counting towards your blocked task limit.", "500 points", false),
		new UnlockCategory("Double Trouble", "Slaying Dusk and Dawn now counts for two kills towards your task rather than one.", "500 points", false),
		new UnlockCategory("Basilocked", "Konar, Duradel and Nieve will be able to assign Basilisks as your task.", "80 points", false),
		new UnlockCategory("Actual Vampyre Slayer", "Konar, Duradel, Nieve and Chaeldar will be able to assign Vampyres as your task.", "80 points", false),
		new UnlockCategory("Task Storage", "Gain the ability to store your task, allowing you to take it at a later point in time.", "500 points", false)
	);

	private static final List<UnlockCategory> EXTEND_CATEGORIES = List.of(
			new UnlockCategory("Ankou Very Much", "Whenever you get a Ankou task, it will be a bigger task.", "100 points", false),
			new UnlockCategory("Fire & Darkness", "Whenever you get a Black Dragon task, it will be a bigger task.", "50 points", false),
			new UnlockCategory("Pedal to the Metals", "Whenever you get a Metal Dragon task, it will be a bigger task.", "200 points", false),
			new UnlockCategory("Greater Challenge", "Whenever you get a Greater Demon task, it will be a bigger task.", "100 points", false),
			new UnlockCategory("It's Dark in Here", "Whenever you get a Black Demon task, it will be a bigger task.", "100 points", false),
			new UnlockCategory("Bleed Me Dry", "Whenever you get a Bloodveld task, it will be a bigger task.", "75 points", false),
			new UnlockCategory("Smell Ya Later", "Whenever you get a Aberrant Spectre task, it will be a bigger task.", "100 points", false),
			new UnlockCategory("Get Smashed", "Whenever you get a Gargoyle task, it will be a bigger task.", "100 points", false),
			new UnlockCategory("Nechs Please", "Whenever you get a Nechryael task, it will be a bigger task.", "100 points", false),
			new UnlockCategory("Augment My Abbies", "Whenever you get a Abyssal Demon task, it will be a bigger task.", "100 points", false)
			//new UnlockCategory("Spiritual Fervour", "Whenever you get a Spiritual Creature task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Birds of a Feather", "Whenever you get an Aviansie task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Horrorific", "Whenever you get a Cave Horror task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("To Dust You Shall Return", "Whenever you get a Dust Devil task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Wyver-nother one", "Whenever you get a Skeletal Wyvern task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Need More Darkness", "Whenever you get a Dark Beast task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Suq-a-nother One", "Whenever you get a Suqah task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Krack On", "Whenever you get a Cave Kraken task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Get Scabaright on It", "Whenever you get a Scabarite task, it will be a bigger task.", "50 points", false),
			//new UnlockCategory("Wyver-nother Two", "Whenever you get a Fossil Island Wyvern task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Basilonger", "Whenever you get a Basilisk task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("More at Stake", "Whenever you get a Vampyre task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Revenenenenenants", "Whenever you get a Revenants task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("More eyes than sense", "Whenever you get an Araxyte task, it will be a bigger task.", "150 points", false),
			//new UnlockCategory("Un-restraining Order", "Whenever you get a Custodian Stalker task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Let's Stay All Aquanite", "Whenever you get an Aquanite task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Can of Wyrms", "Whenever you get an Wyrm task, it will be a bigger task.", "100 points", false),
			//new UnlockCategory("Gryphon and on", "Whenever you get a Gryphon task, it will be a bigger task.", "50 points", false),
			//new UnlockCategory("I see Dragons", "Whenever you get a Frost Dragon task, it will be a bigger task.", "100 points", false)
	);

	private final ChestMenu menu;
	private final Variant variant;
	private final List<String> decodedTitleSegments;
	private int leftPos;
	private int topPos;
	private int unlockScrollOffset;
	private int selectedUnlockIndex = -1;
	private int selectedUnlockSlotId = -1;
	private HudTab previousHudTab;

	public SlayerScreen(ChestMenu menu, Inventory inventory, Component title) {
		super(title);
		this.menu = menu;
		this.variant = Variant.fromTitle(title.getString());
		this.decodedTitleSegments = PrivateUseAsciiDecoder.decodeSegments(normalize(title.getString()));
	}

	@Override
	protected void init() {
		this.leftPos = (this.width - SCREEN_WIDTH) / 2;
		this.topPos = (this.height - SCREEN_HEIGHT) / 2 + SCREEN_Y_OFFSET;
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
		if (this.variant == Variant.BUY) {
			this.drawTabs(graphics);
			this.drawRewardPoints(graphics);
			this.drawBuyItems(graphics);
			this.drawBuyTooltips(graphics, mouseX, mouseY);
		} else if (this.variant == Variant.TASKS) {
			this.drawTabs(graphics);
			this.drawRewardPoints(graphics);
			this.drawDescription(graphics);
			this.drawCurrentAssignment(graphics, mouseX, mouseY);
			this.drawBlockedTasks(graphics, mouseX, mouseY);
			this.drawActionButtonTooltips(graphics, mouseX, mouseY);
		} else if (this.isRewardsListVariant()) {
			if (this.selectedUnlockIndex >= 0) {
				this.drawUnlockDetailScreen(graphics, mouseX, mouseY);
			} else {
				this.drawTabs(graphics);
				this.drawRewardPoints(graphics);
				this.drawUnlockScreen(graphics, mouseX, mouseY);
			}
		}
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
		if (this.handleTabClick(mouseX, mouseY)) {
			return true;
		}
		if (this.handleBuySlotClick(mouseX, mouseY, event.button(), shift)) {
			return true;
		}
		if (this.isRewardsListVariant()) {
			if (this.selectedUnlockIndex >= 0) {
				if (this.handleUnlockDetailClick(mouseX, mouseY)) {
					return true;
				}
			} else if (this.handleUnlockClick(mouseX, mouseY)) {
				return true;
			}
		}
		if (this.handleTaskActionClick(mouseX, mouseY)) {
			return true;
		}
		if (this.handleUnblockClick(mouseX, mouseY)) {
			return true;
		}
		if (this.handleHeaderClick(mouseX, mouseY)) {
			return true;
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
		if (this.isRewardsListVariant() && verticalAmount != 0.0D && this.scrollUnlock(verticalAmount > 0.0D ? -UNLOCK_SCROLL_STEP : UNLOCK_SCROLL_STEP)) {
			return true;
		}
		return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
	}

	private void layoutSlots() {
		if (this.variant == Variant.BUY) {
			int chestSlotCount = this.chestSlotCount();
			for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
				Slot slot = this.menu.slots.get(slotId);
				if (slotId >= BUY_SLOT_COLS * BUY_HIDDEN_ROWS && slotId < chestSlotCount) {
					int visibleIndex = slotId - BUY_SLOT_COLS * BUY_HIDDEN_ROWS;
					int row = visibleIndex / BUY_SLOT_COLS;
					int col = visibleIndex % BUY_SLOT_COLS;
					((SlotAccessor) slot).minescapeaddon$setX(BUY_SLOT_START_X + col * BUY_SLOT_STEP);
					((SlotAccessor) slot).minescapeaddon$setY(BUY_SLOT_START_Y + row * BUY_SLOT_STEP);
				} else {
					((SlotAccessor) slot).minescapeaddon$setX(-1000);
					((SlotAccessor) slot).minescapeaddon$setY(-1000);
				}
			}
			return;
		}

		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			((SlotAccessor) this.menu.slots.get(slotId)).minescapeaddon$setX(-1000);
			((SlotAccessor) this.menu.slots.get(slotId)).minescapeaddon$setY(-1000);
		}
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

	private void drawTitle(GuiGraphicsExtractor graphics) {
		this.drawCenteredText(graphics, this.variant.headerTitle(), 13, TOP_TEXT);
	}

	private void drawBuyItems(GuiGraphicsExtractor graphics) {
		int chestSlotCount = this.chestSlotCount();
		for (int slotId = BUY_SLOT_COLS * BUY_HIDDEN_ROWS; slotId < chestSlotCount; slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slot == null || !slot.hasItem()) {
				continue;
			}
			this.drawScaledItem(graphics, slot.getItem(), this.leftPos + slot.x, this.topPos + slot.y, BUY_ITEM_DRAW_SIZE);
		}
	}

	private void drawBuyTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		Slot slot = this.findBuySlot(mouseX, mouseY);
		if (slot != null && slot.hasItem()) {
			graphics.setTooltipForNextFrame(this.font, slot.getItem(), mouseX, mouseY);
		}
	}

	private void drawTabs(GuiGraphicsExtractor graphics) {
		for (TabSpec tab : TASK_TABS) {
			Identifier texture = this.isSelectedTab(tab) ? THIN_BUTTON_SELECTED : THIN_BUTTON;
			int drawX = this.leftPos + tab.x;
			int drawY = this.topPos + TAB_Y;
			graphics.blit(RenderPipelines.GUI_TEXTURED, texture, drawX, drawY, 0.0F, 0.0F, TAB_WIDTH, TAB_HEIGHT, 72, 36, 72, 36);
			this.drawCenteredText(graphics, tab.label, tab.x + TAB_WIDTH / 2, TAB_Y + 7, this.isSelectedTab(tab) ? TOP_TEXT : ORANGE_TEXT);
		}
	}

	private void drawRewardPoints(GuiGraphicsExtractor graphics) {
		String text = "Reward points: " + this.segmentOrFallback(1, this.variant.rewardPoints());
		int drawX = this.leftPos + 275;
		graphics.text(this.font, text, drawX, this.topPos + 38, TOP_TEXT, false);
	}

	private void drawDescription(GuiGraphicsExtractor graphics) {
		String cancelPoints = this.tooltipNumber(13, 0, "30");
		String blockPoints = this.tooltipNumber(13, 1, "80");
		int startY = 57;
		this.drawCenteredSegments(graphics, startY,
			new TextSegment("You may spend points to ", WARNING_TEXT),
			new TextSegment("Cancel", WHITE_TEXT),
			new TextSegment(" or ", WARNING_TEXT),
			new TextSegment("Block", WHITE_TEXT),
			new TextSegment(" your current task.", WARNING_TEXT)
		);
		this.drawCenteredSegments(graphics, startY + 10,
			new TextSegment("If you ", WARNING_TEXT),
			new TextSegment("cancel", WHITE_TEXT),
			new TextSegment(" it you may be assigned that target again in future. ", WARNING_TEXT),
			new TextSegment("(" + cancelPoints + " points)", RED_TEXT)
		);
		this.drawCenteredSegments(graphics, startY + 20,
			new TextSegment("If you ", WARNING_TEXT),
			new TextSegment("block", WHITE_TEXT),
			new TextSegment(" it, you'll not get that assignment again. ", WARNING_TEXT),
			new TextSegment("(" + blockPoints + " points)", RED_TEXT)
		);
		this.drawCenteredSegments(graphics, startY + 30,
			new TextSegment("You may also ", WARNING_TEXT),
			new TextSegment("store", WHITE_TEXT),
			new TextSegment(" your current task for later.", WARNING_TEXT)
		);
	}

	private void drawCurrentAssignment(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		String currentAssignmentRight = this.segmentOrFallback(5, this.variant.currentAssignmentRight());

		this.drawPanel(graphics, 12, 98, 125, 33);
		this.drawPanel(graphics, 146, 116, 207, 15);
		graphics.text(this.font, "Current assignment", this.leftPos + 34, this.topPos + 102, ORANGE_TEXT, false);

		graphics.centeredText(this.font, this.segmentOrFallback(4, this.variant.currentAssignmentLeft()), this.leftPos + 69, this.topPos + 119, WHITE_TEXT);
		graphics.centeredText(this.font, currentAssignmentRight, this.leftPos + 249, this.topPos + 119, WHITE_TEXT);

		this.drawActionButton(graphics, "Cancel", 171, 94, 51, 22, mouseX, mouseY);
		this.drawActionButton(graphics, "Block", 223, 94, 51, 22, mouseX, mouseY);
		this.drawActionButton(graphics, this.swapButtonLabel(currentAssignmentRight), 275, 94, 51, 22, mouseX, mouseY);
		this.drawActionButton(graphics, "View List", 327, 94, 51, 22, mouseX, mouseY);
	}

	private void drawBlockedTasks(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		if (this.variant != Variant.TASKS) {
			return;
		}
		this.drawCenteredText(graphics, "Blocked tasks", 137, ORANGE_TEXT);

		int startY = 151;
		int rowStep = 14;
		for (int i = 0; i < TASK_ROWS.size(); i++) {
			BlockedTaskRow row = TASK_ROWS.get(i);
			int textY = startY + i * rowStep;
			graphics.text(this.font, row.slotLabel(), this.leftPos + 34, this.topPos + textY, ORANGE_TEXT, false);
			this.drawCenteredText(graphics, this.blockedTaskName(i), 160, textY, WHITE_TEXT);
			this.drawActionButton(graphics, "Unblock task", 286, textY - 7, 72, 22, mouseX, mouseY);
		}
	}

	private void drawPanel(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		int left = this.leftPos + x;
		int top = this.topPos + y;
		graphics.fill(left - 1, top - 1, left + width + 1, top, PANEL_OUTER_BORDER);
		graphics.fill(left - 1, top + height, left + width + 1, top + height + 1, PANEL_OUTER_BORDER);
		graphics.fill(left - 1, top, left, top + height, PANEL_OUTER_BORDER);
		graphics.fill(left + width, top, left + width + 1, top + height, PANEL_OUTER_BORDER);
		graphics.fill(left, top, left + width, top + height, PANEL_FILL);
		graphics.fill(left, top, left + width, top + 1, BORDER);
		graphics.fill(left, top + height - 1, left + width, top + height, BORDER);
		graphics.fill(left, top, left + 1, top + height, BORDER);
		graphics.fill(left + width - 1, top, left + width, top + height, BORDER);
	}

	private void drawActionButton(GuiGraphicsExtractor graphics, String label, int centerX, int y, int width, int height, int mouseX, int mouseY) {
		int drawX = this.leftPos + centerX - width / 2;
		int drawY = this.topPos + y;
		boolean hovered = this.containsThinButton(mouseX, mouseY, drawX, drawY, width);
		graphics.blit(RenderPipelines.GUI_TEXTURED, THIN_BUTTON, drawX, drawY, 0.0F, 0.0F, width, height, 72, 36, 72, 36);
		this.drawCenteredText(graphics, label, centerX, y + 7, hovered ? WHITE_TEXT : ORANGE_TEXT);
	}

	private void drawThinButton(GuiGraphicsExtractor graphics, String label, int centerX, int y, int mouseX, int mouseY) {
		this.drawActionButton(graphics, label, centerX, y, UNLOCK_DETAIL_BUTTON_W, UNLOCK_DETAIL_BUTTON_H, mouseX, mouseY);
	}

	private void drawUnlockScreen(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		this.drawUnlockGrid(graphics);
		this.drawUnlockEntries(graphics, mouseX, mouseY);
		this.drawUnlockScrollbar(graphics, mouseX, mouseY);
		this.drawUnlockTooltips(graphics, mouseX, mouseY);
	}

	private void drawUnlockDetailScreen(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		UnlockCategory entry = this.selectedUnlock();
		if (entry == null) {
			return;
		}

		this.drawPanel(graphics, UNLOCK_DETAIL_X, UNLOCK_DETAIL_Y, UNLOCK_DETAIL_W, UNLOCK_DETAIL_H);
		Slot selectedSlot = this.menuSlot(this.selectedUnlockSlotId);
		if (selectedSlot != null && selectedSlot.hasItem()) {
			this.drawScaledItem(graphics, selectedSlot.getItem(), this.leftPos + UNLOCK_DETAIL_X + 16, this.topPos + UNLOCK_DETAIL_Y + 16, 20);
		}
		int centerX = this.leftPos + UNLOCK_DETAIL_X + UNLOCK_DETAIL_W / 2;
		graphics.centeredText(this.font, entry.title(), centerX, this.topPos + 104, TOP_TEXT);

		List<String> lines = this.wrapText(entry.description(), UNLOCK_DETAIL_W - 44, 5);
		for (int i = 0; i < lines.size(); i++) {
			graphics.centeredText(this.font, lines.get(i), centerX, this.topPos + 118 + i * 10, ORANGE_TEXT);
		}

		graphics.centeredText(this.font, "Pay " + entry.points() + "?", centerX, this.topPos + 118 + lines.size() * 10 + 10, RED_TEXT);
		this.drawThinButton(graphics, "Back", UNLOCK_DETAIL_BACK_X, UNLOCK_DETAIL_BUTTON_Y, mouseX, mouseY);
		this.drawThinButton(graphics, "Confirm", UNLOCK_DETAIL_CONFIRM_X, UNLOCK_DETAIL_BUTTON_Y, mouseX, mouseY);
	}

	private void drawUnlockGrid(GuiGraphicsExtractor graphics) {
	}

	private void drawUnlockEntries(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int clipLeft = this.leftPos + UNLOCK_LIST_LEFT;
		int clipTop = this.topPos + UNLOCK_LIST_TOP;
		int clipRight = this.leftPos + UNLOCK_LIST_RIGHT + 1;
		int clipBottom = this.topPos + UNLOCK_LIST_BOTTOM;
		graphics.enableScissor(clipLeft, clipTop, clipRight, clipBottom);
		try {
			int totalRows = this.unlockTotalRows();
			for (int row = 0; row < totalRows; row++) {
				int y = this.topPos + UNLOCK_LIST_TOP + row * (UNLOCK_CARD_HEIGHT + UNLOCK_CARD_GAP_Y) - this.unlockScrollOffset;
				if (y > this.topPos + UNLOCK_LIST_BOTTOM || y + UNLOCK_CARD_HEIGHT < this.topPos + UNLOCK_LIST_TOP) {
					continue;
				}
				for (int col = 0; col < 2; col++) {
					int index = row * 2 + col;
					if (index >= this.rewardCategories().size()) {
						break;
					}
					int x = this.leftPos + UNLOCK_LIST_LEFT + col * (UNLOCK_CARD_WIDTH + UNLOCK_CARD_GAP_X);
					boolean hovered = contains(mouseX, mouseY, x, y, UNLOCK_CARD_WIDTH, UNLOCK_CARD_HEIGHT);
					this.drawUnlockEntry(graphics, this.rewardCategories().get(index), index, x, y, hovered);
				}
			}
		} finally {
			graphics.disableScissor();
		}
	}

	private void drawUnlockEntry(GuiGraphicsExtractor graphics, UnlockCategory entry, int index, int x, int y, boolean hovered) {
		if (hovered) {
			this.tileFill(graphics, BACKGROUND_BRIGHTER, x + 1, y + 1, UNLOCK_CARD_WIDTH - 2, UNLOCK_CARD_HEIGHT - 2, 88, 60);
		}
		graphics.fill(x, y, x + UNLOCK_CARD_WIDTH, y + 1, UNLOCK_OUTER_BORDER);
		graphics.fill(x, y + UNLOCK_CARD_HEIGHT - 1, x + UNLOCK_CARD_WIDTH, y + UNLOCK_CARD_HEIGHT, UNLOCK_OUTER_BORDER);
		graphics.fill(x, y, x + 1, y + UNLOCK_CARD_HEIGHT, UNLOCK_OUTER_BORDER);
		graphics.fill(x + UNLOCK_CARD_WIDTH - 1, y, x + UNLOCK_CARD_WIDTH, y + UNLOCK_CARD_HEIGHT, UNLOCK_OUTER_BORDER);
		graphics.fill(x + 1, y + 1, x + UNLOCK_CARD_WIDTH - 1, y + 2, BORDER);
		graphics.fill(x + 1, y + UNLOCK_CARD_HEIGHT - 2, x + UNLOCK_CARD_WIDTH - 1, y + UNLOCK_CARD_HEIGHT - 1, BORDER);
		graphics.fill(x + 1, y + 1, x + 2, y + UNLOCK_CARD_HEIGHT - 1, BORDER);
		graphics.fill(x + UNLOCK_CARD_WIDTH - 2, y + 1, x + UNLOCK_CARD_WIDTH - 1, y + UNLOCK_CARD_HEIGHT - 1, BORDER);
		Slot itemSlot = this.unlockDisplaySlot(index);
		if (itemSlot != null && itemSlot.hasItem()) {
			this.drawScaledItem(graphics, itemSlot.getItem(), x + UNLOCK_ITEM_X, y + UNLOCK_ITEM_Y, UNLOCK_ITEM_SIZE);
		}
		Identifier checkbox = this.unlockCheckboxTexture(itemSlot);
		graphics.blit(RenderPipelines.GUI_TEXTURED, checkbox, x + UNLOCK_CHECKBOX_X, y + UNLOCK_CHECKBOX_Y, 0.0F, 0.0F, UNLOCK_CHECKBOX_SIZE, UNLOCK_CHECKBOX_SIZE, UNLOCK_CHECKBOX_SIZE, UNLOCK_CHECKBOX_SIZE, UNLOCK_CHECKBOX_SIZE, UNLOCK_CHECKBOX_SIZE);

		this.drawScaledText(graphics, entry.title(), x + 54, y + 8, ORANGE_TEXT, ENTRY_TITLE_SCALE);

		int textY = y + 21;
		this.drawWrappedColoredText(graphics, x + 4, textY, UNLOCK_CARD_WIDTH - 8, 4, entry.description(), ORANGE_TEXT, " (" + entry.points() + ")", RED_TEXT);
	}

	private void drawSelectionGlyph(GuiGraphicsExtractor graphics, int x, int y, boolean unlocked) {
		graphics.fill(x, y, x + 8, y + 1, BORDER);
		graphics.fill(x, y + 8, x + 8, y + 9, BORDER);
		graphics.fill(x, y, x + 1, y + 9, BORDER);
		graphics.fill(x + 8, y, x + 9, y + 9, BORDER);
		graphics.fill(x + 1, y + 1, x + 8, y + 8, PANEL_FILL);
		if (unlocked) {
			graphics.text(this.font, "✓", x + 1, y, GREEN_TEXT, false);
		}
	}

	private void drawUnlockScrollbar(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int barX = this.leftPos + UNLOCK_SCROLL_X;
		int barY = this.topPos + UNLOCK_SCROLL_Y;
		this.tileFill(graphics, SCROLLBAR_TRACK, barX, barY, UNLOCK_SCROLL_W, UNLOCK_SCROLL_H, UNLOCK_SCROLL_W, 12);

		graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_UP, barX, barY - 1, 0.0F, 0.0F, UNLOCK_SCROLL_BUTTON_W, 14, UNLOCK_SCROLL_BUTTON_W, 14, UNLOCK_SCROLL_BUTTON_W, 14);
		graphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_DOWN, barX, barY + UNLOCK_SCROLL_H - 13, 0.0F, 0.0F, UNLOCK_SCROLL_BUTTON_W, 14, UNLOCK_SCROLL_BUTTON_W, 14, UNLOCK_SCROLL_BUTTON_W, 14);
		int maxScroll = this.unlockMaxScroll();
		if (maxScroll > 0) {
			int thumbMinY = barY + UNLOCK_SCROLL_BUTTON_H;
			int thumbMaxY = barY + UNLOCK_SCROLL_H - UNLOCK_SCROLL_BUTTON_H - UNLOCK_SCROLL_THUMB_H;
			int thumbTravel = Math.max(0, thumbMaxY - thumbMinY);
			int thumbY = thumbMinY + Math.round((thumbTravel * this.unlockScrollOffset) / (float) maxScroll);
			this.drawScrollbarThumb(graphics, barX, thumbY, UNLOCK_SCROLL_W, UNLOCK_SCROLL_THUMB_H);
		}
	}

	private void drawScrollbarThumb(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, SCROLLBAR_THUMB_TOP, x, y, 0.0F, 0.0F, width, 4, width, 4, width, 4);
		int middleHeight = Math.max(0, height - 8);
		if (middleHeight > 0) {
			this.tileFill(graphics, SCROLLBAR_THUMB_MIDDLE, x, y + 4, width, middleHeight, width, 4);
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, SCROLLBAR_THUMB_BOTTOM, x, y + height - 4, 0.0F, 0.0F, width, 4, width, 4, width, 4);
	}

	private void drawUnlockTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
	}

	private void drawActionButtonTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		this.drawActionButtonTooltip(graphics, mouseX, mouseY, 171, 94, 51, 22, 14);
		this.drawActionButtonTooltip(graphics, mouseX, mouseY, 223, 94, 51, 22, 15);
		this.drawActionButtonTooltip(graphics, mouseX, mouseY, 275, 94, 51, 22, 16);
		this.drawActionButtonTooltip(graphics, mouseX, mouseY, 327, 94, 51, 22, 17);
	}

	private void drawActionButtonTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int centerX, int y, int width, int height, int slotId) {
		int left = this.leftPos + centerX - width / 2;
		int top = this.topPos + y;
		if (!this.containsThinButton(mouseX, mouseY, left, top, width)) {
			return;
		}

		Slot slot = this.menuSlot(slotId);
		if (slot != null && slot.hasItem()) {
			graphics.setTooltipForNextFrame(this.font, slot.getItem(), mouseX, mouseY);
		}
	}

	private boolean handleTabClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		for (TabSpec tab : TASK_TABS) {
			if (contains(relativeX, relativeY, tab.x(), TAB_Y, TAB_WIDTH, TAB_HEIGHT)) {
				this.sendMenuClick(tab.slotId(), 0, ContainerInput.PICKUP);
				return true;
			}
		}
		return false;
	}

	private boolean handleUnlockClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		if (contains(relativeX, relativeY, UNLOCK_SCROLL_X, UNLOCK_SCROLL_Y - 1, UNLOCK_SCROLL_BUTTON_W, 14)) {
			return this.scrollUnlock(-UNLOCK_SCROLL_STEP);
		}
		if (contains(relativeX, relativeY, UNLOCK_SCROLL_X, UNLOCK_SCROLL_Y + UNLOCK_SCROLL_H - 13, UNLOCK_SCROLL_BUTTON_W, 14)) {
			return this.scrollUnlock(UNLOCK_SCROLL_STEP);
		}
		int clicked = this.unlockIndexAt(mouseX, mouseY);
		if (clicked >= 0) {
			int slotId = 9 + clicked;
			Slot slot = this.menuSlot(slotId);
			if (this.isPurchasedOrRemoveUnlock(slot)) {
				this.sendMenuClick(slotId, 0, ContainerInput.PICKUP);
				return true;
			}
			this.selectedUnlockIndex = clicked;
			this.selectedUnlockSlotId = slotId;
			return true;
		}
		return false;
	}

	private boolean handleUnlockDetailClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		int backX = UNLOCK_DETAIL_BACK_X - UNLOCK_DETAIL_BUTTON_W / 2;
		int confirmX = UNLOCK_DETAIL_CONFIRM_X - UNLOCK_DETAIL_BUTTON_W / 2;
		if (contains(relativeX, relativeY, backX, UNLOCK_DETAIL_BUTTON_Y + THIN_BUTTON_HITBOX_Y_INSET, UNLOCK_DETAIL_BUTTON_W, TASK_UNBLOCK_BUTTON_HITBOX_H)) {
			this.selectedUnlockIndex = -1;
			this.selectedUnlockSlotId = -1;
			return true;
		}
		if (contains(relativeX, relativeY, confirmX, UNLOCK_DETAIL_BUTTON_Y + THIN_BUTTON_HITBOX_Y_INSET, UNLOCK_DETAIL_BUTTON_W, TASK_UNBLOCK_BUTTON_HITBOX_H)) {
			if (this.selectedUnlockSlotId >= 0) {
				this.sendMenuClick(this.selectedUnlockSlotId, 0, ContainerInput.PICKUP);
			}
			return true;
		}
		return false;
	}

	private boolean handleTaskActionClick(int mouseX, int mouseY) {
		if (this.variant != Variant.TASKS) {
			return false;
		}

		return this.handleThinButtonClick(mouseX, mouseY, 171, TASK_ACTION_BUTTON_Y, TASK_ACTION_BUTTON_WIDTH, 14)
			|| this.handleThinButtonClick(mouseX, mouseY, 223, TASK_ACTION_BUTTON_Y, TASK_ACTION_BUTTON_WIDTH, 15)
			|| this.handleThinButtonClick(mouseX, mouseY, 275, TASK_ACTION_BUTTON_Y, TASK_ACTION_BUTTON_WIDTH, 16)
			|| this.handleThinButtonClick(mouseX, mouseY, 327, TASK_ACTION_BUTTON_Y, TASK_ACTION_BUTTON_WIDTH, 17);
	}

	private boolean handleThinButtonClick(int mouseX, int mouseY, int centerX, int y, int width, int slotId) {
		int left = this.leftPos + centerX - width / 2;
		int top = this.topPos + y;
		if (!this.containsThinButton(mouseX, mouseY, left, top, width)) {
			return false;
		}
		this.sendMenuClick(slotId, 0, ContainerInput.PICKUP);
		return true;
	}

	private boolean handleUnblockClick(int mouseX, int mouseY) {
		if (this.variant != Variant.TASKS) {
			return false;
		}

		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		int startY = 151;
		int rowStep = 14;
		for (int i = 0; i < TASK_ROWS.size(); i++) {
			int textY = startY + i * rowStep;
			int buttonCenterX = 286;
			int buttonY = textY - 7;
			int buttonWidth = 72;
            int buttonX = buttonCenterX - buttonWidth / 2;
			if (contains(relativeX, relativeY, buttonX, buttonY, buttonWidth, TASK_UNBLOCK_BUTTON_HITBOX_H)) {
				this.sendMenuClick(17, 0, ContainerInput.PICKUP);
				return true;
			}
		}
		return false;
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

	private boolean handleBuySlotClick(int mouseX, int mouseY, int button, boolean shift) {
		if (this.variant != Variant.BUY) {
			return false;
		}

		Slot slot = this.findBuySlot(mouseX, mouseY);
		if (slot == null || !slot.hasItem()) {
			return false;
		}

		int slotId = this.slotId(slot);
		if (slotId < 0) {
			return false;
		}

		this.sendMenuClick(slotId, button, shift ? ContainerInput.QUICK_MOVE : ContainerInput.PICKUP);
		return true;
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

	private boolean clickNamedItem(String itemName) {
		if (!MenuInteractionGate.allowScrollAction(this.menu.containerId, itemName)) {
			return false;
		}
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			if (slot == null || !slot.hasItem()) {
				continue;
			}
			if (itemName.equalsIgnoreCase(slot.getItem().getHoverName().getString())) {
				this.sendMenuClick(slotId, 0, ContainerInput.PICKUP);
				return true;
			}
		}
		return false;
	}

	private Slot menuSlot(int slotId) {
		if (slotId < 0 || slotId >= this.menu.slots.size()) {
			return null;
		}
		return this.menu.slots.get(slotId);
	}

	private Slot findBuySlot(int mouseX, int mouseY) {
		int chestSlotCount = this.chestSlotCount();
		for (int slotId = BUY_SLOT_COLS * BUY_HIDDEN_ROWS; slotId < chestSlotCount; slotId++) {
			Slot slot = this.menu.slots.get(slotId);
			int x = this.leftPos + slot.x - Math.round((BUY_ITEM_DRAW_SIZE - 16) / 2.0F);
			int y = this.topPos + slot.y - Math.round((BUY_ITEM_DRAW_SIZE - 16) / 2.0F);
			if (contains(mouseX, mouseY, x, y, BUY_ITEM_DRAW_SIZE, BUY_ITEM_DRAW_SIZE)) {
				return slot;
			}
		}
		return null;
	}

	private Slot unlockDisplaySlot(int unlockIndex) {
		return this.menuSlot(9 + unlockIndex);
	}

	private Identifier unlockCheckboxTexture(Slot slot) {
		if (slot == null || !slot.hasItem()) {
			return ROUND_CHECK_BOX;
		}

		return this.isPurchasedOrRemoveUnlock(slot) ? ROUND_CHECK_BOX_CHECKED_GREEN : ROUND_CHECK_BOX;
	}

	private boolean isPurchasedOrRemoveUnlock(Slot slot) {
		if (slot == null || !slot.hasItem()) {
			return false;
		}

		ItemLore lore = slot.getItem().get(DataComponents.LORE);
		if (lore == null) {
			return false;
		}

		for (Component line : lore.lines()) {
			String text = line.getString().trim().toLowerCase(Locale.ROOT);
			if (text.contains("purchased") || text.contains("remove unlock")) {
				return true;
			}
		}

		return false;
	}

	private int unlockIndexAt(int mouseX, int mouseY) {
		for (int row = 0; row < this.unlockTotalRows(); row++) {
			int y = this.topPos + UNLOCK_LIST_TOP + row * (UNLOCK_CARD_HEIGHT + UNLOCK_CARD_GAP_Y) - this.unlockScrollOffset;
			if (y > this.topPos + UNLOCK_LIST_BOTTOM || y + UNLOCK_CARD_HEIGHT < this.topPos + UNLOCK_LIST_TOP) {
				continue;
			}
			for (int col = 0; col < 2; col++) {
				int index = row * 2 + col;
				if (index >= this.rewardCategories().size()) {
					return -1;
				}
				int x = this.leftPos + UNLOCK_LIST_LEFT + col * (UNLOCK_CARD_WIDTH + UNLOCK_CARD_GAP_X);
				if (contains(mouseX, mouseY, x, y, UNLOCK_CARD_WIDTH, UNLOCK_CARD_HEIGHT)) {
					return index;
				}
			}
		}
		return -1;
	}

	private String tooltipNumber(int slotId, int numberIndex, String fallback) {
		Slot slot = this.menuSlot(slotId);
		if (slot == null || !slot.hasItem()) {
			return fallback;
		}

		ItemStack stack = slot.getItem();
		Minecraft minecraft = this.minecraft;
		Item.TooltipContext context = minecraft != null && minecraft.level != null
			? Item.TooltipContext.of(minecraft.level)
			: Item.TooltipContext.EMPTY;
		List<Component> tooltipLines = stack.getTooltipLines(context, minecraft != null ? minecraft.player : null, TooltipFlag.Default.NORMAL);

		int found = 0;
		for (Component line : tooltipLines) {
			Matcher matcher = NUMBER_PATTERN.matcher(line.getString());
			while (matcher.find()) {
				if (found == numberIndex) {
					return matcher.group(1);
				}
				found++;
			}
		}
		return fallback;
	}

	private String segmentOrFallback(int index, String fallback) {
		return index >= 0 && index < this.decodedTitleSegments.size() && !this.decodedTitleSegments.get(index).isBlank()
			? this.decodedTitleSegments.get(index)
			: fallback;
	}

	private String blockedTaskName(int rowIndex) {
		return switch (rowIndex) {
			case 0 -> this.segmentOrFallback(14, "Metal Dragons");
			case 1 -> this.segmentOrFallback(15, "Dark Beasts");
			case 2 -> this.segmentOrFallback(16, "Abyssal Demons");
			case 3 -> this.segmentOrFallback(17, "Nechryael");
			case 4 -> this.segmentOrFallback(18, "Bloodveld");
			case 5 -> this.segmentOrFallback(19, "Drakes");
			case 6 -> this.segmentOrFallback(20, "Suqahs");
			default -> "";
		};
	}

	private List<UnlockEntry> unlockEntries() {
		return this.menu.slots.stream()
			.limit(this.chestSlotCount())
			.filter(Slot::hasItem)
			.filter(slot -> !this.isScrollControl(slot.getItem()))
			.map(slot -> new UnlockEntry(this.slotId(slot), slot.getItem(), this.buildUnlockTitle(slot.getItem()), this.buildUnlockDescription(slot.getItem()), this.isUnlockedEntry(slot.getItem())))
			.toList();
	}

	private UnlockEntry hoveredUnlockEntry(int mouseX, int mouseY) {
		return null;
	}

	private boolean scrollUnlock(int delta) {
		int maxScroll = this.unlockMaxScroll();
		int next = Math.max(0, Math.min(maxScroll, this.unlockScrollOffset + delta));
		if (next == this.unlockScrollOffset) {
			return false;
		}
		this.unlockScrollOffset = next;
		return true;
	}

	private int unlockTotalRows() {
		return (this.rewardCategories().size() + 1) / 2;
	}

	private int unlockContentHeight() {
		int rows = this.unlockTotalRows();
		return rows <= 0 ? 0 : rows * UNLOCK_CARD_HEIGHT + (rows - 1) * UNLOCK_CARD_GAP_Y;
	}

	private int unlockViewportHeight() {
		return UNLOCK_LIST_BOTTOM - UNLOCK_LIST_TOP;
	}

	private int unlockMaxScroll() {
		return Math.max(0, this.unlockContentHeight() - this.unlockViewportHeight());
	}

	private UnlockCategory selectedUnlock() {
		if (this.selectedUnlockIndex < 0 || this.selectedUnlockIndex >= this.rewardCategories().size()) {
			return null;
		}
		return this.rewardCategories().get(this.selectedUnlockIndex);
	}

	private List<String> wrapText(String text, int maxWidth, int maxLines) {
		List<String> lines = new ArrayList<>();
		String[] words = text.split("\\s+");
		StringBuilder current = new StringBuilder();
		for (String word : words) {
			String candidate = current.isEmpty() ? word : current + " " + word;
			if (this.font.width(candidate) <= maxWidth) {
				current.setLength(0);
				current.append(candidate);
				continue;
			}
			if (!current.isEmpty()) {
				lines.add(current.toString());
				if (lines.size() == maxLines) {
					return lines;
				}
			}
			current.setLength(0);
			current.append(word);
		}
		if (!current.isEmpty() && lines.size() < maxLines) {
			lines.add(current.toString());
		}
		return lines;
	}

	private void drawWrappedColoredText(
		GuiGraphicsExtractor graphics,
		int x,
		int y,
		int maxWidth,
		int maxLines,
		String primaryText,
		int primaryColor,
		String secondaryText,
		int secondaryColor
	) {
		List<TextRun> runs = new ArrayList<>();
		this.appendWrappedRuns(runs, primaryText, primaryColor);
		this.appendWrappedRuns(runs, secondaryText, secondaryColor);

		List<TextRun> lineRuns = new ArrayList<>();
		int lineWidth = 0;
		int lineIndex = 0;
		for (TextRun run : runs) {
			int runWidth = this.font.width(run.text());
			if (!lineRuns.isEmpty() && lineWidth + runWidth > maxWidth) {
				this.drawTextRuns(graphics, x, y + lineIndex * 10, lineRuns);
				lineRuns = new ArrayList<>();
				lineWidth = 0;
				lineIndex++;
				if (lineIndex >= maxLines) {
					return;
				}
			}
			lineRuns.add(run);
			lineWidth += runWidth;
		}

		if (!lineRuns.isEmpty() && lineIndex < maxLines) {
			this.drawTextRuns(graphics, x, y + lineIndex * 10, lineRuns);
		}
	}

	private void appendWrappedRuns(List<TextRun> runs, String text, int color) {
		if (text == null || text.isEmpty()) {
			return;
		}

		Matcher matcher = Pattern.compile("\\S+|\\s+").matcher(text);
		while (matcher.find()) {
			runs.add(new TextRun(matcher.group(), color));
		}
	}

	private void drawTextRuns(GuiGraphicsExtractor graphics, int x, int y, List<TextRun> runs) {
		int drawX = x;
		for (TextRun run : runs) {
			graphics.text(this.font, run.text(), drawX, y, run.color(), false);
			drawX += this.font.width(run.text());
		}
	}

	private String buildUnlockTitle(ItemStack stack) {
		return stack.getHoverName().getString();
	}

	private List<String> buildUnlockDescription(ItemStack stack) {
		ItemLore lore = stack.get(DataComponents.LORE);
		if (lore == null || lore.lines().isEmpty()) {
			return List.of();
		}

		return lore.lines().stream()
			.map(Component::getString)
			.map(String::trim)
			.filter(line -> !line.isBlank())
			.limit(3)
			.toList();
	}

	private boolean isUnlockedEntry(ItemStack stack) {
		List<String> descriptionLines = this.buildUnlockDescription(stack);
		return descriptionLines.stream().anyMatch(line -> line.contains("(0 points)")) || stack.isEnchanted();
	}

	private boolean isScrollControl(ItemStack stack) {
		String name = stack.getHoverName().getString();
		return "scroll up".equalsIgnoreCase(name) || "scroll down".equalsIgnoreCase(name);
	}

	private void drawScaledItem(GuiGraphicsExtractor graphics, ItemStack stack, int x, int y, int drawSize) {
		float scale = drawSize / 16.0F;
		int centeredX = x - Math.round((drawSize - 16) / 2.0F);
		int centeredY = y - Math.round((drawSize - 16) / 2.0F);
		graphics.pose().pushMatrix();
		graphics.pose().translate(centeredX, centeredY);
		graphics.pose().scale(scale, scale);
		graphics.item(stack, 0, 0);
		graphics.pose().popMatrix();
	}

	private int chestSlotCount() {
		return this.menu.getRowCount() * 9;
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

	private int slotId(Slot target) {
		for (int slotId = 0; slotId < this.menu.slots.size(); slotId++) {
			if (this.menu.slots.get(slotId) == target) {
				return slotId;
			}
		}
		return -1;
	}

	private boolean isSelectedTab(TabSpec tab) {
		return switch (this.variant) {
			case BUY -> "Buy".equals(tab.label());
			case TASKS -> "Tasks".equals(tab.label());
			case UNLOCK -> "Unlock".equals(tab.label());
			case EXTEND -> "Extend".equals(tab.label());
		};
	}

	private boolean isRewardsListVariant() {
		return this.variant == Variant.UNLOCK || this.variant == Variant.EXTEND;
	}

	private List<UnlockCategory> rewardCategories() {
		return this.variant == Variant.EXTEND ? EXTEND_CATEGORIES : UNLOCK_CATEGORIES;
	}

	private String swapButtonLabel(String currentAssignmentRight) {
		return "No Stored Task".equalsIgnoreCase(currentAssignmentRight) ? "Store" : "Swap";
	}

	private void drawOuterFrame(GuiGraphicsExtractor graphics) {
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

	private void drawCenteredText(GuiGraphicsExtractor graphics, String text, int y, int color) {
		this.drawCenteredText(graphics, text, SCREEN_WIDTH / 2, y, color);
	}

	private void drawCenteredText(GuiGraphicsExtractor graphics, String text, int centerX, int y, int color) {
		int drawX = this.leftPos + centerX - this.font.width(text) / 2;
		graphics.text(this.font, text, drawX, this.topPos + y, color, false);
	}

	private void drawCenteredSegments(GuiGraphicsExtractor graphics, int y, TextSegment... segments) {
		int totalWidth = 0;
		for (TextSegment segment : segments) {
			totalWidth += this.font.width(segment.text());
		}

		int drawX = this.leftPos + (SCREEN_WIDTH - totalWidth) / 2;
		for (TextSegment segment : segments) {
			graphics.text(this.font, segment.text(), drawX, this.topPos + y, segment.color(), false);
			drawX += this.font.width(segment.text());
		}
	}

	private void drawScaledText(GuiGraphicsExtractor graphics, String text, int x, int y, int color, float scale) {
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(scale, scale);
		graphics.text(this.font, text, 0, 0, color, false);
		graphics.pose().popMatrix();
	}

	private boolean containsThinButton(int mouseX, int mouseY, int x, int y, int width) {
		return contains(mouseX, mouseY, x, y + THIN_BUTTON_HITBOX_Y_INSET, width, TASK_UNBLOCK_BUTTON_HITBOX_H);
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

	private static String normalize(String title) {
		return title == null ? "" : title.trim().toLowerCase(Locale.ROOT);
	}

	private static Identifier texture(String path) {
		return Identifier.parse("minescapeaddon:textures/gui/runescape/" + path);
	}

	private record TabSpec(String label, boolean selected, int x, int slotId) {
	}

	private record BlockedTaskRow(String slotLabel) {
	}

	private record TextSegment(String text, int color) {
	}

	private record TextRun(String text, int color) {
	}

	private record UnlockEntry(int slotId, ItemStack stack, String title, List<String> descriptionLines, boolean unlocked) {
	}

	private record UnlockCategory(String title, String description, String points, boolean unlocked) {
	}

	private enum Variant {
		BUY(
			"Slayer Rewards",
			"0",
			List.of(),
			"",
			""
		),
		EXTEND(
			"Slayer Rewards",
			"0",
			List.of(),
			"",
			""
		),
		UNLOCK(
			"Slayer Rewards",
			"0",
			List.of(),
			"",
			""
		),
		TASKS(
			"Slayer Rewards",
			"1,165",
			List.of(
				"You may spend points to Cancel or Block your current task.",
				"If you cancel it you may be assigned that target again in future. (50 points)",
				"If you block it, you'll not get that assignment again. (80 points)",
				"You may also store your current task for later."
			),
			"123 x Kalphites",
			"24 x Araxytes"
		);

		private final String headerTitle;
		private final String rewardPoints;
		private final String currentAssignmentLeft;
		private final String currentAssignmentRight;

		Variant(String headerTitle, String rewardPoints, List<String> descriptionLines, String currentAssignmentLeft, String currentAssignmentRight) {
			this.headerTitle = headerTitle;
			this.rewardPoints = rewardPoints;
			this.currentAssignmentLeft = currentAssignmentLeft;
			this.currentAssignmentRight = currentAssignmentRight;
		}

		public String headerTitle() {
			return this.headerTitle;
		}

		public String rewardPoints() {
			return this.rewardPoints;
		}

		public List<String> descriptionLines(String cancelPoints, String blockPoints) {
			return List.of(
				"You may spend points to Cancel or Block your current task.",
				"If you cancel it you may be assigned that target again in future. (" + cancelPoints + " points)",
				"If you block it, you'll not get that assignment again. (" + blockPoints + " points)",
				"You may also store your current task for later."
			);
		}

		public String currentAssignmentLeft() {
			return this.currentAssignmentLeft;
		}

		public String currentAssignmentRight() {
			return this.currentAssignmentRight;
		}

		public static Variant fromTitle(String title) {
			String normalized = normalizeDecoded(title);
			if (normalized.contains("slayer - buy")) {
				return BUY;
			}
			if (normalized.contains("slayer - extend")) {
				return EXTEND;
			}
			if (normalized.contains("slayer - unlock")) {
				return UNLOCK;
			}
			if (normalized.contains("slayer - tasks")) {
				return TASKS;
			}
			return TASKS;
		}

		private static String normalizeDecoded(String title) {
			return PrivateUseAsciiDecoder.decode(title == null ? "" : title).trim().toLowerCase(Locale.ROOT);
		}
	}
}
