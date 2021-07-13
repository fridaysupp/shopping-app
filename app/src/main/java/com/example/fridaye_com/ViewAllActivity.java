package com.example.fridaye_com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.fridaye_com.ui.home.HorizontalProductModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridView gridView;
    public static  List <HorizontalProductModel> horizontalProductModelList ;
    public static  List<WishListModel> wishListModelsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_all );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle(getIntent().getStringExtra( "title" ) );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        recyclerView=findViewById( R.id.recyclerview_view_all_activity );
        gridView=findViewById( R.id.grid_view );
        int layout_code = getIntent().getIntExtra( "layout_code",-1 );
        if(layout_code == 0) {
            recyclerView.setVisibility( View.VISIBLE );
            LinearLayoutManager layoutManager = new LinearLayoutManager( this );
            layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerView.setLayoutManager( layoutManager );

            WishListAdapter adapter = new WishListAdapter( wishListModelsList, false );
            recyclerView.setAdapter( adapter );
            adapter.notifyDataSetChanged();
        }else if(layout_code == 1) {

            gridView.setVisibility( View.VISIBLE );
            ///////////Horizontal Product Layout
            GrideProductLayoutAdapter grideProductLayoutAdapter = new GrideProductLayoutAdapter( horizontalProductModelList );
            gridView.setAdapter( grideProductLayoutAdapter );
        }

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