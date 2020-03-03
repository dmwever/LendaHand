package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class org_signup4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup4);

        Intent intent = getIntent();
        final ServiceOrganization newOrg = (ServiceOrganization)intent.getSerializableExtra("ServiceOrg");

        final MaterialButton btnOrgAddServiceOp = (MaterialButton) findViewById(R.id.orgAddServOp);
        final MaterialButton btnOrgSeePage = (MaterialButton) findViewById(R.id.orgSeePage);

        btnOrgAddServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  createServiceOpGenInfo.class);
                startActivityForResult(nextScreen, 0);
            }
        });

        btnOrgSeePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  org_page.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ServiceOrg", newOrg);
                nextScreen.putExtras(bundle);
                startActivityForResult(nextScreen, 0);
            }
        });
    }
}
