package com.example.fridaye_com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment1#} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment1 extends Fragment {

    public ProductDetailsFragment1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_product_details1, container, false );
    }
}