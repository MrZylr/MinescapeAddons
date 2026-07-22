package com.zylr.client.items.armor.dragon;

import com.zylr.MinescapeAddon;
import com.zylr.client.items.armor.AbstractArmorItem;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;


public class DragonPlateskirtArmorItem extends AbstractArmorItem {
    private static final Identifier TEXTURE_LOCATION = makeCustomTextureLocation(MinescapeAddon.MOD_ID, "dragon_plateskirt_armor");

    public DragonPlateskirtArmorItem(ArmorType pType) {
        super(net.minecraft.world.item.equipment.ArmorMaterials.DIAMOND, pType, new Properties().rarity(Rarity.RARE));
    }

    @Override
    public Identifier getCustomArmorTexture() {
        return TEXTURE_LOCATION;
    }
}
