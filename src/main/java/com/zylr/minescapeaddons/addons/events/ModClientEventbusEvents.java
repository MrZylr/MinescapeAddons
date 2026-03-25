package com.zylr.minescapeaddons.addons.events;

import com.zylr.minescapeaddons.addons.item.ModItems;
import com.zylr.minescapeaddons.addons.item.armor.AbstractArmorItem;
import com.zylr.minescapeaddons.addons.item.armor.client.ArmorClientExtension;
import com.zylr.minescapeaddons.addons.item.armor.client.model.*;
import com.zylr.minescapeaddons.addons.item.armor.client.provider.ArmorModelProvider;
import com.zylr.minescapeaddons.addons.item.armor.client.provider.SimpleModelProvider;
import com.zylr.minescapeaddons.addons.listeners.ResourceReloadListener;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;

public class ModClientEventbusEvents {
    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent e) {


        registerArmorExtension(ModItems.GUTHANS_ARMOR, e, new SimpleModelProvider(GuthansArmorModel::createBodyLayer, GuthansArmorModel::new));
        registerArmorExtension(ModItems.GUTHANS_ARMOR_SLIM, e, new SimpleModelProvider(GuthansArmorSlimModel::createBodyLayer, GuthansArmorSlimModel::new));
        registerArmorExtension(ModItems.AHRIMS_ARMOR, e, new SimpleModelProvider(AhrimsArmorModel::createBodyLayer, AhrimsArmorModel::new));
        registerArmorExtension(ModItems.AHRIMS_ARMOR_SLIM, e, new SimpleModelProvider(AhrimsArmorSlimModel::createBodyLayer, AhrimsArmorSlimModel::new));
        registerArmorExtension(ModItems.DHAROKS_ARMOR, e, new SimpleModelProvider(DharoksArmorModel::createBodyLayer, DharoksArmorModel::new));
        registerArmorExtension(ModItems.DHAROKS_ARMOR_SLIM, e, new SimpleModelProvider(DharoksArmorSlimModel::createBodyLayer, DharoksArmorSlimModel::new));
        registerArmorExtension(ModItems.VERACS_ARMOR, e, new SimpleModelProvider(VeracsArmorModel::createBodyLayer, VeracsArmorModel::new));
        registerArmorExtension(ModItems.VERACS_ARMOR_SLIM, e, new SimpleModelProvider(VeracsArmorSlimModel::createBodyLayer, VeracsArmorSlimModel::new));
        registerArmorExtension(ModItems.TORAGS_ARMOR, e, new SimpleModelProvider(ToragsArmorModel::createBodyLayer, ToragsArmorModel::new));
        registerArmorExtension(ModItems.TORAGS_ARMOR_SLIM, e, new SimpleModelProvider(ToragsArmorSlimModel::createBodyLayer, ToragsArmorSlimModel::new));
        registerArmorExtension(ModItems.KARILS_ARMOR, e, new SimpleModelProvider(KarilsArmorModel::createBodyLayer, KarilsArmorModel::new));
        registerArmorExtension(ModItems.KARILS_ARMOR_SLIM, e, new SimpleModelProvider(KarilsArmorSlimModel::createBodyLayer, KarilsArmorSlimModel::new));

        registerArmorExtension(ModItems.BRONZE_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BRONZE_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BRONZE_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.IRON_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.IRON_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.IRON_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.IRON_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.IRON_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.IRON_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.STEEL_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.STEEL_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.STEEL_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.STEEL_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.STEEL_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.STEEL_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.BLACK_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BLACK_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BLACK_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.BLACK_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.BLACK_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.WHITE_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.WHITE_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.WHITE_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.MITHRIL_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.MITHRIL_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.MITHRIL_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.ADAMANT_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.ADAMANT_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.ADAMANT_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.RUNE_ARMOR, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.RUNE_PLATESKIRT_ARMOR, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_TRIM, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.RUNE_PLATESKIRT_ARMOR_TRIM, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_TRIM_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_GOLD, e, new SimpleModelProvider(MetalArmorModel::createBodyLayer, MetalArmorModel::new));
        registerArmorExtension(ModItems.RUNE_PLATESKIRT_ARMOR_GOLD, e, new SimpleModelProvider(PlateskirtArmorModel::createBodyLayer, PlateskirtArmorModel::new));
        registerArmorExtension(ModItems.RUNE_ARMOR_GOLD_SLIM, e, new SimpleModelProvider(MetalArmorSlimModel::createBodyLayer, MetalArmorSlimModel::new));
        registerArmorExtension(ModItems.RUNE_CHAINBODY, e, new SimpleModelProvider(ChainbodyModel::createBodyLayer, ChainbodyModel::new));

        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_TRIM, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_TRIM_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_GOLD, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLUE_WIZARD_ROBES_GOLD_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_TRIM, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_TRIM_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_GOLD, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.BLACK_WIZARD_ROBES_GOLD_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.ELDER_CHAOS_ROBES, e, new SimpleModelProvider(ElderChaosRobesModel::createBodyLayer, ElderChaosRobesModel::new));
        registerArmorExtension(ModItems.ELDER_CHAOS_ROBES_SLIM, e, new SimpleModelProvider(ElderChaosRobesSlimModel::createBodyLayer, ElderChaosRobesSlimModel::new));

        registerArmorExtension(ModItems.PINK_SKIRT, e, new SimpleModelProvider(PinkSkirtArmorModel::createBodyLayer, PinkSkirtArmorModel::new));

        registerArmorExtension(ModItems.MONKS_ROBE, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_TRIM, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_TRIM_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_GOLD, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.MONKS_ROBE_GOLD_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));
        registerArmorExtension(ModItems.ZAMORAK_MONK_ROBE, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.ZAMORAK_MONK_ROBE_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.PRIEST_GOWN_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.PRIEST_GOWN_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.DRUIDS_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.DRUIDS_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.PURPLE_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.PURPLE_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.GREY_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.GREY_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.YELLOW_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.YELLOW_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.TEAL_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.TEAL_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.RED_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.RED_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.HAM_ROBES, e, new SimpleModelProvider(RobesArmorModel::createBodyLayer, RobesArmorModel::new));
        registerArmorExtension(ModItems.HAM_ROBES_SLIM, e, new SimpleModelProvider(RobesArmorSlimModel::createBodyLayer, RobesArmorSlimModel::new));

        registerArmorExtension(ModItems.BLACK_DHIDE_ARMOR, e, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));
        registerArmorExtension(ModItems.GREEN_DHIDE_ARMOR, e, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));
        registerArmorExtension(ModItems.RED_DHIDE_ARMOR, e, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));
        registerArmorExtension(ModItems.BLUE_DHIDE_ARMOR, e, new SimpleModelProvider(DhideArmorModel::createBodyLayer, DhideArmorModel::new));

        registerArmorExtension(ModItems.BUILDERS_COSTUME, e, new SimpleModelProvider(BuildersCostumeModel::createBodyLayer, BuildersCostumeModel::new));
        registerArmorExtension(ModItems.BUILDERS_COSTUME_SLIM, e, new SimpleModelProvider(BuildersCostumeSlimModel::createBodyLayer, BuildersCostumeSlimModel::new));

        registerArmorExtension(ModItems.MYSTIC_ROBES_BLUE, e, new SimpleModelProvider(MysticRobesModel::createBodyLayer, MysticRobesModel::new));
        registerArmorExtension(ModItems.MYSTIC_ROBES_BLUE_SLIM, e, new SimpleModelProvider(MysticRobesSlimModel::createBodyLayer, MysticRobesSlimModel::new));
    }

    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        // Register the resource reload listener for minimap cache clearing
        event.registerReloadListener(new ResourceReloadListener());
    }

    @SuppressWarnings("unchecked")
    private static <T extends AbstractArmorItem> void registerArmorExtension(Map<ArmorItem.Type, DeferredItem<T>> map, RegisterClientExtensionsEvent event, ArmorModelProvider provider) {
        event.registerItem(new ArmorClientExtension(provider), map.values().toArray(DeferredItem[]::new));
    }
}
