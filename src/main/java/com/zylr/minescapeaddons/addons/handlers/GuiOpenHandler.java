package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.ModConfiguration;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.screens.vanilla.ModVanillaInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GuiOpenHandler {
    @SubscribeEvent
    public void guiOpen(GuiOpenEvent e){
        Minecraft mc = Minecraft.getInstance();
        if (e.getGui() instanceof InventoryScreen) {
            if (ModConfiguration.CLIENT.rsInventory.get()) {
                RunescapeInventoryScreen rs = new RunescapeInventoryScreen(Minecraft.getInstance().player);
                e.setGui(rs);
                if (mc.currentScreen instanceof RunescapeInventoryScreen)
                    ((RunescapeInventoryScreen)mc.currentScreen).setMousePosition();
            }else {
                ModVanillaInventoryScreen inv = new ModVanillaInventoryScreen(Minecraft.getInstance().player);
                e.setGui(inv);
            }
        }
    }
}