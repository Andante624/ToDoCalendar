package com.kmkyoung.todocalendar.Setting;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kmkyoung.todocalendar.DataManage.DB.DBManager;
import com.kmkyoung.todocalendar.R;
import com.kmkyoung.todocalendar.Utils;

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
    private final String[] color_strings = new String[]{"Blue","Green","Yellow","Orange","Red","Black"};
    private OnFragmentInteractionListener mListener;
    private ListView setting_listview;
    private Fragment_Setting_Information fragment_setting_information;

    public static Fragment_Setting newInstance(String param1, String param2) {
        Fragment_Setting fragment = new Fragment_Setting();
        return fragment;
    }
    public Fragment_Setting() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment_setting_information = new Fragment_Setting_Information();
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
                add("데이터 삭제");
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

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = View.inflate(getActivity().getApplicationContext(),R.layout.setting_listview_item,null);

            TextView textview = (TextView)convertView.findViewById(R.id.setting_listview_textview);
            textview.setText(setting_strings.get(position));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    switch (position) {
                        case 0:
                            showDialog_Category();
                            break;
                        case 1:
                            showDialog_DeleteDB();
                            break;
                        case 2:
                            showDialog_Colorpicker();
                            break;
                        case 3:
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container, fragment_setting_information)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                    }
                }
            });

            return convertView;
        }
    }

    public void showDialog_Colorpicker()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("테마 색상 선택");
        builder.setSingleChoiceItems(color_strings, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                ActionBar actionBar = getActivity().getActionBar();
//                actionBar.setBackgroundDrawable(new ColorDrawable(Utils.getColorId(which)));
//                actionBar.setDisplayShowTitleEnabled(false);
            }
        })
        .setPositiveButton("확인",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("Setting",context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("BackGroundColor",position);
//                editor.commit();
                dialog.dismiss();
            }
        })
        .setNegativeButton("취소",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();

//        Dialog_Setting_ColorPicker dialog_setting_colorPicker = new Dialog_Setting_ColorPicker(getActivity());
//        WindowManager.LayoutParams params = dialog_setting_colorPicker.getWindow().getAttributes();
//        params.height = 600;
//        dialog_setting_colorPicker.getWindow().setAttributes(params);
//        dialog_setting_colorPicker.show();
    }

    public void showDialog_Category()
    {
        Dialog_Setting_Category dialog_setting_category = new Dialog_Setting_Category(getActivity());
        WindowManager.LayoutParams params = dialog_setting_category.getWindow().getAttributes();
        params.height = 600;
        dialog_setting_category.getWindow().setAttributes(params);
        dialog_setting_category.show();
    }

    public void showDialog_DeleteDB()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("주의!");
        alertDialog.setMessage("ToDo 아이템과 카테고리를 모두 삭제하시겠습니까?");
        alertDialog.setPositiveButton("승인",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager dbManager = DBManager.open(getActivity().getApplicationContext());
                dbManager.delete_AllData();
                dbManager.close();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("취소",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

}
