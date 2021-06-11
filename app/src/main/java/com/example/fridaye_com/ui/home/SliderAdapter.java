package com.example.fridaye_com.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;


import com.example.fridaye_com.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    ArrayList<SliderModel> sliderModelItems;

    public SliderAdapter(ArrayList<SliderModel> sliderModelItems) {
        this.sliderModelItems = sliderModelItems;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SliderModel sliderModel = sliderModelItems.get(position);
        holder.imageView.setImageResource(sliderModelItems.get(position).getBanner());
    }

    @Override
    public int getItemCount() {
        return sliderModelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView  imageView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_slide);
        }
    }


}
