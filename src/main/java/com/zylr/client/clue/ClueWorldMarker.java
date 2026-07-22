package com.zylr.client.clue;

import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.world.phys.Vec3;

public final class ClueWorldMarker {
    private static final int MARKER_COLOR = ARGB.opaque(0xFF0000);
    private static final float LINE_WIDTH = 5.0F;
    private static final double MARKER_Y_OFFSET = 1.05D;
    private static final double MARKER_SIZE = 0.85D;

    private ClueWorldMarker() {
    }

    public static void register() {
        LevelRenderEvents.BEFORE_GIZMOS.register(context -> {
            Minecraft minecraft = Minecraft.getInstance();
            ClueScrollClue clue = ClueHelper.activeClue(minecraft);
            if (clue == null || minecraft == null || minecraft.level == null || minecraft.player == null) {
                return;
            }

            BlockPos pos = clue.blockPos();
            double y = pos.getY() + MARKER_Y_OFFSET;
            double minX = pos.getX() + (1.0D - MARKER_SIZE) * 0.5D;
            double maxX = pos.getX() + 1.0D - (1.0D - MARKER_SIZE) * 0.5D;
            double minZ = pos.getZ() + (1.0D - MARKER_SIZE) * 0.5D;
            double maxZ = pos.getZ() + 1.0D - (1.0D - MARKER_SIZE) * 0.5D;

            try (Gizmos.TemporaryCollection ignored = context.levelRenderer().collectPerFrameGizmos()) {
                Gizmos.line(new Vec3(minX, y, minZ), new Vec3(maxX, y, maxZ), MARKER_COLOR, LINE_WIDTH);
                Gizmos.line(new Vec3(minX, y, maxZ), new Vec3(maxX, y, minZ), MARKER_COLOR, LINE_WIDTH);
            }
        });
    }
}
