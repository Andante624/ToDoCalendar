package com.kmkyoung.todocalendar.DataManage;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kmkyoung.todocalendar.DataManage.DB.DBManager;
import com.kmkyoung.todocalendar.DataManage.DB.ToDo_Item;
import com.kmkyoung.todocalendar.R;
import com.kmkyoung.todocalendar.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_AddToDoItem extends Fragment implements View.OnClickListener{
    private OnFragmentInteractionListener mListener;
    private EditText title_editview;
    private TextView date_textview;
    private Spinner category_spinner;
    private RatingBar importance_ratingbar;
    private Button ok_button, cancel_button;

    private Boolean editmode = false;
    private int todo_id = -1;
    private ToDo_Item editItem;

    private int get_deadline_year=0, get_deadline_month=0, get_deadline_day=0;
    private String get_title, get_category, get_deadline_date;
    private float get_importance;

    private List<String> strings = new ArrayList<String>();

    public static Fragment_AddToDoItem newInstance(int todo_id) {
        Fragment_AddToDoItem fragment_addToDoItem = new Fragment_AddToDoItem();
        Bundle bundle = new Bundle();
        bundle.putInt("ToDo_ID", todo_id);
        fragment_addToDoItem.setArguments(bundle);
        return fragment_addToDoItem;
    }

    public Fragment_AddToDoItem() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            editmode = true;
            todo_id = getToDoID();
            DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
            editItem = dbManager.select_ToDoItem(todo_id);
            dbManager.close();
        }

        DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
        strings = dbManager.select_AllCategoryItem_Strings();
        dbManager.close();
    }

    public int getToDoID()
    {
        return getArguments().getInt("ToDo_ID",-1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_to_do_item, container, false);
        setLayout(view);
        setListener();

        if(editmode)
        {
            title_editview.setText(editItem.getTitle());
            date_textview.setText(editItem.getDeadlineDate());
            DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
            String title = dbManager.get_CategoryTitle(editItem.getCategory());
            dbManager.close();
            for(int i= 0 ; i<strings.size() ; i++)
            {
                if(strings.get(i).equals(title))
                    category_spinner.setSelection(i);
            }
            importance_ratingbar.setRating(editItem.getInportance());
        }
        else
        {
            Calendar calendar = Calendar.getInstance();
            get_deadline_year = calendar.get(Calendar.YEAR);
            get_deadline_month = calendar.get(Calendar.MONTH)+1;
            get_deadline_day = calendar.get(Calendar.DAY_OF_MONTH);
            date_textview.setText(get_deadline_year + "-" + get_deadline_month + "-" + get_deadline_day);
        }

        return view;
    }

    public void setLayout(View view)
    {
        title_editview = (EditText)view.findViewById(R.id.add_todo_title);
        date_textview = (TextView)view.findViewById(R.id.add_todo_date);
        category_spinner = (Spinner)view.findViewById(R.id.add_todo_spinner);
        importance_ratingbar = (RatingBar)view.findViewById(R.id.add_todo_ratingbar);
        ok_button = (Button)view.findViewById(R.id.add_todo_ok);
        cancel_button = (Button)view.findViewById(R.id.add_todo_cancel);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,strings);

        category_spinner.setAdapter(adapter);

    }

    public void setListener()
    {
        date_textview.setOnClickListener(this);
        ok_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);
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
            case R.id.add_todo_date:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        get_deadline_year = year;
                        get_deadline_month = monthOfYear+1;
                        get_deadline_day = dayOfMonth;
                        date_textview.setText(get_deadline_year+"-"+get_deadline_month+"-"+get_deadline_day);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
                break;
            case R.id.add_todo_ok:
                if(editmode) {
                    if (editToDoItem())
                        getFragmentManager().popBackStack();
                }
                else {
                    if (saveToDoItem())
                        getFragmentManager().popBackStack();
                }
                break;
            case R.id.add_todo_cancel:
                getFragmentManager().popBackStack();
                break;
        }
    }


    public boolean saveToDoItem()
    {
        if(getInputData())
        {
            DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
            dbManager.insert_ToDoItem(get_title, get_deadline_date, get_category, get_importance);
            dbManager.close();
            return true;
        }
        return false;

    }

    public boolean getInputData()
    {
        get_title = title_editview.getText().toString();

        if(get_title.length() != 0)
        {
            get_deadline_date = Utils.getStringDate(get_deadline_year,get_deadline_month,get_deadline_day);
            get_category = category_spinner.getSelectedItem().toString();
            get_importance = importance_ratingbar.getRating();
            return true;
        }
        else
            Toast.makeText(getActivity().getApplicationContext(),"Title 을 입력해 주세요",Toast.LENGTH_SHORT).show();

        return false;
    }

    public boolean editToDoItem()
    {
        if(getInputData())
        {
            editItem.setTitle(get_title);
            editItem.setDeadlineDate(get_deadline_date);
            editItem.setInportance(get_importance);
            DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
            editItem.setCategory(dbManager.get_CategoryID(get_category));
            dbManager.update_ToDoItem(editItem);
            dbManager.close();
            return true;
        }
        return false;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type an d name
        public void onFragmentInteraction(Uri uri);
    }

}
