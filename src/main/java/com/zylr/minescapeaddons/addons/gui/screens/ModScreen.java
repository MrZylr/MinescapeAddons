package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.gui.hud.Hud;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ModScreen extends Screen {

    protected Minecraft mc = Minecraft.getInstance();
    protected List<Widget> widgets;

    public ModScreen(ITextComponent titleIn) {
        super(titleIn);

        widgets = new ArrayList<>();
    }

    public void renderWidgets() {
        for (Widget widget : widgets) {
            widget.render();
        }
    }

    public void widgetsFromHud(Hud hud) {
        this.widgets = hud.getWidgets();
    }

    public List<Widget> getWidgets() { return this.widgets; }
}
