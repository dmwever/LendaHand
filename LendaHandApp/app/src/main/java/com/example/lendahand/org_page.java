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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class org_page extends AppCompatActivity {
    ServiceOrganization serviceOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);

        Intent serviceOrgIntent = getIntent();
        serviceOrg = (ServiceOrganization)serviceOrgIntent.getSerializableExtra("ServiceOrg");

        final TextView txtOrgName = (TextView) findViewById(R.id.orgNameText);
        final TextView txtOrgEmail = (TextView) findViewById(R.id.orgEmailText);
        final TextView txtOrgPhone = (TextView) findViewById(R.id.orgPhoneText);
        final TextView txtOrgWebsite = (TextView) findViewById(R.id.orgWebsiteText);
        final TextView txtOrgDesc = (TextView) findViewById(R.id.orgDescText);
        final ImageView imgOrgLogo = (ImageView) findViewById(R.id.orgLogo);
        final ImageView imgOrgHeader = (ImageView) findViewById(R.id.imgOpHeader);
        final ImageButton btnOrgEdit = (ImageButton) findViewById(R.id.orgEditPage);
        final LinearLayout listOrgServiceOps = (LinearLayout) findViewById(R.id.orgServiceOpsList);
        MaterialButton btnOrgSeeMoreOps = (MaterialButton) findViewById(R.id.orgSeeMoreOps);

        txtOrgName.setText(serviceOrg.getOrgName());
        txtOrgEmail.setText(serviceOrg.getOrgEmail());
        txtOrgPhone.setText(serviceOrg.getOrgPhone());
        txtOrgWebsite.setText(serviceOrg.getOrgWebsite());
        txtOrgDesc.setText(serviceOrg.getOrgDescription());
        imgOrgLogo.setImageURI(Uri.fromFile(serviceOrg.getOrgLogo()));
        imgOrgHeader.setImageURI(Uri.fromFile(serviceOrg.getOrgHeader()));

        HashMap<String, ServiceOpportunity> serviceOps = serviceOrg.getOrgServiceOpsList();
        ArrayList<ServiceOpportunity> serviceOpsList = new ArrayList<ServiceOpportunity>(serviceOps.values());
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int i = 0; i < 2; i++){
            final ServiceOpportunity serviceOp = serviceOpsList.get(i);
            if(serviceOp != null){
                View serviceOpView = inflater.inflate(R.layout.cardview_service_op, listOrgServiceOps, false);
                TextView txtOrgServiceOpName = (TextView) serviceOpView.findViewById(R.id.orgServiceOpNameText);
                TextView txtOrgServiceOpSubtitle = (TextView) serviceOpView.findViewById(R.id.orgServiceOpSubtitleText);
                ImageButton btnOrgEditServiceOp = (ImageButton) serviceOpView.findViewById(R.id.orgEditServiceOp);
                ImageButton btnOrgRemoveService = (ImageButton) serviceOpView.findViewById(R.id.RemoveServiceOp);
                ImageView imgOrgServiceOp = (ImageView) serviceOpView.findViewById(R.id.orgServiceOp);
                txtOrgServiceOpName.setText(serviceOp.getOpName());
                txtOrgServiceOpSubtitle.setText(serviceOp.getOpSubtitle());
                imgOrgServiceOp.setImageURI(Uri.fromFile(serviceOp.getOpEventPhoto()));
                btnOrgRemoveService.setVisibility(View.GONE);

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

                serviceOpView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { ;
                        Intent nextScreen = new Intent(v.getContext(),  DisplayServiceOpportunity.class);
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

