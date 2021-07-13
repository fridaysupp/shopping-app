package com.example.fridaye_com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment1#} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment1 extends Fragment {

    public ProductDetailsFragment1() {
        // Required empty public constructor
    }
    private TextView OtherDetails;
    public static String productDescriptionBody;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_product_details1, container, false );
        OtherDetails = view.findViewById( R.id.tv_product_otherDetails );
        OtherDetails.setText( productDescriptionBody );
        return  view;
    }
}