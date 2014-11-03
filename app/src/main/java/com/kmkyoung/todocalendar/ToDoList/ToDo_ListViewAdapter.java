package com.kmkyoung.todocalendar.ToDoList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.ToDoDBManager;
import com.kmkyoung.todocalendar.DataManage.DB.ToDo_Item;
import com.kmkyoung.todocalendar.DataManage.Fragment_AddToDoItem;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeiMac on 14. 10. 30..
 */
public class ToDo_ListViewAdapter extends BaseAdapter {
    List<ToDo_Item> todo_items = new ArrayList<ToDo_Item>();
    Fragment fragment;
    Activity activity;
    ListView listview;

    public void setContext(Activity input_activity)
    {
        activity = input_activity;
    }

    public void setListview(ListView listview)
    {
        this.listview = listview;
    }
    public void setFragment(Fragment fragment) { this.fragment = fragment; }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = View.inflate(activity.getApplicationContext(), R.layout.listview_item,null);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = fragment.getFragmentManager();
                Fragment_AddToDoItem fragment_editItem = Fragment_AddToDoItem.newInstance(todo_items.get(position).getID());

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_editItem)
                        .addToBackStack(null)
                        .commit();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(activity);
                alertdialog.setTitle("ToDo 삭제");
                alertdialog.setMessage("ToDo를 삭제하시겠습니까?");
                alertdialog.setPositiveButton("승인",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToDoDBManager toDoDBManager = ToDoDBManager.open(activity.getApplicationContext());
                        toDoDBManager.deleteToDoItem(todo_items.get(position).getID());
                        toDoDBManager.close();
                        todo_items.remove(position);
                        dialog.dismiss();
                        listview.invalidateViews();
                    }
                });
                alertdialog.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertdialog.show();
                return false;
            }
        });

        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.todoitem_checkbox);
        TextView dateview = (TextView)convertView.findViewById(R.id.todoitem_date);
        TextView titleview = (TextView)convertView.findViewById(R.id.todoitem_title);

        String[] split_data = todo_items.get(position).getDeadlineDate().split("-");
        String viewString = split_data[0]+"-"+(Integer.valueOf(split_data[1])+1)+"-"+split_data[2];
        dateview.setText(viewString);
        titleview.setText(todo_items.get(position).getTitle());

        return convertView;
    }

}
