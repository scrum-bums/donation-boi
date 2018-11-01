package com.scrumbums.donationboi.model;

import com.scrumbums.donationboi.model.entities.Store;

import java.util.Calendar;

//TODO: ADD ItemDao
public class Item {

    private static int itemCount = 0;

    private String name;

    private String description;

    private double price;

    private String type;

    private Categories category;

    private int itemId;

    private String timestamp;

    private int storeId;

    public Item(String n, String d, double p, String t, Categories category, int storeId) {
        this.name = n;
        this.description = d;
        this.price = p;
        this.type = t;
        this.category = category;
        itemCount++;
        this.itemId = itemCount;
        timestamp = Calendar.getInstance().getTime().toString();
        this.storeId = storeId;
    }

    public Item(String n) {
        this(n, null, 0.0, null, null, 0);
    }

    public int getItemId() {
        return itemId;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
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

