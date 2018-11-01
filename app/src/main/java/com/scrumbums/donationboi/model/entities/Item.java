package com.scrumbums.donationboi.model.entities;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.util.Converters;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.LinkingObjects;

public class Item extends RealmObject {
    private String name;

    private String description;

    private double price;

    private String type;

    private String category;

    private int itemId;


    private Store store;

    private String timestamp;

    public Item() { } // Public no-arg constructor for Realm

    public Item(String n, String d, double p, String t, Categories category, Store store) {
        this.name = n;
        this.description = d;
        this.price = p;
        this.type = t;
        setCategory(category);

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

    public Item(String n, Store store) {
        this(n, null, 0.0, null, null, store);
    }

    public int getItemId() {
        return itemId;
    }

    public Categories getCategory() {
        return Converters.stringToCategories(category);
    }

    public void setCategory(Categories category) {
        this.category = Converters.fromCategories(category);
    }

    public String getName() {
        return name;
    }

    private void setName(String n) {
        this.name = n;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String d) {
        this.description = d;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double p) {
        this.price = p;
    }

    public String getType() {
        return type;
    }

    private void setType(String t) {
        this.type = t;
    }

    public String toString() {
        String ret = "Name: " + name;
        ret += description == null ? "\n" + "Description: not listed": "\n" + "Description: " + description;
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
        return (temp.getName().equals(name) && temp.getPrice() == price && temp.getType().equals(type) && temp.getDescription().equals(description));
    }

    public int hashCode() {
        int hash = 13;
        hash = 31 * hash + (int) (Math.round(price) == 0 ? 0 : Math.round(price));
        hash = 31 * hash + name == null ? 0 : name.hashCode();
        hash = 31 * hash + description == null ? 0 : description.hashCode();
        hash = 31 * hash + type == null ? 0 : type.hashCode();
        return hash;
    }

    public String getTimestamp() {
        return timestamp;
    }


}

