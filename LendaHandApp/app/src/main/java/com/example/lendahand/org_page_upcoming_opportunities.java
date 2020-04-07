package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import java.util.ArrayList;

public class org_page_upcoming_opportunities extends AppCompatActivity {

    ServiceOrganization serviceOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page_upcoming_opportunities);

        Bundle bundle = getIntent().getExtras();
        String ID = bundle.getString("ID");

        final Database db = new Database();
        db.init();
        serviceOrg = db.getOrganization(ID,this);

        ArrayList<ServiceOpportunity> serviceOps = serviceOrg.getOrgServiceOpsList();
        RecyclerView recyclerViewOrgServiceOpsList = (RecyclerView) findViewById(R.id.orgServiceOpsList);
        recyclerViewOrgServiceOpsList.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerViewOrgServiceOpsList.setLayoutManager(layout);
        ServiceOrgServiceOpsAdapter adapter = new ServiceOrgServiceOpsAdapter(this, serviceOps);
        recyclerViewOrgServiceOpsList.setAdapter(adapter);
    }

}
