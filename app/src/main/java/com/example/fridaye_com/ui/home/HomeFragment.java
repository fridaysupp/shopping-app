package com.example.fridaye_com.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fridaye_com.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class HomeFragment extends Fragment {

    private Object SliderAdapter;

    public HomeFragment() {

    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    ///////////Banner Slider
    private ViewPager2 bannerSliderViewPager;
        ArrayList<SliderModel>sliderModelArrayList;
    private int currentPage = 2;
    private Timer timer;

    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    ///////////Banner Slider


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<HomeViewModel> categoryModelList = new ArrayList<HomeViewModel>();
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));
        categoryModelList.add(new HomeViewModel("link", "Home"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        ////////////////// Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);
        int[] images = {R.mipmap.bell,R.mipmap.home_icon,R.mipmap.bell};

        sliderModelArrayList = new ArrayList<>();
        bannerSliderViewPager.setAdapter((RecyclerView.Adapter) SliderAdapter);

        for(int i=0; i<images.length;i++){
            SliderModel sliderModel = new SliderModel(images[i]);
            sliderModelArrayList.add(sliderModel);
        }
        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelArrayList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setClipChildren(false);
        bannerSliderViewPager.setOffscreenPageLimit(2);
        bannerSliderViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);




        ////////////////// Banner Slider

        return view;
    }
}