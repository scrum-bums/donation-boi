package com.scrumbums.donationboi.model.util;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.UserRole;

public class Converters {
    /**
     * converts a role to its string.
     * @param role role to convert
     * @return string form of role.
     */
    public static String fromUserRole(UserRole role) {
        return role == null ? null : role.toString();
    }

    /**
     * Converts a string to the role represented by that string.
     * @param roleName name to convert
     * @return category represented by roleName
     */
    public static UserRole stringToUserRole(String roleName) {
        return roleName == null ? null : UserRole.getRole(roleName);
    }

    /**
     * converts a category to its string.
     * @param category role to convert
     * @return string form of category.
     */
    public static String fromCategories(Categories category) {
        return category == null ? null : category.toString();
    }


    /**
     * Converts a string to the category represented by that string.
     * @param catName name to convert
     * @return category represented by catName
     */
    public static Categories stringToCategories(String catName) {
        return catName == null ? null : Categories.getCategory(catName);
    }
}
