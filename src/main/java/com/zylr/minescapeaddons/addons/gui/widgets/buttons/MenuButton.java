package com.zylr.minescapeaddons.addons.gui.widgets.buttons;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.awt.*;

public class MenuButton extends AbstractRightClickableImageButton{

    private MenuButton.OnLeftClick onLeftClick;
    public MenuButton(ResourceLocation image, Component text, int textColor, int x, int y, int width, int height, OnLeftClick onLeftClick) {
        super(image, text, textColor, x, y, width, height);
        this.onLeftClick = onLeftClick;

        this.scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;

        this.setVisible(true);
    }

    @Override
    public void render(GuiGraphics gui) {
        if (this.isVisible()) {
            this.fixAnchors();
            gui.pose().pushPose();
            gui.pose().translate(0, 0, 1501);
            this.drawImage(this.image, gui);
            this.drawText(gui);
            if (this.isHovered())
                gui.fill(this.getLeftSide()+6, this.getTop()-1, this.getRightSide()-6, this.getBottom(), new Color(255, 255, 255, 30).getRGB());

            this.onHover();
            gui.pose().popPose();
        }
    }

    @Override
    public void drawText(GuiGraphics gui) {
        gui.drawString(mc.font, this.text, this.getLeftSide()+6, this.getTop(), this.color, false);
    }

    @Override
    public void onRightClick() {
    }

     @Override
    public void onClick(double mouseX, double mouseY, int button) {
        // Caller has already verified hover via AbstractRightClickMenu.isButtonHovered —
        // skip the isHovered() re-check so scale pivot issues can't block the action.
        if (this.isVisible()) {
            if (button == 0) this.onLeftClick();
            else if (button == 1) this.onRightClick();
        }
    }

    @Override
    public void onLeftClick() {
        // Call the onLeftClick method if it is not null
        if (this.onLeftClick != null) {
            this.onLeftClick.onLeftClick(this);
        }
    }


    @OnlyIn(Dist.CLIENT)
    public interface OnLeftClick {
        void onLeftClick(MenuButton var1);
    }
}
