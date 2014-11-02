package com.kmkyoung.todocalendar.Setting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
        category_listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(adapter.getCount() == position+1) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(context);
                    ab.setTitle("카테고리 추가");
                    ab.setMessage("추가할 카테고리를 입력해주세요.");
                    final EditText input_category = new EditText(context);
                    ab.setView(input_category);
                    ab.setPositiveButton("확인", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insertCategory(input_category.getText().toString());
                            invalidateOptionsMenu();
                        }
                    });
                    ab.setNegativeButton("취소",new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ab.show();
                }
            }
        });
    }

    public void loadCategory()
    {
        ToDoDBManager toDoDBManager = ToDoDBManager.open(context.getApplicationContext());
        toDoDBManager.selectAllCategory(category_items,strings);
        toDoDBManager.close();

        strings.remove(new String("없음"));
        strings.add("추가하기");
    }

    public void insertCategory(String new_category_name)
    {
        ToDoDBManager toDoDBManager = ToDoDBManager.open(context);
        int newId = toDoDBManager.insertCategory(new_category_name);
        if(newId != -1)
        {
            Category_Item new_Category_item = new Category_Item(newId,new_category_name);
            category_items.add(new_Category_item);
            strings.add(new_category_name);
        }
        toDoDBManager.close();

    }

}
