package com.kmkyoung.todocalendar.Calendar;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Calendar_Utils {
    /* return value 1 = Sunday, 2 = Monday, 3 = Tuseday, 4 = Wednesday, 5 = Thursday, 6 = Friday, 7 = Saturday */
    public static int getFirstWeek(int year, int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getLastWeek(int year, int month, int day)
    {
        Calendar calendar  = Calendar.getInstance();
        calendar.set (year,month,day);
        return calendar.getActualMaximum(Calendar.DATE);
    }
}
