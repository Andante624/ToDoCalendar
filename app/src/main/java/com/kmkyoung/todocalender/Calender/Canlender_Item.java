package com.kmkyoung.todocalender.Calender;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Canlender_Item {
    private String Title;
    private String Date;
    private String Category;
    private int Inportance;

    Canlender_Item(String title, String date, String category, int inportance)
    {
        Title = title;
        Date = date;
        Category = category;
        Inportance = inportance;
    }

    public void setTitle(String title) { Title = title; }
    public void setDate(String date) { Date = date; }
    public void setCategory(String category) { Category = category; }
    public void setInportance(int inportance) { Inportance = inportance; }
    public String getTitle() { return Title; }
    public String getCategory() { return Category; }
    public int getInportance() { return Inportance; }


}
