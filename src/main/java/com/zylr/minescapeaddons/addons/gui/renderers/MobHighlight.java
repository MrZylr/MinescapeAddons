package com.zylr.minescapeaddons.addons.gui.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class MobHighlight {
    private static final Logger log = LoggerFactory.getLogger(MobHighlight.class);

    @SubscribeEvent
    public void onRenderLevelStage(RenderLevelStageEvent e) {
        if (e.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;


        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null)
            return;

        if (Config.getMobHighlight()) {
            PoseStack stack = e.getPoseStack();
            MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
            EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();

            Vec3 camPos = dispatcher.camera.getPosition();

            for (Entity entity : mc.level.entitiesForRendering()) {
                if (entity instanceof ArmorStand armorStand) {
                    // Check if the armor stand has a name that matches the pattern
                    // Highlight armor stands with names that contain digits and letters
                    if(Util.isArmorStandMob(armorStand) || Util.isImpling(entity)) {
                        AABB box = armorStand.getBoundingBox().move(-camPos.x, -camPos.y, -camPos.z);
                        Vec3 boxCenter = armorStand.getBoundingBox().getCenter();
                        double distance = boxCenter.distanceTo(mc.player.position());

                        // Set color green/red
                        if (distance >= 2.75D)
                            LevelRenderer.renderLineBox(stack, bufferSource.getBuffer(RenderType.lines()), box,
                                    1.0F, 0.0F, 0.0F, 0.2F);
                        else {
                            LevelRenderer.renderLineBox(stack, bufferSource.getBuffer(RenderType.lines()), box,
                                    0.0F, 1.0F, 0.0F, 0.2F);
                        }
                    }
                }

                if (entity instanceof Player mob) {
                    if(Util.isMob(entity)) {
                        // This is not a real player, so highlight it
                        AABB box = mob.getBoundingBox().move(-camPos.x, -camPos.y, -camPos.z);
                        Vec3 boxCenter = mob.getBoundingBox().getCenter();
                        double distance = boxCenter.distanceTo(mc.player.position());

                        // Set color green/red
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
