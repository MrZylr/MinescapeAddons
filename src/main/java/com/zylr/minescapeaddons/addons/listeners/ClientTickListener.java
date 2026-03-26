package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;

@Listener
public class ClientTickListener {

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre e) {
        if (!Config.getFixedMode()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.getWindow() == null) return;

        long handle = mc.getWindow().getWindow();
        if (handle == 0) return;

        if (Config.getSmallInventory()) {
            if (mc.options.guiScale().get() != 1) {
                mc.options.guiScale().set(1);
            }
        } else if (mc.options.guiScale().get() != 2) {
            mc.options.guiScale().set(2);
        }
        int width = 1640;
        int height = 1036;
        //822x518
        if (Config.getSmallInventory()) {
            width = 822;
            height = 518;
        }

        if (mc.getWindow().getWidth() != width || mc.getWindow().getHeight() != height) {
            //System.out.println(mc.getWindow().getWidth() + "x" + mc.getWindow().getHeight());
            GLFW.glfwSetWindowSize(handle, width, height);
            mc.getWindow().setWidth(width);
            mc.getWindow().setHeight(height);
        }
    }
}
