package com.zylr.minescapeaddons.addons.handlers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.screens.ChatScreen;
import com.zylr.minescapeaddons.addons.gui.screens.farming.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.screens.ConfigBuilder;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import com.zylr.minescapeaddons.addons.config.BooleanOption;
import com.zylr.minescapeaddons.addons.gui.screens.settings.MainSettings;
import com.zylr.minescapeaddons.addons.gui.screens.vanilla.ModVanillaInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.io.IOException;

public class KeyInputHandler {
    @SubscribeEvent
    public void keyInputEvent(InputEvent.KeyInputEvent e) {
        Minecraft mc = Minecraft.getInstance();
        // TEST
        if (!ModConfiguration.CLIENT.debugOff.get()) {
            if (e.getKey() == GLFW.GLFW_KEY_J) {
                if (e.getAction() == 1)
                    return;/*
                Minecraft.getInstance().player.sendChatMessage("/scoreboard");

                Minecraft.getInstance().player.sendChatMessage(
                        "/chat line 1 <clan>|<region>|<online>|"
                );
                Minecraft.getInstance().player.sendChatMessage(
                        "/chat line 2 <health>|<prayer_points>|<slayer_task_current>|<slayer_task_mob>|<slayer_task_remaining>|<slayer_task_required>"
                );
                Minecraft.getInstance().player.sendChatMessage("/chat close");
                Minecraft.getInstance().ingameGUI.getChatGUI().render(0);*/
            }

            if (e.getKey() == GLFW.GLFW_KEY_H) {
                if (e.getAction() == 1)
                    return;
            }

            if (e.getKey() == GLFW.GLFW_KEY_G) {
                if (e.getAction() == 1)
                    return;
                System.out.println(mc.gameSettings.keyBindChat.getKey().getKeyCode());
            }

            if (e.getKey() == GLFW.GLFW_KEY_KP_ENTER) {
                System.out.println("keypad enter pressed");
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

        // Open chat
        if (ModConfiguration.CLIENT.osrsChat.get()) {
            if (Minecraft.getInstance().currentScreen == null) {
                if (e.getKey() == Main.chatKey.getKey().getKeyCode() || e.getKey() == Main.altChatKey.getKey().getKeyCode()) {
                    if (e.getAction() == 1)
                        return;
                    mc.displayGuiScreen(new ChatScreen(Main.getInstance().getRsHud(), false));
                }
                if (e.getKey() == Main.commandKey.getKey().getKeyCode()) {
                    if (e.getAction() == 1)
                        return;
                    mc.displayGuiScreen(new ChatScreen(Main.getInstance().getRsHud(), true));
                }
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
