package com.zylr.minescapeaddons.addons.gui.builders;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.ArrayList;
import java.util.List;

public class BuilderUtils {
    public static void rectWithText(String text, int left, int top, int right, int bottom, int initialColor, int endColor, boolean small) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;

        font.drawString(text, left + 5, top - ((top-bottom)/2), 16777215);

        drawRect(left, top, right, bottom, initialColor, endColor, small);
    }

    public static void rectangle(double originX, double originY, double width, double height, ResourceLocation resource, Boolean small) {
        int screenWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int screenHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
        // Setup for building
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        // Scale the gui
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        Minecraft.getInstance().textureManager.bindTexture(resource);

        // Create corners
        List<Double> corners = new ArrayList<>();
        corners.add(originX);           corners.add(originY);
        corners.add(originX);           corners.add(originY-height);
        corners.add(originX-width);     corners.add(originY-height);
        corners.add(originX-width);     corners.add(originY);
        // Check if small
        if(small) {
            // Small
            for (int i = 0; i < corners.size(); i++) {
                corners.set(i, corners.get(i)*.75);
            }
        }

        //RenderSystem.enableBlend();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        // Build rectangle
        bufferBuilder.pos(screenWidth - corners.get(0), screenHeight - corners.get(1), -90.0D).tex(0.0F, 1.0F).endVertex();
        bufferBuilder.pos(screenWidth - corners.get(2), screenHeight - corners.get(3), -90.0D).tex(0.0F, 0.0F).endVertex();
        bufferBuilder.pos(screenWidth - corners.get(4), screenHeight - corners.get(5), -90.0D).tex(1.0F, 0.0F).endVertex();
        bufferBuilder.pos(screenWidth - corners.get(6), screenHeight - corners.get(7), -90.0D).tex(1.0F, 1.0F).endVertex();
        tessellator.draw();

        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }
    public static void scaledRect(double originX, double originY, double width, double height, ResourceLocation resource, Boolean small) {
        // Get gui scale
        float posScale = (float)Minecraft.getInstance().getMainWindow().getGuiScaleFactor() / 2;
        int screenWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int screenHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
        // Setup for building
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        // Scale the gui
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        // Set resource
        Minecraft.getInstance().textureManager.bindTexture(resource);

        // Create corners
        List<Double> corners = new ArrayList<>();
        corners.add(originX);           corners.add(originY);
        corners.add(originX);           corners.add(originY-height);
        corners.add(originX-width);     corners.add(originY-height);
        corners.add(originX-width);     corners.add(originY);
        // Check if small
        if(small) {
            // Small
            for (int i = 0; i < corners.size(); i++) {
                corners.set(i, corners.get(i)*.75);
            }
        }

        //RenderSystem.enableBlend();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        // Build rectangle
        bufferBuilder.pos(screenWidth*posScale - corners.get(0), screenHeight*posScale - corners.get(1), -90.0D).tex(0.0F, 1.0F).endVertex();
        bufferBuilder.pos(screenWidth*posScale - corners.get(2), screenHeight*posScale - corners.get(3), -90.0D).tex(0.0F, 0.0F).endVertex();
        bufferBuilder.pos(screenWidth*posScale - corners.get(4), screenHeight*posScale - corners.get(5), -90.0D).tex(1.0F, 0.0F).endVertex();
        bufferBuilder.pos(screenWidth*posScale - corners.get(6), screenHeight*posScale - corners.get(7), -90.0D).tex(1.0F, 1.0F).endVertex();
        tessellator.draw();

        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawRect(int left, int top, int width, int height, int initialColor, int endColor, boolean small) {
        int screenWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int screenHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();

        // Scale the gui
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();

        // Create corners
        List<Integer> data = new ArrayList<>();
        data.add(left);
        data.add(top);
        data.add(width);
        data.add(height);
        // Check if small
        if(small) {
            // Small
            for (int i = 0; i < data.size(); i++) {
                data.set(i, (int)((double)data.get(i)*.75));
            }
        }
        GuiUtils.drawGradientRect(0, (int)(screenWidth) - data.get(0), (int)(screenHeight) - data.get(1),
                (int)((screenWidth) - data.get(0)) + data.get(2), (int)((screenHeight)-data.get(1) + data.get(3)),
                initialColor, endColor);

        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }

    public static void drawScaleRect(int left, int top, int right, int bottom, int initialColor, int endColor, boolean small) {
        // Get gui scale
        float posScale = (float)Minecraft.getInstance().getMainWindow().getGuiScaleFactor() / 2;
        int screenWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        int screenHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();

        // Scale the gui
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();

        // Create corners
        List<Integer> data = new ArrayList<>();
        data.add(left);
        data.add(top);
        data.add(right);
        data.add(bottom);
        // Check if small
        if(small) {
            // Small
            for (int i = 0; i < data.size(); i++) {
                data.set(i, (int)((double)data.get(i)*.75));
            }
        }
        GuiUtils.drawGradientRect(0, (int)(screenWidth*posScale) - data.get(0), (int)(screenHeight*posScale) - data.get(1),
                (int)((screenWidth*posScale) - data.get(0)) + data.get(2), (int)((screenHeight*posScale)-data.get(1) + data.get(3)),
                initialColor, endColor);

        RenderSystem.disableBlend();
        RenderSystem.popMatrix();
    }
}
