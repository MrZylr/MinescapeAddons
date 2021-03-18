package com.zylr.minescapeaddons.addons.idle;

public class IdleTimer {
    private long startTime;
    private long seconds;

    public IdleTimer() {
        startTime = 0;
        seconds = 0;
    }

    public void startTimer() {
        startTime = System.currentTimeMillis()/1000;
    }

    public int getSeconds() {
        seconds = (System.currentTimeMillis()/1000) - startTime;
        return (int)seconds;
    }

    public boolean isIdle(int idle) {
        seconds = (System.currentTimeMillis()/1000) - startTime;
        if (idle <= (int)seconds)
            return true;
        return false;
    }
}
