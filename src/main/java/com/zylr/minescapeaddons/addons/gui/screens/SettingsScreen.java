package com.zylr.minescapeaddons.addons.gui.screens;

import com.mojang.blaze3d.platform.InputConstants;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.Controls;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.BrowserWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsScreen extends Screen {
    private Minecraft mc = Minecraft.getInstance();
    private double scrollOffset = 0;
    private final int DEFAULT_CONTENT_WIDTH = 600;
    private int contentWidth = DEFAULT_CONTENT_WIDTH;
    private int contentStartX;
    private int headerHeight = 60;

    private List<SettingCategory> categories = new ArrayList<>();

    public SettingsScreen() {
        super(Component.literal("Minescape Addons Settings"));
        initializeCategories();
    }

    private void initializeCategories() {
        // UI Category
        SettingCategory uiCategory = new SettingCategory("Interface & Display");
        uiCategory.addSetting(new SettingEntry("Scoreboard", "Toggle scoreboard. Also check Minescape settings",
                Config::getScoreboard, Config::setScoreboard));
        uiCategory.addSetting(new SettingEntry("HP/Prayer Bars", "Render bars on either side of the inventory",
                Config::getHpPrayerBars, Config::setHpPrayerBars));
        uiCategory.addSetting(new SettingEntry("Compact Mode", "Compact Inventory. Also try scaling in the EditHud",
                Config::getSmallInventory, Config::setSmallInventory));
        uiCategory.addSetting(new SettingEntry("Remove Branding", null,
                Config::getRemoveBranding, Config::setRemoveBranding));
        uiCategory.addSetting(new SettingEntry("Custom Chat", null,
                Config::getCustomChat, Config::setCustomChat));
        uiCategory.addSetting(new SettingEntry("Dark Mode Custom Chat", "Make the custom chat background darker",
                Config::getDarkChatMode, Config::setDarkChatMode));
        uiCategory.addSetting(new SettingEntry("Close Chat After Send", null,
                Config::getCloseChatOnEnter, Config::setCloseChatOnEnter));
        uiCategory.addSetting(new SettingEntry("Toggle item numbers in the bank", null,
                Config::getBankNumbers, Config::setBankNumbers));
        uiCategory.addSetting(new SettingEntry("Resizeable/Fixed Mode", "IMPORTANT: If disabling, you may need to adjust your GUI scaling. Works with Compact Mode.",
                Config::getFixedMode, Config::setFixedMode));
        categories.add(uiCategory);

        // Map Catgory
        SettingCategory mapCategory = new SettingCategory("Minimap Map Features");
        mapCategory.addSetting(new SettingEntry("Minimap", "Render Minimap",
                Config::getMinimap, Config::setMinimap));
        mapCategory.addSetting(new SettingEntry("Minimap Rotates", "Toggle minimap rotation",
                Config::getMinimapRotation, Config::setMinimapRotation));
        mapCategory.addSetting(new SettingEntry("Classic Frame", "Use a more classic style minimap frame",
                Config::getClassicMinimapFrame, Config::setClassicMinimapFrame));
        mapCategory.addSetting(new SettingEntry("Show Coords", "Show coordinates on the minimap",
                Config::getMapCoords, Config::setMapCoords));
        categories.add(mapCategory);

        // Gameplay Category
        SettingCategory gameplayCategory = new SettingCategory("Gameplay Features");
        gameplayCategory.addSetting(new SettingEntry("Right Click Menus", "Hold CTRL for temp override",
                Config::getRightClickMenu, Config::setRightClickMenu));
        gameplayCategory.addSetting(new SettingEntry("XP Tracker", null,
                Config::getXpTracker, Config::setXpTracker));
        gameplayCategory.addSetting(new SettingEntry("Virtual Levels", "Requires relogging",
                Config::getVirtualLevels, Config::setVirtualLevels));
        gameplayCategory.addSetting(new SettingEntry("Barrows Helper", "Show Barrows brother status",
                Config::getBarrowsHelper, Config::setBarrowsHelper));
        gameplayCategory.addSetting(new SettingEntry("AFK Timer", "Pause XP trackers when AFK",
                Config::getAfkTimer, Config::setAfkTimer));
        categories.add(gameplayCategory);

        // Visual Enhancements Category
        SettingCategory visualCategory = new SettingCategory("Visual Enhancements");
        visualCategory.addSetting(new SettingEntry("Agility Outlines", "Highlight obstacles you can interact with",
                Config::getAgilityOutlines, Config::setAgilityOutlines));
        visualCategory.addSetting(new SettingEntry("Mob Highlight", "Highlight NPC hitboxes that you can attack",
                Config::getMobHighlight, Config::setMobHighlight));
        visualCategory.addSetting(new SettingEntry("Target Info", "Show target info above NPCs",
                Config::getTargetInfo, Config::setTargetInfo));
        visualCategory.addSetting(new SettingEntry("Slot Highlight", "Highlight inventory slots when item is selected",
                Config::getSlotHighlight, Config::setSlotHighlight));
        visualCategory.addSetting(new SettingEntry("Low Health Vignette", "When below 20% health",
                Config::getVignette, Config::setVignette));
        visualCategory.addSetting(new SettingEntry("Custom Capes", "May require relogging",
                Config::getCustomCapes, Config::setCustomCapes));
        visualCategory.addSetting(new SettingEntry("Move Hotbar", "Attach hotbar to inventory",
                Config::getCustomHotbar, Config::setCustomHotbar));
        visualCategory.addSetting(new SettingEntry("Zylr's Purple Particles", "Spawn portal particles around Zylr",
                Config::getZylrParticles, Config::setZylrParticles));
        categories.add(visualCategory);

        // Technical Category
        SettingCategory technicalCategory = new SettingCategory("Technical Settings");
        technicalCategory.addSetting(new SettingEntry("Armor Override", "Render custom armor models",
                Config::getArmorOverride, Config::setArmorOverride));
        technicalCategory.addSetting(new SettingEntry("Force Resource Pack", "Force resource pack order",
                Config::getForceResourcePack, Config::setForceResourcePack));
        technicalCategory.addSetting(new SettingEntry("Remove Food Tooltip", null,
                Config::getRemoveFoodTooltip, Config::setRemoveFoodTooltip));
        technicalCategory.addSetting(new SettingEntry("Enable Browser", "Open/Close with " + Controls.BROWSER_KEY.getKey().getDisplayName().getString() + " key",
                Config::getEnableBrowser, this::toggleBrowser));
        categories.add(technicalCategory);
    }

    private void toggleBrowser(boolean enabled) {
        Config.setEnableBrowser(enabled);
        if (!enabled && MinescapeAddons.getInstance().resizableClassic.getBrowserWidget() != null) {
            MinescapeAddons.getInstance().resizableClassic.getBrowserWidget().browser.close();
            MinescapeAddons.getInstance().resizableClassic.setBrowserWidget(null);
            MinescapeAddons.getInstance().resizableClassic.fillHudList();
        }
        if (enabled) {
            MinescapeAddons.getInstance().resizableClassic.setBrowserWidget(
                    new BrowserWidget(0, 0, 267, 150, "https://www.google.com"));
            MinescapeAddons.getInstance().resizableClassic.fillHudList();
            MinescapeAddons.getInstance().resizableClassic.getBrowserWidget().loadURL("https://www.google.com");
        }
    }

    @Override
    protected void init() {
        super.init();
        updateLayoutValues();
    }

    private void updateLayoutValues() {
        int margin = 10;
        int maxAvailable = Math.max(200, this.width - margin * 2);
        this.contentWidth = Math.min(DEFAULT_CONTENT_WIDTH, maxAvailable);
        this.contentStartX = Math.max(margin, (this.width - this.contentWidth) / 2);
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        // ensure layout matches current window size
        updateLayoutValues();

        super.render(gui, mouseX, mouseY, partialTick);
        renderBackground(gui, mouseX, mouseY, partialTick);

        int totalHeight = calculateTotalHeight();
        int maxScroll = Math.max(0, totalHeight - (this.height - headerHeight - 20));

        // Header
        gui.fill(0, 0, this.width, headerHeight, new Color(30, 30, 35, 240).getRGB());
        gui.drawCenteredString(mc.font, "Minescape Addons Settings", this.width / 2, 20, 0xFFFFFF);
        gui.drawCenteredString(mc.font, "Configure your addon preferences", this.width / 2, 35, 0xAAAAAA);
        gui.fill(0, headerHeight, this.width, headerHeight + 2, new Color(100, 100, 255, 200).getRGB());

        gui.enableScissor(0, headerHeight + 2, this.width, this.height - 10);

        int currentY = headerHeight + 20 - (int) scrollOffset;

        for (SettingCategory category : categories) {
            currentY = renderCategory(gui, category, currentY, mouseX, mouseY);
            currentY += 20;
        }

        gui.disableScissor();

        if (maxScroll > 0) {
            drawScrollBar(gui, maxScroll);
        }
    }

    private int renderCategory(GuiGraphics gui, SettingCategory category, int startY, int mouseX, int mouseY) {
        int currentY = startY;
        int categoryBoxX = contentStartX - 10;
        int categoryBoxWidth = contentWidth + 20;

        boolean headerHovered = mouseX >= categoryBoxX && mouseX <= categoryBoxX + categoryBoxWidth &&
                mouseY >= currentY && mouseY <= currentY + 25;

        // Category header background
        int headerColor = headerHovered ? new Color(50, 50, 60, 220).getRGB() : new Color(45, 45, 55, 200).getRGB();
        gui.fill(categoryBoxX, currentY, categoryBoxX + categoryBoxWidth, currentY + 25, headerColor);
        gui.fill(categoryBoxX, currentY, categoryBoxX + 4, currentY + 25,
                new Color(100, 100, 255, 255).getRGB());

        // Draw collapse/expand arrow
        String arrow = category.isExpanded ? "▼" : "▶";
        gui.drawString(mc.font, arrow, categoryBoxX + 10, currentY + 8, 0xFFFFFF);

        // Category name
        gui.drawString(mc.font, category.name, categoryBoxX + 25, currentY + 8, 0xFFFFFF);

        currentY += 35;

        // Render settings only if expanded
        if (category.isExpanded) {
            for (SettingEntry setting : category.settings) {
                currentY = renderSetting(gui, setting, currentY, mouseX, mouseY);
            }
        }

        return currentY;
    }

    private int renderSetting(GuiGraphics gui, SettingEntry setting, int startY, int mouseX, int mouseY) {
        int settingHeight = setting.description != null ? 45 : 35;
        int settingBoxX = contentStartX;
        int settingBoxWidth = contentWidth;

        boolean hovered = mouseX >= settingBoxX && mouseX <= settingBoxX + settingBoxWidth &&
                mouseY >= startY && mouseY <= startY + settingHeight;

        int bgColor = hovered ? new Color(55, 55, 65, 220).getRGB() : new Color(40, 40, 50, 180).getRGB();
        gui.fill(settingBoxX, startY, settingBoxX + settingBoxWidth, startY + settingHeight, bgColor);

        gui.drawString(mc.font, setting.name, settingBoxX + 10, startY + 8, 0xFFFFFF);

        if (setting.description != null) {
            gui.drawString(mc.font, setting.description, settingBoxX + 10, startY + 20, 0x999999);
        }

        int toggleX = settingBoxX + settingBoxWidth - 50;
        int toggleY = startY + (settingHeight - 20) / 2;
        boolean enabled = setting.getter.get();

        drawModernToggle(gui, toggleX, toggleY, enabled, hovered);

        return startY + settingHeight + 5;
    }

    private void drawModernToggle(GuiGraphics gui, int x, int y, boolean enabled, boolean hovered) {
        int width = 40;
        int height = 20;
        int sliderSize = 16;

        Color trackColor = enabled ? new Color(76, 175, 80, 255) : new Color(120, 120, 120, 255);
        if (hovered) {
            trackColor = new Color(trackColor.getRed(), trackColor.getGreen(), trackColor.getBlue(), 230);
        }

        gui.fill(x, y, x + width, y + height, trackColor.getRGB());

        int sliderX = enabled ? x + width - sliderSize - 2 : x + 2;
        gui.fill(sliderX, y + 2, sliderX + sliderSize, y + height - 2, Color.WHITE.getRGB());
        gui.fill(sliderX + 2, y + 4, sliderX + sliderSize - 2, y + height - 4,
                new Color(230, 230, 230).getRGB());
    }

    private void drawScrollBar(GuiGraphics gui, int maxScroll) {
        int scrollBarWidth = 4;
        int scrollBarX = this.width - scrollBarWidth - 5;
        int scrollBarY = headerHeight + 10;
        int scrollBarHeight = this.height - headerHeight - 30;

        gui.fill(scrollBarX, scrollBarY, scrollBarX + scrollBarWidth, scrollBarY + scrollBarHeight,
                new Color(50, 50, 50, 100).getRGB());

        double scrollPercentage = scrollOffset / maxScroll;
        int totalHeight = calculateTotalHeight();
        double visiblePercentage = (double) (this.height - headerHeight) / totalHeight;

        int thumbHeight = Math.max(20, (int) (scrollBarHeight * visiblePercentage));
        int thumbY = scrollBarY + (int) ((scrollBarHeight - thumbHeight) * scrollPercentage);

        gui.fill(scrollBarX, thumbY, scrollBarX + scrollBarWidth, thumbY + thumbHeight,
                new Color(100, 100, 255, 200).getRGB());
    }

    private int calculateTotalHeight() {
        int height = 20;
        for (SettingCategory category : categories) {
            height += 25;
            height += 35;
            if (category.isExpanded) {
                for (SettingEntry setting : category.settings) {
                    height += setting.description != null ? 50 : 40;
                }
            }
            height += 20;
        }
        return height;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int currentY = headerHeight + 20 - (int) scrollOffset;

            for (SettingCategory category : categories) {
                int categoryBoxX = contentStartX - 10;
                int categoryBoxWidth = contentWidth + 20;

                // Check if category header was clicked
                if (mouseX >= categoryBoxX && mouseX <= categoryBoxX + categoryBoxWidth &&
                        mouseY >= currentY && mouseY <= currentY + 25) {
                    category.isExpanded = !category.isExpanded;
                    return true;
                }

                currentY += 35; // Match the spacing in renderCategory

                // Only check settings if category is expanded
                if (category.isExpanded) {
                    for (SettingEntry setting : category.settings) {
                        int settingHeight = setting.description != null ? 45 : 35;
                        int settingBoxX = contentStartX;
                        int settingBoxWidth = contentWidth;

                        if (mouseX >= settingBoxX && mouseX <= settingBoxX + settingBoxWidth &&
                                mouseY >= currentY && mouseY <= currentY + settingHeight) {
                            setting.setter.accept(!setting.getter.get());
                            return true;
                        }

                        currentY += settingHeight + 5;
                    }
                }
                currentY += 20; // Space between categories
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int totalHeight = calculateTotalHeight();
        int maxScroll = Math.max(0, totalHeight - (this.height - headerHeight - 20));
        this.scrollOffset = Mth.clamp(this.scrollOffset - scrollY * 15, 0, maxScroll);
        return true;
    }

    @Override
    public boolean keyPressed(int key, int scancode, int mods) {
        if (Controls.SETTINGS_KEY.isActiveAndMatches(InputConstants.getKey(key, scancode))) {
            this.onClose();
            return true;
        }
        return super.keyPressed(key, scancode, mods);
    }

    private static class SettingCategory {
        String name;
        List<SettingEntry> settings = new ArrayList<>();
        boolean isExpanded = false;

        SettingCategory(String name) {
            this.name = name;
        }

        void addSetting(SettingEntry setting) {
            settings.add(setting);
        }
    }

    private static class SettingEntry {
        String name;
        String description;
        java.util.function.Supplier<Boolean> getter;
        java.util.function.Consumer<Boolean> setter;

        SettingEntry(String name, String description,
                     java.util.function.Supplier<Boolean> getter,
                     java.util.function.Consumer<Boolean> setter) {
            this.name = name;
            this.description = description;
            this.getter = getter;
            this.setter = setter;
        }
    }
}
