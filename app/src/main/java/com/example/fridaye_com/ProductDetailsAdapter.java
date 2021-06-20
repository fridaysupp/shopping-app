package com.example.fridaye_com;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    private int totalTabs;

    public ProductDetailsAdapter(@NonNull @NotNull FragmentManager fragment,int totalTabs) {
        super( fragment );
        this.totalTabs=totalTabs;

    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProductDescriptionFragment();
            case 1:
                return new ProductSpecificationFragment();
            case 2:
                return new ProductDetailsFragment1();
            default:
                return null;
        }
    }




    public int getCount() {
        return totalTabs;
    }
}
