package com.zylr.client.items;

import com.zylr.client.items.ModItems;
import com.zylr.client.items.armor.AbstractArmorItem;
import com.zylr.client.items.armor.client.ArmorClientExtension;
import com.zylr.client.items.armor.client.model.*;
import com.zylr.client.items.armor.client.provider.ArmorModelProvider;
import com.zylr.client.items.armor.client.provider.SimpleModelProvider;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class ModClientEventbusEvents {
    public static void register() {


        registerArmorExtension(ModItems.DRAGON_ARMOR, new SimpleModelProvider(DragonArmorModel::createBodyLayer, DragonArmorModel::new));
        registerArmorExtension(ModItems.DRAGON_PLATESKIRT_ARMOR, new SimpleModelProvider(DragonPlateskirtArmorModel::createBodyLayer, DragonPlateskirtArmorModel::new));

        registerArmorExtension(ModItems.GUTHANS_ARMOR, new SimpleModelProvider(GuthansArmorModel::createBodyLayer, GuthansArmorModel::new));
        registerArmorExtension(ModItems.GUTHANS_ARMOR_SLIM, new SimpleModelProvider(GuthansArmorSlimModel::createBodyLayer, GuthansArmorSlimModel::new));
        registerArmorExtension(ModItems.AHRIMS_ARMOR, new SimpleModelProvider(AhrimsArmorModel::createBodyLayer, AhrimsArmorModel::new));
        registerArmorExtension(ModItems.AHRIMS_ARMOR_SLIM, new SimpleModelProvider(AhrimsArmorSlimModel::createBodyLayer, AhrimsArmorSlimModel::new));
        registerArmorExtension(ModItems.DHAROKS_ARMOR, new SimpleModelProvider(DharoksArmorModel::createBodyLayer, DharoksArmorModel::new));
        registerArmorExtension(ModItems.DHAROKS_ARMOR_SLIM, new SimpleModelProvider(DharoksArmorSlimModel::createBodyLayer, DharoksArmorSlimModel::new));
        registerArmorExtension(ModItems.VERACS_ARMOR, new SimpleModelProvider(VeracsArmorModel::createBodyLayer, VeracsArmorModel::new));
        registerArmorExtension(ModItems.VERACS_ARMOR_SLIM, new SimpleModelProvider(VeracsArmorSlimModel::createBodyLayer, VeracsArmorSlimModel::new));
        registerArmorExtension(ModItems.TORAGS_ARMOR, new SimpleModelProvider(ToragsArmorModel::createBodyLayer, ToragsArmorModel::new));
        registerArmorExtension(ModItems.TORAGS_ARMOR_SLIM, new SimpleModelProvider(ToragsArmorSlimModel::createBodyLayer, ToragsArmorSlimModel::new));
        registerArmorExtension(ModItems.KARILS_ARMOR, new SimpleModelProvider(KarilsArmorModel::createBodyLayer, KarilsArmorModel::new));
        registerArmorExtension(ModItems.KARILS_ARMOR_SLIM, new SimpleModelProvider(KarilsArmorSlimModel::createBodyLayer, KarilsArmorSlimModel::new));

        registerArmorExtension(ModItems.BRONZE_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BRONZE_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.IRON_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.IRON_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.IRON_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.IRON_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.IRON_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.STEEL_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.STEEL_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.STEEL_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.STEEL_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.STEEL_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.BLACK_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BLACK_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BLACK_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BLACK_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.WHITE_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.WHITE_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.WHITE_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.MITHRIL_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.MITHRIL_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.ADAMANT_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.ADAMANT_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.RUNE_ARMOR, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.RUNE_PLATESKIRT_ARMOR, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_TRIM, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.RUNE_PLATESKIRT_ARMOR_TRIM, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_TRIM_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_GOLD, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.RUNE_PLATESKIRT_ARMOR_GOLD, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_GOLD_SLIM, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.RUNE_CHAINBODY, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_TRIM, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_TRIM_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_GOLD, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_GOLD_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_TRIM, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_TRIM_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_GOLD, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_GOLD_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.ELDER_CHAOS_ROBES, new SimpleModelProvider(ElderChaosRobesModel::createBodyLayer, ElderChaosRobesModel::new));
        registerArmorExtension(ModItems.ELDER_CHAOS_ROBES_SLIM, new SimpleModelProvider(ElderChaosRobesSlimModel::createBodyLayer, ElderChaosRobesSlimModel::new));

        registerArmorExtension(ModItems.PINK_SKIRT, new SimpleModelProvider(PinkSkirtArmorModel::createBodyLayer, PinkSkirtArmorModel::new));

        registerArmorExtension(ModItems.MONKS_ROBE, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_TRIM, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_TRIM_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_GOLD, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_GOLD_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.ZAMORAK_MONK_ROBE, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.ZAMORAK_MONK_ROBE_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.PRIEST_GOWN_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.PRIEST_GOWN_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.DRUIDS_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.DRUIDS_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.PURPLE_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.PURPLE_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.GREY_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.GREY_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.YELLOW_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.YELLOW_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.TEAL_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.TEAL_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.SNAKESKIN_ARMOR, new SimpleModelProvider(SnakskinModel::createBodyLayer, SnakskinModel::new));
        registerArmorExtension(ModItems.SNAKESKIN_ARMOR_SLIM, new SimpleModelProvider(SnakskinSlimModel::createBodyLayer, SnakskinSlimModel::new));

        registerArmorExtension(ModItems.LEATHER_ARMOR, new SimpleModelProvider(LeatherModel::createBodyLayer, LeatherModel::new));
        registerArmorExtension(ModItems.LEATHER_GOLD_ARMOR, new SimpleModelProvider(LeatherModel::createBodyLayer, LeatherModel::new));
        registerArmorExtension(ModItems.LEATHER_HARD_ARMOR, new SimpleModelProvider(LeatherModel::createBodyLayer, LeatherModel::new));
        registerArmorExtension(ModItems.STUDDED_ARMOR, new SimpleModelProvider(StuddedModel::createBodyLayer, StuddedModel::new));
        registerArmorExtension(ModItems.STUDDED_TRIM_ARMOR, new SimpleModelProvider(StuddedModel::createBodyLayer, StuddedModel::new));
        registerArmorExtension(ModItems.STUDDED_GOLD_ARMOR, new SimpleModelProvider(StuddedModel::createBodyLayer, StuddedModel::new));

        registerArmorExtension(ModItems.RED_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.RED_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.HAM_ROBES, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.HAM_ROBES_SLIM, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.BLACK_DHIDE_ARMOR, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));
        registerArmorExtension(ModItems.GREEN_DHIDE_ARMOR, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));
        registerArmorExtension(ModItems.RED_DHIDE_ARMOR, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));
        registerArmorExtension(ModItems.BLUE_DHIDE_ARMOR, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));

        registerArmorExtension(ModItems.BUILDERS_COSTUME, new SimpleModelProvider(BuildersCostumeModel::createBodyLayer, BuildersCostumeModel::new));
        registerArmorExtension(ModItems.BUILDERS_COSTUME_SLIM, new SimpleModelProvider(BuildersCostumeSlimModel::createBodyLayer, BuildersCostumeSlimModel::new));

        registerArmorExtension(ModItems.MYSTIC_ROBES_BLUE, new SimpleModelProvider(MysticRobesModel::createBodyLayer, MysticRobesModel::new));
        registerArmorExtension(ModItems.MYSTIC_ROBES_BLUE_SLIM, new SimpleModelProvider(MysticRobesSlimModel::createBodyLayer, MysticRobesSlimModel::new));
    }

    private static <T extends AbstractArmorItem> void registerArmorExtension(Map<ArmorType, AbstractArmorItem.RegisteredItem<T>> map, ArmorModelProvider provider) { for (AbstractArmorItem.RegisteredItem<T> item : map.values()) ArmorClientExtension.register(item.get(), provider); }
}
