package com.skytala.eCommerce.framework.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;

public class TimestampUtil {

    public static Timestamp currentTime(){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        currentTime.setNanos(0);
        return currentTime;
    }

    public static Timestamp emailVerificationExpireTime(){

        Calendar calendar = Calendar.getInstance(Locale.GERMANY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
        Timestamp expiredTime = new Timestamp(calendar.getTimeInMillis());
        expiredTime.setNanos(0);
        return expiredTime;
    }
}
