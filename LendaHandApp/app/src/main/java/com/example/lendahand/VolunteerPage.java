package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;

public class VolunteerPage extends AppCompatActivity {

    Volunteer currentVolunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_page);


        Intent intent = getIntent();
        final Volunteer currentVolunteer = (Volunteer) intent.getSerializableExtra("CurrentVolunteer");

        //Get a reference to all the text fields on the volunteer page
        final TextView volunteerFullName = findViewById(R.id.volunteerFullName_volunteerScreen);
        final TextView volunteerDescription = findViewById(R.id.volunteerDescription_VolunteerScreen);
        final TextView volunteerEmail = findViewById(R.id.volunteerEmail_volunteerscreen);
        final TextView volunteerPhone = findViewById(R.id.volunteerPhone_volunteerscreen);
        final TextView volunteerDateOfBirth = findViewById(R.id.volunteerBirthday_volunteerScreen);
        final TextView volunteerPassword = findViewById(R.id.volunteerPassword_volunterScreen);
        final CardView editButton = findViewById(R.id.editButton_VolunteerScreen);
        final CardView cardView = findViewById(R.id.cardView);
        final Button btnVolunteerSeeMoreOps = findViewById(R.id.volunteerSeeMoreOps);
        final LinearLayout listOrgServiceOps = (LinearLayout) findViewById(R.id.volunteerServiceOpsList);

        //This makes the edges round. The XML method does not work.
        cardView.setClipToOutline(true);
        editButton.setClipToOutline(true);


        //Set each text field with the appropriate text
        String fullname = currentVolunteer.getFirstName() + currentVolunteer.getLastName();
        volunteerFullName.setText(fullname);
        volunteerEmail.setText(currentVolunteer.getEmail());
        volunteerPhone.setText(currentVolunteer.getPhone());
        volunteerDateOfBirth.setText(currentVolunteer.getDateOfBirth());
        volunteerPassword.setText(currentVolunteer.getPassword());
        editButton.isClickable();

        HashMap<String, ServiceOpportunity> serviceOps = currentVolunteer.getVolunteerServiceOpsList();
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
                btnOrgEditServiceOp.setVisibility(View.GONE);
                btnOrgRemoveService.setVisibility(View.GONE);

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
            btnVolunteerSeeMoreOps.setVisibility(View.VISIBLE);
            btnVolunteerSeeMoreOps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextScreen = new Intent(v.getContext(),  VolunteerManageOps.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CurrentVolunteer", currentVolunteer);
                    nextScreen.putExtras(bundle);
                    startActivityForResult(nextScreen, 0);
                }
            });
        }
        else{
            btnVolunteerSeeMoreOps.setVisibility(View.GONE);
        }

        //Allow button to be clicked to edit volunteer information
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editScreen = new Intent(v.getContext(), EditVolunteerPage.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentVolunteer", currentVolunteer);
                editScreen.putExtras(bundle);
                startActivity(editScreen);
            }
        });

    }
}
