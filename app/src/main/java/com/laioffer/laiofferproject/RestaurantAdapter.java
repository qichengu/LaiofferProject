package com.laioffer.laiofferproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Qiche on 1/10/2017.
 */

public class RestaurantAdapter extends BaseAdapter {
    Context context;
    List<Restaurant> restaurantData;

    public RestaurantAdapter(Context context) {
        this.context = context;
        restaurantData = DataService.getRestaurantData();
    }

    @Override
    public int getCount() {
        return restaurantData.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_restaurant_list_item,
                    parent, false);
        }

        ImageView restaurantPhoto = (ImageView) convertView.findViewById(R.id.restaurant_thumbnail);
        restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail0);
        switch (position % 10) {
            case 0: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail0); break;
            case 1: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail1); break;
            case 2: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail2); break;
            case 3: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail3); break;
            case 4: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail4); break;
            case 5: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail5); break;
            case 6: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail6); break;
            case 7: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail7); break;
            case 8: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail8); break;
            case 9: restaurantPhoto.setImageResource(R.drawable.restaurant_thumbnail9); break;
        }
        /*Resources res = getResources();
        String mDrawableName = "logo_default";
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID );
        icon.setImageDrawable(drawable );
        */

        //restaurantPhoto.setBackgroundColor(256*256*256/10*position);
        /*Resources res = context.getResources();
        String mDrawableName = "p1.png";
        Log.e("Image name test", "restaurant_thumbnail" + position+ ".png");
        int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID);
        restaurantPhoto.setImageDrawable(drawable);
*/
        TextView restaurantName = (TextView) convertView.findViewById(
                R.id.restaurant_name);
        TextView restaurantAddress = (TextView) convertView.findViewById(
                R.id.restaurant_address);
        TextView restaurantType = (TextView) convertView.findViewById(
                R.id.restaurant_type);

        Restaurant r = restaurantData.get(position);
        restaurantName.setText(r.getName());
        restaurantAddress.setText(r.getAddress());
        restaurantType.setText(r.getType());
        return convertView;
    }
}