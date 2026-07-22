package com.zylr.client.screen.overridescreens;

import com.zylr.client.hud.HudManager;
import com.zylr.client.hud.HudTab;
import com.zylr.mixin.SlotAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import org.lwjgl.glfw.GLFW;

public final class SearchScreen extends Screen implements HudTabRestoringScreen {
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
	private static final Identifier DECLINE_ICON = texture("sailing/cancel.png");
	private static final Identifier ACCEPT_ICON = texture("icon/checkmark_green.png");
	private static final Identifier TEXT_FIELD_SPRITE = Identifier.withDefaultNamespace("container/anvil/text_field");
	private static final Identifier TEXT_FIELD_DISABLED_SPRITE = Identifier.withDefaultNamespace("container/anvil/text_field_disabled");
	private static final int SCREEN_WIDTH = 365;
	private static final int SCREEN_HEIGHT = 180;
	private static final int OUTER_EDGE = 36;
	private static final int EDGE_OUTSET = 15;
	private static final int HEADER_TEXT_Y = 10;
	private static final int HEADER_CLOSE_X = SCREEN_WIDTH - 31;
	private static final int HEADER_CLOSE_Y = 7;
	private static final int HEADER_CLOSE_W = 22;
	private static final int HEADER_CLOSE_H = 19;
	private static final int FIELD_X = 56;
	private static final int FIELD_Y = 72;
	private static final int FIELD_W = 252;
	private static final int FIELD_H = 16;
	private static final int FIELD_TEXT_X = FIELD_X + 10;
	private static final int FIELD_TEXT_Y = FIELD_Y + 5;
	private static final int BUTTON_Y = 120;
	private static final int DECLINE_X = 110;
	private static final int ACCEPT_X = 218;
	private static final int BUTTON_SIZE = 36;
	private static final int TEXT_COLOR = 0xFFD4C39B;
	private static final int LABEL_COLOR = 0xFFD4C39B;
	private static final int BUTTON_HOVER_COLOR = 0x40FFFFFF;
	private static final int BUTTON_DISABLED_COLOR = 0xA0000000;

	private final AnvilMenu menu;
	private final Inventory inventory;
	private int leftPos;
	private int topPos;
	private HudTab previousHudTab;
	private EditBox searchField;
	private boolean syncingField;
	private int pendingInitialSyncTicks = 2;
	private boolean initialSyncApplied;

	public SearchScreen(AnvilMenu menu, Inventory inventory, Component title) {
		super(title);
		this.menu = menu;
		this.inventory = inventory;
	}

