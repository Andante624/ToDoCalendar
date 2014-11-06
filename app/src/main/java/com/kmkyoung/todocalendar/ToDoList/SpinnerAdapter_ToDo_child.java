package com.kmkyoung.todocalendar.ToDoList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.DataManage.DB.DBManager;
import com.kmkyoung.todocalendar.R;

import java.util.List;

/**
 * Created by MinchaeiMac on 14. 11. 5..
 */
public class SpinnerAdapter_ToDo_child extends BaseAdapter {
    private Context context;
    private int select = 0;
    private String[] importance_items;    //value of importance
    private String[] category_items;

    public void setContext(Context context) { this.context = context; }
    public void setItems() {
        importance_items = context.getResources().getStringArray(R.array.todolist_child_strings);
        updateCategory();
    }
    public void setSelect(int selectnum) { select = selectnum; }
    public int getSelect() {return select;}

    @Override
    public int getCount() {
        if(select ==0)
            return importance_items.length;
        else
            return category_items.length;
    }

    @Override
    public String getItem(int position) {
        if(select ==0)
            return importance_items[position];
        else
            return category_items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, android.R.layout.simple_spinner_item, null);
        TextView textView = (TextView)view.findViewById(android.R.id.text1);
        textView.setTextColor(context.getResources().getColor(R.color.default_blue));
        if(select ==0)
            textView.setText(importance_items[position]);
        else
            textView.setText(category_items[position]);
        return view;
    }

    public void updateCategory()
    {
        DBManager dbManager = DBManager.open(context.getApplicationContext());
        List<String> strings = dbManager.select_AllCategoryItem_Strings();
        category_items = strings.toArray(new String[strings.size()]);
        dbManager.close();
    }
}

