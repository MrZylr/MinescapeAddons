package com.zylr.minescapeaddons.addons.util;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class Util {

    public static boolean withinRegion(int maxX, int minX, int maxZ, int minZ, int pointX, int pointZ) {
        boolean isXWithinRegion = false;
        boolean isYWithinRegion = false;

        if (pointX <= maxX && pointX >= minX) {
            isXWithinRegion = true;
        }

        if (pointZ <= maxZ && pointZ >= minZ) {
            isYWithinRegion = true;
        }
        if (isXWithinRegion && isYWithinRegion)
            return true;

        return false;
    }

    public static boolean withinCornerRegion(int maxX, int minX, int maxZ, int minZ, int maxX2, int minX2, int maxZ2, int minZ2, int pointX, int pointZ) {
        if (withinRegion(maxX, minX, maxZ, minZ, pointX, pointZ))
            return true;

        if (withinRegion(maxX2, minX2, maxZ2, minZ2, pointX, pointZ))
            return true;

        return false;
    }

    public static void file() {

    }
}
