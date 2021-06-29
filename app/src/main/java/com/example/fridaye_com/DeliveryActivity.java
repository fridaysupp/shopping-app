package com.example.fridaye_com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddNewAddressBtn;
    public static final int SELECT_ADDRESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delivery );
        toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Delivery" );
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        //Initialize Variables
        deliveryRecyclerView= findViewById( R.id.delivery_recyclerview );
        changeOrAddNewAddressBtn = findViewById( R.id.chnage_or_add_address );
        //Initialize Variables



        LinearLayoutManager layoutManager= new LinearLayoutManager( this );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        deliveryRecyclerView.setLayoutManager( layoutManager );

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 2,"Rs.49999/-","Rs.59999/-",1,0,0 ));
        cartItemModelList.add( new CartItemModel(0,R.drawable.phone,"Real me 5", 1,"Rs.49999/-","Rs.59999/-",1,1,1 ));
        cartItemModelList.add( new CartItemModel(1,"Price (2 items)","Rs.159999/-","Free","Rs.79999/-" ,"Rs.159999/-"));

        CartAdapter cartAdapter= new CartAdapter( cartItemModelList );
        deliveryRecyclerView.setAdapter( cartAdapter );
        cartAdapter.notifyDataSetChanged();

        changeOrAddNewAddressBtn.setVisibility( View.VISIBLE );
        changeOrAddNewAddressBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressChangeIntent = new Intent(DeliveryActivity.this,MyAddressesActivity.class);
                addressChangeIntent.putExtra( "MODE",SELECT_ADDRESS );
                startActivity(addressChangeIntent  );
            }
        } );
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

}