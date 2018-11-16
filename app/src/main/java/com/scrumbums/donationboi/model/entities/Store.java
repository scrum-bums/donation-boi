package com.scrumbums.donationboi.model.entities;

import android.content.Context;
import android.support.annotation.NonNull;

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

    /**
     * No-arg constructor for Realm
     */
    public Store() { }

    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     *
     * @param name        The name of the store.
     * @param location    The location of the store.
     * @param phoneNumber This store's phone number.
     */
    private Store(String name, Location location, String phoneNumber) {
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
    private Store(String name, Location location, String phoneNumber, String website,
                  String locationType) {
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
     */
    public static void saveSampleLocationData(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.location_data);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8));

        try {
            bufferedReader.readLine(); //Skip over the first line of the CSV that's the column names
            Realm realm = Realm.getDefaultInstance();
            bufferedReader.lines().map(line -> line.split(",")).forEach(tokens -> {
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
                RealmQuery<Store> storeExists = realm.where(Store.class);
                storeExists.equalTo("name", s.getName());
                if (storeExists.findFirst() == null) {
                    realm.beginTransaction();
                    realm.insertOrUpdate(s);
                    realm.commitTransaction();
                }
            });

            realm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get this location's ID
     *
     * @return This location's ID
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Set this location's ID
     * @param locationId The ID to set the location ID to
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * Get this location's phone number
     * @return This location's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set this location's phone number
     * @param phoneNumber The new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get this store's ID
     * @return This store's ID
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Set this store's ID
     * @param storeId The new store ID
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /**
     * Get the type of this location
     * @return This location's type
     */
    public String getLocationType() {

        return locationType;
    }

    /**
     * Set this location's type
     * @param locationType New location type
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * Get this store's inventory.
     *
     * @return A RealmResults list containing the store's inventory
     */
    public RealmResults<Item> getInventory() {
        return this.inventory; // note: the type of this return is correct as-is
    }

    /**
     * Get this store's name
     * @return The store's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set this store's name
     * @param name The store's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get this store's location
     * @return A Location object representing the store's physical location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set this store's location
     * @param location A Location  object representing the new location for this store
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get the website URL for this store
     * @return The store's website
     */
    public String getWebsite() {
        return website;
    }

    private void setWebsite(String website) {
        this.website = website;
    }

    private void addToInventory(Item item) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();
    }

    /**
     * Creates a new Item and adds it to the inventory
     *
     * @param name        The item's name
     * @param description The item's description
     * @param price       The item's price
     * @param type        The item's type
     * @param cat         The item's category
     */
    public void addToInventory(String name, String description, double price, String type,
                               Categories cat) {
        addToInventory(new Item(name, description, price, type, cat, this));
    }

    /**
     * Get the store's inventory
     * @return An ArrayList of the items in this store's inventory
     */
    public ArrayList<Item> getInventoryArrayList() {
        if (inventory == null) { // Not always null - Realm handles this though, so it's not
            // obvious here
            return null;
        }
        return new ArrayList<>(inventory);
    }

    /**
     * Lookup an item in this store's inventory by its ID
     * @param itemId The item ID to search by
     * @return The item, if it was found.  Otherwise, null.
     */
    public Item getInventoryItem(int itemId) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Item> query = realm.where(Item.class);
        query.equalTo("itemId", itemId);
        return query.findFirst();
    }

    @NonNull
    public String toString() {
        String ret = "";
        ret += "Name: " + (name == null ? "not listed" : name);
        ret += "\nLocation: " + (location == null ? "not listed" : location);
        ret += "\nPhone Number: " + (phoneNumber == null ? "not listed" : phoneNumber);
        ret += "\nWebsite: " + (website == null ? "not listed" : website);
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Store) {
            Store compare = (Store) obj;
            return compare.storeId == this.storeId;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return name.hashCode() + location.hashCode() + phoneNumber.hashCode() + website.hashCode();
    }

    /**
     * Get this store's latitude
     *
     * @return The store's latitude
     */
    public float getLatitude() {
        return location.getLatitude();
    }

    /**
     * Get this store's longitude
     * @return The store's longitude
     */
    public float getLongitude() {
        return location.getLongitude();
    }

}
