package com.zylr.minescapeaddons.addons.Proxy;

import com.zylr.minescapeaddons.addons.armor.model.MetalArmorModel;
import com.zylr.minescapeaddons.addons.armor.model.barrows.DharoksArmorModel;
import com.zylr.minescapeaddons.addons.armor.model.barrows.GuthansArmorModel;
import net.minecraft.inventory.EquipmentSlotType;

public class ClientProxy implements IProxy {

    private final MetalArmorModel runeArmorModel = new MetalArmorModel(1.0f);
    private final MetalArmorModel runeArmorLeggings = new MetalArmorModel(0.5f);

    // Barrows
    private final GuthansArmorModel guthansArmorModel = new GuthansArmorModel(1.0f);
    private final GuthansArmorModel guthansArmorModelLegs = new GuthansArmorModel(0.5F);
    private final DharoksArmorModel dharoksArmorModel = new DharoksArmorModel(1.0f);
    private final DharoksArmorModel dharoksArmorModelLegs = new DharoksArmorModel(0.5F);

    @SuppressWarnings("unchecked")
    @Override
    public <A> A getMetalArmorModel(EquipmentSlotType armorSlot) {
        return (A) (armorSlot == EquipmentSlotType.LEGS ? runeArmorLeggings : runeArmorModel);
    }
    // Barrows armors
    @Override
    public <A> A getGuthansArmorModel(EquipmentSlotType armorSlot) {
        return (A) (armorSlot == EquipmentSlotType.LEGS ? guthansArmorModelLegs : guthansArmorModel);
    }
    @Override
    public <A> A getDharoksArmorModel(EquipmentSlotType armorSlot) {
        return (A) (armorSlot == EquipmentSlotType.LEGS ? dharoksArmorModelLegs : dharoksArmorModel);
    }
}
