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
    private static Categories getCategory(String name) {
        for(Categories c: Categories.values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Converts a string to the category represented by that string.
     *
     * @param catName name to convert
     * @return category represented by catName
     */
    public static Categories stringToCategories(String catName) {
        return (catName == null) ? null : getCategory(catName);
    }

    /**
     * converts a category to its string.
     *
     * @param category role to convert
     * @return string form of category.
     */
    public static String fromCategories(Categories category) {
        return (category == null) ? null : category.toString();
    }

    @NonNull
    public String toString() {
        return name;
    }
}
