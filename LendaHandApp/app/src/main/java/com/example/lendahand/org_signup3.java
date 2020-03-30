package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class org_signup3 extends AppCompatActivity {

    private static final int LOGO_REQUEST_CODE = 100;
    private static final int HEADER_REQUEST_CODE = 101;
    ServiceOrganization newOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup3);

        Intent serviceOrg = getIntent();
        newOrg = (ServiceOrganization)serviceOrg.getSerializableExtra("ServiceOrg");

        //STEP 1: Add reference to button using R.id
        final MaterialButton btnOrgAddLogo = findViewById(R.id.orgAddLogo);
        final MaterialButton btnOrgAddHeader = findViewById(R.id.orgAddHeader);
        final MaterialButton btnOrgSignUpFinish = (MaterialButton) findViewById(R.id.orgSignupFinish);

        //STEP 2: Set onClickListener for YOUR button
        btnOrgAddLogo.setOnClickListener(new View.OnClickListener() {
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
        btnOrgAddHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code from https://spreys.com/how-to-access-and-store-images-with-android/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), HEADER_REQUEST_CODE);
            }
        });

        btnOrgSignUpFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  org_signup4.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ServiceOrg", newOrg);
                nextScreen.putExtras(bundle);
                startActivityForResult(nextScreen, 0);
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
                    Uri selectLogo = data.getData();
                    newOrg.setOrgLogo(selectLogo.toString());
                    ImageView imgOrgLogo = findViewById(R.id.orgLogo);
                    imgOrgLogo.setImageURI(selectLogo);
                    break;
                case HEADER_REQUEST_CODE:
                    Uri selectHeader = data.getData();
                    newOrg.setOrgHeader(selectHeader.toString());
                    ImageView imgOrgHeader = findViewById(R.id.orgHeader);
                    imgOrgHeader.setImageURI(selectHeader);
                    break;
            }
    }
}
