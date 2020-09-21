package com.zylr.minescapeaddons.addons.skills.farming;

import com.zylr.minescapeaddons.addons.util.Util;
import com.zylr.minescapeaddons.addons.util.files.PersistenceFile;
import net.minecraft.block.Block;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FarmingUtil {

    public static boolean isFarmPatchClick(Block block, ItemStack item) {
        boolean clickedBarrier = false;
        boolean holdingSeed = false;
        String itemName = item.getDisplayName().getFormattedText().substring(2);
        String blockName = block.getNameTextComponent().getFormattedText();

        // Check if clicking a barrier
        if (blockName.equalsIgnoreCase("Farmland") || blockName.equalsIgnoreCase("Barrier"))
            clickedBarrier = true;

        // Check if holding a seed
        for (SeedType seed:SeedType.values()) {
            if (itemName.equalsIgnoreCase(seed.name))
                holdingSeed = true;
        }

        // Return trying to farm
        if (clickedBarrier && holdingSeed)
            return true;
        return false;
    }

    public static boolean isInFarmingArea(BlockPos blockPos) {
        // Check if getFarmingLocation() returns null
        if (getFarmingLocation(blockPos) != null)
            return true;
        return false;
    }

    public static boolean isInFarmingPatch(FarmingLocations location, BlockPos blockPos) {
        // Check if getFarmingPatchLocation returns null
        if (getFarmingPatchLocation(location, blockPos) != null)
            return true;
        return false;
    }

    public static FarmingLocations getFarmingLocation(BlockPos blockPos) {

        int x = blockPos.getX();
        int z = blockPos.getZ();
        // Loop through the FarmingLocations to see if the clicked block in their region
        for (FarmingLocations location:FarmingLocations.values()) {
            if (Util.withinRegion(location.maxX, location.minX, location.maxZ, location.minZ, x, z))
                return location;
        }
        return null;
    }

    public static FarmingPatchLocations getFarmingPatchLocation(FarmingLocations location, BlockPos blockPos) {
        return getFarmingPatchFromLocation(location, blockPos).getPatchLocation();
    }

    public static FarmingPatch getFarmingPatchFromLocation(FarmingLocations location, BlockPos blockPos) {

        int x = blockPos.getX();
        int z = blockPos.getZ();
        // Loop through all the keys for the EnumMap<FarmingPatchLocation, FarmingPatch> to find the farming patches
        for (FarmingPatchLocations patchLocationKey:location.patches.keySet()) {
            if (location.patches.containsKey(patchLocationKey)) {
                FarmingPatch patch = location.patches.get(patchLocationKey);
                // Check if the clicked block is in the region of the current patch
                int[] region = patch.region;
                boolean withinRegion;
                // TODO FIX THIS SHIT
                if (patch.getPatchType() == PatchType.ALLOTMENT)
                    withinRegion = Util.withinCornerRegion(region[0],region[1], region[2], region[3], region[4], region[5], region[6], region[7], x, z);
                else
                    withinRegion = Util.withinRegion(region[0], region[1], region[2], region[3], x, z);
                if (withinRegion) {
                    return patch;
                }
            }
        }
        return null;
    }

    public static SeedType getSeed(ItemStack item) {
        // Cycle through all SeedTypes to see if the name matches the name on the held item
        String itemName = item.getDisplayName().getFormattedText().substring(2);

        for (SeedType seed:SeedType.values()) {
            if (itemName.equalsIgnoreCase(seed.name)) {
                return seed;
            }
        }

        return null;
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

    public static boolean checkForCompletedTimers() {
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

    public static void reduceArmorStandsInFarmingPatch(ArmorStandEntity armorstand) {
        // Get the armor stand locations that should be kept rendered
        List<double[]> exceptions = new ArrayList<>();
        // South Falador Allotment 1
        exceptions.add(new double[]{105.5, -94.5});
        // South Falador Allotment 2
        exceptions.add(new double[]{123.5, -82.5});
        // North Catherby Allotment 1
        exceptions.add(new double[]{-624.5, -553.5});
        // North Catherby Allotment 2
        exceptions.add(new double[]{-624.5, -531.5});

        // check if the armorstand is in the general area of a farming patch
        FarmingLocations location = FarmingUtil.getFarmingLocation(armorstand.getPosition());
        if (location != null) {
            // Get the specific patch the armorstand is in
            FarmingPatch patch = FarmingUtil.getFarmingPatchFromLocation(location, armorstand.getPosition());
            if (patch != null) {
                FarmingPatchLocations patchLocation = patch.getPatchLocation();
                // Check if it is in an allotment patch
                if (patch.getPatchType() == PatchType.ALLOTMENT) {
                    double x = armorstand.getPosX();
                    double z = armorstand.getPosZ();
                    // Farming items are either an iron sword or iron pickaxe
                    // They will be in the head, mainhand, or offhand slot
                    // It will check if it is holding the correct item and replace it with air
                    // This grabs the 3 possible slots
                    String headItem = armorstand.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem().getName().getFormattedText();
                    String mainHandItem = armorstand.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem().getName().getFormattedText();
                    String offHandItem = armorstand.getItemStackFromSlot(EquipmentSlotType.OFFHAND).getItem().getName().getFormattedText();
                    // This will check for the head slot
                    if (headItem.equalsIgnoreCase("iron pickaxe") || headItem.equalsIgnoreCase("iron sword")) {
                        for (double[] exception : exceptions) {
                            if (x == exception[0] && z == exception[1])
                                return;
                        }
                        armorstand.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.AIR));
                    }
                    // This will check for the mainhand slot
                    if (mainHandItem.equalsIgnoreCase("iron pickaxe") || headItem.equalsIgnoreCase("iron sword")) {
                        for (double[] exception : exceptions) {
                            if (x == exception[0] && z == exception[1])
                                return;
                        }
                        armorstand.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.AIR));
                    }
                    // This will check for the offhand slot
                    if (offHandItem.equalsIgnoreCase("iron pickaxe") || headItem.equalsIgnoreCase("iron sword")) {
                        for (double[] exception : exceptions) {
                            if (x == exception[0] && z == exception[1])
                                return;
                        }
                        armorstand.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.AIR));
                    }
                }
            }
        }
    }
}
