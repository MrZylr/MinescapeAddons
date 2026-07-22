package com.zylr.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public final class ArmorStandListener {
    private static final List<Integer> TRIM_VALUES = List.of(0, 1, 2, 90, 97, 101, 102, 103);

    private ArmorStandListener() {
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(ArmorStandListener::removePassengerArmorTrims);
    }

    private static void removePassengerArmorTrims(Minecraft minecraft) {
        if (minecraft.player == null) {
            return;
        }

        for (Entity entity : minecraft.player.getPassengers()) {
            if (entity instanceof ArmorStand armorStand) {
                removeTrimItem(armorStand);
            }
        }
    }

    private static void removeTrimItem(ArmorStand armorStand) {
        ItemStack mainHandItem = armorStand.getMainHandItem();
        if (mainHandItem.getItem() == Items.IRON_SHOVEL && TRIM_VALUES.contains(mainHandItem.getDamageValue())) {
            armorStand.setItemSlot(EquipmentSlot.MAINHAND, Items.AIR.getDefaultInstance());
        }
    }
}
