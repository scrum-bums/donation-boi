package com.scrumbums.donationboi.model;

public enum UserRole {
    USER("User"),
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    ADMINISTRATOR("Administrator");

    private String roleName;

    UserRole(String role) {
        this.roleName = role;
    }

    @Override
    public String toString() {
        return roleName;
    }

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
}
