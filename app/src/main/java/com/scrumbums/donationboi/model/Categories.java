package com.scrumbums.donationboi.model;

import android.support.annotation.NonNull;

/**
 * Represents the different categories an Item in a Store an be classified into.
 */
public enum Categories {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private final String name;

    Categories(String name) {
        this.name = name;
    }

    /**
     * Get the enum for a category based on its String representation
     *
     * @param name The name of the category to use to look for an enum member
     * @return The enum member if found.  Otherwise, null.
     */
    public static Categories getCategory(String name) {
        for(Categories c: Categories.values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }

    @NonNull
    public String toString() {
        return name;
    }
}
