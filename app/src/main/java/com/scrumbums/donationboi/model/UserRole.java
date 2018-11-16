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

    /**
     * converts a role to its string.
     *
     * @param role role to convert
     * @return string form of role.
     */
    public static String fromUserRole(UserRole role) {
        return (role == null) ? null : role.toString();
    }

    /**
     * Converts a string to the role represented by that string.
     *
     * @param roleName name to convert
     * @return category represented by roleName
     */
    public static UserRole stringToUserRole(String roleName) {
        return (roleName == null) ? null : getRole(roleName);
    }

    @NonNull
    @Override
    public String toString() {
        return roleName;
    }
}
