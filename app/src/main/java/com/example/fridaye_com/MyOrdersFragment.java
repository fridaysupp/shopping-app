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
 * Use the {@link MyOrdersFragment#} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {
private RecyclerView myOrdersRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_orders, container, false );
      myOrdersRecyclerView = view.findViewById( R.id.my_orders_recyclerview );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        myOrdersRecyclerView.setLayoutManager( layoutManager );
        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add( new MyOrderItemModel( R.drawable.phone,2,"Pixel 2 Black","Delivered on Monday ,15hth Jan 2021"));
        myOrderItemModelList.add( new MyOrderItemModel( R.mipmap.cart_black,1,"Pixel 2 Black","Delivered on Monday ,15hth Jan 2021"));
        myOrderItemModelList.add( new MyOrderItemModel( R.drawable.phone,0,"Pixel 2 Black","Cancelled"));

        MyOrderAdpater myOrderAdpater = new MyOrderAdpater( myOrderItemModelList );
        myOrdersRecyclerView.setAdapter( myOrderAdpater );
        myOrderAdpater.notifyDataSetChanged();
    return view;
    }
}