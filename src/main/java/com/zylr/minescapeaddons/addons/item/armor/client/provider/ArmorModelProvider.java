package com.zylr.minescapeaddons.addons.item.armor.client.provider;

import com.zylr.minescapeaddons.addons.item.armor.client.model.ArmorModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface ArmorModelProvider {

    ArmorModel getModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot);
}
