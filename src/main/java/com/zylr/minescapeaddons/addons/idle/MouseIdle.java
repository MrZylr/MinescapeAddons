package com.zylr.minescapeaddons.addons.idle;

import net.minecraft.client.Minecraft;

public class MouseIdle {
    private double oldPitch;
    private double oldYaw;
    private double oldMouseX;
    private double oldMouseY;
    public MouseIdle(){
        oldPitch = 0;
        oldYaw = 0;
        oldMouseX = 0;
        oldMouseY = 0;
    }

    public boolean hasMouseMoved() {
        boolean same = false;
        if ((oldYaw != Minecraft.getInstance().player.rotationYaw || oldPitch != Minecraft.getInstance().player.rotationPitch) ||
                (oldMouseX != Minecraft.getInstance().mouseHelper.getMouseX() || oldMouseY != Minecraft.getInstance().mouseHelper.getMouseY())) {
            same = true;
        }
        oldPitch = Minecraft.getInstance().player.rotationPitch;
        oldYaw = Minecraft.getInstance().player.rotationYaw;
        oldMouseX = Minecraft.getInstance().mouseHelper.getMouseX();
        oldMouseY = Minecraft.getInstance().mouseHelper.getMouseY();

        return same;
    }
}