	@Override
	protected void init() {
		this.leftPos = (this.width - SCREEN_WIDTH) / 2;
		this.topPos = (this.height - SCREEN_HEIGHT) / 2;
		this.hideMenuSlots();
		this.buildSearchField();
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
	public void tick() {
		super.tick();
		if (this.searchField == null) {
			return;
		}

		if (!this.initialSyncApplied && this.pendingInitialSyncTicks > 0) {
			this.pendingInitialSyncTicks--;
			if (this.pendingInitialSyncTicks == 0) {
				this.applyInitialSearchValue();
			}
		}

		boolean editable = this.hasInputItem();
		this.searchField.setEditable(editable);
		if (!editable && !this.searchField.getValue().isEmpty()) {
			this.syncingField = true;
			this.searchField.setValue("");
			this.syncingField = false;
		}
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		if (this.minecraft != null) {
			HudManager.getInstance().render(graphics, this.minecraft, mouseX, mouseY, delta, false, null);
			HudManager.getInstance().renderRuntimeContextMenu(graphics, this.minecraft, mouseX, mouseY);
		}
		this.drawOuterFrame(graphics);
		this.drawTitle(graphics);
		this.drawFieldLabel(graphics);
		this.drawSearchFieldBackground(graphics);
		this.drawHeaderButtons(graphics, mouseX, mouseY);
		this.drawActionButtons(graphics, mouseX, mouseY);
		super.extractRenderState(graphics, mouseX, mouseY, delta);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		int mouseX = (int) event.x();
		int mouseY = (int) event.y();
		HudManager.getInstance().updateCursor(mouseX, mouseY);
		if (this.handleHeaderClick(mouseX, mouseY)) {
			return true;
		}
		if (this.handleActionClick(mouseX, mouseY)) {
			return true;
		}
		if (this.minecraft != null && HudManager.getInstance().isContentPanelTabHit(mouseX, mouseY, this.width, this.height)) {
			return true;
		}
		if (super.mouseClicked(event, doubleClick)) {
			return true;
		}
		long window = this.minecraft != null ? this.minecraft.getWindow().handle() : 0L;
		boolean shift = window != 0L && (
			GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
				|| GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS
		);
		return this.minecraft != null && HudManager.getInstance().clickHud(mouseX, mouseY, this.width, this.height, event.button(), shift);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (event.isEscape()) {
			this.onClose();
			return true;
		}
		if (event.key() == GLFW.GLFW_KEY_ENTER || event.key() == GLFW.GLFW_KEY_KP_ENTER) {
			return this.acceptSelection();
		}
		if (super.keyPressed(event)) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		HudManager.getInstance().updateCursor(mouseX, mouseY);
		super.mouseMoved(mouseX, mouseY);
	}

	private void buildSearchField() {
		this.searchField = new EditBox(this.font, this.leftPos + FIELD_TEXT_X, this.topPos + FIELD_TEXT_Y, FIELD_W - 8, 12, Component.literal("Search"));
		this.searchField.setCanLoseFocus(true);
		this.searchField.setBordered(false);
		this.searchField.setTextShadow(false);
		this.searchField.setTextColor(0xFFFFFFFF);
		this.searchField.setTextColorUneditable(0xFF8F8F8F);
		this.searchField.setMaxLength(AnvilMenu.MAX_NAME_LENGTH);
		this.searchField.setHint(Component.literal(""));
		this.searchField.setResponder(this::handleSearchChanged);
		this.searchField.setEditable(this.hasInputItem());
		this.addRenderableWidget(this.searchField);
		this.setInitialFocus(this.searchField);
	}

	private void handleSearchChanged(String value) {
		if (this.syncingField) {
			return;
		}
		this.sendRename(value);
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

	private void hideMenuSlots() {
		for (Slot slot : this.menu.slots) {
			((SlotAccessor) slot).minescapeaddon$setX(-1000);
			((SlotAccessor) slot).minescapeaddon$setY(-1000);
		}
	}

	private void drawTitle(GuiGraphicsExtractor graphics) {
		String menuTitle = this.title.getString();
		int titleX = (SCREEN_WIDTH - this.font.width(menuTitle)) / 2;
		graphics.text(this.font, menuTitle, this.leftPos + titleX, this.topPos + HEADER_TEXT_Y, TEXT_COLOR, false);
	}

	private void drawFieldLabel(GuiGraphicsExtractor graphics) {
		//graphics.text(this.font, Component.literal("Search"), this.leftPos + FIELD_X, this.topPos + FIELD_Y - 16, LABEL_COLOR, false);
	}

	private void drawSearchFieldBackground(GuiGraphicsExtractor graphics) {
		Identifier sprite = this.hasInputItem() ? TEXT_FIELD_SPRITE : TEXT_FIELD_DISABLED_SPRITE;
		graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, this.leftPos + FIELD_X, this.topPos + FIELD_Y, FIELD_W, FIELD_H);
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

	private void drawActionButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		this.drawActionButton(graphics, mouseX, mouseY, DECLINE_X, BUTTON_Y, DECLINE_ICON, true);
		this.drawActionButton(graphics, mouseX, mouseY, ACCEPT_X, BUTTON_Y, ACCEPT_ICON, this.hasInputItem());
	}

	private void drawActionButton(GuiGraphicsExtractor graphics, int mouseX, int mouseY, int buttonX, int buttonY, Identifier icon, boolean enabled) {
		int drawX = this.leftPos + buttonX;
		int drawY = this.topPos + buttonY;
		graphics.fill(drawX, drawY, drawX + BUTTON_SIZE, drawY + BUTTON_SIZE, 0x7F1B140D);
		graphics.fill(drawX + 1, drawY + 1, drawX + BUTTON_SIZE - 1, drawY + BUTTON_SIZE - 1, 0xC03A3021);
		if (enabled && contains(mouseX, mouseY, drawX, drawY, BUTTON_SIZE, BUTTON_SIZE)) {
			graphics.fill(drawX + 1, drawY + 1, drawX + BUTTON_SIZE - 1, drawY + BUTTON_SIZE - 1, BUTTON_HOVER_COLOR);
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, icon, drawX + 8, drawY + 8, 0.0F, 0.0F, 20, 20, 20, 20, 20, 20);
		if (!enabled) {
			graphics.fill(drawX + 1, drawY + 1, drawX + BUTTON_SIZE - 1, drawY + BUTTON_SIZE - 1, BUTTON_DISABLED_COLOR);
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

	private boolean handleActionClick(int mouseX, int mouseY) {
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		if (contains(relativeX, relativeY, DECLINE_X, BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE)) {
			return this.declineSelection();
		}
		if (contains(relativeX, relativeY, ACCEPT_X, BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE)) {
			return this.acceptSelection();
		}
		return false;
	}

	private boolean declineSelection() {
		this.sendMenuClick(AnvilMenu.ADDITIONAL_SLOT, 0, ContainerInput.PICKUP);
		return true;
	}

	private boolean acceptSelection() {
		if (!this.hasInputItem()) {
			return false;
		}
		this.sendMenuClick(AnvilMenu.RESULT_SLOT, 0, ContainerInput.PICKUP);
		return true;
	}

	private void sendRename(String value) {
		Minecraft minecraft = this.minecraft;
		if (minecraft == null || minecraft.player == null) {
			return;
		}

		ClientPacketListener connection = minecraft.getConnection();
		if (connection == null) {
			return;
		}

		if (this.menu.setItemName(value)) {
			connection.send(new ServerboundRenameItemPacket(value));
		}
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

	private boolean hasInputItem() {
		return this.menu.getSlot(AnvilMenu.INPUT_SLOT).hasItem();
	}

	private String initialSearchValue() {
		Slot inputSlot = this.menu.getSlot(AnvilMenu.INPUT_SLOT);
		if (!inputSlot.hasItem()) {
			return "";
		}
		return inputSlot.getItem().getHoverName().getString();
	}

	private void applyInitialSearchValue() {
		String initialValue = this.initialSearchValue();
		this.initialSyncApplied = true;
		if (initialValue.isEmpty()) {
			return;
		}
		this.menu.setItemName(initialValue);
		this.syncingField = true;
		this.searchField.setValue(initialValue);
		this.syncingField = false;
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
