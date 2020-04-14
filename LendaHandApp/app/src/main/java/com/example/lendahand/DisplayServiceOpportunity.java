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

public class DisplayServiceOpportunity extends AppCompatActivity {
    ServiceOpportunity serviceOp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_service_opportunity);

        Bundle bundle = getIntent().getExtras();
        String ID = bundle.getString("ID");

        Intent intent = getIntent();
        final ServiceOpportunity CurrentServiceOp = (ServiceOpportunity) intent.getSerializableExtra("CurrentServiceOp");

        Button btnServiceOpSignUp = findViewById(R.id.btnServiceOpSignUP);
        btnServiceOpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addService(newServiceOp);
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


        final Database db = new Database();
        db.init();
        serviceOp = db.getService(ID, this);
        if(serviceOp == null){
            System.out.println("FAILURE");
        }

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
