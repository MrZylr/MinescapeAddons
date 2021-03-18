package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.widgets.screens.HudEditScreen;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import com.zylr.minescapeaddons.addons.skills.tracker.XpOrb;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class XpDropWidget extends Widget{

    private List<XpOrb> xpOrbs;
    private Minecraft mc;
    private XpOrb testDrop;
    private String xpDropString;
    private boolean defaultXpOrb = false;

    public XpDropWidget() {
        this.type = WidgetType.XPDROPWIDGET;
        this.setupConfig();
        this.mc = Minecraft.getInstance();
        this.testDrop = new XpOrb(Main.skills.get(SkillType.TOTAL), 1000);
        this.xpDropString = "";

        this.xpOrbs = new ArrayList<>();
        this.widgetWidth = 102;
        this.widgetHeight = 45;

        try {
            this.relativeAnchorX = Double.parseDouble(config.getProperty("x"));
            this.relativeAnchorY = Double.parseDouble(config.getProperty("y"));
        }catch(Exception ex) {
            setupConfig();
            // TODO:: Setup to redo config in a method IWidget
            this.relativeAnchorX = Double.parseDouble(config.getProperty("x"));
            this.relativeAnchorY = Double.parseDouble(config.getProperty("y"));
        }
    }

    @Override
    public void render() {
        super.render();
        defaultXpOrb = false;

        // Build a rectangle to show boundaries in edit screen
        if (mc.currentScreen instanceof HudEditScreen) {
            testDrop.setXpAmount(100);
            this.widgetWidth = XpOrb.orbDiameter;
//            System.out.println(this.getAnchorX() + ", " + this.getAnchorY());
            int backgroundColor = new Color(255, 255, 255, 100).getRGB();
            GuiUtils.drawGradientRect(1, this.getLeftSide(), this.getTop(), this.getRightSide(), this.getBottom()+15,
                    backgroundColor, backgroundColor);
            testDrop.loadXpDrop(this.getLeftSide(), this.getTop()+17);
            FontRenderer font = mc.fontRenderer;
            String xpFont = "+" + testDrop.getXpAmount();
            font.drawStringWithShadow(xpFont + "xp", (this.getLeftSide() + (this.widgetWidth/2))-(font.getStringWidth(xpFont+"xp")/2), this.getTop()+40, -1);
//            font.drawString("x2", (this.getLeftSide() + (this.widgetWidth/2))-(font.getStringWidth("x2")/2), this.getTop()+40 + font.FONT_HEIGHT, 14408448);

        }else if (defaultXpOrb) {
            if (!xpOrbs.isEmpty()) {
                for (int i = 0; i < xpOrbs.size(); i++) {
                    if (xpOrbs.get(i).isExpired())
                        xpOrbs.remove(i);
                }
                if (!xpOrbs.isEmpty()) {
                    xpOrbs.get(0).loadXpDrop(xpDropString, this.getAnchorX(), this.getAnchorY());
                }
            }
        }else {
            if (!xpOrbs.isEmpty()) {
                int numberOfOrbs = xpOrbs.size();
                this.widgetWidth = (numberOfOrbs * XpOrb.orbDiameter);

                for (int i = 0; i < xpOrbs.size(); i++) {
                    if (xpOrbs.get(i).isExpired())
                        xpOrbs.remove(i);
                    else {
                        int xPosition = this.getLeftSide() + (i * XpOrb.orbDiameter);
                        int yPosition = this.getTop() + 17;
                        // Draw orb
                        xpOrbs.get(i).loadXpDrop(xPosition, yPosition);

                        // Add Xp dropping from below
                        FontRenderer font = mc.fontRenderer;
                        String xpFont = "+" + xpOrbs.get(i).getXpAmount();
                        long milliseconds = xpOrbs.get(i).getTimer().getElapsedTime();
                        int fontDropAmount = 23;
                        if (milliseconds < 3000)
                            fontDropAmount = (int)milliseconds/15 + 10;
                        if (fontDropAmount > 23)
                            fontDropAmount = 23;

                        font.drawStringWithShadow(xpFont+"xp", xPosition+(XpOrb.orbDiameter/2)-(font.getStringWidth(xpFont+"xp")/2), yPosition + fontDropAmount, -1);
//                        font.drawString("x2", xPosition+(XpOrb.orbDiameter/2)-(font.getStringWidth("x2")/2), yPosition + fontDropAmount + font.FONT_HEIGHT, 14408448);
                    }
                }
            }
        }
    }

    public void addXpDrop(XpOrb xpOrb) {
        for (int i = 0; i < xpOrbs.size(); i++) {
            if (xpOrbs.get(i).getSkill().getType() == xpOrb.getSkill().getType()) {
                xpOrbs.get(i).getTimer().reset();
                xpOrbs.get(i).setXpAmount(xpOrb.getXpAmount());
                xpOrbs.get(i).getTimer().startTimer();
                return;
            }
        }
        this.xpOrbs.add(xpOrb);
    }

    public void setXpDropString(String xpDropString) { this.xpDropString = xpDropString; }
}
