package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.util.StringUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class createServiceOpGenInfo extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_op_gen_info);

        final TextInputEditText txtServiceOpName = findViewById(R.id.txtServiceOpName);
        final TextInputEditText txtServiceOpSub = findViewById(R.id.txtServiceOpSub);
        final TextInputEditText txtServiceOpDesc = findViewById(R.id.txtServiceOpDesc);
        final TextInputEditText txtServiceOpContactName = findViewById(R.id.txtServiceOpContactName);
        final TextInputEditText txtServiceOpContactEmail = findViewById(R.id.txtServiceOpContactEmail);
        final TextInputEditText txtServiceOpContactPhone = findViewById(R.id.txtServiceOpContactPhone);

        MaterialButton createServiceOpDateTime = findViewById(R.id.btnServiceOpGenInfoNext);
        //STEP 2: Set onClickListener for YOUR button
        createServiceOpDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String opName = txtServiceOpName.getText().toString().trim();
                String opSub = txtServiceOpSub.getText().toString().trim();
                String opDesc = txtServiceOpDesc.getText().toString().trim();
                String opContactName = txtServiceOpContactName.getText().toString().trim();
                String opContactEmail = txtServiceOpContactEmail.getText().toString().trim();
                String opContactPhone = txtServiceOpContactPhone.getText().toString().trim();

                InputChecker inputChecker = new InputChecker();
                String error = "";
                error += inputChecker.isBlank(opName, "Service Opportunity Name");
                error += inputChecker.isBlank(opSub, "Service Opportunity Subtitle");
                error += inputChecker.isBlank(opDesc, "Service Opportunity Description");
                error += inputChecker.isBlank(opContactName, "Contact Name");
                error += inputChecker.isEmailValid(opContactEmail);
                error += inputChecker.isPhoneValid(opContactPhone);

                if(StringUtils.isBlank(error)) {
                    ServiceOpportunity newServiceOp =
                            new ServiceOpportunity(
                                    opName,
                                    opSub,
                                    opDesc,
                                    opContactName,
                                    opContactEmail,
                                    opContactPhone,
                                    false,
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    ""
                            );
                    //newServiceOp.displayServiceOp();

                    //STEP 3: Create Intent for your class
                    Intent createServiceOpScreen = new Intent(v.getContext(), createServiceOpDateTime.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ServiceOp", newServiceOp);
                    createServiceOpScreen.putExtras(bundle);
                    //STEP 4: Start your Activity
                    startActivityForResult(createServiceOpScreen, 0);
                }
                else{
                    Toast.makeText(v.getContext(), error, Toast.LENGTH_LONG).show();
                }


            }

        });
    }
}
