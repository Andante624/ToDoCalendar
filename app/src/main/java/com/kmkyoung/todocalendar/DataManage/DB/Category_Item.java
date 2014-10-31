package com.kmkyoung.todocalendar.DataManage.DB;

/**
 * Created by MinchaeAir on 14. 10. 31..
 */
public class Category_Item {
    int Category_ID;
    String Category_Name;

    Category_Item(int category_ID, String category_Name)
    {
        Category_ID = category_ID;
        Category_Name = category_Name;
    }

    public void setCategory_ID(int category_ID) { Category_ID = category_ID;}
    public void setCategory_Name(String category_Name) { Category_Name = category_Name;}

    public int getCategory_ID() { return Category_ID; }
    public String getCategory_Name() { return Category_Name; }
}
