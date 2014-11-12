package com.kmkyoung.todocalendar.ToDoList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kmkyoung.todocalendar.R;

/**
 * Created by MinchaeiMac on 14. 11. 5..
 */
public class SpinnerAdapter_ToDo_Parent extends BaseAdapter{
    Context context;
    String[] items;

    public void setContext(Context context) { this.context = context;}
    public void setItems() { items = context.getResources().getStringArray(R.array.todolist_parents_strings);}


    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public String getItem(int position) {
        return items[position];
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
        textView.setText(items[position]);
        return view;
    }

}
