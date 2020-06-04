package com.example.venturuscatimages.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.venturuscatimages.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{

    private List<String> catList;

    public CatAdapter(List<String> catList) {
        this.catList = catList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Glide.with(holder.catView.getContext())
                .load(catList.get(position))
                .into(holder.catView);
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    static class CatViewHolder extends RecyclerView.ViewHolder{
        private ImageView catView;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            catView = itemView.findViewById(R.id.imageView_cat);
        }
    }
}

