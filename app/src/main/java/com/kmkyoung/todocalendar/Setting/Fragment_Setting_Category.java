package com.kmkyoung.todocalendar.Setting;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.DataManage.DB.ToDoDBManager;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Setting_Category.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Setting_Category#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Fragment_Setting_Category extends Fragment {
    private ListView category_listview;
    private Setting_Category_Adapter setting_category_adapter;
    private List<Category_Item> category_items;

    private OnFragmentInteractionListener mListener;

    public static Fragment_Setting_Category newInstance() {
        Fragment_Setting_Category fragment = new Fragment_Setting_Category();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category_items = new ArrayList<Category_Item>();
        setting_category_adapter = new Setting_Category_Adapter();

        loadCategory();
    }

    public void loadCategory()
    {
        ToDoDBManager toDoDBManager = ToDoDBManager.open(getActivity().getApplicationContext());
        category_items = toDoDBManager.selectAllCategory();
        if(category_items.isEmpty())
            category_items.add(new Category_Item(0,"없음"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_category, container, false);

        category_listview = (ListView)view.findViewById(R.id.setting_category_listview);
        category_listview.setAdapter(setting_category_adapter);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
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
                convertView = View.inflate(getActivity().getApplicationContext(),R.layout.setting_listview_item,null);

            TextView categorys = (TextView)convertView.findViewById(R.id.setting_listview_menu);
            categorys.setText(category_items.get(position).getCategory_Name());

            return convertView;
        }
    }

}
