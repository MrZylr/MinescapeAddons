package com.zylr.minescapeaddons.addons.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.Minecraft;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener.isShadersActive;

/**
 * Intercepts RenderTarget.bindWrite() during level rendering and resets the
 * GL viewport back to the cropped viewport after the framebuffer binding resets
 * it to the full window size.
 *
 * RenderTarget.bindWrite() always calls GlStateManager._viewport(0, 0, this.width, this.height)
 * which undoes our ViewportAdjustmentListener's glViewport(0, panelPhysicalH, viewW, viewH) call.
 * This mixin runs after bindWrite and restores the correct cropped viewport.
 */
@Mixin(RenderTarget.class)
public class RenderTargetMixin {

    private static final int PANEL_WIDTH_GUI  = 250;
    private static final int PANEL_HEIGHT_GUI = 142;

    @Inject(method = "bindWrite", at = @At("TAIL"))
    private void restoreNarrowViewport(boolean updateViewport, CallbackInfo ci) {
        int newPanelWidth  = PANEL_WIDTH_GUI;
        int newPanelHeight = PANEL_HEIGHT_GUI;
        if (isShadersActive()) {
            newPanelWidth = (int)(PANEL_WIDTH_GUI * 0.54);
            newPanelHeight = (int)(PANEL_HEIGHT_GUI * 0.54);
        }
        // Only apply during level rendering (when the flag is active)
        if (!ViewportAdjustmentListener.isLevelRendering) return;
        if (!updateViewport) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        int fbWidth  = mc.getWindow().getWidth();
        int fbHeight = mc.getWindow().getHeight();
        if (fbWidth <= 0 || fbHeight <= 0) return;

        double guiScale    = mc.getWindow().getGuiScale();
        int panelPhysicalW = (int) Math.round(newPanelWidth  * guiScale);
        int panelPhysicalH = (int) Math.round(newPanelHeight * guiScale);
        int viewW          = Math.max(1, fbWidth  - panelPhysicalW);
        int viewH          = Math.max(1, fbHeight - panelPhysicalH);

        GlStateManager._viewport(0, panelPhysicalH, viewW, viewH);
    }
}


