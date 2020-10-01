package com.zylr.minescapeaddons.addons.armor.overrides;

import com.zylr.minescapeaddons.addons.armor.MinescapeItems;
import com.zylr.minescapeaddons.addons.armor.item.MetalArmorItem;

public enum ArmorToOverride {
//    BRONZE_PLATEBODY_T("Bronze Platebody T", null),
// Bronze
    BRONZE_PLATEBODY("Bronze Platebody", MinescapeItems.BRONZE_CHESTPLATE.get()),
    BRONZE_PLATELEGS("Bronze Platelegs", MinescapeItems.BRONZE_LEGGINGS.get()),
    BRONZEPLATESKIRT("Bronze Plateskirt", MinescapeItems.BRONZESKIRT_LEGGINGS.get()),

    // Bronze T
    BRONZE_PLATEBODY_T("Bronze Platebody T", MinescapeItems.BRONZETRIM_CHESTPLATE.get()),
    BRONZE_PLATELEGS_T("Bronze Platelegs T", MinescapeItems.BRONZETRIM_LEGGINGS.get()),
    BRONZEPLATESKIRT_T("Bronze Plateskirt T", MinescapeItems.BRONZESKIRTTRIM_LEGGINGS.get()),

    // Bronze G
    BRONZE_PLATEBODY_G("Bronze Platebody G", MinescapeItems.BRONZEGOLD_CHESTPLATE.get()),
    BRONZE_PLATELEGS_G("Bronze Platelegs G", MinescapeItems.BRONZEGOLD_LEGGINGS.get()),
    BRONZEPLATESKIRT_G("Bronze Plateskirt G", MinescapeItems.BRONZESKIRTGOLD_LEGGINGS.get()),

    // Iron
    IRON_PLATEBODY("Iron Platebody", MinescapeItems.IRON_CHESTPLATE.get()),
    IRON_PLATELEGS("Iron Platelegs", MinescapeItems.IRON_LEGGINGS.get()),
    IRONPLATESKIRT("Iron Plateskirt", MinescapeItems.IRONSKIRT_LEGGINGS.get()),

    // Iron T
    IRON_PLATEBODY_T("Iron Platebody T", MinescapeItems.IRONTRIM_CHESTPLATE.get()),
    IRON_PLATELEGS_T("Iron Platelegs T", MinescapeItems.IRONTRIM_LEGGINGS.get()),
    IRONPLATESKIRT_T("Iron Plateskirt T", MinescapeItems.IRONSKIRTTRIM_LEGGINGS.get()),

    // Iron G
    IRON_PLATEBODY_G("Iron Platebody G", MinescapeItems.IRONGOLD_CHESTPLATE.get()),
    IRON_PLATELEGS_G("Iron Platelegs G", MinescapeItems.IRONGOLD_LEGGINGS.get()),
    IRONPLATESKIRT_G("Iron Plateskirt G", MinescapeItems.IRONSKIRTGOLD_LEGGINGS.get()),

    // Rune
    RUNE_PLATEBODY("Rune Platebody", MinescapeItems.RUNE_CHESTPLATE.get()),
    RUNE_PLATLEGS("Rune Platelegs", MinescapeItems.RUNE_LEGGINGS.get());

    public String name;
    public MetalArmorItem newArmor;

    ArmorToOverride(String name, MetalArmorItem newArmor){
        this.name = name;
        this.newArmor = newArmor;
    }
}
