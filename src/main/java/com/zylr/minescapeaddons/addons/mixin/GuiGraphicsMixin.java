package com.zylr.minescapeaddons.addons.mixin;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.zylr.minescapeaddons.addons.listeners.ViewportAdjustmentListener.isShadersActive;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    public static final int PANEL_WIDTH = ViewportAdjustmentListener.PANEL_WIDTH;
    public static final int PANEL_HEIGHT = ViewportAdjustmentListener.PANEL_HEIGHT;

    @Inject(method = "guiWidth", at = @At("RETURN"), cancellable = true)
    public void modifyGuiWidth(CallbackInfoReturnable<Integer> cir) {
        if (!Config.getFixedMode()) return;
        int originalWidth = cir.getReturnValue();
        // Reduce GUI width by 250 pixels to make room for side panel
        int modifiedWidth = originalWidth - PANEL_WIDTH;
        cir.setReturnValue(modifiedWidth);
    }

    @Inject(method = "guiHeight", at = @At("RETURN"), cancellable = true)
    public void modifyGuiHeight(CallbackInfoReturnable<Integer> cir) {
        if (!Config.getFixedMode()) return;
        int originalHeight = cir.getReturnValue();
        // Reduce GUI height by 142 pixels to make room for bottom panel
        int modifiedHeight = originalHeight - PANEL_HEIGHT;
        cir.setReturnValue(modifiedHeight);
    }
}
