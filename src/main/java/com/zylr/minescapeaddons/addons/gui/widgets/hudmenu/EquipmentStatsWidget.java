package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.awt.*;
import java.util.List;

public class EquipmentStatsWidget extends Widget {
    Minecraft mc = Minecraft.getInstance();
    public static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/equipment_stats_background.png");

    // Pre-computed color constants — avoids Color.X.getRGB() on every renderFont call
    private static final int COLOR_ORANGE = Color.ORANGE.getRGB();

    private int attackStab;
    private int attackSlash;
    private int attackCrush;
    private int attackMagic;
    private int attackRanged;
    private int defenseStab;
    private int defenseSlash;
    private int defenseCrush;
    private int defenseMagic;
    private int defenseRanged;
    private int meleeStrength;
    private int rangedStrength;
    private double magicDamage;
    private int prayerBonus;

    public EquipmentStatsWidget() {
        super();

        this.widgetWidth = mc.getWindow().getGuiScaledWidth()/3;
        this.widgetHeight = mc.getWindow().getGuiScaledHeight()/3;

        this.anchorX = (mc.getWindow().getGuiScaledWidth()/2 - this.widgetWidth/2);
        this.anchorY = (mc.getWindow().getGuiScaledHeight()/2 - this.widgetHeight/2);

        this.isParent = false;
        this.visible = false;

        this.attackStab = 0;
        this.attackSlash = 0;
        this.attackCrush = 0;
        this.attackMagic = 0;
        this.attackRanged = 0;
        this.defenseStab = 0;
        this.defenseSlash = 0;
        this.defenseCrush = 0;
        this.defenseMagic = 0;
        this.defenseRanged = 0;
        this.meleeStrength = 0;
        this.rangedStrength = 0;
        this.magicDamage = 0.0;
        this.prayerBonus = 0;
    }

    // Snapshot of equipment item IDs from last getBonuses() call — used to detect changes
    private final ItemStack[] lastEquipSnapshot = new ItemStack[EquipmentWidget.EQUIPEMENT_SLOT_ORDER.length];

    /** Returns true if the player's equipment has changed since the last call. */
    private boolean equipmentChanged() {
        if (mc.player == null) return false;
        for (int idx = 0; idx < EquipmentWidget.EQUIPEMENT_SLOT_ORDER.length; idx++) {
            int slot = EquipmentWidget.EQUIPEMENT_SLOT_ORDER[idx];
            ItemStack current = mc.player.containerMenu.getSlot(slot).getItem();
            ItemStack last = lastEquipSnapshot[idx];
            if (last == null || !ItemStack.isSameItemSameComponents(current, last)) {
                // Snapshot the whole array before returning
                for (int j = 0; j < EquipmentWidget.EQUIPEMENT_SLOT_ORDER.length; j++)
                    lastEquipSnapshot[j] = mc.player.containerMenu.getSlot(EquipmentWidget.EQUIPEMENT_SLOT_ORDER[j]).getItem().copy();
                return true;
            }
        }
        return false;
    }

    public void render(GuiGraphics gui, int xOffset, int yOffset) {
        if (!this.visible) return;

        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        this.widgetWidth = 300;
        this.widgetHeight = (int)(this.widgetWidth * 0.648);
        // Anchor is the top-left of the centered widget in screen coordinates
        this.anchorX = mc.getWindow().getGuiScaledWidth() / 2 - (int)(this.widgetWidth * scale / 2);
        this.anchorY = mc.getWindow().getGuiScaledHeight() / 2 - (int)(this.widgetHeight * scale / 2);

        gui.pose().pushPose();
        gui.pose().translate(1.0f, 1.0f, 99.0f);
        this.scaleAroundAnchor(gui, scale);
        this.drawImage(BACKGROUND, gui);

        // Only recompute bonuses when equipment actually changes, not every frame
        if (equipmentChanged()) {
            this.attackStab = 0;
            this.attackSlash = 0;
            this.attackCrush = 0;
            this.attackMagic = 0;
            this.attackRanged = 0;
            this.defenseStab = 0;
            this.defenseSlash = 0;
            this.defenseCrush = 0;
            this.defenseMagic = 0;
            this.defenseRanged = 0;
            this.meleeStrength = 0;
            this.rangedStrength = 0;
            this.magicDamage = 0.0;
            this.prayerBonus = 0;
            this.getBonuses();
        }
        this.renderFont(gui);

        gui.pose().popPose();
    }

