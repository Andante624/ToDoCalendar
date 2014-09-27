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
public class Fragment_Calendar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Calender_GridViewAdapter calendar_gridViewAdapter;
    private GridView calendar_gridView;
    private TextView calendar_month;
    private Button calendar_pre_button, calendar_next_button;
    private ListView calendar_checklist;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Calender.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Calendar newInstance(String param1, String param2) {
        Fragment_Calendar fragment = new Fragment_Calendar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment_Calendar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        calendar_gridViewAdapter = new Calender_GridViewAdapter();

        calendar_gridViewAdapter.setContext(getActivity().getApplicationContext());
        for(int i = 0; i<30 ; i++)
            calendar_gridViewAdapter.add(new Canlendar_Item("test"+i,i+"",i+"",i));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);

        setLayout(view);
        calendar_gridView.setAdapter(calendar_gridViewAdapter);


        return view;
    }

    public void getToday()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setLayout(View view)
    {
        calendar_gridView = (GridView)view.findViewById(R.id.calender_gridview);
        calendar_month = (TextView)view.findViewById(R.id.calender_month);
        calendar_pre_button = (Button)view.findViewById(R.id.calender_pre_botton);
        calendar_next_button = (Button)view.findViewById(R.id.calender_next_botton);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
