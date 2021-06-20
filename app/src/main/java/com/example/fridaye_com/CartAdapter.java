package com.example.fridaye_com;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
switch (cartItemModelList.get( position ).getType()){
    case 0:
        return  CartItemModel.CART_ITEM;
    case 1:
        return CartItemModel.TOTAL_AMOUNT;
    default:
        return -1;
}
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View CartItemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cart_item_layout ,parent,false);
                return new cartItemViewHolder( CartItemView );

            case CartItemModel.TOTAL_AMOUNT:
                View CartTotalView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.cart_total_amount_layout ,parent,false);
                return new cartTotalAmountViewHolder( CartTotalView );
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
    switch (cartItemModelList.get( position ).getType()){
        case CartItemModel.CART_ITEM:
            int resource = cartItemModelList.get( position ).getProductImage();
            String title = cartItemModelList.get( position ).getProductTitle();
            int freeCoupons = cartItemModelList.get( position ).getFreeCoupon();
            String productPrice = cartItemModelList.get( position ).getProductPrice();
            String cuttedPrice = cartItemModelList.get( position ).getCuttedPrice();
            int offersAppled = cartItemModelList.get( position ).getOffersApplied();
            ((cartItemViewHolder)holder).setItemDetails( resource,title,freeCoupons,productPrice,cuttedPrice,offersAppled );
            break;
            case CartItemModel.TOTAL_AMOUNT:
                String totalItems=cartItemModelList.get( position ).getTotalItems();
                String totalItemsPrice=cartItemModelList.get( position ).getTotalItemsPrice();
                String deliveryPrice=cartItemModelList.get( position ).getDeliveryPrice();
                String totalAmount=cartItemModelList.get( position ).getTotalAmount();
                String savedAmount=cartItemModelList.get( position ).getSavedAmount();
                ((cartTotalAmountViewHolder)holder).setTotalAmount(totalItems,totalItemsPrice,totalAmount,deliveryPrice,savedAmount );

                break;
        default:
            return;

    }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }
    class  cartItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private ImageView freeCouponIcon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView couponsApplied;
        private TextView productQuantity;

        public cartItemViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            productImage=itemView.findViewById( R.id.product_image );
            productTitle = itemView.findViewById( R.id.product_title );
            freeCouponIcon=itemView.findViewById( R.id.free_coupon_icon );
            freeCoupons=itemView.findViewById( R.id.tv_free_coupon );
            productPrice =itemView.findViewById( R.id.product_price );
            cuttedPrice=itemView.findViewById( R.id.cutted_price );
            offersApplied=itemView.findViewById( R.id.offers_applied );
            couponsApplied=itemView.findViewById( R.id.coupons_applied );
            productQuantity=itemView.findViewById( R.id.product_quantity );


        }
        private void setItemDetails(int resource,String title,int freeCouponsNo,String productPriceText, String productCuttedPriceText,int offersAppliedNo){
            productImage.setImageResource( resource );
            productTitle.setText( title );
            if(freeCouponsNo > 0){
                freeCouponIcon.setVisibility( View.VISIBLE );
                freeCoupons.setVisibility( View.VISIBLE );
                if(freeCouponsNo == 1){
                    freeCoupons.setText( "free" + freeCouponsNo +"Coupon" );

                }else {
                    freeCoupons.setText( "free" + freeCouponsNo +"Coupons" );

                }
            }else{
                freeCouponIcon.setVisibility( View.INVISIBLE );
                freeCoupons.setVisibility( View.INVISIBLE );
            }
            productPrice.setText( productPriceText );
            cuttedPrice.setText( productCuttedPriceText );

            if(offersAppliedNo > 0){
                offersApplied.setVisibility( View.VISIBLE );
                offersApplied.setText( offersAppliedNo +"offers applied" );
            }else{
                offersApplied.setVisibility( View.INVISIBLE );
            }

        }
    }
    class cartTotalAmountViewHolder extends  RecyclerView.ViewHolder{
        private  TextView totalItems;
        private  TextView totalItemPrice;
        private  TextView deliveryPrice;
        private  TextView totalAmount;
        private  TextView savedAmount;
        public cartTotalAmountViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            totalItems= itemView.findViewById( R.id.total_items );
            totalItemPrice = itemView.findViewById( R.id.total_items_price );
            deliveryPrice= itemView.findViewById( R.id.delivery_charge_price );
            totalAmount = itemView.findViewById( R.id.total_price );
            savedAmount= itemView.findViewById( R.id.saved_amount );
        }
        private void setTotalAmount(String totalItemText,String totalItemPriceText,String totalAmountText,String deliveryPriceText,String savedAmountText){
            totalItems.setText( totalItemText );
            totalItemPrice.setText(totalItemPriceText  );
            deliveryPrice.setText( deliveryPriceText );
            totalAmount.setText(  totalAmountText);
            savedAmount.setText( savedAmountText );
        }
    }
}
