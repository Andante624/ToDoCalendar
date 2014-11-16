package com.andante624.todocalendar.Setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.andante624.todocalendar.DataManage.DB.Category_Item;
import com.andante624.todocalendar.DataManage.DB.DBManager;
import com.andante624.todocalendar.R;

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
        View view = View.inflate(context, R.layout.setting_listview_item, null);

        TextView textView = (TextView) view.findViewById(R.id.setting_listview_textview);
        textView.setText(category_items.get(position).getCategory_Name());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category_items.size() - 1 == position ) {
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
                    ab.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ab.show();
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                    alertdialog.setTitle("Category 삭제");
                    alertdialog.setMessage("Category 를 삭제하시겠습니까?");
                    alertdialog.setPositiveButton("승인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DBManager dbManager = DBManager.open(context.getApplicationContext());
                            dbManager.delete_CategoryItem(category_items.get(position).getCategory_ID());
                            dbManager.close();
                            category_items.remove(position);
                            dialog.dismiss();
                            listView.invalidateViews();
                        }
                    });
                    alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertdialog.show();
                    return true;
                }
            });

        if (category_items.get(position).getCategory_Name().equals("추가하기"))
            view.setLongClickable(false);

        view.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));

            return view;
    }

    public void setCategorys(List<Category_Item> items)
    {
        category_items.clear();
        category_items = items;
        for(int i = 0; i<category_items.size() ; i++)
            if(category_items.get(i).getCategory_Name().equals("없음")) category_items.remove(i);

        category_items.add(new Category_Item(0,"추가하기"));
    }

    public void insertCategory(String new_category_name)
    {
        category_items.remove(category_items.size()-1);
        DBManager dbManager = DBManager.open(context);
        int newId = dbManager.insert_CategoryItem(new_category_name);
        if(newId != -1)
        {
            add(new Category_Item(newId,new_category_name));
        }
        dbManager.close();

        category_items.add(new Category_Item(0,"추가하기"));
        listView.invalidateViews();
    }


}
