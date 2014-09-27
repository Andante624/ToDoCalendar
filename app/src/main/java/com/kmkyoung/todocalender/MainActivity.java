package com.kmkyoung.todocalender;

import android.app.Activity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.kmkyoung.todocalender.Calender.Fragment_Calender;
import com.kmkyoung.todocalender.ToDoList.Fragment_ToDoList;
import com.kmkyoung.todocalender.Visualization.Fragment_Visualization;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, Fragment_Calender.OnFragmentInteractionListener, Fragment_ToDoList.OnFragmentInteractionListener, Fragment_Visualization.OnFragmentInteractionListener
{

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Fragment_Calender fragment_calender;
    private Fragment_ToDoList fragment_todolist;
    private Fragment_Visualization fragment_visualization;
    private int pre_frgment_count = 0;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment_calender = new Fragment_Calender();
        fragment_todolist = new Fragment_ToDoList();
        fragment_visualization = new Fragment_Visualization();


        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment_calender)
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        if(pre_frgment_count != position) {
            switch (position) {
                case 0:
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment_calender)
                            .commit();
                    break;
                case 1:
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment_todolist)
                            .commit();
                    break;
                case 2:
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment_visualization)
                            .commit();
                    break;

            }
        }
        pre_frgment_count = position;

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
            getMenuInflater().inflate(R.menu.calender, menu);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
