package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.Main;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldLoadHandler {
    @SubscribeEvent
    public void onWorldLoad(ClientPlayerNetworkEvent.LoggedOutEvent e) {
        System.out.println("Logged out. Pausing XpTracker.");
        Main.getInstance().getXpTrackerBuilder().pauseAllTimers();
    }
}
