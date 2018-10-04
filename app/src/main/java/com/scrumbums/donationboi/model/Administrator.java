package com.scrumbums.donationboi.model;

/**
 * Class representing a system administrator. Not part of the domain model but
 * responsible for handling moderation requests, complaints, managing the
 * database, application, and other systems, etc. Administrators have the
 * highest privilege level.
 *
 * @author jdierberger3
 * @version 1.0
 */
public class Administrator extends AbstractUser {

    /**
     * Create an Administrator.
     * @param username Username of the Administrator.
     * @param name Name of the Administrator.
     * @param email Email address for the Administrator.
     */
    public Administrator(String username, String name, String email, String password) {
        super(username, name, email, password);
    }

}
