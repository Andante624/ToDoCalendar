package com.kmkyoung.todocalender.Calender;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kmkyoung.todocalender.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmkyoung on 2014. 9. 27..
 */
public class Calender_GridViewAdapter extends BaseAdapter {
    Context context;
    List<Canlender_Item> canlender_items = new ArrayList<Canlender_Item>();

    public void setContext(Context input_context)
    {
        context = input_context;
    }

    public void add(Canlender_Item newItem)
    {
        canlender_items.add(newItem);
    }

    @Override
    public int getCount() {
        return canlender_items.size();
    }

    @Override
    public Canlender_Item getItem(int position) {
        return canlender_items.get(position);
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
        textView.setText(position+"");
        return convertView;
    }
}
