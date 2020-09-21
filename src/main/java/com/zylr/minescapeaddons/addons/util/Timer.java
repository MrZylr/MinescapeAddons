package com.zylr.minescapeaddons.addons.util;

public class Timer {

    protected long startTime;
    protected long elapsedTime;
    protected long elapsedSeconds;
    protected long secondsDisplay;
    protected long minutesDisplay;
    protected long elapsedMinutes;
    protected long elapsedHours;

    protected long pauseTime;
    protected boolean paused;
    protected long totalTimePaused;
    private boolean hasStarted;

    public Timer() {
        this.startTime = 0;
        this.paused = false;
        this.totalTimePaused = 0;
        this.hasStarted = false;
        updateTime();
    }

    public Timer(long startTime, long pausedTime) {
        this.startTime = startTime;
        this.paused = false;
        this.totalTimePaused = pausedTime;
        this.hasStarted = true;
        updateTime();
    }

    public void startTimer() {
        this.hasStarted = true;
        this.startTime = System.currentTimeMillis();
    }

    public void pause() {
        this.paused = true;
        pauseTime = System.currentTimeMillis();
    }

    public void unpause() {
        this.paused = false;
        pauseTime = 0;
    }

    public void reset() {
        this.startTime = 0;
        this.totalTimePaused = 0;
        this.elapsedTime = 0;
        this.elapsedSeconds = 0;
        this.secondsDisplay = 0;
        this.minutesDisplay = 0;
        this.elapsedMinutes = 0;
        this.elapsedHours = 0;
        this.hasStarted = false;
        this.paused = false;
    }

    private void updateTime() {
        this.elapsedTime = getElapsedTime();
        this.elapsedSeconds = getElapsedSeconds();
        this.elapsedMinutes = getElapsedMinutes();
        this.secondsDisplay = getSecondsDisplay();
        this.minutesDisplay = getMinutesDisplay();
        this.elapsedHours = getElapsedHours();
    }

    public boolean isPaused() {
        return paused;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getSeconds() {
        updateTime();
        return (int)secondsDisplay;
    }

    public int getMinutes() {
        updateTime();
        return (int)minutesDisplay;
    }

    public int getHours() {
        updateTime();
        return (int)elapsedHours;
    }

    public long getElapsedTime() {
        if (paused) {
            long currentTime = System.currentTimeMillis();
            totalTimePaused += currentTime - pauseTime;
            pauseTime = currentTime;
        }
        if (startTime == 0)
            return 0;
        return System.currentTimeMillis() - startTime - totalTimePaused;
    }

    private long getSecondsDisplay() { return elapsedSeconds % 60; }

    private long getMinutesDisplay() { return elapsedMinutes % 60; }

    protected long getElapsedSeconds() { return elapsedTime / 1000; }

    private long getElapsedMinutes() { return elapsedSeconds / 60; }

    private long getElapsedHours() {
        return  elapsedMinutes / 60;
    }

    public boolean hasStarted() {
        return this.hasStarted;
    }
}
