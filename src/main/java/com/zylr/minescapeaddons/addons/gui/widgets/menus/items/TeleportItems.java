package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum TeleportItems {

    CONSTRUCT_CAPE("Construct cape"),
    CRAFTING_CAPE("Crafting cape"),
    FISHING_CAPE("Fishing cape"),
    STRENGTH_CAPE("Strength cape"),
    CHRONICLE("Chronicle"),
    ARDOUGNE_TELEPORT("Ardougne teleport"),
    CAMELOT_TELEPORT("Camelot teleport"),
    DIGSITE_TELEPORT("Digsite teleport"),
    FALADOR_TELEPORT("Falador teleport"),
    FELDIP_HILLS_TELEPORT("Feldip hills teleport"),
    IORWERTH_CAMP_TELEPORT("Iorwerth camp teleport"),
    LUMBERYARD_TELEPORT("Lumberyard teleport"),
    LUMBRIDGE_TELEPORT("Lumbridge teleport"),
    LUNAR_ISLE_TELEPORT("Lunar isle teleport"),
    MORTTON_TELEPORT("Mortton teleport"),
    MOS_LEHARMLESS_TELEPORT("Mos leharmless teleport"),
    NARDAH_TELEPORT("Nardah teleport"),
    PEST_CONTROL_TELEPORT("Pest control teleport"),
    PISCATORIS_TELEPORT("Piscatoris teleport"),
    TAI_BWO_WANNAI_TELEPORT("Tai bwo wannai teleport"),
    TELEPORT_TO_HOUSE("Teleport to house"),
    VARROCK_TELEPORT("Varrock teleport"),
    WATCHTOWER_TELEPORT("Watchtower teleport"),
    WATSON_TELEPORT("Watson teleport");

    public static final String menuTitle = "Teleport";
    private final String name;
    TeleportItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
