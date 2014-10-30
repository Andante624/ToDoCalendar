package com.kmkyoung.todocalendar.Calendar;

import com.kmkyoung.todocalendar.DataManage.DB.ToDo_Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Calendar_Item {
    private int Day;
    private String Date;
    private boolean Blank = false;
    private List<ToDo_Item> Items;

    Calendar_Item(int day, String date)
    {
        Day = day;
        Date = date;
    }

    Calendar_Item(boolean blank)
    {
        Blank = blank;
    }

    public void setDay(int day) { Day = day; }
    public int getDay() { return Day; }
    public String getDate() { return Date; }
    public void setToDo_ItemList(List<ToDo_Item> items)
    {
        Items = items;
    }

    public List<ToDo_Item> getToDo_ItemList()
    {
        return Items;
    }

    public boolean isBlank(){ return Blank; }

}
