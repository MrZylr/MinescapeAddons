package com.zylr.client;

import com.zylr.MinescapeAddon;

import java.util.LinkedHashMap;
import java.util.Map;

public final class PerfDebug {
    private static final long REPORT_INTERVAL_NANOS = 5_000_000_000L;
    private static final Map<String, Stat> STATS = new LinkedHashMap<>();
    private static boolean enabled;
    private static long lastReportNanos = System.nanoTime();

    private PerfDebug() {
    }

    public static void setEnabled(boolean value) {
        enabled = value;
    }

    public static long start() {
        return enabled ? System.nanoTime() : 0L;
    }

    public static void record(String name, long startNanos) {
        if (!enabled || startNanos == 0L) return;
        long elapsed = System.nanoTime() - startNanos;
        Stat stat = STATS.computeIfAbsent(name, ignored -> new Stat());
        stat.count++;
        stat.totalNanos += elapsed;
        if (elapsed > stat.maxNanos) stat.maxNanos = elapsed;
        maybeReport();
    }

    public static void count(String name) {
        if (!enabled) return;
        Stat stat = STATS.computeIfAbsent(name, ignored -> new Stat());
        stat.count++;
        maybeReport();
    }

    private static void maybeReport() {
        long now = System.nanoTime();
        if (now - lastReportNanos < REPORT_INTERVAL_NANOS) return;
        lastReportNanos = now;
        if (STATS.isEmpty()) return;

        StringBuilder message = new StringBuilder("PerfDebug");
        for (Map.Entry<String, Stat> entry : STATS.entrySet()) {
            Stat stat = entry.getValue();
            double totalMs = stat.totalNanos / 1_000_000.0D;
            double avgMs = stat.count > 0 ? totalMs / stat.count : 0.0D;
            double maxMs = stat.maxNanos / 1_000_000.0D;
            message.append(" | ")
                .append(entry.getKey())
                .append(": count=").append(stat.count)
                .append(", totalMs=").append(String.format(java.util.Locale.US, "%.3f", totalMs))
                .append(", avgMs=").append(String.format(java.util.Locale.US, "%.3f", avgMs))
                .append(", maxMs=").append(String.format(java.util.Locale.US, "%.3f", maxMs));
        }
        MinescapeAddon.LOGGER.info(message.toString());
        STATS.clear();
    }

    private static final class Stat {
        long count;
        long totalNanos;
        long maxNanos;
    }
}
