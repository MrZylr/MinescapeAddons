package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.gui.builders.BuilderUtils;
import com.zylr.minescapeaddons.addons.gui.widgets.screens.HudEditScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ThurgoWidget extends Widget {
    Minecraft mc = Minecraft.getInstance();

    private boolean isUpsideDown;
    public static final ResourceLocation THURGO_RESOURCE = new ResourceLocation(Main.ID, "textures/gui/notsothiccthurgo.png");
    public static final ResourceLocation THURGO_RESOURCE_UPSIDEDOWN = new ResourceLocation(Main.ID, "textures/gui/notsothiccthurgo_upsidedown.png");

    public ThurgoWidget() {
        this.type = WidgetType.THURGOWIDGET;
        this.setupConfig();

        isUpsideDown = false;
        this.widgetWidth = 50;
        this.widgetHeight = 76;

        this.relativeAnchorX = Double.parseDouble(config.getProperty("x"));
        this.relativeAnchorY = Double.parseDouble(config.getProperty("y"));
        this.isUpsideDown = Boolean.parseBoolean(config.getProperty("upsideDown"));
    }

    @Override
    public void render() {
        super.render();

        if (mc.currentScreen instanceof HudEditScreen) {
            BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth() - this.getLeftSide(),
                    mc.getMainWindow().getScaledHeight() - this.getTop(), this.widgetWidth, this.widgetHeight, THURGO_RESOURCE, false);
            return;
        }

        if (!isUpsideDown) {
            BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth()-this.getLeftSide(),
                    mc.getMainWindow().getScaledHeight()-this.getTop(), this.widgetWidth, this.widgetHeight, THURGO_RESOURCE_UPSIDEDOWN, false);
        }
        else {
            double aspectRatio = (double)widgetWidth / (double)widgetHeight;
            int thurgoHeight = mc.getMainWindow().getScaledHeight()*5;
            int thurgoWidth = (int)(thurgoHeight*aspectRatio);
            BuilderUtils.rectangle(mc.getMainWindow().getScaledWidth()-((mc.getMainWindow().getScaledWidth()/2)-(thurgoWidth/2.5)),
                    mc.getMainWindow().getScaledHeight(), thurgoWidth, thurgoHeight, THURGO_RESOURCE, false);
        }

    }

    public void toggleUpsideDown() {
        if(isUpsideDown)
            isUpsideDown = false;
        else
            isUpsideDown = true;
        config.setProperty("upsideDown", Boolean.toString(isUpsideDown));
        saveConfig();
    }
}
