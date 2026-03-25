package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import com.zylr.minescapeaddons.addons.skills.tracker.XpTracker;
import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Listener
public class ClientPlayerNetworkListener {
    Logger LOGGER = MinescapeAddons.LOGGER;

    @SubscribeEvent
    public void onConnect(ClientPlayerNetworkEvent.LoggingIn e) {
        List<String> ips = new ArrayList<>();
        ips.add("us.minescape.com/147.135.30.146:25565");
        ips.add("us.minescape.me/147.135.30.146:25565");
        ips.add("eu.minescape.com/130.162.52.137:25565");
        ips.add("eu.minescape.me/130.162.52.137:25565");
        ips.add("au.minescape.com/192.9.168.195:25565");
        ips.add("au.minescape.me/192.9.168.195:25565");

        // Clear minimap color caches when joining a server
        // Server may have a resource pack that changes block textures
        // Clear immediately
        ResourceReloadListener.clearCaches("server login (immediate)");

        // Also schedule a delayed clear to catch resource packs that load after login
        // This ensures we clear AFTER the server resource pack is applied
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Wait 3 seconds for resource pack to load
                ResourceReloadListener.clearCaches("server login (delayed)");
            } catch (InterruptedException ex) {
                // Ignore
            }
        }).start();
    }
    @SubscribeEvent
    public void onLogOut(ClientPlayerNetworkEvent.LoggingOut e) {
        System.out.println("ClientPlayerNetworkListener: Player is disconnecting from a server.");
        FarmingUtil.writeTimersToFile();
        XpTracker.pauseAllTrackers();
        LOGGER.info("Paused all XP trackers due to server disconnect.");
        if (MinescapeAddons.getInstance().resizableClassic.getBrowserWidget() != null)
            MinescapeAddons.getInstance().resizableClassic.getBrowserWidget().pauseAllMedia();
    }
    @SubscribeEvent
    public void onConnect(ClientPlayerNetworkEvent.Clone e) {
    }


    private boolean isHubLocation(BlockPos pos) {
        // Add your hub's specific spawn coordinates here
        return pos.getX() == 8 && pos.getY() == 65 && pos.getZ() == 8; // Example coordinates
    }
}
