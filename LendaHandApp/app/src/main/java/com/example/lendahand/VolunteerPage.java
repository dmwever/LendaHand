package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class VolunteerPage extends AppCompatActivity {

    Volunteer currentVolunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_page);


        Intent intent = getIntent();
        final Volunteer currentVolunteer = (Volunteer) intent.getSerializableExtra("CurrentVolunteer");

        //Get a reference to all the text fields on the volunteer page
        final TextInputEditText volunteerFirstName = findViewById(R.id.volunteerFirstName);
        final TextInputEditText volunteeerLastName = findViewById(R.id.volunteerLastName);
        final TextInputEditText volunteerEmail = findViewById(R.id.volunteerEmail);
        final TextInputEditText volunteerPhone = findViewById(R.id.volunteerPhone);
        final TextInputEditText volunteerDateOfBirth = findViewById(R.id.volunteerDateOfBirth);
        final TextInputEditText volunteerPassword = findViewById(R.id.volunteerPassword);
        final MaterialButton editVolunteerButton =  (MaterialButton) findViewById(R.id.editVolunteerInformationButton);



        //Set each text field with the appropriate text
        volunteerFirstName.setText(currentVolunteer.getFirstName());
        volunteeerLastName.setText(currentVolunteer.getLastName());
        volunteerEmail.setText(currentVolunteer.getEmail());
        volunteerPhone.setText(currentVolunteer.getPhone());
        volunteerDateOfBirth.setText(currentVolunteer.getDateOfBirth());
        volunteerPassword.setText(currentVolunteer.getPassword());

        //Allow button to be clicked to edit volunteer information
        editVolunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editScreen = new Intent(v.getContext(), EditVolunteerPage.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentVolunteer", currentVolunteer);
                editScreen.putExtras(bundle);
                startActivity(editScreen);
            }
        });

    }
}
