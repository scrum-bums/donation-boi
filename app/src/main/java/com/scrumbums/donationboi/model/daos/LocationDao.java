package com.scrumbums.donationboi.model.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.scrumbums.donationboi.model.entities.Location;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insertLocations(List<Location> locations);

}
