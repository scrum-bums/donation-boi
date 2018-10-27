package com.scrumbums.donationboi.model;

public enum Categories {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private String name;
    Categories(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
