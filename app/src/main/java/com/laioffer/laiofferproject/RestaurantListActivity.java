package com.laioffer.laiofferproject;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListFragment.OnItemSelectListener//, RestaurantGridFragment.OnItemSelectListener
{

    RestaurantListFragment listFragment;
    RestaurantMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);


        //add list view
        //if (isTablet()) {
            listFragment = new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_list_container, listFragment).commit();
        //}

        //add Gridview
        mapFragment = new RestaurantMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_map_container, mapFragment).commit();
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
    public void onItemSelectedList(String lat_log){
        mapFragment.onItemSelected(lat_log);
    }

    /*@Override
    public void onItemSelectedGrid(int position){
        listFragment.onItemSelected(position);
    }*/
}

