package com.kmkyoung.todocalendar.DataManage.DB;

/**
 * Created by kmkyoung on 2014. 10. 1..
 */
public class ToDo_Item {
    //insert DB
    private int ID;
    private String Title;
    private String CreatedDate;
    private String DeadlineDate;
    private String CompletedDate;
    private int Category;
    private float Importance;
    //not insert DB
    private int Day;

    ToDo_Item(int id, String title, String createddate, String deadlinedate, String completeddate, int category, float importance)
    {
        ID = id;
        Title = title;
        CreatedDate = createddate;
        DeadlineDate = deadlinedate;
        CompletedDate = completeddate;
        Category = category;
        Importance = importance;
        Day = Integer.valueOf(DeadlineDate.split("-")[2]);
        setDay();

    }

    public void setID(int id) { ID = id; }
    public void setTitle(String title) { Title = title; }
    public void setCreatedDate(String createddate) { CreatedDate = createddate; }
    public void setDeadlineDate(String deadlinedate) { DeadlineDate = deadlinedate; }
    public void setCompletedDate(String completeddate) { CompletedDate = completeddate; }
    public void setCategory(int category) { Category = category; }
    public void setImportance(float importance) { Importance = importance; }

    public void setDay() { Day = Integer.valueOf(DeadlineDate.split("-")[2]); }

    public int getID() { return ID; }
    public String getTitle() { return Title; }
    public String getCreatedDate() { return CreatedDate; }
    public String getDeadlineDate() { return DeadlineDate; }
    public String getCompletedDate() { return CompletedDate; }
    public int getCategory() { return Category; }
    public float getImportance() { return Importance; }
    public int getDay() { return Day; }
}
