package com.laioffer.laiofferproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiche on 1/10/2017.
 */

public class DataService {
    /**
     * Fake all the restaurant data for now. We will refine this and connect
     * to our backend later.
     */
    public static List<Restaurant> getRestaurantData() {
        List<Restaurant> restaurantData = new ArrayList<Restaurant>();
        for (int i = 0; i < 10; ++i) {
            restaurantData.add(
                    new Restaurant("Restaurant", "1184 W valley Blvd, CA 90101",
                            "New American", i * 7 + 7,
                            i * 5 - 5));
        }
        return restaurantData;
    }
}
