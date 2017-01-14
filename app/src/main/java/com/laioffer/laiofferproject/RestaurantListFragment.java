package com.laioffer.laiofferproject;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {
    //ListView listView;

    public static final String KEY_ITEM = "list_tag";
    public static final String KEY_INDEX = "list_tag";
    private String mTime;

    public RestaurantListFragment() {
        // Required empty public constructor
    }

    OnItemSelectListener mCallback;

    // Container Activity must implement this interface
    public interface OnItemSelectListener {
        public void onItemSelectedList(String lat_log);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemSelectListener) context;
        } catch (ClassCastException e) {
            //do something
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        /*if (savedInstanceState != null) {
            // Restore last state
            mTime = savedInstanceState.getString("time_key");
        } else {
            mTime = "" + Calendar.getInstance().getTimeInMillis();

        }
        */
        final ListView listView = (ListView) view.findViewById(R.id.restaurant_list);
        listView.setAdapter(new RestaurantAdapter(getActivity()));
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getRestaurantNames());

        // Assign adapter to ListView.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int idx = 0; idx < listView.getChildCount(); idx++){
                    if (i == idx) {
                        listView.getChildAt(idx).setBackgroundColor(Color.BLUE);
                    } else {
                        listView.getChildAt(idx).setBackgroundColor(Color.parseColor("#EEEEEE"));
                    }
                }
                mCallback.onItemSelectedList(i);
            }
        });
*/
        listView.setAdapter(new RestaurantAdapter(getActivity()));
        //Context context = getApplicationContext();
        // Set a listener to ListView.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Log.e("list index", new Integer(position).toString());
                Log.e("child count", new Integer(listView.getAdapter().getCount()).toString());

                for(int i = 0; i < listView.getAdapter().getCount(); i++){
                    if (position == i) {
                        View wantedView = listView.getAdapter().getView(position, null, listView);
                        wantedView.setBackgroundColor(Color.BLUE);
                        Log.e("list index");


                    } else {
                        View wantedView = listView.getAdapter().getView(position, null, listView);
                        wantedView.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        Log.e("i = ", new Integer(i).toString());
                    }
                }
*/
                Restaurant r = (Restaurant) listView.getItemAtPosition(position);
                String lat_log = new Double(r.getLat()).toString() + "," + new Double(r.getLng()).toString();
                Log.e("lat log", lat_log);
                mCallback.onItemSelectedList(lat_log);
                //Toast.makeText( , new LatLng(r.getLat(), r.getLng())).toString(), Toast.LENGTH_SHORT).show();
                //Create explicit intent to start map activity class
                //Intent intent = new Intent(view.getContext(), RestaurantMapActivity.class);
                //Prepare all the data we need to start map activity.
                //Bundle bundle = new Bundle();
                //bundle.putParcelable(
                //        RestaurantMapActivity.EXTRA_LATLNG,
                //        new LatLng(r.getLat(), r.getLng()));
                //Intent intent = new Intent(view.getContext(), RestaurantMapActivity.class);
                //intent.putExtras(bundle);
                //startActivity(intent);
            }
        });
        return view;
    }

    private String[] getRestaurantNames() {
        String[] names= {
                "Restaurant1", "Restaurant2", "Restaurant3",
                "Restaurant4", "Restaurant5", "Restaurant6",
                "Restaurant7", "Restaurant8", "Restaurant9",
                "Restaurant10"};
        return names;
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("time_key", mTime);
    }*/


}
