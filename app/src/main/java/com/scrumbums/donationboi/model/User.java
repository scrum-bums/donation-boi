package com.scrumbums.donationboi.model;

/**
 * Class representing a normal front-end user of the app. This class can view
 * Stores, items at Stores, and interfaces with Employees of the Store to
 * purchase items or donate to a Store.
 *
 * @author jdierberger3
 * @version 1.0
 */
public class User extends AbstractUser {

    /**
     * Create a normal User.
     * @param username Username of the User.
     * @param name Name of the Use.
     * @param email Email address for the User.
     */
    public User(String username, String name, String email) {
        super(username, name, email);
    }

}
