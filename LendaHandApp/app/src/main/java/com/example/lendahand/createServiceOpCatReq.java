package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.util.StringUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class createServiceOpCatReq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_op_cat_req);

        Intent intent = getIntent();
        final ServiceOpportunity newServiceOp = (ServiceOpportunity) intent.getSerializableExtra("ServiceOp");

        final TextInputEditText txtAgeReq = findViewById(R.id.txtDispServOpAgeReq);
        final TextInputEditText txtAdditionalReq = findViewById(R.id.txtAdditionalReq);


        //STEP 1: Add reference to button using R.id
        MaterialButton createServiceOpPhoto = findViewById(R.id.btnServiceOpCatReqNext);

        //STEP 2: Set onClickListener for YOUR button
        createServiceOpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opAgeReq = txtAgeReq.getText().toString().trim();
                String opAdditionalReq = txtAdditionalReq.getText().toString().trim();

                //Do input checking
                InputChecker inputChecker = new InputChecker();
                String error = "";

                error += inputChecker.isBlank(opAgeReq, "Service Opportunity Age Requirement");

                if (StringUtils.isBlank(error)) {
                    newServiceOp.setOpAgeReq(opAgeReq);
                    newServiceOp.setOpAdditionalReq(opAdditionalReq);


                    //STEP 3: Create Intent for your class
                    Intent createServiceOpScreen = new Intent(v.getContext(), createServiceOpPhoto.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ServiceOp", newServiceOp);
                    createServiceOpScreen.putExtras(bundle);
                    //STEP 4: Start your Activity
                    startActivityForResult(createServiceOpScreen, 0);
                }

                else{
                    Toast.makeText(v.getContext(), error, Toast.LENGTH_LONG);
                }

            }
        });
    }
}
