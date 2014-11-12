package com.kmkyoung.todocalendar.Visualization;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.kmkyoung.todocalendar.DataManage.DB.DBManager;
import com.kmkyoung.todocalendar.R;


public class Fragment_Visualization extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_parent, spinner_child;
    private SpinnerAdapter_Visual_Parent spinnerAdapter_visual_parent;
    private SpinnerAdapter_Visual_Child spinnerAdapter_visual_child;
    private OnFragmentInteractionListener mListener;

    public static Fragment_Visualization newInstance() {
        Fragment_Visualization fragment = new Fragment_Visualization();
        return fragment;
    }
    public Fragment_Visualization() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init()
    {
        spinnerAdapter_visual_parent = new SpinnerAdapter_Visual_Parent();
        spinnerAdapter_visual_parent.setContext(getActivity().getApplicationContext());
        spinnerAdapter_visual_parent.setItems();

        spinnerAdapter_visual_child = new SpinnerAdapter_Visual_Child();
        spinnerAdapter_visual_child.setContext(getActivity().getApplicationContext());
        spinnerAdapter_visual_child.setItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualization, container, false);
        setLayout(view);
        spinnerAdapter_visual_child.updateCategory();

        return view;
    }

    public void setLayout(View view)
    {
        spinner_parent = (Spinner)view.findViewById(R.id.visualization_parent_spinner);
        spinner_parent.setAdapter(spinnerAdapter_visual_parent);
        spinner_parent.setOnItemSelectedListener(this);

        spinner_child = (Spinner)view.findViewById(R.id.visualization_child_spinner);
        spinner_child.setAdapter(spinnerAdapter_visual_child);
        spinner_child.setOnItemSelectedListener(this);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId())
        {
            case R.id.visualization_parent_spinner:
                switch (position)
                {
                    case 0:
                        spinner_child.setVisibility(View.INVISIBLE);
                        requestToDoCount(DBManager.WHERE_ALL_COUNT,null);
                        break;
                    case 1:
                        spinnerAdapter_visual_child.setSelect(1);
                        spinner_child.setVisibility(View.VISIBLE);
                        spinner_child.setSelection(0);
                        spinnerAdapter_visual_child.notifyDataSetChanged();
                        break;
                    case 2:
                        spinnerAdapter_visual_child.setSelect(0);
                        spinner_child.setVisibility(View.VISIBLE);
                        spinner_child.setSelection(0);
                        spinnerAdapter_visual_child.notifyDataSetChanged();
                        break;
                }
                break;
            case R.id.visualization_child_spinner:
                if(spinnerAdapter_visual_child.getSelect()==1) //category
                {
                    String category_title = spinnerAdapter_visual_child.getItem(position);
                    requestToDoCount(DBManager.WHERE_COMPARISION_CATEGORY_COUNT,category_title);
                }
                else
                {
                    requestToDoCount(DBManager.WHERE_COMPARISION_IMPORTANCE_COUNT,position+"");
                }
                break;

        }

    }

    public void requestToDoCount(int where, String condition)
    {
        DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
        int completedCount = dbManager.count_ToDoItems(where,condition,true);
        int uncompletedCount = dbManager.count_ToDoItems(where,condition,false);
        Log.d("kmky","completed :"+completedCount);
        Log.d("kmky","uncompleted :"+uncompletedCount);
        dbManager.close();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
