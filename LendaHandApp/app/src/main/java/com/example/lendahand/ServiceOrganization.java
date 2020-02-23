package com.example.lendahand;

import java.io.Serializable;

public class ServiceOrganization implements Serializable {
    private String orgName;
    private String orgEmail;
    private String orgPhone;
    private String orgWebsite;
    private String orgPassword;
    private String orgDescription;
    public ServiceOrganization(String name, String email, String phone, String website, String password, String description){
        orgName = name;
        orgEmail = email;
        orgPhone = phone;
        orgWebsite = website;
        orgPassword = password;
        orgDescription = description;
    }

    public void setOrgName(String name){
        orgName = name;
    }

    public String getOrgName(){
        return orgName;
    }


    public void setOrgEmail(String email){
        orgEmail = email;
    }

    public String getOrgEmail(){
        return orgEmail;
    }


    public void setOrgPhone(String phone){
        orgPhone = phone;
    }

    public String getOrgPhone(){
        return orgPhone;
    }


    public void setOrgWebsite(String website){
        orgWebsite = website;
    }

    public String getOrgWebsite(){
        return orgWebsite;
    }


    public void setOrgPassword(String password){
        orgPassword = password;
    }

    public String getOrgPassword(){
        return orgPassword;
    }


    public void setOrgDescription(String description){
        orgDescription = description;
    }

    public String getOrgDescription(){
        return orgDescription;
    }

    public void displayServiceOrg(){
        System.out.println(orgName);
        System.out.println(orgEmail);
        System.out.println(orgPhone);
        System.out.println(orgWebsite);
        System.out.println(orgPassword);
        System.out.println(orgDescription);
    }
}