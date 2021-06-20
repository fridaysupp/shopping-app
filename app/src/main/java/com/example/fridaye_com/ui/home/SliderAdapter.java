package com.example.fridaye_com.ui.home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.example.fridaye_com.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    ArrayList<SliderModel> sliderModelItems;
    private ViewPager2 viewPager2;

    public SliderAdapter(ArrayList<SliderModel> sliderModelItems, ViewPager2 viewPager2) {
        this.sliderModelItems = sliderModelItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout,parent,false);
        ConstraintLayout bannerContainer = view.findViewById(R.id.banner_container);
        bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelItems.get(viewType).getBackgroundColor())));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SliderModel sliderModel = sliderModelItems.get(position);
        holder.imageView.setImageResource(sliderModelItems.get(position).getBanner());
        if(position == sliderModelItems.size() - 2){
    viewPager2.post( runnable );
        }

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
    private Runnable runnable= new Runnable() {
        @Override
        public void run() {
            sliderModelItems.addAll( sliderModelItems );
            notifyDataSetChanged();

        }
    };


}
