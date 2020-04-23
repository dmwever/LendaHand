package com.example.lendahand;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class VolunteerServiceOpsAdapter extends RecyclerView.Adapter<VolunteerServiceOpsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ServiceOpportunity> serviceOps;
    private HashMap<String, ServiceOpportunity> serviceOpsMap;
    private Volunteer volunteer;

    public VolunteerServiceOpsAdapter(Context context,Volunteer vol){
        this.context = context;
        this.volunteer = vol;
        this.serviceOpsMap = vol.getVolunteerServiceOpsList();
        ArrayList<ServiceOpportunity> serviceOpsList = new ArrayList<ServiceOpportunity>(serviceOpsMap.values());
        this.serviceOps = serviceOpsList;
    }
    @Override
    public VolunteerServiceOpsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_service_op, parent,false);
        VolunteerServiceOpsAdapter.ViewHolder viewHolder = new VolunteerServiceOpsAdapter.ViewHolder(v);
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        return serviceOps.size();
    }

    @Override
    public void onBindViewHolder(final VolunteerServiceOpsAdapter.ViewHolder holder, final int position) {

        holder.itemView.setTag(serviceOps.get(position));
        final ServiceOpportunity serviceOp = serviceOps.get(position);
        holder.txtOrgServiceOpName.setText(serviceOp.getOpName());
        holder.txtOrgServiceOpSubtitle.setText(serviceOp.getOpSubtitle());
        holder.imgOrgServiceOp.setImageURI(Uri.fromFile(serviceOp.getOpEventPhoto()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  DisplayServiceOpportunity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentServiceOp", serviceOp);
                nextScreen.putExtras(bundle);
                context.startActivity(nextScreen);
            }
        });
        holder.btnRemoveServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceOps.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), serviceOps.size());
                serviceOpsMap.remove(serviceOp.getId());
                volunteer.setVolunteerServiceOpsList(serviceOpsMap);
                HashMap<String, String> volunteerMap = serviceOp.getOpVolunteers();
                volunteerMap.remove(volunteer.getEmail());
                serviceOp.setOpVolunteerList(volunteerMap);
                Database db = new Database();
                db.init();
                db.addVolunteer(volunteer);
                db.addService(serviceOp);
                Intent nextScreen = new Intent(v.getContext(),  org_page.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentVolunteer", volunteer);
                nextScreen.putExtras(bundle);
                context.startActivity(nextScreen);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtOrgServiceOpName;
        private TextView txtOrgServiceOpSubtitle;
        private ImageView imgOrgServiceOp;
        private ImageButton btnRemoveServiceOp;

        private ViewHolder(View itemView) {
            super(itemView);

            txtOrgServiceOpName = (TextView) itemView.findViewById(R.id.orgServiceOpNameText);
            txtOrgServiceOpSubtitle = (TextView) itemView.findViewById(R.id.orgServiceOpSubtitleText);
            imgOrgServiceOp = (ImageView) itemView.findViewById(R.id.orgServiceOp);
            btnRemoveServiceOp = (ImageButton) itemView.findViewById(R.id.RemoveServiceOp);
        }
    }

}
