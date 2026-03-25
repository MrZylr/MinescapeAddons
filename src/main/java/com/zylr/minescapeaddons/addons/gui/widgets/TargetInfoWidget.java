// java
package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.OptionalInt;

public class TargetInfoWidget extends Widget {

    public TargetInfoWidget(double x, double y) {
        super();
        this.type = WidgetType.TARGET_INFO;

        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;
        this.setupConfig();
        this.setVisible(false);

        this.widgetWidth = 75;
        this.widgetHeight = 35;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0.7")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));
    }

    public void triggerDeath() {
        System.out.println("Target is dead, resetting.");
        // Check if Barrows target
        MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().entityDied(EntityTracker.getTargetedEntity());
    }

    @Override
    public void render(GuiGraphics gui){
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        if (!Config.getTargetInfo())
            return;

        super.render(gui);

        this.anchorX = (mc.getWindow().getGuiScaledWidth() - ViewportAdjustmentListener.PANEL_WIDTH - this.widgetWidth);
        this.anchorY = mc.getWindow().getGuiScaledHeight() - ViewportAdjustmentListener.PANEL_HEIGHT - this.widgetHeight;
        this.fixAnchors();

        this.scale = 1.0f;

        // Call clean up to check if target is outside radius

        EntityTracker.evaluateTargetState();
        EntityTracker.TargetState targetState = EntityTracker.getTargetState();

        this.visible = targetState != EntityTracker.TargetState.NONE;
        if (targetState == EntityTracker.TargetState.LEFT_RADIUS) {
            System.out.println("Target left radius, resetting.");
            EntityTracker.resetTargetedEntity();
            return;
        }


        if (this.visible) {
            // Render background
            this.drawBackground(gui);


            Entity target = EntityTracker.getTargetedEntity();

            // Get target info
            OptionalInt possibleHealth = Util.getHpFromName(target.getDisplayName().getString());
            int totalHealth = EntityTracker.getTotalHp();
            String name = "";
            if (Util.getEntityName(target.getDisplayName().getString()).isPresent())
                name = Util.getEntityName(target.getDisplayName().getString()).get();


            if (possibleHealth.isPresent()) {
                int health = possibleHealth.getAsInt();
                // Draw horizontal health bar along bottom of widget
                int barPadding = 4;
                int barHeight = 6;

                int left = (int) this.anchorX + barPadding;
                int right = (int) this.anchorX + this.widgetWidth - barPadding;
                int bottom = (int) this.anchorY + this.widgetHeight - barPadding;
                int top = bottom - barHeight;

                int barWidth = Math.max(0, right - left);
                int filledWidth = 0;
                if (totalHealth > 0) {
                    float frac = Math.max(0f, Math.min(1f, (float) health / (float) totalHealth));
                    filledWidth = Math.round(frac * barWidth);
                }

                // Colors (ARGB)
                int bgColor = 0xFF2B2B2B;      // dark background
                int fillColor = 0xFF3FC13F;    // green fill
                int dangerColor = 0xFFFF5A5A;  // red fill when low (optional)
                int borderColor = 0xFF000000;  // black border

                // choose fill color (switch to dangerColor at low %)
                int fillCol = (totalHealth > 0 && ((float) health / totalHealth) < 0.25f) ? dangerColor : fillColor;

                // draw bar background
                gui.fill(left, top, right, bottom, bgColor);
                // draw filled portion
                if (filledWidth > 0) {
                    gui.fill(left, top, left + filledWidth, bottom, fillCol);
                }
                // draw border (1px)
                gui.fill(left, top, right, top + 1, borderColor); // top border
                gui.fill(left, bottom - 1, right, bottom, borderColor); // bottom border
                gui.fill(left, top, left + 1, bottom, borderColor); // left border
                gui.fill(right - 1, top, right, bottom, borderColor); // right border

                Font font = Minecraft.getInstance().font;
                if (this.scale < 1) {
                    font = MinescapeAddons.getInstance().getVanillaFont();
                }

                // Draw hp text centered on health bar
                String hpText = health + " / " + totalHealth + " HP";
                gui.drawCenteredString(font, hpText, (int)this.anchorX + this.widgetWidth / 2,
                        (int)this.anchorY + this.widgetHeight - barPadding - barHeight - mc.font.lineHeight,
                        0xFFFFFFFF);

                // Draw name above health bar, centered
                gui.drawCenteredString(font, name, (int)this.anchorX + this.widgetWidth / 2,
                        (int)this.anchorY + barPadding,
                        0xFFFFFFFF);
            }

        }
    }

