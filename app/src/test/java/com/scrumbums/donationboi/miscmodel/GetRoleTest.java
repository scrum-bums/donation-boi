package com.scrumbums.donationboi.miscmodel;

import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.UserRole;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetRoleTest {
    @Test
    public void testGetRole() {
        assertEquals(UserRole.USER, UserRole.getRole("User"));
        assertEquals(UserRole.EMPLOYEE, UserRole.getRole("Employee"));
        assertEquals(UserRole.ADMINISTRATOR, UserRole.getRole("Administrator"));
        assertEquals(UserRole.MANAGER, UserRole.getRole("Manager"));
    }
}
