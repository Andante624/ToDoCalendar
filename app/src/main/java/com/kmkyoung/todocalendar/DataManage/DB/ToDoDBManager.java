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
    private ToDoDBHelper todo_db_helper;
    private SQLiteDatabase db;
    private HashMap category_map = new HashMap();

    //init
    public ToDoDBManager(Context context)
    {
        Log.d("kmky","ToDoDBManager");
        todo_db_helper = new ToDoDBHelper(context,"ToDo_Calendar.sqlite",null, 1);
    }

    public static ToDoDBManager open(Context context)
    {
        return new ToDoDBManager(context);
    }

    //save
    public void insertDB(String title, String deadlinedate, String completeddate, String category, float inportance)
    {
        Log.d("kmky","insertDB");
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

    public static void selectAllCategory()
    {

        Log.d("kmky","selectAllCategory");
    }

    public List<ToDo_Item> selectDeadLineDate(String deadline)
    {
        Log.d("kmky","selectDeadLineDate");
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

    public void close()
    {
        db.close();
    }

}
