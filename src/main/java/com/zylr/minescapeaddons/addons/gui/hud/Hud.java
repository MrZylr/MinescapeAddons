package com.zylr.minescapeaddons.addons.gui.hud;

import com.zylr.minescapeaddons.addons.gui.widgets.screens.ModScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;

import java.util.ArrayList;
import java.util.List;

public class Hud {
    // Hud gives access to the ability to convert widgets from hud gui elements to Screen elements
    // On the hud widgets cannot be clicked, while on a screen they can be hovered and clicked

    List<IWidget> widgets;

    public Hud() {
        widgets = new ArrayList<>();
    }

    public void renderWidgets() {
        for (IWidget widget : widgets) {
            if (widget.isVisible())
                widget.render();
        }
    }

    public void widgetsFromScreen(ModScreen screen) {
        this.widgets = screen.getWidgets();
    }

    public List<IWidget> getWidgets() { return this.widgets; }
}
