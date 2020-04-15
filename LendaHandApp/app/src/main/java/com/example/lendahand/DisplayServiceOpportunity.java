package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DisplayServiceOpportunity extends AppCompatActivity {
    ServiceOpportunity serviceOp;

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    public void  updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_service_opportunity);

        Bundle bundle = getIntent().getExtras();
        String ID = bundle.getString("ID");


        Intent intent = getIntent();
        final ServiceOpportunity CurrentServiceOp = (ServiceOpportunity) intent.getSerializableExtra("CurrentServiceOp");

        final Database db = new Database();
        db.init();
        serviceOp = db.getService(ID);
        if(serviceOp == null){
            System.out.println("FAILURE");
        }

        Button btnServiceOpSignUp = findViewById(R.id.btnServiceOpSignUP);
        btnServiceOpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                updateUI(currentUser);
                CurrentServiceOp.addOpVolunteer(currentUser.getEmail(), currentUser.getDisplayName());
                db.addService(CurrentServiceOp);
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), MainActivity.class);
                //STEP 4: Start your Activity
                startActivityForResult(createServiceOpScreen, 0);
            }
        });

        ImageButton btnDispServiceOpEdit = findViewById(R.id.btnDispServiceOpEdit);
        btnDispServiceOpEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addService(newServiceOp);
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), ManageServiceOp.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentServiceOp", CurrentServiceOp);
                createServiceOpScreen.putExtras(bundle);
                //STEP 4: Start your Activity
                startActivityForResult(createServiceOpScreen, 0);
            }
        });




        final TextView txtServiceOpName = (TextView) findViewById(R.id.txtDispServOpName);
        final TextView txtServiceOpSub = (TextView) findViewById(R.id.txtDispServOpSub);
        final TextView txtServiceOpDate = (TextView) findViewById(R.id.txtDispServOpDate);
        final TextView txtServiceOpTime = (TextView) findViewById(R.id.txtDispServOpTime);
        final TextView txtServiceOpDesc = (TextView) findViewById(R.id.txtDispServOpDesc);
        final TextView txtServiceOpDate2 = (TextView) findViewById(R.id.txtDispServOpDate2);
        final TextView txtServiceOpTime2 = (TextView) findViewById(R.id.txtDispServOpTime2);
        final TextView txtServiceOpSignupCutoff = (TextView) findViewById(R.id.txtDispServOpSignupCutoff);
        final TextView txtServiceOpLocation = (TextView) findViewById(R.id.txtDispServOpLocation);
        final TextView txtServiceOpContName = (TextView) findViewById(R.id.txtDispServOpContName);
        final TextView txtServiceOpContPhone = (TextView) findViewById(R.id.txtDispServOpContactPhone);
        final TextView txtServiceOpAgeReq = (TextView) findViewById(R.id.txtDispServOpAgeReq);
        final TextView txtServiceOpAddReq = (TextView) findViewById(R.id.txtDispServOpAddReq);
        final ImageView imgHeader = (ImageView) findViewById(R.id.imgOpHeader);
        final ImageView imgEvent = (ImageView) findViewById(R.id.imgOpEvent);

        txtServiceOpName.setText(CurrentServiceOp.getOpName());
        txtServiceOpSub.setText(CurrentServiceOp.getOpSubtitle());
        txtServiceOpDate.setText(CurrentServiceOp.getOpDate());
        txtServiceOpTime.setText(CurrentServiceOp.getOpTime());
        txtServiceOpDesc.setText(CurrentServiceOp.getOpDescription());
        txtServiceOpDate2.setText(CurrentServiceOp.getOpDate());
        txtServiceOpTime2.setText(CurrentServiceOp.getOpTime());
        txtServiceOpSignupCutoff.setText(CurrentServiceOp.getOpCutoffDate() + System.lineSeparator() + CurrentServiceOp.getOpCutoffTime());
        txtServiceOpLocation.setText(CurrentServiceOp.getOpLocation());
        txtServiceOpContName.setText(CurrentServiceOp.getOpContactName());
        txtServiceOpContPhone.setText(CurrentServiceOp.getOpContactPhone() + System.lineSeparator() + CurrentServiceOp.getOpContactEmail());
        txtServiceOpAgeReq.setText(CurrentServiceOp.getOpAgeReq());
        txtServiceOpAddReq.setText(CurrentServiceOp.getOpAdditionalReq());

        Uri headerImage = Uri.fromFile( CurrentServiceOp.getOpHeaderPhoto());
        Uri eventImage = Uri.fromFile( CurrentServiceOp.getOpEventPhoto());

        imgHeader.setImageURI(headerImage);
        imgEvent.setImageURI(eventImage);
    }
}
