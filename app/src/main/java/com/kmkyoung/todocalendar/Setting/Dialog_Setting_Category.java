package com.kmkyoung.todocalendar.Setting;

import android.app.Dialog;
import android.content.Context;
import android.widget.ListView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.DataManage.DB.DBManager;
import com.kmkyoung.todocalendar.R;

import java.util.List;

/**
 * Created by MinchaeiMac on 14. 11. 2..
 */
public class Dialog_Setting_Category extends Dialog {
    private Context context;
    private ListView category_listview;
    private Adapter_Setting_Category adapter_setting_category = new Adapter_Setting_Category();


    public Dialog_Setting_Category(final Context context) {
        super(context);
        this.context = context;
        setTitle("카테고리 목록");
        setContentView(R.layout.dialog_setting_category);
        loadCategory();

        category_listview = (ListView)findViewById(R.id.setting_category_dialoglist);
        category_listview.setAdapter(adapter_setting_category);
        adapter_setting_category.setListView(category_listview);
        adapter_setting_category.setContext(context);
    }

    public void loadCategory()
    {
        DBManager dbManager = DBManager.open(context.getApplicationContext());
        List<Category_Item> items = dbManager.select_AllCategoryItem();
        adapter_setting_category.setCategorys(items);
        dbManager.close();
    }

}
