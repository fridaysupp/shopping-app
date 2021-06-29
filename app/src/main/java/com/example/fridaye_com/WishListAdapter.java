package com.example.fridaye_com;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
    private List<WishListModel> wishListModelList;
    private boolean wishList;

    public WishListAdapter(List<WishListModel> wishListModelList ,boolean wishList) {
        this.wishListModelList = wishListModelList;
        this.wishList=wishList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.wishlist, parent, false );


        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
            int resource = wishListModelList.get( position ).getProductImage();
            String title = wishListModelList.get( position ).getProductTitle();
            int freeCoupons = wishListModelList.get( position ).getFreeCoupons();
            String rating = wishListModelList.get( position ).getRating();
            int totalRatings = wishListModelList.get( position ).getTotalRatings();
            String productPrice = wishListModelList.get( position ).getProductPrice();
            String cuttedPrice = wishListModelList.get( position ).getCuttedPrice();
            String paymentMethod = wishListModelList.get( position ).getPaymentMethod();
            holder.setData( resource,title,freeCoupons,rating,totalRatings,productPrice,cuttedPrice,paymentMethod );
    }

    @Override
    public int getItemCount() {
        return wishListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupons;
        private ImageView freeCouponIcon;
        private TextView rating;
        private TextView totalRatings;
        private View priceCUT;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deleteBtn;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            productImage = itemView.findViewById( R.id.product_image );
            productTitle = itemView.findViewById( R.id.product_title );
            freeCoupons = itemView.findViewById( R.id.free_coupon );
            freeCouponIcon = itemView.findViewById( R.id.coupon_icon );
            rating = itemView.findViewById( R.id.tv_product_rating_mini_view );
            totalRatings = itemView.findViewById( R.id.total_ratings );
            priceCUT = itemView.findViewById( R.id.price_cut );
            productPrice = itemView.findViewById( R.id.product_price );
            cuttedPrice = itemView.findViewById( R.id.cutted_price );
            paymentMethod = itemView.findViewById( R.id.payment_method );
            deleteBtn = itemView.findViewById( R.id.delete_btn );
        }

        private void setData(int resource, String title, int freeCouponsNo, String averageRate, int totalRatingsNo, String price, String cuttedPriceValue, String payMethod) {
            productImage.setImageResource( resource );
            productTitle.setText( title );
            if (freeCouponsNo != 0) {
                freeCouponIcon.setVisibility( View.VISIBLE );
                if (freeCouponsNo == 1) {
                    freeCoupons.setText( "free " + freeCouponsNo + "coupon" );
                } else {
                    freeCoupons.setText( "free " + freeCouponsNo + "coupons" );

                }

            } else {
                freeCouponIcon.setVisibility( View.INVISIBLE );
                freeCoupons.setVisibility( View.INVISIBLE );
            }
            rating.setText( averageRate );
            totalRatings.setText( totalRatingsNo+"(ratings)" );
            productPrice.setText( price );
            cuttedPrice.setText( cuttedPriceValue );
            paymentMethod.setText( payMethod );
            if (wishList){
                deleteBtn.setVisibility( View.VISIBLE );
            }else{
                deleteBtn.setVisibility( View.GONE );
            }
            deleteBtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( itemView.getContext(),"delete",Toast.LENGTH_SHORT ).show();
                }
            } );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity( productDetailsIntent );
                }
            } );
        }
    }
}
