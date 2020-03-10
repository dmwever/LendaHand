package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class org_page extends AppCompatActivity {
    ServiceOrganization serviceOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);

        Intent getServiceOrg = getIntent();
        serviceOrg = (ServiceOrganization)getServiceOrg.getSerializableExtra("ServiceOrg");

        ServiceOpportunity s1 = new ServiceOpportunity("one", "one", "one", "one", "one", "one", false, "01/01/01", "0100", "01/01/01", "0100", "oneoneone", "1", "", serviceOrg.getOrgHeader(), serviceOrg.getOrgLogo());
        ServiceOpportunity s2 = new ServiceOpportunity("two", "two", "two", "two", "two", "two", false, "02/02/02", "0200", "02/02/02", "0200", "twotwotwo", "2", "", serviceOrg.getOrgLogo(), serviceOrg.getOrgHeader());
        ServiceOpportunity s3 = new ServiceOpportunity("three", "three", "two", "three", "three", "three", false, "03/03/03", "0300", "03/03/03", "0300", "threethreethree", "2", "", serviceOrg.getOrgLogo(), serviceOrg.getOrgHeader());
        s1.setOpServiceOrg(serviceOrg);
        s2.setOpServiceOrg(serviceOrg);
        s3.setOpServiceOrg(serviceOrg);
        serviceOrg.addOrgServiceOp(s1);
        serviceOrg.addOrgServiceOp(s2);
        serviceOrg.addOrgServiceOp(s3);


        final TextView txtOrgName = (TextView) findViewById(R.id.orgNameText);
        final TextView txtOrgEmail = (TextView) findViewById(R.id.orgEmailText);
        final TextView txtOrgPhone = (TextView) findViewById(R.id.orgPhoneText);
        final TextView txtOrgWebsite = (TextView) findViewById(R.id.orgWebsiteText);
        final TextView txtOrgDesc = (TextView) findViewById(R.id.orgDescText);
        final ImageView imgOrgLogo = (ImageView) findViewById(R.id.orgLogo);
        final ImageView imgOrgHeader = (ImageView) findViewById(R.id.orgHeader);
        final ImageButton btnOrgEdit = (ImageButton) findViewById(R.id.orgEditPage);
        final LinearLayout listOrgServiceOps = (LinearLayout) findViewById(R.id.orgServiceOpsList);
        MaterialButton btnOrgSeeMoreOps = (MaterialButton) findViewById(R.id.orgSeeMoreOps);

        txtOrgName.setText(serviceOrg.getOrgName());
        txtOrgEmail.setText(serviceOrg.getOrgEmail());
        txtOrgPhone.setText(serviceOrg.getOrgPhone());
        txtOrgWebsite.setText(serviceOrg.getOrgWebsite());
        txtOrgDesc.setText(serviceOrg.getOrgDescription());
        imgOrgLogo.setImageURI(Uri.parse(serviceOrg.getOrgLogo()));
        imgOrgHeader.setImageURI(Uri.parse(serviceOrg.getOrgHeader()));

        ArrayList <ServiceOpportunity> serviceOps = serviceOrg.getOrgServiceOpsList();
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int i = 0; i < 2; i++){
            final ServiceOpportunity serviceOp = serviceOps.get(i);
            if(serviceOp != null){
                View serviceOpView = inflater.inflate(R.layout.activity_org_page_service_op, listOrgServiceOps, false);
                TextView txtOrgServiceOpName = (TextView) serviceOpView.findViewById(R.id.orgServiceOpNameText);
                TextView txtOrgServiceOpSubtitle = (TextView) serviceOpView.findViewById(R.id.orgServiceOpSubtitleText);
                ImageButton btnOrgEditServiceOp = (ImageButton) serviceOpView.findViewById(R.id.orgEditServiceOp);
                ImageView imgOrgServiceOp = (ImageView) serviceOpView.findViewById(R.id.orgServiceOp);
                txtOrgServiceOpName.setText(serviceOp.getOpName());
                txtOrgServiceOpSubtitle.setText(serviceOp.getOpSubtitle());
                imgOrgServiceOp.setImageURI(Uri.parse(serviceOp.getOpEventPhoto()));
                btnOrgEditServiceOp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent nextScreen = new Intent(v.getContext(),  ManageServiceOp.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("CurrentServiceOp", serviceOp);
                        nextScreen.putExtras(bundle);
                        startActivityForResult(nextScreen, 0);
                    }
                });
                listOrgServiceOps.addView(serviceOpView);
            }
        }
        if(serviceOps.size() > 2){
            btnOrgSeeMoreOps.setVisibility(View.VISIBLE);
            btnOrgSeeMoreOps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextScreen = new Intent(v.getContext(),  org_page_upcoming_opportunities.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ServiceOrg", serviceOrg);
                    nextScreen.putExtras(bundle);
                    startActivityForResult(nextScreen, 0);
                }
            });
        }
        else{
            btnOrgSeeMoreOps.setVisibility(View.GONE);
        }

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

