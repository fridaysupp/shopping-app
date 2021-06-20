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

public class CategoryActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerView categoryRecyclerView;

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

        ////////////////// Banner Slider
        List<SliderModel> sliderModelArrayList = new ArrayList<SliderModel>();

        sliderModelArrayList.add(new SliderModel(R.mipmap.home_icon,"#007AE4"));
        sliderModelArrayList.add(new SliderModel(R.mipmap.home_icon,"#007AE4"));
        sliderModelArrayList.add(new SliderModel(R.mipmap.home_icon,"#007AE4"));
        sliderModelArrayList.add(new SliderModel(R.mipmap.home_icon,"#007AE4"));
        sliderModelArrayList.add(new SliderModel(R.mipmap.home_icon,"#007AE4"));
        ////////////////// Banner Slider



        ///////////Horizontal Product Layout

        List <HorizontalProductModel> horizontalProductModelList= new ArrayList<>();
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));

        ///////////Horizontal Product Layout


        ////////////////////////////////////////////////
        LinearLayoutManager testingLayoutManager= new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelArrayList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner_image,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.cart_black,"#ffff00"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
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