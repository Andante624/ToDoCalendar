package com.andante624.todocalendar.Setting;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andante624.todocalendar.R;
import com.andante624.todocalendar.Utils;

public class Fragment_Setting_Information extends Fragment {
    private TextView versiontext, versionvalue, updatetext, updatevalue, madeby;
    private OnFragmentInteractionListener mListener;

    public static Fragment_Setting_Information newInstance(String param1, String param2) {
        Fragment_Setting_Information fragment = new Fragment_Setting_Information();
        return fragment;
    }
    public Fragment_Setting_Information() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_information, container, false);
        versiontext = (TextView)view.findViewById(R.id.setting_information_versiontext);
        versionvalue = (TextView)view.findViewById(R.id.setting_information_versionvalue);;
        updatetext = (TextView)view.findViewById(R.id.setting_information_updatetext);;
        updatevalue = (TextView)view.findViewById(R.id.setting_information_updatevalue);;
        madeby = (TextView)view.findViewById(R.id.setting_information_madeby);;

        versiontext.setTextColor(getActivity().getResources().getColor(Utils.getBackgroundDarkColorID(getActivity())));
        versionvalue.setTextColor(getActivity().getResources().getColor(Utils.getBackgroundDarkColorID(getActivity())));
        updatetext.setTextColor(getActivity().getResources().getColor(Utils.getBackgroundDarkColorID(getActivity())));
        updatevalue.setTextColor(getActivity().getResources().getColor(Utils.getBackgroundDarkColorID(getActivity())));
        madeby.setTextColor(getActivity().getResources().getColor(Utils.getBackgroundDarkColorID(getActivity())));


        return view;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
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

}
