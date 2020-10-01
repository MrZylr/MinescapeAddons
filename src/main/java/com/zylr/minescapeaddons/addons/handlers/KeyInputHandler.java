package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.screens.HudEditScreen;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimerCompletedOnLogin;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.screens.ConfigBuilder;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.screens.vanilla.ModVanillaInventoryScreen;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.io.IOException;

public class KeyInputHandler {
    @SubscribeEvent
    public void keyInputEvent(InputEvent.KeyInputEvent e) throws IOException {
        // TEST
        if (!ModConfiguration.CLIENT.debugOff.get()) {
            if (e.getKey() == GLFW.GLFW_KEY_J) {
                int x = Main.getInstance().getRsHud().getWidgets().get(0).getAnchorX();
                int y = Main.getInstance().getRsHud().getWidgets().get(0).getAnchorY();

                System.out.println(x +", "+ y);
                System.out.println(Minecraft.getInstance().getMainWindow().getScaledWidth() +", "+ Minecraft.getInstance().getMainWindow().getScaledHeight());
            }
            if (e.getKey() == GLFW.GLFW_KEY_H) {
            }
            if (e.getKey() == GLFW.GLFW_KEY_G) {
            }
        }

        // Key held down give mouse control
        if (e.getKey() == Main.heldInventoryKey.getKey().getKeyCode()) {
            if (e.getAction() == 0) {
                if (Minecraft.getInstance().currentScreen instanceof ModVanillaInventoryScreen
                || Minecraft.getInstance().currentScreen instanceof ContainerScreen) {
                    Minecraft.getInstance().player.closeScreen();
                }
            } else if (e.getAction() == 1) {
                if (Minecraft.getInstance().currentScreen instanceof RunescapeInventoryScreen)
                    return;
                if (Minecraft.getInstance().currentScreen == null) {
                    if (ModConfiguration.CLIENT.rsInventory.get())
                        Minecraft.getInstance().displayGuiScreen(new RunescapeInventoryScreen(Minecraft.getInstance().player));
                    else
                        Minecraft.getInstance().displayGuiScreen(new ModVanillaInventoryScreen(Minecraft.getInstance().player));
                }
            }
        }

        // Close setting menu if open
        if (e.getKey() == Main.settingsKey.getKey().getKeyCode()) {
            if (e.getAction() == 1)
                return;
            if (Minecraft.getInstance().currentScreen instanceof ConfigBuilder) {
                Minecraft.getInstance().player.closeScreen();
                return;
            }
        }
        // Open settings menu
        if (Minecraft.getInstance().currentScreen == null) {
            if (e.getKey() == Main.settingsKey.getKey().getKeyCode()) {
                if (e.getAction() == 1)
                    return;
                if (Minecraft.getInstance().currentScreen instanceof ConfigBuilder) {
                    Minecraft.getInstance().player.closeScreen();
                    return;
                }
                ConfigBuilder configBuilder = new ConfigBuilder();
                configBuilder.build();
            }
        }

        // Farming Menu
        // Close farming Menu
        if (e.getKey() == Main.farmTimersKey.getKey().getKeyCode()) {
            if (e.getAction() == 1)
                return;
            if (Minecraft.getInstance().currentScreen instanceof FarmingTimersScreen) {
                FarmingUtil.setAlertsAsChecked();
                Minecraft.getInstance().player.closeScreen();
                return;
            }
        }
        // Open farming menu
        if (Minecraft.getInstance().currentScreen == null) {
            if (e.getKey() == Main.farmTimersKey.getKey().getKeyCode()) {
                if (e.getAction() == 1)
                    return;
                if (Minecraft.getInstance().currentScreen instanceof FarmingTimersScreen) {
                    FarmingUtil.setAlertsAsChecked();
                    Minecraft.getInstance().player.closeScreen();
                    return;
                }
                Minecraft.getInstance().displayGuiScreen(new FarmingTimersScreen());
            }
        }
    }

    /*
    public void openStats() {
        Minecraft.getInstance().displayGuiScreen(new InventoryScreen(Minecraft.getInstance().player));
        Minecraft.getInstance().playerController.windowClick(Minecraft.getInstance().player.container.windowId, 14, 0, ClickType.PICKUP, Minecraft.getInstance().player);
        //Minecraft.getInstance().player.closeScreen();
    }
*/
}
