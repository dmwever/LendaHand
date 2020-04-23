package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.amazonaws.util.StringUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class createServiceOpDateTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_op_date_time);

        Intent intent = getIntent();
        final ServiceOpportunity newServiceOp = (ServiceOpportunity) intent.getSerializableExtra("ServiceOp");

        final Switch swtchServiceOpRepeat = findViewById(R.id.swtchServiceOpRepeat);
        final TextInputEditText txtServiceOpDate = findViewById(R.id.txtServiceOpDate);
        final TextInputEditText txtServiceOpTime = findViewById(R.id.txtServiceOpTime);
        final TextInputEditText txtServiceOpCutoffDate = findViewById(R.id.txtServiceOpCutoffDate);
        final TextInputEditText txtServiceOpCutoffTime = findViewById(R.id.txtServiceOpCutoffTime);
        final TextInputEditText txtServiceOpLocation = findViewById(R.id.txtServiceOpLocation);


        //STEP 1: Add reference to button using R.id
        final MaterialButton createServiceOpCatReq = findViewById(R.id.btnServiceOpDateTimeNext);

        //STEP 2: Set onClickListener for YOUR button
        createServiceOpCatReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean opRepeat = swtchServiceOpRepeat.isChecked();
                String opDate = txtServiceOpDate.getText().toString().trim();
                String opTime = txtServiceOpTime.getText().toString().trim();
                String opCutoffDate = txtServiceOpCutoffDate.getText().toString().trim();
                String opCutoffTime = txtServiceOpCutoffTime.getText().toString().trim();
                String opLocation = txtServiceOpLocation.getText().toString().trim();

                //Do input checking
                InputChecker inputChecker = new InputChecker();
                String error = "";

                error += inputChecker.isDateValid(opDate);
                error += inputChecker.isTimeValid(opTime);
                error += inputChecker.isDateValid(opCutoffDate);
                error += inputChecker.isTimeValid(opCutoffTime);
                error += inputChecker.isBlank(opLocation, "Service Opportunity Location");

                if(StringUtils.isBlank(error)){
                    newServiceOp.setOpRepeat(opRepeat);
                    newServiceOp.setOpDate(opDate);
                    newServiceOp.setOpTime(opTime);
                    newServiceOp.setOpCutoffDate(opCutoffDate);
                    newServiceOp.setOpCutoffTime(opCutoffTime);
                    newServiceOp.setOpLocation(opLocation);

                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), createServiceOpCatReq.class);
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
