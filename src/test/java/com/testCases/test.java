package com.testCases;

import com.testBase.BaseClass;

import java.io.IOException;

import org.testng.annotations.Test;

public class test extends BaseClass {
    @Test
    public void testLogin() throws IOException {
        // Fetch email and password from the second row
        String[] credentials = getSecondRowCredentials();
        String email = credentials[0];
        String password = credentials[1];

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        // Use the credentials in your test
        // Example: loginPage.loginWithCredentials(email, password);
    }
}
