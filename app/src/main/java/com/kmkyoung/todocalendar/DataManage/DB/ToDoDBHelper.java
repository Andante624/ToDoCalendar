package com.kmkyoung.todocalendar.DataManage.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kmkyoung on 2014. 9. 28..
 */
public class ToDoDBHelper extends SQLiteOpenHelper{
    public ToDoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table ToDoList ( " +
                " todo_id integer primary key autoincrement , " +
                " title text , " +
                " createddate text , " +
                " deadlinedate text , " +
                " completeddate text , " +
                " category text , " +
                " inportance float)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
