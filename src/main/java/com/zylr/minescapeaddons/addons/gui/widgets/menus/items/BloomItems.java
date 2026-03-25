package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum BloomItems {
    SILVER_SICKLE_B("Silver sickle b");

    public static final String menuTitle = "Cast Bloom";
    private final String name;

    BloomItems(String name) { this.name = name; }

    public String getName() { return this.name; }
}
