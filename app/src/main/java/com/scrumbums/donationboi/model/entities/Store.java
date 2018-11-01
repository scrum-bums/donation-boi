package com.scrumbums.donationboi.model.entities;

import android.content.Context;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Store class. Abstracts away a store.
 *
 * @author Nate Schneider, Gibran Essa, and Evan Strat
 */
public class Store {
    private static int storeCount = 0;
    private int storeId;
    private HashMap<Integer, Item> inventory;

    private String name;

    private Location location;
    private int locationId;
    private String locationType;
    private String phoneNumber;
    private String website;

    /**
     * Constructor for a store. Only accepts a name and location with no phone
     * number or website.
     *
     * @param name     The name of the store.
     * @param location The location of the store.
     */
    public Store(String name, Location location) {
        this(name, location, null);
    }

    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     *
     * @param name        The name of the store.
     * @param location    The location of the store.
     * @param phoneNumber This store's phone number.
     */
    public Store(String name, Location location, String phoneNumber) {
        this(name, 0, phoneNumber, null, null);
    }


    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     *
     * @param name        The name of the store.
     * @param phoneNumber This store's phone number.
     * @param website     This store's website.
     */
    public Store(String name, int locationId, String phoneNumber, String website, String locationType) {
        this.inventory = new HashMap<>();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.locationId = locationId;
        this.locationType = locationType;
    }

    /**
     * Get sample store data
     *
     * @param context App context object
     * @return A List of sample Stores
     */
    public static HashMap<String, List> populateData(Context context) {
        return readLocationData(context);

    }

    /**
     * Reads in sample stores from a CSV and converts them to Store objects
     *
     * @param context App context object
     * @return A list of sample Stores
     */
    private static HashMap<String, List> readLocationData(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.location_data);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        List<Store> stores = new ArrayList<>();
        List<Location> locations = new ArrayList<>();

        String line = "";
        try {
            int storeId = 1;
            bufferedReader.readLine(); // Skip over the first line of the CSV that's the column names
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                String name = (tokens[1]);
                float latitude = Float.parseFloat(tokens[2]);
                float longitude = Float.parseFloat(tokens[3]);
                String streetAddress = tokens[4];
                String city = tokens[5];
                String state = tokens[6];
                int zipCode = Integer.parseInt(tokens[7]);
                String locationType = tokens[8];
                String phoneNumber = tokens[9];
                String website = tokens[10];
                Location location = new Location(streetAddress, state, city, zipCode,
                                                 latitude, longitude);
                Store store = new Store(name, location.getLocationId(), phoneNumber, website, locationType);
                stores.add(store);
                locations.add(location);
                storeId++; // since we're importing this data only when the database is created
                            // it's a somewhat reasonable that the first store id will be 0.  But we
                            // really shouldn't bank on that
            }

            HashMap<String, List> result = new HashMap<>();
            result.put("stores",stores);
            result.put("locations", locations);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getLocationType() {

        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * Get this store's inventory.
     *
     * @return
     */
    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    /**
     * Set this store's entire inventory.
     *
     * @param aList The list to set the inventory to.
     */
    private void setInventory(HashMap<Integer, Item> aList) {
        this.inventory = aList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    private void setWebsite(String website) {
        this.website = website;
    }

    public void addToInventory(Item item) {
        inventory.put(item.getItemId(), item);
    }

    public void addToInventory(String name, String description, double price, String type, Categories cat) {
        addToInventory(new Item(name, description, price, type, cat, storeId));
    }

    public ArrayList<Item> getInventoryArrayList() {
        return new ArrayList<>(inventory.values());
    }

    public Item getInventoryItem(int itemId) {
        if (inventory.containsKey(itemId)) {
            return inventory.get(itemId);
        } else {
            return null;
        }
    }

    public String toString() {
        String ret = "";
        ret += "Name: " + (name == null ? "not listed" : name);
        ret += "\nLocation: " + (location == null ? "not listed" : location);
        ret += "\nPhone Number: " + (phoneNumber == null ? "not listed" : phoneNumber);
        ret += "\nWebsite: " + (website == null ? "not listed" : website);
        return ret;
    }

    @Override
    public int hashCode() {
        return inventory.hashCode() + name.hashCode() + location.hashCode() + phoneNumber.hashCode() + website.hashCode();
    }

}
