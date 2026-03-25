package com.zylr.minescapeaddons.addons.skills.farming;

public class FarmingPatchClicked {
    public static boolean xpGained = false;
    public static boolean patchClicked = false;

    public static boolean bothClickedAndGainedXp() {
        return xpGained && patchClicked;
    }


    public static void reset() {
        xpGained = false;
        patchClicked = false;
    }
}
