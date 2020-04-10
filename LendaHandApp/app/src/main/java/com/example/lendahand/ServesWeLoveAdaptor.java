package com.example.lendahand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServesWeLoveAdaptor extends RecyclerView.Adapter<ServesWeLoveAdaptor.ServesViewHolder> {

    Context context;

    public ServesWeLoveAdaptor(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ServesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @NonNull
    @Override
    public ServesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.homepage_serve_op_card, parent, false);

        return new ServesViewHolder(view);
    }

    public class ServesViewHolder extends RecyclerView.ViewHolder {

        public ServesViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
