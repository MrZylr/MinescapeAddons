package com.zylr.minescapeaddons.addons.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;

public enum OverrideArmors {
    BRONZE_CHAINBODY(ModItems.BRONZE_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "bronze chainbody"),
    BRONZE_PLATEBODY(ModItems.BRONZE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "bronze platebody"),
    BRONZE_PLATELEGS(ModItems.BRONZE_ARMOR.get(ArmorItem.Type.LEGGINGS), "bronze platelegs"),
    BRONZE_PLATESKIRT(ModItems.BRONZE_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "bronze plateskirt"),
    BRONZE_PLATEBODY_T(ModItems.BRONZE_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "bronze platebody(t)"),
    BRONZE_PLATELEGS_T(ModItems.BRONZE_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "bronze platelegs(t)"),
    BRONZE_PLATESKIRT_T(ModItems.BRONZE_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "bronze plateskirt(t)"),
    BRONZE_PLATEBODY_G(ModItems.BRONZE_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "bronze platebody(g)"),
    BRONZE_PLATELEGS_G(ModItems.BRONZE_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "bronze platelegs(g)"),
    BRONZE_PLATESKIRT_G(ModItems.BRONZE_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "bronze plateskirt(g)"),
    BRONZE_BOOTS(ModItems.BRONZE_ARMOR.get(ArmorItem.Type.BOOTS), "bronze boots"),

    IRON_CHAINBODY(ModItems.IRON_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "iron chainbody"),
    IRON_PLATEBODY(ModItems.IRON_ARMOR.get(ArmorItem.Type.CHESTPLATE), "iron platebody"),
    IRON_PLATELEGS(ModItems.IRON_ARMOR.get(ArmorItem.Type.LEGGINGS), "iron platelegs"),
    IRON_PLATESKIRT(ModItems.IRON_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "iron plateskirt"),
    IRON_PLATEBODY_T(ModItems.IRON_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "iron platebody(t)"),
    IRON_PLATELEGS_T(ModItems.IRON_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "iron platelegs(t)"),
    IRON_PLATESKIRT_T(ModItems.IRON_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "iron plateskirt(t)"),
    IRON_PLATEBODY_G(ModItems.IRON_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "iron platebody(g)"),
    IRON_PLATELEGS_G(ModItems.IRON_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "iron platelegs(g)"),
    IRON_PLATESKIRT_G(ModItems.IRON_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "iron plateskirt(g)"),
    IRON_BOOTS(ModItems.IRON_ARMOR.get(ArmorItem.Type.BOOTS), "iron boots"),

    STEEL_CHAINBODY(ModItems.STEEL_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "steel chainbody"),
    STEEL_PLATEBODY(ModItems.STEEL_ARMOR.get(ArmorItem.Type.CHESTPLATE), "steel platebody"),
    STEEL_PLATELEGS(ModItems.STEEL_ARMOR.get(ArmorItem.Type.LEGGINGS), "steel platelegs"),
    STEEL_PLATESKIRT(ModItems.STEEL_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "steel plateskirt"),
    STEEL_PLATEBODY_T(ModItems.STEEL_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "steel platebody(t)"),
    STEEL_PLATELEGS_T(ModItems.STEEL_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "steel platelegs(t)"),
    STEEL_PLATESKIRT_T(ModItems.STEEL_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "steel plateskirt(t)"),
    STEEL_PLATEBODY_G(ModItems.STEEL_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "steel platebody(g)"),
    STEEL_PLATELEGS_G(ModItems.STEEL_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "steel platelegs(g)"),
    STEEL_PLATESKIRT_G(ModItems.STEEL_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "steel plateskirt(g)"),
    STEEL_BOOTS(ModItems.STEEL_ARMOR.get(ArmorItem.Type.BOOTS), "steel boots"),

