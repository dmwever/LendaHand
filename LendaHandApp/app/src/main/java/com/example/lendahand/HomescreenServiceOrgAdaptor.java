package com.example.lendahand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomescreenServiceOrgAdaptor extends RecyclerView.Adapter<HomescreenServiceOrgAdaptor.HomescreenOrgCardViewHolder> {

    Context context;
    private ArrayList<String> orgIDs;
    private ArrayList<String> orgNames;

    public HomescreenServiceOrgAdaptor(Context context, ArrayList<String> orgIDs, ArrayList<String> orgNames) {
        this.context = context;
        this.orgIDs = orgIDs;
        this.orgNames = orgNames;
    }

    @NonNull
    @Override
    public HomescreenOrgCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_org_card, parent, false);

        return new HomescreenOrgCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomescreenOrgCardViewHolder holder, final int position) {
        holder.orgName.setText(orgNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orgScreen = new Intent(context, org_page.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("ID", orgIDs.get(position));
                orgScreen.putExtras(bundle);

                //STEP 4: Start your Activity
                context.startActivity(orgScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orgIDs.size();
    }



    public class HomescreenOrgCardViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView mainImage;
        ImageView logo;
        TextView orgName;
        ConstraintLayout parentLayout;


        public HomescreenOrgCardViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            mainImage = itemView.findViewById(R.id.mainImage_OrgHomescreenCard);
            logo = itemView.findViewById(R.id.logo_homescreenCard);
            orgName = itemView.findViewById(R.id.orgName_OrgHomescreenCard);
            parentLayout = itemView.findViewById(R.id.layout_OrgHomeScreencard);

        }
    }
}
