package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.amazonaws.util.StringUtils;
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

                String opName = txtServiceOpName.getText().toString().trim();
                String opSub = txtServiceOpSub.getText().toString().trim();
                String opDesc = txtServiceOpDesc.getText().toString().trim();
                String opContactName = txtServiceOpContactName.getText().toString().trim();
                String opContactEmail = txtServiceOpContactEmail.getText().toString().trim();
                String opContactPhone = txtServiceOpContactPhone.getText().toString().trim();

                boolean opRepeat = swtchServiceOpRepeat.isChecked();
                String opDate = txtServiceOpDate.getText().toString().trim();
                String opTime = txtServiceOpTime.getText().toString().trim();
                String opCutoffDate = txtServiceOpCutoffDate.getText().toString().trim();
                String opCutoffTime = txtServiceOpCutoffTime.getText().toString().trim();
                String opLocation = txtServiceOpLocation.getText().toString().trim();

                String opAgeReq = txtAgeReq.getText().toString().trim();
                String opAdditionalReq = txtAdditionalReq.getText().toString().trim();

                InputChecker inputChecker = new InputChecker();
                String error = "";
                error += inputChecker.isBlank(opName, "Service Opportunity Name");
                error += inputChecker.isBlank(opSub, "Service Opportunity Subtitle");
                error += inputChecker.isBlank(opDesc, "Service Opportunity Description");
                error += inputChecker.isBlank(opContactName, "Contact Name");
                error += inputChecker.isEmailValid(opContactEmail);
                error += inputChecker.isPhoneValid(opContactPhone);

                error += inputChecker.isBlank(opDate, "Service Opportunity Date");
                error += inputChecker.isBlank(opTime, "Service Opportunity Time");
                error += inputChecker.isBlank(opCutoffDate, "Service Opportunity Cutoff Date");
                error += inputChecker.isBlank(opCutoffTime, "Service Opportunity Cutoff Time");
                error += inputChecker.isBlank(opLocation, "Service Opportunity Location");

                error += inputChecker.isBlank(opAgeReq, "Service Opportunity Age Requirement");

                if(StringUtils.isBlank(error)) {
                    currentServiceOp.setOpName(opName);
                    currentServiceOp.setOpSubtitle(opSub);
                    currentServiceOp.setOpDescription(opDesc);
                    currentServiceOp.setOpContactName(opName);
                    currentServiceOp.setOpContactEmail(opContactEmail);
                    currentServiceOp.setOpContactPhone(opContactPhone);

                    currentServiceOp.setOpRepeat(opRepeat);
                    currentServiceOp.setOpDate(opDate);
                    currentServiceOp.setOpTime(opTime);
                    currentServiceOp.setOpCutoffDate(opCutoffDate);
                    currentServiceOp.setOpCutoffTime(opCutoffTime);
                    currentServiceOp.setOpLocation(opLocation);

                    currentServiceOp.setOpAgeReq(opAgeReq);
                    currentServiceOp.setOpAdditionalReq(opAdditionalReq);
                }
                
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), MainActivity.class);
                //STEP 4: Start your Activity
                startActivity(createServiceOpScreen);
            }
        });
    }
}
