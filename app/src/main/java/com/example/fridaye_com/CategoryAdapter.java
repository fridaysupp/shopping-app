package com.example.fridaye_com;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fridaye_com.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<HomeViewModel> categoryModelList;
    private int lastPosition = -1;

    public CategoryAdapter(List<HomeViewModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @NotNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.ViewHolder holder, int position) {
        String icon = categoryModelList.get(position).getCategoryIconLink();
        String iconName = categoryModelList.get(position).getCategoryName();
        holder.setCategory(iconName,position);
        holder.setCategoryIcon( icon );

        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation( holder.itemView.getContext(), R.anim.fade_in );
            holder.itemView.setAnimation( animation );
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);

        }
        private void setCategoryIcon(String iconUrl){
            if (!iconUrl.equals( "null" )) {
                //todo:change placeholder icon
                Glide.with( itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.mipmap.home_icon)).into(categoryIcon);
                //todo:change placeholder icon

            }
            else {
                categoryIcon.setImageResource( R.mipmap.home_icon );
            }
        }
        private void setCategory(final String name,int position){
            categoryName.setText(name);
            if (!name.equals( "" )) {
                itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != 0) {
                            Intent categoryIntent = new Intent( itemView.getContext(), CategoryActivity.class );
                            categoryIntent.putExtra( "CategoryName", name );
                            itemView.getContext().startActivity( categoryIntent );
                        }
                    }
                } );
            }

        }
    }
}
