package com.example.fridaye_com;

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

import static com.example.fridaye_com.DeliveryActivity.SELECT_ADDRESS;
import static com.example.fridaye_com.MyAccountFragment.MANAGE_ADDRESS;
import static com.example.fridaye_com.MyAddressesActivity.refreshItem;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
private List<AddressesModel> addressesModelList ;
private int MODE;
private int pre_selectedPoistion = -1;

    public AddressAdapter(List<AddressesModel> addressesModelList,int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.addresses_item_layout,parent,false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String fullName= addressesModelList.get( position ).getFullName();
        String address= addressesModelList.get( position ).getAddress();
        String Pincode= addressesModelList.get( position ).getPincode();
        Boolean selected= addressesModelList.get( position).getSelected();
        holder.setData( fullName,address,Pincode ,selected,position);
    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView fullName;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            fullName = itemView.findViewById( R.id.name );
            address = itemView.findViewById( R.id.address );
            pincode= itemView.findViewById( R.id.pincode );
            icon =itemView.findViewById( R.id.icon_view );
            optionContainer = itemView.findViewById( R.id.option_container );
        }
        private void setData(String FullName,String address,String pincode,Boolean selected , int position ){
            fullName.setText( FullName );
            fullName.setText( address );
            fullName.setText( pincode );
            if (MODE == SELECT_ADDRESS){
            icon.setImageResource( R.drawable.check );
            if(selected){
            icon.setVisibility( View.VISIBLE );
            pre_selectedPoistion= position;
            }else{
                icon.setVisibility( View.GONE );
            }
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pre_selectedPoistion != position){
                        addressesModelList.get( position ).setSelected( true );
                        addressesModelList.get( pre_selectedPoistion ).setSelected( false );
                        refreshItem(pre_selectedPoistion,position);
                        pre_selectedPoistion=position;
                    }

                }
            } );
            }else if(MODE == MANAGE_ADDRESS){
                optionContainer.setVisibility( View.GONE );
                icon.setImageResource( R.drawable.vertical_dots);
                icon.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionContainer.setVisibility( View.VISIBLE );
                        refreshItem( pre_selectedPoistion,pre_selectedPoistion );
                        pre_selectedPoistion = position;


                    }
                } );
                itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshItem( pre_selectedPoistion,pre_selectedPoistion );
                        pre_selectedPoistion = -1;
                    }
                } );

            }

        }
    }
}
