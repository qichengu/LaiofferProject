package com.laioffer.laiofferproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class RestaurantMapActivity extends FragmentActivity implements OnMapReadyCallback {
    public final static String EXTRA_LATLNG = "EXTRA_LATLNG";
    public final static String EXTRA_ZOOM = "EXTRA_ZOOM";
    private int number = 1;
    private LatLng toMark;
    private MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);

        mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.restaurant_map_full_screen);

        // This function automatically initializes the maps system and the view.
        mapFragment.getMapAsync(this);

        /*Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            toMark = bundle.getParcelable(EXTRA_LATLNG);
            String temp = bundle.getParcelable(EXTRA_ZOOM);
            number = Integer.parseInt(temp);
        }
*/
        Intent intent = getIntent();
        toMark = intent.getParcelableExtra(EXTRA_LATLNG);
        number = intent.getIntExtra(EXTRA_ZOOM, 1);
        IntentFilter filter = new IntentFilter();
        filter.addAction("GOOGLEMAP_ZOOM");
        this.registerReceiver(new ZoomMap(), filter);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        //map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        if (toMark != null) {
            map.addMarker(new MarkerOptions().position(toMark).title("Marker"));
            map.moveCamera(CameraUpdateFactory.newLatLng(toMark));
            map.animateCamera(CameraUpdateFactory.zoomTo(number), 2000, null);

        }
    }
    class ZoomMap extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                number = Integer.parseInt(extras.getString("ZOOM"));
                mapFragment.getMapAsync(RestaurantMapActivity.this);
            }
        }
    }

}

