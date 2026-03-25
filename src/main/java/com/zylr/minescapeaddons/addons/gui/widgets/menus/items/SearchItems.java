package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum SearchItems {

    FLETCHING_CAPE("Fletching cape"),
    FLETCHING_CAPE_TRIMMED("Fletching cape trimmed"),
    HERBLORE_CAPE("Herblore cape"),
    HERBLORE_CAPE_TRIMMED("Herblore cape trimmed");

    public static final String menuTitle = "Search";
    private final String name;
    SearchItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
