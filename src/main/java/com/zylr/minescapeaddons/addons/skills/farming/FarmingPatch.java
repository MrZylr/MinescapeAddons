package com.zylr.minescapeaddons.addons.skills.farming;

public class FarmingPatch {

    public FarmingTimer timer;
    private String patchName;
    private PatchType patchType;
    private FarmingPatchLocations patchLocation;
    public int[] region;
    public boolean ingame;

    public FarmingPatch(String patchName, PatchType patchType, SeedType seed, int[] region, FarmingPatchLocations patchLocation, boolean ingame) {
        this.patchName = patchName;
        this.patchType = patchType;
        this.timer = new FarmingTimer(seed);
        this.region = region;
        this.ingame = ingame;
        this.patchLocation = patchLocation;
    }

    public String getPatchName() {
        return patchName;
    }

    public SeedType getSeed() {
        return timer.getSeed();
    }

    public void setSeed(SeedType seed) {
        timer.setSeed(seed);
    }

    public PatchType getPatchType() {
        return patchType;
    }

    public boolean isCompleted() {
        if (patchType.alertOnComplete && timer.isCompleted()) {
            if (patchType.onlyAlertForAllSameType)
                if (areAllSameTypesCompleted())
                    return true;
                else
                    return false;
            return true;
        }
        return false;
    }

    public boolean areAllSameTypesCompleted() {
        boolean completed = false;
        int othersStarted = 0;

        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations key : FarmingPatchLocations.values()) {
                if (location.patches.containsKey(key)) {
                    if (location.patches.get(key).patchType == patchType) {
                        if (location.patches.get(key).timer.hasStarted())
                            othersStarted += 1;
                        if (location.patches.get(key).timer.hasStarted() && location.patches.get(key).timer.isCompleted())
                            completed = true;
                        else
                            completed = false;
                    }
                }
            }
        }
        if (othersStarted > 1)
            return true;
        return completed;
    }

    public FarmingPatchLocations getPatchLocation() { return patchLocation; }
}
