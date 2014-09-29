package com.kmkyoung.todocalendar.Calendar;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Calendar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Calendar#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Fragment_Calendar extends Fragment implements View.OnClickListener{
    private Calendar_GridViewAdapter calendar_gridViewAdapter;
    private GridView calendar_gridView;
    private TextView calendar_month;
    private Button calendar_pre_button, calendar_next_button;
    private ListView calendar_checklist;
    private int selected_year, selected_month, selected_day;
    private OnFragmentInteractionListener mListener;

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
        calendar_gridViewAdapter = new Calendar_GridViewAdapter();
        calendar_gridViewAdapter.setContext(getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        setLayout(view);
        calendar_gridView.setAdapter(calendar_gridViewAdapter);
        getToday();
        setListener();

        return view;
    }

    public void getToday()
    {
        Calendar calendar = Calendar.getInstance();
        selected_year = calendar.get(Calendar.YEAR);
        selected_month = calendar.get(Calendar.MONTH);
        selected_day= calendar.get(Calendar.DAY_OF_MONTH);

        calendar_month.setText(selected_year+"년"+(selected_month+1)+"월");
        drawCalendar(selected_year, selected_month, selected_day);
    }

    public void drawCalendar(int year, int month, int day)
    {
        calendar_gridViewAdapter.removeAllItems();

        for(int i = 1; i < Calendar_Utils.getFirstWeek(year,month); i++)
            calendar_gridViewAdapter.add(new Calendar_Item(" ","-1"," ",-1," "));
        for(int i = 1; i<=Calendar_Utils.getLastWeek(year,month,day) ; i++)
            calendar_gridViewAdapter.add(new Calendar_Item("test"+i,i+"",i+"",5," "));

    }

    public void setListener()
    {
        calendar_next_button.setOnClickListener(this);
        calendar_pre_button.setOnClickListener(this);
    }

    public void setLayout(View view)
    {
        calendar_gridView = (GridView)view.findViewById(R.id.calender_gridview);
        calendar_month = (TextView)view.findViewById(R.id.calender_month);
        calendar_pre_button = (Button)view.findViewById(R.id.calender_pre_button);
        calendar_next_button = (Button)view.findViewById(R.id.calender_next_button);
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

    public void updateMonthCalender(boolean nextMonth)
    {
        if(nextMonth)
        {
            selected_month = (selected_month == 11) ? 0 : selected_month + 1;
            if(selected_month == 0) selected_year++;
        }
        else
        {
            selected_month = (selected_month == 0) ? 11 : selected_month - 1;
            if(selected_month == 11) selected_year--;
        }

        drawCalendar(selected_year,selected_month,selected_day);
        calendar_month.setText(selected_year+"년"+(selected_month+1)+"월");
        calendar_gridView.invalidateViews();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
