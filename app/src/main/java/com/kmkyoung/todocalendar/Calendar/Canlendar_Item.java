package com.kmkyoung.todocalendar.Calendar;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Canlendar_Item {
    //insert DB
    private String Title;
    private String Date;
    private String Category;
    private String DateCreated;
    private float Inportance;
    //not insert DB
    private int Day;

    Canlendar_Item(String title, String date, String category, int inportance, String created)
    {
        Title = title;
        Date = date;
        Day = Integer.valueOf(Date);
        Category = category;
        Inportance = inportance;
        DateCreated = created;
    }

    public void setTitle(String title) { Title = title; }
    public void setDate(String date) { Date = date; }
    public void setDateCreated(String created) { DateCreated = created; }
    public void setCategory(String category) { Category = category; }
    public void setInportance(float inportance) { Inportance = inportance; }
    public String getTitle() { return Title; }
    public String getDate() { return Category; }
    public String getDateCreated() { return DateCreated; }
    public String getCategory() { return Category; }
    public float getInportance() { return Inportance; }
    public int getDay() { return Day; }


}
