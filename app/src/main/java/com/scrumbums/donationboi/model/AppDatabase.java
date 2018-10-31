package com.scrumbums.donationboi.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.scrumbums.donationboi.model.util.Converters;

@TypeConverters({Converters.class})
@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile AppDatabase DATABASE;


    public static AppDatabase getDatabase(final Context context) {
        if (DATABASE == null) {
            synchronized (AppDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return DATABASE;
    }
}
