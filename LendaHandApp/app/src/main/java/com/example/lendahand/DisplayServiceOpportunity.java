package com.example.lendahand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayServiceOpportunity extends AppCompatActivity {
    ServiceOpportunity serviceOp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_service_opportunity);

        Bundle bundle = getIntent().getExtras();
        String ID = bundle.getString("ID");

        final Database db = new Database();
        db.init();
        serviceOp = db.getService(ID);
        if(serviceOp == null){
            System.out.println("FAILURE");
        }

        final TextView txtServiceOpName = (TextView) findViewById(R.id.txtDispServOpName);
        final TextView txtServiceOpSub = (TextView) findViewById(R.id.txtDispServOpSub);
        final TextView txtServiceOpDate = (TextView) findViewById(R.id.txtDispServOpDate);
        final TextView txtServiceOpTime = (TextView) findViewById(R.id.txtDispServOpTime);
        final TextView txtServiceOpDesc = (TextView) findViewById(R.id.txtDispServOpDesc);

        txtServiceOpName.setText(serviceOp.getOpName());
        txtServiceOpSub.setText(serviceOp.getOpSubtitle());
        txtServiceOpDate.setText(serviceOp.getOpDate());
        txtServiceOpTime.setText(serviceOp.getOpTime());
        txtServiceOpDesc.setText(serviceOp.getOpDescription());
    }
}
