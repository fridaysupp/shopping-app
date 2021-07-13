package com.example.fridaye_com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fridaye_com.databinding.ActivityMainBinding;
import com.example.fridaye_com.ui.home.HomePageAdapter;
import com.example.fridaye_com.ui.home.HomePageModel;
import com.example.fridaye_com.ui.home.HorizontalProductModel;
import com.example.fridaye_com.ui.home.SliderModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.fridaye_com.DBQueries.lists;
import static com.example.fridaye_com.DBQueries.loadFragmentData;
import static com.example.fridaye_com.DBQueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerView categoryRecyclerView;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();

    private HomePageAdapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recycler_view);


        //homepage fake list
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add( new SliderModel( "null", "#ffffff" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#ffffff" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#ffffff" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#ffffff" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#ffffff" ) );

        List<HorizontalProductModel> horizontalProductModelFakeList = new ArrayList<>();
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        homePageModelFakeList.add( new HomePageModel( 0, sliderModelFakeList ) );
        homePageModelFakeList.add( new HomePageModel( 1, "", "#ffffff" ) );
        homePageModelFakeList.add( new HomePageModel( 2, "", "#ffffff", horizontalProductModelFakeList, new ArrayList<WishListModel>() ) );
        homePageModelFakeList.add( new HomePageModel( 3, "", "#ffffff", horizontalProductModelFakeList ) );
//homepage fake list

        ////////////////////////////////////////////////
        LinearLayoutManager testingLayoutManager= new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        adapter = new HomePageAdapter( homePageModelFakeList );


        int listPosition =0;
        for(int x=0;x<loadedCategoriesNames.size();x++){
            if (loadedCategoriesNames.get( x ).equals( title.toUpperCase() )){
                    listPosition = x;
            }
        }
        if(listPosition == 0){
            loadedCategoriesNames.add( title.toUpperCase());
            lists.add( new ArrayList<HomePageModel>() );
            loadFragmentData( categoryRecyclerView, this,loadedCategoriesNames.size() - 1 ,title);
        }else{
            adapter = new HomePageAdapter( lists.get( listPosition) );
        }

        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        /////////////////////////////////////////
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            //todo:search
            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}