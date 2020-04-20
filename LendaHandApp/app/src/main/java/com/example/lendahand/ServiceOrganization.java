package com.example.lendahand;

import android.net.Uri;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceOrganization implements Serializable {
    private String orgName;
    private String orgEmail;
    private String orgPhone;
    private String orgWebsite;
    private String orgPassword;
    private String orgDescription;
    private File orgLogo;
    private File orgHeader;
    private HashMap<String, ServiceOpportunity> orgServiceOps;


    public ServiceOrganization(String name, String email, String phone, String website, String password, String description, File logo, File header){
        orgName = name;
        orgEmail = email;
        orgPhone = phone;
        orgWebsite = website;
        orgPassword = password;
        orgDescription = description;
        orgLogo = logo;
        orgHeader = header;
        orgServiceOps = new HashMap<>();
    }

    public ServiceOrganization(String ID) {
        this.orgEmail = ID;
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


    public void setOrgLogo(File logo){
        orgLogo = logo;
    }

    public File getOrgLogo(){
        return orgLogo;
    }


    public void setOrgHeader(File header){
        orgHeader = header;
    }

    public File getOrgHeader(){ return orgHeader; }

    public void editServiceOrg(String name, String phone, String email, String website, String password, String description, File logo, File header){
        this.setOrgName(name);
        this.setOrgPhone(phone);
        this.setOrgEmail(email);
        this.setOrgWebsite(website);
        this.setOrgPassword(password);
        this.setOrgDescription(description);
        this.setOrgLogo(logo);
        this.setOrgHeader(header);

    }

    public void setOrgServiceOpsList(HashMap<String, ServiceOpportunity> serviceOp) {this.orgServiceOps = serviceOp;}

    public void addOrgServiceOp(ServiceOpportunity serviceop) {
        if(orgServiceOps == null){
            orgServiceOps = new HashMap<>();
        }
        orgServiceOps.put(serviceop.getId(), serviceop);
    }

    public HashMap<String, ServiceOpportunity> getOrgServiceOpsList() { return orgServiceOps; }

    public ServiceOpportunity getOrgServiceOp(String id){
        if(orgServiceOps != null){
            if(orgServiceOps.size() >= 1){
                return orgServiceOps.get(id);
            }
            else {
                return null;
            }
        }
        else{
            return null;
        }
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