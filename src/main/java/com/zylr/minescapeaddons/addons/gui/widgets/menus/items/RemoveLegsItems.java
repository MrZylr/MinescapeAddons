package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum RemoveLegsItems {

    SWAMP_TOAD("Swamp toad");

    public static final String menuTitle = "Remove Legs";
    private final String name;
    RemoveLegsItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
