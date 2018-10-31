package com.scrumbums.donationboi.model.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.scrumbums.donationboi.model.entities.Store;

import java.util.List;

@Dao
public interface StoreDao {

    @Query("SELECT * FROM store")
    List<Store> getAll();

    @Query("SELECT * FROM store WHERE storeId=:id")
    Store getStorebyId(int id);

    @Insert
    public void insertStores(Store ... stores);
}
