package com.example.lendahand;

import android.util.Patterns;

import com.amazonaws.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class InputChecker {
    public InputChecker(){

    }
    String isBlank(String text, String field){
        if (StringUtils.isBlank(text)){
            return field + " cannot be blank.\n";
        }
        else{
            return "";
        }
    }

    String isEmailValid(String email){
        if(!StringUtils.isBlank(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid Email Format.\n";
        }
        else{
            return "";
        }
    }

    String isPhoneValid(String phone){
        if(!StringUtils.isBlank(phone) &&!Patterns.PHONE.matcher(phone).matches()){
            return "Invalid Phone Format.\n";
        }
        else{
            return "";
        }
    }

    String isPasswordValid(String password){
        String password_pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,})";
        Pattern PASSWORD = Pattern.compile(password_pattern);
        if(!StringUtils.isBlank(password) && !PASSWORD.matcher(password).matches()){
            return "Password must contain at least one uppercase letter, one lowercase letter, one special character (@, #, $, %, !), and be at least 8 characters long.\n";
        }
        else{
            return "";
        }
    }

    String isDateValid(String date) {
        if(!StringUtils.isBlank(date) && !isValidDate(date)) {
            return "Date must be formatted 'MM/dd/yyyy'\n";
        }
        else {
            return "";
        }
    }

    String isTimeValid(String time) {
        if(!StringUtils.isBlank(time) && !isValidTime(time)) {
            return "Time must be 'HH:mm'\n";
        }
        else {
            return "";
        }
    }

    public boolean isValidDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public boolean isValidTime(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
