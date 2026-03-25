package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

@Listener
public class TooltipRenderListener {
    Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public void tooltipRender(RenderTooltipEvent.Pre e) {
        for (Component tooltipLine : e.getItemStack().getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL)) {
            if (tooltipLine.getString().toLowerCase().contains("heals") ||
            tooltipLine.getString().toLowerCase().contains("prayer potion") ||
            tooltipLine.getString().toLowerCase().contains("super restore")){
                if (Config.getRemoveFoodTooltip() && Minecraft.getInstance().screen instanceof ZylrInventoryScreen)
                    e.setCanceled(true);
            }
        }
    }
}
