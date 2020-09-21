package com.zylr.minescapeaddons.addons.handlers;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.screens.runescape.RunescapeInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class GuiContainerHandler {
    @SubscribeEvent
    public void openGui(GuiContainerEvent e) {
        if (e.getGuiContainer().getContainer().windowId != Minecraft.getInstance().player.container.windowId) {
            Main.getInstance().getIdleChecker().setChestOpen(true);
        }
        if (Minecraft.getInstance().currentScreen instanceof RunescapeInventoryScreen){

        }

        // Decide if container title is Skills
        String title;
        if (e.getGuiContainer().getTitle().toString() != null) {
            title = e.getGuiContainer().getTitle().getFormattedText();
            if (title.contains("s")) {
                title = title.toLowerCase();
                title = title.substring(title.indexOf("s"), title.lastIndexOf("s")+1);
            }
        }else
            title = "";

        if (title.equalsIgnoreCase("skills")) {

            // Get the list of items from container to sort for skills
            List<ItemStack> inv = e.getGuiContainer().getContainer().getInventory();
            if (!inv.get(41).getDisplayName().getFormattedText().equalsIgnoreCase("air"))
                Main.getInstance().getStatsPanel().updateStats(inv);
        }
    }
}
