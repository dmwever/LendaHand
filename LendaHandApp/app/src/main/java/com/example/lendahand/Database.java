package com.example.lendahand;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
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

    public void addService (final ServiceOpportunity newService) {

        final String ID = newService.getId();
        Map<String, Object> service = new HashMap<>();
        service.put("opName", newService.getOpName());
        service.put("opSub", newService.getOpSubtitle());
        service.put("opDesc", newService.getOpDescription());
        service.put("opContName", newService.getOpContactName());
        service.put("opContEmail", newService.getOpContactEmail());
        service.put("opContNum", newService.getOpContactPhone());
        service.put("opRepeat", newService.getOpRepeat());
        service.put("opDate", newService.getOpDate());
        service.put("opTime", newService.getOpTime());
        service.put("opCutoffDate", newService.getOpCutoffDate());
        service.put("opCuttoffTime", newService.getOpCutoffTime());
        service.put("opLoc", newService.getOpLocation());
        service.put("opAge", newService.getOpAgeReq());
        service.put("opReq", newService.getOpAdditionalReq());
        //FIXME service.put("orgName", newService.getOpServiceOrgName());

        CollectionReference serviceRef = db.collection("serviceOpportunities");
        serviceRef.document(ID)
                .set(service)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("init", "DocumentSnapshot added with ID: " + ID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("init", "Error adding document", e);
                    }
                });
    }
    public void addVolunteer (final Volunteer newVolunteer) {

        // Create a new user with a first and last name

        final String ID = newVolunteer.getEmail();
        Map<String, Object> user = new HashMap<>();
        user.put("fName", newVolunteer.getFirstName());
        user.put("lName", newVolunteer.getLastName());
        user.put("pNum", newVolunteer.getPhone());
        user.put("DoB", newVolunteer.getDateOfBirth());
        user.put("tags", newVolunteer.getTags());

        CollectionReference volunteersRef = db.collection("volunteers");
        volunteersRef.document(ID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("init", "DocumentSnapshot added with ID: " + ID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("init", "Error adding document", e);
                    }
                });

/* Add a new document with a generated ID
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
                });*/
    }

}
