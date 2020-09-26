package com.zylr.minescapeaddons.addons.gui.hud;

import com.zylr.minescapeaddons.addons.gui.screens.ModScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class Hud {
    // Hud gives access to the ability to convert widgets from hud gui elements to Screen elements
    // On the hud widgets cannot be clicked, while on a screen they can be hovered and clicked

    List<Widget> widgets;

    public Hud() {
        widgets = new ArrayList<>();
    }

    public void renderWidgets() {
        for (Widget widget : widgets) {
            widget.render();
        }
    }

    public void widgetsFromScreen(ModScreen screen) {
        this.widgets = screen.getWidgets();
    }

    public List<Widget> getWidgets() { return this.widgets; }
}
