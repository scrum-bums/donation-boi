package com.scrumbums.donationboi.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.scrumbums.donationboi.model.daos.LocationDao;
import com.scrumbums.donationboi.model.daos.StoreDao;
import com.scrumbums.donationboi.model.daos.UserDao;
import com.scrumbums.donationboi.model.entities.Location;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.entities.User;
import com.scrumbums.donationboi.model.util.Converters;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

@TypeConverters({Converters.class})
@Database(entities = {User.class, Store.class, Location.class }, version = 4,
          exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract StoreDao storeDao();
    public abstract LocationDao locationDao();

    private static volatile AppDatabase DATABASE;


    public static AppDatabase getDatabase(final Context context) {
        if (DATABASE == null) {
            synchronized (AppDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = buildDatabase(context);
                }
            }
        }

        new Thread(() -> {
            // force population of sample data
            DATABASE.beginTransaction();
            DATABASE.endTransaction();

            DATABASE.query("select 1", null);
        }).start();


        return DATABASE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        HashMap<String, List> sampleData = Store.populateData(context);
                                        getDatabase(context).storeDao().insertStores(sampleData.get("stores"));
                                        getDatabase(context).locationDao().insertLocations(sampleData.get("locations"));

                                    }
                                }
                        );
                    }
                })
                .fallbackToDestructiveMigration()
                .build();

    }
}
