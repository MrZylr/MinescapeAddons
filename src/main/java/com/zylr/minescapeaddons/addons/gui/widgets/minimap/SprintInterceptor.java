package com.zylr.minescapeaddons.addons.gui.widgets.minimap;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import java.text.CompactNumberFormat;

@Listener
public class SprintInterceptor {

    @SubscribeEvent
    private void onClientTick(ClientTickEvent.Pre event) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null || client.options == null)
            return;

        if (!Config.getSprint()) {
            // Clear sprint key state so pressing it has no effect
            KeyMapping sprintKey = client.options.keySprint;
            sprintKey.setDown(false);

            // Force-disable sprint each tick to block sprinting by any method (including double-tap forward)
            if (client.player.isSprinting()) {
                client.player.setSprinting(false);
            }
        }
    }
}
