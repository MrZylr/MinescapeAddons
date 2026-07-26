package com.zylr.client.hud;

import com.zylr.MinescapeAddon;
import com.zylr.client.PerfDebug;
import com.zylr.client.screen.overridescreens.categories.CustomContainerScreenRegistry;
import com.zylr.client.screen.HudInventoryScreen;
import com.zylr.client.skills.SkillType;
import com.zylr.client.skills.Skills;
import com.zylr.player.PlayerStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zylr.client.hud.HudManager.TAB_ROW_BOTTOM;
import static com.zylr.client.hud.HudManager.TAB_ROW_TOP;
import static com.zylr.client.hud.HudManager.TAB_STONE;
import static com.zylr.client.hud.HudManager.TAB_STONE_SELECTED;
import static com.zylr.client.hud.HudManager.blitTexture;
import static com.zylr.client.hud.HudManager.drawScaledText;
import static com.zylr.client.hud.HudManager.resolveHudTextScale;
import static com.zylr.client.hud.HudManager.scaledTextHeight;
import static com.zylr.client.hud.HudManager.scaledTextWidth;

public final class ContentPanelWidget extends HudWidget {
	private static final int BASE_WIDTH = 77;
	private static final int BASE_HEIGHT = 111;
	private static final int PADDING = 5;
	private static final int TITLE_HEIGHT = 22;
	private static final int INVENTORY_SLOT_BASE_SIZE = 15;
	private static final int INVENTORY_SLOT_BASE_GAP = 1;
	private static final int INVENTORY_SLOT_Y_OFFSET = -6;
	private static final int EQUIPMENT_SLOT_BASE_SIZE = 14;
	private static final int EQUIPMENT_SLOT_BASE_GAP = 1;
	private static final int EQUIPMENT_BUTTON_BASE_SIZE = 16;
	private static final int EQUIPMENT_BUTTON_BASE_GAP = 1;
	private static final int SKILL_TILE_BASE_SIZE = 14;
	private static final int SKILL_TILE_OVERLAP = 3;
	private static final int SKILL_COLUMN_GAP_REDUCTION = 4;
	private static final int SKILL_GRID_SHIFT_X = 5;
	private static final int SKILL_TILE_LEFT_INSET = 4;
	private static final int SKILL_TILE_RIGHT_TRIM = 2;
	private static final double SKILL_TEXT_BASE_WIDGET_SCALE = 1.95D;
	private static final float SKILL_TEXT_SCALE_SLOPE = 0.4F;
	private static final long MAX_XP = 200_000_000L;
	private static final int TAB_ICON_WIDTH = 33;
	private static final int TAB_ICON_HEIGHT = 36;
	private static final Identifier RESIZEABLE_PANEL_BG = texture("resizeable_mode/side_panel_background.png");
	private static final Identifier RESIZEABLE_PANEL_EDGE_LEFT = texture("resizeable_mode/side_panel_edge_left.png");
	private static final Identifier RESIZEABLE_PANEL_EDGE_RIGHT = texture("resizeable_mode/side_panel_edge_right.png");
	private static final Identifier FRAME_MIDDLE = texture("tab/side_border_middle.png");
	private static final Identifier FRAME_TOP = texture("tab/side_border_top.png");
	private static final Identifier FRAME_BOTTOM = texture("tab/side_border_bottom.png");
	private static final Identifier FRAME_LEFT = texture("tab/side_border_left.png");
	private static final Identifier FRAME_RIGHT = texture("tab/side_border_right.png");
	private static final Identifier FRAME_TOP_LEFT = texture("tab/side_border_edge_top_left.png");
	private static final Identifier FRAME_TOP_RIGHT = texture("tab/side_border_edge_top_right.png");
	private static final Identifier FRAME_BOTTOM_LEFT = texture("tab/side_border_edge_bottom_left.png");
	private static final Identifier FRAME_BOTTOM_RIGHT = texture("tab/side_border_edge_bottom_right.png");
	private static final Identifier SLOT_TILE = texture("equipment/slot_tile.png");
	private static final Identifier SLOT_TORSO = texture("equipment/slot_torso.png");
	private static final Identifier SLOT_LEGS = texture("equipment/slot_legs.png");
	private static final Identifier SLOT_WEAPON = texture("equipment/slot_weapon.png");
	private static final Identifier SLOT_HANDS = texture("equipment/slot_hands.png");
	private static final Identifier SLOT_CAPE = texture("equipment/slot_cape.png");
	private static final Identifier SLOT_SHIELD = texture("equipment/slot_shield.png");
	private static final Identifier SLOT_AMMUNITION = texture("equipment/slot_ammunition.png");
	private static final Identifier SLOT_FEET = texture("equipment/slot_feet.png");
	private static final Identifier SLOT_NECK = texture("equipment/slot_neck.png");
	private static final Identifier SLOT_HEAD = texture("equipment/slot_head.png");
	private static final Identifier SLOT_RING = texture("equipment/slot_ring.png");
	private static final Identifier BTN_STATS = texture("button/equipment_stats_icon.png");
	private static final Identifier BTN_GUIDE_PRICES = texture("button/equipment_guide_prices.png");
	private static final Identifier BTN_ITEMS_LOST = texture("button/equipment_items_lost_on_death.png");
	private static final Identifier BTN_CALL_FOLLOWER = texture("button/equipment_call_follower.png");
	private static final Identifier BTN_BG = texture("button/enter_wilderness_teleport.png");
	private static final Identifier BTN_BG_HOVERED = texture("button/enter_wilderness_teleport_hovered.png");
	private static final Identifier COMBAT_ICON = texture("tab/combat.png");
	private static final Identifier HITPOINTS_ORB_ICON = texture("other/minimap_orb_hitpoints_icon.png");
	private static final Identifier PRAYER_ORB_ICON = texture("other/minimap_orb_prayer_icon.png");
	private static final Identifier STAT_TILE_LEFT = texture("stats/new_tile_left.png");
	private static final Identifier STAT_TILE_RIGHT = texture("stats/new_tile_right_with_slash.png");
	private static final Identifier STAT_BAR_LEFT = texture("stats/new_tile_left_black.png");
	private static final Identifier STAT_BAR_MIDDLE = texture("stats/new_tile_middle_black.png");
	private static final Identifier STAT_BAR_RIGHT = texture("stats/new_tile_right_black.png");
	private static final SkillType[] ALL_SKILLS = SkillType.values();
	private static final Map<SkillType, Identifier> SKILL_ICONS;

	static {
		EnumMap<SkillType, Identifier> map = new EnumMap<>(SkillType.class);
		for (SkillType skill : SkillType.values()) {
			map.put(skill, texture("skill/" + skill.getIconName() + ".png"));
		}
		SKILL_ICONS = map;
	}

	private static final int[] INVENTORY_SLOT_ORDER = {
			2, 3, 4, 5, 6, 7, 8,
			11,12,13,14,15,16,17,
			20,21,22,23,24,25,26,
			29,30,31,32,33,34,35
	};
	private static final int GRID_COLS = 4;
	private static final int GRID_ROWS = 7;
	private static final int[] EQUIPMENT_LEFT_SLOTS = {38, 37, 0, 18};
	private static final int[] EQUIPMENT_RIGHT_SLOTS = {10, 40, 28, 36};
	private static final int[] EQUIPMENT_TOP_SLOTS = {9, 39, 19};
	private static final int[] EQUIPMENT_ALL_SLOTS = {38, 37, 0, 18, 10, 40, 28, 36, 9, 39, 19};
	private static final int SIDE_STAT_BG_COLOR = 0xFF000000;
	private static final int SIDE_HP_FILL_COLOR = new Color(119,26,10).getRGB();
	private static final int SIDE_STAT_RESTORE_PREVIEW_COLOR = 0xFF00C853;
	private static final int SIDE_PRAYER_FILL_COLOR = new Color(38, 140, 140).getRGB();
	private static final int SIDE_STAT_TEXT_COLOR = Color.WHITE.getRGB();
	private static final Pattern HEALS_LORE_PATTERN = Pattern.compile("\\b([\\d,]+)\\b.*\\bheals\\b|\\bheals\\b.*\\b([\\d,]+)\\b", Pattern.CASE_INSENSITIVE);

