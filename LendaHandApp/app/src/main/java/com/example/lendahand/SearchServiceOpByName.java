package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class SearchServiceOpByName extends AppCompatActivity {

    private static final String TAG = "DocSnippets";
    private ListView dataListView;
    private final Database database = new Database();
    private final TextInputEditText txtServiceOpName = findViewById(R.id.txtSearchServiceOp);
    private Button btnSearchServiceOp;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service_op_by_name);
        database.init();
        dataListView = findViewById(R.id.viewServiceOpSearchView);
        btnSearchServiceOp = findViewById(R.id.btnServiceOpSearchName);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                listItems);
        dataListView.setAdapter(adapter);
        dataListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        dataListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                    }
                });

       // addChildEventListener();


        btnSearchServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opSearchName = txtServiceOpName.getText().toString().trim();
                ArrayList<String> opIDs = database.getServiceByName(opSearchName);
                Log.d(TAG, "Ops size: " + opIDs.size());
                if(opIDs.size() > 0) {
                    Log.d(TAG, "reached inner if ");
                    Intent createServiceOpScreen = new Intent(v.getContext(), DisplayServiceOpportunity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CurrentServiceOp", opIDs.get(0));
                    createServiceOpScreen.putExtras(bundle);
                    startActivity(createServiceOpScreen);
                }
            }
        });

    }
/*
    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) (
                            dataSnapshot.child("opName").getValue()


                        ));

                listKeys.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    listItems.remove(index);
                    listKeys.remove(index);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addChildEventListener(childListener);
    }
*/
}
