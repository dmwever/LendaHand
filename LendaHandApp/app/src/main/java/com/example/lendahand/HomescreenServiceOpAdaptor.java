package com.example.lendahand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomescreenServiceOpAdaptor extends RecyclerView.Adapter<HomescreenServiceOpAdaptor.HomescreenCardViewHolder> {

    Context context;
    private ArrayList<String> serveIDs;
    private ArrayList<String> serveNames;
    private ArrayList<String> serveSubtitles;

    public HomescreenServiceOpAdaptor(Context context, ArrayList<String> serveIDs, ArrayList<String> serveNames, ArrayList<String> serveSubtitles) {
        this.context = context;
        this.serveIDs = serveIDs;
        this.serveNames = serveNames;
        this.serveSubtitles = serveSubtitles;
    }

    @NonNull
    @Override
    public HomescreenCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_serve_op_card, parent, false);

        return new HomescreenCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomescreenCardViewHolder holder, final int position) {
        holder.serveOpName.setText(serveNames.get(position));
        holder.serveOpSubtitle.setText(serveSubtitles.get(position));

        StorageReference storage = FirebaseStorage.getInstance().getReference();
        String emailOnly = serveIDs.get(position).replaceAll("[0-9]", "");


        StorageReference logoRef = storage.child("images/" + emailOnly + "logo.png");

        Log.d("roof",  "images/" + emailOnly + "logo.png");


        GlideApp.with(context)
                .load(logoRef)
                .into(holder.logo);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceOpScreen = new Intent(context, DisplayServiceOpportunity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ID", serveIDs.get(position));
                serviceOpScreen.putExtras(bundle);

                //STEP 4: Start your Activity
                context.startActivity(serviceOpScreen);
            }
        });

    }


    @Override
    public int getItemCount() {
        return serveIDs.size();
    }



    public class HomescreenCardViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView logo;
        TextView serveOpName;
        TextView serveOpSubtitle;
        ConstraintLayout parentLayout;


        public HomescreenCardViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            logo = itemView.findViewById(R.id.logo_homescreenCard);
            serveOpName = itemView.findViewById(R.id.serveName_homescreenCard);
            serveOpSubtitle = itemView.findViewById(R.id.subtitle_homescreenCard);
            parentLayout = itemView.findViewById(R.id.layout_ServeHomescreenCard);

        }
    }
}
