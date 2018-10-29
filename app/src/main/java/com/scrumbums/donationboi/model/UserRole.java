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
}
