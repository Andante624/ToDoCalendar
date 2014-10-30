package com.kmkyoung.todocalendar.ToDoList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.ToDo_Item;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeiMac on 14. 10. 30..
 */
public class ToDo_ListViewAdapter extends BaseAdapter {
    List<ToDo_Item> todo_items = new ArrayList<ToDo_Item>();
    Context context;

    public void setContext(Context input_context)
    {
        context = input_context;
    }

    @Override
    public int getCount() {
        return todo_items.size();
    }

    @Override
    public ToDo_Item getItem(int position) {
        return todo_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setTodolist_items(List<ToDo_Item> items)
    {
        todo_items = items;
    }

    public void add(){
        // todolist_items.add(new ToDo_Item());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = View.inflate(context, R.layout.listview_item,null);

        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.todoitem_checkbox);
        TextView dateview = (TextView)convertView.findViewById(R.id.todoitem_date);
        TextView titleview = (TextView)convertView.findViewById(R.id.todoitem_title);

        dateview.setText(todo_items.get(position).getDeadlineDate());
        titleview.setText(todo_items.get(position).getTitle());

        return convertView;
    }
}
