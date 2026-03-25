package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.utils.EntityTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;

public class EntityRendererListener {
    @SubscribeEvent
    public void onRenderPlayer(RenderLivingEvent.Post e) {
        Minecraft mc = Minecraft.getInstance();
    }
}
