package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.builders.InventoryBuilder;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ContainerCloseHandler {
    @SubscribeEvent
    public void onPlayerContainerCloseEvent(PlayerContainerEvent.Close e) {
        Minecraft mc = Minecraft.getInstance();
        InventoryBuilder inv = Main.getInstance().getInv();
        if (ModConfiguration.CLIENT.persistentMouse.get()) {
            if (mc.currentScreen instanceof RunescapeInventoryScreen) {
                inv.setMouseX((int) mc.mouseHelper.getMouseX());
                inv.setMouseY((int) mc.mouseHelper.getMouseY());
            }
        }else
            inv.resetMouse();
    }
}
