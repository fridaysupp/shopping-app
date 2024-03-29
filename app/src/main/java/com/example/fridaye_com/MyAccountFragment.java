package com.example.fridaye_com;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAccountFragment#} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {

    public static final int MANAGE_ADDRESS = 1;
    private Button viewAllAddressBtn;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_account, container, false );
        viewAllAddressBtn=view.findViewById( R.id.view_all_address_btn );
        viewAllAddressBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mangeAddressIntent = new Intent(getContext(),MyAddressesActivity.class);
                mangeAddressIntent.putExtra( "MODE",MANAGE_ADDRESS );
                startActivity(mangeAddressIntent  );
            }
        } );
        return view;
    }
}