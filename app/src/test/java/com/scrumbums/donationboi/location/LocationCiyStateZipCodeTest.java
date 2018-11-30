package com.scrumbums.donationboi.location;

import com.scrumbums.donationboi.model.entities.Location;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests Location's toString method.
 * @author jdierberger1
 * @version 1.0
 */
public class LocationCiyStateZipCodeTest {

    @Test
    public void testAllNull() {
        Location loc = new Location(null,null, null, 0, 1.23f, 12.3f);
        assertEquals("Address: not listed", loc.toString());
    }

    @Test
    public void testStreetAddress() {
        Location loc = new Location("123 Ex St.",null, null, 0, 1.23f, 12.3f);
        assertEquals("Address: 123 Ex St., (1.23, 12.3)", loc.toString());
    }

    @Test
    public void testState() {
        Location loc = new Location("123 Ex St.","State of Existence", null, 0, 1.23f, 12.3f);
        assertEquals("Address: 123 Ex St., State of Existence, (1.23, 12.3)", loc.toString());
    }

    @Test
    public void testJustState() {
        Location loc = new Location(null,"State of Existence", null, 0, 1.23f, 12.3f);
        assertEquals("Address: , State of Existence, (1.23, 12.3)", loc.toString());
    }

    @Test
    public void testCity() {
        Location loc = new Location("123 Ex St.","State of Existence", "Nowhere", 0, 1.23f, 12.3f);
        assertEquals("Address: 123 Ex St., Nowhere, State of Existence, (1.23, 12.3)", loc.toString());
    }

    @Test
    public void testZipCode() {
        Location loc = new Location("123 Ex St.","State of Existence", "Nowhere", 12345, 1.23f, 12.3f);
        assertEquals("Address: 123 Ex St., Nowhere, State of Existence, 12345, (1.23, 12.3)", loc.toString());
    }

}
