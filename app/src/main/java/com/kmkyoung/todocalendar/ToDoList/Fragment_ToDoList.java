package com.kmkyoung.todocalendar.ToDoList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.kmkyoung.todocalendar.R;

import java.util.List;


public class Fragment_ToDoList extends Fragment {
    private Spinner spinner_parents, spinner_child;
    private ListView todo_listview;
    private ToDo_ListViewAdapter toDo_listViewAdapter;
    private SpinnerAdapter_ToDo_parents spinnerAdapter_todo_parents;
    private SpinnerAdapter_ToDo_child spinnerAdapter_todo_child;
    private OnFragmentInteractionListener mListener;

    public static Fragment_ToDoList newInstance(String param1, String param2) {
        Fragment_ToDoList fragment = new Fragment_ToDoList();

        return fragment;
    }
    public Fragment_ToDoList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toDo_listViewAdapter = new ToDo_ListViewAdapter();
        toDo_listViewAdapter.setFragment(this);
        toDo_listViewAdapter.setContext(getActivity());
        spinnerAdapter_todo_parents = new SpinnerAdapter_ToDo_parents();
        spinnerAdapter_todo_child = new SpinnerAdapter_ToDo_child();
        spinnerAdapter_todo_parents.setContext(getActivity().getApplicationContext());
        spinnerAdapter_todo_parents.setItems();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        setLayout(view);

        return view;
    }

    public void setLayout(View view)
    {
        todo_listview = (ListView)view.findViewById(R.id.todolist_todolist);
        spinner_parents = (Spinner)view.findViewById(R.id.todolist_parents_spinner);
        spinner_child = (Spinner)view.findViewById(R.id.todolist_child_spinner);
        toDo_listViewAdapter.setListview(todo_listview);

        spinner_parents.setAdapter(spinnerAdapter_todo_parents);
        spinner_child.setAdapter(spinnerAdapter_todo_child);
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
