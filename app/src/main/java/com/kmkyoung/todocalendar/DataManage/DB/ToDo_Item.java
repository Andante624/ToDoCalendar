package com.kmkyoung.todocalendar.DataManage.DB;

/**
 * Created by kmkyoung on 2014. 10. 1..
 */
public class ToDo_Item {
    //insert DB

    private String Title;
    private String CreatedDate;
    private String DeadlineDate;
    private String CompletedDate;
    private String Category;
    private float Inportance;
    //not insert DB
    private int Day;

    ToDo_Item(String title, String createddate, String deadlinedate, String completeddate, String category, float inportance)
    {
        Title = title;
        CreatedDate = createddate;
        DeadlineDate = deadlinedate;
        CompletedDate = completeddate;
        Category = category;
        Inportance = inportance;
        Day = Integer.valueOf(DeadlineDate.split("-")[2]);
        setDay();

    }

    public void setTitle(String title) { Title = title; }
    public void setCreatedDate(String createddate) { CreatedDate = createddate; }
    public void setDeadlineDate(String deadlinedate) { DeadlineDate = deadlinedate; }
    public void setCompletedDate(String completeddate) { CompletedDate = completeddate; }
    public void setCategory(String category) { Category = category; }
    public void setInportance(float inportance) { Inportance = inportance; }

    public void setDay() { Day = Integer.valueOf(DeadlineDate.split("-")[2]); }

    public String getTitle() { return Title; }
    public String getCreatedDate() { return CreatedDate; }
    public String getDeadlineDate() { return DeadlineDate; }
    public String getCompletedDate() { return CompletedDate; }
    public String getCategory() { return Category; }
    public float getInportance() { return Inportance; }
    public int getDay() { return Day; }
}
