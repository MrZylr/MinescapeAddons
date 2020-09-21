package com.zylr.minescapeaddons.addons.skills;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.util.ModHoverChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.util.*;

public class SkillHover {
    private Map<SkillType, Skill> skills;
    private Map<SkillType, ModHoverChecker> skillHover;
    private SkillType skillType;
    private List<String> hoverMessage;
    private int mouseX;
    private int mouseY;

    public SkillHover(int mouseX, int mouseY) {
        this.skillHover = Main.getInstance().getImageButtons().getSkillHovers();
        this.skills = Main.getInstance().skills;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.skillType = getHoveredSkill(mouseX, mouseY);
        this.hoverMessage = skillHoverMessage(this.skillType);
    }

    public void build() {
        if (hoverMessage.size() == 0)
            return;
        int height = ((hoverMessage.size()+1)/2)* Minecraft.getInstance().fontRenderer.FONT_HEIGHT;
        int width = getLongestLine();
        drawHoverBackground(this.mouseX, this.mouseY, height + 6, width + 6);
        writeText(this.mouseX, this.mouseY, height, width);
    }

    public SkillType getHoveredSkill(int mouseX, int mouseY) {
        for (SkillType type:SkillType.values()) {
            if (skillHover.get(type) != null) {
                if (skillHover.get(type).checkHover(mouseX, mouseY)) {
                    this.skillType = type;
                }
            }
        }
        return this.skillType;
    }

    public List<String> skillHoverMessage(SkillType skillType) {
        this.hoverMessage = new ArrayList<>();
        if (skillType == null)
            return this.hoverMessage;
        Skill skill = skills.get(skillType);
        // Get Total EXP
        this.hoverMessage.add(skill.getName() + " XP: ");
        if (skillType == SkillType.TOTAL)
            this.hoverMessage.add(skill.getTotalExp() + "");
        else
            this.hoverMessage.add(skill.getExp() + "");

        if (skillType != SkillType.TOTAL) {
            // Get Next Level EXP
            this.hoverMessage.add("Next level at: ");
            this.hoverMessage.add(skill.getNextLevel() + "");

            // Get Remaining EXP
            this.hoverMessage.add("Remaining XP: ");
            this.hoverMessage.add((skill.getNextLevel() - skill.getExp()) + "");
        }
        this.hoverMessage.add("(Click to set tracker)");

        return this.hoverMessage;
    }

    public int getLongestLine() {
        int width = 0;
        if (hoverMessage.size() == 0)
            return width;

        List<Integer> lines = new ArrayList<>();
        FontRenderer font = Minecraft.getInstance().fontRenderer;

        lines.add(font.getStringWidth(hoverMessage.get(0)) + font.getStringWidth(hoverMessage.get(1)));
        if (hoverMessage.size() > 3) {
            lines.add(font.getStringWidth(hoverMessage.get(2)) + font.getStringWidth(hoverMessage.get(3)));
            lines.add(font.getStringWidth(hoverMessage.get(4)) + font.getStringWidth(hoverMessage.get(5)));
            lines.add(font.getStringWidth(hoverMessage.get(6)));
        }else
            lines.add(font.getStringWidth(hoverMessage.get(2)));

        width = Collections.max(lines);
        return width;
    }

    public void drawHoverBackground(int mouseX, int mouseY, int width, int height) {
        // Color FFFFA0
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.disableAlphaTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(GL11.GL_SMOOTH);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(mouseX-height-1, mouseY+1, 0)        .color(0, 0, 0, 255).endVertex();
        buffer.pos(mouseX+1, mouseY+1, 0)               .color(0, 0, 0, 255).endVertex();
        buffer.pos(mouseX+1, mouseY-width-1, 0)         .color(0, 0, 0, 255).endVertex();
        buffer.pos(mouseX-height-1, mouseY-width-1, 0)  .color(0, 0, 0, 255).endVertex();
        tessellator.draw();
        buffer.discard();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(mouseX-height, mouseY, 0).color(255, 255, 160, 255).endVertex();
        buffer.pos(mouseX, mouseY, 0).color(255, 255, 160, 255).endVertex();
        buffer.pos(mouseX, mouseY-width, 0).color(255, 255, 160, 255).endVertex();
        buffer.pos(mouseX-height, mouseY-width, 0).color(255, 255, 160, 255).endVertex();
        tessellator.draw();

        RenderSystem.shadeModel(GL11.GL_FLAT);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableTexture();
    }

    public void writeText(int mouseX, int mouseY, int width, int height) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        if (this.hoverMessage.size() == 0)
            return;

        // Line 1
        font.drawString(this.hoverMessage.get(0), mouseX - height - 5, mouseY - width - 3, 000000);
        font.drawString(this.hoverMessage.get(1), mouseX - font.getStringWidth(this.hoverMessage.get(1)), mouseY - width - 3, 000000);

        if (this.hoverMessage.size() > 3) {
            // Line 2
            font.drawString(this.hoverMessage.get(2), mouseX - height - 5, mouseY - width + font.FONT_HEIGHT - 3, 000000);
            font.drawString(this.hoverMessage.get(3), mouseX - font.getStringWidth(this.hoverMessage.get(3)), mouseY - width + font.FONT_HEIGHT - 3, 000000);
            // Line 2
            font.drawString(this.hoverMessage.get(4), mouseX - height - 5, mouseY - width + font.FONT_HEIGHT*2 - 3, 000000);
            font.drawString(this.hoverMessage.get(5), mouseX - font.getStringWidth(this.hoverMessage.get(5)), mouseY - width + font.FONT_HEIGHT*2 - 3, 000000);
            font.drawString(hoverMessage.get(hoverMessage.size()-1), mouseX - height - 5, mouseY - width + font.FONT_HEIGHT*3 - 3, 000000);
        }else
            font.drawString(hoverMessage.get(hoverMessage.size()-1), mouseX - height - 5, mouseY - width + font.FONT_HEIGHT - 3, 000000);
    }
}


