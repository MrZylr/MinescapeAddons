package com.zylr.client.screen.overridescreens;

import com.zylr.utils.PrivateUseAsciiDecoder;
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

public abstract class AbstractGrandExchangeOfferSetupScreen extends AbstractGrandExchangeScreen {
	private static final Identifier SMALL_BUTTON = texture("ge/button.png");
	private static final Identifier SMALL_BUTTON_HOVERED = texture("ge/button_hovered.png");
	private static final Identifier SELECTED_ITEM_BOX = texture("ge/selected_item_box.png");
	private static final Identifier COLLECTION_BOX_OFFER_SELL = texture("ge/collection_box_offer_sell.png");
	private static final Identifier GE_BORDER_TOP = texture("ge/border_offer_top.png");
	private static final Identifier NUMBER_FIELD_EDGE_LEFT = texture("ge/number_field_edge_left.png");
	private static final Identifier NUMBER_FIELD_EDGE_RIGHT = texture("ge/number_field_edge_right.png");
	private static final Identifier NUMBER_FIELD_MIDDLE = texture("ge/number_field_middle.png");
	private static final Identifier LARGE_BUTTON = texture("button/enter_wilderness_teleport.png");
	private static final Identifier LARGE_BUTTON_HOVERED = texture("button/enter_wilderness_teleport_hovered.png");
	private static final Identifier BACK_ARROW = texture("ge/back_arrow_button.png");
	private static final Identifier DECREMENT_BUTTON = texture("ge/decrement_button.png");
	private static final Identifier INCREMENT_BUTTON = texture("ge/increment_button.png");
	private static final Identifier TUTORIAL_BUTTON = texture("button/tutorial.png");
	private static final Identifier TUTORIAL_BUTTON_HOVERED = texture("button/tutorial_hovered.png");

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
	private static final int BUTTON_Y = 46;
	private static final int SMALL_BUTTON_W = 25;
	private static final int SMALL_BUTTON_H = 25;
	private static final int SMALL_BUTTON_GAP = 4;
	private static final int LEFT_MINUS_X = 7;
	private static final int RIGHT_MINUS_X = 7;
	private static final int INCREMENT_X_OFFSET = 149;
	private static final int QUANTITY_BUTTON_START_X = 14;
	private static final int PRICE_BUTTON_START_X = 45;
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
	private static final int CONFIRM_BUTTON_X = 140;
	private static final int CONFIRM_BUTTON_Y = 225;
	private static final int CONFIRM_BUTTON_W = 90;
	private static final int CONFIRM_BUTTON_H = 25;
	private static final int COLLECTION_ICON_X = 95;
	private static final int COLLECTION_ICON_Y = 1;
	private static final int ITEM_RENDER_SIZE = 16;
	private static final int ITEM_RENDER_X = ITEM_BOX_X + (ITEM_BOX_W - 16) / 2;
	private static final int ITEM_RENDER_Y = ITEM_BOX_Y + (ITEM_BOX_H - 16) / 2;
	private static final int TUTORIAL_BUTTON_X = HEADER_CLOSE_X - 22;
	private static final int TUTORIAL_BUTTON_Y = HEADER_CLOSE_Y + 1;
	private static final int TUTORIAL_BUTTON_W = 17;
	private static final int TUTORIAL_BUTTON_H = 17;

	private static final int SLOT_QUANTITY_DECREMENT = 27;
	private static final int SLOT_QUANTITY_INCREMENT = 30;
	private static final int SLOT_PRICE_DECREMENT = 32;
	private static final int SLOT_PRICE_INCREMENT = 35;
	private static final int SLOT_PLUS_1 = 36;
	private static final int SLOT_PLUS_10 = 37;
	private static final int SLOT_PLUS_100 = 38;
	private static final int SLOT_PLUS_1K = 39;
	private static final int SLOT_PRICE_MINUS_5 = 41;
	private static final int SLOT_PRICE_CUSTOM = 42;
	private static final int SLOT_PRICE_PLUS_5 = 43;
	private static final int SLOT_BACK = 45;
	private static final int SLOT_TUTORIAL = 46;
	private static final int SLOT_CONFIRM = 49;

