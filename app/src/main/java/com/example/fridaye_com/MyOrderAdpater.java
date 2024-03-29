package com.example.fridaye_com;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyOrderAdpater extends RecyclerView.Adapter<MyOrderAdpater.ViewHoler> {

    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdpater(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @NotNull
    @Override
    public MyOrderAdpater.ViewHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.my_order_item_layout ,parent,false);

        return new ViewHoler( view );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyOrderAdpater.ViewHoler holder, int position) {

        int resource = myOrderItemModelList.get( position ).getProductImage();
        int rating = myOrderItemModelList.get( position ).getRating();
        String title = myOrderItemModelList.get( position).getProductTitle();
        String deliveryStatus = myOrderItemModelList.get( position).getDeliveryStatus();
        holder.setData( resource,title,deliveryStatus,rating );
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView productTitle;
        private ImageView productImage;
        private ImageView orderIndicator;
        private TextView deliveryStatus;
        private LinearLayout rateNowContainer;
        public ViewHoler(@NonNull @NotNull View itemView) {
            super( itemView );
            productImage= itemView.findViewById( R.id.product_image );
            productTitle = itemView.findViewById( R.id.product_title );
            orderIndicator= itemView.findViewById( R.id.order_indicator );
            deliveryStatus = itemView.findViewById( R.id.order_delivered_date );
            rateNowContainer = itemView.findViewById( R.id.rate_now_container );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent = new Intent(itemView.getContext(),OrderDetialsActivity.class);
                    itemView.getContext().startActivity( orderDetailsIntent );
                }
            } );
        }
        private void setData(int resource,String title,String deliveredDate,int rating){
            productImage.setImageResource( resource );
            productTitle.setText( title );
            if(deliveredDate.equals( "Cancelled" )){
                orderIndicator.setImageTintList( ColorStateList.valueOf( (itemView.getContext().getResources().getColor( R.color.btnRed )  ) ) );
            }else{
                orderIndicator.setImageTintList( ColorStateList.valueOf( (itemView.getContext().getResources().getColor( R.color.SuccessGreen )  ) ) );

            }
            deliveryStatus.setText( deliveredDate );

            ///////////////Rating Layout
            setRating( rating );
            for (int x=0;x<rateNowContainer.getChildCount();x++){
                final int starPosition = x;
                rateNowContainer.getChildAt( x ).setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }
                } );
            }
            ///////////////Rating Layout
        }
        private void setRating(int starPosition) {
            for(int x=0;x <rateNowContainer.getChildCount();x++){
                ImageView starButton = (ImageView) rateNowContainer.getChildAt( x );
                starButton.setImageTintList(ColorStateList.valueOf( Color.parseColor( "#bebebe" ) )  );
                if(x <= starPosition){
                    starButton.setImageTintList(ColorStateList.valueOf( Color.parseColor( "#ffbb00" ) )  );
                }
            }
        }
    }
}
