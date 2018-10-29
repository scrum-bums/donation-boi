package com.scrumbums.donationboi.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELEct * FROM user WHERE email=:email AND password=:password")
    User getUser(String email, String password);

    @Insert
    void createUser(User user);
}
