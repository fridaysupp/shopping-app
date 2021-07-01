package com.example.fridaye_com.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {



    private String categoryIcon;
    private String categoryName;

    public HomeViewModel(String categoryIcon, String categoryName) {
        this.categoryIcon = categoryIcon;
        this.categoryName = categoryName;

    }

    public String getCategoryIconLink() {
        return categoryIcon;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        categoryIcon = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}