package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class createServiceOpGenInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_op_gen_info);

        //STEP 1: Add reference to button using R.id
        MaterialButton createServiceOpDateTime = findViewById(R.id.btnServiceOpGenInfoNext);

        //STEP 2: Set onClickListener for YOUR button
        createServiceOpDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), createServiceOpDateTime.class);
                //STEP 4: Start your Activity
                startActivity(createServiceOpScreen);
            }
        });
    }
}
