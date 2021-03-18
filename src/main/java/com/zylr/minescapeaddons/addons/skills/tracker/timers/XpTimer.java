package com.zylr.minescapeaddons.addons.skills.tracker.timers;


import com.zylr.minescapeaddons.addons.util.Timer;

public class XpTimer extends Timer {

    private double hourlyRatio;

    public XpTimer() {
        super();
    }

    public double getHourlyRatio() {
        return 3600/(double)this.elapsedSeconds;
    }
}