    private void renderFont(GuiGraphics gui) {
        // Title
        gui.drawCenteredString(mc.font, "Equip Your Character...", this.anchorX + (this.widgetWidth/2), this.anchorY + 7, COLOR_ORANGE);
        //Categories
        gui.drawString(mc.font, "Attack bonus", this.anchorX + 175, this.anchorY + 25, COLOR_ORANGE, false);
        gui.drawString(mc.font, "Stab: " + attackStab, this.anchorX + 180, this.anchorY + 35, 0xFFFFFF, false);
        gui.drawString(mc.font, "Magic: " + attackMagic, this.anchorX + 235, this.anchorY + 35, 0xFFFFFF, false);
        gui.drawString(mc.font, "Slash: " + attackSlash, this.anchorX + 180, this.anchorY + 45, 0xFFFFFF, false);
        gui.drawString(mc.font, "Ranged: " + attackRanged, this.anchorX + 235, this.anchorY + 45, 0xFFFFFF, false);
        gui.drawString(mc.font, "Crush: " + attackCrush, this.anchorX + 180, this.anchorY + 55, 0xFFFFFF, false);
        gui.drawString(mc.font, "Defence bonus", this.anchorX + 175, this.anchorY + 65, COLOR_ORANGE, false);
        gui.drawString(mc.font, "Stab: " + defenseStab, this.anchorX + 180, this.anchorY + 75, 0xFFFFFF, false);
        gui.drawString(mc.font, "Magic: " + defenseMagic, this.anchorX + 235, this.anchorY + 75, 0xFFFFFF, false);
        gui.drawString(mc.font, "Slash: " + defenseSlash, this.anchorX + 180, this.anchorY + 85, 0xFFFFFF, false);
        gui.drawString(mc.font, "Ranged: " + defenseRanged, this.anchorX + 235, this.anchorY + 85, 0xFFFFFF, false);
        gui.drawString(mc.font, "Crush: " + defenseCrush, this.anchorX + 180, this.anchorY + 95, 0xFFFFFF, false);
        gui.drawString(mc.font, "Other bonuses", this.anchorX + 175, this.anchorY + 105, COLOR_ORANGE, false);
        gui.drawString(mc.font, "Melee STR: " + meleeStrength, this.anchorX + 180, this.anchorY + 115, 0xFFFFFF, false);
        gui.drawString(mc.font, "Ranged STR: " + rangedStrength, this.anchorX + 180, this.anchorY + 125, 0xFFFFFF, false);
        gui.drawString(mc.font, "Magic DMG: " + magicDamage + "%", this.anchorX + 180, this.anchorY + 135, 0xFFFFFF, false);
        gui.drawString(mc.font, "Prayer: " + prayerBonus, this.anchorX + 180, this.anchorY + 145, 0xFFFFFF, false);

    }

    private void getBonuses() {
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER) {
            Slot slot = mc.player.containerMenu.getSlot(i);
            ItemStack item = slot.getItem();

            List<Component> tooltipLines = item.getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL);
            for (Component tooltipLine : tooltipLines) {
                // Cache getString() and toLowerCase() once — was called up to 3× per keyword check
                String raw = tooltipLine.getString();
                String lower = raw.toLowerCase();

                if (lower.contains("aggressive stab"))        this.attackStab    += parseBonus(raw);
                else if (lower.contains("aggressive slash"))  this.attackSlash   += parseBonus(raw);
                else if (lower.contains("aggressive crush"))  this.attackCrush   += parseBonus(raw);
                else if (lower.contains("aggressive magic"))  this.attackMagic   += parseBonus(raw);
                else if (lower.contains("aggressive ranged")) this.attackRanged  += parseBonus(raw);
                else if (lower.contains("defence stab"))      this.defenseStab   += parseBonus(raw);
                else if (lower.contains("defence slash"))     this.defenseSlash  += parseBonus(raw);
                else if (lower.contains("defence crush"))     this.defenseCrush  += parseBonus(raw);
                else if (lower.contains("defence magic"))     this.defenseMagic  += parseBonus(raw);
                else if (lower.contains("defence ranged"))    this.defenseRanged += parseBonus(raw);
                else if (lower.contains("melee strength"))    this.meleeStrength += parseBonus(raw);
                else if (lower.contains("ranged strength"))   this.rangedStrength += parseBonus(raw);
                else if (lower.contains("magic strength")) {
                    String[] parts = raw.split(":");
                    if (parts.length > 1) {
                        try { this.magicDamage += Double.parseDouble(parts[1].trim().replace("%", "")); }
                        catch (NumberFormatException ignored) {}
                    }
                } else if (lower.contains("prayer"))          this.prayerBonus   += parseBonus(raw);
            }
        }
    }

    /** Parses the integer value after the colon in a "Label: N" tooltip string. Returns 0 on failure. */
    private static int parseBonus(String raw) {
        String[] parts = raw.split(":");
        if (parts.length > 1) {
            try { return Integer.parseInt(parts[1].trim()); }
            catch (NumberFormatException ignored) {}
        }
        return 0;
    }
}
