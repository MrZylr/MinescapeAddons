package com.zylr.minescapeaddons.addons.skills;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.utils.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class XpOrb {

    public final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID, "textures/gui/xp_orb_background.png");
    public static int orbDiameter = 34;
    private Timer lifeTime;
    private Skill skill;
    private int lifeSpan;
    private double xpAmount;
    private Minecraft mc = Minecraft.getInstance();
    private int start;
    private int bottom;
    private int increment = 0;

    public XpOrb(Skill skill, double xpAmount) {
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

    public void loadXpDrop(String xpDropString, int xPos, int yPos, GuiGraphics gui) {
        Font font = mc.font;
        int leftMargin = font.width(xpDropString)/2;
        gui.drawString(font, xpDropString, xPos-leftMargin, yPos, -1);
    }

    public void loadXpDrop(int xPos, int yPos, float radius, GuiGraphics gui) {
        // Get percentage complete to next level
        xPos -= radius;
        this.start = yPos+(int)radius*2;
        this.bottom = yPos+(int)radius*4;
        DecimalFormat format = new DecimalFormat("#,###");
        int textX = (int)(xPos + radius);
        gui.drawCenteredString(Minecraft.getInstance().font, format.format(this.xpAmount), textX,
                this.start+this.increment, Color.WHITE.getRGB());
        if (this.start+this.increment<this.bottom)
            this.increment++;



       //GL11.glColor4f(1f,1f, 1f, 1f);
       // BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth() - xPos+((orbDiameter-4)/2),
        //        mc.getMainWindow().getScaledHeight() - yPos+((orbDiameter-4)/2), orbDiameter-4, orbDiameter-4, BACKGROUND, false);
       // RenderSystem.disableTexture();
        RenderSystem.disableBlend();

        //GL11.glColor4f(1f,1f, 1f, 1f);
        //drawXpRing(xPos, yPos);

        // Do the math for the percentage through current level
        double xpForLevel = skill.getNextLevel() - skill.getExperienceAtLevel(skill.getLevel());
        double xpLeft = skill.getNextLevel() - skill.getExp();

        double percentLeft = (double)xpLeft / (double)xpForLevel;
        if (percentLeft < 0)
            percentLeft = 0;
        double percentDone = 1.0 - percentLeft;
        float arcAngle = (float) (360*percentDone);

        int width = (int)radius*2;
        drawPartialRing( gui.pose(),
         xPos+radius, yPos+radius, radius-5, radius,
        -90, arcAngle-90, 128,
        255 - skill.r,255 - skill.g,255 - skill.b, 1);
        gui.blit(BACKGROUND, xPos+1, yPos+1, 0, 0, width-2,
                width-2, width-2, width-2);
        gui.pose().pushPose();
        gui.pose().translate(0, 0, 2001);
        gui.blit(BACKGROUND, xPos+2, yPos+2, 0, 0, width-4,
                width-4, width-4, width-4);
        gui.blit(ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
                "textures/gui/skillicons/" + skill.getType().name().toLowerCase() + ".png"),
                xPos+6, yPos+6, 0, 0, width-12, width-12, width-12, width-12);
        gui.pose().popPose();


        //GL11.glColor4f(1f,1f, 1f, 1f);
       // BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth() - xPos + 7,
        //        mc.getMainWindow().getScaledHeight() - yPos + 7, 14, 14,
        //        new ResourceLocation(MinescapeAddons.MOD_ID, "textures/gui/skillicons/" +
             //           skill.getType().name().toLowerCase() + ".png"), false);

    }

    /**
     * Draws a partial ring (arc) in screen space.
     *
     * @param cx         Center x (pixels)
     * @param cy         Center y (pixels)
     * @param inner      Inner radius (pixels)
     * @param outer      Outer radius (pixels)
     * @param startAngle Start angle (degrees)
     * @param endAngle   End angle (degrees)
     * @param segments   Number of segments (smoother = more)
     * @param r          Red [0,1]
     * @param g          Green [0,1]
     * @param b          Blue [0,1]
     * @param a          Alpha [0,1]
     */
    public static void drawPartialRing(
            PoseStack pose, double cx, double cy, double inner, double outer,
            double startAngle, double endAngle, int segments,
            float r, float g, float b, float a
    ) {
        pose.pushPose();
        pose.translate(0, 0, 100);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        Tesselator tess = Tesselator.getInstance();
        BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);

        double angleStep = (endAngle - startAngle) / segments;

        for (int i = 0; i <= segments; i++) {
            double angle = Math.toRadians(startAngle + i * angleStep);
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);

            float xOuter = (float) (cx + cos * outer);
            float yOuter = (float) (cy + sin * outer);
            float xInner = (float) (cx + cos * inner);
            float yInner = (float) (cy + sin * inner);

            buffer.addVertex(xOuter, yOuter, 2000).setColor(r, g, b, a);
            buffer.addVertex(xInner, yInner, 2000).setColor(r, g, b, a);
        }
        BufferUploader.drawWithShader(buffer.buildOrThrow());


        tess.clear();
        RenderSystem.disableBlend();
        pose.popPose();
    }

    private void drawXpRing(int xPos, int yPos) {
        // Do the math for the percentage through current level
        double xpForLevel = skill.getNextLevel() -skill.getExperienceAtLevel(skill.getLevel());
        double xpLeft = skill.getNextLevel() - skill.getExp();

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
            //else
                //GL11.glColor4f(skill.getType().getRed(), skill.getType().getGreen(), skill.getType().getBlue(), 1F);

            // Complicated math to make circle boii
            GL11.glVertex2f((float)Math.cos(d) * r + (float)x, (float)Math.sin(d) * r + (float)y);
        }
        GL11.glEnd();

    }

    public Skill getSkill() { return this.skill; }

    public Timer getTimer() { return this.lifeTime; }

    public double getXpAmount() { return this.xpAmount; }

    public void setXpAmount (int xpAmount) { this.xpAmount = xpAmount; }

    public static void handleXpGained(String xpMessage) {
        String message = xpMessage;
        int totalExp = 0;
        int skillExp = 0;
        Map<SkillType, Skill> skills = MinescapeAddons.getInstance().skills;
        List<String> skillsList = Arrays.asList(MinescapeAddons.getInstance().skillsList);
        List<String> skillsSymbolList = Arrays.asList(MinescapeAddons.getInstance().skillsSymbolList);

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
                        /*if (skill.getType() == SkillType.FARMING) {
                            if (Main.getInstance().clickOnFarmingPatch != null)
                                Main.getInstance().clickOnFarmingPatch.setSeed();
                        }*/
                        if (xp > 0) {
                            skill.addExp(xp);
                           /* for (IWidget widget : MinescapeAddons.getInstance().resizableClassic.widgets) {
                                if (widget instanceof XpDropWidget) {
                                    ((XpDropWidget) widget).addXpDrop(new XpOrb(skill, xp));
                                }
                            }
                            */
                        }
                    }
                }
                totalExp += xp;
            }
        }

        // Start the counter if xp gained
        if (totalExp > 0) {
           // MinescapeAddons.getInstance().getIdleChecker().getXpTimer().startTimer();
            skills.get(SkillType.TOTAL).addExp(totalExp);
        }
    }
}
