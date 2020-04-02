package com.example.lendahand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeaturedServeOpsAdaptor extends RecyclerView.Adapter<FeaturedServeOpsAdaptor.MyViewHolder> {

    String titles[];
    String subtitles[];
    int images[];
    Context context;


    public FeaturedServeOpsAdaptor(Context context, String titles[], String subtitles[], int images[]) {
        this.context = context;
        this.titles = titles;
        this.subtitles = subtitles;
        this.images = images;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.featured_banner, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(titles[position]);
        holder.subtitle.setText(subtitles[position]);
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subtitle;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text_view);
            subtitle = itemView.findViewById(R.id.subtitle_text_view);
            image = itemView.findViewById(R.id.featured_image_view);



        }
    }
}
