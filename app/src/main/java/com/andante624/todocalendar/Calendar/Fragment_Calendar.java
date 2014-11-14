package com.andante624.todocalendar.Calendar;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andante624.todocalendar.DataManage.DB.DBManager;
import com.andante624.todocalendar.DataManage.DB.ToDo_Item;
import com.andante624.todocalendar.R;
import com.andante624.todocalendar.ToDoList.ToDo_ListViewAdapter;
import com.andante624.todocalendar.Utils;

import java.util.Calendar;
import java.util.List;


public class Fragment_Calendar extends Fragment implements View.OnClickListener, GridView.OnItemClickListener{
    public static int[] array_rectangle = new int[]{
            R.drawable.round_rectangle_blue, R.drawable.round_rectangle_green, R.drawable.round_rectangle_yellow,
            R.drawable.round_rectangle_orange, R.drawable.round_rectangle_red, R.drawable.round_rectangle_black
    };
    //calendar
    private GridView calendar_gridView;
    private Calendar_GridViewAdapter calendar_gridViewAdapter;
    private TextView calendar_month;
    private ImageView calendar_pre_button, calendar_next_button;
    private int selected_year, selected_month, selected_day;
    private OnFragmentInteractionListener mListener;
    private LinearLayout calendar_layout;

    //todolist
    private ListView todo_listview;
    private ToDo_ListViewAdapter todo_listViewAdapter;

    public static Fragment_Calendar newInstance() {
        Fragment_Calendar fragment = new Fragment_Calendar();
        return fragment;
    }
    public Fragment_Calendar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getActivity().getApplicationContext();
        calendar_gridViewAdapter = new Calendar_GridViewAdapter();
        calendar_gridViewAdapter.setContext(context);
        todo_listViewAdapter = new ToDo_ListViewAdapter();
        todo_listViewAdapter.setContext(getActivity());
        todo_listViewAdapter.setFragment(this);

        for(int i=0 ; i<10 ; i++)
            todo_listViewAdapter.add();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        setLayout(view);
        calendar_gridView.setAdapter(calendar_gridViewAdapter);
        todo_listview.setAdapter(todo_listViewAdapter);
        todo_listViewAdapter.setListview(todo_listview);
        getToday();
        setListener();

        return view;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendar_layout.setBackgroundResource(array_rectangle[Utils.getBackgroundPosition(getActivity())]);

    }

    public void getToday()
    {
        Calendar calendar = Calendar.getInstance();
        selected_year = calendar.get(Calendar.YEAR);
        selected_month = calendar.get(Calendar.MONTH)+1;
        selected_day= calendar.get(Calendar.DAY_OF_MONTH);

        drawCalendar(selected_year, selected_month, selected_day);

        drawToDoList(Utils.getStringToday());
    }

    public void drawCalendar(int year, int month, int day)
    {
        calendar_gridViewAdapter.removeAllItems();

        for(int i = 1; i < Utils.getFirstWeek(year, month-1); i++)
            calendar_gridViewAdapter.add(new Calendar_Item(true));
        for(int i = 1; i<= Utils.getLastWeek(year, month-1, day) ; i++)
        {
            String date = Utils.getStringDate(selected_year,selected_month,i);
            calendar_gridViewAdapter.add(new Calendar_Item(i, date));
        }
        calendar_month.setText(selected_year + "년" + selected_month  + "월");
        calendar_gridView.invalidateViews();
    }

    public void setListener()
    {
        calendar_next_button.setOnClickListener(this);
        calendar_pre_button.setOnClickListener(this);
        calendar_gridView.setOnItemClickListener(this);
    }

    public void setLayout(View view)
    {
        //calendar layout
        calendar_layout = (LinearLayout)view.findViewById(R.id.calendar_layout);
        calendar_gridView = (GridView)view.findViewById(R.id.calender_gridview);
        calendar_month = (TextView)view.findViewById(R.id.calender_month);
        calendar_pre_button = (ImageView)view.findViewById(R.id.calender_pre_button);
        calendar_next_button = (ImageView)view.findViewById(R.id.calender_next_button);

        //todolist layout
        todo_listview = (ListView)view.findViewById(R.id.calender_todolist);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.calender_next_button:
                updateMonthCalender(true);
                break;
            case R.id.calender_pre_button:
                updateMonthCalender(false);
                break;
        }
    }

    public void updateMonthCalender(boolean nextMonth) {
        if (nextMonth) {
            selected_month = (selected_month == 12) ? 1 : selected_month + 1;
            if (selected_month == 1) selected_year++;
            drawToDoList(Utils.getStringDate(selected_year,selected_month,selected_day));
        } else {
            selected_month = (selected_month == 1) ? 12 : selected_month - 1;
            if (selected_month == 12) selected_year--;
            drawToDoList(Utils.getStringDate(selected_year,selected_month,selected_day));
        }

        drawCalendar(selected_year, selected_month, selected_day);

    }

    public void drawToDoList(String date)
    {
        DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
        List<ToDo_Item> items = dbManager.select_ToDoItems(dbManager.WHERE_MATCH_DEADLINE_DATE, date);
        dbManager.close();

        todo_listViewAdapter.setTodolist_items(items);
        todo_listview.invalidateViews();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        drawToDoList(calendar_gridViewAdapter.getItem(position).getDate());
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
