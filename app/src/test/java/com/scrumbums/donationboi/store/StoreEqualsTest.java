package com.scrumbums.donationboi.store;

import com.scrumbums.donationboi.model.entities.Location;
import com.scrumbums.donationboi.model.entities.Store;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class StoreEqualsTest {
    @Test
    public void storeEquals_null() {
        Location location = new Location("123 Some St", "GA", "Atlanta", 30332, 1.23f, 4.56f);
        Store store = new Store("Test Store", location, "123-456-7890", "example.com", "Donation " +
                "Center", 1);
        assertNotEquals(store, null);
    }

    @Test
    public void storeEquals_differentStoreNames() {
        Location location = new Location("123 Some St", "GA", "Atlanta", 30332, 1.23f, 4.56f);
        Store store1 = new Store("Test Store", location, "123-456-7890", "example.com", "Donation" +
                " Center", 1);
        Store store2 = new Store("Test Store 2", location, "123-456-7890", "example.com",
                "Donation" +
                        " Center", 2);
        assertNotEquals(store1, store2);
    }

    @Test
    public void storeEquals_differentStoreLocations() {
        Location location = new Location("123 Some St", "GA", "Atlanta", 30332, 1.23f, 4.56f);
        Location location2 = new Location("456 Some St", "GA", "Atlanta", 30332, 1.33f, 4.86f);

        assertNotEquals("Above locations should not be equal by their equals method " +
                "[different streetAddress]", location, location2);

        Store store1 = new Store("Test Store", location, "123-456-7890", "example.com", "Donation" +
                " Center", 1);
        Store store2 = new Store("Test Store", location2, "123-456-7890", "example.com",
                "Donation" +
                        " Center", 2);
        assertNotEquals(store1, store2);
    }

    @Test
    public void storeEquals_sameNameSameLocation() {
        Location location = new Location("123 Some St", "GA", "Atlanta", 30332, 1.23f, 4.56f);
        Location location2 = new Location("123 Some St", "GA", "Atlanta", 30332, 1.33f, 4.86f);

        assertEquals("Above locations should be equal by their equals method", location,
                location2);

        Store store1 = new Store("Test Store", location, "123-456-7890", "example.com", "Donation" +
                " Center", 1);
        Store store2 = new Store("Test Store", location2, "123-456-7890", "example.com",
                "Donation" +
                        " Center", 2);
        assertEquals(store1, store2);
    }

    @Test
    public void storeEquals_notStore() {
        Location location = new Location("123 Some St", "GA", "Atlanta", 30332, 1.23f, 4.56f);

        Store store = new Store("Test Store", location, "123-456-7890", "example.com", "Donation" +
                " Center", 1);
        assertNotEquals("Store and Location should not be equal", store, location);
    }
}