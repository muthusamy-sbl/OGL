package com.manappuram.ogl.util;

//import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeUtil {

//    public static boolean isAfter(){
//
//    }

    public static String getTimesAgo(String date) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy", Locale.ENGLISH);
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String value = DateUtils.getRelativeTimeSpanString(calendar.getTimeInMillis(), new Date().getTime(),
//                DateUtils.DAY_IN_MILLIS).toString();

        return TimeAgo.getTimeAgo(calendar.getTimeInMillis());
    }

}
