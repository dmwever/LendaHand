package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class org_page extends AppCompatActivity {
    ServiceOrganization serviceOrg;

    TextView txtOrgName;
    TextView txtOrgEmail;
    TextView txtOrgPhone;
    TextView txtOrgWebsite;
    TextView txtOrgDesc;
    ImageView imgOrgLogo;
    ImageView imgOrgHeader;
    ImageButton btnOrgEdit;
    LinearLayout listOrgServiceOps;
    MaterialButton btnOrgSeeMoreOps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);

        //Associate each view with its XML component
        txtOrgName = (TextView) findViewById(R.id.orgNameText);
        txtOrgEmail = (TextView) findViewById(R.id.orgEmailText);
        txtOrgPhone = (TextView) findViewById(R.id.orgPhoneText);
        txtOrgWebsite = (TextView) findViewById(R.id.orgWebsiteText);
        txtOrgDesc = (TextView) findViewById(R.id.orgDescText);
        imgOrgLogo = (ImageView) findViewById(R.id.orgLogo);
        imgOrgHeader = (ImageView) findViewById(R.id.imgOpHeader);
        btnOrgEdit = (ImageButton) findViewById(R.id.orgEditPage);
        listOrgServiceOps = (LinearLayout) findViewById(R.id.orgServiceOpsList);
        btnOrgSeeMoreOps = (MaterialButton) findViewById(R.id.orgSeeMoreOps);

        //Check for information in the bundle
        if (getIntent().hasExtra("ID")) {
            Bundle bundle = getIntent().getExtras();
            String ID = bundle.getString("ID");
            serviceOrg = new ServiceOrganization(ID);
            Log.d("org_page", "If 1");

            queryDatabaseForOrganziation(ID);

        } else if (getIntent().hasExtra("ServiceOrg")) {
            Intent serviceOrgIntent = getIntent();
            serviceOrg = (ServiceOrganization)serviceOrgIntent.getSerializableExtra("ServiceOrg");
            Log.d("org_page", "If 2");
            updateUI();

        } else {
            Log.d("org_page", "If 3");
            return;
        }

        Random rand = new Random();
        ServiceOpportunity s1 = new ServiceOpportunity("one", "one", "one", "one", "one", "one", false, "01/01/01", "0100", "01/01/01", "0100", "oneoneone", "1", "", serviceOrg.getOrgLogo(), serviceOrg.getOrgLogo(), "one@one.com" ,"org1");
        serviceOrg.addOrgServiceOp(s1);
        s1.setOpServiceOrg(serviceOrg.getOrgEmail());
        s1.setId(s1.getOpServiceOrg() + (rand.nextInt(999999)));
        ServiceOpportunity s2 = new ServiceOpportunity("two", "two", "two", "two", "two", "two", false, "02/02/02", "0200", "02/02/02", "0200", "twotwotwo", "2", "", serviceOrg.getOrgLogo(), serviceOrg.getOrgLogo(), "two@two.com", "org2");
        s2.setOpServiceOrg(serviceOrg.getOrgEmail());
        serviceOrg.addOrgServiceOp(s2);
        s2.setId(s2.getOpServiceOrg() + (rand.nextInt(999999)));
        ServiceOpportunity s3 = new ServiceOpportunity("three", "three", "two", "three", "three", "three", false, "03/03/03", "0300", "03/03/03", "0300", "threethreethree", "2", "", serviceOrg.getOrgLogo(), serviceOrg.getOrgLogo(), "three@three.com","org3");
        s3.setOpServiceOrg(serviceOrg.getOrgEmail());
        serviceOrg.addOrgServiceOp(s3);
        s3.setId(s3.getOpServiceOrg() + (rand.nextInt(999999)));

        allowUsertoEditOrg();

    }




    private void updateUI() {
        txtOrgName.setText(serviceOrg.getOrgName());
        txtOrgEmail.setText(serviceOrg.getOrgEmail());
        txtOrgPhone.setText(serviceOrg.getOrgPhone());
        txtOrgWebsite.setText(serviceOrg.getOrgWebsite());
        txtOrgDesc.setText(serviceOrg.getOrgDescription());
//        imgOrgLogo.setImageURI(Uri.fromFile(serviceOrg.getOrgLogo()));
//        imgOrgHeader.setImageURI(Uri.fromFile(serviceOrg.getOrgHeader()));

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

//                imgOrgServiceOp.setImageURI(Uri.fromFile(serviceOp.getOpEventPhoto()));
//                imgOrgServiceOp.setImageURI(Uri.fromFile(serviceOp.getOpEventPhoto()));
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

    }

    private void queryDatabaseForOrganziation(String ID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference organization = db.collection("serviceOrganizations").document(ID);
        Task<DocumentSnapshot> task = organization.get();

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("getService", "DocumentSnapshot data: " + document.getData());
                    serviceOrg.setOrgName(document.getString("orgName"));
                    serviceOrg.setOrgEmail(document.getString("orgEmail"));
                    serviceOrg.setOrgPhone(document.getString("orgPhone"));
                    serviceOrg.setOrgWebsite(document.getString("orgWebsite"));
                    serviceOrg.setOrgDescription(document.getString("orgDescription"));
//                    serviceOrg.setOpVolunteerList((HashMap<String, String>) document.get("opVolunteerList"));
//                    getOpPhotos();
                    updateUI();
                }
            }
        });
    }

    private void allowUsertoEditOrg() {
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

