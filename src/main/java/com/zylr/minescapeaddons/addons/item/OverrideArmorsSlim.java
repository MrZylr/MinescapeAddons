package com.zylr.minescapeaddons.addons.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;

public enum OverrideArmorsSlim {
    BRONZE_CHAINBODY_SLIM(ModItems.BRONZE_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "bronze chainbody"),
    BRONZE_PLATEBODY_SLIM(ModItems.BRONZE_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "bronze platebody"),
    BRONZE_PLATEBODY_T_SLIM(ModItems.BRONZE_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "bronze platebody(t)"),
    BRONZE_PLATEBODY_G_SLIM(ModItems.BRONZE_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "bronze platebody(g)"),

    IRON_CHAINBODY_SLIM(ModItems.IRON_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "iron chainbody"),
    IRON_PLATEBODY_SLIM(ModItems.IRON_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "iron platebody"),
    IRON_PLATEBODY_T_SLIM(ModItems.IRON_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "iron platebody(t)"),
    IRON_PLATEBODY_G_SLIM(ModItems.IRON_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "iron platebody(g)"),

    STEEL_CHAINBODY_SLIM(ModItems.STEEL_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "steel chainbody"),
    STEEL_PLATEBODY_SLIM(ModItems.STEEL_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "steel platebody"),
    STEEL_PLATEBODY_T_SLIM(ModItems.STEEL_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "steel platebody(t)"),
    STEEL_PLATEBODY_G_SLIM(ModItems.STEEL_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "steel platebody(g)"),

    BLACK_CHAINBODY_SLIM(ModItems.BLACK_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "black chainbody"),
    BLACK_PLATEBODY_SLIM(ModItems.BLACK_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "black platebody"),
    BLACK_PLATEBODY_T_SLIM(ModItems.BLACK_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "black platebody(t)"),
    BLACK_PLATEBODY_G_SLIM(ModItems.BLACK_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "black platebody(g)"),

    WHITE_CHAINBODY_SLIM(ModItems.WHITE_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "white chainbody"),
    WHITE_PLATEBODY_SLIM(ModItems.WHITE_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "white platebody"),

    MITHRIL_CHAINBODY_SLIM(ModItems.MITHRIL_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "mithril chainbody"),
    MITHRIL_PLATEBODY_SLIM(ModItems.MITHRIL_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "mithril platebody"),
    MITHRIL_PLATEBODY_T_SLIM(ModItems.MITHRIL_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "mithril platebody(t)"),
    MITHRIL_PLATEBODY_G_SLIM(ModItems.MITHRIL_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "mithril platebody(g)"),

    ADAMANT_CHAINBODY_SLIM(ModItems.ADAMANT_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "adamant chainbody"),
    ADAMANT_PLATEBODY_SLIM(ModItems.ADAMANT_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "adamant platebody"),
    ADAMANT_PLATEBODY_T_SLIM(ModItems.ADAMANT_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "adamant platebody(t)"),
    ADAMANT_PLATEBODY_G_SLIM(ModItems.ADAMANT_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "adamant platebody(g)"),

    RUNE_CHAINBODY_SLIM(ModItems.RUNE_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "rune chainbody"),
    RUNE_PLATEBODY_SLIM(ModItems.RUNE_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "rune platebody"),
    RUNE_PLATEBODY_T_SLIM(ModItems.RUNE_ARMOR_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "rune platebody(t)"),
    RUNE_PLATEBODY_G_SLIM(ModItems.RUNE_ARMOR_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "rune platebody(g)"),

