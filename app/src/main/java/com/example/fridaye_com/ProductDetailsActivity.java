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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import static com.example.fridaye_com.MainActivity.showCart;
import static com.example.fridaye_com.RegisterActivity.setSignUpFragment;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView productTitle;
    private TextView productPrice;
    private TextView productCuttedPrice;
    private TextView averageRatingMiniView;
    private TextView totalRatingMiniView;
    private ImageView codIndicator;
    private TextView tvCODIndicator;
    private TextView rewardTitle;
    private TextView rewardBody;
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
    private LinearLayout couponRedeemLayout;
    ////coupon dialog //


    ///Floating Action Button
    private FloatingActionButton addToWishListBTN;
    private boolean ALREADY_ADDED_TO_WISHLIST = false;
    private boolean enable = true;
    ///Floating Action Button

    //////////Product Details layout Variables
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;
    private ConstraintLayout productTabsLayoutContainer, productDetailsOnlyContainer;
    private TextView productOnlyDescriptionBody;
    private String productDescription;
    private String productOtherDetails;
    private List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();

    //////////Product Details layout Variables

    /////////Rating Layout
    private LinearLayout rateNowContainer;
    private TextView totalRatings;
    private LinearLayout ratingsNoContainer;
    private TextView totalRatingsFigure;
    private LinearLayout ratingsProgressbarContainer;
    private TextView averageRating;
    //////////Rating Layout

    private Button buyNowBtn;
    private LinearLayout addToCartBtn;
    private Dialog signInDialog;
    private FirebaseUser currentUser;
    private FirebaseFirestore firebaseFirestore;
    private String productID;

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
        productTitle = findViewById( R.id.product_title );
        productPrice = findViewById( R.id.tv_product_price );
        productCuttedPrice = findViewById( R.id.tv_cutted_price );
        averageRatingMiniView = findViewById( R.id.tv_product_rating_mini_view );
        totalRatingMiniView = findViewById( R.id.tv__total_ratings_mini_view );
        codIndicator = findViewById( R.id.cod_indicator_image_view );
        tvCODIndicator = findViewById( R.id.tv_cod_indicator );
        rewardTitle = findViewById( R.id.rewards_title );
        rewardBody = findViewById( R.id.reward_body );
        productTabsLayoutContainer = findViewById( R.id.product_details_tabs_container );
        productDetailsOnlyContainer = findViewById( R.id.product_details_conatiner );
        productOnlyDescriptionBody = findViewById( R.id.product_details_body );
        totalRatings = findViewById( R.id.total_ratings );
        ratingsNoContainer = findViewById( R.id.ratings_numbers_container );
        totalRatingsFigure = findViewById( R.id.total_rating_figure );
        ratingsProgressbarContainer = findViewById( R.id.rating_progressbar_container );
        averageRating = findViewById( R.id.average_rating );
        addToCartBtn= findViewById( R.id.add_to_cart_btn );
        couponRedeemLayout = findViewById( R.id.coupon_redeem_layout );

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

        //ProductDetailsAdapter productDetailsAdapter ;
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


        final List<String> productImages = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance(); //connection to fireStoreDatabase
        productID = getIntent().getStringExtra("PRODUCT_ID");
        firebaseFirestore.collection( "PRODUCTS" ).document( productID )
                .get().addOnCompleteListener( new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    for (long x = 1; x < (long) documentSnapshot.get( "no_of_product_images" ) + 1; x++) {
                        productImages.add( documentSnapshot.get( "product_image_" + x ).toString() );
                    }
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter( productImages );
                    viewPager.setAdapter( viewPagerAdapter );

                    productTitle.setText( documentSnapshot.get( "product_title_1" ).toString() );
                    averageRatingMiniView.setText( documentSnapshot.get( "average_rating" ).toString() );
                    totalRatingMiniView.setText( "(" + (long) documentSnapshot.get( "total_ratings" ) + ")ratings" );
                    productPrice.setText( "Rs." + documentSnapshot.get( "product_price" ).toString() + "/-" );
                    productCuttedPrice.setText( "Rs." + documentSnapshot.get( "cutted_price" ).toString() + "/-" );

                    if ((boolean) documentSnapshot.get( "COD" )) {
                        codIndicator.setVisibility( View.VISIBLE );
                        tvCODIndicator.setVisibility( View.VISIBLE );
                    } else {
                        codIndicator.setVisibility( View.INVISIBLE );
                        tvCODIndicator.setVisibility( View.INVISIBLE );
                    }
                    rewardTitle.setText( (long) documentSnapshot.get( "free_coupons" ) + documentSnapshot.get( "free_coupon_title" ).toString() );
                    rewardBody.setText( documentSnapshot.get( "free_coupon_body" ).toString() );

                    if ((boolean) documentSnapshot.get( "use_tab_layout" )) {
                        productTabsLayoutContainer.setVisibility( View.VISIBLE );
                        productDetailsOnlyContainer.setVisibility( View.GONE );
                        productDescription = documentSnapshot.get( "product_description" ).toString();
                        productOtherDetails = documentSnapshot.get( "product_other_details" ).toString();


                        for (long x = 1; x < (long) documentSnapshot.get( "total_spec_titles" ) + 1; x++) {
                            productSpecificationModelList.add( new ProductSpecificationModel( 0, documentSnapshot.get( "spec_title_" + x ).toString() ) );
                            for (long y = 1; y < (long) documentSnapshot.get( "spec_title_" + x + "_total_field" ) + 1; y++) {
                                productSpecificationModelList.add( new ProductSpecificationModel( 1, documentSnapshot.get( "spec_title_" + x + "_field_" + y + "_name" ).toString(), documentSnapshot.get( "spec_title_" + x + "_field_" + y + "_value" ).toString() ) );
                            }
                        }
                    } else {
                        productTabsLayoutContainer.setVisibility( View.GONE );
                        productDetailsOnlyContainer.setVisibility( View.VISIBLE );
                        productOnlyDescriptionBody.setText( documentSnapshot.get( "product_description" ).toString() );

                    }
                    totalRatings.setText( "(" + (long) documentSnapshot.get( "total_ratings" ) + ") ratings" );
                    for (int x = 0; x < 5; x++) {
                        TextView rating = (TextView) ratingsNoContainer.getChildAt( x );
                        rating.setText( String.valueOf( (long) documentSnapshot.get( (5 - x) + "_star" ) ) );

                        ProgressBar progressBar = (ProgressBar) ratingsProgressbarContainer.getChildAt( x );
                        int maxProgress = Integer.parseInt( String.valueOf( (long) documentSnapshot.get( "total_ratings" ) ) );
                        progressBar.setMax( maxProgress );
                        progressBar.setProgress( Integer.parseInt( String.valueOf( (long) documentSnapshot.get( 5 - x + "_star" ) ) ) );
                    }
                    totalRatingsFigure.setText( String.valueOf( (long) documentSnapshot.get( "total_ratings" ) ) );
                   // averageRating.setText( documentSnapshot.get( "average_rating" ).toString() );
                    productDetailsViewpager.setAdapter( new ProductDetailsAdapter( getSupportFragmentManager(), productDetailsTabLayout.getTabCount(), productDescription, productOtherDetails, productSpecificationModelList ) );

                    if (DBQueries.wishList.size() == 0){
                      DBQueries.loadWishList( ProductDetailsActivity.this );
                    }
                    if (DBQueries.wishList.contains( productID )){
                        ALREADY_ADDED_TO_WISHLIST = true;
                    }else{
                        ALREADY_ADDED_TO_WISHLIST = false;
                    }

                } else {
                    String error = task.getException().getMessage();
                    makeText( ProductDetailsActivity.this, error, LENGTH_SHORT ).show();
                }
            }
        } );

