package com.zylr.client.hud;

import com.zylr.client.screen.BrowserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public final class BrowserHudWidget extends HudWidget {
	private static final int BASE_WIDTH = 267;
	private static final int BASE_HEIGHT = 150;
	private static final int HUD_BROWSER_TEXTURE_WIDTH = 1920;
	private static final int HUD_BROWSER_TEXTURE_HEIGHT = 1080;
	private BrowserWidget lastBrowser;
	private int lastBrowserWidth = -1;
	private int lastBrowserHeight = -1;

	BrowserHudWidget(double defaultX, double defaultY, double defaultScale) {
		super("browserWidget", defaultX, defaultY, defaultScale);
	}

	@Override
	protected int baseWidth() {
		return BASE_WIDTH;
	}

	@Override
	protected int baseHeight() {
		return BASE_HEIGHT;
	}

	@Override
	protected boolean shouldRenderWidget(Minecraft minecraft, boolean editMode) {
		if (minecraft.screen instanceof BrowserScreen) return false;
		return HudManager.getInstance().isBrowserEnabled() && BrowserScreen.hasActiveBrowserTab();
	}

	@Override
	protected boolean shouldHighlightInEditModeWarning() {
		return !HudManager.getInstance().isBrowserEnabled() || !BrowserScreen.hasActiveBrowserTab();
	}

	@Override
	protected void renderWidget(GuiGraphicsExtractor graphics, Minecraft minecraft, int mouseX, int mouseY, float delta) {
		if (!HudManager.getInstance().isBrowserEnabled()) return;
		BrowserWidget browser = BrowserScreen.activeBrowserWidget();
		if (browser == null) return;
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();
		int x = this.pixelX(screenWidth);
		int y = this.pixelY(screenHeight);
		int width = this.pixelWidth();
		int height = this.pixelHeight();
		this.resizeBrowserIfNeeded(browser, width, height);
		browser.render(graphics, minecraft, x, y, x + width, y + height);
	}

	boolean click(double mouseX, double mouseY, int screenWidth, int screenHeight, int button, Minecraft minecraft) {
		if (minecraft.screen instanceof BrowserScreen) return false;
		if (!HudManager.getInstance().isBrowserEnabled() || !this.contains(mouseX, mouseY, screenWidth, screenHeight)) return false;
		BrowserWidget browser = BrowserScreen.activeBrowserWidget();
		if (browser == null) return false;
		this.resizeBrowserIfNeeded(browser, this.pixelWidth(), this.pixelHeight());
		int browserX = this.browserMouseX(mouseX, screenWidth, minecraft);
		int browserY = this.browserMouseY(mouseY, screenHeight, minecraft);
		browser.mouseClicked(browserX, browserY, button);
		browser.mouseReleased(browserX, browserY, button);
		return true;
	}

	boolean scroll(double mouseX, double mouseY, int screenWidth, int screenHeight, double verticalAmount, Minecraft minecraft) {
		if (minecraft.screen instanceof BrowserScreen) return false;
		if (!HudManager.getInstance().isBrowserEnabled() || !this.contains(mouseX, mouseY, screenWidth, screenHeight)) return false;
		BrowserWidget browser = BrowserScreen.activeBrowserWidget();
		if (browser == null) return false;
		this.resizeBrowserIfNeeded(browser, this.pixelWidth(), this.pixelHeight());
		return browser.mouseScrolled(this.browserMouseX(mouseX, screenWidth, minecraft), this.browserMouseY(mouseY, screenHeight, minecraft), verticalAmount);
	}

	void mouseMoved(double mouseX, double mouseY, int screenWidth, int screenHeight, Minecraft minecraft) {
		if (minecraft.screen instanceof BrowserScreen) return;
		if (!HudManager.getInstance().isBrowserEnabled() || !this.contains(mouseX, mouseY, screenWidth, screenHeight)) return;
		BrowserWidget browser = BrowserScreen.activeBrowserWidget();
		if (browser == null) return;
		this.resizeBrowserIfNeeded(browser, this.pixelWidth(), this.pixelHeight());
		browser.mouseMoved(this.browserMouseX(mouseX, screenWidth, minecraft), this.browserMouseY(mouseY, screenHeight, minecraft));
	}

	private int browserMouseX(double mouseX, int screenWidth, Minecraft minecraft) {
		int widgetWidth = Math.max(1, this.pixelWidth());
		int local = (int) (((mouseX - this.pixelX(screenWidth)) / widgetWidth) * this.lastBrowserWidth);
		return Math.max(0, Math.min(local, Math.max(0, this.lastBrowserWidth - 1)));
	}

	private int browserMouseY(double mouseY, int screenHeight, Minecraft minecraft) {
		int widgetHeight = Math.max(1, this.pixelHeight());
		int local = (int) (((mouseY - this.pixelY(screenHeight)) / widgetHeight) * this.lastBrowserHeight);
		return Math.max(0, Math.min(local, Math.max(0, this.lastBrowserHeight - 1)));
	}

	private void resizeBrowserIfNeeded(BrowserWidget browser, int width, int height) {
		int browserWidth = HUD_BROWSER_TEXTURE_WIDTH;
		int browserHeight = HUD_BROWSER_TEXTURE_HEIGHT;
		if (browser == this.lastBrowser && browserWidth == this.lastBrowserWidth && browserHeight == this.lastBrowserHeight) return;
		this.lastBrowser = browser;
		this.lastBrowserWidth = browserWidth;
		this.lastBrowserHeight = browserHeight;
		browser.resize(browserWidth, browserHeight);
	}
}
