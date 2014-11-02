package com.kmkyoung.todocalendar.Setting;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.DataManage.DB.ToDoDBManager;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeiMac on 14. 11. 2..
 */
public class Dialog_Setting_Category extends Dialog {
    private Context context;
    private ListView category_listview;
    private List<Category_Item> category_items;
    private ArrayAdapter<String> adapter;
    private List<String> strings = new ArrayList<String>();

    public Dialog_Setting_Category(final Context context) {
        super(context);
        this.context = context;
        setTitle("카테고리 목록");
        setContentView(R.layout.dialog_setting_category);

        category_items = new ArrayList<Category_Item>();
        loadCategory();

        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,strings);
        category_listview = (ListView)findViewById(R.id.setting_category_dialoglist);
        category_listview.setAdapter(adapter);
        category_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(adapter.getCount() == position) {
//                    AlertDialog.Builder ab = new AlertDialog.Builder(getOwnerActivity());
//                    ab.setItems(strings,new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    ab.show();
//                }
            }
        });
    }

    public void loadCategory()
    {
        strings.clear();
        ToDoDBManager toDoDBManager = ToDoDBManager.open(context.getApplicationContext());
        category_items = toDoDBManager.selectAllCategory();
        for(int i=0 ; i<category_items.size() ; i++)
        {
            strings.add(category_items.get(i).getCategory_Name());
            Log.d("kmky", "loadCategory : Category_ID = " + category_items.get(i).getCategory_ID() + " " + category_items.get(i).getCategory_Name());
        }
        toDoDBManager.close();
    }

}
