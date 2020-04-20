package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SearchServiceOpByName extends AppCompatActivity {

    private static final String TAG = "DocSnippets";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service_op_by_name);

        final Database database = new Database();
        database.init();

        final TextInputEditText txtServiceOpName = findViewById(R.id.txtSearchServiceOp);

        Button btnSearchServiceOp = findViewById(R.id.btnServiceOpSearchName);
        btnSearchServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opSearchName = txtServiceOpName.getText().toString().trim();

                ArrayList<ServiceOpportunity> ops = database.getServiceByName(opSearchName);

                Log.d(TAG, "Ops size: " + ops.size());
                if(ops.size() > 0) {
                    Log.d(TAG, "reached inner if ");
                    Intent createServiceOpScreen = new Intent(v.getContext(), DisplayServiceOpportunity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CurrentServiceOp", ops.get(0));
                    createServiceOpScreen.putExtras(bundle);
                    startActivity(createServiceOpScreen);
                }
            }
        });

    }
}
