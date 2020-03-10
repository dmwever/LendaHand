package com.example.lendahand;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void test() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        Log.i("createStorage", "Success");
        myRef.setValue("Hello, World!");
    }


}
