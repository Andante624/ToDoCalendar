package com.kmkyoung.todocalendar.DataManage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 29..
 */
public class ToDoDBManager {
    private static List<Category_Item> category_items = new ArrayList<Category_Item>();
    private ToDoDBHelper todo_db_helper;
    private SQLiteDatabase db;

    public ToDoDBManager(Context context)
    {
        todo_db_helper = new ToDoDBHelper(context,"ToDo_Calendar.sqlite",null, 1);
    }

    public static ToDoDBManager open(Context context)
    {
        return new ToDoDBManager(context);
    }

    public void close()
    {
        db.close();
    }

    public static int getCategoryCount()
    {
        return category_items.size();
    }

    public void deleteAllData()
    {
        String sql = "delete from ToDo_Table;";
        db = todo_db_helper.getWritableDatabase();
        db.execSQL(sql);
        String sql2 = "delete from Category_Table;";
        db.execSQL(sql2);

    }

    /* ToDo_Table 관련 class */
    public void insertToDo(String title, String deadlinedate, String completeddate, int category, float inportance)
    {
        Calendar calendar = Calendar.getInstance();
        String createddate = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);

        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ToDo_Title", title);
        values.put("ToDo_Created_date",createddate);
        values.put("ToDo_Deadline_date",deadlinedate);
        values.put("ToDo_Completed_date", completeddate);
        values.put("Category_ID",category);
        values.put("ToDo_Inportance",inportance);
        db.insert("ToDo_Table",null,values);
    }

    public List<ToDo_Item> selectDeadLineDate(String deadline)
    {
        List<ToDo_Item> items = new ArrayList<ToDo_Item>();

        String sql = "select * from 'ToDo_Table' where ToDo_Deadline_date = '"+deadline+"';";

        db = todo_db_helper.getReadableDatabase();
        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();


        while (!result.isAfterLast()) {
            int id = result.getInt(result.getColumnIndex("ToDo_ID"));
            String title = result.getString(result.getColumnIndex("ToDo_Title"));
            String createddate = result.getString(result.getColumnIndex("ToDo_Created_date"));
            String deadlinedate = result.getString(result.getColumnIndex("ToDo_Deadline_date"));
            String completeddate = result.getString(result.getColumnIndex("ToDo_Completed_date"));
            int category = result.getInt(result.getColumnIndex("Category_ID"));
            float importance = result.getFloat(result.getColumnIndex("ToDo_Inportance"));

            items.add(new ToDo_Item(id, title, createddate, deadlinedate, completeddate, category, importance));
            result.moveToNext();
        }

        return items;
    }




    /* Category_Table 관련 class */

    //return keyvalue = Category_ID
    public int insertCategory(String name)
    {
        if(!checkExistCategory(name))
        {
            db = todo_db_helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Category_Title", name);
            db.insert("Category_Table", null, values);
            db.close();
        }

        db = todo_db_helper.getReadableDatabase();
        String sql = "select * from 'Category_Table' where Category_Title='" + name + "';";
        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();
        int category_id = result.getColumnIndex("Category_ID");

        return category_id;
    }

    public boolean checkExistCategory(String name)
    {
        db = todo_db_helper.getReadableDatabase();
        String sql = "select * from 'Category_Table' where Category_Title='"+name+"';";
        Cursor result = db.rawQuery(sql,null);
        int count = result.getCount();
        db.close();
        if(count == 0)
            return false;
        else
            return true;
    }

    //return count
    public int selectAllCategory(List<Category_Item> items, List<String> strings)
    {
        strings.clear();
        items.clear();

        category_items.clear();
        String sql = "select * from 'Category_Table';";
        db = todo_db_helper.getReadableDatabase();
        Cursor categorys = db.rawQuery(sql,null);
        categorys.moveToFirst();
        while(!categorys.isAfterLast())
        {
            category_items.add(new Category_Item(categorys.getInt(categorys.getColumnIndex("Category_ID")), categorys.getString(categorys.getColumnIndex("Category_Title"))));
            strings.add(categorys.getString(categorys.getColumnIndex("Category_Title")));
            categorys.moveToNext();
        }

        items = category_items;

        return category_items.size();
    }

    public static String getCategoryName(int Category_ID)
    {
        for(int i=0; i<category_items.size() ; i++)
        {
            if(category_items.get(i).getCategory_ID() == Category_ID)
                return category_items.get(i).getCategory_Name();
        }
        return "";
    }

}
