package com.zylr.minescapeaddons.addons.mixin;

import com.mojang.blaze3d.platform.Window;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.BossEvent;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Mixin(BossHealthOverlay.class)
public abstract class BossHealthOverlayMixin {

    @Shadow
    private Map<UUID, LerpingBossEvent> events;
    @Shadow
    private Minecraft minecraft;
    @Shadow
    abstract void drawBar(GuiGraphics guiGraphics, int x, int y, BossEvent event);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(GuiGraphics guiGraphics, CallbackInfo ci) {
        if (Config.getFixedMode()) {
            // Cancel the rendering of the boss health overlay
            ci.cancel();

            if (!this.events.isEmpty()) {
                this.minecraft.getProfiler().push("bossHealth");
                int i = guiGraphics.guiWidth() - ViewportAdjustmentListener.PANEL_WIDTH;
                int j = 12;

                for (LerpingBossEvent lerpingbossevent : this.events.values()) {
                    int k = i / 2 - 91;
                    Window var10001 = this.minecraft.getWindow();
                    Objects.requireNonNull(this.minecraft.font);
                    CustomizeGuiOverlayEvent.BossEventProgress event = ClientHooks.onCustomizeBossEventProgress(guiGraphics, var10001, lerpingbossevent, k, j, 10 + 9);
                    if (!event.isCanceled()) {
                        this.drawBar(guiGraphics, k, j, lerpingbossevent);
                        Component component = lerpingbossevent.getName();
                        int l = this.minecraft.font.width(component);
                        int i1 = i / 2 - l / 2;
                        int j1 = j - 9;
                        guiGraphics.drawString(this.minecraft.font, component, i1, j1, 16777215);
                    }

                    j += event.getIncrement();
                    if (j >= guiGraphics.guiHeight() / 3) {
                        break;
                    }
                }

                this.minecraft.getProfiler().pop();
            }
        }
    }
}
