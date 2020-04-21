package com.example.lendahand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class SearchServiceOpByName extends AppCompatActivity {

    private static final String TAG = "DocSnippets";
    private ListView dataListView;
    //private Database database = new Database();
    private TextInputEditText txtServiceOpName;
    private Button btnSearchServiceOp;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service_op_by_name);
        //database.init();
        recyclerView = findViewById(R.id.viewServiceOpSearchView);
        btnSearchServiceOp = findViewById(R.id.btnServiceOpSearchName);
        txtServiceOpName = findViewById(R.id.txtSearchServiceOp);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
       // recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new ServiceOpSearchAdapter(null);
        //recyclerView.setAdapter(mAdapter);


        btnSearchServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opSearchName = txtServiceOpName.getText().toString().trim();
                getServiceByName(opSearchName, db);

            }
        });

    }

    public void getServiceByName(String opSearchName, FirebaseFirestore db) {
        final ArrayList<QueryDocumentSnapshot> serveOps = new ArrayList<>();
        db.collection("serviceOpportunities")
                .whereEqualTo("opName", opSearchName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                serveOps.add(document);
                            }
                            updateStuff(serveOps);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void updateStuff(ArrayList<QueryDocumentSnapshot> opIDs){
        Log.d(TAG, "Ops size: " + opIDs.size());
        if(opIDs.size() > 0) {
            Log.d(TAG, "reached inner if ");
            mAdapter = new ServiceOpSearchAdapter(opIDs);
            recyclerView.setAdapter(mAdapter);
            /*Intent createServiceOpScreen = new Intent(v.getContext(), DisplayServiceOpportunity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CurrentServiceOp", opIDs.get(0));
            createServiceOpScreen.putExtras(bundle);
            startActivity(createServiceOpScreen);*/
        }
    }
}
