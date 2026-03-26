package com.zylr.minescapeaddons.addons.listeners;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.BrowserWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import com.zylr.minescapeaddons.addons.gui.widgets.hudmenu.*;
import com.zylr.minescapeaddons.addons.skills.SkillType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.awt.*;
import java.util.List;

@Listener
public class OpenContainerListener {
    public static final Logger LOGGER  = MinescapeAddons.LOGGER;
    Minecraft mc = Minecraft.getInstance();

    // Static constants — avoids allocating a new list and Color objects every hover frame
    private static final List<String> PRAYER_BOOST_ITEMS = List.of(
            "prayer cape", "prayer cape t", "max cape", "ring of the gods (i)", "holy wrench");
    private static final int COLOR_YELLOW = Color.YELLOW.getRGB();
    private static final int COLOR_WHITE  = Color.WHITE.getRGB();
    private static final int COLOR_GREEN  = Color.GREEN.getRGB();
    @SubscribeEvent
    public void onOpenInventory(ScreenEvent.Render.Post e) {


        if (mc.screen instanceof ContainerScreen) {
            if (MinescapeAddons.getInstance().scoreboard == null)
                MinescapeAddons.getInstance().scoreboard = Minecraft.getInstance().level.getScoreboard();

            for (IWidget widget : MinescapeAddons.getInstance().activeHud.widgets) {
                if (widget != null) {
                    if (widget instanceof BrowserWidget browser) {
                        browser.render(e.getGuiGraphics());
                    } else {
                        if (mc.screen instanceof ZylrInventoryScreen) return;
                        widget.render(e.getGuiGraphics());
                    }
                }
            }
        }


        // Barrows answers
        // Type 1 answer damage value 1536
        // Type 2 answer damage value 1516
        // Type 3 answer damage value 1523
        // Type 4 answer damage value 1530

        // Check for bank screen
        if (mc.screen instanceof ContainerScreen bankScreen) {
            //if (mc.screen.getTitle().getString().equalsIgnoreCase("bank")) {

            // Check for Barrows loot screen to reset brother widget values
            if (mc.screen.getTitle().getString().equalsIgnoreCase("barrows loot")) {
                MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().setHasFinished(true);
                MinescapeAddons.getInstance().resizableClassic.getBarrrowsBrothersWidget().resetValues();
            }


            if (Config.getBankNumbers()) {
                float scale = 1f;
                PoseStack pose = e.getGuiGraphics().pose();
                pose.pushPose();
                pose.scale(scale, scale, 1.0f);
                pose.translate(0, 0, 301);
                for (Slot slot : bankScreen.getMenu().slots) {
                    ItemStack item = slot.getItem();
                    if (item.isEmpty()) continue;

                    // Cache tooltip lines once — was called up to 4 times per slot per frame
                    List<Component> tooltipLines = item.getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL);
                    if (tooltipLines.size() <= 1) continue;

                    String line2 = tooltipLines.get(1).getString();
                    String line4 = tooltipLines.size() > 3 ? tooltipLines.get(3).getString() : null;

                    int amount = 0;
                    try { amount = Integer.parseInt(line2.substring(1)); } catch (Exception ignored) {}
                    if (amount == 0) {
                        try { if (line4 != null) amount = Integer.parseInt(line4.substring(1)); } catch (Exception ignored) {}
                    }

                    if (amount > 1) {
                        String number = amount + "";
                        int color = COLOR_YELLOW;
                        if (amount >= 1000)    { number = amount / 1000 + "K";    color = COLOR_WHITE; }
                        if (amount >= 1000000) { number = amount / 1000000 + "M"; color = COLOR_GREEN; }
                        e.getGuiGraphics().drawString(mc.font, number,
                                (int) ((bankScreen.getGuiLeft() + slot.x) / scale),
                                (int) ((bankScreen.getGuiTop() + slot.y) / scale), color);
                    }

                }
                pose.popPose();
            }
        }


