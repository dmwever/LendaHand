package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

public class VolunteerManageOps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_manage_ops);

        Intent intent = getIntent();
        final Volunteer volunteer = (Volunteer)intent.getSerializableExtra("CurrentVolunteer");

        HashMap<String, ServiceOpportunity> serviceOps = volunteer.getVolunteerServiceOpsList();
        RecyclerView recyclerViewVolunteerServiceOpsList = (RecyclerView) findViewById(R.id.volunteerServiceOpsList);
        recyclerViewVolunteerServiceOpsList.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerViewVolunteerServiceOpsList.setLayoutManager(layout);
        VolunteerServiceOpsAdapter adapter = new VolunteerServiceOpsAdapter(this, volunteer);
        recyclerViewVolunteerServiceOpsList.setAdapter(adapter);
    }
}
