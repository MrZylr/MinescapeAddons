package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SkillHoverWidget extends Widget{

    public final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/skill_hover_background.png");
    private Skill skill;

    public SkillHoverWidget(Skill skill) {
        this.widgetWidth = 100;
        this.widgetHeight = 50;

        this.anchorX = 0;
        this.anchorY = 0;
        this.isParent = false;

        this.skill = skill;
        this.visible = false;

        this.scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
    }

    @Override
    public void render(GuiGraphics gui) {
        if (this.skill.getType() == SkillType.TOTAL)
            this.drawTotalBox(gui);
        else
            this.drawSkillBox(gui);
    }

    private void drawSkillBox(GuiGraphics gui) {
        Minecraft mc = Minecraft.getInstance();

        DecimalFormat format = new DecimalFormat("#,###");
        List<String> textList = new ArrayList<>();
        textList.add(this.skill.getName() + " XP: ");
        textList.add("Next level at: ");
        textList.add("Remaining XP: ");
        textList.add(format.format((int)this.skill.getExp()));
        textList.add(format.format((int)this.skill.getNextLevel()));
        textList.add(format.format((int)this.skill.getTilNextLevel()));

        int lineOneWidth = mc.font.width(textList.get(0) + textList.get(3));
        int lineTwoWidth = mc.font.width(textList.get(1) + textList.get(4));
        int lineThreeWidth = mc.font.width(textList.get(2) + textList.get(5));
        int widestLine = Math.max(lineOneWidth, Math.max(lineTwoWidth, lineThreeWidth));

        this.widgetWidth = widestLine + 4;
        if (this.skill.getTilNextLevel() <= 0) {
            this.widgetHeight = mc.font.lineHeight + 4;
        } else {
            this.widgetHeight = mc.font.lineHeight * 3 + 4;
        }
        this.fixAnchors();

        gui.pose().pushPose();
        gui.pose().translate(0, 0, 1100);
        this.drawImage(BACKGROUND, gui);

        gui.drawString(mc.font, textList.get(0), this.getLeftSide() + 2, this.getTop() + 2, Color.BLACK.getRGB(), false);
        gui.drawString(mc.font, textList.get(3), this.getRightSide() - mc.font.width(textList.get(3)) - 2,
                this.getTop() + 2, Color.BLACK.getRGB(), false);

        if (this.skill.getTilNextLevel() > 0) {
            gui.drawString(mc.font, textList.get(1), this.getLeftSide() + 2, this.getTop() + mc.font.lineHeight + 2,
                    Color.BLACK.getRGB(), false);
            gui.drawString(mc.font, textList.get(4), this.getRightSide() - mc.font.width(textList.get(4)) - 2,
                    this.getTop() + mc.font.lineHeight + 2, Color.BLACK.getRGB(), false);

            gui.drawString(mc.font, textList.get(2), this.getLeftSide() + 2, this.getTop() + mc.font.lineHeight * 2 + 2,
                    Color.BLACK.getRGB(), false);
            gui.drawString(mc.font, textList.get(5), this.getRightSide() - mc.font.width(textList.get(5)) - 2,
                    this.getTop() + mc.font.lineHeight * 2 + 2, Color.BLACK.getRGB(), false);
        }
        gui.pose().popPose();
    }

    private void drawTotalBox(GuiGraphics gui) {
        Minecraft mc = Minecraft.getInstance();

        DecimalFormat format = new DecimalFormat("#,###");
        String totalXp = "Total XP: " + format.format(this.skill.getTotalExp());

        this.widgetWidth = mc.font.width(totalXp) + 4;
        this.widgetHeight = mc.font.lineHeight + 4;
        this.fixAnchors();

        gui.pose().pushPose();
        gui.pose().translate(0, 0, 1100);
        this.drawImage(BACKGROUND, gui);
        gui.drawString(mc.font, totalXp, this.getLeftSide() + 2, this.getTop() + 2, Color.BLACK.getRGB(), false);
        gui.pose().popPose();
    }
}
