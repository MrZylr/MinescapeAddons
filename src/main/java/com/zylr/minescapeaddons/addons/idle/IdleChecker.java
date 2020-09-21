package com.zylr.minescapeaddons.addons.idle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class IdleChecker {
    private IdleTimer xpTimer;
    private IdleTimer inputTimer;
    private IdleTimer mouseTimer;
    private IdleTimer interactTimer;
    private boolean isIdle;
    private boolean played;
    private boolean chestOpen;
    private int idleTimeSeconds;

    private MouseIdle mouseIdle;

    public IdleChecker() {
        xpTimer = new IdleTimer();
        inputTimer = new IdleTimer();
        mouseTimer = new IdleTimer();
        interactTimer = new IdleTimer();
        isIdle = false;
        idleTimeSeconds = 5;
        played = false;
        chestOpen = false;

        mouseIdle = new MouseIdle();
    }

    public boolean isIdle() {
        checkIdle();
        return isIdle;
    }

    public void setIsIdle(boolean idle) {
        this.isIdle = idle;
    }

    private boolean checkIdle() {
        if (xpTimer.isIdle(idleTimeSeconds) &&inputTimer.isIdle(idleTimeSeconds) &&
                mouseTimer.isIdle(idleTimeSeconds) && interactTimer.isIdle(idleTimeSeconds))
            return true;
        return false;
    }

    public void build() {

        if (Minecraft.getInstance().currentScreen == null)
            chestOpen = false;
        if (!ModConfiguration.CLIENT.idleAlert.get())
            return;
        if (chestOpen)
            mouseTimer.startTimer();

        // Draw red transparent screen over window if idle
        if (checkIdle()) {
            Minecraft.getInstance().textureManager.bindTexture(new ResourceLocation(Main.ID, "textures/gui/redidle.png"));
            Tessellator tessellator = Tessellator.getInstance();
            GlStateManager.enableBlend();
            GlStateManager.enableTexture();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            int height = Minecraft.getInstance().getMainWindow().getScaledHeight();
            int width = Minecraft.getInstance().getMainWindow().getScaledWidth();


            bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferBuilder.pos(0, 0, 90.0D).tex(0.0F, 1.0F).endVertex();
            bufferBuilder.pos(0, height, 90.0D).tex(0.0F, 0.0F).endVertex();
            bufferBuilder.pos(width, height, 90.0D).tex(1.0F, 0.0F).endVertex();
            bufferBuilder.pos(width, 0, 90.0D).tex(1.0F, 1.0F).endVertex();
            tessellator.draw();

            if (!played)
                playIdleNoise();
        }else
            played = false;
    }

    public void playIdleNoise() {
        SoundEvent idleSound = new SoundEvent(new ResourceLocation(Main.ID, "inventory_full"));
        Minecraft.getInstance().player.playSound(idleSound, 2, 1);
        played = true;
    }

    public IdleTimer getXpTimer() { return xpTimer; }
    public IdleTimer getInputTimer() { return inputTimer; }
    public IdleTimer getMouseTimer() { return mouseTimer; }
    public IdleTimer getInteractTimer() { return interactTimer; }

    public MouseIdle getMouseIdle() { return mouseIdle; }

    public boolean getChestOpen() {return chestOpen; }
    public void setChestOpen(boolean chestOpen) { this.chestOpen = chestOpen; }
}
