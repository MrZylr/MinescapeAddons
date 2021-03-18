package com.zylr.minescapeaddons.addons.skills;

public enum SkillType {

    AGILITY(31/255f, 32/255f, 86/255f),
    ATTACK(96/255f, 17/255f, 9/255f),
    CONSTRUCTION(128/255f, 118/255f, 101/255f),
    COOKING(64/255f, 26/255f, 75/255f),
    CRAFTING(102/255f, 80/255f, 57/255f),
    DEFENCE(89/255f, 105/255f, 157/255f),
    FARMING(31/255f, 71/255f, 33/255f),
    FIREMAKING(166/255f, 87/255f, 21/255f),
    FISHING(97/255f, 121/255f, 143/255f),
    FLETCHING(0/255f, 58/255f, 60/255f),
    HERBLORE(9/255f, 83/255f, 11/255f),
    HITPOINTS(163/255f, 161/255f, 151/255f),
    HUNTER(84/255f, 78/255f, 56/255f),
    MAGIC(134/255f, 125/255f, 107/255f),
    MINING(70/255f, 70/255f, 57/255f),
    PRAYER(211/255f, 205/255f, 204/255f),
    RANGE(70/255f, 89/255f, 21/255f),
    RUNECRAFT(142/255f, 142/255f, 132/255f),
    SLAYER(81/255f, 17/255f, 9/255f),
    SMITHING(68/255f, 68/255f, 55/255f),
    STRENGTH(9/255f, 90/255f, 59/255f),
    THIEVING(92/255f, 52/255f, 79/255f),
    WOODCUTTING(114/255f, 93/255f, 52/255f),
    TOTAL(0.9f, 0.0f, 0.0f);

    private float red;
    private float green;
    private float blue;

    SkillType(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return this.red;
    }

    public float getGreen() {
        return this.green;
    }

    public float getBlue() {
        return this.blue;
    }
}
