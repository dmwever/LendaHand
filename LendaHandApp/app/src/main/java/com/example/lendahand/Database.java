package com.example.lendahand;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private FirebaseFirestore db;
    private StorageReference storage;

    public void init() {
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference();
    }

    public void addOrganization (final ServiceOrganization newOrg) {

        final String ID = newOrg.getOrgEmail();
        Map<String, Object> org = new HashMap<>();
        org.put("orgName", newOrg.getOrgName());
        org.put("orgPhone", newOrg.getOrgPhone());
        org.put("orgWebsite", newOrg.getOrgWebsite());
        org.put("orgDescription", newOrg.getOrgDescription());

        ArrayList<ServiceOpportunity> opportunityList = newOrg.getOrgServiceOpsList();
        ArrayList<String> opportunityIDs = new ArrayList<>();
        for (int i = 0; i < opportunityList.size(); i++) {
            ServiceOpportunity currentOp = opportunityList.get(i);
            opportunityIDs.add(currentOp.getId());
        }
        org.put("orgServiceOps", opportunityIDs);

        CollectionReference orgRef = db.collection("serviceOrganizations");
        orgRef.document(ID)
                .set(org)
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
/*
        Uri file = Uri.fromFile(newOrg.getOrgLogo());
        StorageReference riversRef = storage.child("images/" + newOrg.getOrgEmail());

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Log.v("photo", "Did not successfully add photo");
                    }
                });
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri downloadUrl = uri;
                Toast.makeText(getBaseContext(), "Upload success! URL - " + downloadUrl.toString() , Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    public ServiceOrganization getOrganization (String ID, Context appContext) {

        ServiceOrganization newOrg = new ServiceOrganization(ID);
        DocumentReference service = db.collection("serviceOrganizations").document(ID);
        Task<DocumentSnapshot> task = service.get();
        while (!task.isComplete()) { }
        DocumentSnapshot document = task.getResult();
        if (document.exists()) {
            Log.d("getOrg", "DocumentSnapshot data: " + document.getData() + newOrg.getOrgEmail());
            newOrg.setOrgName(document.getString("orgName"));
            newOrg.setOrgDescription(document.getString("orgName"));
            newOrg.setOrgWebsite(document.getString("orgWebsite"));
            newOrg.setOrgDescription(document.getString("orgDescription"));
            newOrg.setOrgPhone(document.getString("orgPhone"));
            ArrayList<String> ServiceOpsList = (ArrayList<String>) document.get("orgServiceOps");
            for (int i = 0; i < ServiceOpsList.size(); i++) {
                ServiceOpportunity findService = getService(ServiceOpsList.get(i));
                newOrg.addOrgServiceOp(findService);
            }
            StorageReference photoRef = storage.child("images/" + newOrg.getOrgEmail());
            File mydir = appContext.getDir("images", Context.MODE_PRIVATE); //Creating an internal dir;
            final File localFile = new File(mydir, newOrg.getOrgEmail() + ".jpg");
            Task<FileDownloadTask.TaskSnapshot> task2 = photoRef.getFile(localFile);
            while (!task2.isComplete()) { }
            newOrg.setOrgLogo(localFile);

        } else {
            Log.d("getOrg", "No such document");
        }
        return newOrg;
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
        service.put("opCutoffTime", newService.getOpCutoffTime());
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

    public ServiceOpportunity getService (String ID) {

        ServiceOpportunity newService = new ServiceOpportunity(ID);
        DocumentReference service = db.collection("serviceOpportunities").document(ID);
        Task<DocumentSnapshot> task = service.get();
        while (!task.isComplete()) { }
        DocumentSnapshot document = task.getResult();
        if (document.exists()) {
            Log.d("getService", "DocumentSnapshot data: " + document.getData());
            newService.setOpAdditionalReq(document.getString("opReq"));
            newService.setOpAgeReq(document.getString("opAge"));
            newService.setOpContactEmail(document.getString("opContEmail"));
            newService.setOpContactName(document.getString("opContName"));
            newService.setOpContactPhone(document.getString("opContNum"));
            newService.setOpCutoffDate(document.getString("opCutoffDate"));
            newService.setOpCutoffTime(document.getString("opCutoffTime"));
            newService.setOpDate(document.getString("opDate"));
            newService.setOpTime(document.getString("opTime"));
            newService.setOpDescription(document.getString("opDesc"));
            newService.setOpLocation(document.getString("opLoc"));
            newService.setOpName(document.getString("opName"));
            newService.setOpRepeat(document.getBoolean("opRepeat"));
            newService.setOpSubtitle(document.getString("opSub"));
        } else {
            Log.d("getService", "No such document");
        }
        return newService;
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
        user.put("photo", "images/" + ID + "photo.jpg");

        //add info to database
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

        /*get user photo
        ClassLoader classLoader = new Database().getClass().getClassLoader();
        Uri photo = Uri.fromFile(new File(classLoader.getResource("drawable/default_user.jpg").getFile()));
        //TODO remove next line
        newVolunteer.displayVolunteer();
        StorageReference photoRef = storage.child("images/"  + ID + "photo.jpg");
        photoRef.putFile(photo)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("photo", "Photo added for ID:" + ID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("photo", "Did not successfully add photo");
                    }
                });
         */
    }

    public Volunteer getVolunteer (String ID) {
        Volunteer newVol = new Volunteer(ID);
        DocumentReference service = db.collection("serviceOpportunities").document(ID);
        Task<DocumentSnapshot> task = service.get();
        while (!task.isComplete()) { }
        DocumentSnapshot document = task.getResult();
        if (document.exists()) {
            newVol.setLastName(document.getString("lName"));
            newVol.setFirstName(document.getString("fName"));
            newVol.setPhone(document.getString("pNum"));
            newVol.setDateOfBirth(document.getString("DoB"));
            ArrayList<String> tags = (ArrayList<String>) document.get("tags");
            newVol.setTags(tags);
        } else {
            Log.d("getService", "No such document");
        }
        return newVol;
    }
}
