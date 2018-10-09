package com.scrumbums.donationboi.model.util;

import com.scrumbums.donationboi.model.User;

import java.util.HashSet;

/**
 * Abstraction of the database. Handles useful database functions through an
 * abstraction layer (i.e methods) but allows reconfiguration of the database
 * behind the scenes (local for demos, Firebase, etc.)
 *
 * @author jdierberger3
 */
public final class DatabaseAbstraction {

    /**
     * do not use.
     */
    private DatabaseAbstraction() { }

    /**
     * Local databse while we wait on Firebase.
     */
    private static final HashSet<User> DATABASE = new HashSet<User>();



}
