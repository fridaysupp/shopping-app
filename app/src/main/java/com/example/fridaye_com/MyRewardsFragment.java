package com.example.fridaye_com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyRewardsFragment#} factory method to
 * create an instance of this fragment.
 */
public class MyRewardsFragment extends Fragment {


    private RecyclerView rewardsRecyclerView;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_rewards, container, false );
        rewardsRecyclerView = (RecyclerView)view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        rewardsRecyclerView.setLayoutManager( layoutManager );


        ArrayList<RewardsModel> rewardsModelsList = new ArrayList<>();
        rewardsModelsList.add( new RewardsModel( "Cashback", "till 14th ,Jun 2021", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.300/-." ) );
        rewardsModelsList.add( new RewardsModel( "Discount", "till 14th ,Jun 2021", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.300/-." ) );
        rewardsModelsList.add( new RewardsModel( "Buy 1 get 1 free", "till 14th ,Jun 2021", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.300/-." ) );


        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter( rewardsModelsList ,false);
        rewardsRecyclerView.setAdapter( myRewardsAdapter );
        myRewardsAdapter.notifyDataSetChanged();

        return view;


    }
}