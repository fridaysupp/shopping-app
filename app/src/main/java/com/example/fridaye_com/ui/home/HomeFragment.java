package com.example.fridaye_com.ui.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.fridaye_com.CategoryAdapter;
import com.example.fridaye_com.MainActivity;
import com.example.fridaye_com.R;
import com.example.fridaye_com.WishListModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.fridaye_com.DBQueries.categoryModelList;
import static com.example.fridaye_com.DBQueries.lists;
import static com.example.fridaye_com.DBQueries.loadCategories;
import static com.example.fridaye_com.DBQueries.loadFragmentData;
import static com.example.fridaye_com.DBQueries.loadedCategoriesNames;


public class HomeFragment extends Fragment {

    private Object SliderAdapter;

    public HomeFragment() {

    }

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    public static SwipeRefreshLayout swipeRefreshLayout;
    private List<HomeViewModel> categoryModelFakeList = new ArrayList<>();
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerView;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();
    private HomePageAdapter adapter;
    private ImageView noInternetConnection;
    private Button RetryBtn;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_home, container, false );
        swipeRefreshLayout = view.findViewById( R.id.refresh_layout );
        noInternetConnection = view.findViewById( R.id.no_internet_connection );
        RetryBtn = view.findViewById( R.id.retry_btn );

        connectivityManager = (ConnectivityManager) getActivity().getSystemService( Context.CONNECTIVITY_SERVICE );
        networkInfo = connectivityManager.getActiveNetworkInfo();


        categoryRecyclerView = view.findViewById( R.id.category_recyclerview );
        homePageRecyclerView = view.findViewById( R.id.home_page_recyclerview );

        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        layoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        categoryRecyclerView.setLayoutManager( layoutManager );


        LinearLayoutManager testingLayoutManager = new LinearLayoutManager( getContext() );
        testingLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        homePageRecyclerView.setLayoutManager( testingLayoutManager );


//category fake list
        categoryModelFakeList.add( new HomeViewModel( "null", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
        categoryModelFakeList.add( new HomeViewModel( "", "" ) );
//category fake list

//homepage fake list
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add( new SliderModel( "null", "#dfdfdf" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#dfdfdf" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#dfdfdf" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#dfdfdf" ) );
        sliderModelFakeList.add( new SliderModel( "null", "#dfdfdf" ) );

        List<HorizontalProductModel> horizontalProductModelFakeList = new ArrayList<>();
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        horizontalProductModelFakeList.add( new HorizontalProductModel( "", "", "", "", "" ) );
        homePageModelFakeList.add( new HomePageModel( 0, sliderModelFakeList ) );
        homePageModelFakeList.add( new HomePageModel( 1, "", "#dfdfdf" ) );
        homePageModelFakeList.add( new HomePageModel( 2, "", "#dfdfdf", horizontalProductModelFakeList, new ArrayList<WishListModel>() ) );
        homePageModelFakeList.add( new HomePageModel( 3, "", "#dfdfdf", horizontalProductModelFakeList ) );
//homepage fake list

        categoryAdapter = new CategoryAdapter( categoryModelFakeList );
        adapter = new HomePageAdapter( homePageModelFakeList);



        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode( DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternetConnection.setVisibility( View.GONE );
            RetryBtn.setVisibility( View.GONE );
            categoryRecyclerView.setVisibility( View.VISIBLE );
            homePageRecyclerView.setVisibility( View.VISIBLE );

            if (categoryModelList.size() == 0) {
                loadCategories( categoryRecyclerView, getContext() );
            } else {
                categoryAdapter = new CategoryAdapter(categoryModelList  );
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerView.setAdapter( categoryAdapter );
            if (lists.size() == 0) {
                loadedCategoriesNames.add( "HOME" );
                lists.add( new ArrayList<HomePageModel>() );
                loadFragmentData( homePageRecyclerView, getContext(), 0, "home" );
            } else {
                adapter = new HomePageAdapter( lists.get( 0 ) );
                adapter.notifyDataSetChanged();
            }
            homePageRecyclerView.setAdapter( adapter );

        } else {
            MainActivity.drawer.setDrawerLockMode( DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            categoryRecyclerView.setVisibility( View.GONE );
            homePageRecyclerView.setVisibility( View.GONE );
            Glide.with( this ).load( R.drawable.phone ).into( noInternetConnection );
            noInternetConnection.setVisibility( View.VISIBLE );
            RetryBtn.setVisibility( View.VISIBLE );
        }
        /////////////////////////////////////////

        ///refresh layout//

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing( true );
            refreshPage();
        });
        ///refresh layout//
        RetryBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshPage();
            }
        } );

        return view;

    }

    private  void refreshPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
        categoryModelList.clear();
        lists.clear();
        loadedCategoriesNames.clear();


        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer.setDrawerLockMode( DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternetConnection.setVisibility( View.GONE );
            RetryBtn.setVisibility( View.GONE );
            categoryRecyclerView.setVisibility( View.VISIBLE );
            homePageRecyclerView.setVisibility( View.VISIBLE );

            categoryAdapter = new CategoryAdapter( categoryModelFakeList );
            categoryRecyclerView.setAdapter( categoryAdapter );
            adapter= new HomePageAdapter( homePageModelFakeList );
            homePageRecyclerView.setAdapter( adapter );

            loadCategories( categoryRecyclerView, getContext() );

            loadedCategoriesNames.add( "HOME" );
            lists.add( new ArrayList<HomePageModel>() );
            loadFragmentData( homePageRecyclerView, getContext(), 0, "home" );

        } else {
            MainActivity.drawer.setDrawerLockMode( DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            Toast.makeText( getContext(),"No internet connection! ",Toast.LENGTH_SHORT ).show();
            categoryRecyclerView.setVisibility( View.GONE );
            homePageRecyclerView.setVisibility( View.GONE );
            Glide.with( getContext() ).load( R.drawable.phone ).into( noInternetConnection );
            noInternetConnection.setVisibility( View.VISIBLE );
            RetryBtn.setVisibility( View.VISIBLE );
            swipeRefreshLayout.setRefreshing( false );

        }
    }


}