package com.kmkyoung.todocalendar.Setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.DataManage.DB.ToDoDBManager;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_Setting_Category extends Activity {
    private List<Category_Item> category_items;
    private ListView category_listview;
    private Setting_Category_Adapter setting_category_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_category);
        category_items = new ArrayList<Category_Item>();
        category_listview = (ListView)findViewById(R.id.setting_category_listview);
        setting_category_adapter = new Setting_Category_Adapter();
        category_listview.setAdapter(setting_category_adapter);
        loadCategory();
    }

    public void loadCategory()
    {
        ToDoDBManager toDoDBManager = ToDoDBManager.open(getApplicationContext());
        category_items = toDoDBManager.selectAllCategory();
        if(category_items.isEmpty())
            category_items.add(new Category_Item(0,"없음"));

    }

    public class Setting_Category_Adapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return category_items.size();
        }

        @Override
        public Object getItem(int position) {
            return category_items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = View.inflate(getApplicationContext(),R.layout.setting_listview_item,null);

            TextView categorys = (TextView)convertView.findViewById(R.id.setting_listview_menu);
            categorys.setText(category_items.get(position).getCategory_Name());

            return convertView;
        }
    }
}
