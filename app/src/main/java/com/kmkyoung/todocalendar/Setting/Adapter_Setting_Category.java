package com.kmkyoung.todocalendar.Setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.DataManage.DB.ToDoDBManager;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeAir on 14. 11. 3..
 */
public class Adapter_Setting_Category extends BaseAdapter {
    private List<Category_Item> category_items = new ArrayList<Category_Item>();
    private Context context;
    private ListView listView;

    @Override
    public int getCount() {
        return category_items.size();
    }

    public void setListView(ListView listvew)
    {
        this.listView = listvew;
    }

    public void add(Category_Item newItem)
    {
        category_items.add(newItem);
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    @Override
    public Category_Item getItem(int position) {
        return category_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = View.inflate(context, R.layout.setting_listview_item_category,null);

        TextView textView = (TextView)convertView.findViewById(R.id.setting_listview_item_title);
        textView.setText(category_items.get(position).getCategory_Name());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category_items.size()-1 == position) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    ab.setTitle("카테고리 추가");
                    ab.setMessage("추가할 카테고리를 입력해주세요.");
                    final EditText input_category = new EditText(context);
                    ab.setView(input_category);
                    ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insertCategory(input_category.getText().toString());
                        }
                    });
                    ab.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ab.show();
                }
            }
        });
        return convertView;
    }

    public void setCategorys(List<Category_Item> items)
    {
        category_items.clear();
        category_items = items;
        category_items.add(new Category_Item(0,"추가하기"));
    }

    public void insertCategory(String new_category_name)
    {
        category_items.remove(category_items.size()-1);
        ToDoDBManager toDoDBManager = ToDoDBManager.open(context);
        int newId = toDoDBManager.insertCategory(new_category_name);
        if(newId != -1)
        {
            add(new Category_Item(newId,new_category_name));
        }
        toDoDBManager.close();

        category_items.add(new Category_Item(0,"추가하기"));
        listView.invalidateViews();
    }


}
