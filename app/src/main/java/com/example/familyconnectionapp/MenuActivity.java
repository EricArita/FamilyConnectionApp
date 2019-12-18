package com.example.familyconnectionapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.familyconnectionapp.ui.joinCircle.JoinCircleFragment;
import com.example.familyconnectionapp.ui.joinedCircle.JoinedCircleFragment;
import com.example.familyconnectionapp.ui.myCircle.MyCircleFragment;
import com.example.familyconnectionapp.ui.myLocation.LocationSettingsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.FrameLayout;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        LocationSettingsFragment locationSettingsFragment = new LocationSettingsFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, locationSettingsFragment, "nav_host_fragment").commit();


        FloatingActionButton fab = findViewById(R.id.fabMessage);
        fab.setOnClickListener(view -> {
//            ChatFragment chatFragment = new ChatFragment();
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, chatFragment, "nav_host_fragment").commit();
              Intent intent = new Intent(this, ChatActivity.class);
              startActivity(intent);
        });

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_send)
                .setDrawerLayout(drawerLayout)
                .build();

//        NavController navController = Navigation.findNavController(this, R.id.toolbar);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(
                menuItem -> {

                    Fragment fragment = null;

                    FrameLayout fl = (FrameLayout) findViewById(R.id.nav_host_fragment);
                    fl.removeAllViews();

                    menuItem.setChecked(true);
                    actionBar.setTitle(menuItem.getTitle());

                    int i = menuItem.getItemId();

                    if(i==R.id.nav_myLocation) {
                        fragment = new LocationSettingsFragment();
                    }
                    else if(i==R.id.nav_myCircle)
                    {
                        fragment = new MyCircleFragment();
                    }
                    else if(i==R.id.nav_joinCircle)
                    {
                        fragment = new JoinCircleFragment();
                    }
                    else if(i==R.id.nav_joinedCircle)
                    {
                        fragment = new JoinedCircleFragment();
                    }
                    else if(i==R.id.nav_signOut)
                    {
                        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    drawerLayout.closeDrawers();

                    // Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    // Insert the fragment by replacing any existing fragment
                    //FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit();

                    return true;
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
