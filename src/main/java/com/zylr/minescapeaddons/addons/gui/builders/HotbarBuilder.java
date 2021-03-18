package com.zylr.minescapeaddons.addons.gui.builders;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.skills.PersistentExp;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotbarBuilder {
    public final ResourceLocation STATS_SCREEN = new ResourceLocation(Main.ID, "textures/gui/stats.png");
    public final ResourceLocation INVENTORY_SCREEN = new ResourceLocation(Main.ID, "textures/gui/inventory.png");
    public final ResourceLocation EQUIPMENT_SCREEN = new ResourceLocation(Main.ID, "textures/gui/equipment.png");
    public final ResourceLocation HOTBAR = new ResourceLocation(Main.ID, "textures/gui/hotbar.png");
    public final ResourceLocation VERTICAL_HOTBAR_LEFT = new ResourceLocation(Main.ID, "textures/gui/hotbar_vertical_left.png");
    public final ResourceLocation VERTICAL_HOTBAR_RIGHT = new ResourceLocation(Main.ID, "textures/gui/hotbar_vertical_right.png");


    private ResourceLocation display;
    private int currentScreen;
    public int topOfInventory = 0;

    private boolean grabbedStats;
    private boolean small;
    private boolean vertical;
    Map<SkillType, Skill> skills = Main.getInstance().skills;
    String[] skillsList = Main.getInstance().skillsList;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferBuilder = tessellator.getBuffer();

    public HotbarBuilder() {
        grabbedStats = false;
        small = false;
        vertical = false;
        display = STATS_SCREEN;
        currentScreen = 0;
    }

    public void build() {
        if (!ModConfiguration.CLIENT.hotbar.get()) {
            return;
        }

        switch(currentScreen) {
            case 0:
                display = STATS_SCREEN;
                break;
            case 1:
                display = INVENTORY_SCREEN;
                break;
            case 2:
                display = EQUIPMENT_SCREEN;
                break;
            default:
                display = STATS_SCREEN;
                break;
        }
        small = ModConfiguration.CLIENT.small.get();
        vertical = ModConfiguration.CLIENT.verticalBar.get();

        if (vertical) {
            topOfInventory = 178;
            BuilderUtils.rectangle(158D, 178D, 133, 178, display, small);
        } else {
            topOfInventory = 203;
            BuilderUtils.rectangle(133D, 203D, 133, 178, display, small);
        }


        buildHotbar();
        Main.getInstance().getImageButtons().drawSkillButtons();
    }
    public void buildHotbar() {
        // Build skill tab
        List<Double> corners = new ArrayList<>();
        corners.add(298D);corners.add(25D);
        corners.add(298D);corners.add(0D);
        corners.add(0D);corners.add(0D);
        corners.add(0D);corners.add(25D);
        if(vertical) {
                BuilderUtils.rectangle(183, 178D, 25, 178, VERTICAL_HOTBAR_LEFT, small);
            BuilderUtils.rectangle(25, 178D, 25, 178, VERTICAL_HOTBAR_RIGHT, small);
        } else
            BuilderUtils.rectangle(275D, 25D, 275, 25, HOTBAR, small);



        populateSkills();
    }

    public void populateSkills() {
        if (ModConfiguration.CLIENT.hotbar.get() == false)
            return;
        // Check if stats panel
        if (currentScreen != 0)
            return;
        // Get screen size
        int width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        FontRenderer font = Minecraft.getInstance().fontRenderer;

        // Fill skill tab
        int i = 0;
        int w = 0;
        int h = 0;
        int total = 0;
        for (String s : skillsList) {
            Skill skill = skills.get(SkillType.valueOf(s.toUpperCase()));

            List<Float> skillPos = new ArrayList<>();
            skillPos.add(108F);
            skillPos.add(196F);
            skillPos.add(41F);
            skillPos.add(21F);
            skillPos.add(9F);
            if (vertical) {
                skillPos.clear();
                skillPos.add(132F);
                skillPos.add(171F);
                skillPos.add(42F);
                skillPos.add(21F);
                skillPos.add(9F);
            }
            // Starting position
            float skillWidth = skillPos.get(0);
            float skillHeight = skillPos.get(1);
            // Spacing between skills
            float widthAdjust = w * skillPos.get(2);
            float heightAdjust = h * skillPos.get(3);
            // Space between number
            float secondDigit = skillPos.get(4);
            if (small) {
                for (int i1 = 0; i1 < skillPos.size(); i1++) {
                    skillPos.set(i1, skillPos.get(i1)*.75F);
                }
                // Starting position
                skillWidth = skillPos.get(0);
                skillHeight = skillPos.get(1);
                // Spacing between skills
                widthAdjust = w * skillPos.get(2);
                heightAdjust = h * skillPos.get(3);
                // Space between number
                secondDigit = skillPos.get(4);
            }

            skillWidth = skillWidth - widthAdjust;
            skillHeight = skillHeight - heightAdjust;

            List<Float> pos = new ArrayList<>();
            String totalLevel = "Total Lvl:";
            pos.add(42F);
            pos.add(51F);
            pos.add(7F);
            pos.add(6F);
            if (vertical) {
                pos.clear();
                pos.add(64F);
                pos.add(27F);
                pos.add(7F);
                pos.add(7F);
            }
            if(small){
                totalLevel = "Total:";
                for (int i1 = 0; i1 < pos.size(); i1++) {
                    pos.set(i1, pos.get(i1)*.75F);
                }
            }
            // Set Total Level position
            if (skill.getType() == SkillType.TOTAL) {
                skill.setInvX(width - pos.get(0));
                skill.setInvY(height - pos.get(1));
                if(small) {
                    skill.setInvX(width - pos.get(0) - 10);
                }
                font.drawString(totalLevel, width - pos.get(0), height - pos.get(1), 14408448);
                font.drawString(/*total*/ skill.getLevel() + "", width - skillWidth - pos.get(2), height - skillHeight + pos.get(3), 14408448);
                break;
            }
            skill.setInvX(width - skillWidth + secondDigit);
            skill.setInvY(height - skillHeight + secondDigit);
            if (small) {
                skill.setInvX(width - skillWidth + secondDigit + 7);
            }

            font.drawString(skill.getLevel() + "", width - skillWidth, height - skillHeight, 14408448);
            font.drawString(skill.getLevel() + "", width - skillWidth + secondDigit, height - skillHeight + secondDigit, 14408448);

            total += skill.getLevel();
            w++;
            i++;
            if (i % 3 == 0) {
                w = 0;
                h++;
            }
        }

        RenderSystem.popMatrix();
    }

    public void updateStats(List<ItemStack> inv) {
        int totalLvl = 0;

        // Check that stats belong to player
        if (!inv.get(13).getDisplayName().getFormattedText().equalsIgnoreCase("air")) {
            if (!inv.get(13).getDisplayName().getFormattedText().substring(4).
                    equalsIgnoreCase(Minecraft.getInstance().player.getName().getFormattedText()))
                return;
        }

        // Start sorting through each skill
        for (String s : skillsList) {
            Skill skill = skills.get(SkillType.valueOf(s.toUpperCase()));
            int slot = skill.getSlot();
            ItemStack slotItem = inv.get(slot);
            String displayName = slotItem.getDisplayName().getFormattedText();

            // ignore empty slots
            if (displayName.equalsIgnoreCase("air"))
                skill.setLevel(1);
            else {
                int level = 1;

                // Grab info out of the "lore"/"tooltip"
                List<ITextComponent> tooltip = slotItem.getTooltip(Minecraft.getInstance().player, ITooltipFlag.TooltipFlags.NORMAL);
                // 1=xp
                skill.setupLevel(Integer.parseInt(tooltip.get(1).getFormattedText().
                        substring(tooltip.get(1).getFormattedText().indexOf(":")+2, tooltip.get(1).getFormattedText().lastIndexOf("r")-1 )));
            }

            skill.shouldLvlUp();
            // Set total level
            if (skill.getType() == SkillType.TOTAL)
                skill.setLevel(totalLvl);

            // Add total lvl
            totalLvl = skill.getLevel() + totalLvl;

        }
        PersistentExp.saveExp();
        this.grabbedStats = true;
    }
    public void setCurrentScreen(int currentScreen) { this.currentScreen = currentScreen; }

    public int getCurrentScreen() { return currentScreen; }

    public boolean hasGrabbedStats() {
        return grabbedStats;
    }

    public void setGrabbedStats(boolean b) {
        this.grabbedStats = b;
    }

    public int getXOrigin() {
        if (vertical) {
            if (small)
                return (int)(158*.75);
            return 158;
        }else if (small)
            return (int)(133*.75);

        return 133;
    }

    public int getYOrigin() {
        if (vertical) {
            if (small)
                return (int)(178*.75);
            return 178;
        }else if (small)
            return (int)(203*.75);

        return 203;
    }
}
