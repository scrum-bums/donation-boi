package com.scrumbums.donationboi.model;

import com.scrumbums.donationboi.model.util.AccountValidation;

/**
 * Common superclass to Employee, Manager, User, etc. Defines things common
 * to users for back-end purposes; not an actual part of the domain.
 *
 * @author jdierberger3
 * @version 1.0
 */
public abstract class AbstractUser {

    private final String username;
    private String name;
    private String emailAddress;
    private String password;

    /**
     * Create an AbstractUser.
     * @param username The username of this user. Must be unique.
     * @param name The name of this user.
     * @param emailAddress The email address of this user.
     */
    AbstractUser(String username, String name, String emailAddress, String password) {
        this.username = username;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password; // TODO : not this lol
    }

    /**
     * Set the name of this user.
     * @param name The new name of this user. Must be non-null and non-empty.
     * This includes containing non-whitespace characters.
     */
    public void setName(String name) {
        if (AccountValidation.isStringNonEmpty(name)) {
            this.name = name;
        }
    }

    /**
     * Get the name of this user.
     * @return The name of this user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the email address of this user.
     * @param email The new email address of this user. Must be non-null and
     * be a valid email address.
     */
    public void setEmailAddress(String email) {
        if (AccountValidation.isValidEmail(email)) {
            this.emailAddress = email;
        }
    }

    /**
     * Get the email address of this user.
     * @return The email address of this user.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Get the username of this user.
     * @return The username of this user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the password of this user.
     * @param password The new password for this user. Must be non-null.
     */
    public void setPassword(String password) {
        if (AccountValidation.isValidPassword(password)) {
            this.password = password;
        }
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public int hashCode() {
        int hash = 0x31313131; // magic number, idc what it is really :)
        hash = username.hashCode() * 17 + hash;
        hash = name.hashCode() * 17 + hash;
        hash = emailAddress.hashCode() * 17 + hash;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AbstractUser)) return false;
        AbstractUser a = (AbstractUser) o;
        return name.equals(a.name) && username.equals(a.username)
                && emailAddress.equals(emailAddress);
    }

    // TODO : Serialization?
}