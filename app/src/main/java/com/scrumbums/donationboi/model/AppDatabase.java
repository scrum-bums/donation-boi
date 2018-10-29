package com.scrumbums.donationboi.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile AppDatabase DATABASE;


    static AppDatabase getDatabase(final Context context) {
        if (DATABASE == null) {
            synchronized (AppDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .build();
                }
            }
        }
        return DATABASE;
    }
}
