package com.example.fridaye_com.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {



    private String CategoryIconLink;
    private String categoryName;

    public HomeViewModel(String categoryIconLink, String categoryName) {
        CategoryIconLink = categoryIconLink;
        this.categoryName = categoryName;

    }

    public String getCategoryIconLink() {
        return CategoryIconLink;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        CategoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}