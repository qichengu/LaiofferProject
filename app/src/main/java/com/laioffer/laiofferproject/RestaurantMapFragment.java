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

    LatLng toMark = new LatLng(0, 0);
    MapView mapView;
    GoogleMap map;
    int number = 8;
    int init_number = 1;

    public RestaurantMapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.clear(); // to remove previous markers. This is not necessary when map is in another activity because the map is destroyed on activity exit.
        // map.clear(); may overkill. have to reconstruct LatLng and zoom
        map.addMarker(new MarkerOptions().position(toMark).title("Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLng(toMark));
        map.animateCamera(CameraUpdateFactory.zoomTo(number), 2000, null);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            // Restore last state
            String mTime = savedInstanceState.getString("latlng_key");
            String[] doubles = mTime.split(",");
            toMark = new LatLng(Double.parseDouble(doubles[0]), Double.parseDouble(doubles[1]));
            String mZoom = savedInstanceState.getString("zoom_key");
            number = Integer.parseInt(mZoom);
            //Toast.makeText(getActivity(), mTime, Toast.LENGTH_SHORT).show();
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
        number = init_number;
        toMark = new LatLng(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
        mapView.getMapAsync(this);
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
        outState.putString("latlng_key", Double.toString(toMark.latitude) + "," + Double.toString(toMark.longitude));
        outState.putString("zoom_key", Integer.toString(number));
    }
}
