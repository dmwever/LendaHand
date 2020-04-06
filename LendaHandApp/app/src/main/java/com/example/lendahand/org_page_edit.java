package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amazonaws.util.StringUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class org_page_edit extends AppCompatActivity {

    private static final int LOGO_REQUEST_CODE = 100;
    private static final int HEADER_REQUEST_CODE = 101;
    ServiceOrganization serviceOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page_edit);


        Bundle bundle = getIntent().getExtras();
        String ID = bundle.getString("ID");

        final Database db = new Database();
        db.init();
        serviceOrg = db.getOrganization(ID, this);

        //Get a reference to all the text fields in edit page
        final TextInputEditText txtOrgName = (TextInputEditText) findViewById(R.id.orgNameText);
        final TextInputEditText txtOrgEmail = (TextInputEditText)findViewById(R.id.orgEmailText);
        final TextInputEditText txtOrgPhone = (TextInputEditText) findViewById(R.id.orgPhoneText);
        final TextInputEditText txtOrgWebsite = (TextInputEditText) findViewById(R.id.orgWebsiteText);
        final TextInputEditText txtOrgPassword = (TextInputEditText) findViewById(R.id.orgPasswordText);
        final TextInputEditText txtOrgDesc = (TextInputEditText)findViewById(R.id.orgDescText);
        final ImageView imgOrgLogo = (ImageView) findViewById(R.id.orgLogo);
        final ImageView imgOrgHeader = (ImageView) findViewById(R.id.imgOpHeader);
        final MaterialButton btnOrgChangeLogo = findViewById(R.id.btnOrgChangeLogo);
        final MaterialButton btnOrgChangeHeader = findViewById(R.id.btnOrgChangeHeader);
        final MaterialButton btnOrgSavePage = (MaterialButton)findViewById(R.id.orgSavePage);


        txtOrgName.setText(serviceOrg.getOrgName());
        txtOrgEmail.setText(serviceOrg.getOrgEmail());
        txtOrgPhone.setText(serviceOrg.getOrgPhone());
        txtOrgWebsite.setText(serviceOrg.getOrgWebsite());
        txtOrgPassword.setText(serviceOrg.getOrgPassword());
        txtOrgDesc.setText(serviceOrg.getOrgDescription());
        imgOrgLogo.setImageURI(Uri.fromFile(serviceOrg.getOrgLogo()));
        imgOrgHeader.setImageURI(Uri.parse(serviceOrg.getOrgHeader()));

        //STEP 2: Set onClickListener for YOUR button
        btnOrgChangeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code from https://spreys.com/how-to-access-and-store-images-with-android/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOGO_REQUEST_CODE);
            }
        });
        //STEP 2: Set onClickListener for YOUR button
        btnOrgChangeHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code from https://spreys.com/how-to-access-and-store-images-with-android/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), HEADER_REQUEST_CODE);
            }
        });

        btnOrgSavePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orgName = txtOrgName.getText().toString();
                String orgEmail = txtOrgEmail.getText().toString();
                String orgPhone = txtOrgPhone.getText().toString();
                String orgWebsite = txtOrgWebsite.getText().toString();
                String orgPassword = txtOrgPassword.getText().toString();
                String orgDesc = txtOrgDesc.getText().toString();

                InputChecker inputChecker = new InputChecker();
                String error = "";
                error += inputChecker.isBlank(orgName, "Organization Name");
                error += inputChecker.isBlank(orgEmail, "Organization Email");
                error += inputChecker.isBlank(orgPhone, "Organization Phone");
                error += inputChecker.isBlank(orgDesc, "Organization Description");
                error += inputChecker.isBlank(orgPassword, "Organization Password");
                error += inputChecker.isEmailValid(orgEmail);
                error += inputChecker.isPhoneValid(orgPhone);
                error += inputChecker.isPasswordValid(orgPassword);

                if(StringUtils.isBlank(error)) {
                    serviceOrg.setOrgName(orgName);
                    serviceOrg.setOrgEmail(orgEmail);
                    serviceOrg.setOrgPhone(orgPhone);
                    serviceOrg.setOrgWebsite(orgWebsite);
                    serviceOrg.setOrgPassword(orgPassword);
                    serviceOrg.setOrgDescription(orgDesc);

                    Intent nextScreen = new Intent(v.getContext(), org_page.class);
                    nextScreen.putExtra("ID", serviceOrg.getOrgEmail());
                    startActivityForResult(nextScreen, 0);
                }
                else{
                    Toast.makeText(v.getContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //Code from https://androidclarified.com/pick-image-gallery-camera-android/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case LOGO_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    File imageFile = new File(selectedImage.getPath());
                    serviceOrg.setOrgLogo(imageFile);
                    ImageView imageView = findViewById(R.id.orgLogo);
                    imageView.setImageURI(selectedImage);
                    break;
                case HEADER_REQUEST_CODE:
                    Uri selectImage = data.getData();
                    serviceOrg.setOrgHeader(selectImage.toString());
                    ImageView imageview = findViewById(R.id.imgOpHeader);
                    imageview.setImageURI(selectImage);
                    break;
            }
    }
}
