package com.zylr.minescapeaddons.addons.gui.widgets.buttons;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.SkillHoverWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.menus.RightClickSkillMenu;
import com.zylr.minescapeaddons.addons.skills.Skill;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.slf4j.Logger;

import java.util.ArrayList;

public class SkillButton extends AbstractRightClickableImageButton{
    private static final Logger LOGGER = MinescapeAddons.LOGGER;
    private Skill skill;
    private OnLeftClick onLeftClick;
    private SkillHoverWidget hoverWidget;

    public SkillButton(Skill skill, ResourceLocation image, Component text, int textColor, int x, int y, int width, int height, OnLeftClick onLeftClick) {
        super(image, text, textColor, x, y, width, height);
        this.skill = skill;
        this.onLeftClick = onLeftClick;

        this.scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;

        this.hoverWidget = null;
    }

    @Override
    public boolean isHovered() {
        var hudMenu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
        double screenX = mc.mouseHandler.xpos() / mc.getWindow().getGuiScale();
        double screenY = mc.mouseHandler.ypos() / mc.getWindow().getGuiScale();
        // Transform using the hudMenu anchor (same pivot used by scaleAroundAnchor in render)
        double wx = hudMenu.toWidgetMouseX(screenX);
        double wy = hudMenu.toWidgetMouseY(screenY);
        return wx >= getLeftSide() && wx < getRightSide() && wy >= getTop() && wy < getBottom();
    }

    @Override
    public void render(GuiGraphics gui) {
        super.render(gui);
        if (this.isVisible()) {

            if (this.hoverWidget != null) {
                this.hoverWidget.setAnchorX(this.getLeftSide() + 1);
                this.hoverWidget.setAnchorY(this.getBottom() + 5);
                this.hoverWidget.render(gui);
            }
        }
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        this.onLeftClick.onLeftClick(this);
    }

    @Override
    public void onRightClick() {
        if (mc.screen instanceof ZylrInventoryScreen) {
            ZylrInventoryScreen screen = (ZylrInventoryScreen) mc.screen;
            if (screen.rightClickSkillMenu == null) {
                int menuX = (int)(mc.mouseHandler.xpos() / mc.getWindow().getGuiScale()) - this.widgetWidth / 2;
                int menuY = (int)(mc.mouseHandler.ypos() / mc.getWindow().getGuiScale()) - 5;
                screen.rightClickSkillMenu = new RightClickSkillMenu(skill.getName(),
                        menuX, menuY, new ArrayList<>(), skill);
                screen.rightClickSkillMenu.setVisible(true);
            }
        }
    }

    @Override
    public void onHover() {
        if (this.isHovered() && this.isVisible()) {
            this.hoverWidget = new SkillHoverWidget(this.skill);
            this.hoverWidget.setVisible(true);

        }else
            this.hoverWidget = null;
    }

    @OnlyIn(Dist.CLIENT)
    public interface OnLeftClick {
        void onLeftClick(SkillButton var1);
    }

}
