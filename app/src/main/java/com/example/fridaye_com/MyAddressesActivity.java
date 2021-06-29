package com.example.fridaye_com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.fridaye_com.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {
    private RecyclerView addressRecyclerView;
    private static AddressAdapter addressAdapter;
    private Button deliveryHereBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_addresses );
      Toolbar  toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "My Addresses" );
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );


        addressRecyclerView= findViewById( R.id.addresses_recycler_view );
        deliveryHereBtn=findViewById( R.id.deliver_here_btn );

        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        addressRecyclerView.setLayoutManager( layoutManager );

        List<AddressesModel> addressesModelList= new ArrayList<>();
        addressesModelList.add( new AddressesModel( "Harsh vadera","Xyzxzxsccxczcscczxcscscxcsccascsczxcsscxc","395002",true ) );
        addressesModelList.add( new AddressesModel( "Harsh vadera","Xyzxzxsccxczcscczxcscscxcsccascsczxcsscxc","395002",false ) );
        addressesModelList.add( new AddressesModel( "Harsh vadera","Xyzxzxsccxczcscczxcscscxcsccascsczxcsscxc","395002" ,false) );
        addressesModelList.add( new AddressesModel( "Harsh vadera","Xyzxzxsccxczcscczxcscscxcsccascsczxcsscxc","395002",false ) );
        addressesModelList.add( new AddressesModel( "Harsh vadera","Xyzxzxsccxczcscczxcscscxcsccascsczxcsscxc","395002" ,false) );
        addressesModelList.add( new AddressesModel( "Harsh vadera","Xyzxzxsccxczcscczxcscscxcsccascsczxcsscxc","395002",false ) );

        int MODE= getIntent().getIntExtra( "MODE",-1 );
        if(MODE == SELECT_ADDRESS){
            deliveryHereBtn.setVisibility( View.VISIBLE );
        }else {
            deliveryHereBtn.setVisibility( View.GONE );
        }
        addressAdapter= new AddressAdapter( addressesModelList,MODE );
        addressRecyclerView.setAdapter( addressAdapter );
        ((SimpleItemAnimator)addressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressAdapter.notifyDataSetChanged();
    }
    public static void refreshItem(int deselect,int select){
            addressAdapter.notifyItemChanged( deselect );
            addressAdapter.notifyItemChanged(select);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}