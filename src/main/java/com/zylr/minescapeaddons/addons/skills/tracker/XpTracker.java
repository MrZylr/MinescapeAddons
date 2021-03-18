package com.zylr.minescapeaddons.addons.skills.tracker;

import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.tracker.timers.XpTimer;

import java.text.NumberFormat;

public class XpTracker {

    private int xp;
    private int skillXp;
    double hourlyXp;
    private XpTimer xpTimer;
    private Skill skill;

    public XpTracker(Skill skill) {
        this.xp = 0;
        this.hourlyXp = 0;
        this.skillXp = 0;
        this.xpTimer = new XpTimer();
        this.skill = skill;
    }

    public void calcXp() {
        if (xpTimer.getSeconds() >= 1) {
            hourlyXp = (double)xp * xpTimer.getHourlyRatio();
        }
    }

    public String getExpPerHour() {
        return skill.getName() + " exp/Hour: " + NumberFormat.getIntegerInstance().format(hourlyXp);
    }
    public String getTotalXp() {
        return skill.getName() + " exp Gained: " + NumberFormat.getIntegerInstance().format(xp);
    }
    public String getSessionTime() {
        return "Session Time: " + String.format("%02d", xpTimer.getHours()) + ":" +
                String.format("%02d", xpTimer.getMinutes()) + ":" + String.format("%02d", xpTimer.getSeconds());
    }
    public Skill getSkill() {
        return skill;
    }

    public void startTracker() {
        xpTimer.startTimer();
    }

    public void setXp(int xp) {
        if (xpTimer.isPaused())
            return;
        this.xp = xp + this.xp;
        if (xpTimer.getStartTime() == 0)
            xpTimer.startTimer();
    }

    public void resetTracker() {
        xp = 0;
        xpTimer.reset();
        hourlyXp = 0;
    }

    public XpTimer getTimer() {
        return xpTimer;
    }
}
