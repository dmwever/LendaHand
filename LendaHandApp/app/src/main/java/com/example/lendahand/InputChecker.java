package com.example.lendahand;

import android.util.Patterns;

import com.amazonaws.util.StringUtils;

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
}
