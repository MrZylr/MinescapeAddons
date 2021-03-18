package com.zylr.minescapeaddons.addons.skills.tracker;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.XpDropWidget;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class XpOrb {

    public final ResourceLocation BACKGROUND = new ResourceLocation(Main.ID, "textures/gui/xp_orb_background.png");
    public static int orbDiameter = 34;
    private Timer lifeTime;
    private Skill skill;
    private int lifeSpan;
    private int xpAmount;
    private Minecraft mc = Minecraft.getInstance();

    public XpOrb(Skill skill, int xpAmount) {
        this.skill = skill;
        this.lifeSpan = 5;
        this.lifeTime = new Timer();
        this.lifeTime.startTimer();
        this.xpAmount = xpAmount;
    }

    public boolean isExpired() {
        if (lifeTime.getSeconds() <= lifeSpan)
            return false;
        return true;
    }

    public void loadXpDrop(String xpDropString, int xPos, int yPos) {
        FontRenderer font = mc.fontRenderer;
        int leftMargin = font.getStringWidth(xpDropString)/2;
        font.drawString(xpDropString, xPos-leftMargin, yPos, -1);
    }

    public void loadXpDrop(int xPos, int yPos) {
        // Get percentage complete to next level
        xPos += orbDiameter/2;

//        GL11.glColor4f(1f,1f, 1f, 1f);
        BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth() - xPos+((orbDiameter-4)/2),
                mc.getMainWindow().getScaledHeight() - yPos+((orbDiameter-4)/2), orbDiameter-4, orbDiameter-4, BACKGROUND, false);
        RenderSystem.disableTexture();

        GL11.glColor4f(1f,1f, 1f, 1f);
        drawXpRing(xPos, yPos);

        RenderSystem.enableTexture();
        GL11.glColor4f(1f,1f, 1f, 1f);
        BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth() - xPos + 7,
                mc.getMainWindow().getScaledHeight() - yPos + 7, 14, 14,
                new ResourceLocation(Main.ID, "textures/gui/skillicons/" +
                        skill.getType().name().toLowerCase() + ".png"), false);
    }

    private void drawXpRing(int xPos, int yPos) {
        // Do the math for the percentage through current level
        int xpForLevel = skill.getNextLevel() -skill.getExperienceAtLevel(skill.getLevel());
        int xpLeft = skill.getNextLevel() - skill.getExp();

        double percentLeft = (double)xpLeft / (double)xpForLevel;
        double percentDone = 1.0 - percentLeft;
        int percentDisplay = (int)(percentDone*100);


        float PI = 3.14152f;
        // This is how many time the circle is incremented
        // The higher the number pi is divided by means more increments and a smoother circle
        // The lower the number pi is divided by makes the circle more and more flat sided
        double inc = PI / 150;
        double max = 2 * PI;
        // This is the radius of the xpdrop circle
        float r = 12;
        // This is the position of the center of the xpdrop circle
        int x = xPos;
        int y = yPos;

        // Create the outer ring to track progress
        // Default color, doesn't really matter
        GL11.glColor4f(0.2F, 0.2F, 0.2F, 1F);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(6);
        GL11.glBegin(GL11.GL_LINE_LOOP);

        for(double d = 0; d < max; d += inc) {
            GL11.glColor4f(0.9F, 0.0F, 0.0F, 0.3F);

            // This will calculate the amount of increments left to complete the cirlce as a percentage
            double circlePercentageLeft = d / max;
            int circlePercentage = (int)(circlePercentageLeft*100);

            // The circle starts being created at the 3 O'Clock position, so to start the progress bar at the top
            // the whole the needs to be incremented backwards by 25%
            circlePercentage += 25;
            if (circlePercentage >= 100)
                circlePercentage -= 100;

            // Change the color from black to red depending on how much circle is completed compared to the skill
            if (circlePercentage >= percentDisplay)
                GL11.glColor4f(0.2F, 0.2F, 0.2F, 1F);
            else
                GL11.glColor4f(skill.getType().getRed(), skill.getType().getGreen(), skill.getType().getBlue(), 1F);

            // Complicated math to make circle boii
            GL11.glVertex2f((float)Math.cos(d) * r + (float)x, (float)Math.sin(d) * r + (float)y);
        }
        GL11.glEnd();

    }

    public Skill getSkill() { return this.skill; }

    public Timer getTimer() { return this.lifeTime; }

    public int getXpAmount() { return this.xpAmount; }

    public void setXpAmount (int xpAmount) { this.xpAmount = xpAmount; }

    public static void handleXpGained(String xpMessage) {
        String message = xpMessage;
        int totalExp = 0;
        int skillExp = 0;
        Map<SkillType, Skill> skills = Main.getInstance().skills;
        List<String> skillsList = Arrays.asList(Main.getInstance().skillsList);
        List<String> skillsSymbolList = Arrays.asList(Main.getInstance().skillsSymbolList);

        // Split the game info chat popup into each skill
        // So each skill xp gets its own string
        for (String s : message.split(",")) {
            if (s.contains("xp")) {
                int xp = Integer.parseInt(s.substring(s.indexOf("+") + 1, s.indexOf("x")));
                // Grab the skill symbol
                for (String s1 : skillsSymbolList) {
                    // Check to see if the string contain the skill symbol
                    if (s.contains(s1)) {
                        // Set the skill and begin adding the xp
                        Skill skill = skills.get(SkillType.valueOf(skillsList.get(skillsSymbolList.indexOf(s1)).toUpperCase()));
                        // Check to start farm timer
                        if (skill.getType() == SkillType.FARMING) {
                            if (Main.getInstance().clickOnFarmingPatch != null)
                                Main.getInstance().clickOnFarmingPatch.setSeed();
                        }
                        if (xp > 0) {
                            skill.addExp(xp);
                            for (IWidget widget : Main.getInstance().getRsHud().getWidgets()) {
                                if (widget instanceof XpDropWidget) {
                                    ((XpDropWidget) widget).addXpDrop(new XpOrb(skill, xp));
                                }
                            }
                        }
                    }
                }
                totalExp += xp;
            }
        }

        // Start the counter if xp gained
        if (totalExp > 0) {
            Main.getInstance().getIdleChecker().getXpTimer().startTimer();
            skills.get(SkillType.TOTAL).addExp(totalExp);
        }
    }
}
