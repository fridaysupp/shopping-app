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
 * Use the {@link ProductSpecificationFragment} factory method to
 * create an instance of this fragment.
 */
public class ProductSpecificationFragment extends Fragment {

    private RecyclerView productSpecificationRecyclerView;


    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    public static List<ProductSpecificationModel> productSpecificationModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_product_specification, container, false );

        productSpecificationRecyclerView = view.findViewById( R.id.product_specification_recycleview );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( view.getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        productSpecificationRecyclerView.setLayoutManager( linearLayoutManager );

//        productSpecificationModelList.add( new ProductSpecificationModel( 0,"General" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 0,"Display" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );
//        productSpecificationModelList.add( new ProductSpecificationModel( 1,"RAM", "4 GB" ) );

        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList );
        productSpecificationRecyclerView.setAdapter( productSpecificationAdapter );
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }
}