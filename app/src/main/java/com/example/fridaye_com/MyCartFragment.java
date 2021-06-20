package com.example.fridaye_com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCartFragment#} factory method to
 * create an instance of this fragment.
 */
public class MyCartFragment extends Fragment {
    private RecyclerView cartItemsRecyclerView;
    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate( R.layout.fragment_my_cart, container, false );
        cartItemsRecyclerView= view.findViewById( R.id.cart_items_recyclerview );
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        cartItemsRecyclerView.setLayoutManager( linearLayoutManager );
        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 2,"Rs.49999/-","Rs.59999/-",1,0,0 ));
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 0,"Rs.49999/-","Rs.59999/-",1,1,0 ));
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 2,"Rs.49999/-","Rs.59999/-",1,2,0 ));
        cartItemModelList.add( new CartItemModel(1,"Price (3 items)","Rs.159999/-","Free","Rs.59999/-" ,"Rs.159999/-"));

        CartAdapter cartAdapter= new CartAdapter( cartItemModelList );
        cartItemsRecyclerView.setAdapter( cartAdapter );
        cartAdapter.notifyDataSetChanged();
        return view;
    }
}