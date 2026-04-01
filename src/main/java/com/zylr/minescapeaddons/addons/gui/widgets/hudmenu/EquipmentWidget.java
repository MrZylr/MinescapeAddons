package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.huds.resizableclassic.ResizableClassic;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class EquipmentWidget extends Widget {
    public final ResourceLocation INVENTORY = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/equipment.png");
    public final ResourceLocation FIXED_INVENTORY = ResourceLocation.fromNamespaceAndPath(MinescapeAddons.MOD_ID,
            "textures/gui/classic_equipment.png");
    public static final int[] EQUIPEMENT_SLOT_ORDER = new int[]{5,6,7,8,9,10,18,19,28,45,36};

    public EquipmentWidget(int x, int y) {
        this.anchorX = x;
        this.anchorY = y;

        this.widgetWidth = ResizableClassic.widgetWidth;
        this. widgetHeight = ResizableClassic.widgetHeight;
        this.isParent = true;

    }

    public EquipmentWidget(boolean visible) {
        this.visible = visible;
        this.isParent = false;
    }

    @Override
    public void render(GuiGraphics gui) {
        if (Config.getFixedMode())
            this.renderFixed(gui);
        else
            this.renderResizable(gui);
    }

    public void renderFixed(GuiGraphics gui) {
        this.anchorX = this.parent.getAnchorX();
        this.anchorY = this.parent.getAnchorY();
        this.widgetWidth = this.parent.getWidgetWidth();
        this.widgetHeight = this.parent.getWidgetHeight();

        if(visible) {
            super.render(gui);

            this.drawImage(FIXED_INVENTORY, gui);
            this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);

            renderEntityInInventoryFollowsMouse(gui, 0, 0, 250 + (anchorX * 2), 350 + (anchorY * 2),
                    80, 0F, (int) (this.anchorX + (this.widgetWidth / 1.75)), this.anchorY + 150, mc.player);
            this.drawItemsFixed(gui);
        }
    }

    public void renderResizable(GuiGraphics gui) {
        if(!Config.getSmallInventory()) {
            this.anchorX = this.parent.getAnchorX() + 13;
            this.anchorY = this.parent.getAnchorY() + 20;
            this.widgetWidth = this.parent.getWidgetWidth() - 26;
            this.widgetHeight = this.parent.getWidgetHeight() - 40;
        } else {
            this.anchorX = this.parent.getAnchorX() + 10;
            this.anchorY = this.parent.getAnchorY() + 16;
            this.widgetWidth = this.parent.getWidgetWidth() - 20;
            this.widgetHeight = this.parent.getWidgetHeight() - 32;
        }
        if(visible) {
            super.render(gui);

            float scale = this.parent.scale;
            gui.pose().pushPose();
            this.scaleAroundParentAnchor(gui, scale);
            this.drawImage(INVENTORY, gui);
            this.drawBackground(gui, MinescapeAddons.getInstance().resizableClassic.getHudMenu().backgroundColor);

            if (!Config.getSmallInventory()) {
                renderEntityInInventoryFollowsMouse(gui, 0, 0, 130 + (anchorX * 2), 190 + (anchorY * 2),
                        50, 0F, (int) (this.anchorX + (this.widgetWidth / 1.75)), this.anchorY + 100, mc.player);
                this.drawItemsLarge(gui);
            } else {
                renderEntityInInventoryFollowsMouse(gui, 0, 0, 105 + (anchorX * 2), 150 + (anchorY * 2),
                        35, 0F, (int) (this.anchorX + (this.widgetWidth / 1.75)), this.anchorY + 70, mc.player);
                this.drawItemsSmall(gui);
            }

            gui.pose().popPose();
        }

    }

    public void drawItemsFixed(GuiGraphics gui) {
        InventoryMenu inv = Minecraft.getInstance().player.inventoryMenu;
        int left = this.getLeftSide();
        int top  = this.getTop();
        new InventorySlot(5,  left + 119, top + 55).render(gui);
        new InventorySlot(6,  left + 45, top + 98).render(gui);
        new InventorySlot(7,  left + 45, top + 142).render(gui);
        new InventorySlot(8,  left + 191, top + 229).render(gui);
        new InventorySlot(9,  left + 74, top + 55).render(gui);
        new InventorySlot(10, left + 191, top + 98).render(gui);
        new InventorySlot(18, left + 45, top + 229).render(gui);
        new InventorySlot(19, left + 163, top + 55).render(gui);
        new InventorySlot(36, left + 45, top + 185).render(gui);
        if (inv.slots.size()-1 > 45) {
            new InventorySlot(45, left + 191, top + 142).render(gui);
        }
        new InventorySlot(28, left + 191, top + 185).render(gui);
    }

    public void drawItemsLarge(GuiGraphics gui) {
        InventoryMenu inv = Minecraft.getInstance().player.inventoryMenu;
        int left = this.getLeftSide();
        int top  = this.getTop();
        new InventorySlot(5,  left + 59, top + 12).render(gui);
        new InventorySlot(6,  left + 11, top + 40).render(gui);
        new InventorySlot(7,  left + 11, top + 69).render(gui);
        new InventorySlot(8,  left + 106, top + 124).render(gui);
        new InventorySlot(9,  left + 30, top + 12).render(gui);
        new InventorySlot(10, left + 106, top + 39).render(gui);
        new InventorySlot(18, left + 11, top + 123).render(gui);
        new InventorySlot(19, left + 88, top + 12).render(gui);
        new InventorySlot(36, left + 11, top + 98).render(gui);
        if (inv.slots.size()-1 > 45) {
            new InventorySlot(45, left + 191, top + 142).render(gui);
        }
        new InventorySlot(28, left + 106, top + 95).render(gui);
    }

    public void drawItemsSmall(GuiGraphics gui) {
        InventoryMenu inv = Minecraft.getInstance().player.inventoryMenu;
        int left = this.getLeftSide();
        int top  = this.getTop();
        new InventorySlot(5,  left + 44, top + 8).render(gui);
        new InventorySlot(6,  left + 7,  top + 28).render(gui);
        new InventorySlot(7,  left + 7,  top + 51).render(gui);
        new InventorySlot(8,  left + 83, top + 94).render(gui);
        new InventorySlot(9,  left + 22, top + 8).render(gui);
        new InventorySlot(10, left + 83, top + 28).render(gui);
        new InventorySlot(18, left + 7,  top + 94).render(gui);
        new InventorySlot(19, left + 67, top + 8).render(gui);
        new InventorySlot(36, left + 7,  top + 72).render(gui);
        if (inv.slots.size()-1 > 45) {
            new InventorySlot(45, left + 191, top + 142).render(gui);
        }
        new InventorySlot(28, left + 83, top + 72).render(gui);
    }




    public static void renderEntityInInventoryFollowsMouse(
            GuiGraphics guiGraphics,
            int x1,
            int y1,
            int x2,
            int y2,
            int scale,
            float yOffset,
            float mouseX,
            float mouseY,
            LivingEntity entity
    ) {
        float f = (float)(x1 + x2) / 2.0F;
        float f1 = (float)(y1 + y2) / 2.0F;
        float f2 = (float)Math.atan((double)((f - mouseX) / 40.0F));
        float f3 = (float)Math.atan((double)((f1 - mouseY) / 40.0F));
        // Forge: Allow passing in direct angle components instead of mouse position
        renderEntityInInventoryFollowsAngle(guiGraphics, x1, y1, x2, y2, scale, yOffset, f2, f3, entity);
    }

    public static void renderEntityInInventoryFollowsAngle(
            GuiGraphics p_282802_,
            int p_275688_,
            int p_275245_,
            int p_275535_,
            int p_294406_,
            int p_294663_,
            float p_275604_,
            float angleXComponent,
            float angleYComponent,
            LivingEntity p_275689_
    ) {
        float f = (float)(p_275688_ + p_275535_) / 2.0F;
        float f1 = (float)(p_275245_ + p_294406_) / 2.0F;
        p_282802_.enableScissor(p_275688_, p_275245_, p_275535_, p_294406_);
        float f2 = angleXComponent;
        float f3 = angleYComponent;
        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf1 = new Quaternionf().rotateX(f3 * 20.0F * (float) (Math.PI / 180.0));
        quaternionf.mul(quaternionf1);
        float f4 = p_275689_.yBodyRot;
        float f5 = p_275689_.getYRot();
        float f6 = p_275689_.getXRot();
        float f7 = p_275689_.yHeadRotO;
        float f8 = p_275689_.yHeadRot;
        p_275689_.yBodyRot = 180.0F + f2 * 20.0F;
        p_275689_.setYRot(180.0F + f2 * 40.0F);
        p_275689_.setXRot(-f3 * 20.0F);
        p_275689_.yHeadRot = p_275689_.getYRot();
        p_275689_.yHeadRotO = p_275689_.getYRot();
        float f9 = p_275689_.getScale();
        Vector3f vector3f = new Vector3f(0.0F, p_275689_.getBbHeight() / 2.0F + p_275604_ * f9, 0.0F);
        float f10 = (float)p_294663_ / f9;
        renderEntityInInventory(p_282802_, f, f1, f10, vector3f, quaternionf, quaternionf1, p_275689_);
        p_275689_.yBodyRot = f4;
        p_275689_.setYRot(f5);
        p_275689_.setXRot(f6);
        p_275689_.yHeadRotO = f7;
        p_275689_.yHeadRot = f8;
        p_282802_.disableScissor();
    }

    public static void renderEntityInInventory(
            GuiGraphics guiGraphics,
            float x,
            float y,
            float scale,
            Vector3f translate,
            Quaternionf pose,
            @Nullable Quaternionf cameraOrientation,
            LivingEntity entity
    ) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((double)x, (double)y, 50.0);
        guiGraphics.pose().scale(scale, scale, -scale);
        guiGraphics.pose().translate(translate.x, translate.y, translate.z);
        guiGraphics.pose().mulPose(pose);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (cameraOrientation != null) {
            entityrenderdispatcher.overrideCameraOrientation(cameraOrientation.conjugate(new Quaternionf()).rotateY((float) Math.PI));
        }

        entityrenderdispatcher.setRenderShadow(false);
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880));
        guiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        guiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
    }

    @Override
    public ResourceLocation getHighlight() { return HudMenuWidget.STONE_MIDDLE_SELECTED; }
}
