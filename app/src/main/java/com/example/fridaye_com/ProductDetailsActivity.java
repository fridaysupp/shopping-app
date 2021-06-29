package com.example.fridaye_com;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.fridaye_com.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private Button couponRedeemBtn;

    ////coupon dialog //
    public static TextView couponTitle;
    public static TextView couponExpiryDate;
    public static TextView couponBody;
    private static RecyclerView couponsRecyclerView;
    private static LinearLayout selectedCoupon;
    ////coupon dialog //


    ///Floating Action Button
    private FloatingActionButton addToWishListBTN;
    private boolean ALREADY_ADDED_WISHLIST_BTN = false;
    private boolean enable = true;
    ///Floating Action Button

    //////////Product Details layout Variables
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;
    private FragmentStateAdapter adapter;
    //////////Product Details layout Variables

    /////////Rating Layout
    private LinearLayout rateNowContainer;
    //////////Rating Layout

    private Button buyNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_product_details );


        ///Product Images layout
        //  viewPager2 = findViewById( R.id.product_images_view_pager );
        viewPager = findViewById( R.id.product_images_viewpager );
        tabLayout = findViewById( R.id.viewpager_indicator );
        addToWishListBTN = findViewById( R.id.add_to_wishtlist_btn );
        buyNowBtn = findViewById( R.id.buy_now_btn );
        ///Product Images layout

        ///////////////////////coupon redemption
        couponRedeemBtn = findViewById( R.id.coupon_redeem_btn );

        Dialog checkCouponPriceDialog = new Dialog( ProductDetailsActivity.this );
                checkCouponPriceDialog.setContentView( R.layout.coupon_redeem_dialog );
                checkCouponPriceDialog.setCancelable( true );
                checkCouponPriceDialog.getWindow().setLayout( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                ImageView toggleRecyclerView = checkCouponPriceDialog.findViewById( R.id.toggle_recyclerview );
                couponsRecyclerView = checkCouponPriceDialog.findViewById( R.id.coupons_recyclerView );

                ////Selected Coupon//
                selectedCoupon = checkCouponPriceDialog.findViewById( R.id.selected_coupon );
                couponTitle = checkCouponPriceDialog.findViewById( R.id.coupon_title );
                couponExpiryDate = checkCouponPriceDialog.findViewById( R.id.coupon_validity );
                couponBody = checkCouponPriceDialog.findViewById( R.id.coupon_body );

                ////Selected Coupon//


                TextView originalPrice = checkCouponPriceDialog.findViewById( R.id.original_price );
                TextView couponPrice = checkCouponPriceDialog.findViewById( R.id.coupon_discount_price );

                LinearLayoutManager layoutManager = new LinearLayoutManager( ProductDetailsActivity.this );
                layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                couponsRecyclerView.setLayoutManager( layoutManager );

                ArrayList<RewardsModel> rewardsModelsList = new ArrayList<>();
                rewardsModelsList.add( new RewardsModel( "Cashback", "till 14th ,Jun 2021", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.300/-." ) );
                rewardsModelsList.add( new RewardsModel( "Discount", "till 14th ,Jun 2021", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.300/-." ) );
                rewardsModelsList.add( new RewardsModel( "Buy 1 get 1 free", "till 14th ,Jun 2021", "GET 20% CASHBACK on any product above Rs.200/- and below Rs.300/-." ) );


                MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter( rewardsModelsList, true );
                couponsRecyclerView.setAdapter( myRewardsAdapter );
                myRewardsAdapter.notifyDataSetChanged();

                toggleRecyclerView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDialogShow();
                    }
                } );
                //////////////Coupon Dialog

        couponRedeemBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCouponPriceDialog.show();
            }
        } );

        ///////////////////////coupon redemptionBtn


        //////////Product Details layout Assign Variables
        productDetailsViewpager = findViewById( R.id.product_details_viewpager );
        productDetailsTabLayout = findViewById( R.id.product_details_tabLayout );

        ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter( getSupportFragmentManager(), productDetailsTabLayout.getTabCount() );
        productDetailsViewpager.setAdapter( productDetailsAdapter );
        productDetailsViewpager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( productDetailsTabLayout ) );
        productDetailsTabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );

        //////////Product Details layout Assign Variables


        //////Toolbar

        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( enable );
        //////Toolbar


        List<Integer> productItemList = new ArrayList<>();
        productItemList.add( R.mipmap.cart_black );
        productItemList.add( R.mipmap.cart_black );
        productItemList.add( R.mipmap.cart_black );
        productItemList.add( R.mipmap.cart_black );
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter( productItemList );
        viewPager.setAdapter( viewPagerAdapter );
        tabLayout.setupWithViewPager( viewPager, true );
        addToWishListBTN.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_WISHLIST_BTN) {
                    ALREADY_ADDED_WISHLIST_BTN = false;
                    addToWishListBTN.setImageTintList( ColorStateList.valueOf( Color.parseColor( "#9e9e9e" ) ) );
                } else {
                    ALREADY_ADDED_WISHLIST_BTN = true;
                    addToWishListBTN.setImageTintList( getResources().getColorStateList( R.color.btnRed ) );
                }


            }
        } );

        ///////////////Rating Layout
        rateNowContainer = findViewById( R.id.rate_now_container );
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt( x ).setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating( starPosition );
                }
            } );
        }
        ///////////////Rating Layout

        buyNowBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent( ProductDetailsActivity.this, DeliveryActivity.class );
                startActivity( deliveryIntent );
            }
        } );

    }

    ////////////CouponDialog-toggle recyclerview
    public static void toggleDialogShow() {
        if (couponsRecyclerView.getVisibility() == View.GONE) {
            couponsRecyclerView.setVisibility( View.VISIBLE );
            selectedCoupon.setVisibility( View.GONE );
        } else {
            couponsRecyclerView.setVisibility( View.GONE );
            selectedCoupon.setVisibility( View.VISIBLE );
        }

    }
    ////////////CouponDialog-toggle recyclerview


    private void setRating(int starPosition) {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starButton = (ImageView) rateNowContainer.getChildAt( x );
            starButton.setImageTintList( ColorStateList.valueOf( Color.parseColor( "#bebebe" ) ) );
            if (x <= starPosition) {
                starButton.setImageTintList( ColorStateList.valueOf( Color.parseColor( "#ffbb00" ) ) );
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.search_and_cart_icon, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_search_icon_white) {
            //todo:search
        } else if (id == R.id.main_cart_icon_white) {
            Intent cartIntent = new Intent( ProductDetailsActivity.this, MainActivity.class );
            showCart = true;
            startActivity( cartIntent );
        }
        return super.onOptionsItemSelected( item );
    }

}