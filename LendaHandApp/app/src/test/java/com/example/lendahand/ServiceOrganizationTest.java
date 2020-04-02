package com.example.lendahand;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceOrganizationTest {

    private static String serviceOrgName = "Test Service Organization";
    private static String serviceOrgEmail = "test@serviceorganization.com";
    private static String serviceOrgPhone = "1234567890";
    private static String serviceOrgWebsite = "serviceorganization.com";
    private static String serviceOrgPassword = "S3rv!ce123";
    private static String serviceOrgDescription = "Test Description";
    private static String serviceOrgLogo = "Logo.jpg";
    private static String serviceOrgHeader = "Header.jpg";
/*  @BeforeClass
    public static void setup() {
        Database db = new Database();
        db.init();
        ServiceOrganization serviceOrganization = new ServiceOrganization(serviceOrgName, serviceOrgEmail, serviceOrgPhone, serviceOrgWebsite, serviceOrgPassword, serviceOrgDescription, serviceOrgLogo, serviceOrgHeader);
        db.addOrganization(serviceOrganization);
    }
 */

    @Test
    public void serviceOrganizationConstructor_isCorrect(){
        Database db = new Database();
        db.init();
        ServiceOrganization serviceOrganization = new ServiceOrganization(serviceOrgName, serviceOrgEmail, serviceOrgPhone, serviceOrgWebsite, serviceOrgPassword, serviceOrgDescription, serviceOrgLogo, serviceOrgHeader);
        db.addOrganization(serviceOrganization);
        ServiceOrganization serviceOrg = db.getOrganization(serviceOrgEmail);
        assertEquals(serviceOrgName, serviceOrg.getOrgName());
        assertEquals(serviceOrgEmail, serviceOrg.getOrgEmail());
        assertEquals(serviceOrgPhone, serviceOrg.getOrgPhone());
        assertEquals(serviceOrgWebsite, serviceOrg.getOrgWebsite());
        assertEquals(serviceOrgDescription, serviceOrg.getOrgDescription());
        assertEquals(serviceOrgLogo, serviceOrg.getOrgLogo());
        assertEquals(serviceOrgHeader, serviceOrg.getOrgHeader());

    }
    @Test
    public void serviceOrganizationEdit_isCorrect(){
        Database db = new Database();
        db.init();
        ServiceOrganization serviceOrganization = new ServiceOrganization(serviceOrgName, serviceOrgEmail, serviceOrgPhone, serviceOrgWebsite, serviceOrgPassword, serviceOrgDescription, serviceOrgLogo, serviceOrgHeader);
        db.addOrganization(serviceOrganization);
        String serviceOrgNameEdit = "Test Edit Service Organization";
        String serviceOrgEmailEdit = "testedit@serviceorganization.com";
        String serviceOrgPhoneEdit = "0987654321";
        String serviceOrgWebsiteEdit = "editserviceorganization.com";
        String serviceOrgPasswordEdit = "S3rv!ce123Edit";
        String serviceOrgDescriptionEdit = "Test Edit Description";
        String serviceOrgLogoEdit = "EditLogo.jpg";
        String serviceOrgHeaderEdit = "EditHeader.jpg";
        ServiceOrganization serviceO = db.getOrganization(serviceOrgEmail);
        serviceO.setOrgName(serviceOrgNameEdit);
        serviceO.setOrgEmail(serviceOrgEmailEdit);
        serviceO.setOrgPhone(serviceOrgPhoneEdit);
        serviceO.setOrgWebsite(serviceOrgWebsiteEdit);
        serviceO.setOrgPassword(serviceOrgPasswordEdit);
        serviceO.setOrgDescription(serviceOrgDescriptionEdit);
        serviceO.setOrgLogo(serviceOrgLogoEdit);
        serviceO.setOrgHeader(serviceOrgHeaderEdit);
        db.addOrganization(serviceO);
        ServiceOrganization serviceOrg = db.getOrganization(serviceOrgEmailEdit);
        assertEquals(serviceOrgNameEdit, serviceOrg.getOrgName());
        assertEquals(serviceOrgEmailEdit, serviceOrg.getOrgEmail());
        assertEquals(serviceOrgPhoneEdit, serviceOrg.getOrgPhone());
        assertEquals(serviceOrgWebsiteEdit, serviceOrg.getOrgWebsite());
        assertEquals(serviceOrgDescriptionEdit, serviceOrg.getOrgDescription());
        assertEquals(serviceOrgLogoEdit, serviceOrg.getOrgLogo());
        assertEquals(serviceOrgHeaderEdit, serviceOrg.getOrgHeader());
    }

    @Test
    //FIXME
    public void serviceOrganizationAddServiceOp_isCorrect(){
        Database db = new Database();
        db.init();
        ServiceOrganization serviceOrganization = new ServiceOrganization(serviceOrgName, serviceOrgEmail, serviceOrgPhone, serviceOrgWebsite, serviceOrgPassword, serviceOrgDescription, serviceOrgLogo, serviceOrgHeader);
        db.addOrganization(serviceOrganization);
        ServiceOpportunity serviceOpportunity = new ServiceOpportunity("service op name", "service op subtitle", "service op desc", "service op contactName", "service op contactEmail", "service op contactPhoto", false, "03/10/2020", "1600", "03/26/2020", "1600", "UA", "18", "None", "header.jpg", "eventphoto.jpg", "", "org1");
        serviceOrganization.addOrgServiceOp(serviceOpportunity);
        db.addOrganization(serviceOrganization);
        ServiceOrganization serviceOrg = db.getOrganization(serviceOrgEmail);
        assertEquals(serviceOrg.getOrgServiceOpsList().size(), 1);
        assertEquals(serviceOpportunity, serviceOrg.getOrgServiceOp("service op name"));
    }
}
