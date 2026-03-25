package com.zylr.minescapeaddons.addons.gui.widgets.minimap;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import java.awt.*;

public class RunEnergyOrb extends Widget{
    private static final ResourceLocation FRAME = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_frame.png");
    private static final ResourceLocation ACTIVATED_BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_run_activated.png");
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_run.png");
    private static final ResourceLocation ACTIVATED_ICON = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_run_icon_activated.png");
    private static final ResourceLocation ICON = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/minimap/minimap_orb_run_icon.png");
    ResourceLocation buttonImage = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "widget/clear");

    private ImageButton sprintButton;

    public RunEnergyOrb(Widget parent, boolean visible) {
        this.parent = parent;
        this.visible = visible;

        this.widgetWidth = 57;
        this.widgetHeight = 34;

        this.isParent = false;

        this.anchorX = 0;
        this.anchorY = 0;

        this.scale = parent.scale;

        this.sprintButton = new ImageButton(this.anchorX, this.anchorY, this.widgetWidth, this.widgetHeight, new WidgetSprites(buttonImage, buttonImage), button -> {
            // Handle button click
            Config.setSprint(!Config.getSprint());
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
        if (this.visible) {
            super.render(gui);
            this.scale = parent.scale;

            this.anchorX = this.parent.getAnchorX() -20;
            this.anchorY = this.parent.getAnchorY() + 111;

            this.sprintButton.setX(this.anchorX);
            this.sprintButton.setY(this.anchorY);

            int backgroundXOffset = 27;
            int backgroundYOffset = 4;

            ResourceLocation background = Config.getSprint() ? ACTIVATED_BACKGROUND : BACKGROUND;
            ResourceLocation icon = Config.getSprint() ? ACTIVATED_ICON : ICON;

            gui.blit(FRAME, this.anchorX, this.anchorY, 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);
            gui.blit(background, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);
            gui.blit(icon, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);


            Font font = Minecraft.getInstance().font;
            if (Config.getFixedMode() && Config.getSmallInventory())
                font = MinescapeAddons.getInstance().getVanillaFont();
            if (scale < 1.0f) {
                font = MinescapeAddons.getInstance().getVanillaFont();
            }
            gui.drawString(font, "100", this.anchorX + 10, this.anchorY + 17, Color.GREEN.getRGB());
        }
    }

    public void renderResizable(GuiGraphics gui) {
        if (this.visible) {
            super.render(gui);
            this.scale = parent.scale;

            this.anchorX = this.parent.getAnchorX() + 20;
            this.anchorY = this.parent.getAnchorY() + 111;

            this.sprintButton.setX(this.anchorX);
            this.sprintButton.setY(this.anchorY);

            int backgroundXOffset = 27;
            int backgroundYOffset = 4;

            ResourceLocation background = Config.getSprint() ? ACTIVATED_BACKGROUND : BACKGROUND;
            ResourceLocation icon = Config.getSprint() ? ACTIVATED_ICON : ICON;

            gui.blit(FRAME, this.anchorX, this.anchorY, 0, 0, this.widgetWidth, this.widgetHeight, this.widgetWidth, this.widgetHeight);
            gui.blit(background, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);
            gui.blit(icon, this.anchorX + backgroundXOffset, this.anchorY + backgroundYOffset, 0, 0, 26, 26, 26, 26);


            Font font = Minecraft.getInstance().font;
            if (scale < 1.0f) {
                font = MinescapeAddons.getInstance().getVanillaFont();
            }
            gui.drawString(font, "100", this.anchorX + 10, this.anchorY + 17, Color.GREEN.getRGB());
        }
    }

    public ImageButton getSprintButton() {
        return this.sprintButton;
    }
}
