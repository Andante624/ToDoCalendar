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
        init();
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
        init();
    }

    public void init()
    {
        if(ToDoDBManager.getCategoryCount() == 0)
        {
            insertCategory("없음");
            close();
        }
    }

    /* ToDo_Table 관련 class */
    public void insertToDo(String title, String deadlinedate, String category, float inportance)
    {
        Calendar calendar = Calendar.getInstance();
        String createddate = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);

        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ToDo_Title", title);
        values.put("ToDo_Created_date",createddate);
        values.put("ToDo_Deadline_date",deadlinedate);
        values.put("ToDo_Completed_date", "");
        values.put("Category_ID",getCategoryID(category));
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
            items.add(selectToDoItems(result));
            result.moveToNext();
        }

        return items;
    }

    public ToDo_Item selectToDoItem(int todo_id)
    {
        String sql = "select * from 'ToDo_Table' where ToDo_ID = "+todo_id+";";
        db = todo_db_helper.getReadableDatabase();
        Cursor item = db.rawQuery(sql, null);
        item.moveToFirst();

        return selectToDoItems(item);
    }

    public ToDo_Item selectToDoItems(Cursor cursor)
    {
        int id = cursor.getInt(cursor.getColumnIndex("ToDo_ID"));
        String title = cursor.getString(cursor.getColumnIndex("ToDo_Title"));
        String createddate = cursor.getString(cursor.getColumnIndex("ToDo_Created_date"));
        String deadlinedate = cursor.getString(cursor.getColumnIndex("ToDo_Deadline_date"));
        String completeddate = cursor.getString(cursor.getColumnIndex("ToDo_Completed_date"));
        int category = cursor.getInt(cursor.getColumnIndex("Category_ID"));
        float importance = cursor.getFloat(cursor.getColumnIndex("ToDo_Inportance"));
        return new ToDo_Item(id,title,createddate,deadlinedate,completeddate,category,importance);
    }

    public void editToDoItem(ToDo_Item editItem)
    {
        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ToDo_Title", editItem.getTitle());
        values.put("ToDo_Deadline_date",editItem.getDeadlineDate());
        values.put("ToDo_Completed_date",editItem.getCompletedDate());
        values.put("Category_ID",editItem.getCategory());
        values.put("ToDo_Inportance",editItem.getInportance());
        db.update("ToDo_Table",values,"ToDO_ID = ?",new String[]{String.valueOf(editItem.getID())});
    }


    public void deleteToDoItem(int deleteitem_id)
    {
        String sql = "delete from ToDo_Table where ToDo_ID ="+deleteitem_id+";";
        db = todo_db_helper.getWritableDatabase();
        db.execSQL(sql);
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

    public List<Category_Item> selectAllCategory()
    {
        getUpdateCategory();
        return category_items;
    }

    public List<String> selectAllCategoryStrings()
    {
        getUpdateCategory();
        List<String> strings = new ArrayList<String>();
        for(int i=0 ; i< category_items.size() ; i++)
            strings.add(category_items.get(i).getCategory_Name());
        return strings;
    }

    public void getUpdateCategory()
    {
        category_items.clear();
        String sql = "select * from 'Category_Table';";
        db = todo_db_helper.getReadableDatabase();
        Cursor categorys = db.rawQuery(sql,null);
        categorys.moveToFirst();
        while(!categorys.isAfterLast())
        {
            category_items.add(new Category_Item(categorys.getInt(categorys.getColumnIndex("Category_ID")), categorys.getString(categorys.getColumnIndex("Category_Title"))));
            categorys.moveToNext();
        }
    }

    public void deleteCategoryItem(int category_id)
    {
        int default_id = 0;
        for(int i=0; i<category_items.size() ; i++) {
            if(category_items.get(i).getCategory_Name().equals("없음")) {
                default_id = category_items.get(i).getCategory_ID();
                break;
            }
        }

        String sql = "delete from Category_Table where Category_ID ="+category_id+";";
        db = todo_db_helper.getWritableDatabase();
        db.execSQL(sql);

        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Category_ID",default_id);
        db.update("ToDo_Table",values,"Category_ID = ?",new String[]{String.valueOf(category_id)});
    }

    public int getCategoryID(String Category_Title)
    {
        getUpdateCategory();

        for(int i=0; i<category_items.size() ; i++)
        {
            if(Category_Title.equals(category_items.get(i).getCategory_Name()))
                return category_items.get(i).getCategory_ID();
        }
        return -1;
    }

    public String getCategoryTitle(int Category_id)
    {
        getUpdateCategory();

        for(int i=0; i<category_items.size() ; i++)
        {
            if(Category_id == category_items.get(i).getCategory_ID())
                return category_items.get(i).getCategory_Name();
        }
        return "";
    }
}
