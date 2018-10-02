package com.scrumbums.donationboi.model;

/**
 * Class representing an employee of a store. Employees work fo a store and
 * interface with the user for selling items and accepting donations.
 *
 * @author jdierberger3
 * @version 1.0
 */
public class Employee extends AbstractUser {

    /**
     * Create an Employee.
     * @param username Username of the Employee.
     * @param name Name of the Employee.
     * @param email Email address for the Employee.
     */
    public Employee(String username, String name, String email, String password) {
        super(username, name, email, password);
    }

}
