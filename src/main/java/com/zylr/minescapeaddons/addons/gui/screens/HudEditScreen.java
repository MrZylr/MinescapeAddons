package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.hud.Hud;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.ScoreboardWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HudEditScreen extends ModScreen {

    IWidget draggedWidget;
    double dragOffsetX;
    double dragOffsetY;

    public HudEditScreen(Hud hud) {
        super(new StringTextComponent("Hud Edit Screen"));

        this.widgetsFromHud(hud);
        this.draggedWidget = null;
        dragOffsetX = 0;
        dragOffsetY = 0;
    }
    @Override
    public void init() {
        super.init();
        renderWidgets();
    }

    @Override
    public void tick() {
        renderWidgets();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) {
        super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);
        // Check for if being clicked/dragged
        for (IWidget widget : widgets) {
            if (widget.isHovered()) {
                this.draggedWidget = widget;

                // Setup the drag offset
                dragOffsetX = widget.getAnchorX() - mouseX;
                dragOffsetY = widget.getAnchorY() - mouseY;
            }
        }
        return true;
    }
    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
        if (draggedWidget != null) {
            dragWidget(draggedWidget);
            return true;
        }
        return false;
    }

    private void dragWidget(IWidget widget) {
        double newX = mc.mouseHelper.getMouseX() / mc.getMainWindow().getGuiScaleFactor() + dragOffsetX;
        double newY = mc.mouseHelper.getMouseY() / mc.getMainWindow().getGuiScaleFactor() + dragOffsetY;

        widget.setAnchorX((int)newX);
        widget.setAnchorY((int)newY);
    }

    @Override
    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_)  {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
        // Set the anchor point in the file
        if (draggedWidget != null) {
            // Config work
            draggedWidget.getConfig().setProperty("x", draggedWidget.getAnchorX()+"");
            draggedWidget.getConfig().setProperty("y", draggedWidget.getAnchorY()+"");
            if (draggedWidget instanceof ScoreboardWidget) {
                draggedWidget.getConfig().setProperty("anchor", ((ScoreboardWidget) draggedWidget).anchor+"");
            }
            draggedWidget.saveConfig();
            // Reset draggedWidget
            draggedWidget = null;
        }
        // Redraw the screen once the mouse has been let go and all data saved
        // This removes "ghost" buttons
        mc.displayGuiScreen(new HudEditScreen(Main.getInstance().getRsHud()));
        return true;
    }
}