package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.MenuButton;
import com.zylr.minescapeaddons.addons.gui.widgets.buttons.SkillButton;
import com.zylr.minescapeaddons.addons.gui.widgets.menus.AbstractRightClickMenu;
import com.zylr.minescapeaddons.addons.gui.widgets.menus.RightClickItemMenu;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Widget implements IWidget {
    public Minecraft mc = Minecraft.getInstance();

    public Properties config = null;
    protected Widget parent;
    //protected AnchorType anchorPointToParent;
    protected List<IWidget> children = new ArrayList<>();
    public int backgroundColor;
    public static final int DEFAULT_BACKGROUND_COLOR = 1258291200;
    public boolean isParent;

    protected int x;
    protected int y;
    protected int anchorX;
    protected int anchorY;
    public float scale;
    public double relativeAnchorx;
    public double relativeAnchorY;
    /** Optional override pivot for isHovered(). Set hasPivotOverride=true to activate. */
    public int pivotX;
    public int pivotY;
    public boolean hasPivotOverride;
    protected int widgetWidth;
    protected int widgetHeight;
    protected int width;
    protected int height;
    protected WidgetType type;
    protected boolean visible;
    boolean isBottomHalf;
    boolean isTopHalf;
    boolean isLeftSide;
    boolean isRightSide;

    public Widget() {
        this.type = null;
        this.width = mc.getWindow().getWidth();
        this.height = mc.getWindow().getHeight();
        this.visible = true;
    }

    // Config File
    public void setupConfig() {
        File configFile = this.getType().getFile();
        Properties chatConfig = new Properties();
        try {
            if (!configFile.exists()) {
                System.out.println("making file");
                configFile.createNewFile();
                // Load defaults for config files
                InputStream chatConfigInput = this.getClass().getClassLoader().getResourceAsStream(this.getType().getDefaultFile());
                chatConfig.load(chatConfigInput);
                // Save default config files
                OutputStream chatConfigOutput = new FileOutputStream(this.getType().getFile().getPath());
                chatConfig.store(chatConfigOutput, null);
            }
            InputStream input = new FileInputStream(this.getType().getFile().getPath());
            this.config = new Properties();
            // Load config
            this.config.load(input);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void resetConfig() {
        try {
            File configFile = this.getType().getFile();

            // Delete the existing config file if it exists
            if (configFile.exists()) {
                if (!configFile.delete()) {
                    System.err.println("Failed to delete config file: " + configFile.getPath());
                    return;
                }
            }

            // Regenerate the config with defaults
            setupConfig();

            System.out.println("Config file reset to defaults: " + configFile.getPath());

        } catch (Exception ex) {
            System.err.println("Error resetting config file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void saveConfig() {
        try (OutputStream output = new FileOutputStream(this.getType().getFile().getPath())) {
            // Save the config
            this.config.store(output, null);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    /** Set to true while HudEditScreen is open so widgets bypass config/condition guards. */
    public static boolean isHudEditOpen = false;

    public void render(GuiGraphics gui) {this.fixAnchors();}

    public void renderChildren() {

    }

    public void drawImage(ResourceLocation location, GuiGraphics gui) {
        gui.blit(location, this.getLeftSide(), this.getTop(),
                0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);
    }

    public void drawBackground(GuiGraphics gui) {
        gui.fill(this.getLeftSide(), this.getTop(), this.getRightSide(), this.getBottom(), this.backgroundColor);
    }
    public void drawBackground(GuiGraphics gui, int color) {
        gui.fill(this.getLeftSide(), this.getTop(), this.getRightSide(), this.getBottom(), color);
    }

    public List<Button> getButtons() {
        return List.of();
    }

    public int getTop() {
        return this.anchorY;
    }

    public int getBottom() {
        return this.anchorY + widgetHeight;
    }

    /** Visual bottom edge: anchorY + widgetHeight * scale */
    public int getScaledBottom() {
        return this.anchorY + (int)(widgetHeight * scale);
    }

    public int getLeftSide() {
        return this.anchorX;
    }

    public int getRightSide() {
        return this.anchorX + widgetWidth;
    }

    /** Visual right edge: anchorX + widgetWidth * scale */
    public int getScaledRightSide() {
        return this.anchorX + (int)(widgetWidth * scale);
    }

    public boolean isTopHalf() {
        return this.isTopHalf = this.anchorY + (this.widgetHeight * scale) / 2 < mc.getWindow().getGuiScaledHeight() / 2.0;
    }

    public boolean isBottomHalf() {
        return this.isBottomHalf = this.anchorY + (this.widgetHeight * scale) / 2 > mc.getWindow().getGuiScaledHeight() / 2.0;
    }

    public boolean isLeftSide() {
        this.isLeftSide = this.anchorX + (this.widgetWidth * scale) / 2 < mc.getWindow().getGuiScaledWidth() / 2.0;
        return this.isLeftSide;
    }

    public boolean isRightSide() {
        this.isRightSide = this.anchorX + (this.widgetWidth * scale) / 2 > mc.getWindow().getGuiScaledWidth() / 2.0;
        return this.isRightSide;
    }

    public void fixAnchors() {
        int screenW = mc.getWindow().getGuiScaledWidth();
        int screenH = mc.getWindow().getGuiScaledHeight();
        // anchorX/Y is a screen-space position; the widget renders from anchorX to
        // anchorX + widgetWidth*scale visually, so clamp so the widget stays on screen.
        int scaledW = (int)(widgetWidth  * scale);
        int scaledH = (int)(widgetHeight * scale);
        if (anchorX + scaledW > screenW) anchorX = screenW - scaledW;
        if (anchorX < 0)                 anchorX = 0;
        if (anchorY + scaledH > screenH) anchorY = screenH - scaledH;
        if (anchorY < 0)                 anchorY = 0;
    }

    public boolean isHovered() {
        double screenX = mc.mouseHandler.xpos() / mc.getWindow().getGuiScale();
        double screenY = mc.mouseHandler.ypos() / mc.getWindow().getGuiScale();
        int px = hasPivotOverride ? pivotX : anchorX;
        int py = hasPivotOverride ? pivotY : anchorY;
        double pointerX = (screenX - px) / scale + px;
        double pointerY = (screenY - py) / scale + py;
        return pointerX > getLeftSide() && pointerX < getRightSide() && pointerY > getTop() && pointerY < getBottom();
    }

    /**
     * Applies a pose scale that keeps the widget's top-left corner fixed at its
     * current (anchorX, anchorY) screen position regardless of scale value.
     * Call this inside a pushPose/popPose block instead of a bare pose().scale().
     */
    public void scaleAroundAnchor(GuiGraphics gui, float scale) {
        gui.pose().translate(anchorX * (1.0f - scale), anchorY * (1.0f - scale), 0);
        gui.pose().scale(scale, scale, 1.0f);
    }

    /**
     * Like {@link #scaleAroundAnchor} but uses the parent's anchor as the pivot.
     * Child widgets that live inside a scaled parent should use this so that
     * both the parent border and the child content share the exact same pivot
     * point and therefore stay perfectly aligned at any scale.
     */
    public void scaleAroundParentAnchor(GuiGraphics gui, float scale) {
        int px = parent != null ? parent.anchorX : anchorX;
        int py = parent != null ? parent.anchorY : anchorY;
        gui.pose().translate(px * (1.0f - scale), py * (1.0f - scale), 0);
        gui.pose().scale(scale, scale, 1.0f);
    }

    /**
     * Converts a raw screen mouse X into widget-local coordinates,
     * accounting for the widget scaling anchored at its top-left corner.
     */
    public double toWidgetMouseX(double screenMouseX) {
        return (screenMouseX - anchorX) / scale + anchorX;
    }

    /**
     * Converts a raw screen mouse Y into widget-local coordinates,
     * accounting for the widget scaling anchored at its top-left corner.
     */
    public double toWidgetMouseY(double screenMouseY) {
        return (screenMouseY - anchorY) / scale + anchorY;
    }

    public void setWidgetWidth(int width) {
        this.widgetWidth = width;
    }
    public void setWidgetHeight(int height) {
        this.widgetHeight = height;
    }

    public double setRelativeX(int anchorX) {

        this.relativeAnchorx = (double)anchorX / mc.getWindow().getGuiScaledWidth();
        this.getAnchorXFromRelative(this.relativeAnchorx);
        return this.relativeAnchorx;
    }
    public double setRelativeY(int anchorY) {
        this.relativeAnchorY = (double)anchorY / mc.getWindow().getGuiScaledHeight();
        this.getAnchorYFromRelative(this.relativeAnchorY);
        return this.relativeAnchorY;
    }
    public int getAnchorXFromRelative(double relativeAnchorX) {
        this.relativeAnchorx = relativeAnchorX;
        this.anchorX = (int) (this.relativeAnchorx * mc.getWindow().getGuiScaledWidth());
        return this.anchorX;
    }
    public int getAnchorYFromRelative(double relativeAnchorY) {
        this.relativeAnchorY = relativeAnchorY;
        this.anchorY = (int) (this.relativeAnchorY * mc.getWindow().getGuiScaledHeight());
        return this.anchorY;
    }

    public int getAnchorX() {
        return this.anchorX;
    }

    public int getAnchorY() {
        return this.anchorY;
    }

    public void setAnchorX(int x) {
        this.anchorX = x;
    }

    public void setAnchorY(int y) {
        this.anchorY = y;
    }

    public int getWidgetWidth() {
        return this.widgetWidth;
    }

    public int getWidgetHeight() {
        return this.widgetHeight;
    }

    public WidgetType getType() {
        return this.type;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public List<IWidget> getChildren() {
        return this.children;
    }

    public Widget getParent() {
        return this.parent;
    }

    public void setParent(Widget parent) {
        this.parent = parent;
    }

    @Override
    public double getRelativeAnchorX() {
        return this.relativeAnchorx;
    }

    @Override
    public double getRelativeAnchorY() {
        return this.relativeAnchorY;
    }

    public ResourceLocation getHighlight() { return null; }
}
