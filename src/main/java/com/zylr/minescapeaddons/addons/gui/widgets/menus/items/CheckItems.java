package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum CheckItems {

    AMULET_OF_BOUNTY("Amulet of bounty"),
    AMULET_OF_CHEMISTRY("Amulet of chemistry");

    public static final String menuTitle = "Check";
    private final String name;

    CheckItems(String name) { this.name = name; }

    public String getName() { return this.name; }
}
