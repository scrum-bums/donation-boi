package com.scrumbums.donationboi.item;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Location;
import com.scrumbums.donationboi.model.entities.Store;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import org.junit.Assert;


public class ItemToStringTest {
    Store store1;
    Store store2;
    Store store2_0;
    Location location1;
    Location location2;
    Location location3;

    @Before
    public void beforeTests() {
        location1 = new Location("Street1", "Georgia", "Atlanta",
                12345, 1.0f, 1.0f);
        location2 = new Location("Street2", "Florida", "Didney Worl",
                12345, 2.0f, 2.0f);
        location3 = new Location("Street1", "Georgia", "Atlanta",
                12345, 1.0f, 1.0f);
        store1 = new Store("Store1", location1, "000-000-0000",
                "www.website.com", "DonationCenter", 123);
        store2 = new Store("Store2", location2, "123-456-7890",
                "www.website.org", "Drop Off",456);
        store2_0 = new Store("Store1", location3, "987-654-3210",
                "www.website.gov", "Store", 789);
    }

    @Test
    public void noArgsItem() {
        Item tempItem = new Item();
        String tempString = "Name: null" + "\n" + "Description: not listed";
        Assert.assertTrue(tempItem.toString() instanceof String);
        assertEquals(tempItem.toString(), tempString);
    }

    @Test
    public void withDescriptionToString() {
        Item tempItem = new Item("Name of Item", "Test Description", 50.00f,
                "Random Type", Categories.KITCHEN, store1, 123);
        String tempString = "Name: " + "Name of Item" + "\n" + "Description: "
                + "Test Description";
        String wrongString = "Invalid String";
        assertEquals(tempItem.toString(), tempString);
        Assert.assertFalse(tempItem.toString().equals(wrongString));
    }

    @Test
    public void withNoDescription() {
        Item tempItem = new Item("Name of Item", null, 500.00, "Temporary Type",
                Categories.HOUSEHOLD, store2, 456);
        String tempString = "Name: " + "Name of Item" + "\n"
                + "Description: not listed";
        String wrongString = "Name: " + "Name of Item" + "\n"
                + "Description: null";
        assertEquals(tempItem.toString(), tempString);
        Assert.assertFalse(tempItem.toString().equals(wrongString));
    }
}
