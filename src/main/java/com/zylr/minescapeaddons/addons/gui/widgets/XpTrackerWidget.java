package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.skills.tracker.XpTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

import java.awt.*;

public class XpTrackerWidget extends Widget {
    public Skill skill;

    // OSRS-inspired palette: dark brown background, tan panels, gold accents, cream text
    private static final int BG_COLOR         = new Color(46, 36, 26, 240).getRGB();    // dark brown
    private static final int PANEL_COLOR      = new Color(79, 62, 45, 200).getRGB();    // mid brown/tan
    private static final int ACCENT_COLOR     = new Color(212, 175, 55, 255).getRGB();   // gold accent
    private static final int HEADER_COLOR     = new Color(60, 50, 38, 220).getRGB();     // darker header
    private static final int TEXT_PRIMARY     = 0xF5E6C8;                                // cream
    private static final int TEXT_SECONDARY   = 0xBBA88A;                                // muted tan
    private static final int TEXT_VALUE       = 0xFFD66B;                                // bright gold

    private static final int WIDGET_W = 150;
    private static final int WIDGET_H = 80;
    private static final int ACCENT_BAR_W = 3;
    private static final int HEADER_H = 20;
    private static final int ROW_H = 14;
    private static final int ROW_PADDING = 3;

    public XpTrackerWidget(int x, int y) {
        super();
        this.type = WidgetType.XPTRACKER;

        this.getAnchorXFromRelative(x);
        this.getAnchorYFromRelative(y);
        this.isParent = true;
        this.setupConfig();
        this.skill = MinescapeAddons.skills.get(SkillType.TOTAL);

        this.widgetWidth = WIDGET_W;
        this.widgetHeight = WIDGET_H;

        this.getAnchorXFromRelative(Double.parseDouble(config.getProperty("x", "0")));
        this.getAnchorYFromRelative(Double.parseDouble(config.getProperty("y", "0.5")));
        this.backgroundColor = Integer.parseInt(config.getProperty("backgroundColor", "1258291200"));
        this.scale = Float.parseFloat(config.getProperty("scale", "1"));
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        super.render(gui);


        this.anchorX = 348;//
        this.anchorY = mc.getWindow().getGuiScaledHeight() - 142;
        this.widgetWidth = 222;
        this.widgetHeight = 142;
        this.fixAnchors();

        if (Config.getAfkTimer()) {
            if (MinescapeAddons.getInstance().player.isAfk()) {
                XpTracker.pauseAllTrackers();
                String afkTime = "Session Time Paused. Time AFK: " + MinescapeAddons.getInstance().player.getAfkTimer().toString();
                int x = mc.getWindow().getGuiScaledWidth() / 2;
                int y = mc.getWindow().getGuiScaledHeight() / 2 - mc.font.lineHeight / 2;
                int width = mc.font.width(afkTime) + 10;
                int height = mc.font.lineHeight + 6;
                gui.fill(x - width / 2, y - 3, x + width / 2, y + height - 3, new Color(0, 0, 0, 150).getRGB());

                gui.drawCenteredString(mc.font, afkTime, x, y, TEXT_VALUE);
            }
        }
        if (!MinescapeAddons.getInstance().player.isAfk()) {
            XpTracker.unpauseAllTrackers();
        }

        skill.getTracker().calcXp();

        int x = this.anchorX;
        int y = this.anchorY;
        int w = this.widgetWidth;
        int h = this.widgetHeight;
        Font font = Minecraft.getInstance().font;

        // Outer background
        gui.fill(x, y, x + w, y + h, BG_COLOR);

        // Left accent bar
        gui.fill(x, y, x + ACCENT_BAR_W, y + h, ACCENT_COLOR);

        // Header bar
        gui.fill(x + ACCENT_BAR_W, y, x + w, y + HEADER_H, HEADER_COLOR);

        // Header label — skill name
        String skillName = skill.getType() != null ? skill.getType().name() : "XP Tracker";
        skillName = capitalise(skillName);
        gui.drawString(font, skillName, x + ACCENT_BAR_W + 6, y + (HEADER_H - font.lineHeight) / 2, TEXT_PRIMARY);

        // Divider line under header
        gui.fill(x + ACCENT_BAR_W, y + HEADER_H, x + w, y + HEADER_H + 1, ACCENT_COLOR);

        // Stat rows
        int rowY = y + HEADER_H + ROW_PADDING + 3;
        drawStatRow(gui, font, x, rowY,        w, "XP/hr",    skill.getTracker().getExpPerHour());
        drawStatRow(gui, font, x, rowY + ROW_H + ROW_PADDING, w, "Total XP", skill.getTracker().getTotalXp());
        drawStatRow(gui, font, x, rowY + (ROW_H + ROW_PADDING) * 2, w, "Session",  skill.getTracker().getSessionTime());
    }

