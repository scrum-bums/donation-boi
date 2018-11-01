package com.scrumbums.donationboi;

import android.app.Application;

import com.scrumbums.donationboi.model.entities.Store;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("donation-boi.realm")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        Store.saveSampleLocationData(getApplicationContext()); // Load sample data
    }
}
