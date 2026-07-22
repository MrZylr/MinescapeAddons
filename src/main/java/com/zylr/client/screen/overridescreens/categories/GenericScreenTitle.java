package com.zylr.client.screen.overridescreens.categories;

public enum GenericScreenTitle {
    VIEW_MOB("view mob ", false),
    PICKUP_ITEMS("pickup items", true),
    // Skilling
    WHAT_WOULD_YOU_LIKE_TO_SMELT("what would you like to smelt?", false),
    WHAT_WOULD_YOU_LIKE_TO_SMITH("what would you like to smith?", false),
    WHAT_WOULD_YOU_LIKE_TO_FLETCH("what would you like to fletch?", false),
    WHAT_WOULD_YOU_LIKE_TO_CRAFT("what would you like to craft?", false),
    WHAT_WOULD_YOU_LIKE_TO_COOK("what would you like to cook?", false),
    HERBLORE_POTIONS("herblore - potions", false),
    CHOOSE_CRAFTING_TYPE("choose crafting type", false),
    SELECT_ENCHANT_ITEM("select enchant item", false),
    SELECT_A_SKILL("select a skill", false),
    SELECT_SKILL_CAPE("select skill cape", false),
    PURCHASE_SKILLCAPE("purchase skillcape", true),
    CREATE_TABLET("create tablet", false),
    HERBLORE_MIXES("herblore - mixes", false),
    // Prayers
    VIEW_PRAYERS("view prayers", false),
    VIEW_QUICK_PRAYER_SETS("view quick prayer sets", false),
    ADD_QUICK_PRAYER("add quick prayer", false),
    // Cosmetics
    COSMETICS("cosmetics", false),
    SELECT_TITLE("select title", false),
    SELECT_COMPANION("select companion", false),
    SELECT_GRAVESTONE("select gravestone", false),
    SELECT_EMOTE("select emote", false),
    EMOTE("emote", false),
    ITEMS("items", false),
    TRANSMOG("transmog", false),
    DAILY_REWARDS("daily rewards", false),
    VOTE_REWARDS("vote rewards", false),
    CHOOSE_UP_TO_2_BADGES("choose up to 2 badges", false),
    // Settings
    SETTINGS("settings", false),
    // Construction
    SELECT_TELEPORT("select teleport", false),
    CONSTRUCTION_MENU("construction menu", false),
    TAKE_CONTENTS("take contents", false),
    POH_PORTAL("poh portal", false),
    BUILD("build", false),
    REMOVE("remove ", false),
    //
    SELECT_DESTINATION("select destination", false),
    VIEW("view ", false),
    TRADE("trade", true),
    SKILLS("skills", false),
    DUELING("dueling", true),
    BARROWS_PUZZLE("what is the next shape in the sequence?", false),
    // Bank
    SELECT_BANK_ACTION("select bank action", true),
    DEPOSIT_BOX("deposit box", true),
    BANK_CHARGE_SELECTION("bank charge selection", true),
    ITEM_SETS("item sets", true),
    // Slayer
    CANCEL_TASK("cancel task?", false),
    // Inventory
    RUNE_POUCH("rune pouch", true),
    ARE_YOU_SURE_YOU_WANT_TO_DROP("are you sure you want to drop ", true),
    DO_YOU_WISH_TO_DESTROY_THIS_ITEM("do you wish to destroy this item?", true),
    //
    SELECT_OPTION("select option", false),
    SELECT_INSTANCE_TYPE("select instance type", false),
    COMBAT_STYLE("combat style", false),
    ADVENTURE_PATHS("adventure paths", false),
    EMPTY("empty" , false),
    BLANK("", false),
    // Teleports
    FISHING_GUILD_TELEPORT("fishing guild teleport?", false),
    HOUSE_TELEPORT("house teleport?", false),
    CRAFTING_GUILD_TELEPORT_QUESTION("crafting guild teleport?", false),
    WARRIORS_GUILD_TELEPORT("warriors guild teleport", false),
    CRAFTING_GUILD_TELEPORT("crafting guild teleport", false),
    // Rewards
    BARROWS_LOOT("barrows loot", true);

    private final String title;
    private final boolean focusInventory;

    GenericScreenTitle(String title, boolean focusInventory) {
        this.title = title;
        this.focusInventory = focusInventory;
    }

    public String title() {
        return this.title;
    }

    public boolean focusInventory() {
        return this.focusInventory;
    }
}