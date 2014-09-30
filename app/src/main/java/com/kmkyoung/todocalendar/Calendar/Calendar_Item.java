package com.kmkyoung.todocalendar.Calendar;

import com.kmkyoung.todocalendar.DataManage.DB.ToDo_Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Calendar_Item {
    private int Day;
    private boolean Blank = false;
    private List<ToDo_Item> items =new ArrayList<ToDo_Item>();

    Calendar_Item(int day)
    {
        Day = day;
    }

    Calendar_Item(boolean blank)
    {
        Blank = blank;
    }

    public void setDay(int day) { Day = day; }
    public int getDay() { return Day; }

    public void addToDoItem(ToDo_Item newItems)
    {
        items.add(newItems);
    }

    public boolean isBlank(){ return Blank; }
    public ToDo_Item getItem(int position) { return items.get(position);}
    public int size() { return items.size(); }
    public void clearAll() { items.clear(); }

}
