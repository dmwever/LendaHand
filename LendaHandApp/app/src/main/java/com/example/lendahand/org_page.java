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

        Intent getServiceOrg = getIntent();
        final ServiceOrganization serviceOrg = (ServiceOrganization)getServiceOrg.getSerializableExtra("ServiceOrg");

        final TextView OrgName = (TextView) findViewById(R.id.orgname);
        final TextView OrgEmail = (TextView) findViewById(R.id.orgemail);
        final TextView OrgPhone = (TextView) findViewById(R.id.orgphone);
        final TextView OrgWebsite = (TextView) findViewById(R.id.orgwebsite);
        final TextView OrgDesc = (TextView) findViewById(R.id.orgdesc);
        final ImageView OrgLogo = (ImageView) findViewById(R.id.orglogo);
        final ImageView OrgHeader = (ImageView) findViewById(R.id.orgheader);
        final MaterialButton btnOrgEdit =  (MaterialButton) findViewById(R.id.orgEditPage);

        OrgName.setText(serviceOrg.getOrgName());
        OrgEmail.setText(serviceOrg.getOrgEmail());
        OrgPhone.setText(serviceOrg.getOrgPhone());
        OrgWebsite.setText(serviceOrg.getOrgWebsite());
        OrgDesc.setText(serviceOrg.getOrgDescription());
        OrgLogo.setImageURI(Uri.parse(serviceOrg.getOrgLogo()));
        OrgHeader.setImageURI(Uri.parse(serviceOrg.getOrgHeader()));

        btnOrgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  org_page_edit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ServiceOrg", serviceOrg);
                nextScreen.putExtras(bundle);
                startActivityForResult(nextScreen, 0);
            }
        });
    }
}
