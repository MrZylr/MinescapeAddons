package com.zylr.client.hud;

import com.zylr.MinescapeAddon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public final class ChatWidget extends HudWidget {
	private static final Identifier BACKGROUND = Identifier.fromNamespaceAndPath(
		MinescapeAddon.MOD_ID,
		"textures/gui/runescape/chatbox/background.png"
	);
	private static final int INNER_PADDING = 4;
	private static final int FRAME_SIDE_OVERHANG = 10;
	private static final int CONTENT_RAISE = 6;
	private static final int VANILLA_LINE_HEIGHT = 9;
	private static final int CHAT_TOP_PADDING = VANILLA_LINE_HEIGHT;
	private static final int CHAT_BOTTOM_LIFT = 7;
	private static final int INPUT_HEIGHT = 14;
	private static final int SEPARATOR_HEIGHT = 1;
	private static final ThreadLocal<Boolean> RENDERING_CUSTOM_CHAT = ThreadLocal.withInitial(() -> false);
	private static final ThreadLocal<Boolean> SUPPRESSING_CUSTOM_CHAT_HOVER = ThreadLocal.withInitial(() -> false);

	private int computedBaseWidth = 328;
	private int computedBaseHeight = 112;

	ChatWidget(double defaultX, double defaultY, double defaultScale) {
		super("chatWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() { return this.computedBaseWidth; }

	@Override
	protected int baseHeight() { return this.computedBaseHeight; }

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		if (!HudManager.getInstance().isCustomChatEnabled()) return;
		Layout layout = this.layout(minecraft, false);
		renderFrame(graphics, layout);
		renderChat(graphics, minecraft, layout, mouseX, mouseY, ChatComponent.DisplayMode.FOREGROUND, false, minecraft.screen == null);
	}

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isCustomChatEnabled();
	}

	@Override
	protected void renderEditChrome(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, boolean selected) {
		Layout layout = this.layout(minecraft, false);
		int left = layout.frameX();
		int right = layout.frameX() + layout.frameWidth();
		boolean warning = this.shouldHighlightInEditModeWarning();
		int border = warning ? (selected ? 0xFFFF8A80 : 0xFFFF5252) : (selected ? 0xFFFFD54A : 0xFF6BD4FF);
		int fill = warning ? (selected ? HudManager.rgba(64, 255, 82, 82) : HudManager.rgba(44, 255, 82, 82)) : (selected ? HudManager.rgba(48, 255, 213, 74) : HudManager.rgba(32, 107, 212, 255));
		graphics.fill(left, layout.y, right, layout.y + 1, border);
		graphics.fill(left, layout.y + layout.height - 1, right, layout.y + layout.height, border);
		graphics.fill(left, layout.y, left + 1, layout.y + layout.height, border);
		graphics.fill(right - 1, layout.y, right, layout.y + layout.height, border);
		graphics.fill(left + 1, layout.y + 1, right - 1, layout.y + layout.height - 1, fill);
		int handleSize = Math.max(8, (int) Math.round(8 * this.scale()));
		int handleColor = mouseX >= right - handleSize
			&& mouseX <= right
			&& mouseY >= layout.y + layout.height - handleSize
			&& mouseY <= layout.y + layout.height
			? 0xFFFFFFFF
			: 0xFFE0E0E0;
		graphics.fill(right - handleSize, layout.y + layout.height - handleSize, right, layout.y + layout.height, handleColor);
	}

	@Override
	protected int extraLeftBounds(int screenWidth, int screenHeight) { return FRAME_SIDE_OVERHANG; }

	@Override
	protected int extraRightBounds(int screenWidth, int screenHeight) { return FRAME_SIDE_OVERHANG; }

	@Override
	protected int activeLeftBound(int screenWidth, int screenHeight) {
		return this.collapsedLayoutBounds(screenWidth, screenHeight).x - FRAME_SIDE_OVERHANG;
	}

	@Override
	protected int activeTopBound(int screenWidth, int screenHeight) {
		return this.collapsedLayoutBounds(screenWidth, screenHeight).y;
	}

	@Override
	protected int activeRightBound(int screenWidth, int screenHeight) {
		LayoutBounds bounds = this.collapsedLayoutBounds(screenWidth, screenHeight);
		return bounds.x + bounds.width + FRAME_SIDE_OVERHANG;
	}

	@Override
	protected int activeBottomBound(int screenWidth, int screenHeight) {
		LayoutBounds bounds = this.collapsedLayoutBounds(screenWidth, screenHeight);
		return bounds.y + bounds.height;
	}

	public Layout layout(Minecraft minecraft, boolean focused) {
		double chatScale = minecraft.options.chatScale().get();
		int chatWidth = (int) Math.ceil((ChatComponent.getWidth(minecraft.options.chatWidth().get()) + INNER_PADDING * 2) * chatScale);
		int chatHeight = (int) Math.ceil(ChatComponent.getHeight(focused
			? minecraft.options.chatHeightFocused().get()
			: minecraft.options.chatHeightUnfocused().get()) * chatScale);
		chatHeight = Math.max(0, chatHeight - this.scaledLineHeight(minecraft));
		int maxChatHeight = (int) Math.ceil(ChatComponent.getHeight(minecraft.options.chatHeightFocused().get()) * chatScale);
		maxChatHeight = Math.max(0, maxChatHeight - this.scaledLineHeight(minecraft));
		int renderHeight = chatHeight + INPUT_HEIGHT + SEPARATOR_HEIGHT + CONTENT_RAISE;
		this.computedBaseWidth = Math.max(80, (int) Math.ceil(chatWidth / this.scale()));
		this.computedBaseHeight = Math.max(40, (int) Math.ceil((maxChatHeight + INPUT_HEIGHT + SEPARATOR_HEIGHT + CONTENT_RAISE) / this.scale()));

		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int x = this.pixelX(screenWidth);
		int width = this.pixelWidth();
		int fullHeight = this.pixelHeight();
		int height = Math.min(fullHeight, renderHeight) + 18;
		int y = this.pixelY(screenHeight) + fullHeight - height;
		int inputTop = y + height - INPUT_HEIGHT - CONTENT_RAISE;
		int separatorY = inputTop - SEPARATOR_HEIGHT;
		int chatBottom = separatorY + CONTENT_RAISE - CHAT_BOTTOM_LIFT;
		return new Layout(x, y, width, height, separatorY, inputTop, INPUT_HEIGHT, chatBottom, chatHeight);
	}

	private LayoutBounds collapsedLayoutBounds(int screenWidth, int screenHeight) {
		Minecraft minecraft = Minecraft.getInstance();
		double chatScale = minecraft.options.chatScale().get();
		int chatWidth = (int) Math.ceil((ChatComponent.getWidth(minecraft.options.chatWidth().get()) + INNER_PADDING * 2) * chatScale);
		int chatHeight = (int) Math.ceil(ChatComponent.getHeight(minecraft.options.chatHeightUnfocused().get()) * chatScale);
		chatHeight = Math.max(0, chatHeight - this.scaledLineHeight(minecraft));
		int maxChatHeight = (int) Math.ceil(ChatComponent.getHeight(minecraft.options.chatHeightFocused().get()) * chatScale);
		maxChatHeight = Math.max(0, maxChatHeight - this.scaledLineHeight(minecraft));
		this.computedBaseWidth = Math.max(80, (int) Math.ceil(chatWidth / this.scale()));
		this.computedBaseHeight = Math.max(40, (int) Math.ceil((maxChatHeight + INPUT_HEIGHT + SEPARATOR_HEIGHT + CONTENT_RAISE) / this.scale()));
		int fullHeight = this.pixelHeight();
		int height = Math.min(fullHeight, chatHeight + INPUT_HEIGHT + SEPARATOR_HEIGHT + CONTENT_RAISE);
		return new LayoutBounds(this.pixelX(screenWidth), this.pixelY(screenHeight) + fullHeight - height, this.pixelWidth(), height);
	}

	private int scaledLineHeight(Minecraft minecraft) {
		double lineSpacing = minecraft.options.chatLineSpacing().get();
		double chatScale = minecraft.options.chatScale().get();
		return (int) Math.ceil(VANILLA_LINE_HEIGHT * (lineSpacing + 1.0D) * chatScale);
	}

	public static void renderFrame(GuiGraphicsExtractor graphics, Layout layout) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, layout.frameX(), layout.y, 0.0F, 0.0F, layout.frameWidth(), layout.height, layout.frameWidth(), layout.height);
		graphics.fill(layout.frameX(), layout.separatorY, layout.frameX() + layout.frameWidth(), layout.separatorY + SEPARATOR_HEIGHT, 0xFF000000);
	}

	public static void renderChat(
		GuiGraphicsExtractor graphics,
		Minecraft minecraft,
		Layout layout,
		int mouseX,
		int mouseY,
		ChatComponent.DisplayMode displayMode,
		boolean insertionClickMode
	) {
		renderChat(graphics, minecraft, layout, mouseX, mouseY, displayMode, insertionClickMode, true);
	}

	public static void renderChat(
		GuiGraphicsExtractor graphics,
		Minecraft minecraft,
		Layout layout,
		int mouseX,
		int mouseY,
		ChatComponent.DisplayMode displayMode,
		boolean insertionClickMode,
		boolean allowHover
	) {
		Font font = minecraft.font;
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int chatBottom = layout.separatorY + CONTENT_RAISE - CHAT_BOTTOM_LIFT;
		int chatMouseX = allowHover ? toVanillaChatMouseX(layout, mouseX) : Integer.MIN_VALUE;
		int chatMouseY = allowHover ? toVanillaChatMouseY(minecraft, layout, mouseY) : Integer.MIN_VALUE;
		RENDERING_CUSTOM_CHAT.set(true);
		SUPPRESSING_CUSTOM_CHAT_HOVER.set(!allowHover);
		graphics.enableScissor(layout.frameX(), layout.chatContentTop(), layout.frameX() + layout.frameWidth(), layout.separatorY);
		graphics.pose().pushMatrix();
		graphics.pose().translate(layout.x, chatBottom - (screenHeight - 40));
		try {
			minecraft.gui.getChat().extractRenderState(
				graphics,
				font,
				minecraft.gui.getGuiTicks(),
				chatMouseX,
				chatMouseY,
				displayMode,
				insertionClickMode
			);
		} finally {
			graphics.pose().popMatrix();
			SUPPRESSING_CUSTOM_CHAT_HOVER.set(false);
			RENDERING_CUSTOM_CHAT.set(false);
			graphics.disableScissor();
		}
	}

	public static int toVanillaChatMouseX(Layout layout, double mouseX) {
		return (int) Math.round(mouseX - layout.x);
	}

	public static int toVanillaChatMouseY(Minecraft minecraft, Layout layout, double mouseY) {
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int chatBottom = layout.separatorY + CONTENT_RAISE - CHAT_BOTTOM_LIFT;
		return (int) Math.round(mouseY - (chatBottom - (screenHeight - 40)));
	}

	public static boolean isWithinChatContent(Layout layout, double mouseX, double mouseY) {
		return mouseX >= layout.frameX()
			&& mouseX < layout.frameX() + layout.frameWidth()
			&& mouseY >= layout.chatContentTop()
			&& mouseY < layout.chatContentBottom();
	}

	public static int commandSuggestionsAnchorScreenHeight(Minecraft minecraft) {
		Layout layout = HudManager.getInstance().chatWidget().layout(minecraft, true);
		return layout.inputY() + 12;
	}

	public static boolean isRenderingCustomChat() {
		return RENDERING_CUSTOM_CHAT.get();
	}

	public static boolean isSuppressingCustomChatHover() {
		return SUPPRESSING_CUSTOM_CHAT_HOVER.get();
	}

	public record Layout(int x, int y, int width, int height, int separatorY, int inputY, int inputHeight, int chatBottom, int chatContentHeight) {
		public int frameX() { return this.x - FRAME_SIDE_OVERHANG; }
		public int frameWidth() { return this.width + FRAME_SIDE_OVERHANG * 2; }
		public int inputX() { return this.x + INNER_PADDING; }
		public int inputWidth() { return Math.max(20, this.width - INNER_PADDING * 2); }
		public int chatContentTop() { return this.chatBottom - this.chatContentHeight; }
		public int chatContentBottom() { return this.chatBottom; }
	}

	private record LayoutBounds(int x, int y, int width, int height) {
	}
}
