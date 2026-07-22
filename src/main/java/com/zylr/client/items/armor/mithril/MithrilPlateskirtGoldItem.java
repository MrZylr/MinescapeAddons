package com.zylr.client.items.armor.mithril;

import com.zylr.MinescapeAddon;
import com.zylr.client.items.armor.AbstractArmorItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;


public class MithrilPlateskirtGoldItem extends AbstractArmorItem {
    private static final Identifier TEXTURE_LOCATION = makeCustomTextureLocation(MinescapeAddon.MOD_ID, "mithril_plateskirt_armor_g");

    public MithrilPlateskirtGoldItem(ArmorType pType) {
        super(net.minecraft.world.item.equipment.ArmorMaterials.DIAMOND, pType, new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public Identifier getCustomArmorTexture() {
        return TEXTURE_LOCATION;
    }
}
