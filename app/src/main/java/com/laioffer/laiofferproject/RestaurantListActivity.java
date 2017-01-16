package com.laioffer.laiofferproject;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListFragment.OnItemSelectListener//, RestaurantGridFragment.OnItemSelectListener
{

    RestaurantListFragment listFragment;
    RestaurantMapFragment mapFragment;
/*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_restaurant_list_land);
            getSupportFragmentManager().beginTransaction().hide(listFragment).commit();
            getSupportFragmentManager().beginTransaction().show(mapFragment).commit();
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_restaurant_list);
            getSupportFragmentManager().beginTransaction().hide(listFragment).commit();
            getSupportFragmentManager().beginTransaction().show(mapFragment).commit();
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_restaurant_list_land);
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_restaurant_list);
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        if (savedInstanceState == null) {
            listFragment = new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container, listFragment, "list_tag").commit();
            mapFragment = new RestaurantMapFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_map_container, mapFragment, "map_tag").commit();
        } else {
            //Toast.makeText(this, "find hidden instance", Toast.LENGTH_SHORT).show();
            listFragment = (RestaurantListFragment) getSupportFragmentManager().findFragmentByTag("list_tag");
            mapFragment = (RestaurantMapFragment) getSupportFragmentManager().findFragmentByTag("map_tag");
        }
        //add list view
        //if (isTablet()) {





        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                YelpApi yelp = new YelpApi();
                yelp.searchForBusinessesByLocation("dinner", "San Francisco, CA", 20);
                return null;
            }
        }.execute();

/*
        if (findViewById(R.id.fragment_list_container) != null) {
            listFragment =  new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_list_container, listFragment).commit();
        }

        if (findViewById(R.id.fragment_map_container) != null) {
            mapFragment =  new RestaurantMapFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_map_container, mapFragment).commit();
        }

*/



        //}

        //add Gridview

    }

    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }

    @Override
    public void onItemSelectedList(String lat_lng){
        mapFragment.onItemSelected(lat_lng);
    }

    /*@Override
    public void onItemSelectedGrid(int position){
        listFragment.onItemSelected(position);
    }*/
}

