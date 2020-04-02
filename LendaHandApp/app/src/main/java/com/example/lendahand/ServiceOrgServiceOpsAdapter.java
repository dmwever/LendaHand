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

//https://www.sanktips.com/2017/11/15/android-recyclerview-with-custom-adapter-example/
public class ServiceOrgServiceOpsAdapter extends RecyclerView.Adapter<ServiceOrgServiceOpsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ServiceOpportunity> serviceOps;

    public ServiceOrgServiceOpsAdapter(Context context, ArrayList<ServiceOpportunity> serviceOps){
        this.context = context;
        this.serviceOps = serviceOps;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_org_page_service_op, parent,false);
        ViewHolder viewHolder = new ViewHolder (v);
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        return serviceOps.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(serviceOps.get(position));
        final ServiceOpportunity serviceOp = serviceOps.get(position);
        holder.txtOrgServiceOpName.setText(serviceOp.getOpName());
        holder.txtOrgServiceOpSubtitle.setText(serviceOp.getOpSubtitle());
        //FIXME
        //holder.imgOrgServiceOp.setImageURI(Uri.parse(serviceOp.getOpEventPhoto()));
        holder.btnOrgEditServiceOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(v.getContext(),  ManageServiceOp.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentServiceOp", serviceOp);
                nextScreen.putExtras(bundle);
                context.startActivity(nextScreen);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtOrgServiceOpName;
        private TextView txtOrgServiceOpSubtitle;
        private ImageView imgOrgServiceOp;
        private ImageButton btnOrgEditServiceOp;

        private ViewHolder(View itemView) {
            super(itemView);

            txtOrgServiceOpName = (TextView) itemView.findViewById(R.id.orgServiceOpNameText);
            txtOrgServiceOpSubtitle = (TextView) itemView.findViewById(R.id.orgServiceOpSubtitleText);
            imgOrgServiceOp = (ImageView) itemView.findViewById(R.id.orgServiceOp);
            btnOrgEditServiceOp = (ImageButton) itemView.findViewById(R.id.orgEditServiceOp);
        }
    }

}
