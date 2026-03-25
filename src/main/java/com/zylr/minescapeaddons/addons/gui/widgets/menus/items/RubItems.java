package com.zylr.minescapeaddons.addons.gui.widgets.menus.items;

public enum RubItems {

    RING_OF_RETURNING("Ring of Returning"),
    NECKLACE_OF_PASSAGE("Necklace of Passage"),
    BURNING_AMULET("Burning amulet"),
    GAMES_NECKLACE("Games necklace"),
    RING_OF_DUELING("Ring of dueling"),
    AMULET_OF_NATURE("Amulet of Nature"),
    DIGSITE_PENDANT("Digsite pendant"),
    RING_OF_WEALTH("Ring of Wealth"),
    SKILLS_NECKLACE("Skills necklace"),
    COMBAT_BRACELET("Combat bracelet"),
    AMULET_OF_GLORY("Amulet of Glory"),
    SLAYER_RING("Slayer Ring");

    public static final String menuTitle = "Rub";
    private final String name;
    RubItems(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }
}
