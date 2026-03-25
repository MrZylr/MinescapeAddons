package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.skills.farming.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BlockClickListener {

    private ArmorStand lastLookedAtArmorStand = null;
    Logger LOGGER = MinescapeAddons.LOGGER;

    // Shared scheduler – avoids creating a new thread per farming patch click
    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "MinescapeAddons-FarmingScheduler");
        t.setDaemon(true);
        return t;
    });

    // Static lookup map for SeedType – built once at class load
    private static final Map<String, SeedType> SEED_TYPE_MAP = new HashMap<>();
    static {
        for (SeedType s : SeedType.values()) {
            if (s.name != null && !s.name.isEmpty())
                SEED_TYPE_MAP.put(s.name.toLowerCase(), s);
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();
        ItemStack itemInHand = player.getItemInHand(hand);

        if (hand != InteractionHand.MAIN_HAND)
            return;

        // Resolve farming location once – avoids double lookup inside isInFarmingPatch + getFarmingLocation
        if (FarmingUtil.isInFarmingArea(pos)) {
            FarmingLocations location = FarmingUtil.getFarmingLocation(pos);
            if (location != null && FarmingUtil.isInFarmingPatch(location, pos)) {
                LOGGER.info(pos.getX() + ", " + pos.getZ());
                SeedType seed = resolveSeed(itemInHand);
                if (seed != null)
                    handleFarmingPatchClick(seed, pos);
                else
                    LOGGER.info("Farming patch clicked with non-seed item.");
            }
        }
    }

    private void handleFarmingPatchClick(SeedType seed, BlockPos pos) {
        LOGGER.info("Farming patch clicked with seed: " + seed.name);

        FarmingLocations location = FarmingUtil.getFarmingLocation(pos);
        if (location != null) {
            LOGGER.info("Farming location identified: " + location.name());
            FarmingPatch farmingPatch = FarmingUtil.getFarmingPatchFromLocation(location, pos);
            if (farmingPatch != null) {
                LOGGER.info("Farming patch identified: " + farmingPatch.getPatchName());
                FarmingPatchClicked.patchClicked = true;

                // Schedule task using the shared daemon scheduler – avoids leaking a thread per click
                SCHEDULER.schedule(() -> Minecraft.getInstance().execute(() -> {
                    if (FarmingPatchClicked.bothClickedAndGainedXp()) {
                        LOGGER.info("Farming action confirmed for patch: " + location.name() + ":" + farmingPatch.getPatchName());
                        farmingPatch.setSeed(seed);
                        LOGGER.info("Resetting FarmingPatchClicked state.");
                        FarmingPatchClicked.reset();
                    }
                }), 3000, TimeUnit.MILLISECONDS);
            } else {
                LOGGER.warn("No farming patch found at the clicked position: " + pos);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            return;
        }

        // Only check every 5 ticks to reduce performance impact
        if (mc.player.tickCount % 5 != 0) {
            return;
        }

        // Refresh farming timer cache (replaces per-frame nested loop in HudMenuWidget)
        FarmingUtil.tickTimerCache();

        // Skip if a screen is open (player can't interact with the world)
        if (mc.screen != null) {
            lastLookedAtArmorStand = null;
            return;
        }

        Player player = mc.player;
        Level level = mc.level;
        double reach = 5.0;

        Vec3 eyePos  = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 reachVec = eyePos.add(lookVec.scale(reach));

        AABB searchBox = player.getBoundingBox()
                .expandTowards(lookVec.scale(reach))
                .inflate(1.0);

        // Use getEntitiesOfClass to only iterate ArmorStands – skips all other entity types
        for (ArmorStand armorStand : level.getEntitiesOfClass(ArmorStand.class, searchBox)) {
            Optional<Vec3> hit = armorStand.getBoundingBox().clip(eyePos, reachVec);
            if (hit.isPresent()) {
                lastLookedAtArmorStand = armorStand;
                return;
            }
        }

        lastLookedAtArmorStand = null;
    }

    // Removed unused armorStand parameter
    private void handleArmorStandClick(BlockPos pos) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        ItemStack itemInHand = mc.player.getItemInHand(InteractionHand.MAIN_HAND);
        LOGGER.info(pos.getX() + ", " + pos.getZ());
        if (FarmingUtil.isInFarmingArea(pos)) {
            FarmingLocations location = FarmingUtil.getFarmingLocation(pos);
            if (location != null && FarmingUtil.isInFarmingPatch(location, pos)) {
                SeedType seed = resolveSeed(itemInHand);
                if (seed != null)
                    handleFarmingPatchClick(seed, pos);
                else
                    LOGGER.info("Farming patch clicked with non-seed item.");
            }
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isUseItem()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || lastLookedAtArmorStand == null) {
                return;
            }

            LOGGER.info("Clicked armor stand: " + lastLookedAtArmorStand.blockPosition());

            if (lastLookedAtArmorStand.hasCustomName() && lastLookedAtArmorStand.getCustomName() != null) {
                LOGGER.info("Name: " + lastLookedAtArmorStand.getCustomName().getString());
            }

            handleArmorStandClick(lastLookedAtArmorStand.blockPosition());
        }
    }

    /** Resolve a SeedType from an item display name (strips colour code wrapper). */
    private static SeedType resolveSeed(ItemStack itemInHand) {
        String raw = itemInHand.getDisplayName().getString();
        String itemName = raw.substring(1, raw.length() - 1);
        return SEED_TYPE_MAP.get(itemName.toLowerCase());
    }
}