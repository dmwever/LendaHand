package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DisplayServiceOpportunity extends AppCompatActivity {
    ServiceOpportunity serviceOp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_service_opportunity);

        Bundle bundle = getIntent().getExtras();
        String ID = bundle.getString("ID");

        final Database db = new Database();
        db.init();
        serviceOp = db.getService(ID);
        if(serviceOp == null){
            System.out.println("FAILURE");
        }
    }
}
