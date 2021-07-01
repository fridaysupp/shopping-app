package com.example.fridaye_com.ui.home;

import java.util.List;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fridaye_com.ui.home.HorizontalProductModel;
import com.example.fridaye_com.ui.home.SliderModel;


public class HomePageModel {
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;
    private String backgroundColor;
    ////////////////////Banner Slider
    private List<SliderModel> sliderModelArrayList;
    public HomePageModel(int type, List<SliderModel> sliderModelArrayList) {
        this.type = type;
        this.sliderModelArrayList = sliderModelArrayList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModel> getSliderModelArrayList() {
        return sliderModelArrayList;
    }
    public void setSliderModelArrayList(List<SliderModel> sliderModelArrayList) {
        this.sliderModelArrayList = sliderModelArrayList;
    }
    /////////////////////Banner Slider

    /////////// Strip Ad

    private String resource;

    public HomePageModel(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /////////// Strip Ad


    ///////////Horizontal Product Layout && Grid Product Layout
    private String title;
    private List<HorizontalProductModel> horizontalProductModelList;
    public HomePageModel(int type, String title,String backgroundColor, List<HorizontalProductModel> horizontalProductModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor=backgroundColor;
        this.horizontalProductModelList = horizontalProductModelList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<HorizontalProductModel> getHorizontalProductModelList() {
        return horizontalProductModelList;
    }
    public void setHorizontalProductModelList(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }
    ///////////Horizontal Product Layout && Grid Product Layout



}
