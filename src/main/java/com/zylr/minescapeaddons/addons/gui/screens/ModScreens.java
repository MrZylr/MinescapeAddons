package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.widgets.BrowserWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ModScreens extends Screen {
    protected Minecraft mc = Minecraft.getInstance();
    protected List<IWidget> widgets;

    protected ModScreens(Component title) {
        super(title);

        this.widgets = MinescapeAddons.getInstance().activeHud.widgets;
    }

    @Override
    protected void init() {
        super.init();
    }

    public void renderWidgets(GuiGraphics gui) {
        for (IWidget widget : this.widgets) {
            if (widget != null) {
                if (widget instanceof BrowserWidget browser) {
                    browser.render(gui);
                } else
                    widget.render(gui);
            }
        }
    }

    public List<IWidget> getWidgets() { return widgets; }

}
