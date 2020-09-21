package com.zylr.minescapeaddons.addons.gui.builders;

import com.zylr.minescapeaddons.addons.Main;
import com.zylr.minescapeaddons.addons.ModConfiguration;
import net.minecraft.util.ResourceLocation;

public class ThurgoBuilder {

    private boolean small;

    public ThurgoBuilder() {
        small = false;
    }

    public void build() {
        if (!ModConfiguration.CLIENT.thurgo.get())
            return;

        small = ModConfiguration.CLIENT.small.get();

        ResourceLocation resource = new ResourceLocation(Main.ID, "textures/gui/notsothiccthurgo.png");
        BuilderUtils.rectangle(215D, 101D, 50, 76, resource, small);

    }
}
