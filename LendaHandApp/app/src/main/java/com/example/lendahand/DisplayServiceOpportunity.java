package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Hashtable;

public class DisplayServiceOpportunity extends AppCompatActivity {
    //Define an object that will hold the currently displayed service opp
    ServiceOpportunity serviceOp;
    Database dataBase ;

    private FirebaseAuth mAuth;
    private boolean boolVolLoggedIn;
    private FirebaseFirestore dataB;
    private FirebaseUser currentUser;
    private DocumentReference service;
    private ImageView imgHeader;
    private ImageView imgEvent;
    private static final String TAG = "DocSnippets";

    @Override
    public void onStart() {
        super.onStart();
        dataBase = new Database();
        dataBase.init();
        dataB = FirebaseFirestore.getInstance();
        boolVolLoggedIn = false;
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        checkIfVolLoggedIn();

        // Check if user is signed in (non-null) and update UI accordingly.
    }

    public void  updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }
    //Define each view that will show the details of the currently displayed service op
    TextView txtServiceOpName;
    TextView txtServiceOpSub;
    TextView txtServiceOpDate;
    TextView txtServiceOpTime;
    TextView txtServiceOpDesc;
    TextView txtServiceOpDate2;
    TextView txtServiceOpTime2;
    TextView txtServiceOpSignupCutoff;
    TextView txtServiceOpLocation;
    TextView txtServiceOpContName;
    TextView txtServiceOpContPhone;
    TextView txtServiceOpAgeReq;
    TextView txtServiceOpAddReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Associate each view with its XML component
        setContentView(R.layout.activity_display_service_opportunity);
        txtServiceOpName = (TextView) findViewById(R.id.txtDispServOpName);
        txtServiceOpSub = (TextView) findViewById(R.id.txtDispServOpSub);
        txtServiceOpDate = (TextView) findViewById(R.id.txtDispServOpDate);
        txtServiceOpTime = (TextView) findViewById(R.id.txtDispServOpTime);
        txtServiceOpDesc = (TextView) findViewById(R.id.txtDispServOpDesc);
        txtServiceOpDate2 = (TextView) findViewById(R.id.txtDispServOpDate2);
        txtServiceOpTime2 = (TextView) findViewById(R.id.txtDispServOpTime2);
        txtServiceOpSignupCutoff = (TextView) findViewById(R.id.txtDispServOpSignupCutoff);
        txtServiceOpLocation = (TextView) findViewById(R.id.txtDispServOpLocation);
        txtServiceOpContName = (TextView) findViewById(R.id.txtDispServOpContName);
        txtServiceOpContPhone = (TextView) findViewById(R.id.txtDispServOpContactPhone);
        txtServiceOpAgeReq = (TextView) findViewById(R.id.txtDispServOpAgeReq);
        txtServiceOpAddReq = (TextView) findViewById(R.id.txtDispServOpAddReq);
        imgHeader = (ImageView) findViewById(R.id.imgOpHeader);
	imgEvent = (ImageView) findViewById(R.id.imgOpEvent);


        //If an ID is in the bundle, do this...
        if (getIntent().hasExtra("ID")) {

            //Get the ID from the bundle
            Bundle bundle = getIntent().getExtras();
            String ID = bundle.getString("ID");

            //Initalize the service op object with this ID
            serviceOp = new ServiceOpportunity(ID);

            //then query the database.
            //This function will call the updateUI method to display the
            //serviceOp Details
            queryDatabaseForServiceOp(ID);
            Log.d("getService", "If 1");
        //If a currentServiceOp is already in the bundle, do this...
        } else if (getIntent().hasExtra("CurrentServiceOp")) {

            //Get the Service op from the bundle
            Intent intent = getIntent();
            serviceOp = (ServiceOpportunity) intent.getSerializableExtra("CurrentServiceOp");
            Log.d("getService", "If 2");
            updateUI();

        //If neither are in the bundle we have a problem
        } else {
            Log.d("getService", "If 3");
            return;
        }

        //FM: Conditionally allow these options based on the logged in user
        //Only Volunteers can sign up
        //Only Organizations can edit a service op
        allowUserToSignUpForServiceOp();
        allowUserToEditServiceOp();



    }

    //Functions created to clean up the code
    private void queryDatabaseForServiceOp(String ID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        service = db.collection("serviceOpportunities").document(ID);
        Task<DocumentSnapshot> task = service.get();

        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("getService", "DocumentSnapshot data: " + document.getData());
                    serviceOp.setOpName(document.getString("opName"));
                    serviceOp.setOpSubtitle(document.getString("opSub"));
                    serviceOp.setOpDate(document.getString("opDate"));
                    serviceOp.setOpTime(document.getString("opTime"));
                    serviceOp.setOpDescription(document.getString("opDesc"));
                    serviceOp.setOpAdditionalReq(document.getString("opReq"));
                    serviceOp.setOpAgeReq(document.getString("opAge"));
                    serviceOp.setOpContactEmail(document.getString("opContEmail"));
                    serviceOp.setOpContactName(document.getString("opContName"));
                    serviceOp.setOpContactPhone(document.getString("opContNum"));
                    serviceOp.setOpCutoffDate(document.getString("opCutoffDate"));
                    serviceOp.setOpCutoffTime(document.getString("opCutoffTime"));
                    serviceOp.setOpLocation(document.getString("opLoc"));
                    serviceOp.setOpRepeat(document.getBoolean("opRepeat"));
                    serviceOp.setOpServiceOrg(document.getString("orgID"));
                    serviceOp.setOpVolunteerList((HashMap<String, String>) document.get("opVolunteerList"));
                    getOpPhotos();
                    updateUI();
                }
            }
        });
    }

    private void getOpPhotos() {
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        StorageReference logoRef = storage.child("images/" + serviceOp.getOpServiceOrg() + "logo");
        StorageReference headerRef = storage.child("images/" + serviceOp.getId() + "header");

        Log.d(TAG, "Finding " + "images/" + serviceOp.getOpServiceOrg() + "logo");
        Log.d(TAG, "Finding " + "images/" + serviceOp.getId() + "header");
        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        GlideApp.with(this /* context */)
                .load(logoRef)
                .into(imgEvent);
        GlideApp.with(this /* context */)
                .load(headerRef)
                .into(imgHeader);
    }

    private void updateUI() {

        //Set all the text fields once the service op object is created
        txtServiceOpName.setText(serviceOp.getOpName());
        txtServiceOpSub.setText(serviceOp.getOpSubtitle());
        txtServiceOpDate.setText(serviceOp.getOpDate());
        txtServiceOpTime.setText(serviceOp.getOpTime());
        txtServiceOpDesc.setText(serviceOp.getOpDescription());
        txtServiceOpDate2.setText(serviceOp.getOpDate());
        txtServiceOpTime2.setText(serviceOp.getOpTime());
        txtServiceOpSignupCutoff.setText(serviceOp.getOpCutoffDate() + System.lineSeparator() + serviceOp.getOpCutoffTime());
        txtServiceOpLocation.setText(serviceOp.getOpLocation());
        txtServiceOpContName.setText(serviceOp.getOpContactName());
        txtServiceOpContPhone.setText(serviceOp.getOpContactPhone() + System.lineSeparator() + serviceOp.getOpContactEmail());
        txtServiceOpAgeReq.setText(serviceOp.getOpAgeReq());
        txtServiceOpAddReq.setText(serviceOp.getOpAdditionalReq());


//        Uncomment this code when Image retrieval works
//        Uri headerImage = Uri.fromFile( CurrentServiceOp.getOpHeaderPhoto());
//        Uri eventImage = Uri.fromFile( CurrentServiceOp.getOpEventPhoto());
//
//        imgHeader.setImageURI(headerImage);
//        imgEvent.setImageURI(eventImage);
    }
    private void allowUserToSignUpForServiceOp() {
        //Enable Button Click Functionality
        Button btnServiceOpSignUp = findViewById(R.id.btnServiceOpSignUP);
        btnServiceOpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser currentUser = mAuth.getCurrentUser();
                //updateUI(currentUser);
                if(boolVolLoggedIn && !checkIfVolSignedUp()) {
                    serviceOp.addOpVolunteer(currentUser.getEmail(), currentUser.getDisplayName());
                    Volunteer currentVolunteer = dataBase.getVolunteer(currentUser.getEmail(), getApplicationContext());
                    currentVolunteer.addVolunteerServiceOp(serviceOp);
                    dataBase.addVolunteer(currentVolunteer);
                    service.update("opVolunteerList", serviceOp.getOpVolunteers()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully updated!" + currentUser.getEmail());
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error updating document", e);
                                }
                            });


                    //dataBase.addService(serviceOp);
                    Toast.makeText(v.getContext(), "Signed up success", Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(v.getContext(), "Signed up Failure", Toast.LENGTH_LONG).show();


                //STEP 3: Create Intent for your class
                //Intent createServiceOpScreen = new Intent(v.getContext(), MainActivity.class);
                //STEP 4: Start your Activity
                //startActivityForResult(createServiceOpScreen, 0);
            }
        });
    }
    private void allowUserToEditServiceOp() {
        ImageButton btnDispServiceOpEdit = findViewById(R.id.btnDispServiceOpEdit);
        btnDispServiceOpEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addService(newServiceOp);
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), ManageServiceOp.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentServiceOp", serviceOp);
                createServiceOpScreen.putExtras(bundle);
                //STEP 4: Start your Activity
                startActivityForResult(createServiceOpScreen, 0);
            }
        });
    }

    public void checkIfVolLoggedIn() {
        if (currentUser != null) {
            dataB.collection("volunteers").document(currentUser.getEmail()).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot queryDocumentSnapshots) {
                            boolVolLoggedIn = true;
                        }
                    });
         }

    }

    public boolean checkIfVolSignedUp() {
        if (currentUser != null) {
            return serviceOp.getOpVolunteers().containsKey(currentUser.getEmail());
        }

        else
            return true;
    }
}
