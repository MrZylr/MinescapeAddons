package com.zylr.minescapeaddons.addons.skills.farming;

import com.zylr.minescapeaddons.addons.util.Timer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ClickOnFarmingPatch {
    private Timer timer;
    private SeedType seed;
    private FarmingLocations location;
    private FarmingPatchLocations patchLocation;

    public ClickOnFarmingPatch(BlockPos pos, ItemStack itemStack) {
        // Setup timer for tracking the xp popup
        this.timer = new Timer();
        // Handle the click with seed in hand
        this.seed = FarmingUtil.getSeed(itemStack);
        this.location = FarmingUtil.getFarmingLocation(pos);
        handleClickWithSeed(pos, itemStack);
    }

    public void handleClickWithSeed(BlockPos pos, ItemStack itemStack) {
        // Get the patch from the location
        if (location != null) {
            this.patchLocation = FarmingUtil.getFarmingPatchLocation(location, pos);
        }else
            return;
        this.timer.startTimer();
    }

    public void setSeed() {
        // Has it been less than 5 seconds since clicking on the farm patch
        if (this.timer.hasStarted() && this.timer.getElapsedTime()/1000 <= 5) {
            // Starts the timer by giving it a seed
            location.patches.get(patchLocation).setSeed(seed);
            // Saves farming timers
            FarmingUtil.writeTimersToFile();
        }
    }
}
