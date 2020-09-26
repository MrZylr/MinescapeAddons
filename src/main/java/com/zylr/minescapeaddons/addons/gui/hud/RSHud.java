package com.zylr.minescapeaddons.addons.gui.hud;

import com.zylr.minescapeaddons.addons.gui.widgets.XpTrackerWidget;

public class RSHud extends Hud {

    // RSHud will use Rs style inventory
    // Primary Hud used
    public RSHud() {
        super();
        this.widgets.add(new XpTrackerWidget());
    }
}
