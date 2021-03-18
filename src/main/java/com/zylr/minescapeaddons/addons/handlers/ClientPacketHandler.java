package com.zylr.minescapeaddons.addons.handlers;

import com.google.common.eventbus.Subscribe;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;

public class ClientPacketHandler {
    @Subscribe
    public void onClientConnectToServer(ClientPlayerNetworkEvent.LoggedInEvent e) {
    }
}
