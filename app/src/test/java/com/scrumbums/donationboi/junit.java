package com.scrumbums.donationboi;

import com.scrumbums.donationboi.model.Categories;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class junit {
    @Test
    public void testGetCategory() {
        assertEquals(Categories.ELECTRONICS, Categories.getCategory("Electronics"));
        assertEquals(null, Categories.getCategory("electronics"));
        assertEquals(null, Categories.getCategory("electra"));
        assertEquals(Categories.CLOTHING, Categories.getCategory("Clothing"));
        assertEquals(null, Categories.getCategory("clothing"));
        assertEquals(null, Categories.getCategory("clothes"));
        assertEquals(Categories.HAT, Categories.getCategory("Hat"));
        assertEquals(null, Categories.getCategory("hat"));
        assertEquals(null, Categories.getCategory("het"));
        assertEquals(Categories.KITCHEN, Categories.getCategory("Kitchen"));
        assertEquals(null, Categories.getCategory("kitchen"));
        assertEquals(null, Categories.getCategory("kitch"));
        assertEquals(Categories.HOUSEHOLD, Categories.getCategory("Household"));
        assertEquals(null, Categories.getCategory("household"));
        assertEquals(null, Categories.getCategory("house"));
        assertEquals(Categories.OTHER, Categories.getCategory("Other"));
        assertEquals(null, Categories.getCategory("other"));
        assertEquals(null, Categories.getCategory("othr"));
    }
}
