package com.andante624.todocalendar.Calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.andante624.todocalendar.R;
import com.andante624.todocalendar.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Calendar_GridViewAdapter extends BaseAdapter {
    Context context;
    List<Calendar_Item> canlendar_items = new ArrayList<Calendar_Item>();

    public void setContext(Context input_context)
    {
        context = input_context;
    }

    public void add(Calendar_Item newItem)
    {
        canlendar_items.add(newItem);
    }

    @Override
    public int getCount() {
        return canlendar_items.size();
    }

    @Override
    public Calendar_Item getItem(int position) {
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

        TextView textView = (TextView) convertView.findViewById(R.id.calender_item_date);

        if(!canlendar_items.get(position).isBlank())
            textView.setText(canlendar_items.get(position).getDay() + "");
        else
            textView.setText(" ");

        convertView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60));
        return convertView;
    }

    public void removeAllItems()
    {
        canlendar_items.clear();
    }
}
