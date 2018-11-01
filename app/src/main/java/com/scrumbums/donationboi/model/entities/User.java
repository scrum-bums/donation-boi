package com.scrumbums.donationboi.model.entities;

import com.scrumbums.donationboi.model.UserRole;
import com.scrumbums.donationboi.model.util.Converters;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Database entity representing a user account in the app.
 *
 * @author jdierberger3 and estrat6
 * @version 2.0
 */

public class User extends RealmObject {
    @Ignore
    private static int userCount = 0;

    @PrimaryKey
    private int uid;

    private String name;
    private String username;
    private String email;
    private String password;
    private String role;

    public User() { }

    public User(String name, String username, String email, String password, UserRole role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        userCount++;
        this.uid = userCount;
        setRole(role);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return Converters.stringToUserRole(role);
    }

    public void setRole(UserRole role) {
        this.role = Converters.fromUserRole(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
