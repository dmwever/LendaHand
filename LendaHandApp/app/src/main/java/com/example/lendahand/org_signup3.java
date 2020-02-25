package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class org_signup3 extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup3);

        //STEP 1: Add reference to button using R.id
        final MaterialButton btnOrgAddLogo = findViewById(R.id.btnOrgAddLogo);
        final MaterialButton btnOrgAddHeader = findViewById(R.id.btnOrgAddHeader);
        final MaterialButton btnOrgSignUpFinish = (MaterialButton) findViewById(R.id.orgSignupFinish);
        //STEP 2: Set onClickListener for YOUR button
        btnOrgAddLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code from https://spreys.com/how-to-access-and-store-images-with-android/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
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
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);
            }
        });

        btnOrgSignUpFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                final ServiceOrganization newOrg = (ServiceOrganization)intent.getSerializableExtra("ServiceOrg");
                Intent nextScreen = new Intent(v.getContext(),  org_signup4.class);
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
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    ImageView imageView = findViewById(R.id.orgLogo);
                    imageView.setImageURI(selectedImage);
                    break;
                case 101:
                    Uri selectImage = data.getData();
                    ImageView imageview = findViewById(R.id.orgHeader);
                    imageview.setImageURI(selectImage);
                    break;
            }
    }
}
