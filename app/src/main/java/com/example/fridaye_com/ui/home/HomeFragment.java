package com.example.fridaye_com.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridaye_com.CategoryAdapter;
import com.example.fridaye_com.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Object SliderAdapter;

    public HomeFragment() {

    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerView;
    private HomePageAdapter adapter;
    private List<HomeViewModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_home, container, false );
        categoryRecyclerView = view.findViewById( R.id.category_recyclerview );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        layoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        categoryRecyclerView.setLayoutManager( layoutManager );

        categoryModelList = new ArrayList<HomeViewModel>();
        categoryAdapter = new CategoryAdapter( categoryModelList );
        categoryRecyclerView.setAdapter( categoryAdapter );


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection( "CATEGORIES" ).orderBy( "index" ).get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add( new HomeViewModel( documentSnapshot.get( "icon" ).toString(), documentSnapshot.get( "categoryName" ).toString() ) );
                            }
                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText( getContext(), error, Toast.LENGTH_SHORT ).show();
                        }

                    }
                } );

        ////////////////// Banner Slider

        List<SliderModel> sliderModelArrayList = new ArrayList<SliderModel>();


        ////////////////// Banner Slider


        ///////////Horizontal Product Layout

//        List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );
//        horizontalProductModelList.add( new HorizontalProductModel( R.drawable.phone, "Redmi 5A", "SA 625 processor", "Rs.6999" ) );

        ///////////Horizontal Product Layout


        ////////////////////////////////////////////////


        homePageRecyclerView = view.findViewById( R.id.home_page_recyclerview );
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager( getContext() );
        testingLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        homePageRecyclerView.setLayoutManager( testingLayoutManager );
        List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter( homePageModelList );
        homePageRecyclerView.setAdapter( adapter );

        firebaseFirestore.collection( "CATEGORIES" ).document( "HOME" )
                .collection( "TOP_DEALS" ).orderBy( "index" ).get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                if ((long) documentSnapshot.get( "view_type" ) == 0) {
                                    List<SliderModel> sliderModelItems = new ArrayList<>();
                                    long no_of_banners = (long) documentSnapshot.get("no_of_banners");
                                    for (long x = 1; x < no_of_banners + 1; x++) {
                                        sliderModelItems.add( new SliderModel( documentSnapshot.get( "banner_" + x ).toString(),
                                                documentSnapshot.get("banner_"+x+"_background" ).toString() ) );
                                    }
                                    homePageModelList.add( new HomePageModel( 0, sliderModelItems ) );

                                } else if ((long) documentSnapshot.get( "view_type" ) == 1) {
                                    homePageModelList.add( new HomePageModel( 1, documentSnapshot.get( "strip_ad_banner" ).toString(),
                                            documentSnapshot.get( "background" ).toString() ) );

                                } else if ((long) documentSnapshot.get( "view_type" ) ==  2) {
                                    List <HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        horizontalProductModelList.add( new HorizontalProductModel(
                                                documentSnapshot.get( "product_ID_" + x ).toString()
                                                ,documentSnapshot.get( "product_image_"+x ).toString()
                                                ,documentSnapshot.get( "product_title_"+x ).toString()
                                                ,documentSnapshot.get( "product_subtitle_"+x ).toString()
                                                ,documentSnapshot.get( "product_price_"+x ).toString()));
                                    }


                                    homePageModelList.add( new HomePageModel( 2,documentSnapshot.get( "layout_title" ).toString()
                                            ,documentSnapshot.get( "layout_background" ).toString(), horizontalProductModelList ) );


                                } else if ((long) documentSnapshot.get( "view_type" ) == 3) {

                                }
                                adapter.notifyDataSetChanged();
                            }

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText( getContext(), error, Toast.LENGTH_SHORT ).show();
                        }

                    }
                } );


        /////////////////////////////////////////
        return view;

    }



}