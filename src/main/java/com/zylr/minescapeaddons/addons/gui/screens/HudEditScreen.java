package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.gui.hud.Hud;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HudEditScreen extends ModScreen {
    public HudEditScreen(Hud hud) {
        super(new StringTextComponent("Hud Edit Screen"));

        this.widgetsFromHud(hud);

        renderWidgets();
    }

    @Override
    public void tick() {
        renderWidgets();
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
        // Check for if being clicked/dragged
        for (Widget widget : widgets) {
            widgetClicked(widget);
        }
        return true;
    }
    @Override
    public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
        super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
        for (Widget widget : widgets) {
            if (widget.isHovered()) {
                dragWidget(widget);
                return true;
            }
        }
        return false;
    }
    private void widgetClicked(Widget widget) {
        if (widget.isHovered())
            System.out.println("hovering");
    }

    private void dragWidget(Widget widget) {
        widget.setAnchorX((int)(mc.mouseHelper.getMouseX() / mc.getMainWindow().getGuiScaleFactor()));
        widget.setAnchorY((int)(mc.mouseHelper.getMouseY() / mc.getMainWindow().getGuiScaleFactor()));
    }

    @Override
    public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_)  {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
        // Set the anchor point in the file
        for (Widget widget : widgets) {
            if (widget.isHovered()) {
                Scanner reader = PersistenceFile.readFile(widget.getType().getFile().getPath());
                List<String> widgetData = new ArrayList<>();
                while (reader.hasNext()) {
                    String[] data = reader.nextLine().split(PersistenceFile.SPLIT);

                    // Loop through widget file to update anchors
                    switch (data[0].toLowerCase()) {
                        case "anchorx":
                            data[1] = widget.getAnchorX() + "";
                            break;
                        case "anchory":
                            data[1] = widget.getAnchorY() + "";
                            break;
                    }
                    widgetData.add(data[0] + PersistenceFile.SPLIT + data[1]);
                }
                PersistenceFile.writeFile(widget.getType().getFile().getPath(), widgetData);
            }
        }
        return true;
    }
}
