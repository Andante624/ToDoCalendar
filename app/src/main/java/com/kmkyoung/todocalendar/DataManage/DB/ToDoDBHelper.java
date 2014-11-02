package com.kmkyoung.todocalendar.DataManage.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kmkyoung on 2014. 9. 28..
 */
public class ToDoDBHelper extends SQLiteOpenHelper{
    public ToDoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("kmky","ToDoDBHelper onCreate");
        String category_table = "create table Category_Table( " +
                " Category_ID integer primary key autoincrement NOT NULL, " +
                " Category_Title text NOT NULL);";
        db.execSQL(category_table);

        Log.d("kmky","category_table");
        String todo_table = "create table ToDo_Table( " +
                " ToDo_ID integer primary key autoincrement NOT NULL, " +
                " ToDo_Title text NOT NULL, " +
                " ToDo_Created_date text NOT NULL, " +
                " ToDo_Deadline_date text NOT NULL, " +
                " ToDo_Completed_date text NOT NULL, " +
                " Category_ID integer NOT NULL default(0), " +
                " ToDo_Inportance float NOT NULL," +
                " FOREIGN KEY(Category_ID) REFERENCES Category_Table(Category_ID));";
        db.execSQL(todo_table);
        Log.d("kmky","todo_table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_droptable= "DROP TABLE IF EXISTS " + "Category_Table;";
        db.execSQL(sql_droptable);
        sql_droptable = "DROP TABLE IF EXISTS " + "ToDo_Table;";
        db.execSQL(sql_droptable);
    }

}
