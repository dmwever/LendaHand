package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AddTagsForVolunteer extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ChipGroup tagCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags_for_volunteer);

        Intent volunteerInfo = getIntent();
        final Volunteer newVol = (Volunteer)volunteerInfo.getSerializableExtra("CurrentVolunteer");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        tagCollection = (ChipGroup)findViewById(R.id.tags_group);

        LayoutInflater inflater = LayoutInflater.from(AddTagsForVolunteer.this);

        final String[] tags = new String[]{"Environment", "Hunger Relief", "Health", "Senior", "People with Disabilities", "Animals", "Education", "Construction", "Community", "Animal", "Hunger Relief"};
        final ArrayList<String> userSelectedTags = new ArrayList<String>();

        for (String tagString : tags) {
            Chip tag = (Chip)inflater.inflate(R.layout.tag_item, null, false);
            tag.setText(tagString);
            tagCollection.addView(tag);
        }

        MaterialButton startServingButton = findViewById(R.id.start_serving_button);

        startServingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Code from: https://stackoverflow.com/questions/55905793/get-selected-chips-from-a-chipgroup
                String msg = "Chips checked are: ";
                int chipsCount = tagCollection.getChildCount();
                if (chipsCount == 0) {
                    msg += "None!!";
                } else {
                    int i = 0;
                    while (i < chipsCount) {
                        Chip chip = (Chip) tagCollection.getChildAt(i);
                        if (chip.isChecked() ) {
                            String nameOfTag = chip.getText().toString();
                            msg += nameOfTag + " " ;
                            userSelectedTags.add(nameOfTag);
                        }
                        i++;
                    };
                }

                //Add volunteer to database
                newVol.setTags(userSelectedTags);
                Database db = new Database();
                db.init();
                db.addVolunteer(newVol);
                createAccount(newVol.getEmail(), newVol.getPassword(), newVol);

                Log.d("AddTags", msg);
                Intent home = new Intent(v.getContext(), MainActivity.class);
                startActivity(home);

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
            startActivity(new Intent(this,MainActivity.class));
        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }

    public void createAccount(String email, String password, final Volunteer newVol) {
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
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Toast.makeText(AddTagsForVolunteer.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
