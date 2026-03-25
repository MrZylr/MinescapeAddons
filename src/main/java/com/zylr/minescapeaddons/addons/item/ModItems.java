package com.zylr.minescapeaddons.addons.item;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.item.armor.*;
import com.zylr.minescapeaddons.addons.item.armor.adamant.*;
import com.zylr.minescapeaddons.addons.item.armor.barrows.*;
import com.zylr.minescapeaddons.addons.item.armor.black.*;
import com.zylr.minescapeaddons.addons.item.armor.bronze.*;
import com.zylr.minescapeaddons.addons.item.armor.costume.builder.BuildersCostumeItem;
import com.zylr.minescapeaddons.addons.item.armor.costume.builder.BuildersCostumeSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.dhide.black.BlackDhideArmorItem;
import com.zylr.minescapeaddons.addons.item.armor.dhide.blue.BlueDhideArmorItem;
import com.zylr.minescapeaddons.addons.item.armor.dhide.green.GreenDhideArmorItem;
import com.zylr.minescapeaddons.addons.item.armor.dhide.red.RedDhideArmorItem;
import com.zylr.minescapeaddons.addons.item.armor.iron.*;
import com.zylr.minescapeaddons.addons.item.armor.mithril.*;
import com.zylr.minescapeaddons.addons.item.armor.mystic.MysticRobesBlueItem;
import com.zylr.minescapeaddons.addons.item.armor.mystic.MysticRobesBlueSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.druids.DruidsRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.druids.DruidsRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.elderchaos.ElderChaosRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.elderchaos.ElderChaosRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.grey.GreyRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.grey.GreyRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.ham.HamRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.ham.HamRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.monks.*;
import com.zylr.minescapeaddons.addons.item.armor.robes.pink.PinkSkirtItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.priestgown.PriestGownRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.priestgown.PriestGownRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.purple.PurpleRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.purple.PurpleRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.red.RedRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.red.RedRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.teal.TealRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.teal.TealRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.wizard.*;
import com.zylr.minescapeaddons.addons.item.armor.robes.yellow.YellowRobesItem;
import com.zylr.minescapeaddons.addons.item.armor.robes.yellow.YellowRobesSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.rune.*;
import com.zylr.minescapeaddons.addons.item.armor.steel.*;
import com.zylr.minescapeaddons.addons.item.armor.white.WhiteArmorItem;
import com.zylr.minescapeaddons.addons.item.armor.white.WhiteArmorSlimItem;
import com.zylr.minescapeaddons.addons.item.armor.white.WhiteChainbodyItem;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.A;

import java.util.Map;

public interface ModItems {

    DeferredRegister.Items REGISTRY = DeferredRegister.createItems(MinescapeAddons.MOD_ID);

