package com.laioffer.laiofferproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;


public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback{


    public static final String KEY_ITEM = "map_tag";
    public static final String KEY_INDEX = "map_tag";

    LatLng toMark;
    private String mTime;

    public RestaurantMapFragment() {
        // Required empty public constructor
    }

    MapView mapView;
    GoogleMap map;
    int number = 1;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        /*map.clear();
        map.addMarker(new MarkerOptions().position(toMark).title("Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLng(toMark));
        map.animateCamera(CameraUpdateFactory.zoomTo(number), 2000, null);
        Log.e("Map Fragment", "OnMapReady");
        */
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            // Restore last state
            mTime = savedInstanceState.getString("time_key");
            String[] doubles = mTime.split(",");
            toMark = new LatLng(Double.parseDouble(doubles[0]), Double.parseDouble(doubles[1]));
        } else {
            toMark = new LatLng(0, 0);
            mTime = Double.toString(toMark.latitude) + "," + Double.toString(toMark.longitude);
        }

        View view = inflater.inflate(R.layout.fragment_restaurant_map, container, false);
        mapView = (MapView) view.findViewById(R.id.restaurant_map);
        mapView.onCreate(savedInstanceState);
        if (mapView != null) {
            // Gets to GoogleMap from the MapView and does initialization stuff
            mapView.getMapAsync(this);
        }






        // Updates the location and zoom of the MapView
        //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        //map.animateCamera(cameraUpdate);

        return view;
    }

    class ZoomMap extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                number = Integer.parseInt(extras.getString("ZOOM"));
                //Toast.makeText(getActivity(), Integer.valueOf(number).toString(), Toast.LENGTH_SHORT).show();
                mapView.getMapAsync(RestaurantMapFragment.this);
            }
        }
    }

    public void onItemSelected(String lat_log){
        String[] data = lat_log.split(",");
        LatLng toMark = new LatLng(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
        map.clear();
        map.addMarker(new MarkerOptions().position(toMark).title("Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLng(toMark));
        map.animateCamera(CameraUpdateFactory.zoomTo(number), 2000, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("GOOGLEMAP_ZOOM");
        getActivity().registerReceiver(new ZoomMap(), filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("time_key", mTime);
    }
}
