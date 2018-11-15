package com.scrumbums.donationboi.model.entities;

import android.support.annotation.NonNull;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.util.Converters;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Model class to represent a donated item in a store
 */
public class Item extends RealmObject {

    private String name;

    private String description;

    private double price;

    private String type;

    private String category;

    private int itemId;


    private Store store;

    private String timestamp;

    /**
     * Public no-arg constructor for Realm
     */
    public Item() {
    }

    /**
     * Create a new item
     *
     * @param n        The name of the item
     * @param d        Description for the item
     * @param p        Price for the item
     * @param t        Item type
     * @param category Item category
     * @param store    Store the item was donated to
     */
    public Item(String n, String d, double p, String t, Categories category, Store store) {
        this.name = n;
        this.description = d;
        this.price = p;
        this.type = t;
        this.setCategory(category);

        Realm realm = Realm.getDefaultInstance();
        // make sure we give this user a unique (i.e., auto-incrementing) ID in the database
        Number highestId = realm.where(Item.class).max("itemId");
        if (highestId == null) {
            this.itemId = 1;
        } else {
            this.itemId = highestId.intValue() + 1;
        }

        this.store = store;
        timestamp = Calendar.getInstance().getTime().toString();
    }

    /**
     * Creates a new Item
     * @param n Name of the item
     * @param store Store item was donated to
     */
    public Item(String n, Store store) {
        this(n, null, 0.0, null, null, store);
    }

    /**
     * Returns this item's ID
     * @return The item's ID
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Returns this item's category
     * @return The item's category
     */
    public Categories getCategory() {
        return Converters.stringToCategories(category);
    }

    private void setCategory(Categories category) {
        this.category = Converters.fromCategories(category);
    }

    /**
     * Returns the item's name
     * @return The item's name
     */
    public String getName() {
        return name;
    }

    private void setName(String n) {
        this.name = n;
    }

    /**
     * Returns the item's description
     * @return The item's description
     */
    public String getDescription() {
        return description;
    }

    private void setDescription(String d) {
        this.description = d;
    }

    /**
     * Get the item's price
     * @return The item's price
     */
    public double getPrice() {
        return price;
    }

    private void setPrice(double p) {
        this.price = p;
    }

    /**
     * Returns the item type
     * @return The type of this item
     */
    public String getType() {
        return type;
    }

    private void setType(String t) {
        this.type = t;
    }

    @NonNull
    public String toString() {
        String ret = "Name: " + name;
        ret += (description == null) ? ("\n" + "Description: not listed") : ("\n" + "Description: "
                + description);
        return ret;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item temp = (Item) o;

        return (temp.getName().equals(name) && (temp.getPrice()
                == price)
                && temp.getType().equals(type)
                && temp.getDescription().equals(description));
    }

    public int hashCode() {
        int hash = 13;
        hash = (31 * hash) + (int) (Math.round(price));
        hash = name.hashCode();
        hash = description.hashCode();
        hash = type.hashCode();
        return hash;
    }

    /**
     * Get the date/time this item was donated
     * @return The timestamp the item was added to the database
     */
    public String getTimestamp() {
        return timestamp;
    }


    /**
     * Returns the store the item was donated to
     * @return A Store, where the item was donated
     */
    public Store getStore() {
        return store;
    }

    public int getStoreId() {
        return store.getStoreId();
    }

    /**
     * Set the store the item is located at
     * @param store The store to change the item's location to
     */
    public void setStore(Store store) {
        this.store = store;
    }
}

