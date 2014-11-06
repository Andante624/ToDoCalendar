package com.kmkyoung.todocalendar.DataManage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kmkyoung.todocalendar.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 29..
 */
public class DBManager {
    public final static int WHERE_MATCH_TODO_ID =0;
    public final static int WHERE_MATCH_DEADLINE_DATE = 1;
    public final static int WHERE_MATCH_CATEGORY = 2;
    public final static int WHERE_MATCH_IMPORTANCE = 3;

    public final static int WHERE_COMPARISON_7CREATE_DATE = 4;
    public final static int WHERE_COMPARISON_DEADLINE_DATE = 5;
    public final static int WHERE_COMPARISON_7DEADLINE_DATE = 6;
    public final static int WHERE_COMPARISON_COMPLETE_DATE = 7;


    private static List<Category_Item> category_items = new ArrayList<Category_Item>();
    private DBHelper todo_db_helper;
    private SQLiteDatabase db;

    public DBManager(Context context)
    {
        todo_db_helper = new DBHelper(context,"ToDo_Calendar.sqlite",null, 1);
        init();
    }

    public void init()
    {
        if(DBManager.get_CategoryCount() == 0)
        {
            insert_CategoryItem("없음");
            close();
        }
    }

    public static DBManager open(Context context)
    {
        return new DBManager(context);
    }
    public void close()
    {
        db.close();
    }

    public void delete_AllData()
    {
        String sql = "delete from ToDo_Table;";
        db = todo_db_helper.getWritableDatabase();
        db.execSQL(sql);
        String sql2 = "delete from Category_Table;";
        db.execSQL(sql2);
        init();
    }

    /* ToDo_Table 관련 class */
    public void insert_ToDoItem(String title, String deadlinedate, String category, float inportance)
    {
        db = todo_db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ToDo_Title", title);
        values.put("ToDo_Created_date", Utils.getStringToday());
        values.put("ToDo_Deadline_date",deadlinedate);
        values.put("ToDo_Completed_date", "");
        values.put("Category_ID",get_CategoryID(category));
        values.put("ToDo_Inportance",inportance);
        db.insert("ToDo_Table",null,values);
    }

    public void update_ToDoItem(ToDo_Item editItem)
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

    public ToDo_Item select_ToDoItem(int todo_id)
    {
        String sql = "select * from 'ToDo_Table' where ToDo_ID = "+todo_id+";";
        db = todo_db_helper.getReadableDatabase();
        Cursor item = db.rawQuery(sql, null);
        item.moveToFirst();

        return get_ToDoItems(item);
    }

    public List<ToDo_Item> select_ToDoItems(int where, String condition)
    {
        List<ToDo_Item> items = new ArrayList<ToDo_Item>();
        String sql = "select * from 'ToDo_Table' where ";
        switch (where)
        {
            case WHERE_MATCH_DEADLINE_DATE:
                sql += "ToDo_Deadline_date = '"+condition+"';";
                break;
            case WHERE_COMPARISON_DEADLINE_DATE:
                sql += "date(ToDo_Deadline_date) >= date('now') and ToDo_Completed_date='' order by date(ToDo_Deadline_date) ;";
                break;
            case WHERE_COMPARISON_COMPLETE_DATE:
                sql += "date(ToDo_Completed_date) <= date('now') order by date(ToDo_Completed_date) DESC;";
                break;
            case WHERE_COMPARISON_7DEADLINE_DATE:
                sql += "date(ToDo_Deadline_date) between date('now') and date('now','+6 day') order by date(ToDo_Deadline_date);";
                break;
            case WHERE_COMPARISON_7CREATE_DATE:
                sql += "date(ToDo_Created_date) between date('now','-6 day') and date('now') order by date(ToDo_Created_date) DESC;";
        }

        db = todo_db_helper.getReadableDatabase();
        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();


        while (!result.isAfterLast()) {
            items.add(get_ToDoItems(result));
            result.moveToNext();
        }

        return items;
    }

    public ToDo_Item get_ToDoItems(Cursor cursor)
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

    public void delete_ToDoItem(int deleteitem_id)
    {
        String sql = "delete from ToDo_Table where ToDo_ID ="+deleteitem_id+";";
        db = todo_db_helper.getWritableDatabase();
        db.execSQL(sql);
    }

    /* Category_Table 관련 class */

    //return keyvalue = Category_ID
    public int insert_CategoryItem(String name)
    {
        if(!checkExist_CategoryItem(name))
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



    public boolean checkExist_CategoryItem(String name)
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

    public List<Category_Item> select_AllCategoryItem()
    {
        update_CategoryItems();
        return category_items;
    }

    public List<String> select_AllCategoryItem_Strings()
    {
        update_CategoryItems();
        List<String> strings = new ArrayList<String>();
        for(int i=0 ; i< category_items.size() ; i++)
            strings.add(category_items.get(i).getCategory_Name());
        return strings;
    }

    public void update_CategoryItems()
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

    public void delete_CategoryItem(int category_id)
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

    public int get_CategoryID(String Category_Title)
    {
        update_CategoryItems();

        for(int i=0; i<category_items.size() ; i++)
        {
            if(Category_Title.equals(category_items.get(i).getCategory_Name()))
                return category_items.get(i).getCategory_ID();
        }
        return -1;
    }

    public String get_CategoryTitle(int Category_id)
    {
        update_CategoryItems();

        for(int i=0; i<category_items.size() ; i++)
        {
            if(Category_id == category_items.get(i).getCategory_ID())
                return category_items.get(i).getCategory_Name();
        }
        return "";
    }

    public static int get_CategoryCount()
    {
        return category_items.size();
    }
}
