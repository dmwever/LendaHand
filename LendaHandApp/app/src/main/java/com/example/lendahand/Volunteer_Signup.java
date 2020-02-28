package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Volunteer_Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer__signup);


        MaterialButton startServingButton = (MaterialButton) findViewById(R.id.start_serving_button);

        startServingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tagsScreen = new Intent(v.getContext(), AddTagsForVolunteer.class);
                startActivity(tagsScreen);
            }
        });

    }
}