    GUTHANS_PLATEBODY_SLIM(ModItems.GUTHANS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "guthans platebody"),
    GUTHANS_PLATEBODY_SLIM_100(ModItems.GUTHANS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 100"),
    GUTHANS_PLATEBODY_SLIM_75(ModItems.GUTHANS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 75"),
    GUTHANS_PLATEBODY_SLIM_50(ModItems.GUTHANS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 50"),
    GUTHANS_PLATEBODY_SLIM_25(ModItems.GUTHANS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 25"),

    AHRIMS_ROBETOP_SLIM(ModItems.AHRIMS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop"),
    AHRIMS_ROBETOP_SLIM_100(ModItems.AHRIMS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 100"),
    AHRIMS_ROBETOP_SLIM_75(ModItems.AHRIMS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 75"),
    AHRIMS_ROBETOP_SLIM_50(ModItems.AHRIMS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 50"),
    AHRIMS_ROBETOP_SLIM_25(ModItems.AHRIMS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 25"),

    DHAROKS_PLATEBODY_SLIM(ModItems.DHAROKS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody"),
    DHAROKS_PLATEBODY_SLIM_100(ModItems.DHAROKS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 100"),
    DHAROKS_PLATEBODY_SLIM_75(ModItems.DHAROKS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 75"),
    DHAROKS_PLATEBODY_SLIM_50(ModItems.DHAROKS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 50"),
    DHAROKS_PLATEBODY_SLIM_25(ModItems.DHAROKS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 25"),

    VERACS_PLATEBODY_SLIM(ModItems.VERACS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "veracs brassard"),
    VERACS_PLATEBODY_SLIM_100(ModItems.VERACS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 100"),
    VERACS_PLATEBODY_SLIM_75(ModItems.VERACS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 75"),
    VERACS_PLATEBODY_SLIM_50(ModItems.VERACS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 50"),
    VERACS_PLATEBODY_SLIM_25(ModItems.VERACS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 25"),

    TORAGS_PLATEBODY_SLIM(ModItems.TORAGS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "torags platebody"),
    TORAGS_PLATEBODY_SLIM_100(ModItems.TORAGS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "torags platebody 100"),
    TORAGS_PLATEBODY_SLIM_75(ModItems.TORAGS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "torags platebody 75"),
    TORAGS_PLATEBODY_SLIM_50(ModItems.TORAGS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "torags platebody 50"),
    TORAGS_PLATEBODY_SLIM_25(ModItems.TORAGS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "torags platebody 25"),

    KARILS_LEATHERTOP_SLIM(ModItems.KARILS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "karils leathertop"),
    KARILS_LEATHERTOP_SLIM_100(ModItems.KARILS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 100"),
    KARILS_LEATHERTOP_SLIM_75(ModItems.KARILS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 75"),
    KARILS_LEATHERTOP_SLIM_50(ModItems.KARILS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 50"),
    KARILS_LEATHERTOP_SLIM_25(ModItems.KARILS_ARMOR_SLIM.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 25"),

    BLUE_WIZARD_ROBES_SLIM(ModItems.BLUE_WIZARD_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "blue wizard robe"),
    BLUE_WIZARD_ROBES_T_SLIM(ModItems.BLUE_WIZARD_ROBES_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "blue wizard robe(t)"),
    BLUE_WIZARD_ROBES_G_SLIM(ModItems.BLUE_WIZARD_ROBES_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "blue wizard robe(g)"),

    BLACK_WIZARD_ROBES_SLIM(ModItems.BLACK_WIZARD_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "black robe"),
    BLACK_WIZARD_ROBES_T_SLIM(ModItems.BLACK_WIZARD_ROBES_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "black wizard robe(t)"),
    BLACK_WIZARD_ROBES_G_SLIM(ModItems.BLACK_WIZARD_ROBES_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "black wizard robe(g)"),

    ELDER_CHAOS_ROBES_TOP_SLIM(ModItems.ELDER_CHAOS_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "elder chaos top"),

    MONKS_ROBES_TOP_SLIM(ModItems.MONKS_ROBE_SLIM.get(ArmorItem.Type.CHESTPLATE), "monks robe top"),
    MONKS_ROBES_TOP_T_SLIM(ModItems.MONKS_ROBE_TRIM_SLIM.get(ArmorItem.Type.CHESTPLATE), "monk's robe top(t)"),
    MONKS_ROBES_TOP_G_SLIM(ModItems.MONKS_ROBE_GOLD_SLIM.get(ArmorItem.Type.CHESTPLATE), "monk's robe top(g)"),
    ZAMORAK_MONKS_ROBES_TOP_SLIM(ModItems.ZAMORAK_MONK_ROBE_SLIM.get(ArmorItem.Type.CHESTPLATE), "zamorak monk top"),

    PRIEST_GOWNS_TOP_SLIM(ModItems.PRIEST_GOWN_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "priest gown top"),

    DRUIDS_ROBES_TOP_SLIM(ModItems.DRUIDS_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "druids robe top"),

    PURPLE_ROBE_TOP_SLIM(ModItems.PURPLE_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "purple robe top"),

    GREY_ROBE_TOP_SLIM(ModItems.GREY_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "grey robe top"),

    YELLOW_ROBES_TOP_SLIM(ModItems.YELLOW_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "yellow robe top"),

    TEAL_ROBES_TOP_SLIM(ModItems.TEAL_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "teal robe top"),

    RED_ROBES_TOP_SLIM(ModItems.RED_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "red robe top"),

    HAM_ROBE_TOP_SLIM(ModItems.HAM_ROBES_SLIM.get(ArmorItem.Type.CHESTPLATE), "ham shirt"),

    BLACK_DHIDE_BODY_SLIM(ModItems.BLACK_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "black d'hide body"),
    BLUE_DHIDE_BODY_SLIM(ModItems.BLUE_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "blue d'hide body"),
    RED_DHIDE_BODY_SLIM(ModItems.RED_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "red d'hide body"),
    GREEN_DHIDE_BODY_SLIM(ModItems.GREEN_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "green d'hide body"),

    BUILDERS_COSTUME_BODY_SLIM(ModItems.BUILDERS_COSTUME_SLIM.get(ArmorItem.Type.CHESTPLATE), "builders shirt"),

    MYSTIC_ROBES_TOP_SLIM(ModItems.MYSTIC_ROBES_BLUE_SLIM.get(ArmorItem.Type.CHESTPLATE), "mystic robe top blue");

    public DeferredItem item;
    public String name;

    OverrideArmorsSlim(DeferredItem item, String name) {
        this.item = item;
        this.name = name;
    }
}
