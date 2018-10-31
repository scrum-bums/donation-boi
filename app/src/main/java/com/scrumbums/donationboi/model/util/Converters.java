package com.scrumbums.donationboi.model.util;

import android.arch.persistence.room.TypeConverter;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.UserRole;

public class Converters {
    @TypeConverter
    public static String fromUserRole(UserRole role) {
        return role == null ? null : role.toString();
    }

    @TypeConverter
    public static UserRole stringToUserRole(String roleName) {
        return roleName == null ? null : UserRole.getRole(roleName);
    }

    @TypeConverter
    public static String fromCategories(Categories category) {
        return category == null ? null : category.toString();
    }

    @TypeConverter
    public static Categories stringToCategories(String catName) {
        return catName == null ? null : Categories.getCategory(catName);
    }
}
