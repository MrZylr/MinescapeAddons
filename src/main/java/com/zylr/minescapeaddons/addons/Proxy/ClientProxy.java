package com.zylr.minescapeaddons.addons.Proxy;

import com.zylr.minescapeaddons.addons.armor.model.MetalArmorModel;
import net.minecraft.inventory.EquipmentSlotType;

public class ClientProxy implements IProxy {

    private final MetalArmorModel runeArmorModel = new MetalArmorModel(1.0f);
    private final MetalArmorModel runeArmorLeggings = new MetalArmorModel(0.5f);

    @SuppressWarnings("unchecked")
    @Override
    public <A> A getMetalArmorModel(EquipmentSlotType armorSlot) {
        return (A) (armorSlot == EquipmentSlotType.LEGS ? runeArmorLeggings : runeArmorModel);
    }
}