	private int itemRenderCount = 0;
	private int lastFrameX = -1;
	private int lastFrameY = -1;
	private int lastFrameW = -1;
	private int lastFrameH = -1;
	private double lastScale = -1;
	private int[][] slotGrid = new int[GRID_ROWS][GRID_COLS];
	private int[][] slotBoundsX = new int[GRID_ROWS][GRID_COLS];
	private int[][] slotBoundsY = new int[GRID_ROWS][GRID_COLS];
	private int slotSize = -1;
	private int[] equipmentSlotBoundsX = new int[EQUIPMENT_ALL_SLOTS.length];
	private int[] equipmentSlotBoundsY = new int[EQUIPMENT_ALL_SLOTS.length];
	private int equipmentSlotSize = -1;
	private int modelMinX = -1;
	private int modelMinY = -1;
	private int modelMaxX = -1;
	private int modelMaxY = -1;
	private int modelCenterX = -1;
	private int modelCenterY = -1;
	private boolean rotatingModel = false;
	private float modelMouseOffsetX = 0.0F;
	private int[] buttonBoundsX = new int[4];
	private int[] buttonBoundsY = new int[4];
	private int buttonSize = -1;
	private int[][] skillBoundsX = new int[8][3];
	private int[][] skillBoundsY = new int[8][3];
	private int skillTileSize = -1;
	private int combatIconX = -1;
	private int combatIconY = -1;
	private int combatIconSize = -1;

