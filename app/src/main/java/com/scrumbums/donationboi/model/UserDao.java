package com.scrumbums.donationboi.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE email=:email AND password=:password")
    Single<User> getUser(String email, String password);

    @Insert
    void createUser(User user);
}
