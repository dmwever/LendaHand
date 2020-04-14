package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class org_signup3 extends AppCompatActivity {

    private static final int LOGO_REQUEST_CODE = 100;
    private static final int HEADER_REQUEST_CODE = 101;
    ServiceOrganization newOrg;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup3);

        storageRef = FirebaseStorage.getInstance().getReference();

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
                    ImageView imgOrgLogo = findViewById(R.id.orgLogo);
                    imgOrgLogo.setImageURI(selectLogo);
                    File logoDir = this.getDir("images", Context.MODE_PRIVATE); //Creating an internal dir;
                    final File logoFile = new File(logoDir, newOrg.getOrgEmail() + "logo.jpg");

                    try {
                        InputStream in = getContentResolver().openInputStream(selectLogo);
                        OutputStream out = new FileOutputStream(new File(logoDir, newOrg.getOrgEmail() + "logo.jpg"));
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.close();
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    newOrg.setOrgLogo(logoFile);
                    break;
                case HEADER_REQUEST_CODE:
                    Uri selectHeader = data.getData();
                    ImageView imgOrgHeader = findViewById(R.id.imgOpHeader);
                    imgOrgHeader.setImageURI(selectHeader);
                    File headerDir = this.getDir("images", Context.MODE_PRIVATE); //Creating an internal dir;
                    final File headerFile = new File(headerDir, newOrg.getOrgEmail() + "header.jpg");

                    try {
                        InputStream in = getContentResolver().openInputStream(selectHeader);
                        OutputStream out = new FileOutputStream(new File(headerDir, newOrg.getOrgEmail() + "header.jpg"));
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.close();
                        in.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    newOrg.setOrgHeader(headerFile);
                    break;
            }
    }
}
