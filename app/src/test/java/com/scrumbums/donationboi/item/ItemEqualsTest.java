package com.scrumbums.donationboi.item;

import android.content.Context;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Store;

import org.junit.Test;

import io.realm.Realm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests Item's equals method.
 * @author jdierberger3
 * @version 1.0
 */
public class ItemEqualsTest {

    /**
     * Test reference equality.
     */
    @Test
    public void testItemEqualsReferenceEquals() {
        Item i1 = new Item("TestItem", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        // assertEquals calls equals method
        assertEquals("Expected two reference-equal items to be .equals", i1,
                i1);
    }

    /**
     * Test null input.
     */
    @Test
    public void testItemEqualsNull() {
        Item i1 = new Item("TestItem", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Expected .equals to be false when null", i1,
                null);
    }

    /**
     * Test non-Item input.
     */
    @Test
    public void testItemEqualsNonItem() {
        Item i1 = new Item("TestItem", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        Object o2 = new Object();
        assertNotEquals("Expected .equals to be false when null", i1,
                o2);
    }

    /**
     * Test null names.
     */
    @Test
    public void testItemEqualsNullName() {
        Item i1 = new Item("", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item(null, "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with callee null name, argument not.", i1,
                i2);

        i1 = new Item(null, "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with argument null name, callee not.", i1,
                i2);

        i1 = new Item(null, "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item(null, "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with both null names.", i1,
                i2);
    }

    /**
     * Test non-null names.
     */
    @Test
    public void testItemEqualsNonNullName() {
        Item i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with equal names.", i1,
                i2);

        i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("b", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items not equal with non-equal names.", i1,
                i2);

        i1 = new Item("b", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items not equal with non-equal names.", i1,
                i2);
    }

    /**
     * Test prices.
     */
    @Test
    public void testItemEqualsPrices() {
        Item i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with equal prices.", i1,
                i2);

        i1 = new Item("a", "Description1", 1.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with non-equal prices.", i1,
                i2);

        i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 1.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with non-equal prices.", i1,
                i2);
    }

    /**
     * Test null types.
     */
    @Test
    public void testItemEqualsNullTypes() {
        Item i1 = new Item("a", "Description1", 0.0, null,
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with caller null, callee non-null types.",
                i1, i2);

        i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, null,
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with caller non-null, callee null types.",
                i1, i2);

        i1 = new Item("a", "Description1", 0.0, null,
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, null,
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with both null types.",
                i1, i2);
    }

    /**
     * Test non-null types.
     */
    @Test
    public void testItemEqualsNonNullTypes() {
        Item i1 = new Item("a", "Description1", 0.0, "a",
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with non-equal types.",
                i1, i2);

        i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "a",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with non-equal types.",
                i1, i2);

        i1 = new Item("a", "Description1", 0.0, "a",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "a",
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with equal types.",
                i1, i2);
    }

    /**
     * Test null descriptions.
     */
    @Test
    public void testItemEqualsNullDescriptions() {
        Item i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item("a", null, 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals(
                "Items equal with caller null, callee non-null descriptions.",
                i1, i2);

        i1 = new Item("a", null, 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals(
                "Items equal with caller non-null, callee null descriptions.",
                i1, i2);

        i1 = new Item("a", null, 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", null, 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with both null descriptions.",
                i1, i2);
    }

    /**
     * Test non-null descriptions.
     */
    @Test
    public void testItemEqualsNonNullDescriptions() {
        Item i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        Item i2 = new Item("a", "a", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with non-equal descriptions.",
                i1, i2);

        i1 = new Item("a", "a", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertNotEquals("Items equal with non-equal descriptions.",
                i1, i2);

        i1 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        i2 = new Item("a", "Description1", 0.0, "TestType",
                Categories.CLOTHING, new Store(), 10);
        assertEquals("Items not equal with equal descriptions.",
                i1, i2);
    }
}