package com.zylr.minescapeaddons.addons.gui.builders;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class XpTrackerBuilder {

    // Variables to build the gui
    private int height;
    private int width;
    private int trout;
    private int salmon;
    private float scale;
    private boolean small;
    private Skill skill;

    public final ResourceLocation BLANK_INVENTORY_SCREEN = new ResourceLocation(Main.ID, "textures/gui/blank_inventory.png");

    public XpTrackerBuilder() {
        width = 0;
        height = 0;
        scale = 0;
        trout = 0;
        salmon = 0;
        small = false;
        skill = Main.getInstance().skills.get(SkillType.TOTAL);
    }

    public void buildXp(int originX, int originY, boolean right) {
        // TODO:: Scale with gui
        if (!ModConfiguration.CLIENT.exp.get())
            return;
        // Get gui scale
        float sizeFix = 1F;
        float scale = 2/ (float) Minecraft.getInstance().getMainWindow().getGuiScaleFactor();
        float posScale = (float)Minecraft.getInstance().getMainWindow().getGuiScaleFactor() / 2;
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        FontRenderer font = Minecraft.getInstance().fontRenderer;
        Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation(Main.ID, "textures/gui/background.png"));
        int textHeight = (int)(height / 1.75F) - font.FONT_HEIGHT - 2;

        skill.getTracker().calcXp();

        // Setup for dynamic background size
        List<Integer> lines = new ArrayList<>();
        lines.add(font.getStringWidth(skill.getTracker().getSessionTime()));
        lines.add(font.getStringWidth(skill.getTracker().getTotalXp()));
        lines.add(font.getStringWidth(skill.getTracker().getExpPerHour()));

        // Draw background
        if(right)
            BuilderUtils.drawScaleRect(originX + Collections.max(lines) + 11, originY, Collections.max(lines) + 11, (font.FONT_HEIGHT * 3) + 7,
                    0x70000000, 0x70000000, false);
        else
            BuilderUtils.drawScaleRect(originX, originY, Collections.max(lines) + 11, (font.FONT_HEIGHT * 3) + 7,
                    0x50000000, 0x50000000, false);

        // Scale the gui
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        // Draw tracker background
        if (right) {
            // Draw font for tracker
            float x = (width*posScale) - Collections.max(lines) - 9 - originX;
            font.drawString(skill.getTracker().getSessionTime(), x, (height*posScale) - originY  + 3, 16777215);
            font.drawString(skill.getTracker().getTotalXp(), x,(height*posScale) - originY + (font.FONT_HEIGHT)  + 3, 16777215);
            font.drawString(skill.getTracker().getExpPerHour(), x,(height*posScale) - originY + (font.FONT_HEIGHT*2)  + 3, 16777215);
        } else {
            // Draw font for tracker
            font.drawString(skill.getTracker().getSessionTime(), (width*posScale)  + 2 - originX, (height*posScale) - originY  + 3, 16777215);
            font.drawString(skill.getTracker().getTotalXp(), (width*posScale) + 2 - originX ,(height*posScale) - originY + (font.FONT_HEIGHT)  + 3, 16777215);
            font.drawString(skill.getTracker().getExpPerHour(), (width*posScale) + 2 - originX ,(height*posScale) - originY + (font.FONT_HEIGHT*2)  + 3, 16777215);
        }

        // Build xp til next level bar[25/7/2020 22:22:40 PM] [22:22:40] [Render thread/INFO]: [com.zylr.minescapeaddons.addons.gui.builders.XpTrackerBuilder:buildXp:100]: 650.0, 100.0, 100.0, 550.0, 0.18181818181818182, 0.8181818181818181
        if (skill.getType() != SkillType.TOTAL && skill.getLevel() != 0) {
            double xpNextLevel = (skill.getExperienceAtLevel(skill.getLevel()+1));
            double xpThisLevel = skill.getExperienceAtLevel(skill.getLevel());
            double xpRemaining = xpNextLevel - skill.getExp();
            double xpTilLevel = (xpNextLevel - xpThisLevel);
            double percentLeft = xpRemaining / xpTilLevel;
            double percentDone = 1.0 - percentLeft;
            if (percentDone > 1.0)
                percentDone = 1.0;

            // Building the bar
            if (right) {
                BuilderUtils.scaledRect(originX + Collections.max(lines) + 11, originY - (font.FONT_HEIGHT * 3) - 6,
                        Collections.max(lines) + 11, (font.FONT_HEIGHT), BLANK_INVENTORY_SCREEN, false);
                BuilderUtils.drawScaleRect(originX + Collections.max(lines) + 8, originY - (font.FONT_HEIGHT * 3) - 6,
                        (int) ((Collections.max(lines) + 4) * percentDone), (font.FONT_HEIGHT), 0x9922AFE5, 0x9922AFE5, false);
                font.drawString((int)(percentDone*100)+"%", (width*posScale) - Collections.max(lines)/2 - 9 - originX,
                        (height*posScale) - originY + (font.FONT_HEIGHT * 3) + 7, 16777215);
            } else {
                BuilderUtils.scaledRect(originX , originY - (font.FONT_HEIGHT * 3) - 6,
                        Collections.max(lines) + 11, (font.FONT_HEIGHT), BLANK_INVENTORY_SCREEN, false);
                BuilderUtils.drawScaleRect(originX -3, originY - (font.FONT_HEIGHT * 3) - 6,
                        (int) ((Collections.max(lines) + 4) * percentDone), (font.FONT_HEIGHT), 0x9922AFE5, 0x9922AFE5, false);
                font.drawString((int)(percentDone*100)+"%", (width*posScale)  + 2 + (Collections.max(lines))/2 - originX,
                        (height*posScale) - originY + (font.FONT_HEIGHT * 3) + 7, 16777215);
            }
        }
        RenderSystem.popMatrix();

        // Draw font for tracker
        //font.drawString("Trout: " + trout, width / width ,textHeight + (font.FONT_HEIGHT*3), 16777215);
        //font.drawString("Salmon: " + salmon, width / width ,textHeight + (font.FONT_HEIGHT*4), 16777215);
    }

    public void addTrout() { this.trout += 1; }
    public void addSalmon() { this.salmon += 1; }

    public void resetAllTrackers() {
        for (SkillType skillType : SkillType.values()) {
            Skill skill = Main.getInstance().skills.get(skillType);
            skill.getTracker().resetTracker();
        }
    }

    public void startAllTrackers() {
        for (SkillType skillType : SkillType.values()) {
            Skill skill = Main.getInstance().skills.get(skillType);
            skill.getTracker().startTracker();
        }
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Skill getSkill() { return skill; }

    public void pauseAllTimers() {
        Map<SkillType, Skill> skills = Main.getInstance().skills;
        for (SkillType skillType:SkillType.values()) {
            Skill skill = skills.get(skillType);
            if (!skill.getTracker().getTimer().isPaused() && skill.getTracker().getTimer().hasStarted())
                skill.getTracker().getTimer().pause();
        }
    }
    public void unpauseAllTimers() {
        Map<SkillType, Skill> skills = Main.getInstance().skills;
        for (SkillType skillType:SkillType.values()) {
            Skill skill = skills.get(skillType);
            if (skill.getTracker().getTimer().isPaused() && skill.getTracker().getTimer().hasStarted())
                skill.getTracker().getTimer().unpause();
        }
    }
}
