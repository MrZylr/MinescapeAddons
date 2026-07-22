package com.zylr.client;

import com.zylr.client.hud.HudManager;
import com.zylr.utils.util;
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

public final class CustomMobOutlines {
    private static final int CUSTOM_MOB_OUTLINE = ARGB.opaque(0xFFFF00);
    private static final double MIN_BOX_WIDTH = 0.65D;
    private static final double MIN_BOX_HEIGHT = 1.0D;

    private CustomMobOutlines() {
    }

    public static void register() {
        LevelRenderEvents.BEFORE_GIZMOS.register(context -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (!HudManager.getInstance().isCustomMobOutlinesEnabled() || minecraft.level == null) {
                return;
            }

            try (Gizmos.TemporaryCollection ignored = context.levelRenderer().collectPerFrameGizmos()) {
                for (Entity entity : minecraft.level.entitiesForRendering()) {
                    if (isCustomMob(entity)) {
                        Gizmos.cuboid(outlineBox(entity), GizmoStyle.stroke(CUSTOM_MOB_OUTLINE, 2.0F));
                    }
                }
            }
        });
    }

    private static boolean isCustomMob(Entity entity) {
        if (entity instanceof Player) {
            return util.isMob(entity) && !util.isRealPlayer(entity);
        }

        if (entity instanceof ArmorStand armorStand) {
            return util.isArmorStandMob(armorStand);
        }

        return false;
    }

    private static AABB outlineBox(Entity entity) {
        AABB box = entity.getBoundingBox();
        double width = Math.max(Math.max(box.getXsize(), box.getZsize()), MIN_BOX_WIDTH);
        double height = Math.max(box.getYsize(), MIN_BOX_HEIGHT);
        Vec3 center = box.getCenter();
        double halfWidth = width * 0.5D;
        return new AABB(
            center.x - halfWidth,
            box.minY,
            center.z - halfWidth,
            center.x + halfWidth,
            box.minY + height,
            center.z + halfWidth
        );
    }
}
