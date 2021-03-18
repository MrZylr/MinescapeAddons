package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;

public class InventoryWidget extends Widget{

    Minecraft mc = Minecraft.getInstance();
    public final ResourceLocation INVENTORY_SCREEN = new ResourceLocation(Main.ID, "textures/gui/inventory.png");

    public InventoryWidget() {
        this.type = WidgetType.INVENTORYWIDGET;
        this.setupConfig();

        this.widgetWidth = 133;
        this.widgetHeight = 178;

        try {
            this.relativeAnchorX = Double.parseDouble(config.getProperty("x"));
            this.relativeAnchorY = Double.parseDouble(config.getProperty("y"));
        }catch(Exception ex) {
            setupConfig();
            // TODO:: Setup to redo config in a method IWidget
            this.relativeAnchorX = Double.parseDouble(config.getProperty("x"));
            this.relativeAnchorY = Double.parseDouble(config.getProperty("y"));
        }

    }

    @Override
    public void render() {
        super.render();

        GuiUtils.drawGradientRect(1, this.getLeftSide(), this.getTop(), this.getRightSide(), this.getBottom(),
                Color.BLACK.getRGB(), Color.BLACK.getRGB());
    }
}
