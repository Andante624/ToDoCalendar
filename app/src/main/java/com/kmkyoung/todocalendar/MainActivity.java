package com.kmkyoung.todocalendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.kmkyoung.todocalendar.Calendar.Fragment_Calendar;
import com.kmkyoung.todocalendar.DataManage.Fragment_AddToDoItem;
import com.kmkyoung.todocalendar.Setting.Fragment_Setting;
import com.kmkyoung.todocalendar.Setting.Fragment_Setting_Information;
import com.kmkyoung.todocalendar.ToDoList.Fragment_ToDoList;
import com.kmkyoung.todocalendar.Visualization.Fragment_Visualization;


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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_default, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
