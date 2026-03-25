package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum PolishItems {

    BUTTONS("Buttons");


    public static final String menuTitle = "Polish";
    private final String name;
    PolishItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
