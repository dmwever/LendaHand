package com.example.lendahand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;

class ServiceOpSearchAdapter extends RecyclerView.Adapter<ServiceOpSearchAdapter.MyViewHolder> {
    private ArrayList<String> opList;
    private ArrayList<QueryDocumentSnapshot> docList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtServOpName;
        public TextView txtServOpSub;
        public TextView txtServOpDesc;
        public MyViewHolder(View v) {
            super(v);
            txtServOpName = v.findViewById(R.id.txtSearchOpName);
            txtServOpSub = v.findViewById(R.id.txtSearchOpSub);
            txtServOpDesc = v.findViewById(R.id.txtSearchOpDesc);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ServiceOpSearchAdapter(ArrayList<QueryDocumentSnapshot> list) {
        docList = list;
        opList = new ArrayList<>();
        for(int i = 0; i < docList.size(); i++){
            opList.add(docList.get(i).getId());
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ServiceOpSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View opView = inflater.inflate(R.layout.item_service_op, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(opView);
        return viewHolder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ServiceOpSearchAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtServOpName.setText(docList.get(position).getString("opName"));
        holder.txtServOpSub.setText(docList.get(position).getString("opSub"));
        holder.txtServOpDesc.setText(docList.get(position).getString("opDesc"));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return docList.size();
    }
}
