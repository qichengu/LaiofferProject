package com.laioffer.laiofferproject;

/**
 * Created by Qiche on 1/10/2017.
 */

public class Restaurant {
    /**
     * All data for a restaurant.
     */
    private String name;
    private String address;
    private String type;

    /**
     * Constructor
     *
     * @param name name of the restaurant
     */
    public Restaurant(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    /**
     * Getters for private attributes of Restaurant class.
     */
    public String getName() { return this.name; }
    public String getAddress() { return this.address; }
    public String getType() { return this.type; }
}
