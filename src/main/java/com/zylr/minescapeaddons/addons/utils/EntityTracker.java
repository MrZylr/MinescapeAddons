package com.zylr.minescapeaddons.addons.utils;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import java.util.*;

public class EntityTracker {
    private static Entity target = null;
    private static int totalHp = 0;
    private static final double range = 25.0;
    private static TargetState targetState = TargetState.NONE;

    public static Entity getTargetedEntity() {
        return target;
    }
    public static void setTargetedEntity(Entity entity) {
        target = entity;
    }

    public static int getTotalHp() {
        return totalHp;
    }
    public static void setTotalHp(int hp) {
        totalHp = hp;
    }

    public static double getRange() {
        return range;
    }

    public static TargetState getTargetState() {
        return targetState;
    }
    public static void setTargetState(TargetState ts) {
        targetState = ts;
    }

    public enum TargetState {
        NONE,   // no target tracked / can not evaluate
        VALID,  // target alive and within range
        LEFT_RADIUS, // target left the configured radius
        DEAD    // target died or was removed from the world
    }

    /**
     * Finds a nearby entity with the given UUID around the client player within the given radius.
     * Uses the client level and player position as the search center. Returns an Optional containing
     * the first matching entity found.
     */
    public static Optional<Entity> findNearbyEntityByUUID(UUID uuid, double radius) {
        if (uuid == null) return Optional.empty();
        Minecraft mc = Minecraft.getInstance();
        if (mc == null) return Optional.empty();
        Level level = mc.level;
        if (level == null) return Optional.empty();
        if (mc.player == null) return Optional.empty();

        Vec3 center = mc.player.position();
        AABB box = new AABB(
                center.x - radius, center.y - radius, center.z - radius,
                center.x + radius, center.y + radius, center.z + radius
        );

        List<Entity> hits = level.getEntitiesOfClass(Entity.class, box, e -> uuid.equals(e.getUUID()));
        if (hits.isEmpty()) return Optional.empty();
        return Optional.of(hits.getFirst());
    }

    /**
     * Convenience boolean check whether an entity with the UUID is nearby.
     */
    public static boolean isEntityNearby(UUID uuid, double radius) {
        return findNearbyEntityByUUID(uuid, radius).isPresent();
    }

    /**
     * Evaluate the current tracked target and return whether it is still valid,
     * has left the configured radius, or cannot be evaluated.
     */
    public static void evaluateTargetState() {
        Entity t = getTargetedEntity();
        if (t == null) {
            targetState = TargetState.NONE;
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.player == null) {
            targetState = TargetState.NONE;
            return;
        }

        if (targetState == TargetState.DEAD)
            return;

        Vec3 center = mc.player.position();
        double distSq = t.position().distanceToSqr(center);
        double rangeVal = getRange();
        if (distSq > rangeVal * rangeVal) {
            targetState = TargetState.LEFT_RADIUS;
            return;
        }


        targetState = TargetState.VALID;
    }

    public static void resetTargetedEntity() {
        EntityTracker.setTargetedEntity(null);
        EntityTracker.setTotalHp(0);
        EntityTracker.setTargetState(TargetState.NONE);
    }
}