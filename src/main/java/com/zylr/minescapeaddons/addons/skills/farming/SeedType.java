package com.zylr.minescapeaddons.addons.skills.farming;

public enum SeedType {
    // List of seeds(int timeToGrow)
    ATTAS("", 5120, 8),
    APPLE_SAPLING("Apple Sapling", 960, 6),
    OAK_SAPPLING("Oak Sapling", 200, 5),
    ASGARMIAN("Asgarnian Seed", 50, 5),
    AVANTOE("Avantoe Seed", 80, 4),
    BANANA_SAPLING("Banana Sapling", 960, 6),
    BARLEY("Barley Seed", 40, 4),
    BELLADONNA("Belladonna Seed", 320, 4),
    CABBAGE("Cabbage Seed", 40, 4),
    CACTUS("Cactus Seed", 560, 7),
    CADANTINE("Cadantine Seed", 80, 4),
    CADAVABERRY("Cadavaberry Seed", 120, 6),
    CALQUAT_SAPLING("Calquat Sapling", 1280, 8),
    CELASTRUS_SAPLING("Celastrus Sapling", 800, 5),
    CRYSTAL("", 480, 6),
    CURRY_SAPLING("Curry Sapling", 960, 6),
    DWARF_WEED("", 80, 4),
    DRAGONFRUIT_SAPLING("Dragonfruit Sapling", 960, 6),
    DWELLBERRY("Dwellberry Seed", 140, 7),
    GOUT_TUBER("", 80, 4),
    GRAPE_SEED("Grape Seed", 35, 7),
    GUAM_LEAF("Guam Seed", 80, 4),
    HAMMERSTONE("Hammerstone Seed", 40, 4),
    HARRALANDER("Harralander Seed", 80, 4),
    HESPORI_SEED("", 1920, 3),
    IASOR("", 5120, 8),
    IRIT_LEAF("Irit Seed", 80, 4),
    JANGERBERRY("Jangerberry Seed", 160, 8),
    JUTE("Jute Seed", 50, 5),
    KRANDORIAN("Krandorian Seed", 70, 7),
    KRONOS("", 5120, 8),
    KWUARM("Kwuarm Seed", 80, 4),
    LANTADYME("Lantadyme Seed", 80, 4),
    LIMPWURT("Limpwurt Seed", 20, 4),
    MAGIC_SAPLING("Magic Sapling", 480, 12),
    MAHOGANY_SAPLING("Mahogany Sapling", 5120, 8),
    MAPLE_SAPLING("Maple Sapling", 320, 8),
    MARIGOLD("Marigold Seed", 20, 4),
    MARRENTILL("Marrentill Seed", 80, 4),
    MUSHROOM_SPORE("", 240, 6),
    NASTURIUM("Nasturium Seed", 20, 4),
    ONION("Onion Seed", 40, 4),
    ORANGE_SAPLING("Orange Sapling", 960, 6),
    PALM_SAPLING("Palm Sapling", 960, 6),
    PAPAYA_SAPLING("Papaya Sapling", 960, 6),
    PINEAPPLE_SAPLING("Pineapple Sapling", 960, 6),
    POISON_IVY_BERRY("Poison Ivy Seed", 160, 8),
    POTATO_CACTUS("Potato Cactus Seed", 70, 7),
    POTATO("Potato Seed", 40, 4),
    RANARR("Ranarr Seed", 80, 4),
    REDBERRY("Redberry Seed", 100, 5),
    REDWOOD_SAPLING("Redwood Sapling", 6400, 10),
    ROSEMARY("Rosemary Seed", 20, 4),
    SEAWEED_SPORE("", 40, 4),
    SNAPDRAGON("Snapdragon Seed", 80, 4),
    SNAPE_GRASS("Snape Grass Seed", 70, 7),
    SPIRIT_SAPLING("Spirit Sapling", 3520, 12),
    STRAWBERRY("Strawberry Seed", 60, 6),
    SWEETCORN("Sweetcorn Seed", 60, 6),
    TARROMIN("Tarromin Seed", 80, 4),
    TEAK_SAPLING("Teak Sapling", 3840, 6),
    TOADFLAX("Toadflax Seed", 80, 4),
    TOMATO("Tomato Seed", 40, 4),
    TORSTOL("Torstol Seed", 80, 4),
    WATERMELON("Watermelon Seed", 80, 8),
    WHITE_LILY("White Lily Seed", 20, 4),
    WHITEBERRY("Whiteberry Seed", 160, 8),
    WILDBLOOD("Wildblood Seed", 80, 8),
    WILLOW_SAPLING("Willow Sapling", 280, 7),
    WOAD_SEED("Woad Seed", 20, 4),
    YANILLIAN("Yanillian Seed", 60, 6),
    YEW_SAPLING("Yew Sapling", 400, 10);

    public int growthTime;
    public int stages;
    public String name;

    SeedType (String name, int growthTime, int stages) {
        this.growthTime = growthTime;
        this.name = name;
        this.stages = stages;
    }



}
