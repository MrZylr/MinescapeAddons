package com.zylr.player;

public class PlayerStats {
    private static int health = 0;
    private static int prayer = 0;

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        PlayerStats.health = health;
    }

    public static int getPrayer() {
        return prayer;
    }

    public static void setPrayer(int prayer) {
        PlayerStats.prayer = prayer;
    }
}

