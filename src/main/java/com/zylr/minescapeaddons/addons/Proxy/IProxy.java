package com.zylr.minescapeaddons.addons.Proxy;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.inventory.EquipmentSlotType;

public interface IProxy {

    default <A> A getMetalArmorModel(EquipmentSlotType armorSlot) {
        return null;
    }

    default <A> A getGuthansArmorModel(EquipmentSlotType armorSlot) { return null; }

    default <A> A  getDharoksArmorModel(EquipmentSlotType armorSlot) {
        return null;
    }

    default <A> A  getMysticRobesModel(EquipmentSlotType armorSlot) { return null; }

    default <A> A  getVeracsArmorModel(EquipmentSlotType slotType) { return null; }
}