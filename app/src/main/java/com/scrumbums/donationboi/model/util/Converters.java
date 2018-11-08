package com.scrumbums.donationboi.model.util;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.UserRole;

public class Converters {
    public static String fromUserRole(UserRole role) {
        return role == null ? null : role.toString();
    }

    public static UserRole stringToUserRole(String roleName) {
        return roleName == null ? null : UserRole.getRole(roleName);
    }

    public static String fromCategories(Categories category) {
        return category == null ? null : category.toString();
    }

    public static Categories stringToCategories(String catName) {
        return catName == null ? null : Categories.getCategory(catName);
    }
}