    BLACK_CHAINBODY(ModItems.BLACK_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "black chainbody"),
    BLACK_PLATEBODY(ModItems.BLACK_ARMOR.get(ArmorItem.Type.CHESTPLATE), "black platebody"),
    BLACK_PLATELEGS(ModItems.BLACK_ARMOR.get(ArmorItem.Type.LEGGINGS), "black platelegs"),
    BLACK_PLATESKIRT(ModItems.BLACK_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "black plateskirt"),
    BLACK_PLATEBODY_T(ModItems.BLACK_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "black platebody(t)"),
    BLACK_PLATELEGS_T(ModItems.BLACK_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "black platelegs(t)"),
    BLACK_PLATESKIRT_T(ModItems.BLACK_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "black plateskirt(t)"),
    BLACK_PLATEBODY_G(ModItems.BLACK_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "black platebody(g)"),
    BLACK_PLATELEGS_G(ModItems.BLACK_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "black platelegs(g)"),
    BLACK_PLATESKIRT_G(ModItems.BLACK_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "black plateskirt(g)"),
    BLACK_BOOTS(ModItems.BLACK_ARMOR.get(ArmorItem.Type.BOOTS), "black boots"),

    WHITE_CHAINBODY(ModItems.WHITE_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "white chainbody"),
    WHITE_PLATEBODY(ModItems.WHITE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "white platebody"),
    WHITE_PLATELEGS(ModItems.WHITE_ARMOR.get(ArmorItem.Type.LEGGINGS), "white platelegs"),
    WHITE_BOOTS(ModItems.WHITE_ARMOR.get(ArmorItem.Type.BOOTS), "white boots"),

    MITRHIL_CHAINBODY(ModItems.MITHRIL_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "mithril chainbody"),
    MITHRIL_PLATEBODY(ModItems.MITHRIL_ARMOR.get(ArmorItem.Type.CHESTPLATE), "mithril platebody"),
    MITHRIL_PLATELEGS(ModItems.MITHRIL_ARMOR.get(ArmorItem.Type.LEGGINGS), "mithril platelegs"),
    MITHRIL_PLATESKIRT(ModItems.MITHRIL_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "mithril plateskirt"),
    MITHRIL_PLATEBODY_T(ModItems.MITHRIL_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "mithril platebody(t)"),
    MITHRIL_PLATELEGS_T(ModItems.MITHRIL_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "mithril platelegs(t)"),
    MITHRIL_PLATESKIRT_T(ModItems.MITHRIL_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "mithril plateskirt(t)"),
    MITHRIL_PLATEBODY_G(ModItems.MITHRIL_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "mithril platebody(g)"),
    MITHRIL_PLATELEGS_G(ModItems.MITHRIL_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "mithril platelegs(g)"),
    MITHRIL_PLATESKIRT_G(ModItems.MITHRIL_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "mithril plateskirt(g)"),
    MITHRIL_BOOTS(ModItems.MITHRIL_ARMOR.get(ArmorItem.Type.BOOTS), "mithril boots"),

    ADAMANT_CHAINBODY(ModItems.ADAMANT_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "adamant chainbody"),
    ADAMANT_PLATEBODY(ModItems.ADAMANT_ARMOR.get(ArmorItem.Type.CHESTPLATE), "adamant platebody"),
    ADAMANT_PLATELEGS(ModItems.ADAMANT_ARMOR.get(ArmorItem.Type.LEGGINGS), "adamant platelegs"),
    ADAMANT_PLATESKIRT(ModItems.ADAMANT_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "adamant plateskirt"),
    ADAMANT_PLATEBODY_T(ModItems.ADAMANT_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "adamant platebody(t)"),
    ADAMANT_PLATELEGS_T(ModItems.ADAMANT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "adamant platelegs(t)"),
    ADAMANT_PLATESKIRT_T(ModItems.ADAMANT_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "adamant plateskirt(t)"),
    ADAMANT_PLATEBODY_G(ModItems.ADAMANT_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "adamant platebody(g)"),
    ADAMANT_PLATELEGS_G(ModItems.ADAMANT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "adamant platelegs(g)"),
    ADAMANT_PLATESKIRT_G(ModItems.ADAMANT_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "adamant plateskirt(g)"),
    ADAMANT_BOOTS(ModItems.ADAMANT_ARMOR.get(ArmorItem.Type.BOOTS), "adamant boots"),

