package com.scrumbums.donationboi.model.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.scrumbums.donationboi.model.entities.Location;
import com.scrumbums.donationboi.model.entities.Store;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Dao
public abstract class StoreDao {

    @Query("SELECT * FROM store")
    public abstract Single<List<Store>> getAll();

    @Query("SELECT * FROM store WHERE storeId=:id")
    public abstract Single<Store> getStorebyId(int id);

    public void insertStoresAndLocations(List<Store> stores, List<Location> locations) {
        Completable.fromAction(() -> insertLocations(locations)).subscribe(
                () -> {
                    Completable.fromAction( ()->)
                }
        )
    };

    @Insert
    public abstract void insertLocations(List<Location> locations);

    @Insert
    public abstract void insertStores(List<Store> stores);

    public void insertStoreWithLocation(Store store, Location location) {
        store.setLocationId(location.getLocationId());
        insertStore(store);
    }

    @Query("SELECT * FROM location WHERE locationId=:id")
    public abstract Single<Location> getLocation(int id);

    public List<Store> getStoresWithLocations() {
        List<Store> stores = getAll().blockingGet();
        for (Store store : stores) {
            store.setLocation(getLocation(store.getLocationId()).blockingGet());
        }

        return stores;
    }
}
