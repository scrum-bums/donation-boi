package com.scrumbums.donationboi.model.util;

import android.arch.persistence.room.TypeConverter;

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
}
