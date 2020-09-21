package com.zylr.minescapeaddons.addons.camera;

import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;

public class ToggleableClippingHelper extends ClippingHelperImpl {

    //private static final ToggleableClippingHelper INSTANCE = new ToggleableClippingHelper();

    public ToggleableClippingHelper(Matrix4f p_i226026_1_, Matrix4f p_i226026_2_) {
        super(p_i226026_1_, p_i226026_2_);
    }
/*
    public static ToggleableClippingHelper getInstance() {
        INSTANCE.init();
        return INSTANCE;
    }

    public static ClippingHelperImpl getInstanceWrapper() {
        INSTANCE.init();
        return INSTANCE;
    }

    private boolean enabled;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isBoxInFrustum(double x1, double y1, double z1, double x2, double y2, double z2) {
        if (enabled) {
            return super.isBoxInFrustum(x1, y1, z1, x2, y2, z2);
        } else {
            return true;
        }
    }

 */
}