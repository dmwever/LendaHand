package com.example.lendahand;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Database extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void init() {
        db = FirebaseFirestore.getInstance();
    }

    public void addVolunteer (Volunteer newVolunteer) {

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("fName", newVolunteer.getFirstName());
        user.put("lName", newVolunteer.getLastName());
        user.put("id", newVolunteer.getEmail());
        user.put("pNum", newVolunteer.getPhone());
        user.put("DoB", newVolunteer.getDateOfBirth());

// Add a new document with a generated ID
        db.collection("volunteers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("init", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("init", "Error adding document", e);
                    }
                });
    }

}
