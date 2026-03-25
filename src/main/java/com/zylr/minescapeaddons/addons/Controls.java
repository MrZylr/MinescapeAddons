package com.zylr.minescapeaddons.addons;

import com.mojang.blaze3d.platform.InputConstants;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = MinescapeAddons.MOD_ID)
public class Controls {
    public static final KeyMapping NEW_INVENTORY_KEY = new KeyMapping ("msaddon.controls.new_inventory",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_E,
            "msaddons.controls.category");
    public static final KeyMapping SETTINGS_KEY = new KeyMapping ("msaddon.controls.settings",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "msaddons.controls.category");
    public static final KeyMapping BROWSER_KEY = new KeyMapping ("msaddon.controls.browser",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_BACKSLASH,
            "msaddons.controls.category");

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent e) {
        e.register(NEW_INVENTORY_KEY);
        e.register(SETTINGS_KEY);
        e.register(BROWSER_KEY);
    }
}
