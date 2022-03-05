package com.zylr.minescapeaddons.addons.gui.hud;

import com.zylr.minescapeaddons.addons.gui.widgets.ChatWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.ScoreboardWidget;
import com.zylr.minescapeaddons.addons.gui.widgets.XpTrackerWidget;
import net.minecraft.client.Minecraft;

public class RSHud extends Hud {

    private Minecraft mc = Minecraft.getInstance();

    // RSHud will use Rs style inventory
    // Primary Hud used
    // TODO:: Setup a Method to remove child widget from main render
    public RSHud() {
        super();
        this.widgets.add(new XpTrackerWidget());
        this.widgets.add(new ScoreboardWidget());
        //this.widgets.add(new ChatWidget());
    }
}