	private static final int TEXT_COLOR = 0xFFF6A230;
	private static final int BODY_TEXT_COLOR = 0xFFFFD27F;
	private static final Pattern TITLE_NUMBER_PATTERN = Pattern.compile("\\d[\\d,]*");

	protected AbstractGrandExchangeOfferSetupScreen(ChestMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	protected String getMenuTitle() {
		return "Grand Exchange: Set up offer";
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
		this.drawQuantityPanel(graphics, leftMidX, midY, mouseX, mouseY);
		this.drawPricePanel(graphics, rightMidX, midY, mouseX, mouseY);

		this.drawWideBar(graphics, this.leftPos + BOTTOM_BAR_X, this.topPos + BOTTOM_BAR_Y, BOTTOM_BAR_W, BOTTOM_BAR_H);
		String totalPrice = this.decodedTitleNumber(1);
		this.drawCenteredFieldValue(graphics, this.leftPos + BOTTOM_BAR_X, this.topPos + BOTTOM_BAR_Y, BOTTOM_BAR_W, BOTTOM_BAR_H, totalPrice != null ? totalPrice + " coins" : "");
		this.drawConfirmDivider(graphics);
		this.drawTutorialButton(graphics, mouseX, mouseY);
		this.drawBackArrow(graphics);
		this.drawConfirmButton(graphics, mouseX, mouseY);
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
		if (this.tryMappedClick(mouseX, mouseY, button)) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	protected abstract String getOfferHeaderLabel();

	protected int getSelectedItemClickSlot() {
		return -1;
	}

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

	private void drawQuantityPanel(GuiGraphicsExtractor graphics, int x, int y, int mouseX, int mouseY) {
		String label = "Quantity:";
		int labelX = x + (HALF_PANEL_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, labelX, y + 9, TEXT_COLOR, false);
		this.drawAdjustButton(graphics, x + LEFT_MINUS_X, y + 21, DECREMENT_BUTTON);
		this.drawAdjustButton(graphics, x + INCREMENT_X_OFFSET, y + 21, INCREMENT_BUTTON);
		this.drawWideBar(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H);
		this.drawCenteredFieldValue(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H, this.decodedTitleNumber(0));
		this.drawSmallButtonRow(graphics, x + QUANTITY_BUTTON_START_X, y + BUTTON_Y, mouseX, mouseY, new String[]{"+1", "+10", "+100", "+1k", "..."});
	}

	private void drawPricePanel(GuiGraphicsExtractor graphics, int x, int y, int mouseX, int mouseY) {
		String label = "Price per item:";
		int labelX = x + (HALF_PANEL_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, labelX, y + 9, TEXT_COLOR, false);
		this.drawAdjustButton(graphics, x + RIGHT_MINUS_X, y + 21, DECREMENT_BUTTON);
		this.drawAdjustButton(graphics, x + INCREMENT_X_OFFSET, y + 21, INCREMENT_BUTTON);
		this.drawWideBar(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H);
		this.drawCenteredFieldValue(graphics, x + DARK_BAR_X, y + DARK_BAR_Y, DARK_BAR_W, DARK_BAR_H, this.decodedTitleNumber(2));
		this.drawSmallButtonRow(graphics, x + PRICE_BUTTON_START_X, y + BUTTON_Y, mouseX, mouseY, new String[]{"-5%", "...", "+5%"});
	}

	private void drawWideBar(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, NUMBER_FIELD_EDGE_LEFT, x, y, 0.0F, 0.0F, 4, height, 4, height, 4, 20);
		for (int drawX = 4; drawX < width - 4; drawX += 4) {
			int pieceWidth = Math.min(4, width - 4 - drawX);
			graphics.blit(RenderPipelines.GUI_TEXTURED, NUMBER_FIELD_MIDDLE, x + drawX, y, 0.0F, 0.0F, pieceWidth, height, 4, height, 4, 20);
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, NUMBER_FIELD_EDGE_RIGHT, x + width - 6, y, 0.0F, 0.0F, 4, height, 4, height, 4, 20);
	}

	private void drawSmallButtonRow(GuiGraphicsExtractor graphics, int x, int y, int mouseX, int mouseY, String[] labels) {
		for (int index = 0; index < labels.length; index++) {
			int buttonX = x + index * (SMALL_BUTTON_W + SMALL_BUTTON_GAP);
			this.drawSmallButton(graphics, buttonX, y, mouseX, mouseY, labels[index]);
		}
	}

	private void drawSmallButton(GuiGraphicsExtractor graphics, int x, int y, int mouseX, int mouseY, String label) {
		boolean hovered = contains(mouseX, mouseY, x, y, SMALL_BUTTON_W, SMALL_BUTTON_H);
		Identifier texture = hovered ? SMALL_BUTTON_HOVERED : SMALL_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, SMALL_BUTTON_W, SMALL_BUTTON_H, SMALL_BUTTON_W, SMALL_BUTTON_H, SMALL_BUTTON_W, SMALL_BUTTON_H);
		int textX = x + (SMALL_BUTTON_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, textX, y + 9, TEXT_COLOR, false);
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

	private void drawConfirmButton(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int x = this.leftPos + CONFIRM_BUTTON_X;
		int y = this.topPos + CONFIRM_BUTTON_Y;
		Identifier texture = contains(mouseX, mouseY, x, y, CONFIRM_BUTTON_W, CONFIRM_BUTTON_H) ? LARGE_BUTTON_HOVERED : LARGE_BUTTON;
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, CONFIRM_BUTTON_W, CONFIRM_BUTTON_H, 90, 56, 90, 56);
		String label = this.hasSelectedItem() ? "Confirm" : "---";
		int textX = x + (CONFIRM_BUTTON_W - this.font.width(label)) / 2;
		graphics.text(this.font, label, textX, y + 8, TEXT_COLOR, false);
	}

