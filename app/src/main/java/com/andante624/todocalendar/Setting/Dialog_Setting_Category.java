package com.andante624.todocalendar.Setting;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.andante624.todocalendar.DataManage.DB.Category_Item;
import com.andante624.todocalendar.DataManage.DB.DBManager;
import com.andante624.todocalendar.R;

import java.util.List;

/**
 * Created by MinchaeiMac on 14. 11. 2..
 */
public class Dialog_Setting_Category extends Dialog {
    private Context context;
    private ListView category_listview;
    private Adapter_Setting_Category adapter_setting_category = new Adapter_Setting_Category();
    private Button setting_dialogbutton;


    public Dialog_Setting_Category(final Context context) {
        super(context);
        this.context = context;
        setTitle("카테고리 목록");
        setContentView(R.layout.dialog_setting_listview);
        loadCategory();

        category_listview = (ListView)findViewById(R.id.setting_dialoglist);
        category_listview.setAdapter(adapter_setting_category);
        adapter_setting_category.setListView(category_listview);
        adapter_setting_category.setContext(context);
        setting_dialogbutton = (Button)findViewById(R.id.setting_dialogbutton);
        setting_dialogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void loadCategory()
    {
        DBManager dbManager = DBManager.open(context.getApplicationContext());
        List<Category_Item> items = dbManager.select_AllCategoryItem();
        adapter_setting_category.setCategorys(items);
        dbManager.close();
    }

}
