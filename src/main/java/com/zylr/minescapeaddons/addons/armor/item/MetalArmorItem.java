package com.zylr.minescapeaddons.addons.armor.item;

import com.zylr.minescapeaddons.addons.Main;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class MetalArmorItem extends ArmorItem {
    public MetalArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlotType slotType, A _default) {
        return Main.PROXY.getMetalArmorModel(slotType);
    }


}
