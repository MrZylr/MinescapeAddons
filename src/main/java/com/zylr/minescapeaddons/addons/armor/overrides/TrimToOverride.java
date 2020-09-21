package com.zylr.minescapeaddons.addons.armor.overrides;

public enum TrimToOverride {
    GOLDLEGS_BLUECHEST_COMBINED(90),
    BLUELEGS_GOLDCHEST_COMBINED(97),
    BLUE_TRIM_CHEST(101),
    BLUE_TRIM_LEGS(103),
    BLUE_TRIM_COMBINED(102),
    GOLD_TRIM_CHEST(2),
    GOLD_TRIM_LEGS(1),
    GOLD_TRIM_COMBINED(0);

    public int damageValue;

    TrimToOverride(int damageValue) {
        this.damageValue = damageValue;
    }
}
