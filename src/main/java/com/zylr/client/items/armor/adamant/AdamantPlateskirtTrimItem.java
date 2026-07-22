package com.zylr.client.items.armor.adamant;

import com.zylr.MinescapeAddon;
import com.zylr.client.items.armor.AbstractArmorItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;


public class AdamantPlateskirtTrimItem extends AbstractArmorItem {
    private static final Identifier TEXTURE_LOCATION = makeCustomTextureLocation(MinescapeAddon.MOD_ID, "adamant_plateskirt_armor_t");

    public AdamantPlateskirtTrimItem(ArmorType pType) {
        super(net.minecraft.world.item.equipment.ArmorMaterials.DIAMOND, pType, new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public Identifier getCustomArmorTexture() {
        return TEXTURE_LOCATION;
    }
}
