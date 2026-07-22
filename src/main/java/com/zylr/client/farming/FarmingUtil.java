package com.zylr.client.farming;

import com.zylr.utils.util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.*;

public class FarmingUtil {

    // Static seed lookup map – built once, avoids O(n) SeedType scan on every call
    private static final Map<String, SeedType> SEED_MAP = new HashMap<>();
    static {
        for (SeedType s : SeedType.values()) {
            if (s.name != null && !s.name.isEmpty())
                SEED_MAP.put(s.name.toLowerCase(), s);
        }
    }

    public static boolean isFarmPatchClick(Block block, ItemStack item) {
        String itemName = normalizeName(item.getDisplayName().getString());
        String blockName = normalizeName(block.getName().getString());

        boolean clickedBarrier = blockName.equals("farmland") || blockName.equals("barrier");
        boolean holdingSeed = SEED_MAP.containsKey(itemName);

        return clickedBarrier && holdingSeed;
    }

    public static boolean isInFarmingArea(BlockPos blockPos) {
        return getFarmingLocation(blockPos) != null;
    }

    public static boolean isInFarmingPatch(FarmingLocations location, BlockPos blockPos) {
        return getFarmingPatchLocation(location, blockPos) != null;
    }

    public static FarmingLocations getFarmingLocation(BlockPos blockPos) {
        int x = blockPos.getX();
        int z = blockPos.getZ();
        for (FarmingLocations location : FarmingLocations.values()) {
            if (util.withinRegion(location.maxX, location.minX, location.maxZ, location.minZ, x, z))
                return location;
        }
        return null;
    }

    public static FarmingPatchLocations getFarmingPatchLocation(FarmingLocations location, BlockPos blockPos) {
        // Call getFarmingPatchFromLocation only once instead of twice
        FarmingPatch patch = getFarmingPatchFromLocation(location, blockPos);
        return patch != null ? patch.getPatchLocation() : null;
    }

    public static FarmingPatch getFarmingPatchFromLocation(FarmingLocations location, BlockPos blockPos) {
        int x = blockPos.getX();
        int z = blockPos.getZ();
        for (FarmingPatchLocations patchLocationKey : location.patches.keySet()) {
            FarmingPatch patch = location.patches.get(patchLocationKey);
            int[] region = patch.region;
            boolean withinRegion = patch.getPatchType() == PatchType.ALLOTMENT
                    ? util.withinCornerRegion(region[0], region[1], region[2], region[3], region[4], region[5], region[6], region[7], x, z)
                    : util.withinRegion(region[0], region[1], region[2], region[3], x, z);
            if (withinRegion) return patch;
        }
        return null;
    }

    public static SeedType getSeed(ItemStack item) {
        return SEED_MAP.get(normalizeName(item.getDisplayName().getString()));
    }

    private static String normalizeName(String name) {
        if (name == null) {
            return "";
        }

        String normalized = name.trim();
        if (normalized.length() >= 2 && normalized.charAt(0) == '[' && normalized.charAt(normalized.length() - 1) == ']') {
            normalized = normalized.substring(1, normalized.length() - 1).trim();
        }
        return normalized.toLowerCase(Locale.ROOT);
    }

    public static boolean startTimerForPatch(BlockPos blockPos, Block block, ItemStack mainHandItem) {
        FarmingLocations location = getFarmingLocation(blockPos);
        if (location == null || !isInFarmingPatch(location, blockPos) || !isFarmPatchClick(block, mainHandItem)) {
            return false;
        }

        FarmingPatch patch = getFarmingPatchFromLocation(location, blockPos);
        SeedType seed = getSeed(mainHandItem);
        if (patch == null || seed == null) {
            return false;
        }

        patch.timer.start(seed);
        writeTimersToFile();
        tickTimerCache();
        return true;
    }

    public static void clearCompletedTimers() {
        boolean changed = false;
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatch patch : location.patches.values()) {
                if (patch.timer.isCompleted()) {
                    patch.timer = new FarmingTimer((SeedType) null);
                    changed = true;
                }
            }
        }

        if (changed) {
            writeTimersToFile();
            tickTimerCache();
        }
    }

    public static void resetAllTimers() {
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatch patch : location.patches.values()) {
                patch.timer.reset();
            }
        }

        writeTimersToFile();
        tickTimerCache();
    }

    public static void setAlertsAsChecked() {
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations key : FarmingPatchLocations.values()) {
                if (location.patches.containsKey(key)) {
                    FarmingPatch patch = location.patches.get(key);
                    if (patch.timer.isCompleted()) {
                        patch.timer.alertChecked = true;
                    }
                }
            }
        }
        tickTimerCache();
    }

    public static void writeTimersToFile() {
        List<String> timers = new ArrayList<>();

        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations key : FarmingPatchLocations.values()) {
                if (location.patches.containsKey(key)) {
                    FarmingPatch patch = location.patches.get(key);
                    long startTime = patch.timer.getStartTime();
                    SeedType seed = patch.getSeed();

                    timers.add(location + "/" + key + "/" + seed + "/" + startTime + "/" + patch.timer.alertChecked);
                }
            }
        }

        PersistenceFile.writeFile(PersistenceFile.FARMINGTIMERSFILE.getPath(), timers);
    }

    public static void setTimersFromFile() {
        Scanner data = PersistenceFile.readFile(PersistenceFile.FARMINGTIMERSFILE.getPath());

        while (data.hasNext()) {
            String timerData = data.nextLine();
            // Get all FarmingLocation, FarmingPacthLocation and timer start from line
            String[] timerParts = timerData.split("/");

            if (timerParts.length == 4 || timerParts.length == 5) {
                // Set information from string
                // TODO:: Do checks for correct value
                FarmingLocations location = FarmingLocations.valueOf(timerParts[0]);
                FarmingPatchLocations key = FarmingPatchLocations.valueOf(timerParts[1]);
                SeedType seed;
                try {
                    seed = SeedType.valueOf(timerParts[2]);
                }catch (IllegalArgumentException ex) {
                    seed = null;
                }
                long startTime = 0;
                try {
                    startTime = Long.parseLong(timerParts[3]);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

                // Create the new timer
                if (startTime != 0)
                    location.patches.get(key).timer = new FarmingTimer(startTime, seed);
                if (timerParts.length == 5) {
                    location.patches.get(key).timer.alertChecked = Boolean.parseBoolean(timerParts[4]);
                }
            }
        }
    }

    // Cached result — recomputed on tick, not every render frame
    private static boolean completedTimerCache = false;

    /** Call this from a game tick event (e.g. ClientTickEvent) to refresh the cache. */
    public static void tickTimerCache() {
        completedTimerCache = computeCompletedTimers();
    }

    /** Returns the cached value — safe to call every render frame at zero cost. */
    public static boolean checkForCompletedTimers() {
        return completedTimerCache;
    }

    private static boolean computeCompletedTimers() {
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations key : FarmingPatchLocations.values()) {
                if (location.patches.containsKey(key)) {
                    FarmingPatch patch = location.patches.get(key);

                    if (patch.isCompleted() && !patch.timer.alertChecked)
                        return true;
                }
            }
        }
        return false;
    }
}
