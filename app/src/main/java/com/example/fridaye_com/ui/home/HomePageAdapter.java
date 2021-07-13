package com.example.fridaye_com.ui.home;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fridaye_com.GrideProductLayoutAdapter;
import com.example.fridaye_com.ProductDetailsActivity;
import com.example.fridaye_com.R;
import com.example.fridaye_com.ViewAllActivity;
import com.example.fridaye_com.WishListModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private int lastPosition = -1;
    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {


        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View BannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout, parent, false);
                return new BannerSlideViewHolder(BannerSliderView);

           case HomePageModel.STRIP_AD_BANNER:
                View StripAdview = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);
                return new StripAdBannerViewHolder(StripAdview);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductViewHolder(horizontalProductView);

            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get( position ).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelArrayListList = homePageModelList.get( position ).getSliderModelArrayList();
                ((BannerSlideViewHolder) holder).setBannerSliderViewPager( sliderModelArrayListList );
                break;
            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModelList.get( position ).getResource();
                String color = homePageModelList.get( position ).getBackgroundColor();
                ((StripAdBannerViewHolder) holder).setStripAd( resource, color );
                break;

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String layoutColor = homePageModelList.get( position ).getBackgroundColor();
                String horizontalLayoutTitle = homePageModelList.get( position ).getTitle();
                List<WishListModel> viewAllProductList = homePageModelList.get( position ).getWishListModelList();
                List<HorizontalProductModel> horizontalProductModelList = homePageModelList.get( position ).getHorizontalProductModelList();
                ((HorizontalProductViewHolder) holder).setHorizontalProductLayout( horizontalProductModelList, horizontalLayoutTitle, layoutColor, viewAllProductList );
                break;

            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridLayoutColor = homePageModelList.get( position ).getBackgroundColor();
                String gridLayoutTitle = homePageModelList.get( position ).getTitle();
                List<HorizontalProductModel> gridProductModelList = homePageModelList.get( position ).getHorizontalProductModelList();
                ((GridProductViewHolder) holder).setGridProductLayout( gridProductModelList, gridLayoutTitle, gridLayoutColor );
            default:
                return;
        }
        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation( holder.itemView.getContext(), R.anim.fade_in );
            holder.itemView.setAnimation( animation );
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }
    ////////////////////Banner slider

    public class BannerSlideViewHolder extends RecyclerView.ViewHolder {

        private int currentPage ;
        private ViewPager2 bannerSliderViewPager;
        private Timer timer;
        private Runnable sliderRunnable ;
        private Handler sliderHandler = new Handler();
        //final private long DELAY_TIME = 3000;
       // final private long PERIOD_TIME = 3000;
        private  List<SliderModel> arrangedList;

        public BannerSlideViewHolder(@NonNull  View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }

        ////////////////////Banner slider
        private void setBannerSliderViewPager(final List<SliderModel> sliderModelItems) {
            currentPage = 2;
            if(timer != null){
                timer.cancel();
            }
            arrangedList= new ArrayList<>();
            for (int x=0; x<sliderModelItems.size();x++){
                arrangedList.add( x,sliderModelItems.get( x ) );
            }
            arrangedList.add(0,sliderModelItems.get( sliderModelItems.size() - 2 ) );
            arrangedList.add(1,sliderModelItems.get( sliderModelItems.size() - 1 ) );
            arrangedList.add( sliderModelItems.get( 0 ) );
            arrangedList.add( sliderModelItems.get( 1 ) );


            SliderAdapter sliderAdapter = new SliderAdapter(arrangedList,bannerSliderViewPager );
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setClipChildren(false);
            bannerSliderViewPager.setOffscreenPageLimit(3);
             bannerSliderViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(40));
            compositePageTransformer.addTransformer( new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull @NotNull View page, float position) {
                    float r= 1-Math.abs( position );
                    page.setScaleY( 0.85f + r * 0.15f );

                }
            } );
            bannerSliderViewPager.setPageTransformer(compositePageTransformer);
            bannerSliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    sliderHandler.removeCallbacks( sliderRunnable );
                    sliderHandler.postDelayed( sliderRunnable,6000 );
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                    if (state == bannerSliderViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            });
            startBannerSlideShow(arrangedList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopBannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrangedList);
                    }
                    return false;
                }
            });
        }
        private void startBannerSlideShow(final List<SliderModel> sliderModelItems) {
            sliderRunnable = () -> {
                if (currentPage >= sliderModelItems.size()) {
                    currentPage = 2;
                }
                bannerSliderViewPager.setCurrentItem(bannerSliderViewPager.getCurrentItem() + 1);
            };

        }
        private void stopBannerSlideShow() {
            timer.cancel();
        }
        private void pageLooper(final List<SliderModel> sliderModelItems) {
            if (currentPage == sliderModelItems.size() - 1) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
           else  if (currentPage == 1) {
                currentPage = sliderModelItems.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }
        //////////////////Banner Slider
    }
