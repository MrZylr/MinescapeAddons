package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.huds.resizableclassic.ResizableClassic;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkillsWidget extends Widget {
    private final Minecraft mc = Minecraft.getInstance();
    // Pre-computed — avoids Color.YELLOW.getRGB() being called ~48× per frame
    private static final int COLOR_YELLOW = Color.YELLOW.getRGB();
    public final ResourceLocation SKILLSTAB = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/stats.png");
    public final ResourceLocation FIXED_SKILLSTAB = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/classic_skills.png");

    // Cached ordered skill list — avoids SkillType.valueOf + map lookup on every render frame
    private List<Skill> orderedSkills = null;

    private List<Skill> getOrderedSkills() {
        if (orderedSkills == null) {
            orderedSkills = new ArrayList<Skill>(MinescapeAddons.skillsList.length);
            for (String s : MinescapeAddons.skillsList) {
                orderedSkills.add(MinescapeAddons.skills.get(SkillType.valueOf(s.toUpperCase())));
            }
        }
        return orderedSkills;
    }

    public SkillsWidget(int x, int y) {
        this.anchorX = x;
        this.anchorY = y;

        this.widgetWidth = ResizableClassic.widgetWidth;
        this. widgetHeight = ResizableClassic.widgetHeight;
        this.isParent = true;
    }
    public SkillsWidget(boolean visible) {
        this.visible = visible;
        this.isParent = false;
    }

    @Override
    public void render(GuiGraphics gui) {
        if(this.visible) {
            if (Config.getFixedMode())
                this.renderFixed(gui);
            else
                this.renderResizable(gui);
        }
    }

    public void renderFixed(GuiGraphics gui) {
        super.render(gui);

        this.anchorX = this.parent.getAnchorX();
        this.anchorY = this.parent.getAnchorY();
        this.widgetWidth = this.parent.getWidgetWidth();
        this.widgetHeight = this.parent.getWidgetHeight();

        this.drawImage(FIXED_SKILLSTAB, gui);
        this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);
        // Draw Stats
        this.drawLevelsFixed(gui, 0, 0, true, true);
        this.drawLevelsFixed(gui, 13, 12, false, false);
    }

    public void renderResizable(GuiGraphics gui) {

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
        this.drawImage(SKILLSTAB, gui);
        this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);
        // Draw Stats
        if (!Config.getSmallInventory()) {
            this.drawLevelsLarge(gui, 0, 0, true, true);
            this.drawLevelsLarge(gui, 8, 10, false, false);
        } else {
            this.drawLevelsSmall(gui, 0, 0, true, true);
            this.drawLevelsSmall(gui, 8, 7, false, false);
        }

        gui.pose().popPose();
    }

    private void drawLevelsFixed(GuiGraphics gui, int xOffset, int yOffset, boolean drawTotalLevel, boolean modifiedLevel) {
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        Font font = Minecraft.getInstance().font;
        if (Config.getFixedMode() && Config.getSmallInventory())
            font = MinescapeAddons.getInstance().getVanillaFont();
        for (Skill skill : getOrderedSkills()) {
            if(i1 == 2)
                i=i-1;
            if(i1==3)
                i=i-2;
            if (skill.getType() == SkillType.TOTAL && drawTotalLevel) {
                gui.drawCenteredString(font, "Total Level", this.getLeftSide() + 64 + i,
                        this.getTop() + 45 + i2, COLOR_YELLOW);
                gui.drawCenteredString(font, String.valueOf(skill.getLevel()), this.getLeftSide() + 64 + i,
                        this.getTop() + font.lineHeight + 47 + i2, COLOR_YELLOW);

            }else if(skill.getType() != SkillType.TOTAL) {
                int level = skill.getLevel();
                if (modifiedLevel) {
                    level = skill.getModifiedLevel();
                }

                gui.drawCenteredString(font, String.valueOf(level), this.getLeftSide() + 72 + i + xOffset,
                        this.getTop() + 46 + i2 + yOffset, COLOR_YELLOW);
                i = i + 63;
                i1++;
                if (i1 % 3 == 0) {
                    i = 0;
                    i1 = 0;
                    i2 = i2 + 33 + i3;
                    if (i3 % 3 == 0)
                        ++i3;
                }
            }

        }
    }

    public void drawLevelsLarge(GuiGraphics gui, int xOffset, int yOffset, boolean drawTotalLevel, boolean modifiedLevel) {
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        for (Skill skill : getOrderedSkills()) {
            if(i1 == 2)
                i=i-1;
            if(i1==3)
                i=i-2;
            if (skill.getType() == SkillType.TOTAL && drawTotalLevel) {
                if (mc.screen instanceof ZylrInventoryScreen) {
                    gui.drawString(mc.font, "Total Level", (this.getLeftSide() + 5 + i + xOffset),
                            this.getTop() + 4 + i2 + yOffset, COLOR_YELLOW);
                    gui.drawString(mc.font, String.valueOf(skill.getLevel()), this.getLeftSide() + 18 + i + xOffset,
                            this.getTop() + mc.font.lineHeight + 4 + i2 + yOffset, COLOR_YELLOW);
                }else {
                    gui.drawString(mc.font, "Total Level", this.getLeftSide() + 5 + i,
                            this.getTop() + 4 + i2, COLOR_YELLOW);
                    gui.drawString(mc.font, String.valueOf(skill.getLevel()), this.getLeftSide() + 18 + i,
                            this.getTop() + mc.font.lineHeight + 4 + i2, COLOR_YELLOW);
                }
            }else if(skill.getType() != SkillType.TOTAL) {
                int level = skill.getLevel();
                if (modifiedLevel) {
                    level = skill.getModifiedLevel();
                }

                gui.drawString(mc.font, String.valueOf(level), this.getLeftSide() + 25 + i + xOffset,
                        this.getTop() + 7 + i2 + yOffset, COLOR_YELLOW);
                i = i + 42;
                i1++;
                if (i1 % 3 == 0) {
                    i = 0;
                    i1 = 0;
                    i2 = i2 + 22;
                }
            }

        }
    }

    public void drawLevelsSmall(GuiGraphics gui, int xOffset, int yOffset, boolean drawTotalLevel, boolean modifiedLevel) {
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        for (Skill skill : getOrderedSkills()) {
            if(i1 == 2)
                i=i-1;
            if(i1==3)
                i=i-2;
            if (skill.getType() == SkillType.TOTAL && drawTotalLevel) {
                if (mc.screen instanceof ZylrInventoryScreen) {
                    gui.drawString(mc.font, "Total", (this.getLeftSide() + 10 + i + xOffset),
                            this.getTop() + 4 + i2 + yOffset, COLOR_YELLOW);
                    gui.drawString(mc.font, String.valueOf(skill.getLevel()), this.getLeftSide() + 10 + i + xOffset,
                            this.getTop() + mc.font.lineHeight + 2 + i2 + yOffset, COLOR_YELLOW);
                }else {
                    gui.drawString(mc.font, "Total", this.getLeftSide() + 10 + i,
                            this.getTop() + 4 + i2, COLOR_YELLOW);
                    gui.drawString(mc.font, String.valueOf(skill.getLevel()), this.getLeftSide() + 10 + i,
                            this.getTop() + mc.font.lineHeight + 2 + i2, COLOR_YELLOW);
                }
            }else if(skill.getType() != SkillType.TOTAL) {
                int level = skill.getLevel();
                if (modifiedLevel) {
                    level = skill.getModifiedLevel();
                }

                gui.drawString(mc.font, String.valueOf(level), this.getLeftSide() + 17 + i + xOffset,
                        this.getTop() + 5 + i2 + yOffset, COLOR_YELLOW);
                i = i + 33;
                i1++;
                if (i1 % 3 == 0) {
                    i = 0;
                    i1 = 0;
                    i2 = i2 + 17;
                }
            }

        }
    }

    @Override
    public ResourceLocation getHighlight() { return HudMenuWidget.STONE_MIDDLE_SELECTED; }
}
