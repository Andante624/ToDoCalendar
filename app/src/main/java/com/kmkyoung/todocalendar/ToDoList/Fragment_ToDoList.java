package com.kmkyoung.todocalendar.ToDoList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.kmkyoung.todocalendar.DataManage.DB.ToDoDBManager;
import com.kmkyoung.todocalendar.R;

import java.util.List;


public class Fragment_ToDoList extends Fragment implements AdapterView.OnItemSelectedListener {
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
        init();
    }

    public void init()
    {
        toDo_listViewAdapter = new ToDo_ListViewAdapter();
        toDo_listViewAdapter.setFragment(this);
        toDo_listViewAdapter.setContext(getActivity());

        spinnerAdapter_todo_parents = new SpinnerAdapter_ToDo_parents();
        spinnerAdapter_todo_parents.setContext(getActivity().getApplicationContext());
        spinnerAdapter_todo_parents.setItems();

        spinnerAdapter_todo_child = new SpinnerAdapter_ToDo_child();
        spinnerAdapter_todo_child.setContext(getActivity().getApplicationContext());
        spinnerAdapter_todo_child.setItems();
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
        todo_listview.setAdapter(toDo_listViewAdapter);

        toDo_listViewAdapter.setListview(todo_listview);

        spinner_parents = (Spinner)view.findViewById(R.id.todolist_parents_spinner);
        spinner_parents.setAdapter(spinnerAdapter_todo_parents);
        spinner_parents.setOnItemSelectedListener(this);

        spinner_child = (Spinner)view.findViewById(R.id.todolist_child_spinner);
        spinner_child.setAdapter(spinnerAdapter_todo_child);
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
        switch (parent.getId()) {
            case R.id.todolist_parents_spinner:
                switch (position) {
                    case 0:
                        spinnerAdapter_todo_child.setSelect(true);
                        spinner_child.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        spinnerAdapter_todo_child.setSelect(true);
                        spinner_child.setVisibility(View.VISIBLE);
                        break;
                    case 2:
//                        requestToDoItems(2,0);
                        spinner_child.setVisibility(View.INVISIBLE);
                        todo_listview.invalidateViews();
                        break;
                    case 3:
                        spinnerAdapter_todo_child.setSelect(false);
                        spinner_child.setVisibility(View.VISIBLE);
                        break;
                    case 4:
//                        requestToDoItems(4,0);
                        spinner_child.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
//                        requestToDoItems(5,0);
                        spinner_child.setVisibility(View.INVISIBLE);
                        break;
                }
                break;
            case R.id.todolist_child_spinner:
                break;
        }
    }

    public void requestToDoItems(int where, String condition)
    {
        ToDoDBManager toDoDBManager = ToDoDBManager.open(getActivity().getApplicationContext());
        toDo_listViewAdapter.setTodolist_items(toDoDBManager.select_ToDoItems(where,condition));
        toDoDBManager.close();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
