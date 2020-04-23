package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Random;

public class org_signup4 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_signup4);

        Intent intent = getIntent();
        final ServiceOrganization newOrg = (ServiceOrganization)intent.getSerializableExtra("ServiceOrg");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        final MaterialButton btnOrgAddServiceOp = (MaterialButton) findViewById(R.id.orgAddServOp);
        final MaterialButton btnOrgSeePage = (MaterialButton) findViewById(R.id.orgSeePage);
        final Database db = new Database();
        db.init();
        db.addOrganization(newOrg);
        createAccount(newOrg.getOrgEmail(), newOrg.getOrgPassword(), newOrg);

        ServiceOrganization serv = db.getOrganization(newOrg.getOrgEmail(), this);
        System.out.println(serv.getOrgEmail());



        btnOrgAddServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  createServiceOpGenInfo.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ServiceOrg", newOrg);
                nextScreen.putExtras(bundle);
                startActivityForResult(nextScreen, 0);
            }
        });

        btnOrgSeePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  org_page.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ServiceOrg", newOrg);
                nextScreen.putExtras(bundle);
                startActivityForResult(nextScreen, 0);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void  updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }


    public void createAccount(String email, String password, final ServiceOrganization serviceOrg) {
        Log.i("email", email + " " + password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("create",",createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user
                            Log.w("create", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(org_signup4.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