    public void renderResizable(GuiGraphics gui) {
        super.render(gui);
        if (!Config.getXpTracker() && !Widget.isHudEditOpen) return;

        float scale = this.scale;

        this.getAnchorXFromRelative(this.relativeAnchorx);
        this.getAnchorYFromRelative(this.relativeAnchorY);
        this.widgetWidth = WIDGET_W;
        this.widgetHeight = WIDGET_H;
        this.fixAnchors();

        gui.pose().pushPose();
        this.scaleAroundAnchor(gui, scale);

        if (Config.getAfkTimer()) {
            if (MinescapeAddons.getInstance().player.isAfk()) {
                XpTracker.pauseAllTrackers();
                String afkTime = "Session Time Paused. Time AFK: " + MinescapeAddons.getInstance().player.getAfkTimer().toString();
                int x = mc.getWindow().getGuiScaledWidth() / 2;
                int y = mc.getWindow().getGuiScaledHeight() / 2 - mc.font.lineHeight / 2;
                int width = mc.font.width(afkTime) + 10;
                int height = mc.font.lineHeight + 6;
                gui.fill(x - width / 2, y - 3, x + width / 2, y + height - 3, new Color(0, 0, 0, 150).getRGB());
                gui.drawCenteredString(mc.font, afkTime, x, y, TEXT_VALUE);
            }
        }
        if (!MinescapeAddons.getInstance().player.isAfk()) {
            XpTracker.unpauseAllTrackers();
        }

        skill.getTracker().calcXp();

        int x = this.anchorX;
        int y = this.anchorY;
        int w = WIDGET_W;
        int h = WIDGET_H;
        Font font = Minecraft.getInstance().font;
        if (this.scale < 1) {
            font = MinescapeAddons.getInstance().getVanillaFont();
        }

        // Outer background
        gui.fill(x, y, x + w, y + h, BG_COLOR);

        // Left accent bar
        gui.fill(x, y, x + ACCENT_BAR_W, y + h, ACCENT_COLOR);

        // Header bar
        gui.fill(x + ACCENT_BAR_W, y, x + w, y + HEADER_H, HEADER_COLOR);

        // Header label — skill name
        String skillName = skill.getType() != null ? skill.getType().name() : "XP Tracker";
        skillName = capitalise(skillName);
        gui.drawString(font, skillName, x + ACCENT_BAR_W + 6, y + (HEADER_H - font.lineHeight) / 2, TEXT_PRIMARY);

        // Divider line under header
        gui.fill(x + ACCENT_BAR_W, y + HEADER_H, x + w, y + HEADER_H + 1, ACCENT_COLOR);

        // Stat rows
        int rowY = y + HEADER_H + ROW_PADDING + 3;
        drawStatRow(gui, font, x, rowY,        w, "XP/hr",    skill.getTracker().getExpPerHour());
        drawStatRow(gui, font, x, rowY + ROW_H + ROW_PADDING, w, "Total XP", skill.getTracker().getTotalXp());
        drawStatRow(gui, font, x, rowY + (ROW_H + ROW_PADDING) * 2, w, "Session",  skill.getTracker().getSessionTime());

        gui.pose().popPose();
    }

    private void drawStatRow(GuiGraphics gui, Font font, int x, int y, int w, String label, String value) {
        // Row background
        gui.fill(x + ACCENT_BAR_W, y, x + w, y + ROW_H, PANEL_COLOR);

        if (scale < 1.0f) {
            font = MinescapeAddons.getInstance().getVanillaFont();
        }
        // Label on the left
        gui.drawString(font, label, x + ACCENT_BAR_W + 6, y + (ROW_H - font.lineHeight) / 2, TEXT_SECONDARY);

        // Value right-aligned
        int valueX = x + w - font.width(value) - 6;
        gui.drawString(font, value, valueX, y + (ROW_H - font.lineHeight) / 2, TEXT_VALUE);
    }

    private String capitalise(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    public void setActiveSkill(Skill skill) {
        this.skill = skill;
    }
}