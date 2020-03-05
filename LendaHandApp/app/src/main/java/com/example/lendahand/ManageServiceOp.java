package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ManageServiceOp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service_op);

        Intent intent = getIntent();
        final ServiceOpportunity currentServiceOp = (ServiceOpportunity) intent.getSerializableExtra("CurrentServiceOp");

        final TextInputEditText txtServiceOpName = findViewById(R.id.txtEditServiceOpName);
        final TextInputEditText txtServiceOpSub = findViewById(R.id.txtEditServiceOpSub);
        final TextInputEditText txtServiceOpDesc = findViewById(R.id.txtEditServiceOpDesc);
        final TextInputEditText txtServiceOpContactName = findViewById(R.id.txtEditServiceOpContactName);
        final TextInputEditText txtServiceOpContactEmail = findViewById(R.id.txtEditServiceOpContactEmail);
        final TextInputEditText txtServiceOpContactPhone = findViewById(R.id.txtEditServiceOpContactPhone);

        final Switch swtchServiceOpRepeat = findViewById(R.id.swtchEditServiceOpRepeat);
        final TextInputEditText txtServiceOpDate = findViewById(R.id.txtEditServiceOpDate);
        final TextInputEditText txtServiceOpTime = findViewById(R.id.txtEditServiceOpTime);
        final TextInputEditText txtServiceOpCutoffDate = findViewById(R.id.txtEditServiceOpCutoffDate);
        final TextInputEditText txtServiceOpCutoffTime = findViewById(R.id.txtEditServiceOpCutoffTime);
        final TextInputEditText txtServiceOpLocation = findViewById(R.id.txtEditServiceOpLocation);

        final TextInputEditText txtAgeReq = findViewById(R.id.txtEditAgeReq);
        final TextInputEditText txtAdditionalReq = findViewById(R.id.txtEditAdditionalReq);

        txtServiceOpName.setText(currentServiceOp.getOpName());
        txtServiceOpSub.setText(currentServiceOp.getOpSubtitle());
        txtServiceOpDesc.setText(currentServiceOp.getOpDescription());
        txtServiceOpContactName.setText(currentServiceOp.getOpContactName());
        txtServiceOpContactEmail.setText(currentServiceOp.getOpContactEmail());
        txtServiceOpContactPhone.setText(currentServiceOp.getOpContactPhone());

        swtchServiceOpRepeat.setChecked(currentServiceOp.getOpRepeat());
        txtServiceOpDate.setText(currentServiceOp.getOpDate());
        txtServiceOpTime.setText(currentServiceOp.getOpTime());
        txtServiceOpCutoffDate.setText(currentServiceOp.getOpCutoffDate());
        txtServiceOpCutoffTime.setText(currentServiceOp.getOpCutoffTime());
        txtServiceOpLocation.setText(currentServiceOp.getOpLocation());
        txtAgeReq.setText(currentServiceOp.getOpAgeReq());
        txtAdditionalReq.setText(currentServiceOp.getOpAdditionalReq());

        //STEP 1: Add reference to button using R.id
        MaterialButton createServiceOpPhoto = findViewById(R.id.btnMngServiceOpFinish);
        //STEP 2: Set onClickListener for YOUR button
        createServiceOpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), MainActivity.class);
                //STEP 4: Start your Activity
                startActivity(createServiceOpScreen);
            }
        });
    }
}
