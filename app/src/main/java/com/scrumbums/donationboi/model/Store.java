package com.scrumbums.donationboi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Store class. Abstracts away a store.
 * @author Nate Schneider
 */
public class Store implements Parcelable {
    private ArrayList<Item> inventory;
    private String name;
    private Location location;
    private String phoneNumber;
    private String website;

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
        this(name, location, phoneNumber, null);
    }

    /**
     * Constructor for a store. Only accepts a name, location and phone number
     * without a website.
     * @param name The name of the store.
     * @param location The location of the store.
     * @param phoneNumber This store's phone number.
     * @param website This store's website.
     */
    public Store(String name, Location location, String phoneNumber, String website) {
        this.inventory = new ArrayList<>();
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    /**
     * Set this store's entire inventory.
     * @param aList The list to set the inventory to.
     */
    private void setInventory(ArrayList<Item> aList) {
        this.inventory = aList;
    }

    /**
     * Get this store's inventory.
     * @return
     */
    public ArrayList<Item> getInventory() {
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
        inventory.add(item);
    }

    public void addToInventory(String name, String description, double price, String type, Categories cat) {
        addToInventory(new Item(name, description, price, type, cat));
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.inventory);
        dest.writeString(this.name);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.website);
    }

    protected Store(Parcel in) {
        this.inventory = new ArrayList<Item>();
        in.readList(this.inventory, Item.class.getClassLoader());
        this.name = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.phoneNumber = in.readString();
        this.website = in.readString();
    }

    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel source) {
            return new Store(source);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
}