//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter( productImages );
//        viewPager.setAdapter( viewPagerAdapter );

        tabLayout.setupWithViewPager( viewPager, true );
        addToWishListBTN.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser == null) {
                    signInDialog.show();
                }else{
                    if (ALREADY_ADDED_TO_WISHLIST) {
                        ALREADY_ADDED_TO_WISHLIST = false;
                        addToWishListBTN.setImageTintList( ColorStateList.valueOf( Color.parseColor( "#9e9e9e" ) ) );
                    } else {
                        Map<String,Object>  addProduct = new HashMap<>();
                        addProduct.put("product_ID_"+String.valueOf( DBQueries.wishList.size()),productID );

                        firebaseFirestore.collection( "USERS" ).document(currentUser.getUid()).collection( "USER_DATA" ).document("MY_WISHLIST")
                                .set( addProduct ).addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Map<String,Object> updateListSize = new HashMap<>();
                                updateListSize.put("list_size",(long) DBQueries.wishList.size()+1);

                                firebaseFirestore.collection( "USERS" ).document(currentUser.getUid()).collection( "USER_DATA" ).document("My_WISHLIST")
                                        .update( updateListSize ).addOnCompleteListener( new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            ALREADY_ADDED_TO_WISHLIST = true;
                                            addToWishListBTN.setImageTintList( getResources().getColorStateList( R.color.btnRed ) );
                                            DBQueries.wishList.add(productID);

                                        }else{
                                            String error = task.getException().getMessage();
                                            makeText( ProductDetailsActivity.this, error, LENGTH_SHORT ).show();
                                        }
                                    }
                                } );

                            }else{
                                String error = task.getException().getMessage();
                                makeText( ProductDetailsActivity.this, error, LENGTH_SHORT ).show();
                                }
                            }
                        } );


                    }
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
                    if(currentUser == null){
                        signInDialog.show();
                    }else {
                        setRating( starPosition );
                    }
                }
            } );
        }
        ///////////////Rating Layout

        buyNowBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser == null){
                    signInDialog.show();
                }else {
                    Intent deliveryIntent = new Intent( ProductDetailsActivity.this, DeliveryActivity.class );
                    startActivity( deliveryIntent );
                }
            }
        } );

        addToCartBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser == null) {
                    signInDialog.show();
                }else{
                    //todo: add to cart
                }
            }
        } );

        // signIn dialog

        signInDialog = new Dialog( ProductDetailsActivity.this );
        signInDialog.setContentView( R.layout.sign_in_dialog );
        signInDialog.setCancelable( true );

        signInDialog.getWindow().setLayout( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );


        Button dialogSignInBtn = signInDialog.findViewById( R.id.sign_in_btn );
        Button dialogSignUpBtn = signInDialog.findViewById( R.id.sign_up_btn );
        final Intent registerIntent = new Intent( ProductDetailsActivity.this, RegisterActivity.class );

        dialogSignInBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn = true;
                SignupFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity( registerIntent );
            }
        } );
        dialogSignUpBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupFragment.disableCloseBtn = true;
                SignInFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity( registerIntent );
            }
        } );
        // signIn dialog


    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            couponRedeemLayout.setVisibility( View.GONE );
        }else
        {
            couponRedeemLayout.setVisibility( View.VISIBLE );

        }
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
            if (currentUser == null){
                signInDialog.show();
            }else {
                Intent cartIntent = new Intent( ProductDetailsActivity.this, MainActivity.class );
                showCart = true;
                startActivity( cartIntent );
            }
        }
        return super.onOptionsItemSelected( item );
    }

}