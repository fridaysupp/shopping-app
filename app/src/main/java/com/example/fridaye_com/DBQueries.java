package com.example.fridaye_com;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridaye_com.ui.home.HomeFragment;
import com.example.fridaye_com.ui.home.HomePageAdapter;
import com.example.fridaye_com.ui.home.HomePageModel;
import com.example.fridaye_com.ui.home.HomeViewModel;
import com.example.fridaye_com.ui.home.HorizontalProductModel;
import com.example.fridaye_com.ui.home.SliderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DBQueries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<HomeViewModel> categoryModelList = new ArrayList<HomeViewModel>();


    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames = new ArrayList<>();
    public  static  List<String> wishList = new ArrayList<>();


    public static void loadCategories(RecyclerView categoryRecyclerView, Context context) {


        firebaseFirestore.collection( "CATEGORIES" ).orderBy( "index" ).get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add( new HomeViewModel( documentSnapshot.get( "icon" ).toString(), documentSnapshot.get( "categoryName" ).toString() ) );
                            }
                            CategoryAdapter categoryAdapter = new CategoryAdapter( categoryModelList );
                            categoryRecyclerView.setAdapter( categoryAdapter );
                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText( context, error, Toast.LENGTH_SHORT ).show();
                        }

                    }
                } );
    }

    public static void loadFragmentData(RecyclerView homepageRecyclerView, Context context, final int index, @NotNull String categoryName) {
        firebaseFirestore.collection( "CATEGORIES" ).document( categoryName.toUpperCase() )
                .collection( "TOP_DEALS" ).orderBy( "index" ).get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                if ((long) documentSnapshot.get( "view_type" ) == 0) {
                                    List<SliderModel> sliderModelItems = new ArrayList<>();
                                    long no_of_banners = (long) documentSnapshot.get( "no_of_banners" );
                                    for (long x = 1; x < no_of_banners + 1; x++) {
                                        sliderModelItems.add( new SliderModel( documentSnapshot.get( "banner_" + x ).toString(),
                                                documentSnapshot.get( "banner_" + x + "_background" ).toString() ) );
                                    }
                                    lists.get( index ).add( new HomePageModel( 0, sliderModelItems ) );

                                } else if ((long) documentSnapshot.get( "view_type" ) == 1) {
                                    lists.get( index ).add( new HomePageModel( 1, documentSnapshot.get( "strip_ad_banner" ).toString(),
                                            documentSnapshot.get( "background" ).toString() ) );

                                } else if ((long) documentSnapshot.get( "view_type" ) == 2) {

                                    List<WishListModel> viewAllProductList = new ArrayList<>();
                                    List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get( "no_of_products" );
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        horizontalProductModelList.add( new HorizontalProductModel(
                                                documentSnapshot.get( "product_ID_" + x ).toString()
                                                , documentSnapshot.get( "product_image_" + x ).toString()
                                                , documentSnapshot.get( "product_title_" + x ).toString()
                                                , documentSnapshot.get( "product_subtitle_" + x ).toString()
                                                , documentSnapshot.get( "product_price_" + x ).toString() ) );

                                        viewAllProductList.add( new WishListModel( documentSnapshot.get( "product_image_" + x ).toString(),
                                                documentSnapshot.get( "product_full_title_" + x ).toString()
                                                , (long) documentSnapshot.get( "free_coupons_" + x )
                                                , documentSnapshot.get( "avg_rating_" + x ).toString()
                                                , (long) (documentSnapshot.get( "total_ratings_" + x ))
                                                , documentSnapshot.get( "product_price_" + x ).toString()
                                                , documentSnapshot.get( "cutted_price_" + x ).toString()
                                                , (boolean) documentSnapshot.get( "COD_" + x )
                                        ) );
                                    }


                                    lists.get( index ).add( new HomePageModel( 2, documentSnapshot.get( "layout_title" ).toString()
                                            , documentSnapshot.get( "layout_background" ).toString(), horizontalProductModelList, viewAllProductList ) );


                                } else if ((long) documentSnapshot.get( "view_type" ) == 3) {

                                    List<HorizontalProductModel> gridLayoutProductModelList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get( "no_of_products" );
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        gridLayoutProductModelList.add( new HorizontalProductModel(
                                                documentSnapshot.get( "product_ID_" + x ).toString()
                                                , documentSnapshot.get( "product_image_" + x ).toString()
                                                , documentSnapshot.get( "product_title_" + x ).toString()
                                                , documentSnapshot.get( "product_subtitle_" + x ).toString()
                                                , documentSnapshot.get( "product_price_" + x ).toString() ) );
                                    }


                                    lists.get( index ).add( new HomePageModel( 3, documentSnapshot.get( "layout_title" ).toString()
                                            , documentSnapshot.get( "layout_background" ).toString(), gridLayoutProductModelList ) );

                                }
                                HomePageAdapter homePageAdapter = new HomePageAdapter( lists.get( index ) );
                                homepageRecyclerView.setAdapter( homePageAdapter );
                                homePageAdapter.notifyDataSetChanged();
                                HomeFragment.swipeRefreshLayout.setRefreshing( false );
                            }

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText( context, error, Toast.LENGTH_SHORT ).show();
                        }

                    }
                } );
    }

    public  static  void loadWishList(Context context){
        firebaseFirestore.collection( "USERS").document(FirebaseAuth.getInstance().getUid() )
                .collection( "USER_DATA" ).document("MY_WISHLIST")
               .get().addOnCompleteListener( new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()){
              try {
                  long list_size = (long) task.getResult().get("list_size");
                  for(long z = 0; z < list_size ; z++){
                      wishList.add(task.getResult().get("product_ID_"+z).toString());
                  }
              }catch (Exception e){
                  Toast.makeText( context.getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
              }



            }else{
                String error= task.getException().getMessage();
              Toast.makeText(context,error,Toast.LENGTH_SHORT ).show();
           }
            }
       } );
   }
}
