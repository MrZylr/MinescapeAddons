package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.util.Timer;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class XpTrackerWidget extends Widget {

    public Skill skill;
    private static final File FILE = PersistenceFile.XPTRACKERFILE;
    private int fontColor;
    private int barColor;
    private int barBackgroundColor;

    public XpTrackerWidget() {
        super();
        this.type = WidgetType.XPTRACKER;
        this.setupConfig();

        // default colors
        backgroundColor = new Color(0, 0, 0, 100).getRGB();
        barColor = new Color(34, 175, 229, 183).getRGB();
        barBackgroundColor = new Color(0, 0, 0, 255).getRGB();
        fontColor = new Color(255,255, 255, 255).getRGB();

        try {
            anchorX = Integer.parseInt(config.getProperty("x"));
            anchorY = Integer.parseInt(config.getProperty("y"));
            skill = Main.skills.get(SkillType.valueOf(config.getProperty("skill")));
        }catch(Exception ex) {
            setupConfig();
            // TODO:: Setup to redo config in a method IWidget
            anchorX = Integer.parseInt(config.getProperty("x"));
            anchorY = Integer.parseInt(config.getProperty("y"));
            skill = Main.skills.get(SkillType.valueOf(config.getProperty("skill")));
        }
    }

    @Override
    public void render() {
        super.render();
        // Catherby Colors
//        backgroundColor = new Color(129, 1, 0, 76).getRGB();
//        barColor = new Color(180, 1, 0, 183).getRGB();
//        barBackgroundColor = new Color(0, 0, 0, 105).getRGB();
//        fontColor = new Color(255,255, 255, 255).getRGB();
        // Greens Colors
//        backgroundColor = new Color(0, 110, 14, 212).getRGB();
//        barColor = new Color(0, 110, 14, 212).getRGB();
//        barBackgroundColor = new Color(0, 0, 0, 57).getRGB();
//        fontColor = new Color(255, 242, 17, 255).getRGB();

        this.setBackgroundColor(new Color(85, 0, 67, 212).getRGB());
        barColor = new Color(85, 0, 67, 212).getRGB();
        barBackgroundColor = new Color(0, 0, 0, 57).getRGB();
        fontColor = new Color(255, 242, 17, 255).getRGB();


        // Calculate the exp/hour
        skill.getTracker().calcXp();
        // Setup for dynamic background size
        List<Integer> lines = new ArrayList<>();
        lines.add(mc.fontRenderer.getStringWidth(skill.getTracker().getSessionTime()));
        lines.add(mc.fontRenderer.getStringWidth(skill.getTracker().getTotalXp()));
        lines.add(mc.fontRenderer.getStringWidth(skill.getTracker().getExpPerHour()));

        this.widgetWidth = Collections.max(lines) + 11;
        this.widgetHeight = mc.fontRenderer.FONT_HEIGHT*4 + 7;

        drawTracker();
        if (skill.getType() != SkillType.TOTAL)
            drawXpBar();

        // Render last to keep children layers higher than parent
        this.renderChildren();
    }

    private void drawTracker() {
        // Draw the strings with FontRenderer with user color choice
        FontRenderer font = mc.fontRenderer;

        font.drawString(skill.getTracker().getSessionTime(), getLeftSide()+4, getTop()+3, fontColor);
        font.drawString(skill.getTracker().getTotalXp(), getLeftSide()+4, getTop() + (font.FONT_HEIGHT)  + 3, fontColor);
        font.drawString(skill.getTracker().getExpPerHour(), getLeftSide()+4, getTop() + (font.FONT_HEIGHT*2) + 3, fontColor);
    }

    private void drawXpBar() {
        // Do the math for the percentage through current level
        int xpForLevel = skill.getNextLevel() -skill.getExperienceAtLevel(skill.getLevel());
        int xpLeft = skill.getNextLevel() - skill.getExp();

        double percentLeft = (double)xpLeft / (double)xpForLevel;
        double percentDone = 1.0 - percentLeft;
        int percentDisplay = (int)(percentDone*100);
        String percent =  percentDisplay + "%";

        // Draw the bar
        double barWidth = widgetWidth*percentDone;
        GuiUtils.drawGradientRect(1, getLeftSide(), getBottom()-mc.fontRenderer.FONT_HEIGHT-2, getRightSide(), getBottom(),
                barBackgroundColor, barBackgroundColor);
        GuiUtils.drawGradientRect(2, getLeftSide(), getBottom()-mc.fontRenderer.FONT_HEIGHT-2, getLeftSide()+(int)barWidth, getBottom(),
                barColor, barColor);

        // Draw the percentage number
        FontRenderer font = mc.fontRenderer;
        int xPos = anchorX - font.getStringWidth(percent)/2;
        font.drawString(percent, xPos, getBottom()-9, fontColor);
    }
}
