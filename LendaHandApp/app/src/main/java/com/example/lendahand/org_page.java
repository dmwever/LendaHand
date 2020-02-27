package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class org_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);

        Intent intent = getIntent();
        final ServiceOrganization newOrg = (ServiceOrganization)intent.getSerializableExtra("ServiceOrg");

        final TextView OrgName = (TextView) findViewById(R.id.orgname);
        final TextView OrgEmail = (TextView) findViewById(R.id.orgemail);
        final TextView OrgPhone = (TextView) findViewById(R.id.orgphone);
        final TextView OrgWebsite = (TextView) findViewById(R.id.orgwebsite);
        final TextView OrgDesc = (TextView) findViewById(R.id.orgdesc);
        final ImageView OrgLogo = (ImageView) findViewById(R.id.orglogo);
        final ImageView OrgHeader = (ImageView) findViewById(R.id.orgheader);
        final MaterialButton btnOrgEdit =  (MaterialButton) findViewById(R.id.orgEditPage);

        OrgName.setText(newOrg.getOrgName());
        OrgEmail.setText(newOrg.getOrgEmail());
        OrgPhone.setText(newOrg.getOrgPhone());
        OrgWebsite.setText(newOrg.getOrgWebsite());
        OrgDesc.setText(newOrg.getOrgDescription());
        OrgLogo.setImageURI(Uri.parse(newOrg.getOrgLogo()));
        OrgHeader.setImageURI(Uri.parse(newOrg.getOrgHeader()));

        btnOrgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  org_page_edit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ServiceOrg", newOrg);
                nextScreen.putExtras(bundle);
                startActivityForResult(nextScreen, 0);
            }
        });
    }
}
