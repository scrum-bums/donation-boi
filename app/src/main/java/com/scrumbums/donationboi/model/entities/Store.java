package com.scrumbums.donationboi.model.entities;

import android.content.Context;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Categories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;


/**
 * Store class. Abstracts away a store.
 *
 * @author Nate Schneider, Gibran Essa, and Evan Strat
 */
public class Store extends RealmObject {
    @PrimaryKey private int storeId;

    @LinkingObjects("store")
    private final RealmResults<Item> inventory = null;

    private String name;

    private Location location;

    @Ignore private int locationId;
    private String locationType;
    private String phoneNumber;
    private String website;

    public Store() { }

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
        this(name, location, phoneNumber, null, null);
    }


    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     *
     * @param name        The name of the store.
     * @param phoneNumber This store's phone number.
     * @param website     This store's website.
     */
    public Store(String name, Location location, String phoneNumber, String website, String locationType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.location = location;
        this.locationType = locationType;

        Realm realm = Realm.getDefaultInstance();
        // make sure we give this user a unique (i.e., auto-incrementing) ID in the database
        Number highestId = realm.where(Store.class).max("storeId");
        if (highestId == null) {
            this.storeId = 1;
        } else {
            this.storeId = highestId.intValue() + 1;
        }
    }

    /**
     * Reads in sample stores from a CSV and converts them to Store objects
     *
     * @param context App context object
     * @return A list of sample Stores
     */
    public static void saveSampleLocationData(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.location_data);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line = "";
        try {
            bufferedReader.readLine(); // Skip over the first line of the CSV that's the column names
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
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
                Store s = new Store(name, location, phoneNumber, website, locationType);
                realm.insertOrUpdate(s);
            }

            realm.commitTransaction();
            realm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public RealmResults<Item> getInventory() {
        return inventory;
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
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();
    }

    public void addToInventory(String name, String description, double price, String type, Categories cat) {
        addToInventory(new Item(name, description, price, type, cat, this));
    }

    public ArrayList<Item> getInventoryArrayList() {
        if (inventory == null) {
            return null;
        }
        return new ArrayList<>(inventory);
    }

    public Item getInventoryItem(int itemId) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Item> query = realm.where(Item.class);
        query.equalTo("itemId", itemId);
        return query.findFirst();
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
        return name.hashCode() + location.hashCode() + phoneNumber.hashCode() + website.hashCode();
    }

}
