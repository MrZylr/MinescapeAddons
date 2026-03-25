package com.zylr.minescapeaddons.addons.utils;

import com.zylr.minescapeaddons.addons.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

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

    public static boolean isAttacking() {
        return false;
    }

    public static OptionalInt getHpFromName(String s) {
        if (s == null) return OptionalInt.empty();
        int open = s.lastIndexOf('[');
        if (open == -1) return OptionalInt.empty();
        int close = s.indexOf(']', open);
        if (close == -1) return OptionalInt.empty();
        String inner = s.substring(open + 1, close).trim();
        if (inner.isEmpty()) return OptionalInt.empty();
        try {
            return OptionalInt.of(Integer.parseInt(inner));
        } catch (NumberFormatException exception) {
            return OptionalInt.empty();
        }
    }

    public static Optional<String> getEntityName(String s) {
        int close = s.indexOf(']');
        if (close == -1) return Optional.empty();
        int open = s.indexOf('[', close);
        if (open == -1) return Optional.empty();
        String out = s.substring(close + 1, open).trim();
        return out.isEmpty() ? Optional.empty() : Optional.of(out);
    }

    public static boolean isMob(Entity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (entity instanceof Player mob) {
            String rawName = mob.getDisplayName().getString();
            if (isRealPlayer(entity))
                return false;
            else if (rawName.contains("[") && rawName.contains("]")) {
                if (rawName.matches("\\[\\d+\\].*\\[\\d+\\].*")) {
                    //System.out.println(rawName);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isImpling(Entity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (entity instanceof ArmorStand armorStand) {
            String rawName = armorStand.getDisplayName().getString();

            if (rawName.contains("Impling")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRealPlayer(Entity entity) {
        Minecraft mc = Minecraft.getInstance();

        if (entity instanceof Player mob) {
            String rawName = mob.getDisplayName().getString();
            if (mob == mc.player)
                return true;
            else {
                for (PlayerInfo onlinePlayer : Minecraft.getInstance().getConnection().getOnlinePlayers()) {
                    if (onlinePlayer.getProfile().getName() != null) {
                        if (mob.getUUID().equals(onlinePlayer.getProfile().getId())) {
                            return true; // This is a real player
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isArmorStandMob(ArmorStand armorStand) {
        return armorStand.getName().getString().matches("\\[\\d+\\].*\\[\\d+\\].*");
    }

    public static boolean isArmorstandNPC(ArmorStand armorStand) {
        List<String> npcNames = new ArrayList<>();
        npcNames.add("Camel");

        return npcNames.contains(armorStand.getName().getString());
    }
}
