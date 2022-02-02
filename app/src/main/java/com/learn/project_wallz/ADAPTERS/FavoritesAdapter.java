package com.learn.project_wallz.ADAPTERS;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.learn.project_wallz.ACTIVITIES.ClikedActivity;
import com.learn.project_wallz.MODELS.ImageModel;
import com.learn.project_wallz.R;

import java.util.ArrayList;

public class FavoritesAdapter extends  RecyclerView.Adapter<FavoritesAdapter.viewholder>{

    Context ctx;
    ArrayList<ImageModel> data = new ArrayList<>();
    public FavoritesAdapter(Context ctx ,ArrayList<ImageModel> data){
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.likefragmentimagemodel,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        ImageModel temp = data.get(position);
        Glide.with(holder.itemView.getContext()).load(temp.getUrl()).into(holder.img);
        holder.text.setText(temp.getInfo());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updatedata(ArrayList<ImageModel> kali){
        data.clear();
        data.addAll(kali);
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView text;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageinfragimagemdel);
            text = itemView.findViewById(R.id.textfragimagemodel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int Image_position = this.getAdapterPosition();
            ImageModel temp = data.get(Image_position);

            Intent intent = new Intent(ctx.getApplicationContext(), ClikedActivity.class);
            intent.putExtra("WALLPAPER",temp.getUrl());
            intent.putExtra("WALLPAPER_INFO",temp.getInfo());

            ctx.startActivity(intent);

        }
    }
}
