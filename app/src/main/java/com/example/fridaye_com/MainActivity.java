package com.example.fridaye_com;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fridaye_com.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import static com.example.fridaye_com.RegisterActivity.setSignUpFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FrameLayout frameLayout;
    private ImageView noInternetConnection;
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int MY_ORDERS_FRAGMENT = 2;
    private static final int MY_WISHLIST_FRAGMENT = 3;
    private static final int REWARDS_FRAGMENT = 4;
    private static final int MY_ACCOUNT_FRAGMENT = 5;
    public static boolean showCart = false;
public static DrawerLayout drawer;

    private ImageView actionBarLogo;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Dialog signInDialog;
    private FirebaseUser currentUser;
    // private Window window;
    private int currentFragment = -1;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        toolbar = (Toolbar) findViewById( R.id.toolbar );
        actionBarLogo = findViewById( R.id.action_bar_logo );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
        //window= getWindow();
        // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );

         drawer = findViewById( R.id.drawer_layout );


        navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.getMenu().getItem( 0 ).setChecked( true );

        frameLayout = findViewById( R.id.main_frame_layout );


        if (showCart) {

            drawer.setDrawerLockMode( 1 );
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            gotoFragment( "My Cart", new MyCartFragment(), -2 );
        } else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close );
            drawer.addDrawerListener( toggle );
            toggle.syncState();

            setFragment( new HomeFragment(), HOME_FRAGMENT );
        }

        signInDialog = new Dialog( MainActivity.this );
        signInDialog.setContentView( R.layout.sign_in_dialog );
        signInDialog.setCancelable( true );

        signInDialog.getWindow().setLayout( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );


        Button dialogSignInBtn = signInDialog.findViewById( R.id.sign_in_btn );
        Button dialogSignUpBtn = signInDialog.findViewById( R.id.sign_up_btn );
        final Intent registerIntent = new Intent( MainActivity.this, RegisterActivity.class );

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            navigationView.getMenu().getItem( navigationView.getMenu().size() - 1 ).setEnabled( false );
        } else {
            navigationView.getMenu().getItem( navigationView.getMenu().size() - 1 ).setEnabled( true );
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawerLayout.isDrawerOpen( GravityCompat.START )) {
            drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            if (currentFragment == HOME_FRAGMENT) {
                currentFragment = -1;
                super.onBackPressed();

            } else {
                if (showCart) {
                    showCart = false;
                    finish();
                } else {
                    actionBarLogo.setVisibility( View.VISIBLE );
                    invalidateOptionsMenu();
                    setFragment( new HomeFragment(), HOME_FRAGMENT );
                    navigationView.getMenu().getItem( 0 ).setChecked( true );
                }

            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled( false );
            getMenuInflater().inflate( R.menu.main, menu );
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //todo:search
            return true;
        } else if (id == R.id.main_notification_icon) {
            //todo:notification
            return true;
        } else if (id == R.id.main_cart_icon) {
            if (currentUser == null) {
                signInDialog.show();
            } else {
                gotoFragment( "My Cart", new MyCartFragment(), CART_FRAGMENT );
            }
            return true;
        } else if (id == android.R.id.home) {
            if (showCart) {
                showCart = false;
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected( item );
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        actionBarLogo.setVisibility( View.GONE );
        getSupportActionBar().setDisplayShowTitleEnabled( true );
        getSupportActionBar().setTitle( title );

        invalidateOptionsMenu();
        setFragment( fragment, fragmentNo );
        if (fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem( 3 ).setChecked( true );
        }

    }

    @SuppressWarnings("StatmentWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (currentUser != null) {
            int id = item.getItemId();
            if (id == R.id.nav_my_home) {
                actionBarLogo.setVisibility( View.VISIBLE );
                invalidateOptionsMenu();
                setFragment( new HomeFragment(), HOME_FRAGMENT );

            } else if (id == R.id.nav_my_orders) {
                gotoFragment( "My Orders", new MyOrdersFragment(), MY_ORDERS_FRAGMENT );
            } else if (id == R.id.nav_my_rewards) {
                gotoFragment( "My Rewards", new MyRewardsFragment(), REWARDS_FRAGMENT );
            } else if (id == R.id.nav_my_cart) {
                gotoFragment( "My Cart", new MyCartFragment(), CART_FRAGMENT );
            } else if (id == R.id.nav_my_wishlist) {
                gotoFragment( "My wishlist", new MyWishListFragment(), MY_WISHLIST_FRAGMENT );

            } else if (id == R.id.nav_my_account) {
                gotoFragment( "My Account", new MyAccountFragment(), MY_ACCOUNT_FRAGMENT );

            } else if (id == R.id.nav_sign_out) {
                FirebaseAuth.getInstance().signOut();
                Intent registerIntent= new Intent(MainActivity.this,RegisterActivity.class);
                startActivity( registerIntent );
                finish();
            }
            drawer.closeDrawer( GravityCompat.START );
            return true;

        } else {
            drawer.closeDrawer( GravityCompat.START );
            signInDialog.show();
                return false;
        }

    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {
            if (fragmentNo == REWARDS_FRAGMENT) {
                // window.setStatusBarColor( Color.parseColor("#5B04B1") );
                toolbar.setBackgroundColor( Color.parseColor( "#5B04B1" ) );
            } else {
                //window.setStatusBarColor( getResources().getColor( R.color.light_Dark ) );
                toolbar.setBackgroundColor( getResources().getColor( R.color.light_Dark ) );
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations( R.anim.fade_in, R.anim.fade_out );
            fragmentTransaction.replace( frameLayout.getId(), fragment );
            fragmentTransaction.commit();
        }

    }
}