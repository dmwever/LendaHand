package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
        final TextView volunteerFullName = findViewById(R.id.volunteerFullName_volunteerScreen);
        final TextView volunteerDescription = findViewById(R.id.volunteerDescription_VolunteerScreen);
        final TextView volunteerEmail = findViewById(R.id.volunteerEmail_volunteerscreen);
        final TextView volunteerPhone = findViewById(R.id.volunteerPhone_volunteerscreen);
        final TextView volunteerDateOfBirth = findViewById(R.id.volunteerBirthday_volunteerScreen);
        final TextView volunteerPassword = findViewById(R.id.volunteerPassword_volunterScreen);
        final CardView editButton = findViewById(R.id.editButton_VolunteerScreen);
        final CardView cardView = findViewById(R.id.cardView);

        //This makes the edges round. The XML method does not work.
        cardView.setClipToOutline(true);
        editButton.setClipToOutline(true);


        //Set each text field with the appropriate text
        String fullname = currentVolunteer.getFirstName() + currentVolunteer.getLastName();
        volunteerFullName.setText(fullname);
        volunteerEmail.setText(currentVolunteer.getEmail());
        volunteerPhone.setText(currentVolunteer.getPhone());
        volunteerDateOfBirth.setText(currentVolunteer.getDateOfBirth());
        volunteerPassword.setText(currentVolunteer.getPassword());
        editButton.isClickable();

        //Allow button to be clicked to edit volunteer information
        editButton.setOnClickListener(new View.OnClickListener() {
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
