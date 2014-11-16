package com.andante624.todocalendar.DataManage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andante624.todocalendar.DataManage.DB.DBManager;
import com.andante624.todocalendar.R;
import com.andante624.todocalendar.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinchaeiMac on 14. 11. 5..
 */
public class SpinnerAdapter_AddToDo extends BaseAdapter{
    Context context;
    private List<String> strings = new ArrayList<String>();

    public void setContext(Context context) { this.context = context;}
    public void setCategory()
    {
        DBManager dbManager = DBManager.open(context.getApplicationContext());
        strings = dbManager.select_AllCategoryItem_Strings();
        dbManager.close();
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public String getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.spinner_item, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTextColor(context.getResources().getColor(Utils.getBackgroundId(context)));
        textView.setTextSize(14);
        textView.setText(strings.get(position));
        return view;
    }

    public int getSelectPosition(String title)
    {
        for(int i= 0 ; i<strings.size() ; i++)
        {
            if(strings.get(i).equals(title))
                return i;
        }

        return 0;
    }

}
