package com.zylr.minescapeaddons.addons.gui.screens;

import com.zylr.minescapeaddons.addons.gui.hud.Hud;
import com.zylr.minescapeaddons.addons.gui.widgets.IWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ModScreen extends Screen {

    protected Minecraft mc = Minecraft.getInstance();
    protected List<IWidget> widgets;

    public ModScreen(ITextComponent titleIn) {
        super(titleIn);

        widgets = new ArrayList<>();
    }

    @Override
    protected void init() {
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

    public void widgetsFromHud(Hud hud) {
        this.widgets = hud.getWidgets();
    }

    public List<IWidget> getWidgets() { return this.widgets; }
}
