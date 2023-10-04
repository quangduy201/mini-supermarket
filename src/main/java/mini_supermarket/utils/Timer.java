package mini_supermarket.utils;

import java.util.concurrent.TimeUnit;

public class Timer {
    public static double executionTimeOf(Runnable code, TimeUnit timeUnit) {
        long startTime = System.nanoTime();
        code.run();
        long endTime = System.nanoTime();
        long elapsedNanos = endTime - startTime;

        return switch (timeUnit) {
            case NANOSECONDS -> elapsedNanos;
            case MICROSECONDS -> elapsedNanos / 1000.0;
            case MILLISECONDS -> elapsedNanos / 1_000_000.0;
            case SECONDS -> elapsedNanos / 1_000_000_000.0;
            case MINUTES -> elapsedNanos / (1_000_000_000.0 * 60);
            case HOURS -> elapsedNanos / (1_000_000_000.0 * 60 * 60);
            case DAYS -> elapsedNanos / (1_000_000_000.0 * 60 * 60 * 24);
        };
    }
}
