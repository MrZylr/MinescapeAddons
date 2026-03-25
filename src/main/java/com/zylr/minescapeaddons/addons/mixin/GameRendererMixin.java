package com.zylr.minescapeaddons.addons.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener.PANEL_HEIGHT;
import static com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener.isShadersActive;

/**
 * 1. Changes the aspect ratio used in GameRenderer.getProjectionMatrix() from
 *    (fbWidth / fbHeight) to (viewW / viewH), where
 *    viewW = fbWidth - panelPhysicalW and viewH = fbHeight - panelPhysicalH.
 *
 * 2. Wraps renderItemInHand() with the cropped viewport so that the mainhand
 *    and offhand items are rendered inside the visible play area, not over the
 *    cropped-away strips. The viewport is restored to full size afterwards.
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {

    private static final int PANEL_WIDTH_GUI  = ViewportAdjustmentListener.PANEL_WIDTH;
    private static final int PANEL_HEIGHT_GUI = ViewportAdjustmentListener.PANEL_HEIGHT;

    @Shadow public float getDepthFar() { return 0; }

    // ── Aspect ratio fix ─────────────────────────────────────────────────────

    @ModifyArg(
        method = "getProjectionMatrix",
        at = @At(
            value = "INVOKE",
            target = "Lorg/joml/Matrix4f;perspective(FFFF)Lorg/joml/Matrix4f;"
        ),
        index = 1  // the aspect ratio argument (index 0 = fovY, 1 = aspect, 2 = zNear, 3 = zFar)
    )
    private float adjustAspectRatio(float originalAspect) {
        if (!Config.getFixedMode()) return originalAspect;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return originalAspect;

        int fbWidth  = mc.getWindow().getWidth();
        int fbHeight = mc.getWindow().getHeight();
        if (fbWidth <= 0 || fbHeight <= 0) return originalAspect;

        int panelPhysicalW = (int) Math.round(PANEL_WIDTH_GUI  * mc.getWindow().getGuiScale());
        int panelPhysicalH = (int) Math.round(PANEL_HEIGHT_GUI * mc.getWindow().getGuiScale());
        int viewW          = Math.max(1, fbWidth  - panelPhysicalW);
        int viewH          = Math.max(1, fbHeight - panelPhysicalH);

        return (float) viewW / (float) viewH;
    }

    // ── Hand viewport fix ────────────────────────────────────────────────────
    // renderItemInHand() is called after AFTER_LEVEL, at which point the viewport
    // has already been restored to full size. We re-apply the cropped viewport so
    // the hands appear inside the visible play area, then restore it afterward.

    @Inject(method = "renderItemInHand", at = @At("HEAD"))
    private void setHandViewport(CallbackInfo ci) {
        if (!Config.getFixedMode()) return;
        int newPanelWidth  = PANEL_WIDTH_GUI;
        int newPanelHeight = PANEL_HEIGHT_GUI;
        if (isShadersActive()) {
            newPanelWidth = 0;
            newPanelHeight = 0;
        }
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

    @Inject(method = "renderItemInHand", at = @At("TAIL"))
    private void restoreFullViewportAfterHand(CallbackInfo ci) {
        if (!Config.getFixedMode()) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        int fbWidth  = mc.getWindow().getWidth();
        int fbHeight = mc.getWindow().getHeight();
        if (fbWidth <= 0 || fbHeight <= 0) return;

        GlStateManager._viewport(0, 0, fbWidth, fbHeight);
    }
}
