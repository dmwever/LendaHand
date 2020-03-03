package com.example.lendahand;

import java.io.Serializable;
import java.util.Date;

public class ServiceOpportunity implements Serializable {


    private String opName;
    private String opSubtitle;
    private String opDescription;
    private String opContactName;
    private String opContactEmail;
    private String opContactPhone;
    private boolean opRepeat;
    private String opDate;
    private String opTime;
    private String opCutoffDate;
    private String opCutoffTime;
    private String opLocation;
    private String opAgeReq;
    private String opAdditionalReq;
    private String opHeaderPhoto;
    private String opEventPhoto;

    public ServiceOpportunity(
            String name,
            String subtitle,
            String description,
            String contactName,
            String contactEmail,
            String contactPhone,
            boolean repeat,
            String date,
            String time,
            String cutoffDate,
            String cutoffTime,
            String location,
            String ageReq,
            String additionalReq,
            String headerPhoto,
            String eventPhoto )
    {
            opName = name;
            opSubtitle = subtitle;
            opDescription = description;
            opContactName = contactName;
            opContactEmail = contactEmail;
            opContactPhone = contactPhone;
            opRepeat = repeat;
            opDate = date;
            opTime = time;
            opCutoffDate = cutoffDate;
            opCutoffTime = cutoffTime;
            opLocation = location;
            opAgeReq = ageReq;
            opAdditionalReq = additionalReq;
            opHeaderPhoto = headerPhoto;
            opEventPhoto = eventPhoto;
    }

    public void setOpName (String name) {opName = name;}

    public String getOpName () {return opName;}


    public void setOpSubtitle(String subtitle) {opSubtitle = subtitle;}

    public String getOpSubtitle () {return opSubtitle;}


    public void setOpDescription (String description) {opDescription = description;}

    public String getOpDescription () {return opDescription;}


    public void setOpContactName (String contactName) {opContactName = contactName;}

    public String getOpContactName () {return opContactName;}


    public void setOpContactEmail (String contactEmail) {opContactEmail = contactEmail;}

    public String getOpContactEmail () {return opContactEmail;}


    public  void setOpContactPhone (String contactPhone) {opContactPhone = contactPhone;}

    public String getOpContactPhone () {return opContactPhone;}


    public void setOpRepeat (boolean repeat) {opRepeat = repeat;}

    public boolean getOpRepeat () {return opRepeat;}


    public void setOpDate (String date) {opDate = date;}

    public String getOpDate () {return opDate;}


    public void setOpTime (String time) {opTime = time;}

    public String getOpTime () {return opTime;}


    public void setOpCutoffDate (String cutoffDate) {opCutoffDate = cutoffDate;}

    public String getOpCutoffDate () {return opCutoffDate;}


    public void setOpCutoffTime (String cutoffTime) {opCutoffTime = cutoffTime;}

    public String getOpCutoffTime () {return opCutoffTime;}


    public void setOpLocation (String location) {opLocation = location;}

    public String getOpLocation () {return opLocation;}


    public void setOpAgeReq (String ageReq) {opAgeReq = ageReq;}

    public String getOpAgeReq () {return opAgeReq;}


    public void setOpAdditionalReq (String additionalReq) {opAdditionalReq = additionalReq;}

    public String getOpAdditionalReq () {return opAdditionalReq;}


    public void setOpHeaderPhoto (String headerPhoto) {opHeaderPhoto = headerPhoto;}

    public String getOpHeaderPhoto () {return opHeaderPhoto;}


    public void setOpEventPhoto (String eventPhoto) {opEventPhoto = eventPhoto;}

    public String getOpEventPhoto () {return opEventPhoto;}


    public void displayServiceOp() {
        System.out.println("opName: " + opName);
        System.out.println("opSubtitle: " + opSubtitle);
        System.out.println("opDescription: " + opDescription);
        System.out.println("opContactName: " + opContactName);
        System.out.println("opContactEmail: " +opContactEmail);
        System.out.println("opContactPhone: " + opContactPhone);
        System.out.println("opRepeat: " + opRepeat);
        System.out.println("opDate: " + opDate);
        System.out.println("opTime: " + opTime);
        System.out.println("opCutoffDate: " + opCutoffDate);
        System.out.println("opCutoffTime: " + opCutoffTime);
        System.out.println("opLocation: " + opLocation);
        System.out.println("opAgeReq: " + opAgeReq);
        System.out.println("opAdditionalReq: " + opAdditionalReq);
        System.out.println("opHeaderPhoto: " + opHeaderPhoto);
        System.out.println("opEventPhoto: " + opEventPhoto);

    }
}
