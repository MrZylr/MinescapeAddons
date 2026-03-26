package com.zylr.minescapeaddons.addons.gui.widgets.chat;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@Listener
public class ClientChatInterceptor {

    @SubscribeEvent
    private void onClientTick(ClientTickEvent.Pre event) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null || client.options == null)
            return;

        KeyMapping chatKey = client.options.keyChat;
        KeyMapping commandKey = client.options.keyCommand;
        if (!client.gameMode.hasInfiniteItems()) {
            if (chatKey.consumeClick()) {
                if (client.screen == null) {
                    client.setScreen(new ZylrInventoryScreen(client.player, true, false, ""));
                }
            }
            if (commandKey.consumeClick()) {
                client.setScreen(new ZylrInventoryScreen(client.player, true, true, "/"));
            }
        }
    }
}