package mini_supermarket.utils;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date implements Serializable {
    public static final Date MIN = new Date();
    public static final Date MAX = new Date();

    static {
        MIN.date = LocalDate.of(1000, 1, 1);
        MAX.date = LocalDate.of(9999, 12, 31);
    }

    public LocalDate date;

    public Date() {
        this.date = LocalDate.now();
    }

    public Date(int y, int m, int d) {
        if (!isValidDate(y, m, d))
            throw new IllegalArgumentException("Invalid date");
        this.date = LocalDate.of(y, m, d);
    }

    public Date(LocalDate localDate) {
        if (!isValidDate(localDate))
            throw new IllegalArgumentException("Invalid date");
        this.date = localDate;
    }

    public Date(Date date) {
        this.date = date.date;
    }

    public static Date now() {
        return new Date();
    }

    public static Date of(int y, int m, int d) {
        return new Date(y, m, d);
    }

    public static Date of(LocalDate localDate) {
        return new Date(localDate);
    }

    public static Date of(Date date) {
        return new Date(date);
    }

    public static boolean isLeapYear(int year) {
        if (!isValidDate(year, 1, 1))
            return false;
        return LocalDate.of(year, 1, 1).isLeapYear();
    }

    public static boolean isValidDate(LocalDate localDate) {
        return !localDate.isBefore(MIN.date) && !localDate.isAfter(MAX.date);
    }

    public static boolean isValidDate(int y, int m, int d) {
        try {
            return isValidDate(LocalDate.of(y, m, d));
        } catch (Exception e) {
            return false;
        }
    }

    public static Date parse(String text) {
        try {
            LocalDate localDate = LocalDate.parse(text, DateTime.dateTimeFormatter);
            if (!isValidDate(localDate))
                throw new IllegalArgumentException("Invalid date");
            return new Date(localDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    public static long calculateDays(Date date1, Date date2) {
        Duration duration = Duration.between(date1.date, date2.date);
        return duration.toDays();
    }

    public int getYear() {
        return this.date.getYear();
    }

    public void setYear(int year) {
        this.date = this.date.withYear(year);
    }

    public int getMonth() {
        return this.date.getMonthValue();
    }

    public void setMonth(int month) {
        this.date = this.date.withMonth(month);
    }

    public int getDate() {
        return this.date.getDayOfMonth();
    }

    public void setDate(int date) {
        this.date = this.date.withDayOfMonth(date);
    }

    public boolean isAfter(Date date) {
        return this.date.isAfter(date.date);
    }

    public boolean isBefore(Date date) {
        return this.date.isBefore(date.date);
    }

    public boolean isEqual(Date date) {
        return this.date.isEqual(date.date);
    }

    public Date plus(long y, long m, long d) {
        return new Date(date.plusYears(y)
            .plusMonths(m)
            .plusDays(d));
    }

    public Date plusYears(long yearsToAdd) {
        return new Date(date.plusYears(yearsToAdd));
    }

    public Date plusMonths(long monthsToAdd) {
        return new Date(date.plusMonths(monthsToAdd));
    }

    public Date plusDays(long daysToAdd) {
        return new Date(date.plusDays(daysToAdd));
    }

    public Date minus(long y, long m, long d) {
        return new Date(date.minusYears(y)
            .minusMonths(m)
            .minusDays(d));
    }

    public Date minusYears(long yearsToSubtract) {
        return new Date(date.minusYears(yearsToSubtract));
    }

    public Date minusMonths(long monthsToSubtract) {
        return new Date(date.minusMonths(monthsToSubtract));
    }

    public Date minusDays(long daysToSubtract) {
        return new Date(date.minusDays(daysToSubtract));
    }

    public String toString(DateTimeFormatter dateTimeFormatter) {
        return date.format(dateTimeFormatter);
    }

    public String toString(String pattern) {
        return toString(DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public String toString() {
        return toString("dd/MM/yyyy");
    }
}
