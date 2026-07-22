package com.zylr.client;

import com.zylr.client.hud.HudManager;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Locale;

public final class AgilityShortcutOutlines {
    private static final String[] AGILITY_OBSTACLE_NAMES = {
        "climb",
        "jump",
        "vault",
        "cross",
        "balance",
        "drop",
        "shortcut"
    };
    private static final int REACHABLE_SHORTCUT_OUTLINE = ARGB.opaque(0x00FF00);
    private static final int UNREACHABLE_SHORTCUT_OUTLINE = ARGB.opaque(0xFF0000);
    private static final double MIN_BOX_WIDTH = 0.65D;
    private static final double MIN_BOX_HEIGHT = 1.0D;
    private static final double REACH_DISTANCE_SCALE = 0.65D;

    private AgilityShortcutOutlines() {
    }

    public static void register() {
        LevelRenderEvents.BEFORE_GIZMOS.register(context -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (!HudManager.getInstance().isAgilityShortcutOutlinesEnabled() || minecraft.level == null || minecraft.player == null) {
                return;
            }

            try (Gizmos.TemporaryCollection ignored = context.levelRenderer().collectPerFrameGizmos()) {
                for (Entity entity : minecraft.level.entitiesForRendering()) {
                    if (!(entity instanceof ArmorStand armorStand) || !isAgilityShortcut(armorStand)) {
                        continue;
                    }

                    int color = outlineColor(minecraft.player, armorStand);
                    Gizmos.cuboid(markerBox(armorStand), GizmoStyle.stroke(color, 2.0F));
                }
            }
        });
    }

    public static boolean isAgilityShortcut(ArmorStand armorStand) {
        String armorStandName = armorStand.getDisplayName().getString().toLowerCase(Locale.ROOT);
        for (String obstacleName : AGILITY_OBSTACLE_NAMES) {
            if (armorStandName.contains(obstacleName)) {
                return true;
            }
        }
        return false;
    }

    public static int outlineColor(Player player, ArmorStand armorStand) {
        return isWithinShortenedReach(player, armorStand) ? REACHABLE_SHORTCUT_OUTLINE : UNREACHABLE_SHORTCUT_OUTLINE;
    }

    private static boolean isWithinShortenedReach(Player player, ArmorStand armorStand) {
        double reach = player.entityInteractionRange() * REACH_DISTANCE_SCALE;
        return player.getEyePosition().distanceToSqr(armorStand.getBoundingBox().getCenter()) <= reach * reach;
    }

    private static AABB markerBox(ArmorStand armorStand) {
        AABB box = armorStand.getBoundingBox();
        double width = Math.max(Math.max(box.getXsize(), box.getZsize()), MIN_BOX_WIDTH);
        double height = Math.max(box.getYsize(), MIN_BOX_HEIGHT);
        Vec3 center = armorStand.position();
        double halfWidth = width * 0.5D;
        return new AABB(
            center.x - halfWidth,
            center.y,
            center.z - halfWidth,
            center.x + halfWidth,
            center.y + height,
            center.z + halfWidth
        );
    }
}
