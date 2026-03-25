package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum EmptyItems {

    POT_OF_FLOUR("Pot of flour"),
    BURNT_PIE("Burnt pie"),
    BUCKET_OF_WATER("Bucket of water"),
    BUCKET_OFSAND("Bucket of sand"),
    BUCKET_OF_SLIME("Bucket of slime"),
    BUCKET_OF_MILK("Bucket of milk"),
    BURNT_STEW("Burnt stew"),
    BURNT_CURRY("Burnt curry"),
    BURNT_EGG("Burnt egg"),
    BURNT_MUSHROOM("Burnt mushroom"),
    BOWL_OF_WATER("Bowl of water"),
    BURNT_ONION("Burnt onion"),
    JUG_OF_WATER("Jug of water"),
    VIAL_OF_WATER("Vial of water");

    public static final String menuTitle = "Empty";
    private final String name;
    EmptyItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }

}
