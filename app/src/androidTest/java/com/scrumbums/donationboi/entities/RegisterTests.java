package com.scrumbums.donationboi.entities;

import android.support.test.InstrumentationRegistry;

import com.scrumbums.donationboi.model.UserRole;
import com.scrumbums.donationboi.model.entities.User;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegisterTests {
    private static Realm testRealm;

    @BeforeClass
    public static void setup() {
        RealmConfiguration testConfig = new RealmConfiguration.Builder()
                .inMemory()
                .name("test-realm")
                .build();

        testRealm = Realm.getInstance(testConfig);
    }

    @AfterClass
    public static void tearDownClass() {
        testRealm.close();
    }

    @Test
    public void register_empty_database() {
        User testUser = new User("John Doe", "jdoe3", "john@doe.com", "test1234", UserRole.USER);

        boolean result = DatabaseAbstraction.register(InstrumentationRegistry.getContext(),
                testUser, testRealm);

        assertTrue("Register method should return success code", result);

        RealmQuery<User> findUser = testRealm.where(User.class)
                .equalTo("email", testUser.getEmail())
                .equalTo("password", testUser.getPassword());
        User u = findUser.findFirst();

        assertNotNull("New user is created", u);
        assertEquals("UIDs should match", testUser.getUid(), u.getUid());
    }

    @Test
    public void register_existing_user_unique_email() {
        //register_empty_database(); // Create a user
        User testUser = new User("John Doe", "jdoe3", "john@doe.com", "test1234", UserRole.USER);

        DatabaseAbstraction.register(InstrumentationRegistry.getContext(),
                testUser, testRealm);
        RealmResults<User> checkUserCount = testRealm.where(User.class).findAll();
        assertEquals("User count should be 1", 1, checkUserCount.size());

        User testUser2 = new User("James Doe", "james3", "james@doe.com", "test1234", UserRole
                .USER);

        boolean result = DatabaseAbstraction.register(InstrumentationRegistry.getContext(),
                testUser2, testRealm);
        assertTrue("Register method should return success code", result);

        RealmQuery<User> findUser = testRealm.where(User.class)
                .equalTo("email", testUser2.getEmail())
                .equalTo("password", testUser2.getPassword());
        User u = findUser.findFirst();

        assertNotNull("New user is created", u);
        assertEquals("UIDs should match", testUser2.getUid(), u.getUid());
    }

    @After
    public void tearDown() {
        if (!testRealm.isInTransaction()) {
            testRealm.beginTransaction();
        }
        testRealm.deleteAll();
        testRealm.commitTransaction();

    }
}