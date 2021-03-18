package com.zylr.minescapeaddons.addons.gui.widgets.screens;

import com.zylr.minescapeaddons.addons.gui.hud.Hud;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.InventoryWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class MouseControlScreen extends InventoryScreen {
    protected Minecraft mc = Minecraft.getInstance();
    protected List<IWidget> widgets;


    public MouseControlScreen(Hud hud, ClientPlayerEntity player) {
        super(player);

        this.widgetsFromHud(hud);

    }

    @Override
    public void init() {
        super.init();
        renderWidgets();
    }

    public void renderWidgets() {
        for (IWidget widget : widgets) {
            widget.render();
            this.renderButtons(widget.getButtons());
        }
    }

    public void renderButtons(List<Button> widgetButtons) {
        this.buttons.clear();
        for (Button widgetButton : widgetButtons) {
            this.addButton(widgetButton);
        }
    }

    @Override
    public void tick() {
        renderWidgets();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) {
        super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);

        for (IWidget widget : widgets) {
            if (widget instanceof InventoryWidget) {
                InventoryWidget inv = (InventoryWidget) widget;

                if (inv.isHovered()) {
                    System.out.println("Clicked");
                    mc.player.container.slotClick(mc.player.container.inventorySlots.get(10).getSlotIndex(),
                            1, ClickType.PICKUP, mc.player);
                }
            }
        }
        return true;
    }

    public void widgetsFromHud(Hud hud) {
        this.widgets = hud.getWidgets();
    }

    public List<IWidget> getWidgets() { return this.widgets; }
}
