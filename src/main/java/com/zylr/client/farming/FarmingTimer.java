package com.zylr.client.farming;

public class FarmingTimer {
    private static final long MINUTE_MILLIS = 60_000L;

    public boolean alertChecked;
    private long startTime;
    private SeedType seed;

    public FarmingTimer(SeedType seed) {
        this(0L, seed);
    }

    public FarmingTimer(long startTime, SeedType seed) {
        this.startTime = startTime;
        this.seed = seed;
    }

    public void start(SeedType seed) {
        this.seed = seed;
        this.startTime = System.currentTimeMillis();
        this.alertChecked = false;
    }

    public void reset() {
        this.seed = null;
        this.startTime = 0L;
        this.alertChecked = false;
    }

    public boolean hasStarted() {
        return this.startTime > 0L && this.seed != null;
    }

    public boolean isCompleted() {
        return this.hasStarted() && this.getRemainingMillis() <= 0L;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public SeedType getSeed() {
        return this.seed;
    }

    public void setSeed(SeedType seed) {
        this.seed = seed;
        if (seed == null) {
            this.startTime = 0L;
            this.alertChecked = false;
        }
    }

    public long getTotalMillis() {
        return this.seed == null ? 0L : this.seed.growthTime * MINUTE_MILLIS;
    }

    public long getElapsedMillis() {
        return this.hasStarted() ? Math.max(0L, System.currentTimeMillis() - this.startTime) : 0L;
    }

    public long getRemainingMillis() {
        return Math.max(0L, this.getTotalMillis() - this.getElapsedMillis());
    }

    public int getCurrentStage() {
        if (!this.hasStarted()) return 0;
        if (this.isCompleted()) return this.seed.stages;

        long stageMillis = Math.max(1L, this.getTotalMillis() / Math.max(1, this.seed.stages));
        return Math.min(this.seed.stages, (int) (this.getElapsedMillis() / stageMillis) + 1);
    }

    public static String formatDuration(long millis) {
        long totalSeconds = Math.max(0L, millis / 1000L);
        long hours = totalSeconds / 3600L;
        long minutes = (totalSeconds % 3600L) / 60L;
        long seconds = totalSeconds % 60L;

        if (hours > 0L) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%d:%02d", minutes, seconds);
    }

    public String timeLeft() {
        if (!this.hasStarted()) {
            return null;
        }
        if (this.isCompleted()) {
            return "Done";
        }
        return formatDuration(this.getRemainingMillis());
    }
}
