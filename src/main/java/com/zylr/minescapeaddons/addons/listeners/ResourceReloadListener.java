package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.minimap.MinimapWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.slf4j.Logger;

/**
 * Listens for resource pack reloads and level changes to clear minimap color caches.
 * This ensures the minimap displays the correct colors from server resource packs.
 */
public class ResourceReloadListener implements ResourceManagerReloadListener {
    private static final Logger LOGGER = MinescapeAddons.LOGGER;
    private static long lastClearTime = 0;
    private static final long CLEAR_COOLDOWN = 1000; // Prevent clearing too frequently (1 second cooldown)

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        // Called when F3+T is pressed or resource packs are reloaded
        clearMinimapCachesWithCooldown("resource pack reload");
    }

    /**
     * Clears minimap caches with a cooldown to prevent excessive clearing
     */
    private static void clearMinimapCachesWithCooldown(String reason) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClearTime > CLEAR_COOLDOWN) {
            MinimapWidget.clearColorCaches();
            lastClearTime = currentTime;
            LOGGER.info("MinimapWidget: Cleared color caches due to {}", reason);
        }
    }

    /**
     * Event handler for level load events (world changes, dimension changes)
     */
    @SubscribeEvent
    public void onLevelLoad(LevelEvent.Load event) {
        // Only handle client-side level loads
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null && event.getLevel() == mc.level) {
            // Clear caches when entering a new world/dimension
            clearMinimapCachesWithCooldown("world/dimension change");
        }
    }

    /**
     * Static method to manually clear caches (called from other listeners)
     */
    public static void clearCaches(String reason) {
        clearMinimapCachesWithCooldown(reason);
    }
}

