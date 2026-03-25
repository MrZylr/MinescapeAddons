package com.zylr.minescapeaddons.addons.gui.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.util.ArrayList;
import java.util.List;

public class AgilityCourseHighlights {
    public List<String> agilityObstacleNames;

    public AgilityCourseHighlights() {
        agilityObstacleNames = new ArrayList<>();
        agilityObstacleNames.add("Climb");
        agilityObstacleNames.add("Jump");
        agilityObstacleNames.add("Vault");
        agilityObstacleNames.add("Cross");
        agilityObstacleNames.add("Balance");
        agilityObstacleNames.add("Drop");
        agilityObstacleNames.add("Shortcut");
    }

    @SubscribeEvent
    public void onRenderLevelStage(RenderLevelStageEvent e) {
        if (e.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null)
            return;

        if (Config.getAgilityOutlines()) {
            PoseStack stack = e.getPoseStack();
            MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
            EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();

            Vec3 camPos = dispatcher.camera.getPosition();

            for (Entity entity : mc.level.entitiesForRendering()) {
                if (entity instanceof ArmorStand armorStand) {
                    // Render box around Armorstands
                   if (agilityObstacleNames.contains(armorStand.getName().getString())) {
                        AABB box = armorStand.getBoundingBox().move(-camPos.x, -camPos.y, -camPos.z);
                        Vec3 boxCenter = armorStand.getBoundingBox().getCenter();
                        double distance = boxCenter.distanceTo(mc.player.position());

                        // Set color green/red
                        // Green box
                        if (distance >= 2.75D)
                            LevelRenderer.renderLineBox(stack, bufferSource.getBuffer(RenderType.lines()), box,
                                    1.0F, 0.0F, 0.0F, 0.2F);
                        else {
                            LevelRenderer.renderLineBox(stack, bufferSource.getBuffer(RenderType.lines()), box,
                                    0.0F, 1.0F, 0.0F, 0.2F);
                        }
                   }
                }
            }
        }
    }
}
