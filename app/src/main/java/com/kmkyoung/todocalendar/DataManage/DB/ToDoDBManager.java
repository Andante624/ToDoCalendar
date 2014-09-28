package com.kmkyoung.todocalendar.DataManage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kmkyoung.todocalendar.Calendar.Canlendar_Item;

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

    //save
    public void insertDB(Canlendar_Item newitem)
    {
        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", newitem.getTitle());
        values.put("date",newitem.getDate());
        values.put("datecreated",newitem.getDateCreated());
        values.put("category",newitem.getCategory());
        values.put("inportance",newitem.getInportance());
        db.insert("ToDoList",null,values);
    }

}
