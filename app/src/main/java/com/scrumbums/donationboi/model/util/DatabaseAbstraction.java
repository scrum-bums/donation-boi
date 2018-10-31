package com.scrumbums.donationboi.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import com.scrumbums.donationboi.model.AbstractUser;
import com.scrumbums.donationboi.model.AppDatabase;
import com.scrumbums.donationboi.model.Store;
import com.scrumbums.donationboi.model.User;
import com.scrumbums.donationboi.model.UserDao;
import com.scrumbums.donationboi.model.UserRole;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstraction of the database. Handles useful database functions through an
 * abstraction layer (i.e methods) but allows reconfiguration of the database
 * behind the scenes (local for demos, Firebase, etc.)
 *
 * @author jdierberger3
 */
public final class DatabaseAbstraction {

    private static User signedInUser;

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
     * @param username The username (email or username) of the user.
     * @param password The password of the user.
     * @return 1 if the given credentials are valid, 0 if the password is
     * invalid, or -1 if the username is invalid.
     */
    public static Single<User> login(Context context, String username, String password) {
        // case where the account is not registered
        AppDatabase database = AppDatabase.getDatabase(context);
        Log.i("login",database.userDao().getAll().toString());

        return database.userDao().getUser(username, password);
    }

    /**
     * Register the given user.
     * @param user The User to register.
     * @return If the User was registered. If not, the key (email) has
     * already been registered.
     */
    public static Completable register(final Context context, final User user) {
        AppDatabase database = AppDatabase.getDatabase(context);
        UserDao dao = database.userDao();

        return Completable.fromAction(() -> dao.createUser(user));
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

    public static User getSignedInUser() {
        return signedInUser;
    }
}
