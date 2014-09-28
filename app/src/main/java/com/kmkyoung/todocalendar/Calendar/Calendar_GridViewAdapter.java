package com.kmkyoung.todocalendar.Calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Calendar_GridViewAdapter extends BaseAdapter {
    Context context;
    List<Canlendar_Item> canlendar_items = new ArrayList<Canlendar_Item>();

    public void setContext(Context input_context)
    {
        context = input_context;
    }

    public void add(Canlendar_Item newItem)
    {
        canlendar_items.add(newItem);
    }

    @Override
    public int getCount() {
        return canlendar_items.size();
    }

    @Override
    public Canlendar_Item getItem(int position) {
        return canlendar_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = View.inflate(context,R.layout.gridview_item,null);

        TextView textView = (TextView)convertView.findViewById(R.id.calender_item_date);
        textView.setText(canlendar_items.get(position).getDay()+"");
        return convertView;
    }

    public void removeAllItems()
    {
        canlendar_items.clear();
    }
}
