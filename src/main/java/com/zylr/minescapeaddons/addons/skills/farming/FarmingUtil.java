package com.zylr.minescapeaddons.addons.skills.farming;

import com.zylr.minescapeaddons.addons.properties.PersistenceFile;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FarmingUtil {

    // Static seed lookup map – built once, avoids O(n) SeedType scan on every call
    private static final Map<String, SeedType> SEED_MAP = new HashMap<>();
    static {
        for (SeedType s : SeedType.values()) {
            if (s.name != null && !s.name.isEmpty())
                SEED_MAP.put(s.name.toLowerCase(), s);
        }
    }

    // Static list of allotment exception positions – avoids new ArrayList + new double[] every render event
    private static final List<double[]> ARMORSTAND_EXCEPTIONS = List.of(
            new double[]{105.5,   -94.5},   // South Falador Allotment 1
            new double[]{123.5,   -82.5},   // South Falador Allotment 2
            new double[]{-624.5,  -553.5},  // North Catherby Allotment 1
            new double[]{-624.5,  -531.5},  // North Catherby Allotment 2
            new double[]{-1061.5, -285.5},  // North Ardougne Allotment 1
            new double[]{-1061.5, -263.5},  // North Ardougne Allotment 2
            new double[]{1750.5,  -739.5},  // West Port Phasmatys Allotment 1
            new double[]{1771.5,  -718.5}   // West Port Phasmatys Allotment 2
    );

    public static boolean isFarmPatchClick(Block block, ItemStack item) {
        String itemName = item.getDisplayName().toString().substring(2);
        String blockName = block.getName().toString();

        boolean clickedBarrier = blockName.equalsIgnoreCase("Farmland") || blockName.equalsIgnoreCase("Barrier");
        boolean holdingSeed = SEED_MAP.containsKey(itemName.toLowerCase());

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
            if (Util.withinRegion(location.maxX, location.minX, location.maxZ, location.minZ, x, z))
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
                    ? Util.withinCornerRegion(region[0], region[1], region[2], region[3], region[4], region[5], region[6], region[7], x, z)
                    : Util.withinRegion(region[0], region[1], region[2], region[3], x, z);
            if (withinRegion) return patch;
        }
        return null;
    }

    public static SeedType getSeed(ItemStack item) {
        String itemName = item.getDisplayName().getString().substring(2);
        return SEED_MAP.get(itemName.toLowerCase());
    }

    public static void setAlertsAsChecked() {
        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations key : FarmingPatchLocations.values()) {
                if (location.patches.containsKey(key)) {
                    location.patches.get(key).timer.alertChecked = true;
                }
            }
        }
    }

    public static void writeTimersToFile() {
        List<String> timers = new ArrayList<>();

        for (FarmingLocations location : FarmingLocations.values()) {
            for (FarmingPatchLocations key : FarmingPatchLocations.values()) {
                if (location.patches.containsKey(key)) {
                    FarmingPatch patch = location.patches.get(key);
                    long startTime = patch.timer.getStartTime();
                    SeedType seed = patch.getSeed();

                    timers.add(location + "/" + key + "/" + seed + "/" + startTime);
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

            if (timerParts.length == 4) {
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

                    if (patch.timer.isCompleted())
                        return true;
                }
            }
        }
        return false;
    }

    public static void reduceArmorStandsInFarmingPatch(ArmorStand armorstand, RenderLivingEvent.Pre<?, ?> event) {
        BlockPos armorstandPos = new BlockPos(armorstand.getBlockX(), armorstand.getBlockY(), armorstand.getBlockZ());
        FarmingLocations location = FarmingUtil.getFarmingLocation(armorstandPos);
        if (location == null) return;

        FarmingPatch patch = FarmingUtil.getFarmingPatchFromLocation(location, armorstandPos);
        if (patch == null || patch.getPatchType() != PatchType.ALLOTMENT) return;

        double x = armorstand.getX();
        double z = armorstand.getZ();

        ItemStack headItem     = armorstand.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack mainHandItem = armorstand.getItemBySlot(EquipmentSlot.MAINHAND);
        ItemStack offHandItem  = armorstand.getItemBySlot(EquipmentSlot.OFFHAND);

        boolean isMarker = (headItem.getItem() == Items.IRON_PICKAXE || headItem.getItem() == Items.IRON_SWORD)
                || (mainHandItem.getItem() == Items.IRON_PICKAXE || mainHandItem.getItem() == Items.IRON_SWORD)
                || (offHandItem.getItem() == Items.IRON_PICKAXE || offHandItem.getItem() == Items.IRON_SWORD);

        if (!isMarker) return;

        // Use static exception list – no allocation per frame
        for (double[] exception : ARMORSTAND_EXCEPTIONS) {
            if (x == exception[0] && z == exception[1]) return;
        }
        event.setCanceled(true);
    }
}
