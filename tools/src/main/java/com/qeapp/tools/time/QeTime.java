package com.qeapp.tools.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class QeTime {

    public static String toString(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy d MMMM EEEE H:m:s", Locale.ENGLISH);
        return format.format(calendar.getTime());
    }

    public static Calendar createCalendar(long time) {
        return createCalendar(new Date(time));
    }
    public static Calendar createCalendar(Date date) {
        Calendar calendar = getDefaultCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar getDefaultCalendar() {
        return new GregorianCalendar(TimeZone.getDefault(), Locale.getDefault());
    }

    public enum Month {
        JANUARY(0),
        FEBRUARY(1),
        MARCH(2),
        APRIL(3),
        MAY(4),
        JUNE(5),
        JULY(6),
        AUGUST(7),
        SEPTEMBER(8),
        OCTOBER(9),
        NOVEMBER(10),
        DECEMBER(11);

        private final int number;

        Month(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public static Month get(Calendar calendar) {
            switch (calendar.get(Calendar.MONTH)) {
                case 0: return JANUARY;
                case 1: return FEBRUARY;
                case 2: return MARCH;
                case 3: return APRIL;
                case 4: return MAY;
                case 5: return JUNE;
                case 6: return JULY;
                case 7: return AUGUST;
                case 8: return SEPTEMBER;
                case 9: return OCTOBER;
                case 10: return NOVEMBER;
                case 11: return DECEMBER;
                default: throw new RuntimeException();
            }
        }

    }
    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        public static DayOfWeek get(Calendar calendar) {
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case 1: return SUNDAY;
                case 2: return MONDAY;
                case 3: return TUESDAY;
                case 4: return WEDNESDAY;
                case 5: return THURSDAY;
                case 6: return FRIDAY;
                case 7: return SATURDAY;
                default: throw new RuntimeException(Integer.toString(calendar.get(Calendar.DAY_OF_WEEK)));
            }
        }

    }

    public static final long MS_SECOND = 1_000L;
    public static final long MS_MINUTE = 60_000L;
    public static final long MS_HOUR = 3_600_000L;
    public static final long MS_DAY = 86_400_000L;
    public static final long MS_WEEK = 604_800_000L;
    public static final long MS_YEAR = 31_536_000_000L;
    public static final long MS_YEAR_LEAP = 31_622_400_000L;
    public static final long MS_YEAR_AVG = 31_556_952_000L;

    /** Просчет разницы в годах между двумя датами
     * @param with С какой даты
     * @param on По какую дату
     * @return Разница в годах*/
    public static int differentYear(Calendar with, Calendar on) {
        int diff = on.get(Calendar.YEAR) - with.get(Calendar.YEAR);
        if (with.get(Calendar.MONTH) > on.get(Calendar.MONTH) ||
                (with.get(Calendar.MONTH) == on.get(Calendar.MONTH) && with.get(Calendar.DATE) > on.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

}
