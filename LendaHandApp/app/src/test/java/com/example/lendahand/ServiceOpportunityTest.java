package com.example.lendahand;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ServiceOpportunityTest {

    private static String serviceOpName = "Test";
    private static String serviceOpSubtitle = "SubTest";
    private static String serviceOpDescription = "DescTest";
    private static String serviceOpContactName = "ContactNameTest";
    private static String serviceOpContactEmail = "Contact@Contact.com";
    private static String serviceOpContactPhone = "9019019001";
    private static boolean serviceOpRepeat = false;
    private static String serviceOpDate = "09/09/2020";
    private static String serviceOpTime = "9:00";
    private static String serviceOpCutoffDate = "09/09/2020";
    private static String serviceOpCutoffTime = "9:00";
    private static String serviceOpLocation = "LocationTest";
    private static String serviceOpAgeReq = "AgeTest";
    private static String serviceOpAdditionalReq = "ReqTest";
    private static File serviceOpHeaderPhoto = null;
    private static File serviceOpEventPhoto = null;
    private static String serviceOpOrgEmail = "ABCD@Hab.com";
    private static String serviceOpId = "TestId";
    public ServiceOpportunity newOp;

    @Before
    public void setUp(){
        newOp = new ServiceOpportunity(
                serviceOpName,
                serviceOpSubtitle,
                serviceOpDescription,
                serviceOpContactName,
                serviceOpContactEmail,
                serviceOpContactPhone,
                serviceOpRepeat,
                serviceOpDate,
                serviceOpTime,
                serviceOpCutoffDate,
                serviceOpCutoffTime,
                serviceOpLocation,
                serviceOpAgeReq,
                serviceOpAdditionalReq,
                serviceOpHeaderPhoto,
                serviceOpEventPhoto,
                serviceOpOrgEmail,
                serviceOpId
        );
    }

    @Test
    public void createServiceOpTest(){

        assertEquals(serviceOpName, newOp.getOpName());
        assertEquals(serviceOpSubtitle, newOp.getOpSubtitle());
        assertEquals(serviceOpDescription, newOp.getOpDescription());
        assertEquals(serviceOpContactName, newOp.getOpContactName());
        assertEquals(serviceOpContactEmail, newOp.getOpContactEmail());
        assertEquals(serviceOpContactPhone, newOp.getOpContactPhone());
        assertEquals(serviceOpRepeat, newOp.getOpRepeat());
        assertEquals(serviceOpDate, newOp.getOpDate());
        assertEquals(serviceOpTime, newOp.getOpTime());
        assertEquals(serviceOpCutoffDate, newOp.getOpCutoffDate());
        assertEquals(serviceOpCutoffTime, newOp.getOpCutoffTime());
        assertEquals(serviceOpLocation, newOp.getOpLocation());
        assertEquals(serviceOpAgeReq, newOp.getOpAgeReq());
        assertEquals(serviceOpAdditionalReq, newOp.getOpAdditionalReq());
        assertEquals(serviceOpHeaderPhoto, newOp.getOpHeaderPhoto());
        assertEquals(serviceOpEventPhoto, newOp.getOpEventPhoto());
        assertEquals(serviceOpId, newOp.getId());
    }

    @Test
    public void testSetName(){
        newOp.setOpName("False");
        assertNotEquals(serviceOpName, newOp.getOpName());
    }

    @Test
    public void testSetOpRepeat(){
        newOp.setOpRepeat(true);
        assertTrue(newOp.getOpRepeat());
    }

    @Test
    public void testServiceOrgEmail(){
        newOp.setOpServiceOrg("Email@gmail.com");
        assertNotEquals(serviceOpOrgEmail, newOp.getOpServiceOrg());
        assertEquals("Email@gmail.com", newOp.getOpServiceOrg());
    }
}