    public void renderResizable(GuiGraphics gui) {
        if (!Config.getTargetInfo() && !Widget.isHudEditOpen)
            return;

        super.render(gui);

        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.fixAnchors();

        float scale = this.scale;
        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, scale);

        // Call clean up to check if target is outside radius

        EntityTracker.evaluateTargetState();
        EntityTracker.TargetState targetState = EntityTracker.getTargetState();

        this.visible = targetState != EntityTracker.TargetState.NONE;
        if (targetState == EntityTracker.TargetState.LEFT_RADIUS) {
            System.out.println("Target left radius, resetting.");
            EntityTracker.resetTargetedEntity();
            return;
        }


        if (this.visible) {
            // Render background
            this.drawBackground(gui);


            Entity target = EntityTracker.getTargetedEntity();

            // Get target info
            OptionalInt possibleHealth = Util.getHpFromName(target.getDisplayName().getString());
            int totalHealth = EntityTracker.getTotalHp();
            String name = "";
            if (Util.getEntityName(target.getDisplayName().getString()).isPresent())
                name = Util.getEntityName(target.getDisplayName().getString()).get();


            if (possibleHealth.isPresent()) {
                int health = possibleHealth.getAsInt();
                // Draw horizontal health bar along bottom of widget
                int barPadding = 4;
                int barHeight = 6;

                int left = (int) this.anchorX + barPadding;
                int right = (int) this.anchorX + this.widgetWidth - barPadding;
                int bottom = (int) this.anchorY + this.widgetHeight - barPadding;
                int top = bottom - barHeight;

                int barWidth = Math.max(0, right - left);
                int filledWidth = 0;
                if (totalHealth > 0) {
                    float frac = Math.max(0f, Math.min(1f, (float) health / (float) totalHealth));
                    filledWidth = Math.round(frac * barWidth);
                }

                // Colors (ARGB)
                int bgColor = 0xFF2B2B2B;      // dark background
                int fillColor = 0xFF3FC13F;    // green fill
                int dangerColor = 0xFFFF5A5A;  // red fill when low (optional)
                int borderColor = 0xFF000000;  // black border

                // choose fill color (switch to dangerColor at low %)
                int fillCol = (totalHealth > 0 && ((float) health / totalHealth) < 0.25f) ? dangerColor : fillColor;

                // draw bar background
                gui.fill(left, top, right, bottom, bgColor);
                // draw filled portion
                if (filledWidth > 0) {
                    gui.fill(left, top, left + filledWidth, bottom, fillCol);
                }
                // draw border (1px)
                gui.fill(left, top, right, top + 1, borderColor); // top border
                gui.fill(left, bottom - 1, right, bottom, borderColor); // bottom border
                gui.fill(left, top, left + 1, bottom, borderColor); // left border
                gui.fill(right - 1, top, right, bottom, borderColor); // right border

                Font font = Minecraft.getInstance().font;
                if (this.scale < 1) {
                    font = MinescapeAddons.getInstance().getVanillaFont();
                }

                // Draw hp text centered on health bar
                String hpText = health + " / " + totalHealth + " HP";
                gui.drawCenteredString(font, hpText, (int)this.anchorX + this.widgetWidth / 2,
                        (int)this.anchorY + this.widgetHeight - barPadding - barHeight - mc.font.lineHeight,
                        0xFFFFFFFF);

                // Draw name above health bar, centered
                gui.drawCenteredString(font, name, (int)this.anchorX + this.widgetWidth / 2,
                        (int)this.anchorY + barPadding,
                        0xFFFFFFFF);
            }

        }
        gui.pose().popPose();
    }
}