package com.bishwa.twitter.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author: Bishwa
 * Date: 05/03/2021
 * Time: 16:12
 */
public class DateUtils {
    public static Date getNextSchedulingTime() {
        Calendar scheduledTime = new GregorianCalendar();
        scheduledTime.set(Calendar.MINUTE, 0);
        scheduledTime.set(Calendar.SECOND, 0);

        if((new GregorianCalendar()).after(scheduledTime)) scheduledTime.add(Calendar.HOUR, 1);

        return scheduledTime.getTime();
    }
}
