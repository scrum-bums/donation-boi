package com.scrumbums.donationboi.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.scrumbums.donationboi.controllers.LoginActivity;
import com.scrumbums.donationboi.model.AbstractUser;
import com.scrumbums.donationboi.model.Store;

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
     * Local database while we wait on Firebase. Maps keys (email in this case)
     * to users.
     */
    private static final HashMap<String, AbstractUser> USERDATABASE
            = new HashMap<String, AbstractUser>();

    /**
     * Attempt to login with the given credentials.
     * @param key The key (email or username) of the user.
     * @param password The password of the user.
     * @return 1 if the given credentials are valid, 0 if the password is
     * invalid, or -1 if the username is invalid.
     */
    public static int login(Context context, String key, String password) {
        // case where the account is not registered
        if (USERDATABASE.get(key) == null) {
            return -1;
        }
        // account exists; verify password
        if (USERDATABASE.get(key).verifyPassword(password)) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("canAddItems", USERDATABASE.get(key).canAddDonations);
            editor.commit();
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
        if (USERDATABASE.get(au.getEmailAddress()) != null) return false;
        // otherwise add it
        USERDATABASE.put(au.getEmailAddress(), au);
        return true;
    }

    private static final HashMap<Integer, Store> STOREDATABASE
            = new HashMap<Integer, Store>();

    public static Store getStore(Integer key) {
        return STOREDATABASE.get(key);
    }

    public static boolean addStore(Integer key, Store store) {
        if (STOREDATABASE.get(key) != null) {
            return false;
        }
        STOREDATABASE.put(key, store);
        return true;
    }

}
