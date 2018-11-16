package com.scrumbums.donationboi.model.entities;

import android.support.annotation.NonNull;

import com.scrumbums.donationboi.model.UserRole;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Database entity representing a user account in the app.
 *
 * @author jdierberger3 and estrat6
 * @version 2.0
 */

public class User extends RealmObject {
    @PrimaryKey
    private int uid;

    private String name;
    private String username;
    private String email;
    private String password;
    private String role;

    /**
     * No-arg constructor for User
     */
    public User() { }

    /**
     * Create a new user
     *
     * @param name     The user's full name
     * @param username A username for this user
     * @param email    The user's email address
     * @param password Plaintext password for the user
     * @param role     The role to assign to this user
     */
    public User(String name, String username, String email, String password, UserRole role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;

        Realm realm = Realm.getDefaultInstance();
        // make sure we give this user a unique (i.e., auto-incrementing) ID in the database
        Number highestId = realm.where(User.class).max("uid");
        if (highestId == null) {
            this.uid = 1;
        } else {
            this.uid = highestId.intValue() + 1;
        }

        this.setRole(role);
    }

    /**
     * Get this user's ID
     * @return The user's ID
     */
    public int getUid() {
        return uid;
    }

    /**
     * Set this user's ID
     * @param uid The new user ID
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Get this user's full name
     * @return The user's full name
     */
    public String getName() {
        return name;
    }

    /**
     * Set this user's full name
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the username for this user
     * @return This user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set this user's username
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get this user's email address
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set this user's email address
     * @param email The new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get this user's password in plaintext
     * @return The user's password in plaintext
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set this user's password
     * @param password The new password, in plaintext
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get this user's role
     * @return The user's role
     */
    public UserRole getRole() {
        return UserRole.stringToUserRole(role);
    }

    private void setRole(UserRole role) {
        this.role = UserRole.fromUserRole(role);
    }

    @NonNull
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
