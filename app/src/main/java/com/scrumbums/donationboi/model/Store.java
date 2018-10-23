package com.scrumbums.donationboi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Store implements Parcelable {
    private ArrayList<Item> inventory;
    private String name;
    private Location location;
    private String phoneNumber;
    private String website;

    public Store(String name, Location location) {
        this(name, location, null);
    }

    public Store(String name, Location location, String phoneNumber) {
        this(name, location, phoneNumber, null);
    }

    public Store(String name, Location location, String phoneNumber, String website) {
        this.inventory = new ArrayList<>();
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    private void setInventory(ArrayList<Item> aList) {
        this.inventory = aList;
    }

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

    public void addToInventory(String name, String description, double price, String type) {
        addToInventory(new Item(name, description, price, type));
    }

    public void addToInventory(String name) {
        addToInventory(name, null, 0.0, null);
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(new Object[] {this.name, this.location, this.phoneNumber, this.website});
    }

    public class Item {

        private String name;
        private String description;
        private double price;
        private String type;

        public Item(String n, String d, double p, String t) {
            this.name = n;
            this.description = d;
            this.price = p;
            this.type = t;
        }

        public Item(String n) {
            this(n, null, 0.0, null);
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
            ret += price == 0.0 ? "\n" + "Price: not listed" : "\n" + "Price: $" + price;
            ret += type == null ? "\n" + "Type: not listed" : "\n" + "Type: " + type;
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
    }
}
