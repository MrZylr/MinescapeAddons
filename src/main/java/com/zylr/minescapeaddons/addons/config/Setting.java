package com.zylr.minescapeaddons.addons.config;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;

public class Setting {
    private String name;
    private Config setting;
    private Minecraft mc = Minecraft.getInstance();
    private FontRenderer font = mc.fontRenderer;
    private static final ResourceLocation TOGGLE_IMAGE_BUTTON = new ResourceLocation(Main.ID, "textures/gui/toggle_button.png");
    private int buttonWidth = font.FONT_HEIGHT*2;
    private int buttonHeight = font.FONT_HEIGHT;
    private int lastStringRenderX = 0;
    private int lastButtonRenderX = 0;
    private int lastRenderY = 0;

    public Setting(Config setting) {
        this.setting = setting;
        this.name = setting.getName();
    }

    public void render(int stringPosX, int buttonPosX, int posY) {
        font.drawString(this.name, stringPosX, posY, -1);

        int buttonColor = Color.RED.getRGB();
        int togglePostion = buttonPosX+buttonWidth/2;
        if (!setting.isToggled()) {
            buttonColor = new Color(8, 160, 38, 255).getRGB();
            togglePostion = buttonPosX;
        }
        lastStringRenderX = stringPosX;
        lastButtonRenderX = buttonPosX;
        lastRenderY = posY;
        GuiUtils.drawGradientRect(1, buttonPosX, posY, buttonPosX+buttonWidth, posY+buttonHeight, buttonColor, buttonColor);
        BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth() - togglePostion, mc.getMainWindow().getScaledHeight() - posY, buttonWidth/2, buttonHeight, TOGGLE_IMAGE_BUTTON, false);
    }

    public boolean isClicked(double mouseX, double mouseY) {

        if (mouseY > lastRenderY && mouseY < lastRenderY+font.FONT_HEIGHT) {
            if (mouseX > lastStringRenderX && mouseX < lastStringRenderX + font.getStringWidth(name))
                return true;
            if (mouseX > lastButtonRenderX && mouseX < lastButtonRenderX + buttonWidth)
                return true;
        }
        return false;
    }

    public Config getSetting() { return this.setting; }

    public int getButtonWidth() { return this.buttonWidth; }
}