    RUNE_CHAINBODY(ModItems.RUNE_CHAINBODY.get(ArmorItem.Type.CHESTPLATE), "rune chainbody"),
    RUNE_PLATEBODY(ModItems.RUNE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "rune platebody"),
    RUNE_PLATELEGS(ModItems.RUNE_ARMOR.get(ArmorItem.Type.LEGGINGS), "rune platelegs"),
    RUNE_PLATESKIRT(ModItems.RUNE_PLATESKIRT_ARMOR.get(ArmorItem.Type.LEGGINGS), "rune plateskirt"),
    RUNE_PLATEBODY_T(ModItems.RUNE_ARMOR_TRIM.get(ArmorItem.Type.CHESTPLATE), "rune platebody(t)"),
    RUNE_PLATELEGS_T(ModItems.RUNE_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "rune platelegs(t)"),
    RUNE_PLATESKIRT_T(ModItems.RUNE_PLATESKIRT_ARMOR_TRIM.get(ArmorItem.Type.LEGGINGS), "rune plateskirt(t)"),
    RUNE_PLATEBODY_G(ModItems.RUNE_ARMOR_GOLD.get(ArmorItem.Type.CHESTPLATE), "rune platebody(g)"),
    RUNE_PLATELEGS_G(ModItems.RUNE_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "rune platelegs(g)"),
    RUNE_PLATESKIRT_G(ModItems.RUNE_PLATESKIRT_ARMOR_GOLD.get(ArmorItem.Type.LEGGINGS), "rune plateskirt(g)"),
    RUNE_BOOTS(ModItems.RUNE_ARMOR.get(ArmorItem.Type.BOOTS), "rune boots"),