	private void drawVerticalDivider(GuiGraphicsExtractor graphics, int x, int y, int height) {
		graphics.fill(x, y + 1, x + 1, y + height - 1, DOUBLE_LINE_OUTER_COLOR);
		graphics.fill(x + 1, y + 1, x + 2, y + height - 1, DOUBLE_LINE_INNER_COLOR);
	}

	private void drawAdjustButton(GuiGraphicsExtractor graphics, int x, int y, Identifier texture) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, 20, 18, 20, 18, 20, 18);
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
			return;
		}

		int priceCustomX = this.leftPos + RIGHT_PANEL_X + PRICE_BUTTON_START_X + SMALL_BUTTON_W + SMALL_BUTTON_GAP;
		int priceCustomY = this.topPos + MID_PANEL_Y + BUTTON_Y;
		if (contains(mouseX, mouseY, priceCustomX, priceCustomY, SMALL_BUTTON_W, SMALL_BUTTON_H)) {
			Slot customSlot = this.slotById(SLOT_PRICE_CUSTOM);
			if (customSlot != null && customSlot.hasItem()) {
				graphics.setTooltipForNextFrame(this.font, customSlot.getItem(), mouseX, mouseY);
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

	private void drawCenteredFieldValue(GuiGraphicsExtractor graphics, int x, int y, int width, int height, String value) {
		if (value == null || value.isEmpty()) {
			return;
		}

		int textX = x + (width - this.font.width(value)) / 2;
		int textY = y + (height - 8) / 2;
		graphics.text(this.font, value, textX, textY, TEXT_COLOR, false);
	}

	private String decodedTitleNumber(int index) {
		List<String> numbers = this.decodedTitleNumbers();
		if (index < 0 || index >= numbers.size()) {
			return null;
		}
		return numbers.get(index);
	}

	private List<String> decodedTitleNumbers() {
		String rawTitle = this.title == null ? "" : this.title.getString();
		if (!PrivateUseAsciiDecoder.containsEncodedAscii(rawTitle)) {
			return List.of();
		}

		String decoded = PrivateUseAsciiDecoder.decode(rawTitle);
		Matcher matcher = TITLE_NUMBER_PATTERN.matcher(decoded);
		List<String> numbers = new ArrayList<>();
		while (matcher.find()) {
			numbers.add(matcher.group());
		}
		return numbers;
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
			if (i == 0) continue;

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
		for (int lineIndex = 0; lineIndex < this.wrapText(text, maxWidth).size(); lineIndex++) {
			String line = this.wrapText(text, maxWidth).get(lineIndex);
			graphics.text(this.font, line, x, y + lineIndex * 9, color, false);
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

	private Slot selectedItemSlot() {
		return this.menu.slots.size() > 10 ? this.menu.slots.get(10) : null;
	}

	private boolean hasSelectedItem() {
		Slot slot = this.selectedItemSlot();
		return slot != null && slot.hasItem();
	}

	private boolean tryMappedClick(int mouseX, int mouseY, int button) {
		int selectedItemClickSlot = this.getSelectedItemClickSlot();
		if (selectedItemClickSlot >= 0
			&& this.clickMappedSlot(
				mouseX,
				mouseY,
				this.leftPos + PANEL_X + ITEM_BOX_X,
				this.topPos + PANEL_Y + ITEM_BOX_Y,
				ITEM_BOX_W,
				ITEM_BOX_H,
				selectedItemClickSlot,
				button
			)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + PANEL_X + LEFT_MINUS_X, this.topPos + MID_PANEL_Y + 21, 20, 18, SLOT_QUANTITY_DECREMENT, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + PANEL_X + INCREMENT_X_OFFSET, this.topPos + MID_PANEL_Y + 21, 20, 18, SLOT_QUANTITY_INCREMENT, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + RIGHT_PANEL_X + RIGHT_MINUS_X, this.topPos + MID_PANEL_Y + 21, 20, 18, SLOT_PRICE_DECREMENT, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + RIGHT_PANEL_X + INCREMENT_X_OFFSET, this.topPos + MID_PANEL_Y + 21, 20, 18, SLOT_PRICE_INCREMENT, button)) {
			return true;
		}

		int quantityButtonsX = this.leftPos + PANEL_X + QUANTITY_BUTTON_START_X;
		int quantityButtonsY = this.topPos + MID_PANEL_Y + BUTTON_Y;
		if (this.clickMappedSlot(mouseX, mouseY, quantityButtonsX, quantityButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PLUS_1, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, quantityButtonsX + (SMALL_BUTTON_W + SMALL_BUTTON_GAP), quantityButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PLUS_10, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, quantityButtonsX + 2 * (SMALL_BUTTON_W + SMALL_BUTTON_GAP), quantityButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PLUS_100, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, quantityButtonsX + 3 * (SMALL_BUTTON_W + SMALL_BUTTON_GAP), quantityButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PLUS_1K, button)) {
			return true;
		}

		int priceButtonsX = this.leftPos + RIGHT_PANEL_X + PRICE_BUTTON_START_X;
		int priceButtonsY = this.topPos + MID_PANEL_Y + BUTTON_Y;
		if (this.clickMappedSlot(mouseX, mouseY, priceButtonsX, priceButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PRICE_MINUS_5, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, priceButtonsX + (SMALL_BUTTON_W + SMALL_BUTTON_GAP), priceButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PRICE_CUSTOM, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, priceButtonsX + 2 * (SMALL_BUTTON_W + SMALL_BUTTON_GAP), priceButtonsY, SMALL_BUTTON_W, SMALL_BUTTON_H, SLOT_PRICE_PLUS_5, button)) {
			return true;
		}

		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + BACK_ARROW_X, this.topPos + BACK_ARROW_Y, BACK_ARROW_W, BACK_ARROW_H, SLOT_BACK, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + CONFIRM_BUTTON_X, this.topPos + CONFIRM_BUTTON_Y, CONFIRM_BUTTON_W, CONFIRM_BUTTON_H, SLOT_CONFIRM, button)) {
			return true;
		}
		if (this.clickMappedSlot(mouseX, mouseY, this.leftPos + TUTORIAL_BUTTON_X, this.topPos + TUTORIAL_BUTTON_Y, TUTORIAL_BUTTON_W, TUTORIAL_BUTTON_H, SLOT_TUTORIAL, button)) {
			return true;
		}
		return false;
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
