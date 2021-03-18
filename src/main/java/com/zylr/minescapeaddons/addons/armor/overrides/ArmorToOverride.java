package com.zylr.minescapeaddons.addons.armor.overrides;

import com.zylr.minescapeaddons.addons.armor.MinescapeItems;
import com.zylr.minescapeaddons.addons.armor.item.MetalArmorItem;
import net.minecraft.item.ArmorItem;

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

    // Black
    BLACK_PLATEBODY("Black Platebody", MinescapeItems.BLACK_CHESTPLATE.get()),
    BLACK_PLATELEGS("Black Platelegs", MinescapeItems.BLACK_LEGGINGS.get()),
    BLACKPLATESKIRT("Black Plateskirt", MinescapeItems.BLACKSKIRT_LEGGINGS.get()),

    // Black T
    BLACK_PLATEBODY_T("Black Platebody T", MinescapeItems.BLACKTRIM_CHESTPLATE.get()),
    BLACK_PLATELEGS_T("Black Platelegs T", MinescapeItems.BLACKTRIM_LEGGINGS.get()),
    BLACKPLATESKIRT_T("Black Plateskirt T", MinescapeItems.BLACKSKIRTTRIM_LEGGINGS.get()),

    // Black G
    BLACK_PLATEBODY_G("Black Platebody G", MinescapeItems.BLACKGOLD_CHESTPLATE.get()),
    BLACK_PLATELEGS_G("Black Platelegs G", MinescapeItems.BLACKGOLD_LEGGINGS.get()),
    BLACKPLATESKIRT_G("Black Plateskirt G", MinescapeItems.BLACKSKIRTGOLD_LEGGINGS.get()),

    // Rune
    RUNE_PLATEBODY("Rune Platebody", MinescapeItems.RUNE_CHESTPLATE.get()),
    RUNE_PLATLEGS("Rune Platelegs", MinescapeItems.RUNE_LEGGINGS.get()),

    // Guthans
    GUTHANS_PLATEBODY("Guthans Platebody", MinescapeItems.GUTHANS_CHESTPLATE.get()),
    VERACS_BRASSARD("Veracs Brassard", MinescapeItems.VERACS_CHESTPLATE.get()),
//    DHAROKS_PLATEBODY("Dharoks Platebody", MinescapeItems.DHAROKS_CHESTPLATE.get())

    // Mystic
    MYSTIC_ROBE_DARK("Mystic Robe Top Dark", MinescapeItems.MYSTIC_ROBE_TOP_DARK.get())

    ;

    public String name;
    public ArmorItem newArmor;

    ArmorToOverride(String name, ArmorItem newArmor){
        this.name = name;
        this.newArmor = newArmor;
    }
}