	ContentPanelWidget(double defaultX, double defaultY, double defaultScale) {
		super("contentPanel", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return BASE_WIDTH; }

	@Override
	protected int baseHeight() { return BASE_HEIGHT; }

	private int tabRowEdgeWidth() { return Math.max(10, (int) Math.round(10 * this.scale())); }
	private int tabRowTotalWidth() { return this.pixelWidth() + this.tabRowEdgeWidth() * 2 + 4; }
	private int tabRowStartX(int panelX) { return panelX - this.tabRowEdgeWidth() - 2; }
	private int tabButtonX(int rowStartX, int totalWidth, int numTabs, int index) { return rowStartX + index * totalWidth / numTabs; }
	private int tabButtonW(int rowStartX, int totalWidth, int numTabs, int index) {
		return tabButtonX(rowStartX, totalWidth, numTabs, index + 1) - tabButtonX(rowStartX, totalWidth, numTabs, index);
	}

	private int calcTabButtonHeight() {
		int avgWidth = Math.max(1, this.tabRowTotalWidth() / Math.max(TAB_ROW_TOP.length, TAB_ROW_BOTTOM.length));
		return (int) Math.round((double) avgWidth * TAB_ICON_HEIGHT / TAB_ICON_WIDTH);
	}

	@Override
	protected int extraTopBounds(int sw, int sh) { return this.calcTabButtonHeight(); }

	@Override
	protected int extraLeftBounds(int sw, int sh) { return this.tabRowEdgeWidth() + 2; }

	@Override
	protected int extraRightBounds(int sw, int sh) { return this.tabRowEdgeWidth() + 2; }

	@Override
	protected int extraBottomBounds(int sw, int sh) { return this.calcTabButtonHeight(); }

	public HudTab tabAt(double mouseX, double mouseY, int sw, int sh) {
		int x = pixelX(sw);
		int y = pixelY(sh);
		int h = pixelHeight();
		int bh = calcTabButtonHeight();
		int topY = y - bh;
		int bottomY = y + h;
		int rsx = tabRowStartX(x);
		int tw = tabRowTotalWidth();
		int nt = TAB_ROW_TOP.length;
		for (int i = 0; i < TAB_ROW_TOP.length; i++) {
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			if (mouseX >= bx && mouseX < bx + bw && mouseY >= topY && mouseY < topY + bh) return TAB_ROW_TOP[i].linkedTab;
		}
		for (int i = 0; i < TAB_ROW_BOTTOM.length; i++) {
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			if (mouseX >= bx && mouseX < bx + bw && mouseY >= bottomY && mouseY < bottomY + bh) return TAB_ROW_BOTTOM[i].linkedTab;
		}
		return null;
	}

	public int inventorySlotAt(double mouseX, double mouseY) {
		for (int row = 0; row < GRID_ROWS; row++) {
			for (int col = 0; col < GRID_COLS; col++) {
				int bx = slotBoundsX[row][col];
				int by = slotBoundsY[row][col];
				if (mouseX >= bx && mouseX < bx + slotSize && mouseY >= by && mouseY < by + slotSize) return slotGrid[row][col];
			}
		}
		return -1;
	}

	public int equipmentSlotAt(double mouseX, double mouseY) {
		if (equipmentSlotSize <= 0) return -1;
		for (int i = 0; i < EQUIPMENT_ALL_SLOTS.length; i++) {
			int bx = equipmentSlotBoundsX[i];
			int by = equipmentSlotBoundsY[i];
			if (mouseX >= bx && mouseX < bx + equipmentSlotSize && mouseY >= by && mouseY < by + equipmentSlotSize) return EQUIPMENT_ALL_SLOTS[i];
		}
		return -1;
	}

	public HudManager.TabType getClickedTabType(double mouseX, double mouseY, int sw, int sh) {
		int x = pixelX(sw);
		int y = pixelY(sh);
		int h = pixelHeight();
		int bh = calcTabButtonHeight();
		int topY = y - bh;
		int bottomY = y + h;
		int rsx = tabRowStartX(x);
		int tw = tabRowTotalWidth();
		int nt = TAB_ROW_TOP.length;
		for (int i = 0; i < TAB_ROW_TOP.length; i++) {
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			if (mouseX >= bx && mouseX < bx + bw && mouseY >= topY && mouseY < topY + bh) return HudManager.TabType.fromPosition(i, true);
		}
		nt = TAB_ROW_BOTTOM.length;
		for (int i = 0; i < TAB_ROW_BOTTOM.length; i++) {
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			if (mouseX >= bx && mouseX < bx + bw && mouseY >= bottomY && mouseY < bottomY + bh) return HudManager.TabType.fromPosition(i, false);
		}
		return null;
	}

	public int skillAt(double mouseX, double mouseY) {
		if (skillTileSize <= 0) return -1;
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 8; row++) {
				int idx = col * 8 + row;
				if (idx >= ALL_SKILLS.length) continue;
				int bx = skillBoundsX[row][col];
				int by = skillBoundsY[row][col];
				if (mouseX >= bx && mouseX < bx + skillTileSize * 2 && mouseY >= by && mouseY < by + skillTileSize) return idx;
			}
		}
		return -1;
	}

	public SkillType skillTypeAt(double mouseX, double mouseY) {
		int index = this.skillAt(mouseX, mouseY);
		if (index < 0) return null;
		return index < ALL_SKILLS.length ? ALL_SKILLS[index] : null;
	}

	public boolean isCombatIconHovered(double mouseX, double mouseY) {
		if (combatIconSize <= 0) return false;
		return mouseX >= combatIconX && mouseX < combatIconX + combatIconSize
			&& mouseY >= combatIconY && mouseY < combatIconY + combatIconSize;
	}

	public void clickInventorySlot(int slotIndex, int button, boolean shift) {
		Minecraft mc = Minecraft.getInstance();
		if (mc == null || mc.player == null || mc.gameMode == null || mc.player.containerMenu == null) return;
		int cs;
		if (slotIndex >= 9 && slotIndex <= 35) cs = slotIndex;
		else if (slotIndex >= 0 && slotIndex <= 8) cs = slotIndex + 36;
		else if (slotIndex >= 36 && slotIndex <= 39) cs = 44 - slotIndex;
		else if (slotIndex == 40) cs = 45;
		else return;
		if (cs < 0 || cs >= mc.player.containerMenu.slots.size()) return;
		net.minecraft.world.inventory.ContainerInput ct = shift ? net.minecraft.world.inventory.ContainerInput.QUICK_MOVE : net.minecraft.world.inventory.ContainerInput.PICKUP;
		mc.gameMode.handleContainerInput(mc.player.containerMenu.containerId, cs, button, ct, mc.player);
	}

	public void swapWithHotbar(int slotIndex, int hotbarSlot) {
		Minecraft mc = Minecraft.getInstance();
		if (mc == null || mc.player == null || mc.gameMode == null || mc.player.containerMenu == null) return;
		int cs;
		if (slotIndex >= 9 && slotIndex <= 35) cs = slotIndex;
		else if (slotIndex >= 0 && slotIndex <= 8) cs = slotIndex + 36;
		else if (slotIndex >= 36 && slotIndex <= 39) cs = 44 - slotIndex;
		else if (slotIndex == 40) cs = 45;
		else return;
		if (cs < 0 || cs >= mc.player.containerMenu.slots.size()) return;
		mc.gameMode.handleContainerInput(mc.player.containerMenu.containerId, cs, hotbarSlot, net.minecraft.world.inventory.ContainerInput.SWAP, mc.player);
	}

	public void dropInventorySlot(int slotIndex, boolean dropStack) {
		Minecraft mc = Minecraft.getInstance();
		if (mc == null || mc.player == null || mc.gameMode == null || mc.player.containerMenu == null) return;
		int cs;
		if (slotIndex >= 9 && slotIndex <= 35) cs = slotIndex;
		else if (slotIndex >= 0 && slotIndex <= 8) cs = slotIndex + 36;
		else if (slotIndex >= 36 && slotIndex <= 39) cs = 44 - slotIndex;
		else if (slotIndex == 40) cs = 45;
		else return;
		if (cs < 0 || cs >= mc.player.containerMenu.slots.size()) return;
		mc.gameMode.handleContainerInput(
			mc.player.containerMenu.containerId,
			cs,
			dropStack ? 1 : 0,
			net.minecraft.world.inventory.ContainerInput.THROW,
			mc.player
		);
	}

	public boolean beginEquipmentModelDrag(double mouseX, double mouseY, int sw, int sh) {
		if (modelMinX < 0 || modelMinY < 0) return false;
		if (mouseX < modelMinX || mouseX > modelMaxX || mouseY < modelMinY || mouseY > modelMaxY) return false;
		rotatingModel = true;
		return true;
	}

	public boolean dragEquipmentModel(double deltaX) {
		if (!rotatingModel) return false;
		modelMouseOffsetX += (float) deltaX;
		return true;
	}

	public void endEquipmentModelDrag() { rotatingModel = false; }

	private int equipmentSlotIndex(int inventorySlot) {
		for (int i = 0; i < EQUIPMENT_ALL_SLOTS.length; i++) if (EQUIPMENT_ALL_SLOTS[i] == inventorySlot) return i;
		return -1;
	}

	private void setEquipmentSlotBounds(int inventorySlot, int x, int y, int slotSize) {
		int idx = equipmentSlotIndex(inventorySlot);
		if (idx < 0) return;
		equipmentSlotBoundsX[idx] = x;
		equipmentSlotBoundsY[idx] = y;
		equipmentSlotSize = slotSize;
	}

	private ItemStack playerInventorySlotItem(Minecraft mc, int slot) {
		if (mc.player == null) return ItemStack.EMPTY;
		if (slot < 0 || slot >= mc.player.getInventory().getContainerSize()) return ItemStack.EMPTY;
		return mc.player.getInventory().getItem(slot);
	}

	private static boolean isMappedInventoryGridSlot(int slot) {
		for (int mappedSlot : INVENTORY_SLOT_ORDER) {
			if (mappedSlot == slot) {
				return true;
			}
		}
		return false;
	}

	private static boolean isHighAlchMappedInventoryActive(Minecraft mc) {
		return mc != null
			&& mc.player != null
			&& HudManager.getInstance().isHighAlchContainerMode()
			&& mc.player.containerMenu instanceof ChestMenu;
	}

	private void drawEquipmentSlot(GuiGraphicsExtractor graphics, Minecraft mc, Font overlayFont, int inventorySlot, int sx, int sy, int slotSize) {
		blitTexture(graphics, SLOT_TILE, sx, sy, slotSize, slotSize, 32, 32);
		setEquipmentSlotBounds(inventorySlot, sx, sy, slotSize);
		ItemStack stack = playerInventorySlotItem(mc, inventorySlot);
		if (!stack.isEmpty()) {
			renderScaledItem(graphics, overlayFont, stack, sx + 1, sy + 1, slotSize - 2, true);
		} else {
			Identifier emptyIcon = getEmptySlotIcon(inventorySlot);
			if (emptyIcon != null) blitTexture(graphics, emptyIcon, sx, sy, slotSize, slotSize, 32, 32);
		}
	}

	private Identifier getEmptySlotIcon(int slot) {
		return switch (slot) {
			case 38 -> SLOT_TORSO;
			case 37 -> SLOT_LEGS;
			case 0 -> SLOT_WEAPON;
			case 18 -> SLOT_HANDS;
			case 10 -> SLOT_CAPE;
			case 40 -> SLOT_SHIELD;
			case 28 -> SLOT_AMMUNITION;
			case 36 -> SLOT_FEET;
			case 9 -> SLOT_NECK;
			case 39 -> SLOT_HEAD;
			case 19 -> SLOT_RING;
			default -> null;
		};
	}

	private TabSlot hoveredSlot(double mx, double my, int x, int y, int h, int bh) {
		int topY = y - bh;
		int bottomY = y + h;
		int rsx = tabRowStartX(x);
		int tw = tabRowTotalWidth();
		int nt = TAB_ROW_TOP.length;
		for (int i = 0; i < TAB_ROW_TOP.length; i++) {
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			if (mx >= bx && mx < bx + bw && my >= topY && my < topY + bh) return TAB_ROW_TOP[i];
		}
		for (int i = 0; i < TAB_ROW_BOTTOM.length; i++) {
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			if (mx >= bx && mx < bx + bw && my >= bottomY && my < bottomY + bh) return TAB_ROW_BOTTOM[i];
		}
		return null;
	}

	private void drawTabRow(GuiGraphicsExtractor graphics, int rsx, int tw, int nt, int by, int bh, TabSlot[] row, TabSlot hoveredSlot) {
		for (int i = 0; i < row.length; i++) {
			TabSlot slot = row[i];
			int bx = tabButtonX(rsx, tw, nt, i);
			int bw = tabButtonW(rsx, tw, nt, i);
			boolean active = slot.linkedTab != null && slot.linkedTab == HudManager.getInstance().getSelectedTab();
			blitTexture(graphics, active ? TAB_STONE_SELECTED : TAB_STONE, bx, by, bw, bh, TAB_ICON_WIDTH, TAB_ICON_HEIGHT);
			blitTexture(graphics, slot.icon, bx, by, bw, bh, TAB_ICON_WIDTH, TAB_ICON_HEIGHT);
		}
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		int sw = minecraft.getWindow().getGuiScaledWidth();
		int sh = minecraft.getWindow().getGuiScaledHeight();
		int x = pixelX(sw);
		int y = pixelY(sh);
		int width = pixelWidth();
		int height = pixelHeight();
		if (x + width < 0 || x > sw || y + height < 0 || y > sh) return;
		boolean frameChanged = lastFrameX != x || lastFrameY != y || lastFrameW != width || lastFrameH != height || lastScale != scale();
		int padding = Math.max(4, (int) Math.round(PADDING * scale()));
		int titleHeight = Math.max(16, (int) Math.round(TITLE_HEIGHT * scale()));
		if (frameChanged) {
			lastFrameX = x;
			lastFrameY = y;
			lastFrameW = width;
			lastFrameH = height;
			lastScale = scale();
		}
		long boundsStart = PerfDebug.start();
		updateInventorySlotBounds(minecraft, x, y, padding);
		PerfDebug.record("content.bounds", boundsStart);
		long shellStart = PerfDebug.start();
		int bgExpand = Math.max(1, (int) Math.round(2 * scale()));
		blitTexture(graphics, RESIZEABLE_PANEL_BG, x - bgExpand, y, width + bgExpand * 2, height, 88, 60);
		int edgeW = Math.max(10, (int) Math.round(10 * scale()));
		blitTexture(graphics, RESIZEABLE_PANEL_EDGE_LEFT, x - edgeW, y - 1, edgeW, height + 2, 26, 261);
		blitTexture(graphics, RESIZEABLE_PANEL_EDGE_RIGHT, x + width, y - 1, edgeW, height + 2, 26, 261);
		drawFrameSlices(graphics, x, y, width, height);
		if (HudManager.getInstance().isSideStatBarsEnabled()) {
			renderSideEdgeStatBars(graphics, minecraft, x, y, width, height, edgeW, padding, hoveredItemStack(minecraft, HudManager.getInstance().getSelectedTab(), mouseX, mouseY));
		}
		PerfDebug.record("content.shell", shellStart);
		HudTab tab = HudManager.getInstance().getSelectedTab();
		int tbh = calcTabButtonHeight();
		int topTabsY = y - tbh;
		int bottomTabsY = y + height;
		int rsx = tabRowStartX(x);
		int tw = tabRowTotalWidth();
		int nt = Math.max(TAB_ROW_TOP.length, TAB_ROW_BOTTOM.length);
		long tabsStart = PerfDebug.start();
		TabSlot hovered = hoveredSlot(mouseX, mouseY, x, y, height, tbh);
		drawTabRow(graphics, rsx, tw, nt, topTabsY, tbh, TAB_ROW_TOP, hovered);
		drawTabRow(graphics, rsx, tw, nt, bottomTabsY, tbh, TAB_ROW_BOTTOM, hovered);
		PerfDebug.record("content.tabs", tabsStart);
		long selectedTabStart = PerfDebug.start();
		switch (tab) {
			case COMBAT -> renderCombat(graphics, minecraft, x, y, padding, titleHeight);
			case SKILLS -> renderSkills(graphics, minecraft, x, y, padding, titleHeight, mouseX, mouseY);
			case INVENTORY -> renderInventory(graphics, minecraft, x, y, padding, titleHeight);
			case EQUIPMENT -> renderEquipment(graphics, minecraft, x, y, padding, titleHeight, mouseX, mouseY);
		}
		PerfDebug.record("content.selectedTab", selectedTabStart);
		long tooltipStart = PerfDebug.start();
		renderHoveredItemTooltip(graphics, minecraft, tab, mouseX, mouseY);
		PerfDebug.record("content.tooltip", tooltipStart);
	}

	private void updateInventorySlotBounds(Minecraft mc, int x, int y, int padding) {
		if (mc.player == null) return;
		int ss = Math.max(8, (int) Math.round(INVENTORY_SLOT_BASE_SIZE * scale()));
		int gap = Math.max(0, (int) Math.round(INVENTORY_SLOT_BASE_GAP * scale()));
		int gw = GRID_COLS * ss + (GRID_COLS - 1) * gap;
		int gx = x + (pixelWidth() - gw) / 2;
		int gy = y + padding + INVENTORY_SLOT_Y_OFFSET;
		slotSize = ss;
		for (int row = 0; row < GRID_ROWS; row++) {
			for (int col = 0; col < GRID_COLS; col++) {
				int idx = row * GRID_COLS + col;
				if (idx < INVENTORY_SLOT_ORDER.length) {
					slotGrid[row][col] = INVENTORY_SLOT_ORDER[idx];
					slotBoundsX[row][col] = gx + col * (ss + gap);
					slotBoundsY[row][col] = gy + row * ss;
				}
			}
		}
	}

	private void renderCombat(GuiGraphicsExtractor graphics, Minecraft mc, int x, int y, int padding, int titleHeight) {
		if (mc.player == null) {
			graphics.text(mc.font, Component.literal("No player loaded"), x + padding, y + titleHeight + padding * 2, 0xFFF0D7B0, true);
			return;
		}
		Skills skills = Skills.getInstance();
		int attack = skills.getLevel(SkillType.ATTACK);
		int strength = skills.getLevel(SkillType.STRENGTH);
		int defence = skills.getLevel(SkillType.DEFENCE);
		int ranged = skills.getLevel(SkillType.RANGED);
		int magic = skills.getLevel(SkillType.MAGIC);
		int prayer = skills.getLevel(SkillType.PRAYER);
		int hitpoints = skills.getLevel(SkillType.HITPOINTS);

		double base = 0.25D * (defence + hitpoints + Math.floor(prayer / 2.0D));
		double melee = 0.325D * (attack + strength);
		double range = 0.325D * Math.floor(ranged * 1.5D);
		double mage = 0.325D * Math.floor(magic * 1.5D);
		int combatLevel = (int) Math.floor(base + Math.max(melee, Math.max(range, mage)));

		int currentHp = Mth.clamp(PlayerStats.getHealth(), 0, hitpoints);
		int currentPrayer = Mth.clamp(PlayerStats.getPrayer(), 0, prayer);

		combatIconSize = Math.max(26, (int) Math.round(36 * scale()));
		combatIconX = x + (pixelWidth() - combatIconSize) / 2;
		combatIconY = y + (pixelHeight() - combatIconSize) / 2;
		blitTexture(graphics, COMBAT_ICON, combatIconX, combatIconY, combatIconSize, combatIconSize, TAB_ICON_WIDTH, TAB_ICON_HEIGHT);

		String combatText = "Combat level: " + combatLevel;
		String hpText = "HP: " + currentHp + "/" + hitpoints;
		String prayerText = "Prayer: " + currentPrayer + "/" + prayer;
		int combatX = x + (pixelWidth() - mc.font.width(combatText)) / 2;
		int hpX = x + (pixelWidth() - mc.font.width(hpText)) / 2;
		int prayerX = x + (pixelWidth() - mc.font.width(prayerText)) / 2;
		int topY = y + titleHeight + Math.max(2, padding / 2);
		int bottomY = y + pixelHeight() - padding - 24;
		graphics.text(mc.font, Component.literal(combatText), combatX, topY, 0xFFF0D7B0, true);
		graphics.text(mc.font, Component.literal(hpText), hpX, bottomY, 0xFFF0D7B0, true);
		graphics.text(mc.font, Component.literal(prayerText), prayerX, bottomY + 12, 0xFFF0D7B0, true);
	}

	private void renderSkills(GuiGraphicsExtractor graphics, Minecraft mc, int x, int y, int padding, int titleHeight, int mouseX, int mouseY) {
		final int COLS = 3;
		final int ROWS = 8;
		boolean suppressHover = !((mc.screen instanceof HudInventoryScreen) || CustomContainerScreenRegistry.isCustomContainerScreen(mc.screen)) || HudManager.getInstance().isRuntimeContextMenuOpen();
		int startY = y + 2;
		int pw = pixelWidth();
		int tileSize = Math.max(8, Math.round(SKILL_TILE_BASE_SIZE * (float) scale()));
		int tileOverlap = Math.min(SKILL_TILE_OVERLAP, Math.max(0, Math.round(tileSize / 4.0F)));
		int skillW = 2 * tileSize;
		int colStep = skillW - tileOverlap - SKILL_COLUMN_GAP_REDUCTION;
		int rowStep = tileSize - tileOverlap;
		int gridW = COLS * skillW - (COLS - 1) * tileOverlap;
		int gx = x + Math.round((pw - gridW) / 2.0F) + SKILL_GRID_SHIFT_X;
		int gy = startY;
		Skills data = Skills.getInstance();
		boolean virtualLevelsEnabled = HudManager.getInstance().isVirtualLevelsEnabled();
		float ts = resolveSkillTabTextScale(mc);
		int sfh = scaledTextHeight(mc, ts);
		final int JO = 7;
		SkillType hoveredSkill = null;
		int hoveredSkillX = -1;
		int hoveredSkillY = -1;
		int hoveredSkillW = -1;
		int hoveredSkillH = -1;
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS; row++) {
				int idx = col * ROWS + row;
				if (idx >= ALL_SKILLS.length) continue;
				SkillType skill = ALL_SKILLS[idx];
				int sx = gx + col * colStep;
				int sy = gy + row * rowStep;
				skillBoundsX[row][col] = sx;
				skillBoundsY[row][col] = sy;
				skillTileSize = tileSize;
				int rx = sx + tileSize - JO - tileOverlap;
				int leftTileX = sx + SKILL_TILE_LEFT_INSET;
				int leftTileWidth = Math.max(1, tileSize + tileOverlap - SKILL_TILE_LEFT_INSET);
				int rightTileWidth = Math.max(1, tileSize + JO + tileOverlap - SKILL_TILE_RIGHT_TRIM);
				long tileStart = PerfDebug.start();
				blitTexture(graphics, STAT_TILE_RIGHT, rx, sy, rightTileWidth, tileSize, 36, 36);
				blitTexture(graphics, STAT_TILE_LEFT, leftTileX, sy, leftTileWidth, tileSize, 36, 36);
				PerfDebug.record("skills.tiles", tileStart);
				int iconSize = Math.max(4, (tileSize * 20) / 28);
				int iconOff = (tileSize - iconSize) / 2;
				Identifier icon = SKILL_ICONS.get(skill);
				if (icon != null) {
					long iconStart = PerfDebug.start();
					blitTexture(graphics, icon, sx + iconOff + 2, sy + iconOff - 2, iconSize, iconSize, 25, 25);
					PerfDebug.record("skills.icons", iconStart);
				}
				int baseLevel = data.getLevel(skill);
				int effectiveLevel = data.getEffectiveLevel(skill);
				int modifier = data.getModifier(skill);
				int virtualLevel = virtualLevelsEnabled ? data.getVirtualLevel(skill) : baseLevel;
				boolean preserveDefaultStatColor = skill == SkillType.HITPOINTS || skill == SkillType.PRAYER;
				int effColor = preserveDefaultStatColor
					? 0xFFFFFF00
					: modifier > 0 ? 0xFF4ABAFF : modifier < 0 ? 0xFFFF4444 : 0xFFFFFF00;
				int displayTop;
				if (skill == SkillType.HITPOINTS) {
					displayTop = PlayerStats.getHealth();
				} else if (skill == SkillType.PRAYER) {
					displayTop = PlayerStats.getPrayer();
				} else {
					displayTop = modifier == 0 && virtualLevelsEnabled ? virtualLevel : effectiveLevel;
				}
				String effStr = String.valueOf(displayTop);
				String baseStr = String.valueOf(virtualLevel);
				int effW = scaledTextWidth(mc, effStr, ts);
				int baseW = scaledTextWidth(mc, baseStr, ts);
				int topH = tileSize * 2 / 5;
				int botStart = tileSize * 3 / 5;
				int effY = sy + Math.max(0, (topH - sfh) / 2) + 2;
				int baseY = sy + botStart + Math.max(0, (tileSize - botStart - sfh) / 2) - 4;
				long textStart = PerfDebug.start();
				drawScaledText(graphics, mc, effStr, rx + Math.round((tileSize - effW) / 2.0F), effY, effColor, ts);
				drawScaledText(graphics, mc, baseStr, rx + Math.round((tileSize - baseW) / 2.0F) + 8, baseY, 0xFFFFFF00, ts);
				PerfDebug.record("skills.text", textStart);
				if (!suppressHover && mouseX >= sx && mouseX < sx + skillW && mouseY >= sy && mouseY < sy + tileSize) {
					hoveredSkill = skill;
					hoveredSkillX = sx;
					hoveredSkillY = sy;
					hoveredSkillW = skillW;
					hoveredSkillH = tileSize;
				}
			}
		}
		int barY = gy + ROWS * rowStep;
		int barW = gridW-17;
		int barX = (gx + (gridW - barW) / 2)-5;
		long barStart = PerfDebug.start();
		blitTexture(graphics, STAT_BAR_LEFT, barX, barY, tileSize, tileSize, 36, 36);
		int midW = barW - 2 * tileSize + JO * 2;
		if (midW > 0) blitTexture(graphics, STAT_BAR_MIDDLE, barX + tileSize - JO, barY, midW, tileSize, 36, 36);
		blitTexture(graphics, STAT_BAR_RIGHT, barX + barW - tileSize, barY, tileSize, tileSize, 36, 36);
		PerfDebug.record("skills.bar", barStart);
		int totalLevel = 0;
		double totalExperience = 0.0D;
		for (SkillType s : ALL_SKILLS) {
			totalLevel += virtualLevelsEnabled ? data.getVirtualLevel(s) : data.getLevel(s);
			totalExperience += data.getExperience(s);
		}
		String totalText = "Total level: " + totalLevel;
		int totalTextWidth = scaledTextWidth(mc, totalText, ts);
		int ttx = gx + (gridW - totalTextWidth) / 2;
		int tty = barY + Math.max(0, (tileSize - sfh) / 2) - 5;
		long totalTextStart = PerfDebug.start();
		drawScaledText(graphics, mc, totalText, ttx, tty, 0xFFFFFF00, ts);
		PerfDebug.record("skills.totalText", totalTextStart);
		if (hoveredSkill != null) {
			renderSkillHoverTooltip(graphics, mc, hoveredSkill, hoveredSkillX, hoveredSkillY, hoveredSkillW, hoveredSkillH, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
		} else if (!suppressHover && mouseX >= ttx && mouseX < ttx + totalTextWidth && mouseY >= tty && mouseY < tty + sfh) {
			renderTotalLevelHoverTooltip(graphics, mc, totalLevel, totalExperience, mouseX, mouseY, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
		}
	}

	private float resolveSkillTabTextScale(Minecraft minecraft) {
		float scaled = 1.0F + (float) ((this.scale() - SKILL_TEXT_BASE_WIDGET_SCALE) * 0.28F);
		float minimum = Math.min(1.0F, HudManager.minimumScaledTextScale(minecraft) + 0.1F);
		return Mth.clamp(Math.max(minimum, scaled), minimum, 1.1F);
	}

	private void renderInventory(GuiGraphicsExtractor graphics, Minecraft mc, int x, int y, int padding, int titleHeight) {
		if (mc.player == null) {
			graphics.text(mc.font, Component.literal("No player loaded"), x + padding, y + padding, 0xFFF0D7B0, true);
			return;
		}

		int ss = Math.max(8, (int) Math.round(INVENTORY_SLOT_BASE_SIZE * scale()));
		int gapX = Math.max(0, (int) Math.round(INVENTORY_SLOT_BASE_GAP * scale()));
		int gw = GRID_COLS * ss + (GRID_COLS - 1) * gapX;
		int gx = x + (pixelWidth() - gw) / 2;
		int gy = y + padding + INVENTORY_SLOT_Y_OFFSET;
		int invSize = mc.player.getInventory().getContainerSize();
		Font overlayFont = HudManager.resolveHudTextFont(mc, resolveHudTextScale(this.scale()));
		boolean anyEnchanted = false;
		for (int i = 0; i < invSize; i++) {
			if (mc.player.getInventory().getItem(i).hasFoil()) {
				anyEnchanted = true;
				break;
			}
		}
		itemRenderCount = 0;
		for (int row = 0; row < GRID_ROWS; row++) {
			for (int col = 0; col < GRID_COLS; col++) {
				int slotX = gx + col * (ss + gapX);
				int slotY = gy + row * ss;
				int idx = row * GRID_COLS + col;
				if (idx < INVENTORY_SLOT_ORDER.length) {
					int invIdx = INVENTORY_SLOT_ORDER[idx];
					if (invIdx < 0 || invIdx >= invSize) continue;
					ItemStack stack = mc.player.getInventory().getItem(invIdx);
					if (!stack.isEmpty()) {
						renderScaledItem(graphics, overlayFont, stack, slotX + 1, slotY + 1, ss - 2, true);
						itemRenderCount++;
					}
					if (anyEnchanted) {
						int bc = 0xFFBFA882;
						graphics.fill(slotX, slotY, slotX + ss, slotY + 1, bc);
						graphics.fill(slotX, slotY + ss - 1, slotX + ss, slotY + ss, bc);
						graphics.fill(slotX, slotY, slotX + 1, slotY + ss, bc);
						graphics.fill(slotX + ss - 1, slotY, slotX + ss, slotY + ss, bc);
					}
				}
			}
		}
	}

	private void renderSideEdgeStatBars(GuiGraphicsExtractor graphics, Minecraft mc, int panelX, int panelY, int panelWidth, int panelHeight, int edgeWidth, int padding, ItemStack hoveredStack) {
		if (mc.player == null) return;
		Skills skills = Skills.getInstance();
		int maxHp = Math.max(1, skills.getSnapshot(SkillType.HITPOINTS).level());
		int maxPrayer = Math.max(1, skills.getLevel(SkillType.PRAYER));
		int currentHp = Mth.clamp(PlayerStats.getHealth(), 0, maxHp);
		int currentPrayer = Mth.clamp(PlayerStats.getPrayer(), 0, maxPrayer);
		int previewHp = previewHealedHitpoints(hoveredStack, currentHp, maxHp);
		int previewPrayer = previewRestoredPrayer(mc, hoveredStack, currentPrayer, maxPrayer);

		int edgeXLeft = panelX - edgeWidth;
		int edgeXRight = panelX + panelWidth;
		int edgeY = panelY - 1;
		int edgeHeight = panelHeight + 2;
		renderSideEdgeStatBar(graphics, mc, edgeXLeft, edgeY, edgeWidth, edgeHeight, HITPOINTS_ORB_ICON, currentHp, maxHp, SIDE_HP_FILL_COLOR, previewHp, padding);
		renderSideEdgeStatBar(graphics, mc, edgeXRight, edgeY, edgeWidth, edgeHeight, PRAYER_ORB_ICON, currentPrayer, maxPrayer, SIDE_PRAYER_FILL_COLOR, previewPrayer, padding);
	}

	private void renderSideEdgeStatBar(
		GuiGraphicsExtractor graphics,
		Minecraft mc,
		int barX,
		int topY,
		int barWidth,
		int barHeight,
		Identifier icon,
		int current,
		int max,
		int fillColor,
		int preview,
		int padding
	) {
		graphics.fill(barX, topY, barX + barWidth, topY + barHeight, SIDE_STAT_BG_COLOR);
		float fillRatio = Mth.clamp(current / (float) Math.max(1, max), 0.0F, 1.0F);
		int filledHeight = (int) Math.round(barHeight * fillRatio);
		if (filledHeight > 0) {
			graphics.fill(barX + 1, topY + barHeight - filledHeight, barX + barWidth - 1, topY + barHeight, fillColor);
		}
		if (preview > current) {
			int previewFilledHeight = (int) Math.round(barHeight * Mth.clamp(preview / (float) Math.max(1, max), 0.0F, 1.0F));
			if (previewFilledHeight > filledHeight) {
				graphics.fill(barX + 1, topY + barHeight - previewFilledHeight, barX + barWidth - 1, topY + barHeight - filledHeight, SIDE_STAT_RESTORE_PREVIEW_COLOR);
			}
		}

		int iconSize = Math.max(8, Math.min(26, barWidth - 2));
		int iconX = barX + (barWidth - iconSize) / 2;
		int iconY = topY + Math.max(1, padding / 2);
		blitTexture(graphics, icon, iconX, iconY, iconSize, iconSize, 26, 26);

		String valueText = String.valueOf(preview > current ? preview : current);
		int valueX = barX + (barWidth - mc.font.width(valueText)) / 2;
		int valueY = iconY + iconSize + Math.max(1, (int) Math.round(2 * scale()));
		graphics.text(mc.font, valueText, valueX, valueY, preview > current ? SIDE_STAT_RESTORE_PREVIEW_COLOR : SIDE_STAT_TEXT_COLOR, true);
	}

	private ItemStack hoveredItemStack(Minecraft mc, HudTab tab, int mouseX, int mouseY) {
		if (!((mc.screen instanceof HudInventoryScreen) || CustomContainerScreenRegistry.isCustomContainerScreen(mc.screen)) || HudManager.getInstance().isRuntimeContextMenuOpen()) return ItemStack.EMPTY;
		if (mc.player == null) return ItemStack.EMPTY;
		if (tab == HudTab.INVENTORY) {
			return playerInventorySlotItem(mc, inventorySlotAt(mouseX, mouseY));
		}
		if (tab == HudTab.EQUIPMENT && !rotatingModel) {
			return playerInventorySlotItem(mc, equipmentSlotAt(mouseX, mouseY));
		}
		return ItemStack.EMPTY;
	}

	private int previewHealedHitpoints(ItemStack stack, int currentHp, int maxHp) {
		if (stack.isEmpty() || currentHp >= maxHp) return -1;
		int healAmount = healAmountFromFirstLoreLine(stack);
		if (healAmount <= 0) return -1;
		return Mth.clamp(currentHp + healAmount, 0, maxHp);
	}

	private int previewRestoredPrayer(Minecraft mc, ItemStack stack, int currentPrayer, int maxPrayer) {
		if (stack.isEmpty() || currentPrayer >= maxPrayer) return -1;
		String itemName = stack.getHoverName().getString().toLowerCase(java.util.Locale.ROOT);
		int restoreBase;
		if (itemName.contains("prayer potion")) {
			restoreBase = 7;
		} else if (itemName.contains("super restore")) {
			restoreBase = 8;
		} else {
			return -1;
		}
		boolean bonus = hasPrayerRestoreBonus(mc, itemName.contains("super restore"));
		int percent = bonus ? 27 : 25;
		int restoreAmount = restoreBase + (maxPrayer * percent) / 100;
		return Mth.clamp(currentPrayer + restoreAmount, 0, maxPrayer);
	}

	private boolean hasPrayerRestoreBonus(Minecraft mc, boolean superRestore) {
		if (mc.player == null) return false;
		if (isNamedInventorySlot(mc, 10, "prayer cape", "max cape")) return true;
		if (isNamedInventorySlot(mc, 19, "ring of the gods")) return true;
		for (int slot = 0; slot < mc.player.getInventory().getContainerSize(); slot++) {
			ItemStack stack = mc.player.getInventory().getItem(slot);
			if (stack.isEmpty()) continue;
			String name = stack.getHoverName().getString().toLowerCase(java.util.Locale.ROOT);
			if (name.contains("holy wrench")) return true;
			if (!superRestore && (name.contains("prayer cape") || name.contains("max cape"))) return true;
		}
		return false;
	}

	private boolean isNamedInventorySlot(Minecraft mc, int slot, String... names) {
		ItemStack stack = playerInventorySlotItem(mc, slot);
		if (stack.isEmpty()) return false;
		String itemName = stack.getHoverName().getString().toLowerCase(java.util.Locale.ROOT);
		for (String name : names) {
			if (itemName.contains(name)) return true;
		}
		return false;
	}

	private static int healAmountFromFirstLoreLine(ItemStack stack) {
		ItemLore lore = stack.get(DataComponents.LORE);
		if (lore == null || lore.lines().isEmpty()) return -1;
		String firstLine = lore.lines().get(0).getString();
		Matcher matcher = HEALS_LORE_PATTERN.matcher(firstLine);
		if (!matcher.find()) return -1;
		String number = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
		try {
			return Integer.parseInt(number.replace(",", ""));
		} catch (NumberFormatException ignored) {
			return -1;
		}
	}

	private void renderScaledItem(GuiGraphicsExtractor graphics, Font overlayFont, ItemStack stack, int x, int y, int drawSize, boolean drawOverlay) {
		if (drawSize <= 0) return;
		float scale = drawSize / 16.0F;
		if (Math.abs(scale - 1.0F) < 0.001F) {
			graphics.item(stack, x, y);
			if (drawOverlay) renderLightweightLoreStackSize(graphics, overlayFont, stack, x, y, drawSize);
			return;
		}
		graphics.pose().pushMatrix();
		graphics.pose().translate(x, y);
		graphics.pose().scale(scale, scale);
		graphics.item(stack, 0, 0);
		graphics.pose().popMatrix();
		if (drawOverlay) renderLightweightLoreStackSize(graphics, overlayFont, stack, x, y, drawSize);
	}

	private void renderLoreStackSize(GuiGraphicsExtractor graphics, Font overlayFont, ItemStack stack, int x, int y, int drawSize) {
		StackSizeOverlay.render(graphics, overlayFont, stack, x, y, drawSize);
	}

	private void renderLightweightLoreStackSize(GuiGraphicsExtractor graphics, Font overlayFont, ItemStack stack, int x, int y, int drawSize) {
		StackSizeOverlay.renderLightweightStackSize(graphics, overlayFont, stack, x, y, drawSize, Mth.clamp((float) (this.scale() - 0.95D), 0.5F, 1.45F));
	}

	private void renderHoveredItemTooltip(GuiGraphicsExtractor graphics, Minecraft mc, HudTab tab, int mouseX, int mouseY) {
		ItemStack stack = hoveredItemStack(mc, tab, mouseX, mouseY);
		if (!stack.isEmpty()) {
			graphics.setTooltipForNextFrame(mc.font, stack, mouseX, mouseY);
		}
	}

	private void renderEquipment(GuiGraphicsExtractor graphics, Minecraft mc, int x, int y, int padding, int titleHeight, int mouseX, int mouseY) {
		if (mc.player == null) {
			graphics.text(mc.font, Component.literal("No player loaded"), x + padding, y + titleHeight + padding * 2, 0xFFF0D7B0, true);
			return;
		}
		int lift = Math.max(3, (int) Math.round(4 * scale()));
		int ss = Math.max(10, (int) Math.round(EQUIPMENT_SLOT_BASE_SIZE * scale()));
		int gap = Math.max(0, (int) Math.round(EQUIPMENT_SLOT_BASE_GAP * scale()));
		int cx = x + pixelWidth() / 2;
		int cy = y + pixelHeight() / 2 - lift;
		int mw = Math.max(24, (int) Math.round(26 * scale()));
		int maxMH = Math.max(44, pixelHeight() - padding * 2 - ss - gap - 2);
		int mh = Math.min(Math.max(52, (int) Math.round(68 * scale())), maxMH);
		int mx1 = cx - mw / 2;
		int my1 = cy - mh / 2;
		int mx2 = mx1 + mw;
		int my2 = my1 + mh;
		int topRowY = my1 - ss - gap;
		if (topRowY < y + padding) {
			int sh = y + padding - topRowY;
			my1 += sh;
			my2 += sh;
			topRowY += sh;
		}
		if (my2 > y + pixelHeight() - padding) {
			int sh = my2 - (y + pixelHeight() - padding);
			my1 -= sh;
			my2 -= sh;
			topRowY -= sh;
		}
		modelMinX = mx1;
		modelMinY = my1;
		modelMaxX = mx2;
		modelMaxY = my2;
		modelCenterX = (mx1 + mx2) / 2;
		modelCenterY = (my1 + my2) / 2;
		int lcx = mx1 - gap - ss;
		int rcx = mx2 + gap;
		Font overlayFont = HudManager.resolveHudTextFont(mc, resolveHudTextScale(this.scale()));
		for (int i = 0; i < EQUIPMENT_LEFT_SLOTS.length; i++) drawEquipmentSlot(graphics, mc, overlayFont, EQUIPMENT_LEFT_SLOTS[i], lcx, my1 + i * (ss + gap), ss);
		for (int i = 0; i < EQUIPMENT_RIGHT_SLOTS.length; i++) drawEquipmentSlot(graphics, mc, overlayFont, EQUIPMENT_RIGHT_SLOTS[i], rcx, my1 + i * (ss + gap), ss);
		int ttw = EQUIPMENT_TOP_SLOTS.length * ss + (EQUIPMENT_TOP_SLOTS.length - 1) * gap;
		int tsx = cx - ttw / 2;
		for (int i = 0; i < EQUIPMENT_TOP_SLOTS.length; i++) drawEquipmentSlot(graphics, mc, overlayFont, EQUIPMENT_TOP_SLOTS[i], tsx + i * (ss + gap), topRowY, ss);
		int bs = Math.max(12, (int) Math.round(EQUIPMENT_BUTTON_BASE_SIZE * scale()));
		int bg = Math.max(0, (int) Math.round(EQUIPMENT_BUTTON_BASE_GAP * scale()));
		int tbw = 4 * bs + 3 * bg;
		int bsx = cx - tbw / 2;
		int bsy = y + pixelHeight() - padding - bs - lift;
		buttonSize = bs;
		Identifier[] icons = {BTN_STATS, BTN_GUIDE_PRICES, BTN_ITEMS_LOST, BTN_CALL_FOLLOWER};
		for (int i = 0; i < 4; i++) {
			int bx = bsx + i * (bs + bg);
			buttonBoundsX[i] = bx;
			buttonBoundsY[i] = bsy;
			boolean hovered = mouseX >= bx && mouseX < bx + bs && mouseY >= bsy && mouseY < bsy + bs;
			blitTexture(graphics, hovered ? BTN_BG_HOVERED : BTN_BG, bx, bsy, bs, bs, 32, 32);
			blitTexture(graphics, icons[i], bx, bsy, bs, bs, 32, 32);
		}
		int entitySize = Math.max(24, (int) Math.round(32 * scale()));
		InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, mx1, my1, mx2, my2, entitySize, 0.0625F, modelCenterX + modelMouseOffsetX, modelCenterY, mc.player);
	}

	private static void drawFrameSlices(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		int innerWidth = Math.max(1, width - 8);
		int innerHeight = Math.max(1, height - 8);
		blitTexture(graphics, FRAME_MIDDLE, x + 4, y + 4, innerWidth, innerHeight, 4, 4);
		blitTexture(graphics, FRAME_TOP, x + 4, y, innerWidth, 4, 4, 4);
		blitTexture(graphics, FRAME_BOTTOM, x + 4, y + height - 4, innerWidth, 4, 4, 4);
		blitTexture(graphics, FRAME_LEFT, x, y + 4, 4, innerHeight, 4, 4);
		blitTexture(graphics, FRAME_RIGHT, x + width - 4, y + 4, 4, innerHeight, 4, 4);
		blitTexture(graphics, FRAME_TOP_LEFT, x, y, 4, 4, 4, 4);
		blitTexture(graphics, FRAME_TOP_RIGHT, x + width - 4, y, 4, 4, 4, 4);
		blitTexture(graphics, FRAME_BOTTOM_LEFT, x, y + height - 4, 4, 4, 4, 4);
		blitTexture(graphics, FRAME_BOTTOM_RIGHT, x + width - 4, y + height - 4, 4, 4, 4, 4);
	}

	private static String formatXp(double xp) {
        double xpAsDouble  = (int)xp;
		return String.format(java.util.Locale.US, "%,.0f", xpAsDouble);
	}
	private static String formatXp(long xp) {
		long xpAsLong = (int)xp;
		return String.format(java.util.Locale.US, "%,d", xp);
	}

	private static void drawTooltipRow(GuiGraphicsExtractor graphics, Font font, String label, String value, int textX, int rightEdge, int y) {
		graphics.text(font, label, textX, y, 0xFFFF981F, false);
		graphics.text(font, value, rightEdge - font.width(value), y, 0xFFFFFFFF, false);
	}

	private static void renderSkillHoverTooltip(GuiGraphicsExtractor graphics, Minecraft minecraft, SkillType skill, int anchorX, int anchorY, int anchorW, int anchorH, int screenW, int screenH) {
		Skills data = Skills.getInstance();
		boolean virtualLevelsEnabled = HudManager.getInstance().isVirtualLevelsEnabled();
		int level = virtualLevelsEnabled ? data.getVirtualLevel(skill) : data.getLevel(skill);
		double xp = data.getExperience(skill);
		int maxLevel = virtualLevelsEnabled ? Skills.MAX_VIRTUAL_LEVEL : Skills.MAX_REAL_LEVEL;
		boolean showNextLevelRows = virtualLevelsEnabled ? xp < MAX_XP : level < Skills.MAX_REAL_LEVEL;
		long nextLevelXp = showNextLevelRows && level < maxLevel ? data.getExperienceAtLevel(level + 1) : MAX_XP;
		long remainingXp = Math.max(0L, nextLevelXp - (long) xp);
		String skillName = formatSkillName(skill);
		String labelA = skillName + " XP:";
		String labelB = "Next level at:";
		String labelC = "Remaining XP:";
		String valueA = formatXp(xp);
		String valueB = formatXp(nextLevelXp);
		String valueC = formatXp(remainingXp);
		Font font = minecraft.font;
		int lineH = font.lineHeight + 2;
		int pad = 6;
		int innerGap = 8;
		int rowW = font.width(labelA) + innerGap + font.width(valueA);
		if (showNextLevelRows) {
			rowW = Math.max(rowW, Math.max(font.width(labelB) + innerGap + font.width(valueB), font.width(labelC) + innerGap + font.width(valueC)));
		}
		int boxW = rowW + pad * 2;
		int rowCount = showNextLevelRows ? 3 : 1;
		int boxH = pad * 2 + lineH * rowCount;
		int tooltipX = Mth.clamp(anchorX + (anchorW - boxW) / 2, 2, screenW - boxW - 2);
		int preferredTooltipY = anchorY + anchorH + 4;
		int tooltipY = preferredTooltipY + boxH <= screenH - 2
			? preferredTooltipY
			: Math.max(2, anchorY - boxH - 4);
		graphics.fill(tooltipX, tooltipY, tooltipX + boxW, tooltipY + boxH, 0xFF3B2412);
		int borderColor = 0xFFBFA882;
		graphics.fill(tooltipX, tooltipY, tooltipX + boxW, tooltipY + 1, borderColor);
		graphics.fill(tooltipX, tooltipY + boxH - 1, tooltipX + boxW, tooltipY + boxH, borderColor);
		graphics.fill(tooltipX, tooltipY, tooltipX + 1, tooltipY + boxH, borderColor);
		graphics.fill(tooltipX + boxW - 1, tooltipY, tooltipX + boxW, tooltipY + boxH, borderColor);
		int textX = tooltipX + pad;
		int rightEdge = tooltipX + boxW - pad;
		int curY = tooltipY + pad;
		drawTooltipRow(graphics, font, labelA, valueA, textX, rightEdge, curY);
		if (showNextLevelRows) {
			curY += lineH;
			drawTooltipRow(graphics, font, labelB, valueB, textX, rightEdge, curY);
			curY += lineH;
			drawTooltipRow(graphics, font, labelC, valueC, textX, rightEdge, curY);
		}
	}

	private static String formatSkillName(SkillType skill) {
		String[] parts = skill.name().toLowerCase(java.util.Locale.ROOT).split("_");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].isEmpty()) continue;
			if (builder.length() > 0) builder.append(' ');
			builder.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
		}
		return builder.toString();
	}

	private static void renderTotalLevelHoverTooltip(GuiGraphicsExtractor graphics, Minecraft minecraft, int totalLevel, double totalExperience, int mouseX, int mouseY, int screenW, int screenH) {
		String title = "Total";
		String labelA = "Total level:";
		String labelB = "Total XP:";
		String valueA = formatXp(totalLevel);
		String valueB = formatXp(totalExperience);
		Font font = minecraft.font;
		int lineH = font.lineHeight + 2;
		int pad = 6;
		int innerGap = 8;
		int titleW = font.width(title);
		int rowW = Math.max(font.width(labelA) + innerGap + font.width(valueA), font.width(labelB) + innerGap + font.width(valueB));
		int boxW = Math.max(titleW, rowW) + pad * 2;
		int boxH = pad + lineH + 2 + 4 + lineH * 2 + pad;
		int tooltipX = Math.min(mouseX + 12, screenW - boxW - 2);
		int tooltipY = Math.max(2, Math.min(mouseY - boxH / 2, screenH - boxH - 2));
		graphics.fill(tooltipX, tooltipY, tooltipX + boxW, tooltipY + boxH, 0xFF3B2412);
		int borderColor = 0xFFBFA882;
		graphics.fill(tooltipX, tooltipY, tooltipX + boxW, tooltipY + 1, borderColor);
		graphics.fill(tooltipX, tooltipY + boxH - 1, tooltipX + boxW, tooltipY + boxH, borderColor);
		graphics.fill(tooltipX, tooltipY, tooltipX + 1, tooltipY + boxH, borderColor);
		graphics.fill(tooltipX + boxW - 1, tooltipY, tooltipX + boxW, tooltipY + boxH, borderColor);
		int textX = tooltipX + pad;
		int rightEdge = tooltipX + boxW - pad;
		int curY = tooltipY + pad;
		graphics.text(font, title, tooltipX + (boxW - titleW) / 2, curY, 0xFFFFFF00, false);
		curY += lineH;
		graphics.fill(tooltipX + 2, curY + 1, tooltipX + boxW - 2, curY + 2, borderColor);
		curY += 4;
		drawTooltipRow(graphics, font, labelA, valueA, textX, rightEdge, curY);
		curY += lineH;
		drawTooltipRow(graphics, font, labelB, valueB, textX, rightEdge, curY);
	}

	private static Identifier texture(String path) {
		return Identifier.fromNamespaceAndPath(MinescapeAddon.MOD_ID, "textures/gui/runescape/" + path);
	}
}

