package com.zylr.minescapeaddons.addons;

import com.mojang.blaze3d.platform.InputConstants;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.screens.BrowserScreen;
import com.zylr.minescapeaddons.addons.gui.screens.SettingsScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;

@Listener
public class KeyBindings {
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post e) {

        while (Controls.NEW_INVENTORY_KEY.consumeClick()) {
            if (Minecraft.getInstance().player.isCreative())
                Minecraft.getInstance().setScreen(new InventoryScreen(Minecraft.getInstance().player));
            else
                Minecraft.getInstance().setScreen(new ZylrInventoryScreen(Minecraft.getInstance().player, false, false, ""));

        }
        while (Controls.SETTINGS_KEY.consumeClick()) {
            Minecraft.getInstance().setScreen(new SettingsScreen());
        }
        while (Controls.BROWSER_KEY.consumeClick()) {
            if (Config.getEnableBrowser())
                Minecraft.getInstance().setScreen(new BrowserScreen());
        }
    }

}
