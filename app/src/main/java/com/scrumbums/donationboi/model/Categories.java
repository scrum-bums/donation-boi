package com.scrumbums.donationboi.model;

import java.util.Arrays;

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

    public static Categories getCategory(String name) {
        for(Categories c: Categories.values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
