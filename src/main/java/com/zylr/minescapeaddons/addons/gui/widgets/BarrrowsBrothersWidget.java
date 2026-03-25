package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

public class BarrrowsBrothersWidget extends Widget {
    private static final Logger LOGGER = MinescapeAddons.LOGGER;

    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#.##");
    private static final int MAX_BARROWS_POINTS = 1012;
    private static final Map<String, Integer> BROTHER_POINTS = Map.of(
            "Ahrim the Blighted", 100,
            "Dharok the Wretched", 117,
            "Guthan the Infested", 117,
            "Karil the Tainted", 100,
            "Torag the Corrupted", 117,
            "Verac the Defiled", 117
    );
    private static final Map<String, Integer> CRYPT_UNIT_POINTS = Map.of(
            "Bloodworm", 52,
            "Crypt Rat", 43,
            "Giant Crypt Rat", 76,
            "Crypt Spider", 56,
            "Giant Crypt Spider", 79,
            "Skeleton", 77
    );

    private final boolean[] brothersDefeated = new boolean[6];
    private int barrowsPoints;

    private boolean hasFinished;

    public BarrrowsBrothersWidget(double x, double y) {
        super();
        this.type = WidgetType.BARROWS_BROTHERS_WIDGET;
        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;
        this.setupConfig();
        this.setVisible(false);

        this.widgetWidth = 75;
        this.widgetHeight = 85;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0.2")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));

        this.setValuesFromConfig();
    }

    @Override
    public void render(GuiGraphics guiGraphics) {
        if (Config.getFixedMode())
            this.renderFixed(guiGraphics);
        else
            this.renderResizable(guiGraphics);
    }

    public void renderFixed(GuiGraphics guiGraphics) {
        super.render(guiGraphics);

        this.anchorX = mc.getWindow().getGuiScaledWidth() - ViewportAdjustmentListener.PANEL_WIDTH - this.widgetWidth;
        this.anchorY = 0;
        this.fixAnchors();

        if (!Config.getBarrowsHelper() && !Widget.isHudEditOpen) return;

        BlockPos pos = mc.player.blockPosition();
        if (!Widget.isHudEditOpen && !Util.withinRegion(1710, 1580, 50, -80, pos.getX(), pos.getZ())) {
            this.setVisible(false);
            return;
        }
        //System.out.println(hasFinished);
        if (this.hasFinished && pos.getY() > 30) {
            this.hasFinished = false;
            config.setProperty("hasFinished", "false");
            this.saveConfig();
        }

        this.setVisible(true);
        this.drawBackground(guiGraphics);

        renderBrotherStatus(guiGraphics);
        renderPotential(guiGraphics);
    }

    public void renderResizable(GuiGraphics guiGraphics) {
        super.render(guiGraphics);

        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.fixAnchors();

        guiGraphics.pose().pushPose();
        this.scaleAroundAnchor(guiGraphics, this.scale);

        if (!Config.getBarrowsHelper() && !Widget.isHudEditOpen) return;

        BlockPos pos = mc.player.blockPosition();
        if (!Widget.isHudEditOpen && !Util.withinRegion(1710, 1580, 50, -80, pos.getX(), pos.getZ())) {
            this.setVisible(false);
            return;
        }
        //System.out.println(hasFinished);
        if (this.hasFinished && pos.getY() > 30) {
            this.hasFinished = false;
            config.setProperty("hasFinished", "false");
            this.saveConfig();
        }

        this.setVisible(true);
        this.drawBackground(guiGraphics);

        renderBrotherStatus(guiGraphics);
        renderPotential(guiGraphics);

        guiGraphics.pose().popPose();
    }

    public void entityDied(Entity e) {
        System.out.println("Entity died: " + e.getDisplayName().getString());
        this.handleEntityTracking(e);
    }

    private void handleEntityTracking(Entity e) {
        System.out.println("Handling entity death for: " + e.getDisplayName().getString());
        String name = e.getDisplayName().getString();

        if (!this.hasFinished) {
            handleBrotherDeath(name);
            handleCryptUnitDeath(name);
        }
    }

