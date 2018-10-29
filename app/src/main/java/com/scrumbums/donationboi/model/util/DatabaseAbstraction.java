package com.scrumbums.donationboi.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.scrumbums.donationboi.model.AbstractUser;
import com.scrumbums.donationboi.model.AppDatabase;
import com.scrumbums.donationboi.model.Store;
import com.scrumbums.donationboi.model.User;

import java.util.ArrayList;
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
    private static final HashMap<String, AbstractUser> USER_DATABASE
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
        AppDatabase DATABASE = AppDatabase.getDatabase(context);
        Log.i("login",DATABASE.userDao().getAll().toString());
        if (USER_DATABASE.get(key) == null) {
            return -1;
        }
        // account exists; verify password
        if (USER_DATABASE.get(key).verifyPassword(password)) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("canAddItems", USER_DATABASE.get(key).canAddDonations);
            editor.commit();
            return 1;
        }
        // otherwise password is bayud
        return 0;
    }

    /**
     * Register the given user.
     * @param user The User to register.
     * @return If the User was registered. If not, the key (email) has
     * already been registered.
     */
    public static boolean register(final Context context, final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getDatabase(context).userDao().createUser(user);
                Log.i("databaseabstraction",AppDatabase.getDatabase(context).userDao().getAll().toString());
            }
        }).start();
        // in this case, the email is already registered
//        if (USER_DATABASE.get(au.getEmailAddress()) != null) return false;
//        // otherwise add it
//        USER_DATABASE.put(au.getEmailAddress(), au);
        return true;
    }

    private static final HashMap<Integer, Store> STORE_DATABASE
            = new HashMap<Integer, Store>();

    public static Store getStore(Integer key) {
        return STORE_DATABASE.get(key);
    }

    public static boolean addStore(Integer key, Store store) {
        if (STORE_DATABASE.get(key) != null) {
            return false;
        }
        STORE_DATABASE.put(key, store);
        return true;
    }

    public static ArrayList<Store> getStoresArrayList() {
        ArrayList<Store> temp = new ArrayList<>(STORE_DATABASE.values());
        return temp;
    }
}
