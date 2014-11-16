package com.andante624.todocalendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.andante624.todocalendar.Calendar.Fragment_Calendar;
import com.andante624.todocalendar.DataManage.Fragment_AddToDoItem;
import com.andante624.todocalendar.Setting.Fragment_Setting;
import com.andante624.todocalendar.Setting.Fragment_Setting_Information;
import com.andante624.todocalendar.ToDoList.Fragment_ToDoList;
import com.andante624.todocalendar.Visualization.Fragment_Visualization;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, Fragment_Calendar.OnFragmentInteractionListener, Fragment_ToDoList.OnFragmentInteractionListener,
        Fragment_Visualization.OnFragmentInteractionListener, Fragment_AddToDoItem.OnFragmentInteractionListener,Fragment_Setting.OnFragmentInteractionListener,
        Fragment_Setting_Information.OnFragmentInteractionListener
{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private FragmentManager fragmentManager;
    private Fragment_Calendar fragment_calendar;
    private Fragment_ToDoList fragment_todolist;
    private Fragment_Visualization fragment_visualization;
    private Fragment_Setting fragment_setting;

    private CharSequence mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragment_calendar = new Fragment_Calendar();
        fragment_todolist = new Fragment_ToDoList();
        fragment_visualization = new Fragment_Visualization();
        fragment_setting = new Fragment_Setting();

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment_calendar)
                .commit();

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(Utils.getColorId(Utils.getBackgroundPosition(this)))));
        actionBar.setDisplayShowTitleEnabled(false);
        invalidateOptionsMenu();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_calendar)
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_todolist)
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_visualization)
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment_setting)
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(Utils.getColorId(Utils.getBackgroundPosition(this)))));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.menu_default, menu);
            ActionBar actionBar = getActionBar();
            restoreActionBar();
            return true;
        }
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(Utils.getColorId(Utils.getBackgroundPosition(this)))));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_addtodo) {
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new Fragment_AddToDoItem()).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