    private void handleBrotherDeath(String name) {
        String[] brotherNames = {
                "Ahrim the Blighted",
                "Dharok the Wretched",
                "Guthan the Infested",
                "Karil the Tainted",
                "Torag the Corrupted",
                "Verac the Defiled"
        };

        for (int i = 0; i < brotherNames.length; i++) {
            if (name.contains(brotherNames[i])) {
                Integer points = BROTHER_POINTS.get(brotherNames[i]);
                if (points != null) {
                    System.out.println("Defeated brother: " + brotherNames[i] + ", awarding points: " + points);
                    markBrotherDefeated(i, points);
                }
                break;
            }
        }
    }

    private void handleCryptUnitDeath(String name) {
        System.out.println("name: " + name);
        for (Map.Entry<String, Integer> entry : CRYPT_UNIT_POINTS.entrySet()) {
            if (name.contains(entry.getKey())) {
                System.out.println("Defeated crypt unit: " + entry.getKey() + ", awarding points: " + entry.getValue());
                addPoints(entry.getValue());
                break;
            }
        }
    }

    private void markBrotherDefeated(int index, int points) {
        brothersDefeated[index] = true;
        addPoints(points);
        config.setProperty(getBrotherConfigKey(index), "true");
        this.saveConfig();
    }

    private void addPoints(int points) {
        this.barrowsPoints += points;
        config.setProperty("barrowsPoints", String.valueOf(this.barrowsPoints));
        this.saveConfig();
    }

    private void renderBrotherStatus(GuiGraphics guiGraphics) {
        String[] names = {"Ahrim:", "Dharok:", "Guthan:", "Karil:", "Torag:", "Verac:"};
        for (int i = 0; i < names.length; i++) {
            int y = this.anchorY + 3 + (i * 12);
            Font font = Minecraft.getInstance().font;
            if (this.scale < 1) {
                font = MinescapeAddons.getInstance().getVanillaFont();
            }
            guiGraphics.drawString(font, names[i], this.anchorX + 3, y, Color.WHITE.getRGB(), false);
            String symbol = brothersDefeated[i] ? "✔" : "✘";
            int color = brothersDefeated[i] ? Color.GREEN.getRGB() : Color.RED.getRGB();
            guiGraphics.drawString(font, symbol, this.getRightSide() - 13, y, color, false);
        }
    }

    private void renderPotential(GuiGraphics guiGraphics) {
        double potential = Math.min(100, Math.max(0, this.barrowsPoints / (double) MAX_BARROWS_POINTS * 100.0));
        String potentialText = PERCENTAGE_FORMAT.format(potential) + "%";
        int y = this.anchorY + 73;
        Font font = Minecraft.getInstance().font;
        if (this.scale < 1) {
            font = MinescapeAddons.getInstance().getVanillaFont();
        }
        guiGraphics.drawString(font, "Potential:", this.anchorX + 3, y, Color.WHITE.getRGB(), false);
        guiGraphics.drawString(font, potentialText,
                this.getRightSide() - 13 - mc.font.width(PERCENTAGE_FORMAT.format(potential)),
                y, Color.WHITE.getRGB(), false);
    }

    public void resetValues() {
        for (int i = 0; i < brothersDefeated.length; i++) {
            brothersDefeated[i] = false;
            config.setProperty(getBrotherConfigKey(i), "false");
        }
        this.barrowsPoints = 0;
        config.setProperty("barrowsPoints", "0");
        this.saveConfig();
    }

    private void setValuesFromConfig() {
        for (int i = 0; i < brothersDefeated.length; i++) {
            brothersDefeated[i] = Boolean.parseBoolean(config.getProperty(getBrotherConfigKey(i), "false"));
        }
        this.barrowsPoints = Integer.parseInt(config.getProperty("barrowsPoints", "0"));
        this.hasFinished = Boolean.parseBoolean(config.getProperty("hasFinished", "false"));
    }

    private String getBrotherConfigKey(int index) {
        String[] keys = {"hasAhrimDefeated", "hasDharokDefeated", "hasGuthanDefeated",
                "hasKarilDefeated", "hasToragDefeated", "hasVeracDefeated"};
        return keys[index];
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
        config.setProperty("hasFinished", String.valueOf(hasFinished));
        this.saveConfig();
    }
}

