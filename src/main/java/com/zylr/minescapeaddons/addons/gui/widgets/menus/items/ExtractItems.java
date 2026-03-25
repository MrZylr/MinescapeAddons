package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum ExtractItems {

    NEST_BOX_EMPTY("Nest Box empty"),
    NEST_BOX_RING("Nest Box ring"),
    NEST_BOX_SEED("Nest Box seed");

    public static final String menuTitle = "Extract";
    private final String name;
    ExtractItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
