package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.skills.XpOrb;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.List;

public class XpOrbWidget extends Widget{
    List<XpOrb> orbs;

    public XpOrbWidget() {
        this.widgetWidth = 25;
        this.widgetHeight = 25;

        this.getAnchorXFromRelative(0.5);
        this.getAnchorYFromRelative(0.0);
        this.isParent = true;

        this.orbs = new ArrayList<>();
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        this.widgetWidth = 30;
        this.widgetHeight = 30;
        this.anchorX = (mc.getWindow().getGuiScaledWidth()-250)/2;
        this.getAnchorYFromRelative(0.05);
        this.fixAnchors();

        if (this.orbs.isEmpty())
            this.setVisible(false);
        else
            this.setVisible(true);

        int i = 0;

        if (this.isVisible()) {
            for (XpOrb orb : this.orbs) {
                if (orb.isExpired()) {
                    this.orbs.remove(orb);
                    break;
                }
                orb.loadXpDrop(this.anchorX + (this.widgetWidth * i), this.anchorY, this.widgetWidth / 2, gui);
                i++;
            }
        }
    }

    public void renderResizable(GuiGraphics gui) {
        this.widgetWidth = 30;
        this.widgetHeight = 30;
        this.getAnchorXFromRelative(0.5);
        this.getAnchorYFromRelative(0.05);
        this.fixAnchors();

        if (this.orbs.isEmpty())
            this.setVisible(false);
        else
            this.setVisible(true);

        int i = 0;

        if (this.isVisible()) {
            for (XpOrb orb : this.orbs) {
                if (orb.isExpired()) {
                    this.orbs.remove(orb);
                    break;
                }
                orb.loadXpDrop(this.anchorX + (this.widgetWidth * i), this.anchorY, this.widgetWidth / 2, gui);
                i++;
            }
        }
    }

    public void addXpOrb(XpOrb orb) {
        this.orbs.removeIf(xpOrb -> xpOrb.getSkill() == orb.getSkill());
        this.orbs.add(orb);
    }

    public List<XpOrb> getOrbs() { return orbs; }
}
