package com.example.fridaye_com;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCartFragment#} factory method to
 * create an instance of this fragment.
 */
public class MyCartFragment extends Fragment {

    private RecyclerView cartItemsRecyclerView;
    private Button  continueBtn;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate( R.layout.fragment_my_cart, container, false );

        //Initialize Views
        cartItemsRecyclerView= view.findViewById( R.id.cart_items_recyclerview );
        continueBtn=view.findViewById( R.id.cart_continue_btn );
        //Initialize View

        LinearLayoutManager layoutManager= new LinearLayoutManager( getContext() );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        cartItemsRecyclerView.setLayoutManager( layoutManager );

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 2,"Rs.49999/-","Rs.59999/-",1,0,0 ));
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 1,"Rs.49999/-","Rs.59999/-",1,1,1 ));
        cartItemModelList.add( new CartItemModel(1,"Price (2 items)","Rs.159999/-","Free","Rs.79999/-" ,"Rs.159999/-"));

        CartAdapter cartAdapter= new CartAdapter( cartItemModelList );
        cartItemsRecyclerView.setAdapter( cartAdapter );
        cartAdapter.notifyDataSetChanged();


        continueBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddAddressIntent= new Intent(getContext(),AddAddressActivity.class);
                getContext().startActivity( AddAddressIntent );
            }
        } );
        return view;
    }
}