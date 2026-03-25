package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.huds.resizableclassic.ResizableClassic;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class CombatWidget extends Widget {
    public final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/combat_inventory.png");
    public final ResourceLocation FIXED_BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/classic_combat.png");

    public CombatWidget(int x, int y) {
        this.anchorX = x;
        this.anchorY = y;

        this.widgetWidth = ResizableClassic.widgetWidth;
        this.widgetHeight = ResizableClassic.widgetHeight;
        this.isParent = true;
    }
    public CombatWidget(boolean visible) {
        this.visible = visible;
        this.isParent = false;
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        if (this.visible) {

            this.anchorX = this.parent.getAnchorX();
            this.anchorY = this.parent.getAnchorY();
            this.widgetWidth = this.parent.getWidgetWidth();
            this.widgetHeight = this.parent.getWidgetHeight();

            super.render(gui);
            this.drawImage(FIXED_BACKGROUND, gui);
            this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);
            this.drawTab(gui);
        }
    }

    public void renderResizable(GuiGraphics gui) {
        if (this.visible) {

            if(!Config.getSmallInventory()) {
                this.anchorX = this.parent.getAnchorX() + 13;
                this.anchorY = this.parent.getAnchorY() + 20;
                this.widgetWidth = this.parent.getWidgetWidth() - 26;
                this.widgetHeight = this.parent.getWidgetHeight() - 40;
            } else {
                this.anchorX = this.parent.getAnchorX() + 10;
                this.anchorY = this.parent.getAnchorY() + 16;
                this.widgetWidth = this.parent.getWidgetWidth() - 20;
                this.widgetHeight = this.parent.getWidgetHeight() - 32;
            }

            float scale = this.parent.scale;
            gui.pose().pushPose();
            this.scaleAroundParentAnchor(gui, scale);

            super.render(gui);
            this.drawImage(BACKGROUND, gui);
            this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);
            this.drawTab(gui);

            gui.pose().popPose();
        }
    }

    public void drawTab(GuiGraphics gui) {

    }

    @Override
    public ResourceLocation getHighlight() { return HudMenuWidget.SOTNE_TOP_LEFT_SELECTED; }

}
