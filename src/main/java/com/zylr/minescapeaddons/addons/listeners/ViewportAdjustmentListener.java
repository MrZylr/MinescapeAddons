package com.zylr.minescapeaddons.addons.listeners;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.annotations.Listener;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

/**
 * Restricts the GL viewport to the left (windowWidth - PANEL_WIDTH) pixels
 * and the upper (windowHeight - PANEL_HEIGHT) pixels
 * during level rendering, leaving the right and bottom strips blank for custom GUI.
 *
 * The projection matrix is handled upstream in GameRendererMixin, which crops
 * computeProjectionMatrix()'s return value — ensuring frustum culling, raycasts,
 * entity positions, and block highlights all use the correct cropped projection.
 * RenderTargetMixin restores this same viewport after every bindWrite() reset.
 */
@Listener
public class ViewportAdjustmentListener {

    /** Must match GameRendererMixin.PANEL_WIDTH_GUI and RenderTargetMixin.PANEL_WIDTH_GUI. */
    public static final int PANEL_WIDTH = 250;

    /** Height in GUI pixels to crop from the bottom of the viewport. */
    public static final int PANEL_HEIGHT = 142;

    /**
     * Set to true while the 3D level is rendering so that RenderTargetMixin can
     * restore the narrow viewport after every RenderTarget.bindWrite() call
     * (which always resets the GL viewport to full framebuffer size).
     */
    public static boolean isLevelRendering = false;

    public static boolean isShadersActive() {
        try {
            // Iris API
            Class<?> irisApi = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            Object instance = irisApi.getMethod("getInstance").invoke(null);
            return (boolean) instance.getClass().getMethod("isShaderPackInUse").invoke(instance);
        } catch (Exception ignored) {}
        try {
            // Oculus (Forge port of Iris)
            Class<?> oculus = Class.forName("net.coderbot.iris.Iris");
            return (boolean) oculus.getMethod("isShadersEnabled").invoke(null);
        } catch (Exception ignored) {}
        return false;
    }

    @SubscribeEvent
    public void onRenderLevelStage(RenderLevelStageEvent event) {
        Minecraft mc = Minecraft.getInstance();

        // If fixed-mode is disabled, ensure we don't apply any viewport cropping.
        // If we were previously rendering the level with a cropped viewport,
        // restore the full viewport before returning.
        if (!Config.getFixedMode()) {
            if (isLevelRendering) {
                isLevelRendering = false;
                if (mc != null && mc.getWindow() != null) {
                    int fbWidth  = mc.getWindow().getWidth();
                    int fbHeight = mc.getWindow().getHeight();
                    GlStateManager._viewport(0, 0, fbWidth, fbHeight);
                }
            }
            return;
        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            int newPanelWidth  = PANEL_WIDTH;
            int newPanelHeight = PANEL_HEIGHT;
            if (isShadersActive()) {
                newPanelWidth = (int)(PANEL_WIDTH * 0.54);
                newPanelHeight = (int)(PANEL_HEIGHT * 0.54);
            }
            int fbWidth        = mc.getWindow().getWidth();
            int fbHeight       = mc.getWindow().getHeight();
            double guiScale    = mc.getWindow().getGuiScale();
            int panelPhysicalW = (int) Math.round(newPanelWidth  * guiScale);
            int panelPhysicalH = (int) Math.round(newPanelHeight * guiScale);
            int viewW          = Math.max(1, fbWidth  - panelPhysicalW);
            int viewH          = Math.max(1, fbHeight - panelPhysicalH);

            isLevelRendering = true;
            GlStateManager._viewport(0, panelPhysicalH, viewW, viewH);
        }
        else if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL) {
            isLevelRendering = false;
            int fbWidth  = mc.getWindow().getWidth();
            int fbHeight = mc.getWindow().getHeight();
            GlStateManager._viewport(0, 0, fbWidth, fbHeight);
        }
    }
}
