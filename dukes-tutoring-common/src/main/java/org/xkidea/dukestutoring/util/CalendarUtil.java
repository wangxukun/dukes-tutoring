package org.xkidea.dukestutoring.util;

import java.util.Calendar;

public class CalendarUtil {
    public CalendarUtil() {
    }

    public static void stripTime(final Calendar calendar) {
        calendar.clear(Calendar.AM_PM);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
    }
}
