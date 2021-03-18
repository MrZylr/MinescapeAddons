package com.zylr.minescapeaddons.addons.armor.item.barrows;

import com.zylr.minescapeaddons.addons.Main;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuthansArmorItem extends ArmorItem {
    public GuthansArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlotType slotType, A _default) {
        return Main.PROXY.getGuthansArmorModel(slotType);
    }


}
