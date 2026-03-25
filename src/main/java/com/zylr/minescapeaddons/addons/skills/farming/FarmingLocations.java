package com.zylr.minescapeaddons.addons.skills.farming;

import java.util.EnumMap;

public enum FarmingLocations {
    AL_KHARID                           (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.AL_KHARID_CACTUS,
                new FarmingPatch("Cactus", PatchType.CACTUS, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.AL_KHARID_CACTUS, false));
    }}),
    DRAYNOR_MANOR                       (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.DRAYNOR_MANOR_SPECIAL_PATCH_BELLADONNA,
                new FarmingPatch("Belladona", PatchType.SPECIAL_PATCH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.DRAYNOR_MANOR_SPECIAL_PATCH_BELLADONNA, false));
    }}),
    ENTRANA                             (-621, -632, -154, -165, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.ENTRANA_HOPS,
                new FarmingPatch("Hops", PatchType.HOPS, null, new int[] {-621, -632, -154, -165}, FarmingPatchLocations.ENTRANA_HOPS, true));
    }}),
    EAST_CATHERBY                       (-474, -478, -451, -455, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.EAST_CATHERBY_FRUIT_TREE,
                new FarmingPatch("Fruit Tree", PatchType.FRUIT_TREE, null, new int[] {-474, -478, -451, -455}, FarmingPatchLocations.EAST_CATHERBY_FRUIT_TREE, true));
    }}),
    EAST_PORT_SARIM                     (120, 116, 74, 70, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.EAST_PORT_SARIM_SPIRIT_TREE,
                new FarmingPatch("Spirit Tree", PatchType.SPIRIT_TREE, null, new int[] {120, 116, 74, 70}, FarmingPatchLocations.EAST_PORT_SARIM_SPIRIT_TREE, false));
    }}),
    FALADOR_PARK                        (-59, -63, -267, -271, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.FALADOR_PARK_TREE,
                new FarmingPatch("Tree", PatchType.TREE, null, new int[] {-59, -63, -267, -271}, FarmingPatchLocations.FALADOR_PARK_TREE, true));
    }}),
    FARMING_GUILD                       (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.FARMING_GUILD_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_ALLOTMENT_1, false));
        put(FarmingPatchLocations.FARMING_GUILD_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_ALLOTMENT_2, false));
        put(FarmingPatchLocations.FARMING_GUILD_BUSH,
                new FarmingPatch("Bush", PatchType.BUSH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_BUSH, false));
        put(FarmingPatchLocations.FARMING_GUILD_CACTUS,
                new FarmingPatch("Cactus", PatchType.CACTUS, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_CACTUS, false));
        put(FarmingPatchLocations.FARMING_GUILD_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_FLOWER, false));
        put(FarmingPatchLocations.FARMING_GUILD_FRUIT_TREE,
                new FarmingPatch("Fruit Tree", PatchType.FRUIT_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_FRUIT_TREE, false));
        put(FarmingPatchLocations.FARMING_GUILD_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_HERB, false));
        put(FarmingPatchLocations.FARMING_GUILD_SPECIAL_PATCH_ANIMA,
                new FarmingPatch("Anima", PatchType.SPECIAL_PATCH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_SPECIAL_PATCH_ANIMA, false));
        put(FarmingPatchLocations.FARMING_GUILD_SPECIAL_PATCH_HESPORI,
                new FarmingPatch("Hespori", PatchType.SPECIAL_PATCH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_SPECIAL_PATCH_HESPORI, false));
        put(FarmingPatchLocations.FARMING_GUILD_SPECIAL_TREE_REDWOOD,
                new FarmingPatch("Redwood Tree", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_SPECIAL_TREE_REDWOOD, false));
        put(FarmingPatchLocations.FARMING_GUILD_SPECIAL_TREE_CELASTRUS,
                new FarmingPatch("Celastrus Tree", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_SPECIAL_TREE_CELASTRUS, false));
        put(FarmingPatchLocations.FARMING_GUILD_SPIRIT_TREE,
                new FarmingPatch("Spirit Tree", PatchType.SPIRIT_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_SPIRIT_TREE, false));
        put(FarmingPatchLocations.FARMING_GUILD_TREE,
                new FarmingPatch("Tree", PatchType.TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FARMING_GUILD_TREE, false));
    }}),
    FOSSIL_ISLAND_MUSHROOM_FOREST       (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.FOSSIL_ISLAND_MUSHROOM_FOREST_SPECIAL_TREE_HARDWOOD_1,
                new FarmingPatch("Hardwood Tree 1", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FOSSIL_ISLAND_MUSHROOM_FOREST_SPECIAL_TREE_HARDWOOD_1, false));
        put(FarmingPatchLocations.FOSSIL_ISLAND_MUSHROOM_FOREST_SPECIAL_TREE_HARDWOOD_2,
                new FarmingPatch("Hardwood Tree 2", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FOSSIL_ISLAND_MUSHROOM_FOREST_SPECIAL_TREE_HARDWOOD_2, false));
        put(FarmingPatchLocations.FOSSIL_ISLAND_MUSHROOM_FOREST_SPECIAL_TREE_HARDWOOD_3,
                new FarmingPatch("Hardwood Tree 3", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.FOSSIL_ISLAND_MUSHROOM_FOREST_SPECIAL_TREE_HARDWOOD_3, false));
    }}),
    HARMONY_ISLAND                      (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.HARMONY_ISLAND_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.HARMONY_ISLAND_HERB, false));
    }}),
    HOSIDIUS_VINERY                     (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.HOSIDIUS_VINERY_SPECIAL_PATCH_GRAPES,
                new FarmingPatch("Grapes", PatchType.SPECIAL_PATCH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.HOSIDIUS_VINERY_SPECIAL_PATCH_GRAPES, false));
    }}),
    HOSIDIUS                            (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.HOSIDIUS_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.HOSIDIUS_ALLOTMENT_1, false));
        put(FarmingPatchLocations.HOSIDIUS_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.HOSIDIUS_ALLOTMENT_2, false));
        put(FarmingPatchLocations.HOSIDIUS_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.HOSIDIUS_FLOWER, false));
        put(FarmingPatchLocations.HOSIDIUS_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.HOSIDIUS_HERB, false));
    }}),
    LLETYA                              (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.LLETYA_FRUIT_TREE,
                new FarmingPatch("Fruit Tree", PatchType.FRUIT_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.LLETYA_FRUIT_TREE, false));
    }}),
    NORTH_ARDOUGNE                      (-1045, -1076, -259, -291, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.NORTH_ARDOUGNE_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {-1046, -1075, -260, -265, -1070, -1075, -260, -268}, FarmingPatchLocations.NORTH_ARDOUGNE_ALLOTMENT_1, false));
        put(FarmingPatchLocations.NORTH_ARDOUGNE_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {-1046, -1075, -285, -290, -1070, -1075, -282, -290}, FarmingPatchLocations.NORTH_ARDOUGNE_ALLOTMENT_2, false));
        put(FarmingPatchLocations.NORTH_ARDOUGNE_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {-1059, -1063, -273, -277}, FarmingPatchLocations.NORTH_ARDOUGNE_FLOWER, false));
        put(FarmingPatchLocations.NORTH_ARDOUGNE_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-1047, -1051, -273, -277}, FarmingPatchLocations.NORTH_ARDOUGNE_HERB, false));
    }}),
    NORTH_BRIMHAVEN                     (-650, -767, 245, 212, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.NORTH_BRIMHAVEN_FRUIT_TREE,
                new FarmingPatch("Fruit Tree", PatchType.FRUIT_TREE, null, new int[] {-763, -767, 216, 212}, FarmingPatchLocations.NORTH_BRIMHAVEN_FRUIT_TREE, false));
        put(FarmingPatchLocations.NORTH_BRIMHAVEN_SPIRIT_TREE,
                new FarmingPatch("Spirit Tree", PatchType.SPIRIT_TREE, null, new int[] {-650, -654, 245, 241}, FarmingPatchLocations.NORTH_BRIMHAVEN_SPIRIT_TREE, false));
    }}), // -615 -558    -644 -527
    NORTH_CATHERBY                      (-615, -644, -528, -558, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.NORTH_CATHERBY_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {-615, -644, -553, -558, -639, -644, -550, -558}, FarmingPatchLocations.NORTH_CATHERBY_ALLOTMENT_1, true));
        put(FarmingPatchLocations.NORTH_CATHERBY_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {-615, -644, -528, -533, -639, -644, -528, -536}, FarmingPatchLocations.NORTH_CATHERBY_ALLOTMENT_2, true));
        put(FarmingPatchLocations.NORTH_CATHERBY_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {-628, -632, -541, -545}, FarmingPatchLocations.NORTH_CATHERBY_FLOWER, true));
        put(FarmingPatchLocations.NORTH_CATHERBY_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-616, -620, -541, -545}, FarmingPatchLocations.NORTH_CATHERBY_HERB, true));
    }}),
    NORTH_LUMBRIDGE                     (634, 623, -88, -99, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.NORTH_LUMBRIDGE_HOPS,
                new FarmingPatch("Hops", PatchType.HOPS, null, new int[] {634, 623, -88, -99}, FarmingPatchLocations.NORTH_LUMBRIDGE_HOPS, true));
    }}),
    NORTH_MCGRUBOR_WOODS                (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.NORTH_MCGRUBOR_WOODS_HOPS,
                new FarmingPatch("Hops", PatchType.HOPS, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.NORTH_MCGRUBOR_WOODS_HOPS, false));
    }}),
    NORTH_TAI_BWO_WANNAI                (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.NORTH_TAI_BWO_WANNAI_SPECIAL_TREE_CALQUAT,
                new FarmingPatch("Calcquat", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.NORTH_TAI_BWO_WANNAI_SPECIAL_TREE_CALQUAT, false));
    }}),
    PRIFDDINAS                          (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.PRIFDDINAS_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.PRIFDDINAS_ALLOTMENT_1, false));
        put(FarmingPatchLocations.PRIFDDINAS_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.PRIFDDINAS_ALLOTMENT_2, false));
        put(FarmingPatchLocations.PRIFDDINAS_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.PRIFDDINAS_FLOWER, false));
        put(FarmingPatchLocations.PRIFDDINAS_SPECIAL_TREE_CRYSTAL,
                new FarmingPatch("Crystal Tree", PatchType.SPECIAL_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.PRIFDDINAS_SPECIAL_TREE_CRYSTAL, false));
    }}),
    RIMMINGTON                          (-253, -257, 189, 185, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.RIMMINGTON_BUSH,
                new FarmingPatch("Bush", PatchType.BUSH, null, new int[] {-253, -257, 189, 185}, FarmingPatchLocations.RIMMINGTON_BUSH, true));
    }}),
    SOUTH_ARDOUGNE                      (-1207, -1211, 180, 176, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.SOUTH_ARDOUGNE_BUSH,
                new FarmingPatch("Bush", PatchType.BUSH, null, new int[] {-1207, -1211, 180, 176}, FarmingPatchLocations.SOUTH_ARDOUGNE_BUSH, false));
    }}),
    SOUTH_FALADOR                       (127, 101, -76, -102, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.SOUTH_FALADOR_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {106, 101, -88, -102, 112, 101, -97, -102}, FarmingPatchLocations.SOUTH_FALADOR_ALLOTMENT_1, true));
        put(FarmingPatchLocations.SOUTH_FALADOR_ALLOTMENT_2, //
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {127, 122, -76, -90, 127, 116, -76, -81}, FarmingPatchLocations.SOUTH_FALADOR_ALLOTMENT_2, true));
        put(FarmingPatchLocations.SOUTH_FALADOR_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {116, 112, -87, -91}, FarmingPatchLocations.SOUTH_FALADOR_FLOWER, true));
        put(FarmingPatchLocations.SOUTH_FALADOR_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {127, 123, -98, -102}, FarmingPatchLocations.SOUTH_FALADOR_HERB, true));
    }}),
    SOUTHEAST_ETCETERIA                 (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.SOUTHEAST_ETCETERIA_SPIRIT_TREE,
                new FarmingPatch("Spirit Tree", PatchType.SPIRIT_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.SOUTHEAST_ETCETERIA_SPIRIT_TREE, false));
    }}),
    SOUTHWEST_ETCETERIA                 (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.SOUTHWEST_ETCETERIA_BUSH,
                new FarmingPatch("Bush", PatchType.BUSH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.SOUTHWEST_ETCETERIA_BUSH, false));
    }}),
    SOUTHWEST_HOSIDIUS                  (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.SOUTHWEST_HOSIDIUS_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.SOUTHWEST_HOSIDIUS_ALLOTMENT_1, false));
        put(FarmingPatchLocations.SOUTHWEST_HOSIDIUS_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.SOUTHWEST_HOSIDIUS_ALLOTMENT_2, false));
        put(FarmingPatchLocations.SOUTHWEST_HOSIDIUS_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.SOUTHWEST_HOSIDIUS_FLOWER, false));
        put(FarmingPatchLocations.SOUTHWEST_HOSIDIUS_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.SOUTHWEST_HOSIDIUS_HERB, false));
    }}),
    TAVERLEY(-283, -288, -418, -423, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.TAVERLEY_TREE,
                new FarmingPatch("Tree", PatchType.TREE, null, new int[] {-283, -288, -418, -423}, FarmingPatchLocations.TAVERLEY_TREE, true));
    }}),
    TREE_GNOME_STRONGHOLD               (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.TREE_GNOME_STRONGHOLD_FRUIT_TREE,
                new FarmingPatch("Fruit Tree", PatchType.FRUIT_TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.TREE_GNOME_STRONGHOLD_FRUIT_TREE, false));
        put(FarmingPatchLocations.TREE_GNOME_STRONGHOLD_TREE,
                new FarmingPatch("Tree", PatchType.TREE, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.TREE_GNOME_STRONGHOLD_TREE, false));
    }}),
    TROLL_STRONGHOLD                    (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.TROLL_STRONGHOLD_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.TROLL_STRONGHOLD_HERB, false));
    }}),
    UNDERWATER                          (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.UNDERWATER_SPECIAL_PATCH_GIANT_SEAWEED,
                new FarmingPatch("Giant Seaweed", PatchType.SPECIAL_PATCH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.UNDERWATER_SPECIAL_PATCH_GIANT_SEAWEED, false));
    }}),
    WEISS                               (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.WEISS_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.WEISS_HERB, false));
    }}),
    WEST_CANIFIS                        (-99999, -99999, -99999, -99999, false, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.WEST_CANIFIS_SPECIAL_PATCH_MUSHROOM,
                new FarmingPatch("Mushroom", PatchType.SPECIAL_PATCH, null, new int[] {-99999, -99999, -99999, -99999}, FarmingPatchLocations.WEST_CANIFIS_SPECIAL_PATCH_MUSHROOM, false));
    }}),
    WEST_CHAMPION_GUILD                 (504, 498, -225, -230, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.WEST_CHAMPION_GUILD_BUSH,
                new FarmingPatch("Bush", PatchType.BUSH, null, new int[] {504, 498, -225, -230}, FarmingPatchLocations.WEST_CHAMPION_GUILD_BUSH, true));
    }}),
    WEST_LUMBRIDGE                      (534, 530, 149, 145, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.WEST_LUMBRIDGE_TREE,
                new FarmingPatch("Tree", PatchType.TREE, null, new int[] {534, 530, 149, 145}, FarmingPatchLocations.WEST_LUMBRIDGE_TREE, true));
    }}),
    WEST_PORT_PHASMATYS                 (1775, 1746, -715, -745, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.WEST_PORT_PHASMATYS_ALLOTMENT_1,
                new FarmingPatch("Allotment 1", PatchType.ALLOTMENT, null, new int[] {1751, 1746, -727, -744, 1760, 1746, -739, -744}, FarmingPatchLocations.WEST_PORT_PHASMATYS_ALLOTMENT_1, true));
        put(FarmingPatchLocations.WEST_PORT_PHASMATYS_ALLOTMENT_2,
                new FarmingPatch("Allotment 2", PatchType.ALLOTMENT, null, new int[] {1775, 1770, -715, -732, 1775, 1761, -715, -720}, FarmingPatchLocations.WEST_PORT_PHASMATYS_ALLOTMENT_2, true));
        put(FarmingPatchLocations.WEST_PORT_PHASMATYS_FLOWER,
                new FarmingPatch("Flower", PatchType.FLOWER, null, new int[] {1762, 1758, -728, -732}, FarmingPatchLocations.WEST_PORT_PHASMATYS_FLOWER, true));
        put(FarmingPatchLocations.WEST_PORT_PHASMATYS_HERB,
                new FarmingPatch("Herb", PatchType.HERB, null, new int[] {1775, 1771,-741 , -745}, FarmingPatchLocations.WEST_PORT_PHASMATYS_HERB, true));
    }}),
    WEST_TREE_GNOME_MAZE                (-1594, -1598, 316, 312, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.WEST_TREE_GNOME_MAZE_FRUIT_TREE,
                new FarmingPatch("Fruit Tree", PatchType.FRUIT_TREE, null, new int[] {-1594, -1598, 316, 312}, FarmingPatchLocations.WEST_TREE_GNOME_MAZE_FRUIT_TREE, false));
    }}),
    VARROCK_CASTLE                      (632, 628, -529, -533, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.VARROCK_CASTLE_TREE,
                new FarmingPatch("Tree", PatchType.TREE, null, new int[] {632, 628, -529, -533}, FarmingPatchLocations.VARROCK_CASTLE_TREE, true));
    }}),
    YANILLE                             (-1330, -1341, 546, 535, true, new EnumMap(FarmingPatchLocations.class) {{
        put(FarmingPatchLocations.YANILLE_HOPS,
                new FarmingPatch("Hops", PatchType.HOPS, null, new int[] {-1594, -1598, 316, 312}, FarmingPatchLocations.YANILLE_HOPS, false));
    }});

    public int maxX;
    public int minX;
    public int maxZ;
    public int minZ;
    public boolean ingame;

    public EnumMap<FarmingPatchLocations, FarmingPatch> patches;

    FarmingLocations(int maxX, int minX, int maxZ, int minZ, boolean ingame, EnumMap patches) {
        this.maxX = maxX;
        this.minX = minX;
        this.maxZ = maxZ;
        this.minZ = minZ;
        this.patches = patches;
        this.ingame = ingame;
    }
}
