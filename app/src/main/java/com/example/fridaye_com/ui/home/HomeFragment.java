package com.example.fridaye_com.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fridaye_com.CategoryAdapter;
import com.example.fridaye_com.GrideProductLayoutAdapter;
import com.example.fridaye_com.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private Object SliderAdapter;

    public HomeFragment() {

    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
     private RecyclerView testing;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<HomeViewModel> categoryModelList = new ArrayList<HomeViewModel>();
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Clothing"));
        categoryModelList.add(new HomeViewModel("link", "Women's Wear"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        ////////////////// Banner Slider

       List<SliderModel> sliderModelArrayList = new ArrayList<SliderModel>();

                sliderModelArrayList.add(new SliderModel(R.mipmap.home_icon,"#007AE4"));
                sliderModelArrayList.add(new SliderModel(R.mipmap.custom_error_icon,"#007AE4"));
                sliderModelArrayList.add(new SliderModel(R.mipmap.bell,"#007AE4"));
                sliderModelArrayList.add(new SliderModel(R.mipmap.cart_black,"#007AE4"));
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
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.phone,"Redmi 5A","SA 625 processor","Rs.6999"));

             ///////////Horizontal Product Layout


        ////////////////////////////////////////////////
         testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager= new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelArrayList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner_image,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.cart_black,"#ffff00"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(0,sliderModelArrayList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner_image,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.cart_black,"#ffff00"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductModelList));


        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        /////////////////////////////////////////
        return view;
    }


}