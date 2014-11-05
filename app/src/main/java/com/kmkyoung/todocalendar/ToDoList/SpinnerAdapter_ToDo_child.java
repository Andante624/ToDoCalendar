package com.kmkyoung.todocalendar.ToDoList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.kmkyoung.todocalendar.R;

/**
 * Created by MinchaeiMac on 14. 11. 5..
 */
public class SpinnerAdapter_ToDo_child extends BaseAdapter {
    private boolean select_items1 = true;
    private Context context;
    private String[] items1;    //category or importance
    private String[] items2;    //value of importance

    public void setContext(Context context) { this.context = context; }
    public void setItems() {
        items1 = context.getResources().getStringArray(R.array.todolist_child_strings1);
        items2 = context.getResources().getStringArray(R.array.todolist_child_strings2);
    }

    public void setSelect(boolean selected1) { select_items1 = selected1; }

    @Override
    public int getCount() {
        if(select_items1)
            return items1.length;
        else
            return items2.length;
    }

    @Override
    public String getItem(int position) {
        if(select_items1)
            return items1[position];
        else
            return items2[position];
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
        if(select_items1)
            textView.setText(items1[position]);
        else
            textView.setText(items2[position]);
        return view;
    }
}

