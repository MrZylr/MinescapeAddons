package com.zylr.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

public class util {
    private static final long ONLINE_PLAYER_CACHE_TTL_MS = 1000L;
    private static final Set<UUID> ONLINE_PLAYER_IDS = new HashSet<>();
    private static long onlinePlayerCacheUpdatedMillis;

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
            if (mob == mc.player)
                return true;
            refreshOnlinePlayerCache(mc);
            return ONLINE_PLAYER_IDS.contains(mob.getUUID());
        }
        return false;
    }

    private static void refreshOnlinePlayerCache(Minecraft mc) {
        long now = System.currentTimeMillis();
        if (now - onlinePlayerCacheUpdatedMillis < ONLINE_PLAYER_CACHE_TTL_MS) return;
        onlinePlayerCacheUpdatedMillis = now;
        ONLINE_PLAYER_IDS.clear();
        if (mc == null || mc.getConnection() == null) return;
        for (PlayerInfo onlinePlayer : mc.getConnection().getOnlinePlayers()) {
            if (onlinePlayer.getProfile().name() != null) {
                ONLINE_PLAYER_IDS.add(onlinePlayer.getProfile().id());
            }
        }
    }

    public static boolean isArmorStandMob(ArmorStand armorStand) {
        return armorStand.getName().getString().matches("\\[\\d+\\].*\\[\\d+\\].*");
    }

    public static boolean isArmorstandNPC(ArmorStand armorStand) {
        List<String> npcNames = new ArrayList<>();
        npcNames.add("Camel");
        npcNames.add("Tracker Gnome");
        npcNames.add("Commander Montai");
        npcNames.add("Elkoy");
        npcNames.add("Remsai");
        npcNames.add("Bolkoy");
        npcNames.add("King Bolren");
        npcNames.add("Local Gnome");
        npcNames.add("Og");
        npcNames.add("Chief Tess");
        npcNames.add("Grew");
        npcNames.add("Captain Lawgof");
        npcNames.add("Stankers");
        npcNames.add("Dusuri");
        npcNames.add("Belona");
        npcNames.add("Drogo");
        npcNames.add("Nurmof");
        npcNames.add("Hura");
        npcNames.add("Austri");
        npcNames.add("Vestri");
        npcNames.add("General Bentnoze");
        npcNames.add("General Wartface");
        npcNames.add("Grubfoot");
        npcNames.add("Tool Leprechaun");
        npcNames.add("Dairy cow");
        npcNames.add("Homunculus");
        npcNames.add("Co-ordinator");
        npcNames.add("Chaeldar");
        npcNames.add("Fairy Queen");
        npcNames.add("Fairy");
        npcNames.add("Sheep");
        npcNames.add("Fairy Chef");
        npcNames.add("Fairy shop assistant");
        npcNames.add("Fairy shop keeper");
        npcNames.add("Banker");
        npcNames.add("Gatekeeper");
        npcNames.add("Jukat");
        npcNames.add("Lunderwin");
        npcNames.add("Irksol");
        npcNames.add("Hudon");
        npcNames.add("Golrie");
        npcNames.add("Doric");
        npcNames.add("Boy");
        npcNames.add("Professor Onglewip");
        npcNames.add("Gamefred");
        npcNames.add("Professor Imblewyn");
        npcNames.add("Eniola");
        npcNames.add("Thurgo");

        return npcNames.contains(armorStand.getName().getString());
    }

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
}
