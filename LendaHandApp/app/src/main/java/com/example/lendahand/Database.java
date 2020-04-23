package com.example.lendahand;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Database {

    private FirebaseFirestore db;
    private StorageReference storage;
    private static final String TAG = "DocSnippets";

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

       HashMap<String, ServiceOpportunity> opportunityList = newOrg.getOrgServiceOpsList();
        Set<String> opportunityIDsSet = opportunityList.keySet();
        ArrayList<String> opportunityIDs = new ArrayList<String>(opportunityIDsSet);
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
        //Upload logo
        StorageReference logoRef = storage.child("images/" + newOrg.getOrgEmail() + "logo");
        Uri logo = Uri.fromFile(newOrg.getOrgLogo());
        logoRef.putFile(logo)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("addLogo", "Logo added for: " + newOrg.getOrgEmail());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("addLogo","Logo not added for: " + newOrg.getOrgEmail());
                    }
                });
        //Upload header
        StorageReference headerRef = storage.child("images/" + newOrg.getOrgEmail() + "header");
        Uri header = Uri.fromFile(newOrg.getOrgHeader());
        headerRef.putFile(header)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("addHeader", "Header added for: " + newOrg.getOrgEmail());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("addHeader","Header not added for: " + newOrg.getOrgEmail());
                    }
                });
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
                ServiceOpportunity findService = getService(ServiceOpsList.get(i), appContext);
                newOrg.addOrgServiceOp(findService);
            }
            //Download header and logo
            StorageReference logoRef = storage.child("images/" + newOrg.getOrgEmail() + "logo");
            StorageReference headerRef = storage.child("images/" + newOrg.getOrgEmail() + "header");
            File mydir = appContext.getDir("images", Context.MODE_PRIVATE); //Creating an internal dir;
            final File logoFile = new File(mydir, newOrg.getOrgEmail() + "logo.jpg");
            Task<FileDownloadTask.TaskSnapshot> logoGetter = logoRef.getFile(logoFile);
            final File headerFile = new File(mydir, newOrg.getOrgEmail() + "header.png");
            Task<FileDownloadTask.TaskSnapshot> headerGetter = headerRef.getFile(headerFile);
            while (!logoGetter.isComplete() && !headerGetter.isComplete()) { }
            newOrg.setOrgLogo(logoFile);
            newOrg.setOrgHeader(headerFile);

        } else {
            Log.d("getOrg", "No such document");
        }
        return newOrg;
    }

    public void addService (final ServiceOpportunity newService) {

        final String ID = newService.getId();
        Map<String, Object> service = new HashMap<>();
        service.put("opID", newService.getId());
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
        service.put("orgID", newService.getOpServiceOrg());
        service.put("opVolunteerList", newService.getOpVolunteers());

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
        //Upload event photo
        StorageReference eventRef = storage.child("images/" + newService.getId() + "event");
        Uri logo = Uri.fromFile(newService.getOpEventPhoto());
        eventRef.putFile(logo)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("addEvent", "Event photo added for: " + newService.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("addEvent","Event Photo not added for: " + newService.getId());
                    }
                });
        //Upload header photo
        StorageReference headerRef = storage.child("images/" + newService.getId() + "header");
        Uri header = Uri.fromFile(newService.getOpHeaderPhoto());
        headerRef.putFile(header)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("addHeader", "Header added for: " + newService.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("addHeader","Header not added for: " + newService.getId());
                    }
                });

    }

    public boolean checkIfServiceOpExists(String ID){
        DocumentReference service = db.collection("serviceOpportunities").document(ID);
        Task<DocumentSnapshot> task = service.get();
        while (!task.isComplete()) { }
        DocumentSnapshot document = task.getResult();
        if (document.exists()) { return true;}
        else return false;
    }

    public ServiceOpportunity getService (String ID, Context appContext) {

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
            //Download header and event pics
            StorageReference logoRef = storage.child("images/" + newService.getId() + "logo");
            StorageReference headerRef = storage.child("images/" + newService.getId() + "header");
            File mydir = appContext.getDir("images", Context.MODE_PRIVATE); //Creating an internal dir;
            final File logoFile = new File(mydir, newService.getId() + "event.jpg");
            Task<FileDownloadTask.TaskSnapshot> logoGetter = logoRef.getFile(logoFile);
            final File headerFile = new File(mydir, newService.getId() + "header.png");
            Task<FileDownloadTask.TaskSnapshot> headerGetter = headerRef.getFile(headerFile);
            while (!logoGetter.isComplete() && !headerGetter.isComplete()) { }
            newService.setOpEventPhoto(logoFile);
            newService.setOpHeaderPhoto(headerFile);
        } else {
            Log.d("getService", "No such document");
        }
        return newService;
    }

    public void addVolunteer (final Volunteer newVolunteer) {

        // Create a new user with a first and last name

        final String ID = newVolunteer.getEmail().toLowerCase();
        Map<String, Object> user = new HashMap<>();
        user.put("fName", newVolunteer.getFirstName());
        user.put("lName", newVolunteer.getLastName());
        user.put("pNum", newVolunteer.getPhone());
        user.put("DoB", newVolunteer.getDateOfBirth());
        user.put("tags", newVolunteer.getTags());
        user.put("photo", "images/" + ID + "photo.jpg");
        HashMap<String, ServiceOpportunity> opportunityList = newVolunteer.getVolunteerServiceOpsList();
        Set<String> opportunityIDsSet = opportunityList.keySet();
        ArrayList<String> opportunityIDs = new ArrayList<String>(opportunityIDsSet);
        user.put("volunteerServiceOps", opportunityIDs);

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
        //Upload profile Picture
        /*StorageReference profileRef = storage.child("images/" + newVolunteer.getEmail() + "profile");
        Uri header = Uri.fromFile(newVolunteer.getPhoto());
        profileRef.putFile(header)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("addProfile", "Profile pic added for: " + newVolunteer.getEmail());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("addProfile","Profile pic not added for: " + newVolunteer.getEmail());
                    }
                });*/
    }

    public Volunteer getVolunteer (String ID, Context appContext) {
        Volunteer newVol = new Volunteer(ID);
        DocumentReference service = db.collection("volunteers").document(ID);
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

            ArrayList<String> ServiceOpsList = (ArrayList<String>) document.get("volunteerServiceOps");
            for (int i = 0; i < ServiceOpsList.size(); i++) {
                ServiceOpportunity findService = getService(ServiceOpsList.get(i), appContext);
                newVol.addVolunteerServiceOp(findService);
            }

            //Download profile pic
            StorageReference profileRef = storage.child("images/" + newVol.getEmail() + "profile");
            File mydir = appContext.getDir("images", Context.MODE_PRIVATE); //Creating an internal dir;
            final File profileFile = new File(mydir, newVol.getEmail() + "profile.jpg");
            Task<FileDownloadTask.TaskSnapshot> profileGetter = profileRef.getFile(profileFile);
            while (!profileGetter.isComplete()) { }
            newVol.setPhoto(profileFile);
        } else {
            Log.d("getService", "No such document");
        }
        return newVol;
    }

}


