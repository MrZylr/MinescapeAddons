package com.zylr.client.screen;

import com.zylr.client.hud.BrowserWidget;
import com.zylr.client.hud.HudManager;
import com.cinemamod.mcef.MCEF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefDisplayHandler;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.lwjgl.glfw.GLFW;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class BrowserScreen extends Screen {
	private static final int TAB_HEIGHT = 20;
	private static final int NAV_BAR_HEIGHT = 20;
	public static final int BROWSER_DRAW_LEFT = 20;
	public static final int BROWSER_DRAW_RIGHT = 20;
	public static final int BROWSER_DRAW_TOP = TAB_HEIGHT + NAV_BAR_HEIGHT;
	public static final int BROWSER_DRAW_BOTTOM = 20;
	private static final int TAB_WIDTH = 150;
	private static final String DEFAULT_URL = "https://www.google.com";

	private static final List<BrowserTab> PERSISTED_TABS = new ArrayList<>();
	private static int persistedActiveTabIndex;
	private static BrowserScreen openScreen;

	private final List<BrowserTab> tabs = new ArrayList<>();
	private int activeTabIndex;
	private int tabScrollOffset;
	private EditBox urlTextField;
	private Button minimiseButton;
	private Button backButton;
	private Button forwardButton;
	private Button refreshButton;
	private CefDisplayHandler displayHandler;

	private static final class BrowserTab {
		private final BrowserWidget browserWidget;
		private String title;

		private BrowserTab(String url, String title) {
			this.browserWidget = new BrowserWidget(url);
			this.title = title;
		}
	}

	public BrowserScreen() {
		this(null);
	}

	public BrowserScreen(String initialUrl) {
		super(Component.literal("Browser"));
		if (!PERSISTED_TABS.isEmpty()) {
			this.tabs.addAll(PERSISTED_TABS);
			this.activeTabIndex = Math.min(persistedActiveTabIndex, this.tabs.size() - 1);
		}
		if (this.tabs.isEmpty() || (initialUrl != null && !initialUrl.isBlank())) {
			String url = initialUrl == null || initialUrl.isBlank() ? DEFAULT_URL : processUrlOrSearch(initialUrl);
			this.tabs.add(new BrowserTab(url, extractDomainFromUrl(url)));
			this.activeTabIndex = this.tabs.size() - 1;
		}
	}

	@Override
	protected void init() {
		openScreen = this;
		this.registerDisplayHandler();
		this.resizeBrowsers();
		this.rebuildChrome();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		this.resizeBrowsers();
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void onClose() {
		this.unregisterDisplayHandler();
		for (BrowserTab tab : this.tabs) {
			tab.browserWidget.pauseAllMedia();
			if (tab.browserWidget.isInitialized()) {
				tab.browserWidget.browser().setFocus(false);
				tab.browserWidget.browser().sendMouseRelease(-1, -1, -1);
			}
		}
		PERSISTED_TABS.clear();
		PERSISTED_TABS.addAll(this.tabs);
		persistedActiveTabIndex = this.activeTabIndex;
		if (openScreen == this) openScreen = null;
		HudManager.getInstance().recordActivity();
		super.onClose();
		this.releaseMovementKeys();
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		graphics.fill(0, 0, this.width, this.height, 0xF00B0B0B);
		this.refreshNavigationState();
		this.resizeActiveBrowserForScreen();
		this.activeTab().browserWidget.render(
			graphics,
			this.minecraft,
			BROWSER_DRAW_LEFT,
			BROWSER_DRAW_TOP,
			this.width - BROWSER_DRAW_RIGHT,
			this.height - BROWSER_DRAW_BOTTOM
		);
		super.extractRenderState(graphics, mouseX, mouseY, delta);
		this.renderLoadingIndicator(graphics);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		HudManager.getInstance().recordActivity();
		if (super.mouseClicked(event, doubleClick)) return true;
		if (!this.isInBrowserArea(event.x(), event.y())) return false;
		this.resizeActiveBrowserForScreen();
		return this.activeTab().browserWidget.mouseClicked(this.browserMouseX(event.x()), this.browserMouseY(event.y()), event.button());
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		HudManager.getInstance().recordActivity();
		if (super.mouseReleased(event)) return true;
		if (!this.isInBrowserArea(event.x(), event.y())) return false;
		this.resizeActiveBrowserForScreen();
		return this.activeTab().browserWidget.mouseReleased(this.browserMouseX(event.x()), this.browserMouseY(event.y()), event.button());
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		if (this.isInBrowserArea(mouseX, mouseY)) {
			this.resizeActiveBrowserForScreen();
			this.activeTab().browserWidget.mouseMoved(this.browserMouseX(mouseX), this.browserMouseY(mouseY));
		}
		super.mouseMoved(mouseX, mouseY);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		HudManager.getInstance().recordActivity();
		if (super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)) return true;
		if (!this.isInBrowserArea(mouseX, mouseY)) return false;
		this.resizeActiveBrowserForScreen();
		return this.activeTab().browserWidget.mouseScrolled(this.browserMouseX(mouseX), this.browserMouseY(mouseY), verticalAmount);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		HudManager.getInstance().recordActivity();
		if (event.key() == GLFW.GLFW_KEY_ESCAPE) return super.keyPressed(event);
		if (this.urlTextField != null && this.urlTextField.isFocused()) {
			if (event.key() == GLFW.GLFW_KEY_ENTER || event.key() == GLFW.GLFW_KEY_KP_ENTER) {
				this.navigateFromAddressBar();
				return true;
			}
			return super.keyPressed(event);
		}
		if (super.keyPressed(event)) return true;
		return this.activeTab().browserWidget.keyPressed(event.key(), event.scancode(), event.modifiers());
	}

	@Override
	public boolean keyReleased(KeyEvent event) {
		HudManager.getInstance().recordActivity();
		if (this.urlTextField != null && this.urlTextField.isFocused()) return super.keyReleased(event);
		if (super.keyReleased(event)) return true;
		return this.activeTab().browserWidget.keyReleased(event.key(), event.scancode(), event.modifiers());
	}

	@Override
	public boolean charTyped(CharacterEvent event) {
		HudManager.getInstance().recordActivity();
		if (this.urlTextField != null && this.urlTextField.isFocused()) return super.charTyped(event);
		if (super.charTyped(event)) return true;
		return this.activeTab().browserWidget.charTyped(event.codepoint());
	}

	private void rebuildChrome() {
		this.clearWidgets();
		this.createTabButtons();

		this.backButton = this.addRenderableWidget(Button.builder(Component.literal("<"), button -> {
			this.activeTab().browserWidget.goBack();
			this.setFocused(null);
		})
			.bounds(0, TAB_HEIGHT, 30, NAV_BAR_HEIGHT)
			.build());
		this.forwardButton = this.addRenderableWidget(Button.builder(Component.literal(">"), button -> {
			this.activeTab().browserWidget.goForward();
			this.setFocused(null);
		})
			.bounds(35, TAB_HEIGHT, 30, NAV_BAR_HEIGHT)
			.build());
		this.refreshButton = this.addRenderableWidget(Button.builder(Component.literal("R"), button -> {
			this.activeTab().browserWidget.refresh();
			this.setFocused(null);
		})
			.bounds(70, TAB_HEIGHT, 20, NAV_BAR_HEIGHT)
			.build());
		this.addRenderableWidget(Button.builder(Component.literal("G"), button -> {
			this.loadUrl(DEFAULT_URL);
			this.setFocused(null);
		}).bounds(95, TAB_HEIGHT, 20, NAV_BAR_HEIGHT).build());

		int urlFieldWidth = Math.max(80, this.width - 170);
		this.urlTextField = new EditBox(this.font, 120, TAB_HEIGHT + 2, urlFieldWidth, 16, Component.literal("URL"));
		this.urlTextField.setMaxLength(2048);
		this.urlTextField.setValue(this.activeTab().browserWidget.getCurrentUrl());
		this.urlTextField.moveCursorToStart(false);
		this.urlTextField.setEditable(true);
		this.urlTextField.setCanLoseFocus(true);
		this.addRenderableWidget(this.urlTextField);

		this.minimiseButton = Button.builder(this.activeTab().browserWidget.isVisible() ? Component.literal("-") : Component.literal("+"), button -> {
			BrowserWidget widget = this.activeTab().browserWidget;
			widget.setVisible(!widget.isVisible());
			this.minimiseButton.setMessage(widget.isVisible() ? Component.literal("-") : Component.literal("+"));
			this.setFocused(null);
		}).bounds(this.width - 45, TAB_HEIGHT, 20, NAV_BAR_HEIGHT).build();
		this.addRenderableWidget(this.minimiseButton);

		this.addRenderableWidget(Button.builder(Component.literal("X"), button -> this.onClose())
			.bounds(this.width - 20, TAB_HEIGHT, 20, NAV_BAR_HEIGHT)
			.build());
		this.addRenderableWidget(Button.builder(Component.literal("Open"), button -> this.openExternal(this.activeTab().browserWidget.getCurrentUrl()))
			.bounds(this.width - 54, this.height - 20, 54, 20)
			.build());
	}

	private void createTabButtons() {
		int scrollButtonWidth = 20;
		int newTabButtonWidth = 30;
		int availableWidth = Math.max(TAB_WIDTH, this.width - scrollButtonWidth * 2 - newTabButtonWidth);
		int maxVisibleTabs = Math.max(1, availableWidth / TAB_WIDTH);
		this.tabScrollOffset = Math.max(0, Math.min(this.tabScrollOffset, Math.max(0, this.tabs.size() - maxVisibleTabs)));

		this.addRenderableWidget(Button.builder(Component.literal("<"), button -> {
			if (this.tabScrollOffset > 0) {
				this.tabScrollOffset--;
				this.rebuildChrome();
			}
		}).bounds(0, 0, scrollButtonWidth, TAB_HEIGHT).build()).active = this.tabScrollOffset > 0;

		int tabX = scrollButtonWidth;
		int visibleTabCount = 0;
		for (int i = this.tabScrollOffset; i < this.tabs.size() && visibleTabCount < maxVisibleTabs; i++) {
			final int tabIndex = i;
			BrowserTab tab = this.tabs.get(i);
			Component label = Component.literal((tabIndex == this.activeTabIndex ? "* " : "") + tab.title);
			this.addRenderableWidget(Button.builder(label, button -> this.switchToTab(tabIndex))
				.bounds(tabX, 0, TAB_WIDTH - 20, TAB_HEIGHT)
				.build());
			this.addRenderableWidget(Button.builder(Component.literal("x"), button -> this.closeTab(tabIndex))
				.bounds(tabX + TAB_WIDTH - 20, 0, 20, TAB_HEIGHT)
				.build());
			tabX += TAB_WIDTH;
			visibleTabCount++;
		}

		this.addRenderableWidget(Button.builder(Component.literal(">"), button -> {
			if (this.tabScrollOffset + maxVisibleTabs < this.tabs.size()) {
				this.tabScrollOffset++;
				this.rebuildChrome();
			}
		}).bounds(this.width - scrollButtonWidth - newTabButtonWidth, 0, scrollButtonWidth, TAB_HEIGHT).build()).active = this.tabScrollOffset + maxVisibleTabs < this.tabs.size();
		this.addRenderableWidget(Button.builder(Component.literal("+"), button -> this.createNewTab())
			.bounds(this.width - newTabButtonWidth, 0, newTabButtonWidth, TAB_HEIGHT)
			.build());
	}

	private BrowserTab activeTab() {
		this.activeTabIndex = Math.max(0, Math.min(this.activeTabIndex, this.tabs.size() - 1));
		return this.tabs.get(this.activeTabIndex);
	}

	private void switchToTab(int tabIndex) {
		if (tabIndex < 0 || tabIndex >= this.tabs.size()) return;
		this.activeTab().browserWidget.pauseAllMedia();
		if (this.activeTab().browserWidget.isInitialized()) this.activeTab().browserWidget.browser().setFocus(false);
		this.activeTabIndex = tabIndex;
		this.resizeBrowsers();
		this.rebuildChrome();
	}

	private void createNewTab() {
		this.tabs.add(new BrowserTab(DEFAULT_URL, "google.com"));
		this.activeTabIndex = this.tabs.size() - 1;
		this.resizeBrowsers();
		this.rebuildChrome();
	}

	private void closeTab(int tabIndex) {
		if (tabIndex < 0 || tabIndex >= this.tabs.size()) return;
		BrowserTab closed = this.tabs.remove(tabIndex);
		closed.browserWidget.cleanup();
		if (this.tabs.isEmpty()) {
			PERSISTED_TABS.clear();
			persistedActiveTabIndex = 0;
			this.onClose();
			return;
		}
		if (this.activeTabIndex >= this.tabs.size()) this.activeTabIndex = this.tabs.size() - 1;
		this.rebuildChrome();
	}

	private void navigateFromAddressBar() {
		if (this.urlTextField == null) return;
		this.loadUrl(processUrlOrSearch(this.urlTextField.getValue().trim()));
		this.setFocused(null);
	}

	private void loadUrl(String url) {
		BrowserTab tab = this.activeTab();
		tab.browserWidget.loadURL(url);
		tab.title = extractDomainFromUrl(url);
		if (this.urlTextField != null) {
			this.urlTextField.setValue(url);
			this.urlTextField.moveCursorToStart(false);
		}
		this.rebuildChrome();
	}

	private boolean isInBrowserArea(double x, double y) {
		return x >= BROWSER_DRAW_LEFT && x < this.width - BROWSER_DRAW_RIGHT && y >= BROWSER_DRAW_TOP && y < this.height - BROWSER_DRAW_BOTTOM;
	}

	private int browserMouseX(double x) {
		double guiScale = this.minecraft.getWindow().getGuiScale();
		int max = Math.max(0, this.browserPixelWidth() - 1);
		return Math.max(0, Math.min((int) ((x - BROWSER_DRAW_LEFT) * guiScale), max));
	}

	private int browserMouseY(double y) {
		double guiScale = this.minecraft.getWindow().getGuiScale();
		int max = Math.max(0, this.browserPixelHeight() - 1);
		return Math.max(0, Math.min((int) ((y - BROWSER_DRAW_TOP) * guiScale), max));
	}

	private int browserPixelWidth() {
		return Math.max(1, (int) ((this.width - BROWSER_DRAW_LEFT - BROWSER_DRAW_RIGHT) * this.minecraft.getWindow().getGuiScale()));
	}

	private int browserPixelHeight() {
		return Math.max(1, (int) ((this.height - BROWSER_DRAW_TOP - BROWSER_DRAW_BOTTOM) * this.minecraft.getWindow().getGuiScale()));
	}

	private void resizeBrowsers() {
		for (BrowserTab tab : this.tabs) {
			tab.browserWidget.resize(this.browserPixelWidth(), this.browserPixelHeight());
		}
	}

	private void resizeActiveBrowserForScreen() {
		this.activeTab().browserWidget.resize(this.browserPixelWidth(), this.browserPixelHeight());
	}

	private void refreshNavigationState() {
		BrowserWidget widget = this.activeTab().browserWidget;
		if (this.backButton != null) this.backButton.active = widget.canGoBack();
		if (this.forwardButton != null) this.forwardButton.active = widget.canGoForward();
		if (this.refreshButton != null) this.refreshButton.active = widget.isInitialized();
		if (this.urlTextField != null && !this.urlTextField.isFocused()) {
			String url = widget.getCurrentUrl();
			if (url != null && !url.equals(this.urlTextField.getValue())) {
				this.urlTextField.setValue(url);
				this.urlTextField.moveCursorToStart(false);
			}
		}
	}

	private void renderLoadingIndicator(GuiGraphicsExtractor graphics) {
		if (this.urlTextField == null || !this.activeTab().browserWidget.isLoading()) return;
		int x = this.urlTextField.getX();
		int y = this.urlTextField.getY() + 1;
		int width = this.urlTextField.getWidth();
		graphics.fill(x, y, x + width, y + 2, 0x55000000);
		int barWidth = Math.max(20, width / 4);
		int offset = (int) (Util.getMillis() / 6L % (width + barWidth)) - barWidth;
		int left = Math.max(x, x + offset);
		int right = Math.min(x + width, x + offset + barWidth);
		if (right > left) graphics.fill(left, y, right, y + 2, 0xFF3BA6FF);
	}

	private void registerDisplayHandler() {
		if (this.displayHandler != null || !MCEF.isInitialized()) return;
		this.displayHandler = new CefDisplayHandlerAdapter() {
			@Override
			public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
				BrowserScreen.this.updateTabUrl(browser, url);
			}

			@Override
			public void onTitleChange(CefBrowser browser, String title) {
				BrowserScreen.this.updateTabTitle(browser, title);
			}
		};
		MCEF.getClient().addDisplayHandler(this.displayHandler);
	}

	private void unregisterDisplayHandler() {
		if (this.displayHandler == null || !MCEF.isInitialized()) return;
		MCEF.getClient().removeDisplayHandler(this.displayHandler);
		this.displayHandler = null;
	}

	private void updateTabUrl(CefBrowser browser, String url) {
		for (BrowserTab tab : this.tabs) {
			if (!tab.browserWidget.owns(browser)) continue;
			tab.browserWidget.setCurrentUrl(url);
			if (this.activeTab() == tab && this.urlTextField != null && !this.urlTextField.isFocused()) {
				this.minecraft.execute(() -> {
					if (this.urlTextField != null && !this.urlTextField.isFocused()) {
						this.urlTextField.setValue(url);
						this.urlTextField.moveCursorToStart(false);
					}
				});
			}
			return;
		}
	}

	private void updateTabTitle(CefBrowser browser, String title) {
		for (BrowserTab tab : this.tabs) {
			if (!tab.browserWidget.owns(browser)) continue;
			String text = title == null || title.isBlank() ? extractDomainFromUrl(tab.browserWidget.getCurrentUrl()) : title;
			tab.title = text.length() > 22 ? text.substring(0, 22) : text;
			this.minecraft.execute(this::rebuildChrome);
			return;
		}
	}

	private void openExternal(String url) {
		try {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(URI.create(url));
				this.setFocused(null);
				return;
			}
		} catch (Exception ignored) {
		}
		try {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				new ProcessBuilder("cmd", "/c", "start", "", url).start();
			} else if (os.contains("mac")) {
				new ProcessBuilder("open", url).start();
			} else {
				new ProcessBuilder("xdg-open", url).start();
			}
		} catch (Exception ignored) {
		}
		this.setFocused(null);
	}

	private static String processUrlOrSearch(String input) {
		if (input == null || input.isBlank()) return DEFAULT_URL;
		if (input.startsWith("http://") || input.startsWith("https://")) return input;
		boolean looksLikeDomain = input.contains(".") && !input.contains(" ");
		boolean isLocalhost = input.startsWith("localhost") || input.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+.*");
		if (looksLikeDomain || isLocalhost) return "https://" + input;
		return "https://www.google.com/search?q=" + URLEncoder.encode(input, StandardCharsets.UTF_8);
	}

	private static String extractDomainFromUrl(String url) {
		if (url == null || url.isBlank()) return "New Tab";
		String domain = url.replaceFirst("^https?://", "");
		if (domain.startsWith("www.")) domain = domain.substring(4);
		int slash = domain.indexOf('/');
		if (slash >= 0) domain = domain.substring(0, slash);
		int query = domain.indexOf('?');
		if (query >= 0) domain = domain.substring(0, query);
		int colon = domain.indexOf(':');
		if (colon >= 0) domain = domain.substring(0, colon);
		return domain.isBlank() ? "New Tab" : domain;
	}

	private void releaseMovementKeys() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft == null || minecraft.options == null) return;
		minecraft.options.keyUp.setDown(false);
		minecraft.options.keyDown.setDown(false);
		minecraft.options.keyLeft.setDown(false);
		minecraft.options.keyRight.setDown(false);
		minecraft.options.keyJump.setDown(false);
		minecraft.options.keyShift.setDown(false);
		minecraft.options.keySprint.setDown(false);
	}

	public static boolean hasActiveBrowserTab() {
		return activeBrowserWidget() != null;
	}

	public static BrowserWidget activeBrowserWidget() {
		if (openScreen != null && !openScreen.tabs.isEmpty()) {
			openScreen.activeTabIndex = Math.max(0, Math.min(openScreen.activeTabIndex, openScreen.tabs.size() - 1));
			return openScreen.tabs.get(openScreen.activeTabIndex).browserWidget;
		}
		if (PERSISTED_TABS.isEmpty()) return null;
		persistedActiveTabIndex = Math.max(0, Math.min(persistedActiveTabIndex, PERSISTED_TABS.size() - 1));
		return PERSISTED_TABS.get(persistedActiveTabIndex).browserWidget;
	}
}
