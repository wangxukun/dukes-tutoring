package org.xkidea.dukestutoring.util;

import java.util.Calendar;

public class CalendarUtil {
    public CalendarUtil() {
    }

    /**
     * 剥离时间
     * @param calendar 日历对象
     */
    public static void stripTime(final Calendar calendar) {
        // 设置给定的日历字段值和未定义的此日历的时间值（距纪元的毫秒偏移量）。
        calendar.clear(Calendar.AM_PM);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
    }
}
