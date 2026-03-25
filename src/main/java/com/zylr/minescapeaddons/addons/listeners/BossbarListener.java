package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;

import java.util.ArrayList;
import java.util.List;

public class BossbarListener {
    @SubscribeEvent
    public void onBossBarRender(CustomizeGuiOverlayEvent.BossEventProgress e) {
        for (String s : MinescapeAddons.skillsSymbolList) {
            if (e.getBossEvent().getName().getString().contains(s))
                e.setCanceled(true);
        }


        if (Config.getRemoveBranding()) {
            //System.out.println(e.getBossEvent().getName().getString());
            //e.setCanceled(true);
            String bossBarName = e.getBossEvent().getName().getString();
            List<String> brandingList = new ArrayList<>();
            brandingList.add("Members now have access to the Test Server! store.minescape.com");
            brandingList.add("Purchase Ranks, Treasure Chests & Cosmetics! store.minescape.com");
            brandingList.add("Purchase MineScape Merch on our new Merch Store! merch.minescape.com");

            if (brandingList.contains(bossBarName))
                e.setCanceled(true);
        }
    }
}
