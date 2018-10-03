package com.scrumbums.donationboi.model;

/**
 * Class representing a manager. Managers own stores and are points-of-contact
 * for stores.
 *
 * @author jdierberger3
 * @version 1.0
 */
public class Manager extends Employee {

    /**
     * Create a Manager.
     * @param username Username of the Manager.
     * @param name Name of the Manager.
     * @param email Email address for the Manager.
     */
    public Manager(String username, String name, String email, String password) {
        super(username, name, email, password);
    }

}
