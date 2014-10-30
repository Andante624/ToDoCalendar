package com.kmkyoung.todocalendar.ToDoList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeiMac on 14. 10. 30..
 */
public class ToDoList_ListViewAdapter extends BaseAdapter {
    List<ToDoList_Item> todolist_items = new ArrayList<ToDoList_Item>();
    Context context;

    public void setContext(Context input_context)
    {
        context = input_context;
    }

    @Override
    public int getCount() {
        return todolist_items.size();
    }

    @Override
    public ToDoList_Item getItem(int position) {
        return todolist_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(){
        todolist_items.add(new ToDoList_Item());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = View.inflate(context, R.layout.listview_item,null);

        return convertView;
    }
}