//////////////Strip Ad
    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
            stripAdContainer = itemView.findViewById(R.id.strip_ad_container);

        }

        private void setStripAd(String resource, String color) {
            //todo:placeholder change
            Glide.with( itemView.getContext() ).load(resource ).apply( new RequestOptions().placeholder( R.mipmap.home_icon ) ).into( stripAdImage );
            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }
    //////////////Strip Ad

    /////Horizontal Product View
    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizontalLayoutViewAllBtn;
        private RecyclerView horizontalRecycleViewLayout;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalLayoutTitle = itemView.findViewById(R.id.tv_horizontal_scroll_layout_title);
            horizontalLayoutViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_layout_view_all_button);
            horizontalRecycleViewLayout = itemView.findViewById(R.id.horizontal_product_layout_recyclerview);
            horizontalRecycleViewLayout.setRecycledViewPool( recycledViewPool );
            container = itemView.findViewById( R.id.container );
        }

        private void setHorizontalProductLayout(List<HorizontalProductModel> horizontalProductModelList, String title, String color, List<WishListModel> viewAllProductList) {
            container.setBackgroundTintList( ColorStateList.valueOf( Color.parseColor( color ) ) );
            horizontalLayoutTitle.setText(title);
            if(horizontalProductModelList.size() > 8){

                horizontalLayoutViewAllBtn.setVisibility(View.VISIBLE);
                horizontalLayoutViewAllBtn.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewAllActivity.wishListModelsList = viewAllProductList;
                        Intent viewAllIntent= new Intent(itemView.getContext(), ViewAllActivity.class );
                        viewAllIntent.putExtra("layout_code",0);
                        viewAllIntent.putExtra( "title",title );
                        itemView.getContext().startActivity( viewAllIntent );
                    }
                } );
            }else
            {
                horizontalLayoutViewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter =
                    new HorizontalProductScrollAdapter(horizontalProductModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecycleViewLayout.setLayoutManager(linearLayoutManager);
            horizontalRecycleViewLayout.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();

        }
    }
    /////Horizontal Product View

    /////Grid Product View
    public class GridProductViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout gridContainer;
        private TextView gridLayoutTitle;
        private Button gridLayoutViewAllBtn;
        private GridLayout gridProductLayout;


        public GridProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
             gridLayoutTitle=itemView.findViewById(R.id.grid_product_layout_title);
             gridLayoutViewAllBtn=itemView.findViewById(R.id.grid_product_layout_view_all_btn);
             gridProductLayout=itemView.findViewById( R.id.grid_layout );
             gridContainer = itemView.findViewById( R.id.grid_container );
        }
        private void setGridProductLayout(final List<HorizontalProductModel> horizontalProductModelList,String title,String colorString){
            gridContainer.setBackgroundTintList( ColorStateList.valueOf( Color.parseColor( colorString ) ) );
            gridLayoutTitle.setText(title);

            for (int i=0;i< 4; i++){
                ImageView productImage = gridProductLayout.getChildAt( i ).findViewById( R.id.h_s_productimage );
                TextView productTitle= gridProductLayout.getChildAt( i ).findViewById( R.id.h_s_product_title );
                TextView productDescription= gridProductLayout.getChildAt( i ).findViewById( R.id.h_s_product_title );
                TextView productPrice= gridProductLayout.getChildAt( i ).findViewById( R.id.h_s_product_description );
                    //todo:change placeholder icon
               Glide.with(itemView.getContext()).load(horizontalProductModelList.get(i).getProductImage()).apply(new RequestOptions().placeholder( R.mipmap.home_icon)).into( productImage);
                productTitle.setText( horizontalProductModelList.get( i ).getProduct_title() );
                productDescription.setText( horizontalProductModelList.get( i ).getProduct_description() );
                productPrice.setText("Rs."+ horizontalProductModelList.get( i ).getProduct_price()+"/-" );
                gridProductLayout.getChildAt( i ).setBackgroundColor( Color.parseColor( "#ffffff" ) );

                if(!title.equals( "" )) {
                   int finalI = i;
                    gridProductLayout.getChildAt( i ).setOnClickListener( v -> {
                        Intent productDetailsIntent = new Intent( itemView.getContext(), ProductDetailsActivity.class );
                        productDetailsIntent.putExtra( "PRODUCT_ID",horizontalProductModelList.get( finalI ).getProductID() );
                        itemView.getContext().startActivity( productDetailsIntent );
                    } );
                }
            }

            if(!title.equals( "" )) {
                gridLayoutViewAllBtn.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewAllActivity.horizontalProductModelList = horizontalProductModelList;
                        Intent viewAllIntent = new Intent( itemView.getContext(), ViewAllActivity.class );
                        viewAllIntent.putExtra( "layout_code", 1 );
                        viewAllIntent.putExtra( "title", title );
                        itemView.getContext().startActivity( viewAllIntent );
                    }
                } );
            }


        }
    }
    /////Grid Product View

}

