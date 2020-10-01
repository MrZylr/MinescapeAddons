package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Widget {

    // Position variables
    protected int anchorX;
    protected int anchorY;
    protected int widgetWidth;
    protected int widgetHeight;
    protected int width;
    protected int height;
    protected WidgetType type;
    List<Button> buttons;


    // TODO:: Change anchor from being center based to the matching quadrant on the screen the widget is currently in
    // Implement a top/bottom & left/right check

    // Minecraft variables
    Minecraft mc = Minecraft.getInstance();
    MainWindow window = mc.getMainWindow();

    public Widget() {
        this.type = null;
        this.width = window.getScaledWidth();
        this.height = window.getScaledHeight();
        buttons = new ArrayList<>();
    }

    // Check if this widget is being hovered by the mouse or not
    public boolean isHovered() {
        double pointerX = mc.mouseHelper.getMouseX()/window.getGuiScaleFactor();
        double pointerY = mc.mouseHelper.getMouseY()/window.getGuiScaleFactor();

        if (pointerX > getLeftSide()-20 && pointerX < getRightSide()+20 && pointerY > getTop()-20 && pointerY < getBottom()+20)
            return true;
        return false;
    }

    public void render() {
        // Fix the anchor point if it goes off screen
        fixAnchors();
    }

    public List<Button> getButtons() {
        buttons.clear();
        return buttons;
    }

    public boolean isLeftHalf() {
        if (anchorX < window.getScaledWidth()/2)
            return true;
        return false;
    }

    public boolean isTopHalf() {
        if (anchorY < window.getScaledHeight()/2)
            return true;
        return false;
    }

    protected void fixAnchors() {
        if (getRightSide() > window.getScaledWidth())
            anchorX = window.getScaledWidth() - widgetWidth/2;
        if (getLeftSide() < 0)
            anchorX = widgetWidth/2;
        if (getBottom() > window.getScaledHeight())
            anchorY = window.getScaledHeight() - widgetHeight/2;
        if (getTop() < 0 )
            anchorY = widgetHeight/2;
    }

    public int getLeftSide() {
        int halfWidth = widgetWidth/2;
        return anchorX - halfWidth;
    }

    public int getRightSide() {
        int halfWidth = widgetWidth/2;
        return anchorX + halfWidth;
    }

    public int getTop() {
        int halfHeight = widgetHeight/2;
        return anchorY - halfHeight;
    }

    public int getBottom() {
        int halfHeight = widgetHeight/2;
        return anchorY + halfHeight;
    }

    public int getAnchorX() {
        return anchorX;
    }

    public void setAnchorX(int anchorX) {
        this.anchorX = anchorX;
    }

    public int getAnchorY() {
        return anchorY;
    }

    public void setAnchorY(int anchorY) {
        this.anchorY = anchorY;
    }

    public WidgetType getType() { return type; }
}