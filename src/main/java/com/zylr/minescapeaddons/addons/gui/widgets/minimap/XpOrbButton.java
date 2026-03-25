package com.zylr.minescapeaddons.addons.gui.widgets.minimap;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;

public class XpOrbButton extends Widget {
    private static final ResourceLocation FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_frame_small.png");
    private static final ResourceLocation ICON = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_xp.png");
    ResourceLocation buttonImage = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "widget/clear");

    private ImageButton xpButton;

    public XpOrbButton(Widget parent, boolean visible) {
        this.parent = parent;
        this.visible = visible;

        this.widgetWidth = 34;
        this.widgetHeight = 34;

        this.isParent = false;

        this.anchorX = 0;
        this.anchorY = 0;

        this.xpButton = new ImageButton(this.anchorX, this.anchorY, this.widgetWidth, this.widgetHeight, new WidgetSprites(buttonImage, buttonImage), button -> {
            // Handle button click
            Config.setXpTracker(!Config.getXpTracker());
        });
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
    }

    public void renderResizable(GuiGraphics gui) {
        if (this.visible) {
            super.render(gui);

            this.anchorX = this.parent.getAnchorX();
            this.anchorY = this.parent.getAnchorY() + 20;

            this.xpButton.setX(this.anchorX);
            this.xpButton.setY(this.anchorY);

            gui.blit(FRAME, this.anchorX, this.anchorY, 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);
            gui.blit(ICON, this.anchorX + 3, this.anchorY + 3, 0, 0, 28, 28, 28, 28);
        }
    }

    public ImageButton getXpButton() {
        return this.xpButton;
    }
}
