package com.kmkyoung.todocalendar.ToDoList;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.kmkyoung.todocalendar.R;

/**
 * Created by MinchaeiMac on 14. 11. 5..
 */
public class SpinnerAdapter_ToDo_parents implements SpinnerAdapter {
    Context context;
    String[] items;

    public void setContext(Context context) { this.context = context;}
    public void setItems() { items = context.getResources().getStringArray(R.array.todolist_parents_strings);}
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,android.R.layout.simple_spinner_dropdown_item,null);
        TextView textView = (TextView)view.findViewById(android.R.id.text1);
        textView.setText(items[position]);
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return (items.length == 0)? true : false;
    }
}
