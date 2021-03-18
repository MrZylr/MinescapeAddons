package com.zylr.minescapeaddons.addons.gui.widgets;

import net.minecraft.client.gui.widget.button.Button;

import java.util.List;
import java.util.Properties;

public interface IWidget {

    // Check if this widget is being hovered by the mouse or not
    boolean isHovered();

    void render();

    void pixelXToRelativeX(int xPos);

    void pixelYToRelativeY(int yPos);

    List<Button> getButtons();

    boolean isLeftHalf();

    boolean isTopHalf();

    void fixAnchors();

    int getLeftSide();

    int getRightSide();

    int getTop();

    int getBottom();

    int getAnchorX();

    double getRelativeAnchorX();

    void setAnchorX(int anchorX);

    void setRelativeAnchorX(double relativeAnchorX);

    int getAnchorY();

    double getRelativeAnchorY();

    void setAnchorY(int anchorY);

    void setRelativeAnchorY(double relativeAnchorY);

    int getWidgetHeight();

    int getWidgetWidth();

    WidgetType getType();

    Properties getConfig();

    void setupConfig();

    void saveConfig();

    boolean isVisible();
}
