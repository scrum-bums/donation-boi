package com.scrumbums.donationboi.miscmodel;

import com.scrumbums.donationboi.model.UserRole;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FromUserRoleTest {
    @Test
    public void testFromUserRole() {
        assertEquals("User", UserRole.fromUserRole(UserRole.USER));
        assertEquals("Employee", UserRole.fromUserRole(UserRole.EMPLOYEE));
        assertEquals("Administrator", UserRole.fromUserRole(UserRole.ADMINISTRATOR));
        assertEquals("Manager", UserRole.fromUserRole(UserRole.MANAGER));
    }
}
