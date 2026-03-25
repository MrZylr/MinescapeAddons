package com.zylr.minescapeaddons.addons;

import com.zylr.minescapeaddons.addons.properties.MainProperties;
import net.minecraft.client.Minecraft;

import java.util.Properties;

public class Config {
    public static double getScale() {
        Properties p = MainProperties.getConfig();
        return Double.parseDouble(p.getProperty("scale", "1.0"));
    }
    public static void setScale(double value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("scale", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getAgilityOutlines() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("agility_outlines", "false"));
    }
    public static void setAgilityOutlines(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("agility_outlines", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getRightClickMenu() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("right_click_menu", "true"));
    }
    public static void setRightClickMenu(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("right_click_menu", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getXpTracker() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("xp_tracker", "true"));
    }
    public static void setXpTracker(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("xp_tracker", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getScoreboard() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("scoreboard", "true"));
    }
    public static void setScoreboard(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("scoreboard", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getArmorOverride() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("armor_override", "true"));
    }
    public static void setArmorOverride(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("armor_override", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getHpPrayerBars() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("hp_prayer_bars", "false"));
    }
    public static void setHpPrayerBars(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("hp_prayer_bars", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getRemoveBranding() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("remove_branding", "false"));
    }
    public static void setRemoveBranding(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("remove_branding", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getRemoveFoodTooltip() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("remove_chat_branding", "false"));
    }
    public static void setRemoveFoodTooltip(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("remove_chat_branding", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getMobHighlight() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("mob_highlight", "false"));
    }
    public static void setMobHighlight(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("mob_highlight", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getForceResourcePack() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("force_resource_pack", "true"));
    }
    public static void setForceResourcePack(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("force_resource_pack", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getSlotHighlight() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("slot_highlight", "true"));
    }
    public static void setSlotHighlight(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("slot_highlight", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getEnableBrowser() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("enable_browser", "false"));
    }
    public static void setEnableBrowser(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("enable_browser", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getSmallInventory() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("small_inventory", "false"));
    }
    public static void setSmallInventory(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("small_inventory", String.valueOf(value));
        MainProperties.saveConfig(p);

        MainProperties.saveConfig(p);
        Minecraft.getInstance().getResourcePackRepository().reload();
        Minecraft.getInstance().reloadResourcePacks();
    }

    public static boolean getVignette() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("vignette", "true"));
    }
    public static void setVignette(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("vignette", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getVirtualLevels() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("virtual_levels", "false"));
    }
    public static void setVirtualLevels(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("virtual_levels", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getCustomCapes(){
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("custom_capes", "true"));
    }
    public static void setCustomCapes(boolean value){
        Properties p = MainProperties.getConfig();
        p.setProperty("custom_capes", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getReduceCrops() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("reduce_crops", "false"));
    }
    public static void setReduceCrops(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("reduce_crops", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getBarrowsHelper() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("barrows_helper", "true"));
    }
    public static void setBarrowsHelper(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("barrows_helper", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getTargetInfo() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("target_info", "true"));
    }
    public static void setTargetInfo(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("target_info", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getCustomChat() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("custom_chat", "true"));
    }
    public static void setCustomChat(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("custom_chat", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getDarkChatMode() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("dark_chat_mode", "true"));
    }
    public static void setDarkChatMode(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("dark_chat_mode", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getCloseChatOnEnter() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("close_chat_on_enter", "true"));
    }
    public static void setCloseChatOnEnter(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("close_chat_on_enter", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getCustomHotbar() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("custom_hotbar", "true"));
    }
    public static void setCustomHotbar(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("custom_hotbar", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getMinimap() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("minimap", "true"));
    }
    public  static void setMinimap(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("minimap", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getMinimapRotation() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("minimap_rotation", "true"));
    }
    public static void setMinimapRotation(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("minimap_rotation", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getClassicMinimapFrame() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("classic_minimap_frame", "false"));
    }
    public static void setClassicMinimapFrame(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("classic_minimap_frame", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getMapCoords() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("map_coords", "true"));
    }
    public static void setMapCoords(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("map_coords", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getBankNumbers() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("bank_numbers", "true"));
    }
    public static void setBankNumbers(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("bank_numbers", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getZylrParticles() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("zylr_particles", "true"));
    }
    public static void setZylrParticles(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("zylr_particles", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getSprint() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("sprint", "true"));
    }
    public static void setSprint(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("sprint", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getAfkTimer() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("afk_timer", "true"));

    }
    public static void setAfkTimer(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("afk_timer", String.valueOf(value));
        MainProperties.saveConfig(p);
    }

    public static boolean getFixedMode() {
        Properties p = MainProperties.getConfig();
        return Boolean.parseBoolean(p.getProperty("fixed_mode", "false"));
    }
    public static void setFixedMode(boolean value) {
        Properties p = MainProperties.getConfig();
        p.setProperty("fixed_mode", String.valueOf(value));

        MainProperties.saveConfig(p);
        Minecraft.getInstance().getResourcePackRepository().reload();
        Minecraft.getInstance().reloadResourcePacks();
    }
}
