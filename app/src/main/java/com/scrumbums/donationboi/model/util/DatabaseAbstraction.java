package com.scrumbums.donationboi.model.util;

import com.scrumbums.donationboi.model.AbstractUser;

import java.util.HashMap;

/**
 * Abstraction of the database. Handles useful database functions through an
 * abstraction layer (i.e methods) but allows reconfiguration of the database
 * behind the scenes (local for demos, Firebase, etc.)
 *
 * @author jdierberger3
 */
public final class DatabaseAbstraction {

    /**
     * do not use.
     */
    private DatabaseAbstraction() { }

    /**
     * Local databse while we wait on Firebase. Maps keys (email in this case)
     * to users.
     */
    private static final HashMap<String, AbstractUser> DATABASE
            = new HashMap<String, AbstractUser>();

    /**
     * Attempt to login with the given credentials.
     * @param key The key (email or username) of the user.
     * @param password The password of the user.
     * @return 1 if the given credentials are valid, 0 if the password is
     * invalid, or -1 if the username is invalid.
     */
    public static int login(String key, String password) {
        // case where the account is not registered
        if (DATABASE.get(key) == null) {
            return -1;
        }
        // account exists; verify password
        if (DATABASE.get(key).verifyPassword(password)) {
            return 1;
        }
        // otherwise password is bayud
        return 0;
    }

    /**
     * Register the given user.
     * @param au The AbstractUser to register.
     * @return If the AbstractUser was registered. If not, the key (email) has
     * already been registered.
     */
    public static boolean register(AbstractUser au) {
        // in this case, the email is already registered
        if (DATABASE.get(au.getEmailAddress()) != null) return false;
        // otherwise add it
        DATABASE.put(au.getEmailAddress(), au);
        return true;
    }

}
