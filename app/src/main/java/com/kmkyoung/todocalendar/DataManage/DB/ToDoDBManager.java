package com.kmkyoung.todocalendar.DataManage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kmkyoung.todocalendar.Calendar.Calendar_Item;

import java.util.Calendar;

/**
 * Created by kmkyoung on 2014. 9. 29..
 */
public class ToDoDBManager {
    private ToDoDBHelper todo_db_helper;
    private SQLiteDatabase db;

    //init
    public ToDoDBManager(Context context)
    {
        todo_db_helper = new ToDoDBHelper(context,"todo.sqlite",null, 1);
    }

    public static ToDoDBManager open(Context context)
    {
        return new ToDoDBManager(context);
    }

    //save
    public void insertDB(String title, String deadlinedate, String completeddate, String category, float inportance)
    {
        Calendar calendar = Calendar.getInstance();
        String createddate = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);

        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("createddate",createddate);
        values.put("deadlinedate",deadlinedate);
        values.put("completeddate", completeddate);
        values.put("category",category);
        values.put("inportance",inportance);
        db.insert("ToDoList",null,values);
    }

    public Cursor search()
    {
        db = todo_db_helper.getReadableDatabase();
        Cursor cursor = db.query("ToDoList",null,null,null,null,null,null);
        return cursor;
    }

    public void close()
    {
        db.close();
    }

}
