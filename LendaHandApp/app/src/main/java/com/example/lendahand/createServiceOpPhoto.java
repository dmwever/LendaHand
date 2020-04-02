package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amazonaws.services.s3.model.HeadBucketRequest;
import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class createServiceOpPhoto extends AppCompatActivity {

    private static final int HEADER_REQUEST_CODE = 100;
    private static final int EVENT_REQUEST_CODE = 101;
    ServiceOpportunity newServiceOp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_op_photo);

        Intent intent = getIntent();
       newServiceOp = (ServiceOpportunity) intent.getSerializableExtra("ServiceOp");

        final Database db = new Database();
        db.init();
        Random rand = new Random();
        newServiceOp.setId(newServiceOp.getOpServiceOrg() + (rand.nextInt(999999)));
        //STEP 1: Add reference to button using R.id
        MaterialButton createServiceOpPhoto = findViewById(R.id.btnServiceOpPhotoNext);
        //STEP 2: Set onClickListener for YOUR button
        createServiceOpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addService(newServiceOp);
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), ManageServiceOp.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CurrentServiceOp", newServiceOp);
                    createServiceOpScreen.putExtras(bundle);
                //STEP 4: Start your Activity
                startActivityForResult(createServiceOpScreen, 0);
            }
        });

        //STEP 1: Add reference to button using R.id
        MaterialButton createServiceOpFinish = findViewById(R.id.btnCreateServiceOpFinish);
        //STEP 2: Set onClickListener for YOUR button
        createServiceOpFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addService(newServiceOp);
                //STEP 3: Create Intent for your class
                Intent createServiceOpScreen = new Intent(v.getContext(), DisplayServiceOpportunity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CurrentServiceOp", newServiceOp);
                    bundle.putString("ID", newServiceOp.getId());
                createServiceOpScreen.putExtras(bundle);
                //STEP 4: Start your Activity
                startActivityForResult(createServiceOpScreen, 0);
            }
        });

        //STEP 1: Add reference to button using R.id
        ImageButton btnCreateServiceOpAddHeadPhoto = findViewById(R.id.btnServiceOpAddHeadPhoto);
        //STEP 2: Set onClickListener for YOUR button
        btnCreateServiceOpAddHeadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code from https://spreys.com/how-to-access-and-store-images-with-android/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), HEADER_REQUEST_CODE);


            }
        });

        //STEP 1: Add reference to button using R.id
        ImageButton btnCreateServiceOpAddEventPhoto = findViewById(R.id.btnServiceOpAddEventPhoto);
        //STEP 2: Set onClickListener for YOUR button
        btnCreateServiceOpAddEventPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code from https://spreys.com/how-to-access-and-store-images-with-android/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), EVENT_REQUEST_CODE);


            }
        });
    }

    //Code from https://androidclarified.com/pick-image-gallery-camera-android/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case HEADER_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    newServiceOp.setOpHeaderPhoto(selectedImage.toString());
                    ImageView imageView = findViewById(R.id.btnServiceOpAddHeadPhoto);
                    imageView.setImageURI(selectedImage);
                    break;
                case EVENT_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedEventImage = data.getData();
                    newServiceOp.setOpEventPhoto(selectedEventImage.toString());
                    ImageView imageEventView = findViewById(R.id.btnServiceOpAddEventPhoto);
                    imageEventView.setImageURI(selectedEventImage);
                    break;
            }
    }

}
