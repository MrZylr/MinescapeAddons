package com.zylr.minescapeaddons.addons.mixin;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.utils.Util;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {


    @Shadow public abstract boolean isLocalPlayer();

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        ClientLevel level = Minecraft.getInstance().level;
        Player player = (Player) (Object) this;

        if (level == null) return;

        // Only spawn for Zylr
        if (Config.getZylrParticles()) {
            if (player.getUUID().toString().equals("ba3ab04e-9b13-4a4d-a477-81c4798982b1")) {
                if (this.isLocalPlayer() && Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON) {
                    // Spawn a few purple/portal particles around the player's torso each tick
                    spawnParticles(level, player);
                } else if (!this.isLocalPlayer()) {
                    spawnParticles(level, player);
                }
            }
        }
    }

    private void spawnParticles(Level level, Player player) {
        for (int i = 0; i < 6; i++) {
            double rx = (player.getRandom().nextDouble() - 0.5) * 1.2;
            double ry = player.getRandom().nextDouble() * player.getBbHeight() * 0.8 + 0.2;
            double rz = (player.getRandom().nextDouble() - 0.5) * 1.2;

            double px = player.getX() + rx;
            double py = player.getY() + ry;
            double pz = player.getZ() + rz;

            double vx = (player.getRandom().nextDouble() - 0.5) * 0.02;
            double vy = 0.02 + player.getRandom().nextDouble() * 0.02;
            double vz = (player.getRandom().nextDouble() - 0.5) * 0.02;

            level.addParticle(ParticleTypes.PORTAL, px, py, pz, vx, vy, vz);
        }
    }
}