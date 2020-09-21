package com.zylr.minescapeaddons.addons.skills.farming;

import com.zylr.minescapeaddons.addons.util.Timer;

public class FarmingTimer extends Timer {

    private SeedType seed;
    private String timeLeft;
    private boolean completed;
    private long growthInMilli;
    private int stage;
    private int oldStage;
    public boolean alertChecked;
    // TODO:: Favoritable patch shown on HUD

    public FarmingTimer(SeedType seed) {
        super();
        this.alertChecked = true;
        this.completed = false;
        this. growthInMilli = 0;
        this.stage = 0;

        if (seed != null) {
            this.seed = seed;
            this.startTimer();
        } else
            this.timeLeft = "--:--:--";
    }

    public FarmingTimer(Long startTime, SeedType seed) {
        super(startTime, 0);
        this.seed = seed;
    }

    public String timeLeft() {
        if (seed != null) {
            int growthInMinutes = seed.growthTime;
            this.growthInMilli = (growthInMinutes*60)*1000;
            // System for tracking when completed
            if (getElapsedSeconds() < 0) {
                stage = seed.stages;
                completed = true;
            } else
                completed = false;

            // Calc the growth stage
            int growthInSeconds = growthInMinutes*60;
            double stagePercentage = (double)(growthInSeconds-getElapsedSeconds()) / (double)growthInSeconds;
            double stageProgress = seed.stages*stagePercentage;
            this.stage = (int)stageProgress;/*
            System.out.println(this.stage);
            int minutesPassed = (int)(growthInMinutes - (elapsedMinutes+ 1));
            double increments = (int)(growthInMinutes/seed.stages);
            this.stage = (int)(minutesPassed / increments);
            System.out.println(this.stage);*/
            // Stop stages from going too high
            if (stage > seed.stages || stage < 0)
                stage = seed.stages;

            // Create the timer output
            this.timeLeft = stage + "/" + seed.stages + " -- " + String.format("%02d", this.getHours()) + ":"
                    + String.format("%02d", this.getMinutes()) + ":" + String.format("%02d", this.getSeconds());

            // Completed timer output
            if (completed)
                timeLeft = stage + "/" + seed.stages + " -- " + "Completed";
        } else
            this.timeLeft = "0/0 -- --:--:--";

        return timeLeft;
    }

    public SeedType getSeed() {
        return seed;
    }

    public void setSeed(SeedType seed) {
        this.reset();
        this.seed = seed;
        this.startTimer();
    }

    public boolean isCompleted() { return completed; }

    public boolean isNewStage() {
        if (stage > oldStage && seed != null) {
            alertChecked = false;
            oldStage = stage;
            return true;
        }
        return false;
    }

    @Override
    protected long getElapsedSeconds() {
        return (growthInMilli- elapsedTime) / 1000;
    }

    @Override
    public void reset() {
        super.reset();
        this.oldStage = 0;
        this.completed = false;
        this. growthInMilli = 0;
        this.seed = null;
        this.timeLeft = "--:--:--";
    }
}
