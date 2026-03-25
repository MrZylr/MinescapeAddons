package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum BuryItems {

    BONES("Bones"),
    ASHES("Ashes");

    public static final String menuTitle = "Bury";
    private final String name;
    BuryItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