        // Begin code for HUD in Inventory Screen
        if (MinescapeAddons.getInstance().activeHud == MinescapeAddons.getInstance().resizableClassic) {
            if (mc.screen instanceof ZylrInventoryScreen invScreen) {
                e.getGuiGraphics().fill(0, 0, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(), mc.options.getBackgroundColor(0.5f));

                HudMenuWidget hudMenu = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
                float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
                for (IWidget child : hudMenu.getChildren()) {
                    if (child != hudMenu.getActiveWidget())
                        child.setVisible(false);
                    else
                        child.setVisible(true);
                }

                // Set the slots zero point
                int invScreenZeroX = 0;
                int invScreenZeroY = 0;


                //Move crafting slots
                int offScreenCraft = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;
                invScreen.menu.slots.get(37).x = offScreenCraft;// Hide this slot permanently
                invScreen.menu.slots.get(0).x  = offScreenCraft;// Hide this slot permanently
                invScreen.menu.slots.get(1).x  = offScreenCraft;// Hide this slot permanently
                invScreen.menu.slots.get(2).x  = offScreenCraft;// Hide this slot permanently
                invScreen.menu.slots.get(3).x  = offScreenCraft;// Hide this slot permanently
                invScreen.menu.slots.get(4).x  = offScreenCraft;// Hide this slot permanently


                //Code for in InventoryWidget
                if (HudMenuWidget.INVENTORY_WIDGET.isVisible()) {
                    if (Config.getFixedMode())
                        drawInventoryFixed(e, invScreen, hudMenu);
                    else {
                        if (!Config.getSmallInventory())
                            drawInventoryLarge(e, invScreen, hudMenu);
                        else
                            drawInventorySmall(e, invScreen, hudMenu);
                    }
                }



                //Code for EquipmentWidget
                if (HudMenuWidget.EQUIPMENT_WIDGET.isVisible()) {
                    if (Config.getFixedMode())
                        drawEquipmentFixed(e, invScreen, hudMenu);
                    else {
                        if (!Config.getSmallInventory())
                            drawEquipmentLarge(e, invScreen, hudMenu);
                        else
                            drawEquipmentSmall(e, invScreen, hudMenu);
                    }
                }

                // Code for Combat tab
                if (HudMenuWidget.COMBAT_WIDGET.isVisible()) {
                    if (Config.getFixedMode())
                        drawCombatFixed(e, invScreen, hudMenu);
                    else {
                        if (!Config.getSmallInventory())
                            drawCombatLarge(e, invScreen, hudMenu);
                        else
                            drawCombatSmall(e, invScreen, hudMenu);
                    }
                }

                // Code for Skills tab
                if (HudMenuWidget.SKILLS_WIDGET.isVisible()) {
                    setupSkillsTab(e, invScreen, hudMenu);
                }

                // Code for EquipmentStatsWidget
                EquipmentStatsWidget statsWidget = MinescapeAddons.getInstance().resizableClassicChildren.getEquipmentStatsWidget();
                if (statsWidget.isVisible()) {
                    statsWidget.render(e.getGuiGraphics(), invScreenZeroX, invScreenZeroY);
                    hudMenu.setActiveWidget(HudMenuWidget.INVENTORY_WIDGET);



                    invScreen.menu.slots.get(6).x  = statsWidget.getLeftSide() + 45;
                    invScreen.menu.slots.get(6).y  = statsWidget.getTop() + 87;
                    invScreen.menu.slots.get(7).x  = statsWidget.getLeftSide() + 45;
                    invScreen.menu.slots.get(7).y  = statsWidget.getTop() + 111;
                    invScreen.menu.slots.get(36).x = statsWidget.getLeftSide() + 11;
                    invScreen.menu.slots.get(36).y = statsWidget.getTop() + 86;
                    invScreen.menu.slots.get(18).x = statsWidget.getLeftSide() + 11;
                    invScreen.menu.slots.get(18).y = statsWidget.getTop() + 135;
                    invScreen.menu.slots.get(10).x = statsWidget.getLeftSide() + 20;
                    invScreen.menu.slots.get(10).y = statsWidget.getTop() + 64;
                    invScreen.menu.slots.get(45).x = statsWidget.getLeftSide() + 80;
                    invScreen.menu.slots.get(45).y = statsWidget.getTop() + 86;
                    invScreen.menu.slots.get(28).x = statsWidget.getLeftSide() + 70;
                    invScreen.menu.slots.get(28).y = statsWidget.getTop() + 64;
                    invScreen.menu.slots.get(8).x  = statsWidget.getLeftSide() + 45;
                    invScreen.menu.slots.get(8).y  = statsWidget.getTop() + 135;
                    invScreen.menu.slots.get(9).x  = statsWidget.getLeftSide() + 45;
                    invScreen.menu.slots.get(9).y  = statsWidget.getTop() + 64;
                    invScreen.menu.slots.get(5).x  = statsWidget.getLeftSide() + 45;
                    invScreen.menu.slots.get(5).y  = statsWidget.getTop() + 40;
                    invScreen.menu.slots.get(19).x = statsWidget.getLeftSide() + 80;
                    invScreen.menu.slots.get(19).y = statsWidget.getTop() + 135;
                }

                // Code for MenuHud — must be in its own push/pop so the scale doesn't leak
                e.getGuiGraphics().pose().pushPose();
                hudMenu.scaleAroundAnchor(e.getGuiGraphics(), scale);
                e.getGuiGraphics().pose().translate(0, 0, 100);
                if (Config.getHpPrayerBars())
                    hudMenu.renderBars(e.getGuiGraphics(), invScreenZeroX, invScreenZeroY);
                e.getGuiGraphics().pose().popPose();

                // Warp cursor after first render, but only when window is stable
                if (pendingCursorWarp && !isWindowBeingResized()) {
                    HudMenuWidget hudMenu2 = MinescapeAddons.getInstance().resizableClassic.getHudMenu();
                    float scale2 = hudMenu2.scale;
                    double guiScale = mc.getWindow().getGuiScale();
                    double mouseX = (hudMenu2.getLeftSide() + (hudMenu2.getWidgetWidth()  * scale2) / 2.0) * guiScale;
                    double mouseY = (hudMenu2.getTop()      + (hudMenu2.getWidgetHeight() * scale2) / 2.0) * guiScale;
                    GLFW.glfwSetCursorPos(mc.getWindow().getWindow(), mouseX, mouseY);
                    pendingCursorWarp = false;
                }
            }
        }
    }

    private boolean pendingCursorWarp = false;

    @SubscribeEvent
    public void onInventoryOpen(ScreenEvent.Init.Post e) {
        if (e.getScreen() instanceof ZylrInventoryScreen) {
            pendingCursorWarp = true;  // Always set — resize check happens at render time
        }
    }

    private int lastWidth  = -1;
    private int lastHeight = -1;
    private long lastResizeTime = 0;
    private static final long RESIZE_COOLDOWN_MS = 500;

    private boolean isWindowBeingResized() {
        int currentWidth  = mc.getWindow().getWidth();
        int currentHeight = mc.getWindow().getHeight();

        // First call — seed the values and allow the warp (not a real resize)
        if (lastWidth == -1 || lastHeight == -1) {
            lastWidth  = currentWidth;
            lastHeight = currentHeight;
            return false;
        }

        long now = System.currentTimeMillis();

        if (lastWidth != currentWidth || lastHeight != currentHeight) {
            lastWidth      = currentWidth;
            lastHeight     = currentHeight;
            lastResizeTime = now;
            return true;
        }

        return now - lastResizeTime < RESIZE_COOLDOWN_MS;
    }

    public void setupTab(Widget widget, GuiGraphics gui, ResourceLocation resource, int invScreenZeroX, int invScreenZeroY) {

    }

    private void drawInventoryFixed(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        InventoryWidget invWidget = HudMenuWidget.INVENTORY_WIDGET;
        int offScreen = (int)(Minecraft.getInstance().getWindow().getGuiScaledWidth()) + 500;

        //setupTab(invWidget, e.getGuiGraphics(), invWidget.INVENTORY, 0, 0);

        // Remove equipment items
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

        int slotX = invWidget.getLeftSide();
        int slotY = invWidget.getTop() + 15;

        int i1 = 0;
        boolean isHighlighted = false;
        for (int i : invWidget.INVENTORY_SLOT_ORDER) {
            if (i1 % 4 == 0) {
                slotX = invWidget.getLeftSide();
                slotY = slotY + 38;
            }
            slotX = slotX + (48);
            i1++;
            invScreen.menu.slots.get(i).x = slotX;
            invScreen.menu.slots.get(i).y = slotY;
            if (invScreen.menu.slots.get(i).getItem().isEnchanted()) {
                isHighlighted = true;
            }

            // check if slot is hovered
            if (invScreen.isHovering(invScreen.menu.getSlot(i), e.getMouseX(), e.getMouseY())) {
                ItemStack itemstack = invScreen.menu.getSlot(i).getItem();
                if (!itemstack.isEmpty()) {
                    // Cache toLowerCase once — was computed multiple times per item
                    String displayName = itemstack.getDisplayName().getString().toLowerCase();
                    // Calculate prayer restore amount for prayer potions and super restores
                    if (displayName.contains("prayer potion") || displayName.contains("super restore")) {
                        boolean boostedRestore = false;
                        outer:
                        for (Slot slot : invScreen.menu.slots) {
                            String slotName = slot.getItem().getDisplayName().getString().toLowerCase();
                            for (String boostItem : PRAYER_BOOST_ITEMS) {
                                if (slotName.contains(boostItem)) { boostedRestore = true; break outer; }
                            }
                        }
                        int boostAmount = 0;
                        double boostPercent = 0.25;
                        int prayerLevel = MinescapeAddons.skills.get(SkillType.PRAYER).getLevel();
                        if (boostedRestore) boostPercent = 27;
                        if (displayName.contains("prayer potion")) boostAmount = 7;
                        if (displayName.contains("super restore"))  boostAmount = 8;
                        hudMenu.setPrayerRestoreAmount(boostAmount + (int)(prayerLevel * boostPercent));
                    }
                    // Calculate heal amount for food
                    for (Component tooltipLine : itemstack.getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL)) {
                        if (tooltipLine.getString().toLowerCase().contains("heals")) {
                            String[] parts = tooltipLine.getString().split(" ");
                            if (parts.length > 1) {
                                try { hudMenu.setHealAmount(Integer.parseInt(parts[1])); }
                                catch (NumberFormatException ex) { hudMenu.setHealAmount(0); }
                            }
                        }
                    }
                }
            }
        }
        if (isHighlighted && Config.getSlotHighlight()) {
            for (int i : invWidget.INVENTORY_SLOT_ORDER) {
                Slot slot = invScreen.menu.slots.get(i);
                int slotSize = 32;
                int scaledSlotX = slot.x-8;
                int scaledSlotY = slot.y - 8;
                e.getGuiGraphics().pose().pushPose();
                e.getGuiGraphics().pose().translate(0, 0, 200);
                e.getGuiGraphics().fill(scaledSlotX, scaledSlotY, scaledSlotX + slotSize, scaledSlotY + slotSize,
                        mc.options.getBackgroundColor(0.3f));
                e.getGuiGraphics().pose().popPose();
            }
        }
    }

    private void drawInventoryLarge(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        InventoryWidget invWidget = HudMenuWidget.INVENTORY_WIDGET;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;

        setupTab(invWidget, e.getGuiGraphics(), invWidget.INVENTORY, 0, 0);

        // Remove equipment items
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

        int slotX = invWidget.getLeftSide() - (12);
        int slotY = invWidget.getTop() - (12);

        int i1 = 0;
        boolean isHighlighted = false;
        for (int i : invWidget.INVENTORY_SLOT_ORDER) {
            if (i1 % 4 == 0) {
                slotX = invWidget.getLeftSide() - (12);
                slotY = slotY + 24;
            }
            slotX = slotX + (28);
            i1++;
            invScreen.menu.slots.get(i).x = slotX;
            invScreen.menu.slots.get(i).y = slotY;
            if (invScreen.menu.slots.get(i).getItem().isEnchanted()) {
                isHighlighted = true;
            }

            // check if slot is hovered
            if (invScreen.isHovering(invScreen.menu.getSlot(i), e.getMouseX(), e.getMouseY())) {
                ItemStack itemstack = invScreen.menu.getSlot(i).getItem();
                if (!itemstack.isEmpty()) {
                    // Cache toLowerCase once — was computed multiple times per item
                    String displayName = itemstack.getDisplayName().getString().toLowerCase();
                    // Calculate prayer restore amount for prayer potions and super restores
                    if (displayName.contains("prayer potion") || displayName.contains("super restore")) {
                        boolean boostedRestore = false;
                        outer:
                        for (Slot slot : invScreen.menu.slots) {
                            String slotName = slot.getItem().getDisplayName().getString().toLowerCase();
                            for (String boostItem : PRAYER_BOOST_ITEMS) {
                                if (slotName.contains(boostItem)) { boostedRestore = true; break outer; }
                            }
                        }
                        int boostAmount = 0;
                        double boostPercent = 0.25;
                        int prayerLevel = MinescapeAddons.skills.get(SkillType.PRAYER).getLevel();
                        if (boostedRestore) boostPercent = 27;
                        if (displayName.contains("prayer potion")) boostAmount = 7;
                        if (displayName.contains("super restore"))  boostAmount = 8;
                        hudMenu.setPrayerRestoreAmount(boostAmount + (int)(prayerLevel * boostPercent));
                    }
                    // Calculate heal amount for food
                    for (Component tooltipLine : itemstack.getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL)) {
                        if (tooltipLine.getString().toLowerCase().contains("heals")) {
                            String[] parts = tooltipLine.getString().split(" ");
                            if (parts.length > 1) {
                                try { hudMenu.setHealAmount(Integer.parseInt(parts[1])); }
                                catch (NumberFormatException ex) { hudMenu.setHealAmount(0); }
                            }
                        }
                    }
                }
            }
        }
        if (isHighlighted && Config.getSlotHighlight()) {
            for (int i : invWidget.INVENTORY_SLOT_ORDER) {
                Slot slot = invScreen.menu.slots.get(i);
                int slotSize = (int)(16 * scale);
                e.getGuiGraphics().pose().pushPose();
                e.getGuiGraphics().pose().translate(0, 0, 200);
                e.getGuiGraphics().fill(slot.x, slot.y, slot.x + slotSize, slot.y + slotSize,
                        mc.options.getBackgroundColor(0.3f));
                e.getGuiGraphics().pose().popPose();
            }
        }
    }

    private void drawInventorySmall(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        InventoryWidget invWidget = HudMenuWidget.INVENTORY_WIDGET;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;

        setupTab(invWidget, e.getGuiGraphics(), invWidget.INVENTORY, 0, 0);

        // Remove equipment items
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

        invWidget.render(e.getGuiGraphics());

        int slotX = invWidget.getLeftSide() - (12);
        int slotY = invWidget.getTop() - (12);

        int i1 = 0;
        boolean isHighlighted = false;
        for (int i : invWidget.INVENTORY_SLOT_ORDER) {
            if (i1 % 4 == 0) {
                slotX = invWidget.getLeftSide() - (12);
                slotY = slotY + 19;
            }
            slotX = slotX + (23);
            i1++;
            invScreen.menu.slots.get(i).x = slotX;
            invScreen.menu.slots.get(i).y = slotY;
            if (invScreen.menu.slots.get(i).getItem().isEnchanted()) {
                isHighlighted = true;
            }

            // check if slot is hovered and if food item, then display heal amount
            if (invScreen.isHovering(invScreen.menu.getSlot(i), e.getMouseX(), e.getMouseY())) {
                ItemStack itemstack = invScreen.menu.getSlot(i).getItem();
                if (!itemstack.isEmpty()) {
                    String displayName = itemstack.getDisplayName().getString().toLowerCase();
                    if (displayName.contains("prayer potion") || displayName.contains("super restore")) {
                        boolean boostedRestore = false;
                        outer:
                        for (Slot slot : invScreen.menu.slots) {
                            String slotName = slot.getItem().getDisplayName().getString().toLowerCase();
                            for (String boostItem : PRAYER_BOOST_ITEMS) {
                                if (slotName.contains(boostItem)) { boostedRestore = true; break outer; }
                            }
                        }
                        int boostAmount = 0;
                        double boostPercent = 0.25;
                        int prayerLevel = MinescapeAddons.skills.get(SkillType.PRAYER).getLevel();
                        if (boostedRestore) boostPercent = 27;
                        if (displayName.contains("prayer potion")) boostAmount = 7;
                        if (displayName.contains("super restore"))  boostAmount = 8;
                        hudMenu.setPrayerRestoreAmount(boostAmount + (int)(prayerLevel * boostPercent));
                    }
                    for (Component tooltipLine : itemstack.getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL)) {
                        if (tooltipLine.getString().toLowerCase().contains("heals")) {
                            String[] parts = tooltipLine.getString().split(" ");
                            if (parts.length > 1) {
                                try { hudMenu.setHealAmount(Integer.parseInt(parts[1])); }
                                catch (NumberFormatException ex) { hudMenu.setHealAmount(0); }
                            }
                        }
                    }
                }
            }
        }
        if (isHighlighted && Config.getSlotHighlight()) {
            for (int i : invWidget.INVENTORY_SLOT_ORDER) {
                Slot slot = invScreen.menu.slots.get(i);
                int slotSize = (int)(16 * scale);
                e.getGuiGraphics().fill(slot.x, slot.y, slot.x + slotSize, slot.y + slotSize,
                        mc.options.getBackgroundColor(0.3f));
            }
        }
    }

    private void drawEquipmentFixed(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        int invScreenZeroX = 0;
        int invScreenZeroY = 0;
        EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        setupTab(equipmentWidget, e.getGuiGraphics(), equipmentWidget.INVENTORY, invScreenZeroX, invScreenZeroY);

        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;
        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

        int left = equipmentWidget.getLeftSide();
        int top  = equipmentWidget.getTop();
        invScreen.menu.slots.get(6).x  = left + 45;  invScreen.menu.slots.get(6).y  = top + 98;
        invScreen.menu.slots.get(7).x  = left + 45;  invScreen.menu.slots.get(7).y  = top + 142;
        invScreen.menu.slots.get(36).x = left + 45;  invScreen.menu.slots.get(36).y = top + 185;
        invScreen.menu.slots.get(18).x = left + 45;  invScreen.menu.slots.get(18).y = top + 229;
        invScreen.menu.slots.get(10).x = left + 191; invScreen.menu.slots.get(10).y = top + 98;
        invScreen.menu.slots.get(45).x = left + 191; invScreen.menu.slots.get(45).y = top + 142;
        invScreen.menu.slots.get(28).x = left + 191; invScreen.menu.slots.get(28).y = top + 185;
        invScreen.menu.slots.get(8).x  = left + 191; invScreen.menu.slots.get(8).y  = top + 229;
        invScreen.menu.slots.get(9).x  = left + 74;  invScreen.menu.slots.get(9).y  = top + 55;
        invScreen.menu.slots.get(5).x  = left + 119;  invScreen.menu.slots.get(5).y  = top + 55;
        invScreen.menu.slots.get(19).x = left + 163;  invScreen.menu.slots.get(19).y = top + 55;
    }

    private void drawEquipmentLarge(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        int invScreenZeroX = 0;
        int invScreenZeroY = 0;
        EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        setupTab(equipmentWidget, e.getGuiGraphics(), equipmentWidget.INVENTORY, invScreenZeroX, invScreenZeroY);

        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;
        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

        int left = equipmentWidget.getLeftSide();
        int top  = equipmentWidget.getTop();
        invScreen.menu.slots.get(6).x  = invScreenZeroX + left + 11;  invScreen.menu.slots.get(6).y  = invScreenZeroY + top + 40;
        invScreen.menu.slots.get(7).x  = invScreenZeroX + left + 11;  invScreen.menu.slots.get(7).y  = invScreenZeroY + top + 69;
        invScreen.menu.slots.get(36).x = invScreenZeroX + left + 11;  invScreen.menu.slots.get(36).y = invScreenZeroY + top + 98;
        invScreen.menu.slots.get(18).x = invScreenZeroX + left + 11;  invScreen.menu.slots.get(18).y = invScreenZeroY + top + 123;
        invScreen.menu.slots.get(10).x = invScreenZeroX + left + 106; invScreen.menu.slots.get(10).y = invScreenZeroY + top + 39;
        invScreen.menu.slots.get(45).x = invScreenZeroX + left + 106; invScreen.menu.slots.get(45).y = invScreenZeroY + top + 68;
        invScreen.menu.slots.get(28).x = invScreenZeroX + left + 106; invScreen.menu.slots.get(28).y = invScreenZeroY + top + 95;
        invScreen.menu.slots.get(8).x  = invScreenZeroX + left + 106; invScreen.menu.slots.get(8).y  = invScreenZeroY + top + 124;
        invScreen.menu.slots.get(9).x  = invScreenZeroX + left + 30;  invScreen.menu.slots.get(9).y  = invScreenZeroY + top + 12;
        invScreen.menu.slots.get(5).x  = invScreenZeroX + left + 59;  invScreen.menu.slots.get(5).y  = invScreenZeroY + top + 12;
        invScreen.menu.slots.get(19).x = invScreenZeroX + left + 88;  invScreen.menu.slots.get(19).y = invScreenZeroY + top + 12;
    }

    private void drawEquipmentSmall(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        int invScreenZeroX = 0;
        int invScreenZeroY = 0;
        EquipmentWidget equipmentWidget = HudMenuWidget.EQUIPMENT_WIDGET;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        setupTab(equipmentWidget, e.getGuiGraphics(), equipmentWidget.INVENTORY, invScreenZeroX, invScreenZeroY);

        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;
        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

        int left = equipmentWidget.getLeftSide();
        int top  = equipmentWidget.getTop();
        invScreen.menu.slots.get(6).x  = invScreenZeroX + left + 7;  invScreen.menu.slots.get(6).y  = invScreenZeroY + top + 28;
        invScreen.menu.slots.get(7).x  = invScreenZeroX + left + 7;  invScreen.menu.slots.get(7).y  = invScreenZeroY + top + 51;
        invScreen.menu.slots.get(36).x = invScreenZeroX + left + 7;  invScreen.menu.slots.get(36).y = invScreenZeroY + top + 72;
        invScreen.menu.slots.get(18).x = invScreenZeroX + left + 7;  invScreen.menu.slots.get(18).y = invScreenZeroY + top + 94;
        invScreen.menu.slots.get(10).x = invScreenZeroX + left + 83; invScreen.menu.slots.get(10).y = invScreenZeroY + top + 28;
        invScreen.menu.slots.get(45).x = invScreenZeroX + left + 83; invScreen.menu.slots.get(45).y = invScreenZeroY + top + 51;
        invScreen.menu.slots.get(28).x = invScreenZeroX + left + 83; invScreen.menu.slots.get(28).y = invScreenZeroY + top + 72;
        invScreen.menu.slots.get(8).x  = invScreenZeroX + left + 83; invScreen.menu.slots.get(8).y  = invScreenZeroY + top + 94;
        invScreen.menu.slots.get(9).x  = invScreenZeroX + left + 22; invScreen.menu.slots.get(9).y  = invScreenZeroY + top + 8;
        invScreen.menu.slots.get(5).x  = invScreenZeroX + left + 44; invScreen.menu.slots.get(5).y  = invScreenZeroY + top + 8;
        invScreen.menu.slots.get(19).x = invScreenZeroX + left + 67; invScreen.menu.slots.get(19).y = invScreenZeroY + top + 8;
    }

    private void drawCombatFixed(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        int invScreenZeroX = 0;
        int invScreenZeroY = 0;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;

        CombatWidget combatWidget = HudMenuWidget.COMBAT_WIDGET;
        setupTab(combatWidget, e.getGuiGraphics(), combatWidget.BACKGROUND, invScreenZeroX, invScreenZeroY);

        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;

        invScreen.menu.slots.get(27).x = combatWidget.getAnchorX() + 120;
        invScreen.menu.slots.get(27).y = combatWidget.getAnchorY() + 163;
    }

    private void drawCombatLarge(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        int invScreenZeroX = 0;
        int invScreenZeroY = 0;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;

        CombatWidget combatWidget = HudMenuWidget.COMBAT_WIDGET;
        setupTab(combatWidget, e.getGuiGraphics(), combatWidget.BACKGROUND, invScreenZeroX, invScreenZeroY);

        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;

        invScreen.menu.slots.get(27).x = invScreenZeroX + combatWidget.getAnchorX() + 58;
        invScreen.menu.slots.get(27).y = invScreenZeroY + combatWidget.getAnchorY() + 85;
    }

    private void drawCombatSmall(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        int invScreenZeroX = 0;
        int invScreenZeroY = 0;
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;

        CombatWidget combatWidget = HudMenuWidget.COMBAT_WIDGET;
        setupTab(combatWidget, e.getGuiGraphics(), combatWidget.BACKGROUND, invScreenZeroX, invScreenZeroY);

        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;

        invScreen.menu.slots.get(27).x = invScreenZeroX + combatWidget.getAnchorX() + 45;
        invScreen.menu.slots.get(27).y = invScreenZeroY + combatWidget.getAnchorY() + 64;
    }

    private void setupSkillsTab(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;


        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;
    }

    private void drawSkillsSmall(ScreenEvent.Render.Post e, ZylrInventoryScreen invScreen, HudMenuWidget hudMenu) {
        float scale = MinescapeAddons.getInstance().resizableClassic.getHudMenu().scale;
        int offScreen = Minecraft.getInstance().getWindow().getGuiScaledWidth() + 500;

        for (int i : HudMenuWidget.INVENTORY_WIDGET.INVENTORY_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        for (int i : EquipmentWidget.EQUIPEMENT_SLOT_ORDER)
            invScreen.menu.slots.get(i).x = offScreen;
        invScreen.menu.slots.get(27).x = offScreen;

    }
}
