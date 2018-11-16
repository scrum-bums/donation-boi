package com.scrumbums.donationboi;

import android.app.Application;

import com.scrumbums.donationboi.model.entities.Store;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Handles high-level application logic including configuring the database and sample data on first
 * run.
 */
class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("donation-boi.realm")
                .schemaVersion(4)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        Store.saveSampleLocationData(getApplicationContext()); // Load sample data
    }
}
