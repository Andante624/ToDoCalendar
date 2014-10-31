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
        String todo_table = "create table ToDo_Table ( " +
                " ToDo_ID int(11) primary key autoincrement not null, " +
                " ToDo_Title text not null, " +
                " ToDo_Created_date text not null, " +
                " ToDo_Deadline_date text not null , " +
                " ToDo_Completed_date text not null , " +
                " Category_ID int (11) not null default(0) , " +
                " ToDo_Inportance float not null)," +
                " Foreign Key (Category_ID) REFERENCES Category_Table(Category_ID)";
        db.execSQL(todo_table);

        String category_table = "create table Category_Table ( " +
                " Category_ID int(11) primary key autoincrement not null, " +
                " Category_Title text not null";
        db.execSQL(category_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
