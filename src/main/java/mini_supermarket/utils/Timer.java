package mini_supermarket.utils;

import java.util.concurrent.TimeUnit;

public class Timer {
    public static long executionTimeOf(Runnable code, TimeUnit timeUnit) {
        DateTime startTime = DateTime.now();
        code.run();
        DateTime endTime = DateTime.now();
        return DateTime.calculateTime(startTime, endTime, timeUnit);
    }
}
