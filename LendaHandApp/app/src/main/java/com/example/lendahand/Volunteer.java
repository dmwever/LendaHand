package com.example.lendahand;

import android.util.Log;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Volunteer implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String password;
    private ArrayList<String> tags;
    private List prevServe;
    private File photo;
    /*TODO flags
        private int yellowFlag;
        private boolean redFlag;
    */

    public Volunteer(String ID) {
        this.email = ID;
    }

    public Volunteer(String firstName, String lastName, String email, String phone, String dateOfBirth, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.tags = null;
        this.photo = null;
//        this.yellowFlag = 0;
//        this.redFlag = false;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public List getPrevServe() {
        return prevServe;
    }

    public void addPrevServe(int prevServe) {
        this.prevServe.add(prevServe);
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }


    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }


    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return this.phone;
    }


    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }


    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }


    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public void editVolunteer(String firstName, String lastName, String email, String phone, String dateOfBirth, String password){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setDateOfBirth(dateOfBirth);
        this.setPassword(password);
    }

    public void displayVolunteer(){
        Log.d("Volunteer Class", firstName + " " + lastName + " " + email + " " + phone + " " + dateOfBirth + " " + dateOfBirth);
    }
}

