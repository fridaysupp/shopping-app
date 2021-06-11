package com.example.fridaye_com.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridaye_com.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<HomeViewModel> categoryModelList;

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
        holder.setCategoryName(iconName);
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
        private void setCategoryIcon(){
            //todo:set categoryIcons Here
        }
        private void setCategoryName(String name){
            categoryName.setText(name);
        }
    }
}
