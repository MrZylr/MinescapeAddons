package com.zylr.client.hud;

import com.cinemamod.mcef.MCEF;
import com.cinemamod.mcef.MCEFBrowser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public final class BrowserWidget {
	private MCEFBrowser browser;
	private boolean initialized;
	private boolean visible = true;
	private String currentUrl;
	private final String initialUrl;
	private int browserWidth = 1920;
	private int browserHeight = 1080;
	private float opacity = 0.8F;

	public BrowserWidget(String initialUrl) {
		this.initialUrl = initialUrl == null || initialUrl.isBlank() ? "https://www.google.com" : initialUrl;
		this.currentUrl = this.initialUrl;
	}

	private void initializeBrowser() {
		if (this.initialized || this.browser != null) return;
		try {
			if (!MCEF.isInitialized()) return;
			this.browser = MCEF.createBrowser(this.initialUrl, true);
			this.browser.resize(this.browserWidth, this.browserHeight);
			if (!this.currentUrl.equals(this.initialUrl)) this.browser.loadURL(this.currentUrl);
			this.initialized = true;
		} catch (Throwable ignored) {
			this.browser = null;
			this.initialized = false;
		}
	}

	public void render(GuiGraphicsExtractor graphics, Minecraft minecraft, int left, int top, int right, int bottom) {
		if (!this.visible) return;
		this.initializeBrowser();
		if (!this.isInitialized()) {
			this.renderStatus(graphics, minecraft, left, top, right, bottom, "MCEF is not initialized");
			return;
		}
		if (!this.browser.isTextureReady()) {
			this.renderStatus(graphics, minecraft, left, top, right, bottom, "Loading browser...");
			return;
		}
		Identifier texture = this.browser.getTextureIdentifier();
		if (texture == null) {
			this.renderStatus(graphics, minecraft, left, top, right, bottom, "Browser texture is unavailable");
			return;
		}
		graphics.blit(
			RenderPipelines.GUI_TEXTURED,
			texture,
			left,
			top,
			0.0F,
			0.0F,
			right - left,
			bottom - top,
			this.browserWidth,
			this.browserHeight,
			this.browserWidth,
			this.browserHeight
		);
	}

	private void renderStatus(GuiGraphicsExtractor graphics, Minecraft minecraft, int left, int top, int right, int bottom, String message) {
		int alpha = Math.max(32, Math.min(255, Math.round(this.opacity * 255.0F)));
		graphics.fill(left, top, right, bottom, (alpha << 24) | 0x101010);
		graphics.outline(left, top, right - left, bottom - top, 0xFF8D7A43);
		graphics.centeredText(minecraft.font, Component.literal(message), (left + right) / 2, top + 18, 0xFFFFD98A);
		graphics.centeredText(minecraft.font, Component.literal(this.currentUrl), (left + right) / 2, top + 34, 0xFFE8E0CC);
	}

	public void resize(int width, int height) {
		this.browserWidth = Math.max(1, width);
		this.browserHeight = Math.max(1, height);
		this.initializeBrowser();
		if (this.browser != null) {
			this.browser.resize(this.browserWidth, this.browserHeight);
		}
	}

	public boolean mouseClicked(int x, int y, int button) {
		this.initializeBrowser();
		if (!this.isInitialized()) return false;
		this.browser.sendMousePress(x, y, button);
		this.browser.setFocus(true);
		return true;
	}

	public boolean mouseReleased(int x, int y, int button) {
		this.initializeBrowser();
		if (!this.isInitialized()) return false;
		this.browser.sendMouseRelease(x, y, button);
		this.browser.setFocus(true);
		return true;
	}

	public void mouseMoved(int x, int y) {
		this.initializeBrowser();
		if (this.isInitialized()) this.browser.sendMouseMove(x, y);
	}

	public boolean mouseScrolled(int x, int y, double delta) {
		this.initializeBrowser();
		if (!this.isInitialized()) return false;
		this.browser.sendMouseWheel(x, y, delta, 0);
		return true;
	}

	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		this.initializeBrowser();
		if (!this.isInitialized()) return false;
		this.browser.sendKeyPress(keyCode, scanCode, modifiers);
		this.browser.setFocus(true);
		return true;
	}

	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		this.initializeBrowser();
		if (!this.isInitialized()) return false;
		this.browser.sendKeyRelease(keyCode, scanCode, modifiers);
		this.browser.setFocus(true);
		return true;
	}

	public boolean charTyped(int codePoint) {
		this.initializeBrowser();
		if (!this.isInitialized() || codePoint == 0) return false;
		this.browser.sendKeyTyped((char) codePoint, 0);
		this.browser.setFocus(true);
		return true;
	}

	public boolean isInitialized() {
		return this.initialized && this.browser != null;
	}

	public void cleanup() {
		if (this.browser != null) {
			try {
				this.browser.setFocus(false);
				this.browser.close();
			} catch (Throwable ignored) {
			}
			this.browser = null;
		}
		this.initialized = false;
	}

	public void loadURL(String url) {
		if (url == null || url.isBlank()) return;
		this.currentUrl = url;
		this.initializeBrowser();
		if (this.isInitialized()) this.browser.loadURL(url);
	}

	public void goBack() {
		if (this.isInitialized() && this.browser.canGoBack()) this.browser.goBack();
	}

	public void goForward() {
		if (this.isInitialized() && this.browser.canGoForward()) this.browser.goForward();
	}

	public void refresh() {
		if (this.isInitialized()) this.browser.reload();
	}

	public void stop() {
		if (this.isInitialized()) this.browser.stopLoad();
	}

	public boolean canGoBack() {
		return this.isInitialized() && this.browser.canGoBack();
	}

	public boolean canGoForward() {
		return this.isInitialized() && this.browser.canGoForward();
	}

	public boolean isLoading() {
		return this.isInitialized() && this.browser.isLoading();
	}

	public void setZoom(float zoomLevel) {
		if (this.isInitialized()) this.browser.setZoomLevel(zoomLevel);
	}

	public void pauseAllMedia() {
		if (!this.isInitialized()) return;
		this.browser.executeJavaScript(
			"document.querySelectorAll('video,audio').forEach(function(media) { media.pause(); });",
			"",
			0
		);
	}

	public MCEFBrowser browser() {
		return this.browser;
	}

	public boolean owns(org.cef.browser.CefBrowser browser) {
		return this.browser == browser;
	}

	public String getCurrentUrl() {
		if (this.isInitialized()) {
			String browserUrl = this.browser.getURL();
			if (browserUrl != null && !browserUrl.isBlank() && !"about:blank".equals(browserUrl)) {
				this.currentUrl = browserUrl;
			}
		}
		return this.currentUrl;
	}

	public void setCurrentUrl(String url) {
		if (url != null && !url.isBlank()) this.currentUrl = url;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setOpacity(float opacity) {
		this.opacity = Math.max(0.0F, Math.min(1.0F, opacity));
	}

	public float getOpacity() {
		return this.opacity;
	}
}
