package com.example.lendahand;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VolunteerOrganizationTest {
    private static String firstName = "Test Volunteer FirstName";
    private static String lastName = "Test Volunteer LastName";
    private static String email = "test@volunteer.com";
    private static String phone = "1234567890";
    private static String dateOfBirth = "01/01/2001";
    private static String password = "V0lunt3er!123";
    private static Volunteer volunteer;


    @BeforeClass
    public static void setup() {
        volunteer = new Volunteer(firstName, lastName, email, phone, dateOfBirth, password);
    }

    @Test
    public void volunteerConstructor_isCorrect(){
        assertEquals(firstName, volunteer.getFirstName());
        assertEquals(lastName, volunteer.getLastName());
        assertEquals(phone, volunteer.getPhone());
        assertEquals(dateOfBirth, volunteer.getDateOfBirth());
        assertEquals(password, volunteer.getPassword());
    }

    @Test
    public void volunteerEdit_isCorrect(){
        String volunteerFirstNameEdit = "Test Edit Volunteer FirstName";
        String volunteerLastNameEdit = "Test Edit Volunteer LastName";
        String volunteerEmailEdit = "testedit@volunteer.com";
        String volunteerPhoneEdit = "0987654321";
        String volunteerDateOfBirthEdit = "02/02/2002";
        String volunteerPasswordEdit = "V0lunt3er!321";
        volunteer.editVolunteer(volunteerFirstNameEdit, volunteerLastNameEdit, volunteerEmailEdit, volunteerPhoneEdit, volunteerDateOfBirthEdit, volunteerPasswordEdit);
        assertEquals(volunteerFirstNameEdit, volunteer.getFirstName());
        assertEquals(volunteerLastNameEdit, volunteer.getLastName());
        assertEquals(volunteerEmailEdit, volunteer.getEmail());
        assertEquals(volunteerPhoneEdit, volunteer.getPhone());
        assertEquals(volunteerDateOfBirthEdit, volunteer.getDateOfBirth());
        assertEquals(volunteerPasswordEdit, volunteer.getPassword());
    }

    @Test
    public void volunteerAddTags_isCorrect(){
        final ArrayList<String> userSelectedTags = new ArrayList<String>();
        userSelectedTags.add("ExampleTag");
        volunteer.setTags(userSelectedTags);
        assertEquals(volunteer.getTags().size(), 1);
        assertEquals("ExampleTag", volunteer.getTags().get(0));
    }
}
