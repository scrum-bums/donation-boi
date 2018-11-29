package com.scrumbums.donationboi.miscmodel;

import com.scrumbums.donationboi.model.Categories;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FromCategoriesTest {
    @Test
    public void testFromCategories() {
        assertEquals("Electronics", Categories.fromCategories(Categories.ELECTRONICS));
        assertEquals("Clothing", Categories.fromCategories(Categories.CLOTHING));
        assertEquals("Hat", Categories.fromCategories(Categories.HAT));
        assertEquals("Kitchen", Categories.fromCategories(Categories.KITCHEN));
        assertEquals("Household", Categories.fromCategories(Categories.HOUSEHOLD));
        assertEquals("Other", Categories.fromCategories(Categories.OTHER));
    }
}
