package com.scrumbums.donationboi.model;

import android.support.annotation.NonNull;

/**
 * Represents the different application permission levels a user account can have.
 */
public enum UserRole {
    USER("User"),
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    ADMINISTRATOR("Administrator");

    private final String roleName;

    UserRole(String role) {
        this.roleName = role;
    }

    /**
     * Get an member of this enum based on its String representation
     *
     * @param roleName The role name to use to look for the enum member
     * @return The enum member if found.  Otherwise, null.
     */
    public static UserRole getRole(String roleName) {
        if (roleName.equals(USER.roleName)) {
            return USER;
        } else if (roleName.equals(EMPLOYEE.roleName)) {
            return EMPLOYEE;
        } else if (roleName.equals(MANAGER.roleName)) {
            return MANAGER;
        } else if (roleName.equals(ADMINISTRATOR.roleName)) {
            return ADMINISTRATOR;
        }

        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return roleName;
    }
}
