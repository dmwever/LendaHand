package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.util.StringUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class EditVolunteerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_volunteer_page);

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

        editVolunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Retrieve the values from each text Field
                String firstName = volunteerFirstName.toString();
                String lastName = volunteeerLastName.toString();
                String email = volunteerEmail.toString();
                String phone = volunteerPhone.toString();
                String dateOfBirth = volunteerDateOfBirth.toString();
                String password = volunteerPassword.toString();

                //Check each text field for input errors and save the errors in a string
                InputChecker inputChecker = new InputChecker();
                String signupError= "";
                signupError += inputChecker.isBlank(firstName, "First Name");
                signupError += inputChecker.isBlank(lastName, "Last Name");
//                signupError += inputChecker.isEmailValid(email);
                signupError += inputChecker.isBlank(dateOfBirth, "Date Of Birth");
                signupError += inputChecker.isPasswordValid(password);

                if (StringUtils.isBlank(signupError)) {
                    //If no errors, create a new volunteer
                    currentVolunteer.editVolunteer(firstName,lastName,email,phone,dateOfBirth,password);
                    currentVolunteer.displayVolunteer();

                    //Save the volunteer on disk
                    Intent volunteerPage = new Intent(v.getContext(), Volunteer.class);
                    Bundle volunteerBundle = new Bundle();
                    volunteerBundle.putSerializable("CurrentVolunteer", currentVolunteer);
                    volunteerPage.putExtras(volunteerBundle);

                    //And go to the tags screen
                    startActivity(volunteerPage);

                } else {
                    Toast.makeText(v.getContext(), signupError, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
