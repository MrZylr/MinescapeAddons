package com.zylr.minescapeaddons.addons.config;

public enum Config {
    SMALL(new BooleanOption(                        "small"                             ), "Small OSRS Screen"),
    OSRS_SCREEN(new BooleanOption(                  "osrs_screen"                       ), "Show OSRS Screen"),
    EXP_TRACKER(new BooleanOption(                  "exp_tracker"                       ), "Show Exp Tracker"),
    THURGO(new BooleanOption(                       "thurgo"                            ), "Thick BOIII"),
    IDLE_ALERT(new BooleanOption(                   "idle_alert"                        ), "Use Idle Alert"),
    OSRS_VERTICAL(new BooleanOption(                "osrs_vertical"                     ), "Use Vertical Inv Screen"),
    VIRTUAL_LEVELS(new BooleanOption(               "virtual_levels"                    ), "Use Virtual Levels"),
    OSRS_STYLE_INVENTORY(new BooleanOption(         "osrs_style_inventory"              ), "Use OSRS Style Inventory"),
    DARK_INVENTORY_BACKGROUND(new BooleanOption(    "dark_inventory_background"         ), "Use Dark Inv Background"),
    RENDER_HEALTH(new BooleanOption(                "render_health"                     ), "Render MC Health Bar"),
    RENDER_HOTBAR(new BooleanOption(                "render_hotbar"                     ), "Render MC Hotbar"),
    RENDER_EXPERIENCE_BAR(new BooleanOption(        "render_experience_bar"             ), "Render MC Exp Bar"),
    RENDER_MAP_OVERLAY(new BooleanOption(           "render_map_overlay"                ), "Render Map Overlay"),
    PROTECTED_SLOT(new BooleanOption(               "protected_slot"                    ), "Lock/Unlock Protected Slot"),
    PERSISTENT_MOUSE(new BooleanOption(             "persistent_mouse"                  ), "Use Persistent Inv Mouse"),
    FARMING_LOGIN_MESSAGE(new BooleanOption(        "farming_login_message"             ), "Get Login Farming Alerts"),
    USE_CUSTOM_ARMOR(new BooleanOption(             "use_custom_armor"                  ), "Use Custom Armor Models"),
    UNRENDER_FARMING_ARMORSTANDS(new BooleanOption( "unrender_farming_armorstands"      ), "Unrender Farming Patches"),
    DEBUG(new BooleanOption(                        "debug"                             ), "Mr_Zylr is dumb and my keys don't work")
    ;

    private BooleanOption option;
    private String name;
    Config(BooleanOption option, String name) {
        this.option = option;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean get() {
        return this.option.getValue();
    }
    public void set(boolean value) {
        this.option.setValue(value);
    }
}
