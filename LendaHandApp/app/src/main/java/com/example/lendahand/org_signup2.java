package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class org_signup2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup2);

        Intent intent = getIntent();
        final ServiceOrganization newOrg = (ServiceOrganization)intent.getSerializableExtra("ServiceOrg");
        final TextInputEditText txtOrgDesc = (TextInputEditText)findViewById(R.id.orgDescText);
        final MaterialButton btnOrgSignUpNext2 = (MaterialButton) findViewById(R.id.orgSignupNext2);


        btnOrgSignUpNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orgDesc = txtOrgDesc.getText().toString();
                //Do input checking
                newOrg.setOrgDescription(orgDesc);
                newOrg.displayServiceOrg();
                Intent nextScreen = new Intent(v.getContext(),  org_signup3.class);
                startActivityForResult(nextScreen, 0);
            }
        });

    }
}
