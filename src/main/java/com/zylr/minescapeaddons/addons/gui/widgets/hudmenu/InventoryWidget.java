package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class InventoryWidget extends Widget {

    private final Minecraft mc = Minecraft.getInstance();

    public final ResourceLocation INVENTORY = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/blank_inventory.png");
    public final ResourceLocation CLASSIC_INVENTORY = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/classic_inventory.png");
    public final int[] INVENTORY_SLOT_ORDER = new int[]{38, 39, 40, 41, 42, 43, 44, 11, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 25, 26, 29, 30, 31, 32, 33, 34, 35};

    public InventoryWidget(boolean visible) {
        this.visible = visible;
        this.isParent = false;
    }

    @Override
    public void render(GuiGraphics gui) {
        if (this.visible) {
            super.render(gui);

            if (Config.getFixedMode())
                this.renderFixed(gui);
            else
                this.renderResizable(gui);
        }
    }

    public void renderFixed(GuiGraphics gui) {
        if (this.visible) {
            super.render(gui);

            this.anchorX = this.parent.getAnchorX();
            this.anchorY = this.parent.getAnchorY();
            this.widgetWidth = this.parent.getWidgetWidth();
            this.widgetHeight = this.parent.getWidgetHeight();

            this.drawImage(CLASSIC_INVENTORY, gui);
            this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);

            if (mc.player == null) return;

            int startX = (int) (this.getLeftSide());
            int slotX = startX;
            int slotY = (int) (this.getTop() + 15);

            int col = 0;

            for (int i : INVENTORY_SLOT_ORDER) {
                if (col % 4 == 0) {
                    slotX = startX;
                    slotY += 38;
                }
                slotX += 48;
                col++;

                gui.pose().pushPose();
                RenderSystem.disableBlend();
                new InventorySlot(i, slotX, slotY).render(gui);
                gui.pose().popPose();
            }
        }
    }

    public void renderResizable(GuiGraphics gui) {
        if (this.visible) {
            if (!Config.getSmallInventory()) {
                this.anchorX = this.parent.getAnchorX() + 13;
                this.anchorY = this.parent.getAnchorY() + 20;
                this.widgetWidth = this.parent.getWidgetWidth() - 26;
                this.widgetHeight = this.parent.getWidgetHeight() - 40;
            } else {
                this.anchorX = this.parent.getAnchorX() + 10;
                this.anchorY = this.parent.getAnchorY() + 16;
                this.widgetWidth = this.parent.getWidgetWidth() - 15;
                this.widgetHeight = this.parent.getWidgetHeight() - 33;
            }
            super.render(gui);
            float scale = this.parent.scale;
            gui.pose().pushPose();
            this.scaleAroundParentAnchor(gui, scale);
            this.drawImage(INVENTORY, gui);
            this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);
            this.drawItems(gui);
            gui.pose().popPose();
        }
    }

    private void drawItems(GuiGraphics gui) {
        if (mc.player == null) return;

        if (Config.getSmallInventory())
            drawSmallInventory(gui);
        else
            drawLargeInventory(gui);
    }

    private void drawLargeInventory(GuiGraphics gui) {
        drawSlots(gui, 28, 24);
    }

    private void drawSmallInventory(GuiGraphics gui) {
        drawSlots(gui, 23, 19);
    }

    private void drawSlots(GuiGraphics gui, int slotGapX, int slotGapY) {
        if (mc.player == null) return;

        float scale = this.parent.scale;

        int startX = (int) (this.getLeftSide() - 12);
        int slotX = startX;
        int slotY = (int) (this.getTop() - 12);

        int col = 0;

        for (int i : INVENTORY_SLOT_ORDER) {
            if (col % 4 == 0) {
                slotX = startX;
                slotY += slotGapY;
            }
            slotX += slotGapX;
            col++;

            gui.pose().pushPose();
            RenderSystem.disableBlend();
            new InventorySlot(i, slotX, slotY).render(gui);
            gui.pose().popPose();
        }
    }

    @Override
    public ResourceLocation getHighlight() {
        return HudMenuWidget.STONE_MIDDLE_SELECTED;
    }
}
