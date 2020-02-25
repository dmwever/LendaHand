package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.util.StringUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class org_signup1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup1);

        final MaterialButton btnOrgSignUpNext1 = (MaterialButton) findViewById(R.id.orgSignupNext1);
        final TextInputEditText txtOrgName = (TextInputEditText) findViewById(R.id.orgNameText);
        final TextInputEditText txtOrgEmail = (TextInputEditText)findViewById(R.id.orgEmailText);
        final TextInputEditText txtOrgPhone = (TextInputEditText) findViewById(R.id.orgPhoneText);
        final TextInputEditText txtOrgWebsite = (TextInputEditText) findViewById(R.id.orgWebsiteText);
        final TextInputEditText txtOrgPassword = (TextInputEditText) findViewById(R.id.orgPasswordText);

        btnOrgSignUpNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orgName = txtOrgName.getText().toString();
                String orgEmail = txtOrgEmail.getText().toString();
                String orgPhone = txtOrgPhone.getText().toString();
                String orgWebsite = txtOrgWebsite.getText().toString();
                String orgPassword = txtOrgPassword.getText().toString();

                InputChecker inputChecker = new InputChecker();
                String error = "";
                error += inputChecker.isBlank(orgName, "Organization Name");
                error += inputChecker.isBlank(orgEmail, "Organization Email");
                error += inputChecker.isBlank(orgPhone, "Organization Phone");
                error += inputChecker.isBlank(orgPassword, "Organization Password");
                error += inputChecker.isEmailValid(orgEmail);
                error += inputChecker.isPhoneValid(orgPhone);
                error += inputChecker.isPasswordValid(orgPassword);

                if(StringUtils.isBlank(error)) {
                    ServiceOrganization newOrg = new ServiceOrganization(orgName, orgEmail, orgPhone, orgWebsite, orgPassword, "");
                    newOrg.displayServiceOrg();

                    Intent nextScreen = new Intent(v.getContext(), org_signup2.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ServiceOrg", newOrg);
                    nextScreen.putExtras(bundle);
                    startActivityForResult(nextScreen, 0);
                }
                else{
                    Toast.makeText(v.getContext(), error, 10).show();
                }

            }
        });
    }
}
