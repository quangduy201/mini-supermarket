package mini_supermarket.utils;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class DateTime implements Serializable {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
        "[yyyy[[/][-][MM][M][[/][-][dd][d][ [HH][H][:[mm][m][:[ss][s][.SSSSSS][.SS]]]]]]]" +
            "[[dd][d][[/][-][MM][M][[/][-]yyyy[ [HH][H][:[mm][m][:[ss][s][.SSSSSS][.SS]]]]]]]"
    );
    public static final DateTime MIN = new DateTime();
    public static final DateTime MAX = new DateTime();

    static {
        MIN.dateTime = LocalDateTime.of(1000, 1, 1, 0, 0, 0, 0);
        MAX.dateTime = LocalDateTime.of(9999, 12, 31, 23, 59, 59, 999999);
    }

    public LocalDateTime dateTime;

    public DateTime() {
        this.dateTime = LocalDateTime.now();
    }

    public DateTime(int y, int M, int d, int h, int m, int s, int ns) {
        if (!isValidDateTime(y, M, d, h, m, s, ns))
            throw new IllegalArgumentException("Invalid date and time");
        this.dateTime = LocalDateTime.of(y, M, d, h, m, s, ns);
    }

    public DateTime(int y, int M, int d, int h, int m, int s) {
        this(y, M, d, h, m, s, 0);
    }

    public DateTime(int y, int M, int d, int h, int m) {
        this(y, M, d, h, m, 0, 0);
    }

    public DateTime(LocalDateTime localDateTime) {
        if (!isValidDateTime(localDateTime))
            throw new IllegalArgumentException("Invalid date and time");
        this.dateTime = localDateTime;
    }

    public DateTime(DateTime dateTime) {
        this.dateTime = dateTime.dateTime;
    }

    public static DateTime now() {
        return new DateTime();
    }

    public static DateTime of(int y, int M, int d, int h, int m, int s, int ns) {
        return new DateTime(y, M, d, h, m, s, ns);
    }

    public static DateTime of(int y, int M, int d, int h, int m, int s) {
        return new DateTime(y, M, d, h, m, s, 0);
    }

    public static DateTime of(int y, int M, int d, int h, int m) {
        return new DateTime(y, M, d, h, m, 0, 0);
    }

    public static DateTime of(LocalDateTime localDateTime) {
        return new DateTime(localDateTime);
    }

    public static DateTime of(DateTime dateTime) {
        return new DateTime(dateTime);
    }

    public static boolean isLeapYear(int year) {
        return Date.isLeapYear(year);
    }

    public static boolean isValidDateTime(LocalDateTime localDateTime) {
        return !localDateTime.isBefore(MIN.dateTime) && !localDateTime.isAfter(MAX.dateTime);
    }

    public static boolean isValidDateTime(int y, int M, int d, int h, int m, int s, int ns) {
        try {
            return isValidDateTime(LocalDateTime.of(y, M, d, h, m, s, ns));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDateTime(int y, int M, int d, int h, int m, int s) {
        return isValidDateTime(y, M, d, h, m, s, 0);
    }

    public static boolean isValidDateTime(int y, int M, int d, int h, int m) {
        return isValidDateTime(y, M, d, h, m, 0, 0);
    }

    public static DateTime parse(String text) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(text, dateTimeFormatter);
            if (!isValidDateTime(localDateTime))
                throw new IllegalArgumentException("Invalid date and time");
            return new DateTime(localDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date and time");
        }
    }

    public static long calculateTime(DateTime dateTime1, DateTime dateTime2, TimeUnit timeUnit) {
        Duration duration = Duration.between(dateTime1.dateTime, dateTime2.dateTime);
        return switch (timeUnit) {
            case NANOSECONDS -> duration.toNanos();
            case MICROSECONDS -> duration.toNanos() / 1000;
            case MILLISECONDS -> duration.toMillis();
            case SECONDS -> duration.getSeconds();
            case MINUTES -> duration.toMinutes();
            case HOURS -> duration.toHours();
            case DAYS -> duration.toDays();
        };
    }

    public static long calculateTime(DateTime datetime1, DateTime dateTime2) {
        return calculateTime(datetime1, dateTime2, TimeUnit.SECONDS);
    }

    public int getYear() {
        return this.dateTime.getYear();
    }

    public void setYear(int year) {
        this.dateTime = this.dateTime.withYear(year);
    }

    public int getMonth() {
        return this.dateTime.getMonthValue();
    }

    public void setMonth(int month) {
        this.dateTime = this.dateTime.withMonth(month);
    }

    public int getDate() {
        return this.dateTime.getDayOfMonth();
    }

    public void setDate(int date) {
        this.dateTime = this.dateTime.withDayOfMonth(date);
    }

    public int getHour() {
        return this.dateTime.getHour();
    }

    public void setHour(int hour) {
        this.dateTime = this.dateTime.withHour(hour);
    }

    public int getMinute() {
        return this.dateTime.getMinute();
    }

    public void setMinute(int minute) {
        this.dateTime = this.dateTime.withMinute(minute);
    }

    public int getSecond() {
        return this.dateTime.getSecond();
    }

    public void setSecond(int second) {
        this.dateTime = this.dateTime.withSecond(second);
    }

    public int getNano() {
        return this.dateTime.getNano();
    }

    public void setNano(int nano) {
        this.dateTime = this.dateTime.withNano(nano);
    }

    public boolean isAfter(DateTime dateTime) {
        return this.dateTime.isAfter(dateTime.dateTime);
    }

    public boolean isBefore(DateTime dateTime) {
        return this.dateTime.isBefore(dateTime.dateTime);
    }

    public boolean isEqual(DateTime dateTime) {
        return this.dateTime.isEqual(dateTime.dateTime);
    }

    public DateTime plus(long y, long M, long d, long h, long m, long s, long ns) {
        return new DateTime(dateTime.plusYears(y)
            .plusMonths(M)
            .plusDays(d)
            .plusHours(h)
            .plusMinutes(m)
            .plusSeconds(s)
            .plusNanos(ns));
    }

    public DateTime plusYears(long yearsToAdd) {
        return new DateTime(dateTime.plusYears(yearsToAdd));
    }

    public DateTime plusMonths(long monthsToAdd) {
        return new DateTime(dateTime.plusMonths(monthsToAdd));
    }

    public DateTime plusDays(long daysToAdd) {
        return new DateTime(dateTime.plusDays(daysToAdd));
    }

    public DateTime plusHours(long hoursToAdd) {
        return new DateTime(dateTime.plusHours(hoursToAdd));
    }

    public DateTime plusMinutes(long minutesToAdd) {
        return new DateTime(dateTime.plusMinutes(minutesToAdd));
    }

    public DateTime plusSeconds(long secondsToAdd) {
        return new DateTime(dateTime.plusSeconds(secondsToAdd));
    }

    public DateTime plusNanos(long nanosToAdd) {
        return new DateTime(dateTime.plusNanos(nanosToAdd));
    }

    public DateTime minus(long y, long M, long d, long h, long m, long s, long ns) {
        return new DateTime(dateTime.minusYears(y)
            .minusMonths(M)
            .minusDays(d)
            .minusHours(h)
            .minusMinutes(m)
            .minusSeconds(s)
            .minusNanos(ns));
    }

    public DateTime minusYears(long yearsToAdd) {
        return new DateTime(dateTime.minusYears(yearsToAdd));
    }

    public DateTime minusMonths(long monthsToAdd) {
        return new DateTime(dateTime.minusMonths(monthsToAdd));
    }

    public DateTime minusDays(long daysToAdd) {
        return new DateTime(dateTime.minusDays(daysToAdd));
    }

    public DateTime minusHours(long hoursToAdd) {
        return new DateTime(dateTime.minusHours(hoursToAdd));
    }

    public DateTime minusMinutes(long minutesToAdd) {
        return new DateTime(dateTime.minusMinutes(minutesToAdd));
    }

    public DateTime minusSeconds(long secondsToAdd) {
        return new DateTime(dateTime.minusSeconds(secondsToAdd));
    }

    public DateTime minusNanos(long nanosToAdd) {
        return new DateTime(dateTime.minusNanos(nanosToAdd));
    }

    public String toString(DateTimeFormatter dateTimeFormatter) {
        return dateTime.format(dateTimeFormatter);
    }

    public String toString(String pattern) {
        return toString(DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public String toString() {
        return toString("dd/MM/yyyy HH:mm:ss.SSSSSS");
    }
}
