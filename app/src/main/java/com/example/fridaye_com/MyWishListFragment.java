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
 * Use the {@link MyWishListFragment} factory method to
 * create an instance of this fragment.
 */
public class MyWishListFragment extends Fragment {

private RecyclerView myWishListRecyclerView;
    public MyWishListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_wish_list, container, false );
        myWishListRecyclerView = view.findViewById( R.id.wishlist_recyclerView );
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        myWishListRecyclerView.setLayoutManager( linearLayoutManager );

        List<WishListModel> wishListModelsList = new ArrayList<>();


        WishListAdapter wishListAdapter= new WishListAdapter( wishListModelsList ,true);
        myWishListRecyclerView.setAdapter( wishListAdapter );
        wishListAdapter.notifyDataSetChanged();
        return view;
    }
}