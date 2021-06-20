package com.example.fridaye_com;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fridaye_com.ui.home.HorizontalProductModel;

import java.util.List;

public class GrideProductLayoutAdapter extends BaseAdapter {
    List<HorizontalProductModel> horizontalProductModelList;

    public GrideProductLayoutAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent= new Intent(parent.getContext(),ProductDetailsActivity.class);
                    parent.getContext().startActivity( productDetailsIntent );
                }
            } );




            ImageView productImage = view.findViewById(R.id.h_s_productimage);
            TextView productTitle = view.findViewById(R.id.h_s_product_title);
            TextView productDescription = view.findViewById(R.id.h_s_product_description);
            TextView productPrice= view.findViewById(R.id.h_s_product_price);

            productImage.setImageResource(horizontalProductModelList.get(position).getProductImage());
            productTitle.setText(horizontalProductModelList.get(position).getProduct_title());
            productDescription.setText(horizontalProductModelList.get(position).getProduct_description());
            productPrice.setText(horizontalProductModelList.get(position).getProduct_price());

        } else {
            view=convertView;
        }
        return  view;
    }
}
