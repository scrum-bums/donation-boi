package com.scrumbums.donationboi.model.util;

import android.content.Context;

import com.scrumbums.donationboi.model.AppDatabase;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.entities.User;
import com.scrumbums.donationboi.model.daos.UserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
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
     * Attempt to login with the given credentials.
     * @param email The user's email address.
     * @param password The password of the user.
     * @return A Single that will evaluate successfully if a user with the given email and password exists,
     *         or error otherwise.
     */
    public static Single<User> login(Context context, String email, String password) {
        AppDatabase database = AppDatabase.getDatabase(context);

        return database.userDao().getUser(email, password);
    }

    /**
     * Register the given user.
     * @param context An Application context to use to obtain the database
     * @param user The User to register.
     * @return A Completable that will complete when the user has been registered successfully,
     *         or error otherwise.
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

    public static Single<List<Store>> getStoresArrayList(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);

        return Single.fromCallable(() -> database.storeDao().getStoresWithLocations())
                .observeOn(Schedulers.io());
    }

    public static User getSignedInUser() {
        return signedInUser;
    }
}
