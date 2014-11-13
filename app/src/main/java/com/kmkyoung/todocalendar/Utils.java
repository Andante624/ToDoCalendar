package com.kmkyoung.todocalendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Utils {
    public static int[] array_color = new int[]{
            R.color.default_blue,R.color.default_green, R.color.default_yellow,R.color.default_orange,R.color.default_red,R.color.default_black};
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

    public static String getStringDate(int year, int month, int day)
    {
        String date = year+"-";
        date += (month>=10)? month+"-" : "0"+month+"-";
        date += (day>=10)? day : "0"+day;
        return date;
    }
    public static String getStringToday()
    {
        Calendar calendar = Calendar.getInstance();

        String date = ""+calendar.get(Calendar.YEAR)+ "-";
        date += (calendar.get(Calendar.MONTH)+1>=10)? (calendar.get(Calendar.MONTH)+1)+"-" :"0"+(calendar.get(Calendar.MONTH)+1)+"-";
        date += (calendar.get(Calendar.DAY_OF_MONTH)>=10)? calendar.get(Calendar.DAY_OF_MONTH)+"" :"0"+calendar.get(Calendar.DAY_OF_MONTH);
        return date;
    }
    public static int getColorId(int position)
    {
        return array_color[position];
    }

    public static int getBackgroundColor(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Setting",context.MODE_PRIVATE);
        return sharedPreferences.getInt("BackGroundColor",0);
    }
}
