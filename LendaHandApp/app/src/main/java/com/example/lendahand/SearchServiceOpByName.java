package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class SearchServiceOpByName extends AppCompatActivity {

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

                database.getServiceByName(opSearchName);

               // Intent createServiceOpScreen = new Intent(v.getContext(), DisplayServiceOpportunity.class);
                //startActivity(createServiceOpScreen);

            }
        });

    }
}
