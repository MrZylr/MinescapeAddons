package com.zylr.minescapeaddons.addons.skills.tracker;


import com.zylr.minescapeaddons.addons.utils.Timer;

public class XpTimer extends Timer {

    private double hourlyRatio;

    public XpTimer() {
        super();
    }

    public double getHourlyRatio() {
        return 3600/(double)this.elapsedSeconds;
    }
}
