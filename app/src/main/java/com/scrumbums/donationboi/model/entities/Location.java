package com.scrumbums.donationboi.model.entities;


import io.realm.annotations.PrimaryKey;

public class Location {

    private int locationId;

    private float latitude;
    private float longitude;
    private String streetAddress;
    private String city;
    private String state;
    private int zipcode;

    public Location(String streetAddress, String state, String city, int zipcode, float latitude,
                    float longitude) {
        this.streetAddress = streetAddress;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public float getLatitude() {
        return latitude;
    }

    private void setLatitude(float lat) {
        this.latitude = lat;
    }

    public float getLongitude() {
        return longitude;
    }

    private void setLongitude(float lon) {
        this.longitude = longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    private void setStreetAddress(String sA) {
        this.streetAddress = sA;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    private void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String toString() {
        String ret = "";
        ret += "Address: " + cityStateZipCode();
        return ret;
    }

    private String cityStateZipCode() {
        if (streetAddress == null && city == null && state == null && zipcode == 0) {
            return "not listed";
        }
        String ret = "";
        ret += streetAddress == null ? "" : streetAddress;
        ret += (ret == null) ? (city == null ? "" : city) : (city == null ? "" : ", " + city);
        ret += (ret == null) ? (state == null ? "" : state) : (state == null ? "" : ", " + state);
        ret += ret == null ? zipcode == 0 ? "" : zipcode : zipcode == 0 ? "" : ", " + zipcode;
        String latLon = null;
        if (latitude == 0.0 && longitude == 0.0) {
            return ret;
        }
        if (latitude == 0.0 && longitude != 0.0) {
            latLon = "(na, " + longitude + ")";
        }
        if (latitude != 0.0 && longitude == 0.0) {
            latLon = "(" + latitude + ", na)";
        }
        if (latitude != 0.0 && longitude != 0.0) {
            latLon = "(" + latitude + ", " + longitude + ")";
        }
        ret += ", " + latLon;
        return ret;
    }
}
