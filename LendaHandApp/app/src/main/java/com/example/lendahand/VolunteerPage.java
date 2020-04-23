package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class VolunteerPage extends AppCompatActivity {

    Volunteer currentVolunteer;
    private FirebaseAuth mAuth;

    TextView volunteerFullName;
    TextView volunteerDescription;
    TextView volunteerEmail;
    TextView volunteerPhone;
    TextView volunteerDateOfBirth;
    TextView volunteerPassword;
    CardView editButton;
    CardView cardView;
    Button btnVolunteerSeeMoreOps;
    LinearLayout listOrgServiceOps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_page);

        //XML
        volunteerFullName = findViewById(R.id.volunteerFullName_volunteerScreen);
        volunteerDescription = findViewById(R.id.volunteerDescription_VolunteerScreen);
        volunteerEmail = findViewById(R.id.volunteerEmail_volunteerscreen);
        volunteerPhone = findViewById(R.id.volunteerPhone_volunteerscreen);
        volunteerDateOfBirth = findViewById(R.id.volunteerBirthday_volunteerScreen);
        volunteerPassword = findViewById(R.id.volunteerPassword_volunterScreen);
        editButton = findViewById(R.id.editButton_VolunteerScreen);
        cardView = findViewById(R.id.cardView);
        btnVolunteerSeeMoreOps = findViewById(R.id.volunteerSeeMoreOps);
        listOrgServiceOps = (LinearLayout) findViewById(R.id.volunteerServiceOpsList);


        if (getIntent().hasExtra("ID")) {
            Bundle bundle = getIntent().getExtras();
            String ID = bundle.getString("ID");

            currentVolunteer = new Volunteer(ID);
            getUserFromDatabase(ID);


            Log.d("volPage", "If 1");
            //If a currentServiceOp is already in the bundle, do this...
        } else if (getIntent().hasExtra("CurrentServiceOp")) {

            Intent intent = getIntent();
            currentVolunteer = (Volunteer) intent.getSerializableExtra("CurrentVolunteer");
            Log.d("volPage", "If 2");
            updateUI();

        } else {
            Log.d("volPage", "If 3");
            return;
        }

        //This makes the edges round. The XML method does not work.
        cardView.setClipToOutline(true);
        editButton.setClipToOutline(true);

        enableSignOutButton();
        allowUserToEditProfile();

    }


    private void enableSignOutButton() {
        MaterialButton SignOut = (MaterialButton) findViewById(R.id.signOut);

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                Intent homeScreen = new Intent(v.getContext(), MainActivity.class);
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(homeScreen);
            }
        });
    }

    private void allowUserToEditProfile() {
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

    private void getUserFromDatabase(String ID) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d("volPage", "Current User ID: " + ID);

        DocumentReference volunteer = db.collection("volunteers").document(ID);
        Task<DocumentSnapshot> task = volunteer.get();

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("volPage", "DocumentSnapshot data: " + document.getData());
                    currentVolunteer.setFirstName(document.getString("fName"));
                    currentVolunteer.setLastName(document.getString("lName"));
                    currentVolunteer.setPhone(document.getString("pNum"));
                    currentVolunteer.setDateOfBirth(document.getString("DoB"));


                    updateUI();
                }
            }
        });

    }

    private void updateUI() {

        String fullname = currentVolunteer.getFirstName() + currentVolunteer.getLastName();
        volunteerFullName.setText(fullname);
        volunteerEmail.setText(currentVolunteer.getEmail());
        volunteerPhone.setText(currentVolunteer.getPhone());
        volunteerDateOfBirth.setText(currentVolunteer.getDateOfBirth());
        editButton.isClickable();

//        if (currentVolunteer.getVolunteerServiceOpsList().equals(null)) {
//            return;
//        }
//            Log.d("volPage", "Went in this if");
//
//            HashMap<String, ServiceOpportunity> serviceOps = currentVolunteer.getVolunteerServiceOpsList();
//            ArrayList<ServiceOpportunity> serviceOpsList = new ArrayList<ServiceOpportunity>(serviceOps.values());
//            LayoutInflater inflater = LayoutInflater.from(this);
//            for(int i = 0; i < 2; i++){
//                final ServiceOpportunity serviceOp = serviceOpsList.get(i);
//                if(serviceOp != null){
//                    View serviceOpView = inflater.inflate(R.layout.cardview_service_op, listOrgServiceOps, false);
//                    TextView txtOrgServiceOpName = (TextView) serviceOpView.findViewById(R.id.orgServiceOpNameText);
//                    TextView txtOrgServiceOpSubtitle = (TextView) serviceOpView.findViewById(R.id.orgServiceOpSubtitleText);
//                    ImageButton btnOrgEditServiceOp = (ImageButton) serviceOpView.findViewById(R.id.orgEditServiceOp);
//                    ImageButton btnOrgRemoveService = (ImageButton) serviceOpView.findViewById(R.id.RemoveServiceOp);
//                    ImageView imgOrgServiceOp = (ImageView) serviceOpView.findViewById(R.id.orgServiceOp);
//                    txtOrgServiceOpName.setText(serviceOp.getOpName());
//                    txtOrgServiceOpSubtitle.setText(serviceOp.getOpSubtitle());
////                imgOrgServiceOp.setImageURI(Uri.fromFile(serviceOp.getOpEventPhoto()));
//                    btnOrgEditServiceOp.setVisibility(View.GONE);
//                    btnOrgRemoveService.setVisibility(View.GONE);
//                    serviceOpView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) { ;
//                            Intent nextScreen = new Intent(v.getContext(),  DisplayServiceOpportunity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("CurrentServiceOp", serviceOp);
//                            nextScreen.putExtras(bundle);
//                            startActivityForResult(nextScreen, 0);
//                        }
//                    });
//                    listOrgServiceOps.addView(serviceOpView);
//                }
//            }
//            if(serviceOps.size() == 0){
//                btnVolunteerSeeMoreOps.setVisibility(View.GONE);
//            }
//            else{
//                btnVolunteerSeeMoreOps.setVisibility(View.VISIBLE);
//                btnVolunteerSeeMoreOps.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent nextScreen = new Intent(v.getContext(),  VolunteerManageOps.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("CurrentVolunteer", currentVolunteer);
//                        nextScreen.putExtras(bundle);
//                        startActivityForResult(nextScreen, 0);
//                    }
//                });
//            }
//        }

    }
}
