package com.example.fridaye_com.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fridaye_com.ProductDetailsActivity;
import com.example.fridaye_com.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder>
{

    private List<HorizontalProductModel> horizontalProductModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @NonNull
    @NotNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_scroll_item_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {
    String resource = horizontalProductModelList.get(position).getProductImage();
    String title= horizontalProductModelList.get(position).getProduct_title();
    String description= horizontalProductModelList.get(position).getProduct_description();
    String price= horizontalProductModelList.get(position).getProduct_price();
    String productId= horizontalProductModelList.get( position ).getProductID();

    holder.setData(productId,resource,title,description,price);


    }

    @Override
    public int getItemCount() {
        if(horizontalProductModelList.size() > 8)
        {
            return 8;
        }else{
            return horizontalProductModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.h_s_productimage);
            productTitle=itemView.findViewById(R.id.h_s_product_title);
            productDescription=itemView.findViewById(R.id.h_s_product_description);
            productPrice=itemView.findViewById(R.id.h_s_product_price);


            ///Testing code for tablayout & viewpager

        }
        private void setData(final String productId,String resource,String title,String description,String price) {
            //todo:change place holder icon
            Glide.with( itemView.getContext() ).load( resource ).apply( new RequestOptions().placeholder( R.mipmap.home_icon ) ).into( productImage );
            //todo:change place holder icon
            productTitle.setText( title );
            productDescription.setText( description );
            productPrice.setText( "Rs." + price + "/-" );
            if (!title.equals( "" )){
                itemView.setOnClickListener( v -> {
                    Intent intent = new Intent( itemView.getContext(), ProductDetailsActivity.class );
                    intent.putExtra( "PRODUCT_ID",productId );
                    itemView.getContext().startActivity( intent );
                } );
        }

        }

    }
}
