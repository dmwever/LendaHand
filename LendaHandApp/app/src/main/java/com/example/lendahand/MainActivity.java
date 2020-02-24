package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        addTemporaryButtons();
    }

    private void addTemporaryButtons() {

        //Adding button to Sign Up A Volunteer
        //STEP 1: Add reference to button using R.id
        MaterialButton signupVolunteer = findViewById(R.id.signup_a_volunteer);

        //STEP 2: Set onClickListener for YOUR button
        signupVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //STEP 3: Create Intent for your class
                Intent volunteerSignupScreen = new Intent(v.getContext(), Volunteer_Signup.class);
                //STEP 4: Start your Activity
                startActivity(volunteerSignupScreen);
            }
        });


        //Adding button to Create Service Opportuntity
        MaterialButton createServiceOp = findViewById(R.id.create_service_opportunity);
        createServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createServiceOpScreen = new Intent(v.getContext(), createServiceOpGenInfo.class);
                startActivity(createServiceOpScreen);

            }
        });

        //Adding button to Create Service Organization
        MaterialButton createServiceOrg = (MaterialButton)findViewById(R.id.create_service_org);
        createServiceOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createServiceOrgScreen = new Intent(v.getContext(), org_signup1.class);
                startActivity(createServiceOrgScreen);

            }
        });



    }

}
