package com.scrumbums.donationboi;

import com.scrumbums.donationboi.model.entities.Location;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests Item's equals method.
 * @author gessa8
 * @version 1.0
 */
public class LocationEqualsTest {

    @Test
    public void locationEqualsNull() {
        Location loc = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        assertNotEquals(loc, null);
    }

    @Test
    public void locationEqualsDiffAddress() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("552 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        assertNotEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsDiffState() {
        Location loc1 = new Location("555 Example st","Alabama", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        assertNotEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsDiffCity() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("555 Example st","Georgia", "Athens", 30245, 1.23f, 12.3f);
        assertNotEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsDiffZip() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("555 Example st","Georgia", "Atlanta", 30246, 1.23f, 12.3f);
        assertNotEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsDiffLat() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.2435f, 12.3f);
        assertNotEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsDiffLong() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3123213f);
        assertNotEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsSuccess() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        assertEquals(loc1, loc2);
    }

    @Test
    public void locationEqualsReference() {
        Location loc1 = new Location("555 Example st","Georgia", "Atlanta", 30245, 1.23f, 12.3f);
        Location loc2 = loc1;
        assertEquals(loc1, loc2);
    }

}
