package com.zylr.minescapeaddons.addons.gui.builders;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import xaero.common.XaeroMinimapSession;
import xaero.common.core.IXaeroMinimapClientPlayNetHandler;
import xaero.common.interfaces.InterfaceInstance;
import xaero.common.minimap.MinimapProcessor;
import xaero.minimap.XaeroMinimap;

public class MinimapOverlayBuilder {
    public final ResourceLocation MINIMAP_OVERLAY = new ResourceLocation(Main.ID, "textures/gui/minimap.png");
    public final ResourceLocation MINIMAP_LEFT_OVERLAY = new ResourceLocation(Main.ID, "textures/gui/minimap_left.png");
    private boolean right;
    private boolean bottom;
    private int x;
    private int y;
    private int width;
    private int height;
    double posScale;

    public MinimapOverlayBuilder() {
        right = false;
        bottom = false;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        posScale= 0;
    }
    public void build() {
        // Grab all Xaero information needed
        if (XaeroMinimap.instance.getSettings().minimapDisabled())
            return;
        XaeroMinimapSession minimapSession = ((IXaeroMinimapClientPlayNetHandler)Minecraft.getInstance().player.connection).getXaero_minimapSession();
        MinimapProcessor minimap = minimapSession.getMinimapProcessor();
        double scale = Minecraft.getInstance().getMainWindow().getGuiScaleFactor();
        InterfaceInstance interfaceInstance = minimapSession.getInterfaceInstances().get(XaeroMinimap.instance.getInterfaces().getMinimapInterface());

        int mapSize = minimapSession.getMinimapProcessor().getMinimapSize();
        int bufferSize = minimapSession.getMinimapProcessor().getMinimapBufferSize();
        float sizeFix = (float)bufferSize / 512.0F;
        float mapScale = (float)(scale / 2.0D / (double)XaeroMinimap.instance.getSettings().getMinimapScale());
        int minimapFrameSize = (int)((float)(mapSize / 2) / sizeFix);
        int scaledX = (int)((float)x * mapScale);
        int scaledY = (int)((float)y * mapScale);


        int frameSize = mapSize / (int)(2/XaeroMinimap.instance.getSettings().getMinimapScale());

        right = XaeroMinimap.instance.getInterfaces().getMinimapInterface().isFromRight();
        bottom = XaeroMinimap.instance.getInterfaces().getMinimapInterface().isFromBottom();
        x = XaeroMinimap.instance.getInterfaces().getMinimapInterface().getX();
        y = XaeroMinimap.instance.getInterfaces().getMinimapInterface().getY();

        // Grab scaled screen resolution
        width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        height = Minecraft.getInstance().getMainWindow().getScaledHeight();

        // Adjustment for position scaling
        posScale = Minecraft.getInstance().getMainWindow().getGuiScaleFactor() / 2;


        // Create overlay origin point
        int originX = 0;
        int originY = 0;

        // Determine which corner it is in
        if (right)
            originX = x;
        else
            originX = width - (int)(x/XaeroMinimap.instance.getSettings().getMinimapScale());
        if (bottom)
            originY = y;
        else
            originY = height - (int)(y/XaeroMinimap.instance.getSettings().getMinimapScale());
        if (XaeroMinimap.instance.getSettings().getMinimapScale() == 2) {
            if (right)
                originX = (int)(width/XaeroMinimap.instance.getSettings().getMinimapScale()) + (int)(x/XaeroMinimap.instance.getSettings().getMinimapScale());
            if (bottom)
                originY = (int)(height/XaeroMinimap.instance.getSettings().getMinimapScale()) + (int)(y/XaeroMinimap.instance.getSettings().getMinimapScale());
        }

        // Scale
        originX *= posScale;
        originY *= posScale;

        // X position for xp tracker
        int trackerOriginX = originX;
        int xpOriginX = originX;
        int size = 16;

        // Adjust for minimap size
        double frameHeight = 0;
        double frameWidth = 0;
        switch (XaeroMinimap.instance.getSettings().getMinimapSize()) {
            // tiny= 0
            // small = 1
            // medium = 2 = 128
            // large = 3

            case 0:
                originX += 15;
                originY -= 5;
                frameWidth = 85;
                frameHeight = 65;
                trackerOriginX += 29;
                xpOriginX +=10;
                size = 11;
                if (!right) {
                    originX -= 20;
                    trackerOriginX -= 133;
                    xpOriginX -= 84;
                }
                break;
            case 1:
                originX += 25;
                originY -= 4;
                frameWidth = 124;
                frameHeight = 95;
                trackerOriginX += 36;
                xpOriginX +=17;
                size = 16;
                if (!right) {
                    originX -= 30;
                    trackerOriginX -= 175;
                    xpOriginX -=122;
                }
                break;
            case 2:
                originX += 36;
                originY -= 3+scale/2;
                frameWidth = 161;
                frameHeight = 121;
                trackerOriginX += 43;
                xpOriginX +=23;
                size = 20;
                if (!right) {
                    originX -= 41;
                    trackerOriginX -= 218;
                    xpOriginX -=158;
                }
                break;
            case 3:
                originX += 54;
                originY -= 5;
                frameWidth = 239;
                frameHeight = 180;
                trackerOriginX += 58;
                xpOriginX +=38;
                size = 31;
                if (!right) {
                    originX -= 57;
                    trackerOriginX -= 304;
                    xpOriginX -=233;
                }
                break;
        }

        RenderSystem.pushMatrix();
        GL11.glScalef(1.0F / mapScale, 1.0F / mapScale, 1.0F);
        // Draw
        if (XaeroMinimap.instance.getSettings().getMinimap()){
            if (ModConfiguration.CLIENT.renderMapOverlay.get()) {
                if (right) {
                    BuilderUtils.scaledRect(originX, originY,
                            frameWidth,
                            frameHeight, MINIMAP_OVERLAY, false);
                } else {
                    BuilderUtils.scaledRect(originX, originY,
                            frameWidth,
                            frameHeight, MINIMAP_LEFT_OVERLAY, false);
                }
            }
            // Move xp button
            Main.getInstance().getImageButtons().drawXpButton(xpOriginX, originY, size);
        }else {
            trackerOriginX = 10;
        }
        // Move xp tracker
        Main.getInstance().getXpTrackerBuilder().buildXp(trackerOriginX, originY - 5, right);

        RenderSystem.popMatrix();
    }
}
