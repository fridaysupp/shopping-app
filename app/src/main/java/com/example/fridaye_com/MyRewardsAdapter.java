package com.example.fridaye_com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.ViewHolder> {

    private List<RewardsModel> rewardsModelList;
    private boolean useMiniLayout = false;

    public MyRewardsAdapter(List<RewardsModel> rewardsModelList, boolean useMiniLayout) {
        this.rewardsModelList = rewardsModelList;
        this.useMiniLayout = useMiniLayout;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (useMiniLayout) {
            view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.rewards_item_layout, parent, false );

        } else {
            view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.rewards_item_layout, parent, false );
        }
        return new ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String title = rewardsModelList.get( position ).getTitle();
        String expiryDate = rewardsModelList.get( position ).getExpiryDate();
        String body = rewardsModelList.get( position ).getCouponBody();

        holder.setData( title, expiryDate, body );

    }

    @Override
    public int getItemCount() {
        return rewardsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView couponTitle;
        private TextView CouponExpiryDate;
        private TextView couponBody;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            couponTitle = itemView.findViewById( R.id.coupon_title );
            CouponExpiryDate = itemView.findViewById( R.id.coupon_validity );
            couponBody = itemView.findViewById( R.id.coupon_body );

        }

        private void setData(String title, String expiryDate, String couponsBody) {
            couponTitle.setText( title );
            CouponExpiryDate.setText( expiryDate );
            couponBody.setText( couponsBody );
            if(useMiniLayout){
                itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    ProductDetailsActivity.couponTitle.setText( title );
                    ProductDetailsActivity.couponExpiryDate.setText( expiryDate );
                    ProductDetailsActivity.couponBody.setText( couponsBody );
                    ProductDetailsActivity.toggleDialogShow();
                    }
                } );
            }
        }
    }
}
