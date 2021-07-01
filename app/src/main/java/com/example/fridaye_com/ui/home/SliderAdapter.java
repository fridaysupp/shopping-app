package com.example.fridaye_com.ui.home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fridaye_com.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

   private List<SliderModel> sliderModelItems;
    private ViewPager2 viewPager2;


    public SliderAdapter(List<SliderModel> sliderModelItems, ViewPager2 viewPager2) {
        this.sliderModelItems = sliderModelItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int position) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.slider_layout, parent, false );
             return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.itemView.getContext();
        String  ColorString = sliderModelItems.get( position ).getBackgroundColor();
        Glide.with( holder.itemView.getContext() ).load( sliderModelItems.get( position ).getBanner() )
                .apply( new RequestOptions().placeholder( R.mipmap.home_icon ) ).into( holder.imageView );
         holder.setBannerColor(ColorString );
        if (position == sliderModelItems.size() - 2) {
            viewPager2.post( runnable );
        }
    }

    @Override
    public int getItemCount() {
        return sliderModelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ConstraintLayout bannerContainer;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.banner_slide );
            bannerContainer = itemView.findViewById( R.id.banner_container );
        }
        private String setBannerColor(String ColorString){
            bannerContainer.setBackgroundTintList( ColorStateList.valueOf(Color.parseColor( ColorString ) ) );
            return ColorString;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderModelItems.addAll( sliderModelItems );
            notifyDataSetChanged();

        }
    };


}
