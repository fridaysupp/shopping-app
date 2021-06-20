package com.example.fridaye_com;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.fridaye_com.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fridaye_com.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FrameLayout frameLayout;
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private NavigationView navigationView;

    private static int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.getMenu().getItem( 0 ).setChecked( true );

        frameLayout = findViewById( R.id.main_frame_layout );
        setFragment( new HomeFragment(), HOME_FRAGMENT );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getMenuInflater().inflate( R.menu.main, menu );
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //todo:search
            return true;
        } else if (id == R.id.main_notification_icon) {
            //todo:notification
            return true;
        } else if (id == R.id.main_cart_icon) {
            myCart();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }

    private void myCart() {
        invalidateOptionsMenu();
        setFragment( new MyCartFragment(), CART_FRAGMENT );
        navigationView.getMenu().getItem(3).setChecked( true );

    }
    @SuppressWarnings("StatmentWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_my_home) {
            setFragment( new HomeFragment(), HOME_FRAGMENT );
        } else if (id == R.id.nav_my_orders) {

        } else if (id == R.id.nav_my_rewards) {

        } else if (id == R.id.nav_my_cart) {
            myCart();
        } else if (id == R.id.nav_my_wishlist) {

        } else if (id == R.id.nav_my_account) {

        } else if (id == R.id.nav_sign_out) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        currentFragment = fragmentNo;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( frameLayout.getId(), fragment );
    }
}