package com.zylr.minescapeaddons.addons.skills;

public enum SkillType {
    ATTACK(13, 212, 241),
    HITPOINTS(13, 212, 241),
    MINING(13, 212, 241),
    STRENGTH(13, 212, 241),
    AGILITY(13, 212, 241),
    SMITHING(13, 212, 241),
    DEFENCE(13, 212, 241),
    HERBLORE(13, 212, 241),
    FISHING(13, 212, 241),
    RANGED(13, 212, 241),
    THIEVING(13, 212, 241),
    COOKING(13, 212, 241),
    PRAYER(13, 212, 241),
    CRAFTING(13, 212, 241),
    FIREMAKING(13, 212, 241),
    MAGIC(13, 212, 241),
    FLETCHING(13, 212, 241),
    WOODCUTTING(13, 212, 241),
    RUNECRAFT(13, 212, 241),
    SLAYER(13, 212, 241),
    FARMING(13, 212, 241),
    CONSTRUCTION(13, 212, 241),
    HUNTER(13, 212, 241),
    TOTAL(13, 212, 241);

    public float r;
    public float g;
    public float b;

    SkillType(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
