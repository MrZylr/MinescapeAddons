package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.*;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.screens.BrowserScreen;
import com.zylr.minescapeaddons.addons.gui.screens.FarmingTimersScreen;
import com.zylr.minescapeaddons.addons.gui.screens.HudEditScreen;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingLocations;
import com.zylr.minescapeaddons.addons.skills.farming.FarmingUtil;
import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.lwjgl.glfw.GLFW;

@Listener
public class InputListener {
    public static float yRotation = 0;
    public static double mouseX = 0;
    public static double mouseY = 0;
    public static boolean isCtrlPressed = false;

    @SubscribeEvent
    public void onInput(InputEvent.Key e) {
        Minecraft mc = Minecraft.getInstance();
        MinescapeAddons.getInstance().player.resetAfkTimer();
        /*
        if (e.getKey() == GLFW.GLFW_KEY_GRAVE_ACCENT) {
            if (MinescapeAddons.getInstance().getThurgo() != null) {
                MinescapeAddons.getInstance().getThurgo().timer = new Timer();
                MinescapeAddons.getInstance().getThurgo().timer.startTimer();
            }else
                MinescapeAddons.getInstance().setThurgo(new RenderThurgo());
        }

         */

        if (Minecraft.getInstance().screen instanceof ZylrInventoryScreen) {
            if (e.getKey() == GLFW.GLFW_KEY_LEFT_CONTROL || e.getKey() == GLFW.GLFW_KEY_RIGHT_CONTROL) {
                if (e.getAction() == GLFW.GLFW_PRESS)
                    isCtrlPressed = true;
            }
        }
        if (e.getAction() == GLFW.GLFW_RELEASE)
            isCtrlPressed = false;

        // Code below this point only responds to key press, not release
        if (e.getAction() != GLFW.GLFW_PRESS) return;
        /*-------------------------------------------------------------*/

        if (e.getKey() == GLFW.GLFW_KEY_BACKSLASH) {
            Minecraft.getInstance().options.keySwapOffhand.consumeClick();
        }

        // Close inventory screen if new inventory key is pressed
        if (e.getKey() == Controls.NEW_INVENTORY_KEY.getKey().getValue()) {
            if (Minecraft.getInstance().screen instanceof ContainerScreen containerScreen)
                containerScreen.onClose();
            if (Minecraft.getInstance().screen instanceof InventoryScreen inventoryScreen)
                inventoryScreen.onClose();
        }
    }

    @SubscribeEvent
    public void onMouseScroll(ScreenEvent.MouseScrolled.Post e) {
        MinescapeAddons.getInstance().player.resetAfkTimer();

        if (e.getScreen() instanceof ChatScreen chatScreen) {
            MinescapeAddons.getInstance().resizableClassic.getChatWidget().mouseScrolled(e.getMouseX(), e.getMouseY(), e.getScrollDeltaY());
        }
    }


    @SubscribeEvent
    public void onMouseClick(InputEvent.MouseButton.Post event) {
        MinescapeAddons.getInstance().player.resetAfkTimer();
    }

    @SubscribeEvent
    public void onTick(ClientTickEvent.Post e) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.yHeadRot != yRotation) {
            yRotation = Minecraft.getInstance().player.yHeadRot;
            MinescapeAddons.getInstance().player.resetAfkTimer();
        }
        if (Minecraft.getInstance().mouseHandler.xpos != mouseX || Minecraft.getInstance().mouseHandler.ypos != mouseY) {
            mouseX = Minecraft.getInstance().mouseHandler.xpos;
            mouseY = Minecraft.getInstance().mouseHandler.ypos;
            MinescapeAddons.getInstance().player.resetAfkTimer();
        }
    }
}
