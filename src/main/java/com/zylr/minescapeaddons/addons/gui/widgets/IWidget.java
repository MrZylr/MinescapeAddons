package com.zylr.minescapeaddons.addons.gui.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Properties;

public interface IWidget {

    void setupConfig();
    void saveConfig();
    Properties getConfig();

    void render(GuiGraphics gui);
    void renderChildren();
    void drawImage(ResourceLocation location, GuiGraphics gui);
    void drawBackground(GuiGraphics gui);
    void drawBackground(GuiGraphics gui, int color);
    List<Button> getButtons();

    int getTop();
    int getBottom();
    int getLeftSide();
    int getRightSide();
    boolean isTopHalf();
    boolean isBottomHalf();
    boolean isLeftSide();
    boolean isRightSide();
    boolean isHovered();
    public double setRelativeX(int anchorX);
    public double setRelativeY(int anchorY);
    public int getAnchorXFromRelative(double relativeAnchorX);
    public int getAnchorYFromRelative(double relativeAnchorY);

    int getAnchorX();
    int getAnchorY();
    void setAnchorX(int x);
    void setAnchorY(int y);
    int getWidgetWidth();
    int getWidgetHeight();
    boolean isVisible();
    void setVisible(boolean visible);

    void fixAnchors();

    WidgetType getType();
    List<IWidget> getChildren();
    Widget getParent();
    void setParent(Widget parent);

    double getRelativeAnchorX();

    double getRelativeAnchorY();

    ResourceLocation getHighlight();
}