    Map<ArmorItem.Type, DeferredItem<GuthansArmorItem>> GUTHANS_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "guthans_armor", GuthansArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<GuthansArmorSlimItem>> GUTHANS_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "guthans_armor_slim", GuthansArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<AhrimsArmorItem>> AHRIMS_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "ahrims_armor", AhrimsArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<AhrimsArmorSlimItem>> AHRIMS_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "ahrims_armor_slim", AhrimsArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<DharoksArmorItem>> DHAROKS_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "dharoks_armor", DharoksArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<DharoksArmorSlimItem>> DHAROKS_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "dharoks_armor_slim", DharoksArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<VeracsArmorItem>> VERACS_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "veracs_armor", VeracsArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<VeracsArmorSlimItem>> VERACS_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "veracs_armor_slim", VeracsArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<ToragsArmorItem>> TORAGS_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "torags_armor", ToragsArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<ToragsArmorSlimItem>> TORAGS_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "torags_armor_slim", ToragsArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<KarilsArmorItem>> KARILS_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "karils_armor", KarilsArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<KarilsArmorSlimItem>> KARILS_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "karils_armor_slim", KarilsArmorSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<BronzeArmorItem>> BRONZE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "bronze_armor", BronzeArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzePlateskirtItem>> BRONZE_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "bronze_plateskirt_armor", BronzePlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzeArmorSlimItem>> BRONZE_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "bronze_armor_slim", BronzeArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzeArmorTrimItem>> BRONZE_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "bronze_armor_trim", BronzeArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzePlateskirtTrimItem>> BRONZE_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "bronze_plateskirt_armor_trim", BronzePlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzeArmorTrimSlimItem>> BRONZE_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "bronze_armor_trim_slim", BronzeArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzeArmorGoldItem>> BRONZE_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "bronze_armor_gold", BronzeArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzePlateskirtGoldItem>> BRONZE_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "bronze_plateskirt_armor_gold", BronzePlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzeArmorGoldSlimItem>> BRONZE_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "bronze_armor_gold_slim", BronzeArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BronzeChainbodyItem>> BRONZE_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "bronze_chainbody", BronzeChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<IronArmorItem>> IRON_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "iron_armor", IronArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<IronPlateskirtItem>> IRON_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "iron_plateskirt_armor", IronPlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<IronArmorSlimItem>> IRON_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "iron_armor_slim", IronArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<IronArmorTrimItem>> IRON_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "iron_armor_trim", IronArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<IronPlateskirtTrimItem>> IRON_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "iron_plateskirt_armor_trim", IronPlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<IronArmorTrimSlimItem>> IRON_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "iron_armor_trim_slim", IronArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<IronArmorGoldItem>> IRON_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "iron_armor_gold", IronArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<IronPlateskirtGoldItem>> IRON_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "iron_plateskirt_armor_gold", IronPlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<IronArmorGoldSlimItem>> IRON_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "iron_armor_gold_slim", IronArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<IronChainbodyItem>> IRON_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "iron_chainbody", IronChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<SteelArmorItem>> STEEL_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "steel_armor", SteelArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelPlateskirtItem>> STEEL_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "steel_plateskirt_armor", SteelPlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelArmorSlimItem>> STEEL_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "steel_armor_slim", SteelArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelArmorTrimItem>> STEEL_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "steel_armor_trim", SteelArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelPlateskirtTrimItem>> STEEL_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "steel_plateskirt_armor_trim", SteelPlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelArmorTrimSlimItem>> STEEL_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "steel_armor_trim_slim", SteelArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelArmorGoldItem>> STEEL_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "steel_armor_gold", SteelArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelPlateskirtGoldItem>> STEEL_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "steel_plateskirt_armor_gold", SteelPlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelArmorGoldSlimItem>> STEEL_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "steel_armor_gold_slim", SteelArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<SteelChainbodyItem>> STEEL_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "steel_chainbody", SteelChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<BlackArmorItem>> BLACK_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "black_armor", BlackArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackPlateskirtItem>> BLACK_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "black_plateskirt_armor", BlackPlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackArmorSlimItem>> BLACK_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "black_armor_slim", BlackArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackArmorTrimItem>> BLACK_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "black_armor_trim", BlackArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackPlateskirtTrimItem>> BLACK_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "black_plateskirt_armor_trim", BlackPlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackArmorTrimSlimItem>> BLACK_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "black_armor_trim_slim", BlackArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackArmorGoldItem>> BLACK_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "black_armor_gold", BlackArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackPlateskirtGoldItem>> BLACK_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "black_plateskirt_armor_gold", BlackPlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackArmorGoldSlimItem>> BLACK_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "black_armor_gold_slim", BlackArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackChainbodyItem>> BLACK_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "black_chainbody", BlackChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<WhiteArmorItem>> WHITE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "white_armor", WhiteArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<WhiteArmorSlimItem>> WHITE_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "white_armor_slim", WhiteArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<WhiteChainbodyItem>> WHITE_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "white_chainbody", WhiteChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<MithrilArmorItem>> MITHRIL_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "mithril_armor", MithrilArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilPlateskirtItem>> MITHRIL_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "mithril_plateskirt_armor", MithrilPlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilArmorSlimItem>> MITHRIL_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "mithril_armor_slim", MithrilArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilArmorTrimItem>> MITHRIL_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "mithril_armor_trim", MithrilArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilPlateskirtTrimItem>> MITHRIL_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "mithril_plateskirt_armor_trim", MithrilPlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilArmorTrimSlimItem>> MITHRIL_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "mithril_armor_trim_slim", MithrilArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilArmorGoldItem>> MITHRIL_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "mithril_armor_gold", MithrilArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilPlateskirtGoldItem>> MITHRIL_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "mithril_plateskirt_armor_gold", MithrilPlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilArmorGoldSlimItem>> MITHRIL_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "mithril_armor_gold_slim", MithrilArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<MithrilChainbodyItem>> MITHRIL_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "mithril_chainbody", MithrilChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<AdamantArmorItem>> ADAMANT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "adamant_armor", AdamantArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantPlateskirtItem>> ADAMANT_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "adamant_plateskirt_armor", AdamantPlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantArmorSlimItem>> ADAMANT_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "adamant_armor_slim", AdamantArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantArmorTrimItem>> ADAMANT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "adamant_armor_trim", AdamantArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantPlateskirtTrimItem>> ADAMANT_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "adamant_plateskirt_armor_trim", AdamantPlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantArmorTrimSlimItem>> ADAMANT_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "adamant_armor_trim_slim", AdamantArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantArmorGoldItem>> ADAMANT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "adamant_armor_gold", AdamantArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantPlateskirtGoldItem>> ADAMANT_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "adamant_plateskirt_armor_gold", AdamantPlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantArmorGoldSlimItem>> ADAMANT_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "adamant_armor_gold_slim", AdamantArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<AdamantChainbodyItem>> ADAMANT_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "adamant_chainbody", AdamantChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<RuneArmorItem>> RUNE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "rune_armor", RuneArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<RunePlateskirtItem>> RUNE_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "rune_plateskirt_armor", RunePlateskirtItem::new);
    Map<ArmorItem.Type, DeferredItem<RuneArmorSlimItem>> RUNE_ARMOR_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "rune_armor_slim", RuneArmorSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<RuneArmorTrimItem>> RUNE_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "rune_armor_trim", RuneArmorTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<RunePlateskirtTrimItem>> RUNE_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "rune_plateskirt_armor_trim", RunePlateskirtTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<RuneArmorTrimSlimItem>> RUNE_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "rune_armor_trim_slim", RuneArmorTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<RuneArmorGoldItem>> RUNE_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "rune_armor_gold", RuneArmorGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<RunePlateskirtGoldItem>> RUNE_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "rune_plateskirt_armor_gold", RunePlateskirtGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<RuneArmorGoldSlimItem>> RUNE_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "rune_armor_gold_slim", RuneArmorGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<RuneChainbodyItem>> RUNE_CHAINBODY = AbstractArmorItem.createRegistry(REGISTRY, "rune_chainbody", RuneChainbodyItem::new);

    Map<ArmorItem.Type, DeferredItem<BlueWizardRobesItem>> BLUE_WIZARD_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "blue_wizard_robes", BlueWizardRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<BlueWizardRobesSlimItem>> BLUE_WIZARD_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "blue_wizard_robes_slim", BlueWizardRobesSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlueWizardRobesTrimItem>> BLUE_WIZARD_ROBES_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "blue_wizard_robes_trim", BlueWizardRobesTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlueWizardRobesTrimSlimItem>> BLUE_WIZARD_ROBES_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "blue_wizard_robes_trim_slim", BlueWizardRobesTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlueWizardRobesGoldItem>> BLUE_WIZARD_ROBES_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "blue_wizard_robes_gold", BlueWizardRobesGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<BlueWizardRobesGoldSlimItem>> BLUE_WIZARD_ROBES_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "blue_wizard_robes_gold_slim", BlueWizardRobesGoldSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<BlackWizardRobesItem>> BLACK_WIZARD_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "black_wizard_robes", BlackWizardRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackWizardRobesSlimItem>> BLACK_WIZARD_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "black_wizard_robes_slim", BlackWizardRobesSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackWizardRobesTrimItem>> BLACK_WIZARD_ROBES_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "black_wizard_robes_trim", BlackWizardRobesTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackWizardRobesTrimSlimItem>> BLACK_WIZARD_ROBES_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "black_wizard_robes_trim_slim", BlackWizardRobesTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackWizardRobesGoldItem>> BLACK_WIZARD_ROBES_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "black_wizard_robes_gold", BlackWizardRobesGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<BlackWizardRobesGoldSlimItem>> BLACK_WIZARD_ROBES_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "black_wizard_robes_gold_slim", BlackWizardRobesGoldSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<ElderChaosRobesItem>> ELDER_CHAOS_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "elder_chaos_robes", ElderChaosRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<ElderChaosRobesSlimItem>> ELDER_CHAOS_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "elder_chaos_robes_slim", ElderChaosRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<PinkSkirtItem>> PINK_SKIRT = AbstractArmorItem.createRegistry(REGISTRY, "pink_skirt", PinkSkirtItem::new);

    Map<ArmorItem.Type, DeferredItem<MonksRobeItem>> MONKS_ROBE = AbstractArmorItem.createRegistry(REGISTRY, "monks_robe", MonksRobeItem::new);
    Map<ArmorItem.Type, DeferredItem<MonksRobeSlimItem>> MONKS_ROBE_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "monks_robe_slim", MonksRobeSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<MonksRobeTrimItem>> MONKS_ROBE_TRIM = AbstractArmorItem.createRegistry(REGISTRY, "monks_robe_trim", MonksRobeTrimItem::new);
    Map<ArmorItem.Type, DeferredItem<MonksRobeTrimSlimItem>> MONKS_ROBE_TRIM_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "monks_robe_trim_slim", MonksRobeTrimSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<MonksRobeGoldItem>> MONKS_ROBE_GOLD = AbstractArmorItem.createRegistry(REGISTRY, "monks_robe_gold", MonksRobeGoldItem::new);
    Map<ArmorItem.Type, DeferredItem<MonksRobeGoldSlimItem>> MONKS_ROBE_GOLD_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "monks_robe_gold_slim", MonksRobeGoldSlimItem::new);
    Map<ArmorItem.Type, DeferredItem<ZamorakMonkRobeItem>> ZAMORAK_MONK_ROBE = AbstractArmorItem.createRegistry(REGISTRY, "zamorak_monk_robe", ZamorakMonkRobeItem::new);
    Map<ArmorItem.Type, DeferredItem<ZamorakMonkRobeSlimItem>> ZAMORAK_MONK_ROBE_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "zamorak_monk_robe_slim", ZamorakMonkRobeSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<PriestGownRobesItem>> PRIEST_GOWN_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "priest_gown_robes", PriestGownRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<PriestGownRobesSlimItem>> PRIEST_GOWN_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "priest_gown_robes_slim", PriestGownRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<DruidsRobesItem>> DRUIDS_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "druids_robes", DruidsRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<DruidsRobesSlimItem>> DRUIDS_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "druids_robes_slim", DruidsRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<PurpleRobesItem>> PURPLE_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "purple_robes", PurpleRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<PurpleRobesSlimItem>> PURPLE_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "purple_robes_slim", PurpleRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<GreyRobesItem>> GREY_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "grey_robes", GreyRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<GreyRobesSlimItem>> GREY_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "grey_robes_slim", GreyRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<YellowRobesItem>> YELLOW_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "yellow_robes", YellowRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<YellowRobesSlimItem>> YELLOW_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "yellow_robes_slim", YellowRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<TealRobesItem>> TEAL_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "teal_robes", TealRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<TealRobesSlimItem>> TEAL_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "teal_robes_slim", TealRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<RedRobesItem>> RED_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "red_robes", RedRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<RedRobesSlimItem>> RED_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "red_robes_slim", RedRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<HamRobesItem>> HAM_ROBES = AbstractArmorItem.createRegistry(REGISTRY, "ham_robes", HamRobesItem::new);
    Map<ArmorItem.Type, DeferredItem<HamRobesSlimItem>> HAM_ROBES_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "ham_robes_slim", HamRobesSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<BlackDhideArmorItem>> BLACK_DHIDE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "black_dhide_armor", BlackDhideArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<GreenDhideArmorItem>> GREEN_DHIDE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "green_dhide_armor", GreenDhideArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<BlueDhideArmorItem>> BLUE_DHIDE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "blue_dhide_armor", BlueDhideArmorItem::new);
    Map<ArmorItem.Type, DeferredItem<RedDhideArmorItem>> RED_DHIDE_ARMOR = AbstractArmorItem.createRegistry(REGISTRY, "red_dhide_armor", RedDhideArmorItem::new);

    Map<ArmorItem.Type, DeferredItem<BuildersCostumeItem>> BUILDERS_COSTUME = AbstractArmorItem.createRegistry(REGISTRY, "builders_costume", BuildersCostumeItem::new);
    Map<ArmorItem.Type, DeferredItem<BuildersCostumeSlimItem>> BUILDERS_COSTUME_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "builders_costume_slim", BuildersCostumeSlimItem::new);

    Map<ArmorItem.Type, DeferredItem<MysticRobesBlueItem>> MYSTIC_ROBES_BLUE = AbstractArmorItem.createRegistry(REGISTRY, "mystic_robes_blue", MysticRobesBlueItem::new);
    Map<ArmorItem.Type, DeferredItem<MysticRobesBlueSlimItem>> MYSTIC_ROBES_BLUE_SLIM = AbstractArmorItem.createRegistry(REGISTRY, "mystic_robes_blue_slim", MysticRobesBlueSlimItem::new);
}
