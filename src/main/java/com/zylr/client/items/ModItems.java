package com.zylr.client.items;

import com.zylr.MinescapeAddon;
import com.zylr.client.items.armor.*;
import com.zylr.client.items.armor.adamant.*;
import com.zylr.client.items.armor.barrows.*;
import com.zylr.client.items.armor.black.*;
import com.zylr.client.items.armor.bronze.*;
import com.zylr.client.items.armor.costume.builder.BuildersCostumeItem;
import com.zylr.client.items.armor.costume.builder.BuildersCostumeSlimItem;
import com.zylr.client.items.armor.dhide.black.BlackDhideArmorItem;
import com.zylr.client.items.armor.dhide.blue.BlueDhideArmorItem;
import com.zylr.client.items.armor.dhide.green.GreenDhideArmorItem;
import com.zylr.client.items.armor.dhide.red.RedDhideArmorItem;
import com.zylr.client.items.armor.dragon.DragonArmorItem;
import com.zylr.client.items.armor.dragon.DragonPlateskirtArmorItem;
import com.zylr.client.items.armor.iron.*;
import com.zylr.client.items.armor.leather.*;
import com.zylr.client.items.armor.mithril.*;
import com.zylr.client.items.armor.mystic.MysticRobesBlueItem;
import com.zylr.client.items.armor.mystic.MysticRobesBlueSlimItem;
import com.zylr.client.items.armor.robes.druids.DruidsRobesItem;
import com.zylr.client.items.armor.robes.druids.DruidsRobesSlimItem;
import com.zylr.client.items.armor.robes.elderchaos.ElderChaosRobesItem;
import com.zylr.client.items.armor.robes.elderchaos.ElderChaosRobesSlimItem;
import com.zylr.client.items.armor.robes.grey.GreyRobesItem;
import com.zylr.client.items.armor.robes.grey.GreyRobesSlimItem;
import com.zylr.client.items.armor.robes.ham.HamRobesItem;
import com.zylr.client.items.armor.robes.ham.HamRobesSlimItem;
import com.zylr.client.items.armor.robes.monks.*;
import com.zylr.client.items.armor.robes.pink.PinkSkirtItem;
import com.zylr.client.items.armor.robes.priestgown.PriestGownRobesItem;
import com.zylr.client.items.armor.robes.priestgown.PriestGownRobesSlimItem;
import com.zylr.client.items.armor.robes.purple.PurpleRobesItem;
import com.zylr.client.items.armor.robes.purple.PurpleRobesSlimItem;
import com.zylr.client.items.armor.robes.red.RedRobesItem;
import com.zylr.client.items.armor.robes.red.RedRobesSlimItem;
import com.zylr.client.items.armor.robes.teal.TealRobesItem;
import com.zylr.client.items.armor.robes.teal.TealRobesSlimItem;
import com.zylr.client.items.armor.robes.wizard.*;
import com.zylr.client.items.armor.robes.yellow.YellowRobesItem;
import com.zylr.client.items.armor.robes.yellow.YellowRobesSlimItem;
import com.zylr.client.items.armor.rune.*;
import com.zylr.client.items.armor.snakeskin.SnakeskinArmorItem;
import com.zylr.client.items.armor.steel.*;
import com.zylr.client.items.armor.white.WhiteArmorItem;
import com.zylr.client.items.armor.white.WhiteArmorSlimItem;
import com.zylr.client.items.armor.white.WhiteChainbodyItem;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public interface ModItems {

    static void register() {
        // Accessing this class initializes the static item registrations.
    }

    Map<ArmorType, AbstractArmorItem.RegisteredItem<DragonArmorItem>> DRAGON_ARMOR = AbstractArmorItem.createRegistry("dragon_armor", DragonArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<DragonPlateskirtArmorItem>> DRAGON_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("dragon_plateskirt_armor", DragonPlateskirtArmorItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<GuthansArmorItem>> GUTHANS_ARMOR = AbstractArmorItem.createRegistry("guthans_armor", GuthansArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<GuthansArmorSlimItem>> GUTHANS_ARMOR_SLIM = AbstractArmorItem.createRegistry("guthans_armor_slim", GuthansArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AhrimsArmorItem>> AHRIMS_ARMOR = AbstractArmorItem.createRegistry("ahrims_armor", AhrimsArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AhrimsArmorSlimItem>> AHRIMS_ARMOR_SLIM = AbstractArmorItem.createRegistry("ahrims_armor_slim", AhrimsArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<DharoksArmorItem>> DHAROKS_ARMOR = AbstractArmorItem.createRegistry("dharoks_armor", DharoksArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<DharoksArmorSlimItem>> DHAROKS_ARMOR_SLIM = AbstractArmorItem.createRegistry("dharoks_armor_slim", DharoksArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<VeracsArmorItem>> VERACS_ARMOR = AbstractArmorItem.createRegistry("veracs_armor", VeracsArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<VeracsArmorSlimItem>> VERACS_ARMOR_SLIM = AbstractArmorItem.createRegistry("veracs_armor_slim", VeracsArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<ToragsArmorItem>> TORAGS_ARMOR = AbstractArmorItem.createRegistry("torags_armor", ToragsArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<ToragsArmorSlimItem>> TORAGS_ARMOR_SLIM = AbstractArmorItem.createRegistry("torags_armor_slim", ToragsArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<KarilsArmorItem>> KARILS_ARMOR = AbstractArmorItem.createRegistry("karils_armor", KarilsArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<KarilsArmorSlimItem>> KARILS_ARMOR_SLIM = AbstractArmorItem.createRegistry("karils_armor_slim", KarilsArmorSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeArmorItem>> BRONZE_ARMOR = AbstractArmorItem.createRegistry("bronze_armor", BronzeArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzePlateskirtItem>> BRONZE_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("bronze_plateskirt_armor", BronzePlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeArmorSlimItem>> BRONZE_ARMOR_SLIM = AbstractArmorItem.createRegistry("bronze_armor_slim", BronzeArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeArmorTrimItem>> BRONZE_ARMOR_TRIM = AbstractArmorItem.createRegistry("bronze_armor_trim", BronzeArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzePlateskirtTrimItem>> BRONZE_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("bronze_plateskirt_armor_trim", BronzePlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeArmorTrimSlimItem>> BRONZE_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("bronze_armor_trim_slim", BronzeArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeArmorGoldItem>> BRONZE_ARMOR_GOLD = AbstractArmorItem.createRegistry("bronze_armor_gold", BronzeArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzePlateskirtGoldItem>> BRONZE_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("bronze_plateskirt_armor_gold", BronzePlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeArmorGoldSlimItem>> BRONZE_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("bronze_armor_gold_slim", BronzeArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BronzeChainbodyItem>> BRONZE_CHAINBODY = AbstractArmorItem.createRegistry("bronze_chainbody", BronzeChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronArmorItem>> IRON_ARMOR = AbstractArmorItem.createRegistry("iron_armor", IronArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronPlateskirtItem>> IRON_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("iron_plateskirt_armor", IronPlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronArmorSlimItem>> IRON_ARMOR_SLIM = AbstractArmorItem.createRegistry("iron_armor_slim", IronArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronArmorTrimItem>> IRON_ARMOR_TRIM = AbstractArmorItem.createRegistry("iron_armor_trim", IronArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronPlateskirtTrimItem>> IRON_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("iron_plateskirt_armor_trim", IronPlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronArmorTrimSlimItem>> IRON_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("iron_armor_trim_slim", IronArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronArmorGoldItem>> IRON_ARMOR_GOLD = AbstractArmorItem.createRegistry("iron_armor_gold", IronArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronPlateskirtGoldItem>> IRON_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("iron_plateskirt_armor_gold", IronPlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronArmorGoldSlimItem>> IRON_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("iron_armor_gold_slim", IronArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<IronChainbodyItem>> IRON_CHAINBODY = AbstractArmorItem.createRegistry("iron_chainbody", IronChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelArmorItem>> STEEL_ARMOR = AbstractArmorItem.createRegistry("steel_armor", SteelArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelPlateskirtItem>> STEEL_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("steel_plateskirt_armor", SteelPlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelArmorSlimItem>> STEEL_ARMOR_SLIM = AbstractArmorItem.createRegistry("steel_armor_slim", SteelArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelArmorTrimItem>> STEEL_ARMOR_TRIM = AbstractArmorItem.createRegistry("steel_armor_trim", SteelArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelPlateskirtTrimItem>> STEEL_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("steel_plateskirt_armor_trim", SteelPlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelArmorTrimSlimItem>> STEEL_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("steel_armor_trim_slim", SteelArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelArmorGoldItem>> STEEL_ARMOR_GOLD = AbstractArmorItem.createRegistry("steel_armor_gold", SteelArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelPlateskirtGoldItem>> STEEL_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("steel_plateskirt_armor_gold", SteelPlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelArmorGoldSlimItem>> STEEL_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("steel_armor_gold_slim", SteelArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SteelChainbodyItem>> STEEL_CHAINBODY = AbstractArmorItem.createRegistry("steel_chainbody", SteelChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackArmorItem>> BLACK_ARMOR = AbstractArmorItem.createRegistry("black_armor", BlackArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackPlateskirtItem>> BLACK_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("black_plateskirt_armor", BlackPlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackArmorSlimItem>> BLACK_ARMOR_SLIM = AbstractArmorItem.createRegistry("black_armor_slim", BlackArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackArmorTrimItem>> BLACK_ARMOR_TRIM = AbstractArmorItem.createRegistry("black_armor_trim", BlackArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackPlateskirtTrimItem>> BLACK_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("black_plateskirt_armor_trim", BlackPlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackArmorTrimSlimItem>> BLACK_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("black_armor_trim_slim", BlackArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackArmorGoldItem>> BLACK_ARMOR_GOLD = AbstractArmorItem.createRegistry("black_armor_gold", BlackArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackPlateskirtGoldItem>> BLACK_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("black_plateskirt_armor_gold", BlackPlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackArmorGoldSlimItem>> BLACK_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("black_armor_gold_slim", BlackArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackChainbodyItem>> BLACK_CHAINBODY = AbstractArmorItem.createRegistry("black_chainbody", BlackChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<WhiteArmorItem>> WHITE_ARMOR = AbstractArmorItem.createRegistry("white_armor", WhiteArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<WhiteArmorSlimItem>> WHITE_ARMOR_SLIM = AbstractArmorItem.createRegistry("white_armor_slim", WhiteArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<WhiteChainbodyItem>> WHITE_CHAINBODY = AbstractArmorItem.createRegistry("white_chainbody", WhiteChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilArmorItem>> MITHRIL_ARMOR = AbstractArmorItem.createRegistry("mithril_armor", MithrilArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilPlateskirtItem>> MITHRIL_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("mithril_plateskirt_armor", MithrilPlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilArmorSlimItem>> MITHRIL_ARMOR_SLIM = AbstractArmorItem.createRegistry("mithril_armor_slim", MithrilArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilArmorTrimItem>> MITHRIL_ARMOR_TRIM = AbstractArmorItem.createRegistry("mithril_armor_trim", MithrilArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilPlateskirtTrimItem>> MITHRIL_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("mithril_plateskirt_armor_trim", MithrilPlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilArmorTrimSlimItem>> MITHRIL_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("mithril_armor_trim_slim", MithrilArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilArmorGoldItem>> MITHRIL_ARMOR_GOLD = AbstractArmorItem.createRegistry("mithril_armor_gold", MithrilArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilPlateskirtGoldItem>> MITHRIL_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("mithril_plateskirt_armor_gold", MithrilPlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilArmorGoldSlimItem>> MITHRIL_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("mithril_armor_gold_slim", MithrilArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MithrilChainbodyItem>> MITHRIL_CHAINBODY = AbstractArmorItem.createRegistry("mithril_chainbody", MithrilChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantArmorItem>> ADAMANT_ARMOR = AbstractArmorItem.createRegistry("adamant_armor", AdamantArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantPlateskirtItem>> ADAMANT_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("adamant_plateskirt_armor", AdamantPlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantArmorSlimItem>> ADAMANT_ARMOR_SLIM = AbstractArmorItem.createRegistry("adamant_armor_slim", AdamantArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantArmorTrimItem>> ADAMANT_ARMOR_TRIM = AbstractArmorItem.createRegistry("adamant_armor_trim", AdamantArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantPlateskirtTrimItem>> ADAMANT_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("adamant_plateskirt_armor_trim", AdamantPlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantArmorTrimSlimItem>> ADAMANT_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("adamant_armor_trim_slim", AdamantArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantArmorGoldItem>> ADAMANT_ARMOR_GOLD = AbstractArmorItem.createRegistry("adamant_armor_gold", AdamantArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantPlateskirtGoldItem>> ADAMANT_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("adamant_plateskirt_armor_gold", AdamantPlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantArmorGoldSlimItem>> ADAMANT_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("adamant_armor_gold_slim", AdamantArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<AdamantChainbodyItem>> ADAMANT_CHAINBODY = AbstractArmorItem.createRegistry("adamant_chainbody", AdamantChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneArmorItem>> RUNE_ARMOR = AbstractArmorItem.createRegistry("rune_armor", RuneArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RunePlateskirtItem>> RUNE_PLATESKIRT_ARMOR = AbstractArmorItem.createRegistry("rune_plateskirt_armor", RunePlateskirtItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneArmorSlimItem>> RUNE_ARMOR_SLIM = AbstractArmorItem.createRegistry("rune_armor_slim", RuneArmorSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneArmorTrimItem>> RUNE_ARMOR_TRIM = AbstractArmorItem.createRegistry("rune_armor_trim", RuneArmorTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RunePlateskirtTrimItem>> RUNE_PLATESKIRT_ARMOR_TRIM = AbstractArmorItem.createRegistry("rune_plateskirt_armor_trim", RunePlateskirtTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneArmorTrimSlimItem>> RUNE_ARMOR_TRIM_SLIM = AbstractArmorItem.createRegistry("rune_armor_trim_slim", RuneArmorTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneArmorGoldItem>> RUNE_ARMOR_GOLD = AbstractArmorItem.createRegistry("rune_armor_gold", RuneArmorGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RunePlateskirtGoldItem>> RUNE_PLATESKIRT_ARMOR_GOLD = AbstractArmorItem.createRegistry("rune_plateskirt_armor_gold", RunePlateskirtGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneArmorGoldSlimItem>> RUNE_ARMOR_GOLD_SLIM = AbstractArmorItem.createRegistry("rune_armor_gold_slim", RuneArmorGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RuneChainbodyItem>> RUNE_CHAINBODY = AbstractArmorItem.createRegistry("rune_chainbody", RuneChainbodyItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueWizardRobesItem>> BLUE_WIZARD_ROBES = AbstractArmorItem.createRegistry("blue_wizard_robes", BlueWizardRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueWizardRobesSlimItem>> BLUE_WIZARD_ROBES_SLIM = AbstractArmorItem.createRegistry("blue_wizard_robes_slim", BlueWizardRobesSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueWizardRobesTrimItem>> BLUE_WIZARD_ROBES_TRIM = AbstractArmorItem.createRegistry("blue_wizard_robes_trim", BlueWizardRobesTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueWizardRobesTrimSlimItem>> BLUE_WIZARD_ROBES_TRIM_SLIM = AbstractArmorItem.createRegistry("blue_wizard_robes_trim_slim", BlueWizardRobesTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueWizardRobesGoldItem>> BLUE_WIZARD_ROBES_GOLD = AbstractArmorItem.createRegistry("blue_wizard_robes_gold", BlueWizardRobesGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueWizardRobesGoldSlimItem>> BLUE_WIZARD_ROBES_GOLD_SLIM = AbstractArmorItem.createRegistry("blue_wizard_robes_gold_slim", BlueWizardRobesGoldSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackWizardRobesItem>> BLACK_WIZARD_ROBES = AbstractArmorItem.createRegistry("black_wizard_robes", BlackWizardRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackWizardRobesSlimItem>> BLACK_WIZARD_ROBES_SLIM = AbstractArmorItem.createRegistry("black_wizard_robes_slim", BlackWizardRobesSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackWizardRobesTrimItem>> BLACK_WIZARD_ROBES_TRIM = AbstractArmorItem.createRegistry("black_wizard_robes_trim", BlackWizardRobesTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackWizardRobesTrimSlimItem>> BLACK_WIZARD_ROBES_TRIM_SLIM = AbstractArmorItem.createRegistry("black_wizard_robes_trim_slim", BlackWizardRobesTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackWizardRobesGoldItem>> BLACK_WIZARD_ROBES_GOLD = AbstractArmorItem.createRegistry("black_wizard_robes_gold", BlackWizardRobesGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackWizardRobesGoldSlimItem>> BLACK_WIZARD_ROBES_GOLD_SLIM = AbstractArmorItem.createRegistry("black_wizard_robes_gold_slim", BlackWizardRobesGoldSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<ElderChaosRobesItem>> ELDER_CHAOS_ROBES = AbstractArmorItem.createRegistry("elder_chaos_robes", ElderChaosRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<ElderChaosRobesSlimItem>> ELDER_CHAOS_ROBES_SLIM = AbstractArmorItem.createRegistry("elder_chaos_robes_slim", ElderChaosRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<PinkSkirtItem>> PINK_SKIRT = AbstractArmorItem.createRegistry("pink_skirt", PinkSkirtItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<MonksRobeItem>> MONKS_ROBE = AbstractArmorItem.createRegistry("monks_robe", MonksRobeItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MonksRobeSlimItem>> MONKS_ROBE_SLIM = AbstractArmorItem.createRegistry("monks_robe_slim", MonksRobeSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MonksRobeTrimItem>> MONKS_ROBE_TRIM = AbstractArmorItem.createRegistry("monks_robe_trim", MonksRobeTrimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MonksRobeTrimSlimItem>> MONKS_ROBE_TRIM_SLIM = AbstractArmorItem.createRegistry("monks_robe_trim_slim", MonksRobeTrimSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MonksRobeGoldItem>> MONKS_ROBE_GOLD = AbstractArmorItem.createRegistry("monks_robe_gold", MonksRobeGoldItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MonksRobeGoldSlimItem>> MONKS_ROBE_GOLD_SLIM = AbstractArmorItem.createRegistry("monks_robe_gold_slim", MonksRobeGoldSlimItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<ZamorakMonkRobeItem>> ZAMORAK_MONK_ROBE = AbstractArmorItem.createRegistry("zamorak_monk_robe", ZamorakMonkRobeItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<ZamorakMonkRobeSlimItem>> ZAMORAK_MONK_ROBE_SLIM = AbstractArmorItem.createRegistry("zamorak_monk_robe_slim", ZamorakMonkRobeSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<PriestGownRobesItem>> PRIEST_GOWN_ROBES = AbstractArmorItem.createRegistry("priest_gown_robes", PriestGownRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<PriestGownRobesSlimItem>> PRIEST_GOWN_ROBES_SLIM = AbstractArmorItem.createRegistry("priest_gown_robes_slim", PriestGownRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<DruidsRobesItem>> DRUIDS_ROBES = AbstractArmorItem.createRegistry("druids_robes", DruidsRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<DruidsRobesSlimItem>> DRUIDS_ROBES_SLIM = AbstractArmorItem.createRegistry("druids_robes_slim", DruidsRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<PurpleRobesItem>> PURPLE_ROBES = AbstractArmorItem.createRegistry("purple_robes", PurpleRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<PurpleRobesSlimItem>> PURPLE_ROBES_SLIM = AbstractArmorItem.createRegistry("purple_robes_slim", PurpleRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<GreyRobesItem>> GREY_ROBES = AbstractArmorItem.createRegistry("grey_robes", GreyRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<GreyRobesSlimItem>> GREY_ROBES_SLIM = AbstractArmorItem.createRegistry("grey_robes_slim", GreyRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<YellowRobesItem>> YELLOW_ROBES = AbstractArmorItem.createRegistry("yellow_robes", YellowRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<YellowRobesSlimItem>> YELLOW_ROBES_SLIM = AbstractArmorItem.createRegistry("yellow_robes_slim", YellowRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<TealRobesItem>> TEAL_ROBES = AbstractArmorItem.createRegistry("teal_robes", TealRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<TealRobesSlimItem>> TEAL_ROBES_SLIM = AbstractArmorItem.createRegistry("teal_robes_slim", TealRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<RedRobesItem>> RED_ROBES = AbstractArmorItem.createRegistry("red_robes", RedRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RedRobesSlimItem>> RED_ROBES_SLIM = AbstractArmorItem.createRegistry("red_robes_slim", RedRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<HamRobesItem>> HAM_ROBES = AbstractArmorItem.createRegistry("ham_robes", HamRobesItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<HamRobesSlimItem>> HAM_ROBES_SLIM = AbstractArmorItem.createRegistry("ham_robes_slim", HamRobesSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<SnakeskinArmorItem>> SNAKESKIN_ARMOR = AbstractArmorItem.createRegistry("snakeskin_armor", SnakeskinArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<SnakeskinArmorItem>> SNAKESKIN_ARMOR_SLIM = AbstractArmorItem.createRegistry("snakeskin_armor_slim", SnakeskinArmorItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<LeatherArmorItem>> LEATHER_ARMOR = AbstractArmorItem.createRegistry("leather_armor", LeatherArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<LeatherGoldArmorItem>> LEATHER_GOLD_ARMOR = AbstractArmorItem.createRegistry("leather_gold_armor", LeatherGoldArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<LeatherHardArmorItem>> LEATHER_HARD_ARMOR = AbstractArmorItem.createRegistry("leather_hard_armor", LeatherHardArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<StuddedArmorItem>> STUDDED_ARMOR = AbstractArmorItem.createRegistry("studded_armor", StuddedArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<StuddedTrimArmorItem>> STUDDED_TRIM_ARMOR = AbstractArmorItem.createRegistry("studded_trim_armor", StuddedTrimArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<StuddedGoldArmorItem>> STUDDED_GOLD_ARMOR = AbstractArmorItem.createRegistry("studded_gold_armor", StuddedGoldArmorItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlackDhideArmorItem>> BLACK_DHIDE_ARMOR = AbstractArmorItem.createRegistry("black_dhide_armor", BlackDhideArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<GreenDhideArmorItem>> GREEN_DHIDE_ARMOR = AbstractArmorItem.createRegistry("green_dhide_armor", GreenDhideArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BlueDhideArmorItem>> BLUE_DHIDE_ARMOR = AbstractArmorItem.createRegistry("blue_dhide_armor", BlueDhideArmorItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<RedDhideArmorItem>> RED_DHIDE_ARMOR = AbstractArmorItem.createRegistry("red_dhide_armor", RedDhideArmorItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<BuildersCostumeItem>> BUILDERS_COSTUME = AbstractArmorItem.createRegistry("builders_costume", BuildersCostumeItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<BuildersCostumeSlimItem>> BUILDERS_COSTUME_SLIM = AbstractArmorItem.createRegistry("builders_costume_slim", BuildersCostumeSlimItem::new);

    Map<ArmorType, AbstractArmorItem.RegisteredItem<MysticRobesBlueItem>> MYSTIC_ROBES_BLUE = AbstractArmorItem.createRegistry("mystic_robes_blue", MysticRobesBlueItem::new);
    Map<ArmorType, AbstractArmorItem.RegisteredItem<MysticRobesBlueSlimItem>> MYSTIC_ROBES_BLUE_SLIM = AbstractArmorItem.createRegistry("mystic_robes_blue_slim", MysticRobesBlueSlimItem::new);
}

