package com.scrumbums.donationboi.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.scrumbums.donationboi.model.UserRole;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.entities.User;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
     * Attempt to login with the given credentials.
     * @param context The current application context
     * @param email The email of the user.
     * @param password The password of the user.
     * @return 1 if the given credentials are valid, 0 if the password is
     * invalid, or -1 if the username is invalid.
     */
    public static User login(Context context, String email, String password) {
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class);
        query.equalTo("email", email)
                .equalTo("password",password);

        User user = query.findFirst();

        if (user != null) {
            boolean canAddItems = user.getRole().equals(UserRole.EMPLOYEE);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("canAddItems", canAddItems);
            editor.putString("userEmail", user.getEmail());
            editor.putBoolean("loggedIn", true);
            editor.commit();
            realm.close();
            return user;
        } else {
            realm.close();
            return null;
        }
    }

    /**
     * Register the given user.
     * @param context An Application context to use to obtain the database
     * @param user The User to register.
     * @return A Completable that will complete when the user has been registered successfully,
     *         or error otherwise.
     */
    public static boolean register(final Context context, final User user) {
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<User> query = realm.where(User.class);
        query.equalTo("email", user.getEmail());

        User result = query.findFirst();

        if (result != null) { // User with this email already exists
            return false;
        }

        realm.beginTransaction();
        realm.insert(user);
        realm.commitTransaction();
        realm.close();
        return true;
    }

    /**
     * Get a store by its store ID
     *
     * @param storeId The store ID to search by
     * @return The store if found.  Otherwise, null.
     */
    public static Store getStore(int storeId) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Store> query = realm.where(Store.class);
        query.equalTo("storeId", storeId);
        return query.findFirst();
    }

    /**
     * Get all the stores in the database
     *
     * @return An array containing all the stores in the database
     */
    public static Store[] getStoresArray() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Store> storeRealmQuery = realm.where(Store.class);
        RealmResults<Store> result = storeRealmQuery.findAll();
        Store[] out = new Store[result.toArray().length];
        return storeRealmQuery.findAll().toArray(out);
    }

    /**
     * Logs the user out of the application
     *
     * @param context The current application context
     */
    public static void logout(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
