package com.zylr.minescapeaddons.addons.gui.widgets.buttons;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.slf4j.Logger;

public abstract class AbstractRightClickableImageButton extends Widget {
    private static final Logger LOGGER = MinescapeAddons.LOGGER;
    public ResourceLocation image;
    public Component text;
    public int color;

    public AbstractRightClickableImageButton(ResourceLocation image, Component text, int textColor, int x, int y, int width, int height) {
        super();
        this.image = image;
        this.text = text;
        this.color = textColor;
        this.setAnchorX(x);
        this.setAnchorY(y);

        this.widgetWidth = width;
        this.widgetHeight = height;
        this.isParent = true;
    }

    @Override
    public void render(GuiGraphics gui) {
        super.render(gui);
        if (this.isVisible()) {
            this.drawImage(this.image, gui);
            this.drawText(gui);

            this.onHover();
        }
    }

    public void drawText(GuiGraphics gui) {
        int textWidth = Minecraft.getInstance().font.width(text);
        int textHeight = Minecraft.getInstance().font.lineHeight;
        int centerXOfButton = this.getLeftSide() + (this.widgetWidth/2);
        int centerYofButton = this.getTop() + (this.widgetHeight/2);
        gui.drawString(Minecraft.getInstance().font, this.text, centerXOfButton - (textWidth/2),
                centerYofButton - (textHeight/2), this.color, false);
    }

    private void playSound(SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public void onHover() {
        if (this.isHovered() && this.isVisible()) {

        }
    }

    public void onClick(double mouseX, double mouseY, int button) {
        if (this.isHovered() && this.isVisible()) {
            switch (button) {
                case 0:
                    this.onLeftClick();
                    break;
                case 1:
                    this.onRightClick();
                    break;
                default:
                    break;
            }
        }
    }

    public void onLeftClick() {
        playSound(Minecraft.getInstance().getSoundManager());
    }
    public void onRightClick() {
        playSound(Minecraft.getInstance().getSoundManager());
    }
}