    GUTHANS_PLATEBODY(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "guthans platebody"),
    GUTHANS_PLATELEGS(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.LEGGINGS), "guthans chainskirt"),
    GUTHANS_PLATEBODY_100(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 100"),
    GUTHANS_PLATELEGS_100(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.LEGGINGS), "guthans chainskirt 100"),
    GUTHANS_PLATEBODY_75(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 75"),
    GUTHANS_PLATELEGS_75(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.LEGGINGS), "guthans chainskirt 75"),
    GUTHANS_PLATEBODY_50(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 50"),
    GUTHANS_PLATELEGS_50(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.LEGGINGS), "guthans chainskirt 50"),
    GUTHANS_PLATEBODY_25(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "guthans platebody 25"),
    GUTHANS_PLATELEGS_25(ModItems.GUTHANS_ARMOR.get(ArmorItem.Type.LEGGINGS), "guthans chainskirt 25"),

    AHRIMS_ROBETOP(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop"),
    AHRIMS_ROBESKIRT(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.LEGGINGS), "ahrims robeskirt"),
    AHRIMS_ROBETOP_100(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 100"),
    AHRIMS_ROBESKIRT_100(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.LEGGINGS), "ahrims robeskirt 100"),
    AHRIMS_ROBETOP_75(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 75"),
    AHRIMS_ROBESKIRT_75(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.LEGGINGS), "ahrims robeskirt 75"),
    AHRIMS_ROBETOP_50(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 50"),
    AHRIMS_ROBESKIRT_50(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.LEGGINGS), "ahrims robeskirt 50"),
    AHRIMS_ROBETOP_25(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "ahrims robetop 25"),
    AHRIMS_ROBESKIRT_25(ModItems.AHRIMS_ARMOR.get(ArmorItem.Type.LEGGINGS), "ahrims robeskirt 25"),

    DHAROKS_PLATEBODY(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody"),
    DHAROKS_PLATELEGS(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.LEGGINGS), "dharoks platelegs"),
    DHAROKS_PLATEBODY_100(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 100"),
    DHAROKS_PLATELEGS_100(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.LEGGINGS), "dharoks platelegs 100"),
    DHAROKS_PLATEBODY_75(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 75"),
    DHAROKS_PLATELEGS_75(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.LEGGINGS), "dharoks platelegs 75"),
    DHAROKS_PLATEBODY_50(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 50"),
    DHAROKS_PLATELEGS_50(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.LEGGINGS), "dharoks platelegs 50"),
    DHAROKS_PLATEBODY_25(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "dharoks platebody 25"),
    DHAROKS_PLATELEGS_25(ModItems.DHAROKS_ARMOR.get(ArmorItem.Type.LEGGINGS), "dharoks platelegs 25"),

    VERACS_PLATEBODY(ModItems.VERACS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "veracs brassard"),
    VERACS_PLATELEGS(ModItems.VERACS_ARMOR.get(ArmorItem.Type.LEGGINGS), "veracs plateskirt"),
    VERACS_PLATEBODY_100(ModItems.VERACS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 100"),
    VERACS_PLATELEGS_100(ModItems.VERACS_ARMOR.get(ArmorItem.Type.LEGGINGS), "veracs plateskirt 100"),
    VERACS_PLATEBODY_75(ModItems.VERACS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 75"),
    VERACS_PLATELEGS_75(ModItems.VERACS_ARMOR.get(ArmorItem.Type.LEGGINGS), "veracs plateskirt 75"),
    VERACS_PLATEBODY_50(ModItems.VERACS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 50"),
    VERACS_PLATELEGS_50(ModItems.VERACS_ARMOR.get(ArmorItem.Type.LEGGINGS), "veracs plateskirt 50"),
    VERACS_PLATEBODY_25(ModItems.VERACS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "veracs brassard 25"),
    VERACS_PLATELEGS_25(ModItems.VERACS_ARMOR.get(ArmorItem.Type.LEGGINGS), "veracs plateskirt 25"),

    TORAGS_PLATEBODY(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "torags platebody"),
    TORAGS_PLATELEGS(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.LEGGINGS), "torags platelegs"),
    TORAGS_PLATEBODY_100(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "torags platebody 100"),
    TORAGS_PLATELEGS_100(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.LEGGINGS), "torags platelegs 100"),
    TORAGS_PLATEBODY_75(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "torags platebody 75"),
    TORAGS_PLATELEGS_75(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.LEGGINGS), "torags platelegs 75"),
    TORAGS_PLATEBODY_50(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "torags platebody 50"),
    TORAGS_PLATELEGS_50(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.LEGGINGS), "torags platelegs 50"),
    TORAGS_PLATEBODY_25(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "torags platebody 25"),
    TORAGS_PLATELEGS_25(ModItems.TORAGS_ARMOR.get(ArmorItem.Type.LEGGINGS), "torags platelegs 25"),

    KARILS_LEATHERTOP(ModItems.KARILS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "karils leathertop"),
    KARILS_LEATHERSKIRT(ModItems.KARILS_ARMOR.get(ArmorItem.Type.LEGGINGS), "karils leatherskirt"),
    KARILS_LEATHERTOP_100(ModItems.KARILS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 100"),
    KARILS_LEATHERSKIRT_100(ModItems.KARILS_ARMOR.get(ArmorItem.Type.LEGGINGS), "karils leatherskirt 100"),
    KARILS_LEATHERTOP_75(ModItems.KARILS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 75"),
    KARILS_LEATHERSKIRT_75(ModItems.KARILS_ARMOR.get(ArmorItem.Type.LEGGINGS), "karils leatherskirt 75"),
    KARILS_LEATHERTOP_50(ModItems.KARILS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 50"),
    KARILS_LEATHERSKIRT_50(ModItems.KARILS_ARMOR.get(ArmorItem.Type.LEGGINGS), "karils leatherskirt 50"),
    KARILS_LEATHERTOP_25(ModItems.KARILS_ARMOR.get(ArmorItem.Type.CHESTPLATE), "karils leathertop 25"),
    KARILS_LEATHERSKIRT_25(ModItems.KARILS_ARMOR.get(ArmorItem.Type.LEGGINGS), "karils leatherskirt 25"),

    BLUE_WIZARD_TOP(ModItems.BLUE_WIZARD_ROBES.get(ArmorItem.Type.CHESTPLATE), "blue wizard robe"),
    BLUE_WIZARD_BOTTOMS(ModItems.BLUE_WIZARD_ROBES.get(ArmorItem.Type.LEGGINGS), "blue skirt"),
    BLUE_WIZARD_TOP_T(ModItems.BLUE_WIZARD_ROBES_TRIM.get(ArmorItem.Type.CHESTPLATE), "blue wizard robe(t)"),
    BLUE_WIZARD_BOTTOMS_T(ModItems.BLUE_WIZARD_ROBES_TRIM.get(ArmorItem.Type.LEGGINGS), "blue skirt(t)"),
    BLUE_WIZARD_TOP_G(ModItems.BLUE_WIZARD_ROBES_GOLD.get(ArmorItem.Type.CHESTPLATE), "blue wizard robe(g)"),
    BLUE_WIZARD_BOTTOMS_G(ModItems.BLUE_WIZARD_ROBES_GOLD.get(ArmorItem.Type.LEGGINGS), "blue skirt(g)"),

    BLACK_WIZARD_TOP(ModItems.BLACK_WIZARD_ROBES.get(ArmorItem.Type.CHESTPLATE), "black robe"),
    BLACK_WIZARD_BOTTOMS(ModItems.BLACK_WIZARD_ROBES.get(ArmorItem.Type.LEGGINGS), "black skirt"),
    BLACK_WIZARD_TOP_T(ModItems.BLACK_WIZARD_ROBES_TRIM.get(ArmorItem.Type.CHESTPLATE), "black wizard robe(t)"),
    BLACK_WIZARD_BOTTOMS_T(ModItems.BLACK_WIZARD_ROBES_TRIM.get(ArmorItem.Type.LEGGINGS), "black skirt(t)"),
    BLACK_WIZARD_TOP_G(ModItems.BLACK_WIZARD_ROBES_GOLD.get(ArmorItem.Type.CHESTPLATE), "black wizard robe(g)"),
    BLACK_WIZARD_BOTTOMS_G(ModItems.BLACK_WIZARD_ROBES_GOLD.get(ArmorItem.Type.LEGGINGS), "black skirt(g)"),

    ELDER_CHAOS_ROBES_TOP(ModItems.ELDER_CHAOS_ROBES.get(ArmorItem.Type.CHESTPLATE), "elder chaos top"),
    ELDER_CHAOS_ROBES(ModItems.ELDER_CHAOS_ROBES.get(ArmorItem.Type.LEGGINGS), "elder chaos robe"),

    PINK_SKIRT_BOTTOMS(ModItems.PINK_SKIRT.get(ArmorItem.Type.LEGGINGS), "pink skirt"),

    MONKS_ROBES_TOP(ModItems.MONKS_ROBE.get(ArmorItem.Type.CHESTPLATE), "monks robe top"),
    MONKS_ROBES_BOTTOMS(ModItems.MONKS_ROBE.get(ArmorItem.Type.LEGGINGS), "monks robe bottom"),
    MONKS_ROBES_TOP_T(ModItems.MONKS_ROBE_TRIM.get(ArmorItem.Type.CHESTPLATE), "monk's robe top(t)"),
    MONKS_ROBES_BOTTOMS_T(ModItems.MONKS_ROBE_TRIM.get(ArmorItem.Type.LEGGINGS), "monk's robe bottom(t)"),
    MONKS_ROBES_TOP_G(ModItems.MONKS_ROBE_GOLD.get(ArmorItem.Type.CHESTPLATE), "monk's robe top(g)"),
    MONKS_ROBES_BOTTOMS_G(ModItems.MONKS_ROBE_GOLD.get(ArmorItem.Type.LEGGINGS), "monk's robe bottom(g)"),
    ZAMORAK_MONKS_ROBES_TOP(ModItems.ZAMORAK_MONK_ROBE.get(ArmorItem.Type.CHESTPLATE), "zamorak monk top"),
    ZAMORAK_MONKS_ROBES_BOTTOMS(ModItems.ZAMORAK_MONK_ROBE.get(ArmorItem.Type.LEGGINGS), "zamorak monk bottom"),

    PRIEST_GOWN_TOP(ModItems.PRIEST_GOWN_ROBES.get(ArmorItem.Type.CHESTPLATE), "priest gown top"),
    PRIEST_GOWN_BOTTOMS(ModItems.PRIEST_GOWN_ROBES.get(ArmorItem.Type.LEGGINGS), "priest gown bottom"),

    DRUIDS_ROBES_TOP(ModItems.DRUIDS_ROBES.get(ArmorItem.Type.CHESTPLATE), "druids robe top"),
    DRUIDS_ROBES_BOTTOMS(ModItems.DRUIDS_ROBES.get(ArmorItem.Type.LEGGINGS), "druids robe"),

    PURPLE_ROBES_TOP(ModItems.PURPLE_ROBES.get(ArmorItem.Type.CHESTPLATE), "purple robe top"),
    PURPLE_ROBES_BOTTOMS(ModItems.PURPLE_ROBES.get(ArmorItem.Type.LEGGINGS), "purple robe bottoms"),

    GREY_ROBES_TOP(ModItems.GREY_ROBES.get(ArmorItem.Type.CHESTPLATE), "grey robe top"),
    GREY_ROBES_BOTTOMS(ModItems.GREY_ROBES.get(ArmorItem.Type.LEGGINGS), "grey robe bottoms"),

    YELLOW_ROBES_TOP(ModItems.YELLOW_ROBES.get(ArmorItem.Type.CHESTPLATE), "yellow robe top"),
    YELLOW_ROBES_BOTTOMS(ModItems.YELLOW_ROBES.get(ArmorItem.Type.LEGGINGS), "yellow robe bottoms"),

    TEAL_ROBES_TOP(ModItems.TEAL_ROBES.get(ArmorItem.Type.CHESTPLATE), "teal robe top"),
    TEAL_ROBES_BOTTOMS(ModItems.TEAL_ROBES.get(ArmorItem.Type.LEGGINGS), "teal robe bottoms"),

    RED_ROBES_TOP(ModItems.RED_ROBES.get(ArmorItem.Type.CHESTPLATE), "red robe top"),
    RED_ROBES_BOTTOMS(ModItems.RED_ROBES.get(ArmorItem.Type.LEGGINGS), "red robe bottoms"),

    HAM_ROBES_TOP(ModItems.HAM_ROBES.get(ArmorItem.Type.CHESTPLATE), "ham shirt"),
    HAM_ROBES_BOTTOMS(ModItems.HAM_ROBES.get(ArmorItem.Type.LEGGINGS), "ham robe"),

    BLACK_DHIDE_BODY(ModItems.BLACK_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "black d'hide body"),
    BLACK_DHIDE_LEGS(ModItems.BLACK_DHIDE_ARMOR.get(ArmorItem.Type.LEGGINGS), "black d'hide chaps"),

    GREEN_DHIDE_BODY(ModItems.GREEN_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "green d'hide body"),
    GREEN_DHIDE_LEGS(ModItems.GREEN_DHIDE_ARMOR.get(ArmorItem.Type.LEGGINGS), "green d'hide chaps"),

    RED_DHIDE_BODY(ModItems.RED_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "red d'hide body"),
    RED_DHIDE_LEGS(ModItems.RED_DHIDE_ARMOR.get(ArmorItem.Type.LEGGINGS), "red d'hide chaps"),

    BLUE_DHIDE_BODY(ModItems.BLUE_DHIDE_ARMOR.get(ArmorItem.Type.CHESTPLATE), "blue d'hide body"),
    BLUE_DHIDE_LEGS(ModItems.BLUE_DHIDE_ARMOR.get(ArmorItem.Type.LEGGINGS), "blue d'hide chaps"),

    BUILDERS_COSTUME_BODY(ModItems.BUILDERS_COSTUME.get(ArmorItem.Type.CHESTPLATE), "builders shirt"),
    BUILDERS_COSTUME_LEGS(ModItems.BUILDERS_COSTUME.get(ArmorItem.Type.LEGGINGS), "builders trousers"),
    BUILDERS_COSTUME_BOOTS(ModItems.BUILDERS_COSTUME.get(ArmorItem.Type.BOOTS), "builders boots"),

    MYSTIC_ROBES_BLUE_TOP(ModItems.MYSTIC_ROBES_BLUE.get(ArmorItem.Type.CHESTPLATE), "mystic robe top blue"),
    MYSTIC_ROBES_BLUE_BOTTOMS(ModItems.MYSTIC_ROBES_BLUE.get(ArmorItem.Type.LEGGINGS), "mystic robe bottom blue");

    public DeferredItem item;
    public String name;

    OverrideArmors(DeferredItem item, String name) {
        this.item = item;
        this.name = name;
    }
}
