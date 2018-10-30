package com.scrumbums.donationboi.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Store class. Abstracts away a store.
 * @author Nate Schneider
 */
@Entity
public class Store {
    @PrimaryKey(autoGenerate = true)
    private final int storeId;

    @Ignore
    private HashMap<Integer, Item> inventory;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "location")
    private Location location;

    @ColumnInfo(name = "locationType")
    private String locationType;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "website")
    private String website;

    private static int storeCount = 0;



    public int getStoreId() {
        return storeId;
    }

    public String getLocationType() {

        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }



    /**
     * Constructor for a store. Only accepts a name and location with no phone
     * number or website.
     * @param name The name of the store.
     * @param location The location of the store.
     */
    public Store(String name, Location location) {
        this(name, location, null);
    }

    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     * @param name The name of the store.
     * @param location The location of the store.
     * @param phoneNumber This store's phone number.
     */
    public Store(String name, Location location, String phoneNumber) {
        this(name, location, phoneNumber, null, null);
    }

    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     * @param name The name of the store.
     * @param location The location of the store.
     * @param phoneNumber This store's phone number.
     * @param website This store's website.
     */
    public Store(String name, Location location, String phoneNumber, String website, String locationType) {
        this.inventory = new HashMap<>();
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.locationType = locationType;
        storeCount++;
        this.storeId = storeCount;
    }

    /**
     * Set this store's entire inventory.
     * @param aList The list to set the inventory to.
     */
    private void setInventory(HashMap<Integer, Item> aList) {
        this.inventory = aList;
    }

    /**
     * Get this store's inventory.
     * @return
     */
    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setLocation(Location loc) {
        this.location = location;
    }

    public void setLocation(String streetAddress, String state, String city, int zipcode, float latitude, float longitude) {
        location = new Location(streetAddress, state, city, zipcode, latitude, longitude);
    }

    public Location getLocation() {
        return location;
    }

    private void setPhoneNumber(String pN) {
        this.phoneNumber = pN;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
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

    public void addToInventory(String name) {
        addToInventory(name, null, 0.0, null, null);
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
