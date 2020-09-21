package com.zylr.minescapeaddons.addons.Proxy;

import net.minecraft.inventory.EquipmentSlotType;

public interface IProxy {

    default <A> A getMetalArmorModel(EquipmentSlotType armorSlot) {
        return null;
    }
}