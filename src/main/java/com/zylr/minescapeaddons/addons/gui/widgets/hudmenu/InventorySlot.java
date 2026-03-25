package com.zylr.minescapeaddons.addons.gui.widgets.hudmenu;

import com.zylr.minescapeaddons.addons.Config;
import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.overrides.ZylrInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.awt.*;
import java.util.List;

public class InventorySlot {
    private final Minecraft mc = Minecraft.getInstance();
    private final ItemStack itemStack;
    private final int slotIndex;
    private final int x;
    private final int y;
    private int stackSize;

    public InventorySlot(int slotIndex, int x, int y) {
        this.slotIndex = slotIndex;
        this.x = x;
        this.y = y;
        this.itemStack = mc.player.containerMenu.getSlot(this.slotIndex).getItem();
        List<Component> toolTipLines = this.itemStack.getTooltipLines(Item.TooltipContext.EMPTY, mc.player, TooltipFlag.NORMAL);
        if (toolTipLines.size() > 1) {
            try {
                this.stackSize = Integer.parseInt(toolTipLines.get(1).getString().substring(1));
            }catch(Exception e) {
                this.stackSize = 0;
            }
        }else {
            this.stackSize = 0;
        }
    }

    public void render(GuiGraphics gui) {
        if (mc.player == null) return;
        if (mc.screen instanceof ContainerScreen) return;

        if (Config.getFixedMode()) {
            Font font = Minecraft.getInstance().font;
            if (Config.getFixedMode() && Config.getSmallInventory())
                font = MinescapeAddons.getInstance().getVanillaFont();
            float fixedScale = 2.0f;
            float centerX = x + 8;
            float centerY = y + 8;
            gui.pose().pushPose();
            gui.pose().translate(centerX, centerY, 0);
            gui.pose().scale(fixedScale, fixedScale, 1.0f);
            gui.pose().translate(-centerX, -centerY, 0);
            if (!(mc.screen instanceof ZylrInventoryScreen))
                gui.renderFakeItem(itemStack, x, y, slotIndex);
            gui.pose().popPose();
            gui.pose().pushPose();//
            gui.pose().translate(0, 0, 200);
            if (this.stackSize > 1) {
                Component number = getItemStackSizeColor(itemStack);
                if (Config.getFixedMode()) {
                    gui.drawString(font, number, x-5, y-5,
                            number.getStyle().getColor() != null ? number.getStyle().getColor().getValue() : 0xFFFFFF);
                } else {
                    gui.drawString(font, number, x, y,
                            number.getStyle().getColor() != null ? number.getStyle().getColor().getValue() : 0xFFFFFF);
                }
            }
            gui.pose().popPose();
        } else {
            if (!(mc.screen instanceof ZylrInventoryScreen))
                gui.renderFakeItem(itemStack, x, y, slotIndex);
            gui.pose().pushPose();
            gui.pose().translate(0, 0, 200);
            if (this.stackSize > 1) {
                Component number = getItemStackSizeColor(itemStack);
                gui.drawString(mc.font, number, x, y,
                        number.getStyle().getColor() != null ? number.getStyle().getColor().getValue() : 0xFFFFFF);
            }
            gui.pose().popPose();
        }
    }

    private Component getItemStackSizeColor(ItemStack itemStack) {
        String number = this.stackSize+"";
        Color color = Color.YELLOW;
        if (this.stackSize > 1) {
            if (this.stackSize >= 100000) {
                number = this.stackSize/1000+"K";
                color = Color.WHITE;
            }
            if (this.stackSize >= 10000000){
                number = this.stackSize/1000000+"M";
                color = Color.GREEN;
            }
        }
        return Component.literal(number).withColor(color.getRGB());
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
