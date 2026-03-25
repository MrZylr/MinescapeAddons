package com.zylr.minescapeaddons.addons.skills.tracker;


import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.skills.Skill;
import com.zylr.minescapeaddons.addons.skills.SkillType;

import java.text.NumberFormat;

public class XpTracker {

    private double xp;
    private double skillXp;
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
        return NumberFormat.getIntegerInstance().format(hourlyXp);
    }
    public String getTotalXp() {
        return NumberFormat.getIntegerInstance().format(xp);
    }
    public String getSessionTime() {
        return String.format("%02d", xpTimer.getHours()) + ":" +
                String.format("%02d", xpTimer.getMinutes()) + ":" + String.format("%02d", xpTimer.getSeconds());
    }
    public Skill getSkill() {
        return skill;
    }

    public void startTracker() {
        xpTimer.startTimer();
    }

    public void setXp(double xp) {
        if (xpTimer.isPaused())
            return;
        //System.out.println(this.xp + " : " + xp);
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

    public static void pauseAllTrackers() {
        for (SkillType skillType : SkillType.values()) {
            Skill skill = MinescapeAddons.skills.get(skillType);
            if (!skill.getTracker().getTimer().isPaused() && skill.getTracker().getTimer().hasStarted()) {
                skill.getTracker().getTimer().pause();
                System.out.println("Paused tracker for skill: " + skill.getType().name());
            }
        }
    }

    public static void unpauseAllTrackers() {
        for (SkillType skillType : SkillType.values()) {
            Skill skill = MinescapeAddons.skills.get(skillType);
            if (skill.getTracker().getTimer().isPaused() && skill.getTracker().getTimer().hasStarted()) {
                skill.getTracker().getTimer().unpause();
                System.out.println("Unpaused tracker for skill: " + skill.getType().name());
            }
        }
    }
}
