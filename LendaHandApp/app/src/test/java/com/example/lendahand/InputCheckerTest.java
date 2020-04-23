package com.example.lendahand;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class InputCheckerTest {
    private static String blankTest = "";
    private static String passwordTest = "Lendahand2!";
    private static String dateTest = "09/09/2020";
    private static String timeTest = "9:00";
    private static String dateTest2 = "09-09-2020";
    private static String timetest2 = "12 00";
    private static InputChecker check;

    @Before
    public void setUp() throws Exception {
        check = new InputChecker();
    }

    @Test
    public void isBlank() {
        assertEquals(check.isBlank(blankTest, "testField"), "testField cannot be blank.\n");
        assertEquals(check.isBlank(passwordTest, "testField"), "");
    }

    @Test
    public void isPasswordValid() {
        assertEquals("", check.isPasswordValid(passwordTest));
        assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one special character (@, #, $, %, !), and be at least 8 characters long.\n", check.isPasswordValid(dateTest));
    }

    @Test
    public void isDateValid() {
        assertEquals("", check.isDateValid(dateTest));
        assertEquals("Date must be formatted 'MM/dd/yyyy'\n", check.isDateValid(dateTest2));
        assertEquals("", check.isTimeValid(timeTest));
        assertEquals("Time must be 'HH:mm'\n", check.isTimeValid(timetest2));
    }
}