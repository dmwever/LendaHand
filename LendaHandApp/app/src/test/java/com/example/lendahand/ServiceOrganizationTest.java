package com.example.lendahand;

import com.amazonaws.util.StringUtils;

import org.junit.Assert;
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
    private static ServiceOrganization serviceOrganization;

    @BeforeClass
    public static void setup() {
        serviceOrganization = new ServiceOrganization(serviceOrgName, serviceOrgEmail, serviceOrgPhone, serviceOrgWebsite, serviceOrgPassword, serviceOrgDescription, serviceOrgLogo, serviceOrgHeader);
    }

    @Test
    public void serviceOrganizationConstructor_isCorrect(){
        assertEquals(serviceOrgName, serviceOrganization.getOrgName());
        assertEquals(serviceOrgEmail, serviceOrganization.getOrgEmail());
        assertEquals(serviceOrgPhone, serviceOrganization.getOrgPhone());
        assertEquals(serviceOrgWebsite, serviceOrganization.getOrgWebsite());
        assertEquals(serviceOrgDescription, serviceOrganization.getOrgDescription());
        assertEquals(serviceOrgLogo, serviceOrganization.getOrgLogo());
        assertEquals(serviceOrgHeader, serviceOrganization.getOrgHeader());

    }

    @Test
    public void serviceOrganizationEdit_isCorrect(){
        String serviceOrgNameEdit = "Test Edit Service Organization";
        String serviceOrgEmailEdit = "testedit@serviceorganization.com";
        String serviceOrgPhoneEdit = "0987654321";
        String serviceOrgWebsiteEdit = "editserviceorganization.com";
        String serviceOrgPasswordEdit = "S3rv!ce123Edit";
        String serviceOrgDescriptionEdit = "Test Edit Description";
        String serviceOrgLogoEdit = "EditLogo.jpg";
        String serviceOrgHeaderEdit = "EditHeader.jpg";
        serviceOrganization.setOrgName(serviceOrgNameEdit);
        serviceOrganization.setOrgEmail(serviceOrgEmailEdit);
        serviceOrganization.setOrgPhone(serviceOrgPhoneEdit);
        serviceOrganization.setOrgWebsite(serviceOrgWebsiteEdit);
        serviceOrganization.setOrgPassword(serviceOrgPasswordEdit);
        serviceOrganization.setOrgDescription(serviceOrgDescriptionEdit);
        serviceOrganization.setOrgLogo(serviceOrgLogoEdit);
        serviceOrganization.setOrgHeader(serviceOrgHeaderEdit);
        assertEquals(serviceOrgNameEdit, serviceOrganization.getOrgName());
        assertEquals(serviceOrgEmailEdit, serviceOrganization.getOrgEmail());
        assertEquals(serviceOrgPhoneEdit, serviceOrganization.getOrgPhone());
        assertEquals(serviceOrgWebsiteEdit, serviceOrganization.getOrgWebsite());
        assertEquals(serviceOrgDescriptionEdit, serviceOrganization.getOrgDescription());
        assertEquals(serviceOrgLogoEdit, serviceOrganization.getOrgLogo());
        assertEquals(serviceOrgHeaderEdit, serviceOrganization.getOrgHeader());
    }

    @Test
    public void serviceOrganizationAddServiceOp_isCorrect(){
        ServiceOpportunity serviceOpportunity = new ServiceOpportunity("service op name", "service op subtitle", "service op desc", "service op contactName", "service op contactEmail", "service op contactPhoto", false, "03/10/2020", "1600", "03/26/2020", "1600", "UA", "18", "None", "header.jpg", "eventphoto.jpg", "org1");
        serviceOrganization.addOrgServiceOp(serviceOpportunity);
        assertEquals(serviceOrganization.getOrgServiceOpsList().size(), 1);
        assertEquals(serviceOpportunity, serviceOrganization.getOrgServiceOp("service op name"));
    }
}
