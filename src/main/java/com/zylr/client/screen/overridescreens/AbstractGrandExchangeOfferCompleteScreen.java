package com.zylr.client.screen.overridescreens;

import com.zylr.client.hud.StackSizeOverlay;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractGrandExchangeOfferCompleteScreen extends AbstractGrandExchangeScreen {
	private static final Identifier SELECTED_ITEM_BOX = texture("ge/selected_item_box.png");
	private static final Identifier COLLECTION_BOX_OFFER_SELL = texture("ge/collection_box_offer_sell.png");
	private static final Identifier GE_BORDER_TOP = texture("ge/border_offer_top.png");
	private static final Identifier NUMBER_FIELD_EDGE_LEFT = texture("ge/number_field_edge_left.png");
	private static final Identifier NUMBER_FIELD_EDGE_RIGHT = texture("ge/number_field_edge_right.png");
	private static final Identifier NUMBER_FIELD_MIDDLE = texture("ge/number_field_middle.png");
	private static final Identifier BACK_ARROW = texture("ge/back_arrow_button.png");
	private static final Identifier TUTORIAL_BUTTON = texture("button/tutorial.png");
	private static final Identifier TUTORIAL_BUTTON_HOVERED = texture("button/tutorial_hovered.png");
	private static final Identifier CANCEL_OFFER_BUTTON = texture("ge/cancel_offer_button.png");
	private static final Identifier CANCEL_OFFER_BUTTON_HOVERED = texture("ge/cancel_offer_button_hovered.png");

	private static final int PANEL_X = 6;
	private static final int PANEL_Y = 37;
	private static final int PANEL_W = 352;
	private static final int PANEL_H = 72;
	private static final int LEFT_PANEL_W = 110;
	private static final int ITEM_BOX_W = 30;
	private static final int ITEM_BOX_H = 30;
	private static final int ITEM_BOX_X = (LEFT_PANEL_W / 2) - (ITEM_BOX_W / 2);
	private static final int ITEM_BOX_Y = 24;
	private static final int HEADER_BOX_X = 9;
	private static final int HEADER_BOX_W = 90;
	private static final int HEADER_BOX_H = 15;
	private static final int PROMPT_TEXT_X = 119;
	private static final int PROMPT_TITLE_Y = 9;
	private static final int PROMPT_BODY_Y = 27;
	private static final int PROMPT_TEXT_W = PANEL_W - PROMPT_TEXT_X - 8;
	private static final int MID_PANEL_Y = 115;
	private static final int MID_PANEL_H = 76;
	private static final int HALF_PANEL_W = 176;
	private static final int RIGHT_PANEL_X = PANEL_X + HALF_PANEL_W;
	private static final int DARK_BAR_X = 31;
	private static final int DARK_BAR_Y = 20;
	private static final int DARK_BAR_W = 119;
	private static final int DARK_BAR_H = 20;
	private static final int BOTTOM_BAR_X = 39;
	private static final int BOTTOM_BAR_Y = 197;
	private static final int BOTTOM_BAR_W = 286;
	private static final int BOTTOM_BAR_H = 20;
	private static final int CONFIRM_DIVIDER_X = 5;
	private static final int CONFIRM_DIVIDER_Y = 206;
	private static final int CONFIRM_DIVIDER_W = 355;
	private static final int GE_BORDER_TILE = 32;
	private static final int BACK_ARROW_X = 14;
	private static final int BACK_ARROW_Y = 228;
	private static final int BACK_ARROW_W = 30;
	private static final int BACK_ARROW_H = 23;
	private static final int CANCEL_BUTTON_X = 270;
	private static final int CANCEL_BUTTON_Y = 223;
	private static final int CANCEL_BUTTON_W = 10;
	private static final int CANCEL_BUTTON_H = 10;
	private static final int COMPLETE_BAR_X = 90;
	private static final int COMPLETE_BAR_Y = 235;
	private static final int COMPLETE_BAR_W = 190;
	private static final int COMPLETE_BAR_H = 12;
	private static final int BOTTOM_RIGHT_BOX_Y = 223;
	private static final int BOTTOM_RIGHT_BOX_1_X = 286;
	private static final int BOTTOM_RIGHT_BOX_2_X = 321;
	private static final int COLLECTION_ICON_X = 95;
	private static final int COLLECTION_ICON_Y = 1;
	private static final int ITEM_RENDER_SIZE = 24;
	private static final int ITEM_RENDER_X = ITEM_BOX_X + (ITEM_BOX_W - 16) / 2;
	private static final int ITEM_RENDER_Y = ITEM_BOX_Y + (ITEM_BOX_H - 16) / 2;
	private static final int TUTORIAL_BUTTON_X = HEADER_CLOSE_X - 22;
	private static final int TUTORIAL_BUTTON_Y = HEADER_CLOSE_Y + 1;
	private static final int TUTORIAL_BUTTON_W = 17;
	private static final int TUTORIAL_BUTTON_H = 17;
	private static final int SLOT_BACK = 45;
	private static final int SLOT_TUTORIAL = 46;
	private static final int SLOT_COMPLETE_PROGRESS = 48;
	private static final int SLOT_CANCEL = 50;
	private static final int SLOT_BOTTOM_RIGHT_FIRST = 51;
	private static final int SLOT_BOTTOM_RIGHT_SECOND = 52;
	private static final int SLOT_QUANTITY_TOOLTIP = 28;
	private static final int SLOT_PRICE_TOOLTIP = 33;

	private static final int TEXT_COLOR = 0xFFF6A230;
	private static final int BODY_TEXT_COLOR = 0xFFFFD27F;
	private static final int PROGRESS_YELLOW_COLOR = 0xFFD88020;
	private static final int PROGRESS_GREEN_COLOR = 0xFF005F00;
	private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d[\\d,]*)");
	private static final Pattern SLASH_NUMBER_PATTERN = Pattern.compile("(\\d[\\d,]*)\\s*/\\s*(\\d[\\d,]*)");

	protected AbstractGrandExchangeOfferCompleteScreen(ChestMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	protected String getMenuTitle() {
		return "Grand Exchange";
	}

	@Override
	protected String getSubtitle() {
		return "";
	}

	@Override
	protected void layoutSlots() {
		this.hideAllSlots();
	}

	@Override
	protected void drawContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int panelX = this.leftPos + PANEL_X;
		int panelY = this.topPos + PANEL_Y;
		this.drawDoubleLineBox(graphics, panelX, panelY, PANEL_W, PANEL_H);
		this.drawVerticalDivider(graphics, panelX + LEFT_PANEL_W, panelY, PANEL_H);
		this.drawOfferHeader(graphics, panelX, panelY);
		this.drawItemPrompt(graphics, panelX, panelY);

		int leftMidX = this.leftPos + PANEL_X;
		int rightMidX = this.leftPos + RIGHT_PANEL_X;
		int midY = this.topPos + MID_PANEL_Y;
		this.drawDoubleLineBox(graphics, leftMidX, midY, HALF_PANEL_W, MID_PANEL_H);
		this.drawDoubleLineBox(graphics, rightMidX, midY, HALF_PANEL_W, MID_PANEL_H);
		this.drawQuantityPanel(graphics, leftMidX, midY);
		this.drawPricePanel(graphics, rightMidX, midY);

		this.drawWideBar(graphics, this.leftPos + BOTTOM_BAR_X, this.topPos + BOTTOM_BAR_Y, BOTTOM_BAR_W, BOTTOM_BAR_H);
		String totalPrice = this.tooltipSlashNumber(SLOT_PRICE_TOOLTIP, 1);
		this.drawCenteredFieldValue(graphics, this.leftPos + BOTTOM_BAR_X, this.topPos + BOTTOM_BAR_Y, BOTTOM_BAR_W, BOTTOM_BAR_H, totalPrice != null ? totalPrice + " coins" : "");
		this.drawConfirmDivider(graphics);
		this.drawTutorialButton(graphics, mouseX, mouseY);
		this.drawBackArrow(graphics);
		this.drawCancelButton(graphics, mouseX, mouseY);
		this.drawCompleteBar(graphics);
		this.drawBottomRightBoxes(graphics);
		this.drawHoverTooltips(graphics, mouseX, mouseY);
	}

	@Override
	protected Slot findInteractiveSlot(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		int mouseX = (int) event.x();
		int mouseY = (int) event.y();
		int button = event.button();
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + BOTTOM_RIGHT_BOX_1_X, this.topPos + BOTTOM_RIGHT_BOX_Y, ITEM_BOX_W, ITEM_BOX_H, SLOT_BOTTOM_RIGHT_FIRST, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + BOTTOM_RIGHT_BOX_2_X, this.topPos + BOTTOM_RIGHT_BOX_Y, ITEM_BOX_W, ITEM_BOX_H, SLOT_BOTTOM_RIGHT_SECOND, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + BACK_ARROW_X, this.topPos + BACK_ARROW_Y, BACK_ARROW_W, BACK_ARROW_H, SLOT_BACK, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + CANCEL_BUTTON_X, this.topPos + CANCEL_BUTTON_Y, CANCEL_BUTTON_W, CANCEL_BUTTON_H, SLOT_CANCEL, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + TUTORIAL_BUTTON_X, this.topPos + TUTORIAL_BUTTON_Y, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H, SLOT_TUTORIAL, button)) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	protected abstract String getOfferHeaderLabel();

	private void drawOfferHeader(GuiGraphicsExtractor graphics, int panelX, int panelY) {
		String label = this.getOfferHeaderLabel();
		this.drawDoubleLineBox(graphics, panelX, panelY, LEFT_PANEL_W + 1, HEADER_BOX_H);
		this.drawDoubleLineBox(graphics, panelX, panelY, LEFT_PANEL_W + 1, PANEL_H);
		int labelX = panelX + HEADER_BOX_X + (HEADER_BOX_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, labelX, panelY + 4, TEXT_COLOR, false);
		graphics.blit(RenderPipelines.GUI_TEXTURED, COLLECTION_BOX_OFFER_SELL, panelX + COLLECTION_ICON_X, panelY + COLLECTION_ICON_Y, 0.0F, 0.0F, 11, 12, 11, 12, 11, 12);
		graphics.blit(RenderPipelines.GUI_TEXTURED, SELECTED_ITEM_BOX, panelX + ITEM_BOX_X, panelY + ITEM_BOX_Y, 0.0F, 0.0F, ITEM_BOX_W, ITEM_BOX_H, ITEM_BOX_W, ITEM_BOX_H, ITEM_BOX_W, ITEM_BOX_H);
		this.drawSelectedItem(graphics, panelX, panelY);
	}

	private void drawItemPrompt(GuiGraphicsExtractor graphics, int panelX, int panelY) {
		String header = this.selectedItemName();
		String body = this.selectedItemDescription();
		graphics.text(this.font, header != null ? header : "Choose an item....", panelX + PROMPT_TEXT_X, panelY + PROMPT_TITLE_Y, TEXT_COLOR, false);
		this.drawWrappedText(
			graphics,
			body != null ? body : "Choose an item from your inventory to sell.",
			panelX + PROMPT_TEXT_X,
			panelY + PROMPT_BODY_Y,
			PROMPT_TEXT_W,
			BODY_TEXT_COLOR
		);
	}

	private void drawQuantityPanel(GuiGraphicsExtractor graphics, int x, int y) {
		String label = "Quantity:";
		int labelX = x + (HALF_PANEL_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, labelX, y + 9, TEXT_COLOR, false);
		this.drawWideBar(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H);
		this.drawCenteredFieldValue(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H, this.tooltipNumber(SLOT_QUANTITY_TOOLTIP, 0));
	}

	private void drawPricePanel(GuiGraphicsExtractor graphics, int x, int y) {
		String label = "Price per item:";
		int labelX = x + (HALF_PANEL_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, labelX, y + 9, TEXT_COLOR, false);
		this.drawWideBar(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H);
		this.drawCenteredFieldValue(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H, this.tooltipSlashNumber(SLOT_PRICE_TOOLTIP, 0));
	}

	private void drawWideBar(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, NUMBER_FIELD_EDGE_LEFT, x, y, 0.0F, 0.0F, 4, height, 4, height, 4, 20);
		for (int drawX = 4; drawX < width - 4; drawX += 4) {
			int pieceWidth = Math.min(4, width - 4 - drawX);
			graphics.blit(RenderPipelines.GUI_TEXTURED, NUMBER_FIELD_MIDDLE, x + drawX, y, 0.0F, 0.0F, pieceWidth, height, 4, height, 4, 20);
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, NUMBER_FIELD_EDGE_RIGHT, x + width - 6, y, 0.0F, 0.0F, 4, height, 4, height, 4, 20);
	}

	private void drawBackArrow(GuiGraphicsExtractor graphics) {
		int x = this.leftPos + BACK_ARROW_X;
		int y = this.topPos + BACK_ARROW_Y;
		graphics.blit(RenderPipelines.GUI_TEXTURED, BACK_ARROW, x, y, 0.0F, 0.0F, BACK_ARROW_W, BACK_ARROW_H, BACK_ARROW_W, BACK_ARROW_H, BACK_ARROW_W, BACK_ARROW_H);
	}

	private void drawTutorialButton(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int x = this.leftPos + TUTORIAL_BUTTON_X;
		int y = this.topPos + TUTORIAL_BUTTON_Y;
		Identifier texture = contains(mouseX, mouseY, x, y, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H) ? TUTORIAL_BUTTON_HOVERED : TUTORIAL_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H);
	}

	private void drawCancelButton(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		Slot cancelSlot = this.slotById(SLOT_CANCEL);
		if (cancelSlot == null || !cancelSlot.hasItem()) {
			return;
		}

		int x = this.leftPos + CANCEL_BUTTON_X;
		int y = this.topPos + CANCEL_BUTTON_Y;
		Identifier texture = contains(mouseX, mouseY, x, y, CANCEL_BUTTON_W, CANCEL_BUTTON_H)
			? CANCEL_OFFER_BUTTON_HOVERED
			: CANCEL_OFFER_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, CANCEL_BUTTON_W, CANCEL_BUTTON_H, CANCEL_BUTTON_W, CANCEL_BUTTON_H, CANCEL_BUTTON_W, CANCEL_BUTTON_H);
	}

	private void drawCompleteBar(GuiGraphicsExtractor graphics) {
		int x = this.leftPos + COMPLETE_BAR_X;
		int y = this.topPos + COMPLETE_BAR_Y;
		graphics.fill(x, y, x + COMPLETE_BAR_W, y + COMPLETE_BAR_H, 0xFF171411);
		this.drawCompleteProgressFill(graphics, x, y, COMPLETE_BAR_W, COMPLETE_BAR_H, this.slotById(SLOT_COMPLETE_PROGRESS));
		this.drawDoubleLineBox(graphics, x, y, COMPLETE_BAR_W, COMPLETE_BAR_H);
	}

	private void drawCompleteProgressFill(GuiGraphicsExtractor graphics, int x, int y, int width, int height, Slot progressSlot) {
		if (progressSlot == null || !progressSlot.hasItem()) {
			return;
		}

		int damageValue = progressSlot.getItem().getDamageValue();
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

	private void drawBottomRightBoxes(GuiGraphicsExtractor graphics) {
		int y = this.topPos + BOTTOM_RIGHT_BOX_Y;
		int firstBoxX = this.leftPos + BOTTOM_RIGHT_BOX_1_X;
		int secondBoxX = this.leftPos + BOTTOM_RIGHT_BOX_2_X;
		graphics.blit(RenderPipelines.GUI_TEXTURED, SELECTED_ITEM_BOX, firstBoxX, y, 0.0F, 0.0F, ITEM_BOX_W, ITEM_BOX_H, ITEM_BOX_W, ITEM_BOX_H, ITEM_BOX_W, ITEM_BOX_H);
		graphics.blit(RenderPipelines.GUI_TEXTURED, SELECTED_ITEM_BOX, secondBoxX, y, 0.0F, 0.0F, ITEM_BOX_W, ITEM_BOX_H, ITEM_BOX_W, ITEM_BOX_H, ITEM_BOX_W, ITEM_BOX_H);
		this.drawBoxItem(graphics, this.slotById(51), firstBoxX, y);
		this.drawBoxItem(graphics, this.slotById(52), secondBoxX, y);
	}

	private void drawVerticalDivider(GuiGraphicsExtractor graphics, int x, int y, int height) {
		graphics.fill(x, y + 1, x + 1, y + height - 1, DOUBLE_LINE_OUTER_COLOR);
		graphics.fill(x + 1, y + 1, x + 2, y + height - 1, DOUBLE_LINE_INNER_COLOR);
	}

	private void drawConfirmDivider(GuiGraphicsExtractor graphics) {
		this.tileFill(
			graphics,
			GE_BORDER_TOP,
			this.leftPos + CONFIRM_DIVIDER_X,
			this.topPos + CONFIRM_DIVIDER_Y,
			CONFIRM_DIVIDER_W,
			GE_BORDER_TILE,
			GE_BORDER_TILE,
			GE_BORDER_TILE
		);
	}

	private void drawHoverTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		if (contains(mouseX, mouseY, this.leftPos + TUTORIAL_BUTTON_X, this.topPos + TUTORIAL_BUTTON_Y, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H)) {
			Slot tutorialSlot = this.slotById(SLOT_TUTORIAL);
			if (tutorialSlot != null && tutorialSlot.hasItem()) {
				graphics.setTooltipForNextFrame(this.font, tutorialSlot.getItem(), mouseX, mouseY);
			}
		}
	}

	private void drawSelectedItem(GuiGraphicsExtractor graphics, int panelX, int panelY) {
		Slot slot = this.selectedItemSlot();
		if (slot == null || !slot.hasItem()) {
			return;
		}

		ItemStack stack = slot.getItem().copy();
		this.drawScaledItem(graphics, stack, panelX + ITEM_RENDER_X, panelY + ITEM_RENDER_Y, ITEM_RENDER_SIZE);
	}

	private void drawBoxItem(GuiGraphicsExtractor graphics, Slot slot, int boxX, int boxY) {
		if (slot == null || !slot.hasItem()) {
			return;
		}
		ItemStack stack = slot.getItem().copy();
		int itemX = boxX + (ITEM_BOX_W - 16) / 2;
		int itemY = boxY + (ITEM_BOX_H - 16) / 2;
		int overlayX = itemX - Math.round((ITEM_RENDER_SIZE - 16) / 2.0F);
		int overlayY = itemY - Math.round((ITEM_RENDER_SIZE - 16) / 2.0F);
		this.drawScaledItem(graphics, stack, itemX, itemY, ITEM_RENDER_SIZE);
		StackSizeOverlay.renderLightweightStackSizeOrCount(graphics, this.font, stack, overlayX, overlayY, ITEM_RENDER_SIZE);
	}

	private void drawCenteredFieldValue(GuiGraphicsExtractor graphics, int x, int y, int width, int height, String value) {
		if (value == null || value.isEmpty()) {
			return;
		}

		int textX = x + (width - this.font.width(value)) / 2;
		int textY = y + (height - 8) / 2;
		graphics.text(this.font, value, textX, textY, TEXT_COLOR, false);
	}

	private String tooltipNumber(int slotId, int numberIndex) {
		Slot slot = this.slotById(slotId);
		if (slot == null || !slot.hasItem()) {
			return null;
		}

		int found = 0;
		for (Component line : this.tooltipLines(slot.getItem())) {
			Matcher matcher = NUMBER_PATTERN.matcher(line.getString());
			while (matcher.find()) {
				if (found == numberIndex) {
					return matcher.group(1);
				}
				found++;
			}
		}
		return null;
	}

	private String tooltipSlashNumber(int slotId, int groupIndex) {
		if (groupIndex < 0 || groupIndex > 1) {
			return null;
		}

		Slot slot = this.slotById(slotId);
		if (slot == null || !slot.hasItem()) {
			return null;
		}

		for (Component line : this.tooltipLines(slot.getItem())) {
			Matcher matcher = SLASH_NUMBER_PATTERN.matcher(line.getString());
			if (matcher.find()) {
				return matcher.group(groupIndex + 1);
			}
		}
		return null;
	}

	private List<Component> tooltipLines(ItemStack stack) {
		if (stack.isEmpty()) {
			return List.of();
		}

		Item.TooltipContext context = this.minecraft != null && this.minecraft.level != null
			? Item.TooltipContext.of(this.minecraft.level)
			: Item.TooltipContext.EMPTY;
		return stack.getTooltipLines(context, this.minecraft != null ? this.minecraft.player : null, TooltipFlag.Default.NORMAL);
	}

	private void drawWrappedText(GuiGraphicsExtractor graphics, String text, int x, int y, int maxWidth, int color) {
		List<String> lines = this.wrapText(text, maxWidth);
		for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
			graphics.text(this.font, lines.get(lineIndex), x, y + lineIndex * 9, color, false);
		}
	}

	private List<String> wrapText(String text, int maxWidth) {
		if (text == null || text.isBlank()) {
			return List.of();
		}

		List<String> lines = new ArrayList<>();
		String[] words = text.trim().split("\\s+");
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
			}
			if (this.font.width(word) <= maxWidth) {
				current.setLength(0);
				current.append(word);
				continue;
			}
			lines.add(this.font.plainSubstrByWidth(word, maxWidth));
			current.setLength(0);
		}
		if (!current.isEmpty()) {
			lines.add(current.toString());
		}
		return lines;
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

	private String selectedItemName() {
		Slot slot = this.selectedItemSlot();
		if (slot == null || !slot.hasItem()) {
			return null;
		}
		return slot.getItem().getHoverName().getString();
	}

	private String selectedItemDescription() {
		Slot slot = this.selectedItemSlot();
		if (slot == null || !slot.hasItem()) {
			return null;
		}

		List<Component> lines = this.tooltipLines(slot.getItem());
		int startIndex = this.firstWhiteTooltipLineIndex(lines);
		if (startIndex < 0) {
			return null;
		}

		StringBuilder text = new StringBuilder();
		for (int i = startIndex; i < lines.size(); i++) {
			String line = lines.get(i).getString().trim();
			if (line.isEmpty() || line.contains("Minecraft")) {
				continue;
			}
			if (!text.isEmpty()) {
				text.append(' ');
			}
			text.append(line);
		}
		return text.isEmpty() ? null : text.toString();
	}

	private int firstWhiteTooltipLineIndex(List<Component> lines) {
		for (int i = 0; i < lines.size(); i++) {
			if (this.isWhiteTooltipLine(lines.get(i))) {
				return i;
			}
		}
		return -1;
	}

	private boolean isWhiteTooltipLine(Component line) {
		if (line == null) {
			return false;
		}

		boolean isWhite = true;
		for (Component sibling : line.getSiblings()) {
			if (sibling.getStyle().getColor() == null)
				return false;

			if (sibling.getStyle().getColor().getValue() != 16777215 || sibling.getStyle().getColor() == null) {
				isWhite = false;
				break;
			}
		}
		return isWhite;
	}

	private Slot selectedItemSlot() {
		return this.menu.slots.size() > 10 ? this.menu.slots.get(10) : null;
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
}
