package com.zylr.minescapeaddons.addons.gui.widgets;

import com.cinemamod.mcef.MCEF;
import com.cinemamod.mcef.MCEFBrowser;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.screens.BrowserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;

import java.awt.*;

public class BrowserWidget extends Widget {
    // Use BrowserScreen.BROWSER_DRAW_OFFSET as the single source of truth for where
    // the fullscreen browser texture should start (below tabs + nav bar).
    public MCEFBrowser browser;
    private boolean initialized = false;
    private String currentUrl;
    private String initialUrl;
    private float opacity = 0.8f; // 80% opacity (20% transparent)

    public BrowserWidget(int x, int y, int width, int height, String initialUrl) {
        this.anchorX = x;
        this.anchorY = y;
        this.widgetWidth = width;
        this.widgetHeight = height;
        this.isParent = true;
        this.visible = true;
        this.type = WidgetType.BROWSER;
        this.setupConfig();
        this.initialUrl = initialUrl;
        initializeBrowser();


        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "-1"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));
    }

    private void initializeBrowser() {
        try {
            if (MCEF.isInitialized()) {
                System.out.println("initializing MCEF browser");
                // Create browser with transparency enabled
                browser = MCEF.createBrowser(this.initialUrl, true);

                // Set initial size to 1920x1080 for consistent HUD display
                // The texture will be scaled to fit the widget dimensions (267x150)
                try {
                    browser.resize(1920, 1080);
                    System.out.println("Initialized browser at 1920x1080");
                } catch (Exception resizeError) {
                    System.err.println("Could not resize browser immediately: " + resizeError.getMessage());
                    // Browser will be resized later by BrowserScreen
                }

                initialized = true;
            } else {
                System.err.println("MCEF not initialized");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize MCEF browser: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadURL(String url) {
        if (browser != null && initialized) {
            this.currentUrl = url;
            browser.loadURL(url);
        }
    }

    public void navigateToGoogle() {
        loadURL("https://www.google.com");
    }

    public void navigateToYouTube() {
        loadURL("https://www.youtube.com");
    }

    public void navigateToGitHub() {
        loadURL("https://www.github.com");
    }

    @Override
    public void render(GuiGraphics gui) {
        if (!this.isVisible())
            return;
        if (this.browser != null) {
            this.fixAnchors();
            float scale = this.scale;

            // Draw the browser content in fullscreen mode using per-side insets
            int leftInset = BrowserScreen.BROWSER_DRAW_LEFT;
            int rightInset = BrowserScreen.BROWSER_DRAW_RIGHT;
            int topInset = BrowserScreen.BROWSER_DRAW_TOP;
            int bottomInset = BrowserScreen.BROWSER_DRAW_BOTTOM;

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderTexture(0, browser.getRenderer().getTextureID());
            Tesselator t = Tesselator.getInstance();
            BufferBuilder buffer = t.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

            float left = this.getLeftSide();
            float top = this.getTop();
            float right = left + this.widgetWidth;
            float bottom = top + this.widgetHeight;
            float border = 2 * scale;

            Color color = new Color(backgroundColor, true);
            int alpha = color.getAlpha();

            if (Minecraft.getInstance().screen instanceof BrowserScreen) {
                //System.out.println("Left: " + left + " Top: " + top + " Right: " + right + " Bottom: " + bottom + " Scale: " + scale);
                buffer.addVertex((leftInset), (Minecraft.getInstance().getWindow().getGuiScaledHeight() - bottomInset), 0).setUv(0.0f, 1.0f).setColor(255, 255, 255, 255);
                buffer.addVertex((Minecraft.getInstance().getWindow().getGuiScaledWidth() - rightInset), (Minecraft.getInstance().getWindow().getGuiScaledHeight() - bottomInset), 0).setUv(1.0f, 1.0f).setColor(255, 255, 255, 255);
                buffer.addVertex((Minecraft.getInstance().getWindow().getGuiScaledWidth() - rightInset), (topInset), 0).setUv(1.0f, 0.0f).setColor(255, 255, 255, 255);
                buffer.addVertex((leftInset), (topInset), 0).setUv(0.0f, 0.0f).setColor(255, 255, 255, 255);
            } else {
                buffer.addVertex((left * scale) + border, (bottom * scale) - border, 0).setUv(0.0f, 1.0f).setColor(255, 255, 255, alpha);
                buffer.addVertex((right * scale) - border, (bottom * scale) - border, 0).setUv(1.0f, 1.0f).setColor(255, 255, 255, alpha);
                buffer.addVertex((right * scale) - border, (top * scale) + border, 0).setUv(1.0f, 0.0f).setColor(255, 255, 255, alpha);
                buffer.addVertex((left * scale) + border, (top * scale) + border, 0).setUv(0.0f, 0.0f).setColor(255, 255, 255, alpha);
            }
            BufferUploader.drawWithShader(buffer.build());
            RenderSystem.setShaderTexture(0, 0);
            RenderSystem.disableBlend();
            RenderSystem.enableDepthTest();

            // Draw rounded border (not in BrowserScreen)
            if (!(Minecraft.getInstance().screen instanceof BrowserScreen)) {
                drawRoundedBorder(gui, left, top, this.widgetWidth, this.widgetHeight,
                        5.0f, // corner radius
                        this.backgroundColor, // Background color
                        2.0f); // border thickness
            }
        }

    }

    private void drawRoundedBorder(GuiGraphics gui, float x, float y, float width, float height, float radius, int color, float thickness) {
        float scale = this.scale;
        x *= scale;
        y *= scale;
        width *= scale;
        height *= scale;
        radius *= scale;
        thickness *= scale;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);

        Color borderColor = new Color(color, true);
        int r = borderColor.getRed();
        int g = borderColor.getGreen();
        int b = borderColor.getBlue();
        int a = borderColor.getAlpha();

        // Draw border as connected segments with rounded corners
        int segments = 16; // Number of segments per corner
        float angleStep = (float) (Math.PI / 2 / segments);

        // Top-left corner
        for (int i = 0; i <= segments; i++) {
            float angle = (float) Math.PI + i * angleStep;
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);
            buffer.addVertex(x + radius + cos * radius, y + radius + sin * radius, 0).setColor(r, g, b, a);
            buffer.addVertex(x + radius + cos * (radius - thickness), y + radius + sin * (radius - thickness), 0).setColor(r, g, b, a);
        }

        // Top-right corner
        for (int i = 0; i <= segments; i++) {
            float angle = (float) (Math.PI * 1.5) + i * angleStep;
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);
            buffer.addVertex(x + width - radius + cos * radius, y + radius + sin * radius, 0).setColor(r, g, b, a);
            buffer.addVertex(x + width - radius + cos * (radius - thickness), y + radius + sin * (radius - thickness), 0).setColor(r, g, b, a);
        }

        // Bottom-right corner
        for (int i = 0; i <= segments; i++) {
            float angle = i * angleStep;
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);
            buffer.addVertex(x + width - radius + cos * radius, y + height - radius + sin * radius, 0).setColor(r, g, b, a);
            buffer.addVertex(x + width - radius + cos * (radius - thickness), y + height - radius + sin * (radius - thickness), 0).setColor(r, g, b, a);
        }

        // Bottom-left corner
        for (int i = 0; i <= segments; i++) {
            float angle = (float) (Math.PI * 0.5) + i * angleStep;
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);
            buffer.addVertex(x + radius + cos * radius, y + height - radius + sin * radius, 0).setColor(r, g, b, a);
            buffer.addVertex(x + radius + cos * (radius - thickness), y + height - radius + sin * (radius - thickness), 0).setColor(r, g, b, a);
        }

        // Close the loop back to start
        float angle = (float) Math.PI;
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        buffer.addVertex(x + radius + cos * radius, y + radius + sin * radius, 0).setColor(r, g, b, a);
        buffer.addVertex(x + radius + cos * (radius - thickness), y + radius + sin * (radius - thickness), 0).setColor(r, g, b, a);

        BufferUploader.drawWithShader(buffer.build());
        RenderSystem.disableBlend();
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (browser != null && initialized) {
            if (mouseX >= this.getLeftSide() && mouseX <= this.getLeftSide() + this.widgetWidth && mouseY >= this.getTop() && mouseY <= this.getTop() + this.widgetHeight) {
                int browserX = (int)(mouseX - this.getLeftSide());
                int browserY = (int)(mouseY - this.getTop());

                browser.sendMousePress(browserX, browserY, button);
                browser.sendMouseRelease(browserX, browserY, button);
                return true;
            }
        }
        return false;
    }

    public void mouseMoved(double mouseX, double mouseY) {
        if (browser != null && initialized) {
            if (mouseX >= this.getLeftSide() && mouseX <= this.getLeftSide() + this.widgetWidth && mouseY >= this.getTop() && mouseY <= this.getTop() + this.widgetHeight) {
                int browserX = (int)(mouseX - this.getLeftSide());
                int browserY = (int)(mouseY - this.getTop());
                browser.sendMouseMove(browserX, browserY);
            }
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (browser != null && initialized) {
            if (mouseX >= this.getLeftSide() && mouseX <= this.getLeftSide() + this.widgetWidth && mouseY >= this.getTop() && mouseY <= this.getTop() + this.widgetHeight) {
                int browserX = (int)(mouseX - this.getLeftSide());
                int browserY = (int)(mouseY - this.getTop());
                browser.sendMouseWheel(browserX, browserY, delta, 0);
                return true;
            }
        }
        return false;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (browser != null && initialized) {
            browser.sendKeyPress(keyCode, scanCode, modifiers);
            return true;
        }
        return false;
    }

    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (browser != null && initialized) {
            browser.sendKeyRelease(keyCode, scanCode, modifiers);
            return true;
        }
        return false;
    }

    public boolean charTyped(char chr, int modifiers) {
        if (browser != null && initialized) {
            browser.sendKeyTyped(chr, modifiers);
            return true;
        }
        return false;
    }

    public boolean isInitialized() {
        return initialized && browser != null;
    }

    public void cleanup() {
        if (browser != null) {
            try {
                browser.close();
            } catch (Exception e) {
                System.err.println("Error closing browser: " + e.getMessage());
            }
            browser = null;
        }
        initialized = false;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        if (browser != null) {
            browser.resize(width, height);
        }
    }

    public void setOpacity(float opacity) {
        this.opacity = Math.max(0.0f, Math.min(1.0f, opacity));
    }

    public float getOpacity() {
        return opacity;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    // Browser navigation methods
    public void goBack() {
        if (browser != null && browser.canGoBack()) {
            browser.goBack();
        }
    }

    public void goForward() {
        if (browser != null && browser.canGoForward()) {
            browser.goForward();
        }
    }

    public void refresh() {
        if (browser != null) {
            browser.reload();
        }
    }

    public void setZoom(float zoomLevel) {
        if (browser != null) {
            browser.setZoomLevel(zoomLevel);
        }
    }

    public void pauseAllMedia() {
        if (browser != null && initialized) {
            String pauseScript =
                    "var videos = document.querySelectorAll('video'); " +
                            "for(var i = 0; i < videos.length; i++) { videos[i].pause(); } " +
                            "var audios = document.querySelectorAll('audio'); " +
                            "for(var i = 0; i < audios.length; i++) { audios[i].pause(); }";

            browser.executeJavaScript(pauseScript, "", 0);
        }
    }
}