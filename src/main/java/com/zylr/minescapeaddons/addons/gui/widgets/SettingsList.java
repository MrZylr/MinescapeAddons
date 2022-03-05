package com.zylr.minescapeaddons.addons.gui.widgets;


import com.zylr.minescapeaddons.addons.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;

public class SettingsList extends ScrollPanel {
    Minecraft mc = Minecraft.getInstance();
    int settingsFontColor;

    int settingsMaxWidth;
    int settingsMaxHeight;

    public SettingsList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn);
        this.settingsFontColor = new Color(255, 255, 255, 255).getRGB();
        this.settingsMaxHeight = heightIn;
        this.settingsMaxWidth = widthIn;
    }

    @Override
    public int getContentHeight() {
        int height = 50;
        height += Config.values().length * mc.fontRenderer.FONT_HEIGHT;

        if (height < this.bottom - this.top - 8)
            height = this.bottom - this.top -8;

        return height;
    }

    @Override
    public void drawPanel(int settingsWidth, int settingsMaxWidth, Tessellator tess, int mouseX, int mouseY) {

        FontRenderer font = mc.fontRenderer;
        // Spaces from side of screen and between lines
        int padding = 15;
        int gap = 10;

        // X font postion
        int xPos = settingsWidth - settingsMaxWidth + padding;
        // Y position to start at and then increment
        int yPos = gap;

        for (Config value : Config.values()) {
            font.drawString(value.getName(), xPos, yPos, settingsFontColor);
            yPos += gap+font.FONT_HEIGHT;
        }
    }
}
