package com.scrumbums.donationboi.util;

import com.scrumbums.donationboi.model.entities.Location;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.AccountValidation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class AccountValidationTest {

    @Test
    public void testValidEmail() {
        assertFalse("a@a.a is valid email", AccountValidation.isInvalidEmail("a@a.a"));
        assertFalse("1@a.1 is valid email", AccountValidation.isInvalidEmail("1@a.1"));
        assertFalse("jdierb_rger1@gma1l.c0m is valid email", AccountValidation.isInvalidEmail("jdierb_rger1@gma1l.c0m"));
    }

    @Test
    public void testInvalidEmail() {
        assertTrue("a is not valid email", AccountValidation.isInvalidEmail("a"));
        assertTrue("a@ is not valid email", AccountValidation.isInvalidEmail("a@"));
        assertTrue("a@. is not valid email", AccountValidation.isInvalidEmail("a@."));
        assertTrue("a@a. is not valid email", AccountValidation.isInvalidEmail("a@a."));
        assertTrue("a@.a is not valid email", AccountValidation.isInvalidEmail("a@.a"));
        assertTrue("@a.a is not valid email", AccountValidation.isInvalidEmail("@a.a"));
        assertTrue("@. is not valid email", AccountValidation.isInvalidEmail("@."));
        assertTrue(". is not valid email", AccountValidation.isInvalidEmail("."));
        assertTrue("@ is not valid email", AccountValidation.isInvalidEmail("@"));
    }

    @Test
    public void testValidPassword() {
        assertFalse("AAaaa@11 is a valid password", AccountValidation.isInvalidPassword("AAaaa@11"));
        assertFalse("!a_cAjC%1$4^hK is a valid password", AccountValidation.isInvalidPassword("!a_cA_C%1$4^hK"));
    }

    @Test
    public void testInvalidPassword() {
        assertTrue("AAaa@11 is not a valid password", AccountValidation.isInvalidPassword("AAaa@11"));
        assertTrue(" is not a valid password", AccountValidation.isInvalidPassword(""));
        assertTrue("\" is not a valid password", AccountValidation.isInvalidPassword("\""));
    }
}