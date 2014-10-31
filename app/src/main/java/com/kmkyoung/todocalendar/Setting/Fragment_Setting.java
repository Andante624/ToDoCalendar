package com.kmkyoung.todocalendar.Setting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.Category_Item;
import com.kmkyoung.todocalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Setting#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Fragment_Setting extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ListView setting_listview;

    public static Fragment_Setting newInstance(String param1, String param2) {
        Fragment_Setting fragment = new Fragment_Setting();
        return fragment;
    }
    public Fragment_Setting() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        setting_listview = (ListView)view.findViewById(R.id.setting_listview);
        Setting_Listview_Adapter setting_listview_adapter = new Setting_Listview_Adapter();
        setting_listview.setAdapter(setting_listview_adapter);
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class Setting_Listview_Adapter extends BaseAdapter
    {
        List<String> setting_strings = new ArrayList<String>(){
            {
                add("카테고리 관리");
                add("To Do 삭제");
                add("테마 관리");
                add("버전 정보");
            }
        };

        @Override
        public int getCount() {
            return setting_strings.size();
        }

        @Override
        public Object getItem(int position) {
            return setting_strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void newItem(Category_Item newItem)
        {

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = View.inflate(getActivity().getApplicationContext(),R.layout.setting_listview_item,null);

            TextView textview = (TextView)convertView.findViewById(R.id.setting_listview_menu);
            textview.setText(setting_strings.get(position));

            return convertView;
        }
    }

}
