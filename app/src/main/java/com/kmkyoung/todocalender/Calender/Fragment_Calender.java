package com.kmkyoung.todocalender.Calender;

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

import com.kmkyoung.todocalender.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Calender.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Calender#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Fragment_Calender extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private Calender_GridViewAdapter calender_gridViewAdapter;
    private GridView calender_gridView;
    private TextView calender_month;
    private Button calender_pre_button, calender_next_button;
    private ListView calender_checklist;

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
    public static Fragment_Calender newInstance(String param1, String param2) {
        Fragment_Calender fragment = new Fragment_Calender();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment_Calender() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        calender_gridViewAdapter = new Calender_GridViewAdapter();

        calender_gridViewAdapter.setContext(getActivity().getApplicationContext());
        for(int i = 0; i<30 ; i++)
            calender_gridViewAdapter.add(new Canlender_Item("test"+i,i+"",i+"",i));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);

        setLayout(view);
        calender_gridView.setAdapter(calender_gridViewAdapter);


        return view;
    }

    public void setLayout(View view)
    {
        calender_gridView = (GridView)view.findViewById(R.id.calender_gridview);
        calender_month = (TextView)view.findViewById(R.id.calender_month);
        calender_pre_button = (Button)view.findViewById(R.id.calender_pre_botton);
        calender_next_button = (Button)view.findViewById(R.id.calender_next_botton);
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
