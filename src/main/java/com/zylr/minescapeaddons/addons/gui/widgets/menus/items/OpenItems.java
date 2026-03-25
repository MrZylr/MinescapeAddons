package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum OpenItems {
    AIR_RUNE_PACk("Air rune pack"),
    BAIT_PACK("Bait pack"),
    BASKET_PACK("Basket pack"),
    BIRD_SNARE_PACK("Bird snare pack"),
    BOX_TRAP_PACK("Box trap pack"),
    BROAD_ARROWHEAD_PACK("Broad arrowhead pack"),
    CHAOS_RUNE_PACK("Chaos rune pack"),
    COMPOST_PACK("Compost pack"),
    EARTH_RUNE_PACK("Earth rune pack"),
    EMPTY_BUCKET_PACK("Empty bucket pack"),
    EMPTY_JUG_PACK("Empty jug pack"),
    EMPTY_VIAL_PACK("Empty vial pack"),
    EYE_OF_NEWT_PACK("Eye of newt pack"),
    FEATHER_PACK("Feather pack"),
    FIRE_RUNE_PACK("Fire rune pack"),
    MAGIC_IMP_BOX_PACK("Magic imp box pack"),
    MIND_RUNE_PACK("Mind rune pack"),
    OLIVE_OIL_PACK("Olive oil pack"),
    PLANT_POT_PACK("Plant pot pack"),
    SACK_PACK("Sack pack"),
    SEED_PACK("Seed pack"),
    SOFT_CLAY_PACK("Soft clay pack"),
    UNFINISHED_BROAD_BOLT_PACK("Unfinished broad bolt pack"),
    WATER_FILLED_VIAL_PACK("Water filled vial pack"),
    WATER_RUNE_PACK("Water rune pack"),
    BAG_FULL_OF_GEMS("Bag full of gems"),
    RUNE_POUCH("Rune pouch");

    public static final String menuTitle = "Open";
    private final String name;
    OpenItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
