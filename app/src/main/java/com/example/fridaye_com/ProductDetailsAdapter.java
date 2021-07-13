package com.example.fridaye_com;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    private int totalTabs;
    private String productDescription;
    private String productOtherDetails;
    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductDetailsAdapter(@NonNull @NotNull FragmentManager fm, int totalTabs, String productDescription, String productOtherDetails, List<ProductSpecificationModel> productSpecificationModelList) {
        super( fm );
        this.productDescription = productDescription;
        this.productOtherDetails = productOtherDetails;
        this.productSpecificationModelList = productSpecificationModelList;
        this.totalTabs=totalTabs;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ProductDescriptionFragment.productDescription =productDescription;
                return new ProductDescriptionFragment();
            case 1:
                ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                productSpecificationFragment.productSpecificationModelList =productSpecificationModelList;
                return new ProductSpecificationFragment();
            case 2:
                ProductDetailsFragment1.productDescriptionBody = productOtherDetails;
                return new ProductDetailsFragment1();
            default:
                return null;
        }
    }




    public int getCount() {
        return totalTabs;
    }
}
