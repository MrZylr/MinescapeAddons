package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.item.OverrideArmors;
import com.zylr.minescapeaddons.addons.item.OverrideArmorsSlim;
import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;


@Listener
public class PlayerRendererListener {
    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Pre e) {
        if (e.getEntity() instanceof AbstractClientPlayer player) {

            // Override Armor Textures
            if (Config.getArmorOverride()) {
                // Check if player has slim model (Alex) or classic model (Steve)
                // Alex models have thinner arms (3 pixels wide vs 4 pixels for Steve)
                // Access the model's arm dimensions to determine model type
                boolean isSlimModel = false;
                isSlimModel = player.getSkin().model().name().equals("SLIM");

                String chestplateName = e.getEntity().containerMenu.slots.get(6).getItem().getDisplayName().getString().substring(1,
                        e.getEntity().containerMenu.slots.get(6).getItem().getDisplayName().getString().length() - 1);
                String legsName = e.getEntity().containerMenu.slots.get(7).getItem().getDisplayName().getString().substring(1,
                        e.getEntity().containerMenu.slots.get(7).getItem().getDisplayName().getString().length() - 1);
                String bootsName = e.getEntity().containerMenu.slots.get(8).getItem().getDisplayName().getString().substring(1,
                        e.getEntity().containerMenu.slots.get(8).getItem().getDisplayName().getString().length() - 1);


                // Override Chestplate Armors
                if (isSlimModel) {
                    // Player has Alex model (slim arms)
                    for (OverrideArmorsSlim value : OverrideArmorsSlim.values()) {
                        if (chestplateName.equalsIgnoreCase(value.name)) {
                            ItemStack itemStack = value.item.toStack();
                            ItemStack currentStack = e.getEntity().containerMenu.slots.get(6).getItem();
                            itemStack.applyComponents(currentStack.getComponents());
                            e.getEntity().containerMenu.slots.get(6).set(itemStack);
                        }
                    }
                } else {
                    // Player has Steve model (regular arms)
                    for (OverrideArmors value : OverrideArmors.values()) {
                        if (chestplateName.equalsIgnoreCase(value.name)) {
                            ItemStack itemStack = value.item.toStack();
                            ItemStack currentStack = e.getEntity().containerMenu.slots.get(6).getItem();
                            itemStack.applyComponents(currentStack.getComponents());
                            e.getEntity().containerMenu.slots.get(6).set(itemStack);
                        }
                    }
                }
                // Override Legs Armors
                for (OverrideArmors value : OverrideArmors.values()) {
                    if (legsName.equalsIgnoreCase(value.name)) {
                        ItemStack itemStack = value.item.toStack();
                        ItemStack currentStack = e.getEntity().containerMenu.slots.get(7).getItem();
                        itemStack.applyComponents(currentStack.getComponents());
                        e.getEntity().containerMenu.slots.get(7).set(itemStack);
                    }
                }
                // Override Boots Armors
                for (OverrideArmors value : OverrideArmors.values()) {
                    if (bootsName.equalsIgnoreCase(value.name)) {
                        ItemStack itemStack = value.item.toStack();
                        ItemStack currentStack = e.getEntity().containerMenu.slots.get(8).getItem();
                        itemStack.applyComponents(currentStack.getComponents());
                        e.getEntity().containerMenu.slots.get(8).set(itemStack);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
    }
}
