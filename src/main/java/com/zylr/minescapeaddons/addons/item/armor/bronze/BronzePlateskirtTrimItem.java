package com.zylr.minescapeaddons.addons.item.armor.bronze;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.item.armor.AbstractArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import javax.annotation.Nullable;

public class BronzePlateskirtTrimItem extends AbstractArmorItem {
    private static final ResourceLocation TEXTURE_LOCATION = makeCustomTextureLocation(MinescapeAddons.MOD_ID, "bronze_plateskirt_armor_t");

    public BronzePlateskirtTrimItem(ArmorItem.Type pType) {
        super(ArmorMaterials.DIAMOND, pType, new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot equipmentSlot, ArmorMaterial.Layer layer, boolean inner) {
        return TEXTURE_LOCATION;
    }
}
