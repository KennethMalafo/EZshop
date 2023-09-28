package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class WomenSection extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_section);

        viewPager = (ViewPager)findViewById(R.id.womenviewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        drawerLayout = findViewById(R.id.shop_layout);
        navigationView = findViewById(R.id.WomenSec_navigation);
        toolbar = findViewById(R.id.WomenSec_toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    //Navigation uses
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(WomenSection.this, Profile.class));
                break;
            case R.id.home:
                startActivity(new Intent(WomenSection.this, ScreenActivity.class));
                break;
            case R.id.shoppingcart:
                startActivity(new Intent(WomenSection.this, BuyProd.class));
                break;
            case R.id.settings:
                startActivity(new Intent(WomenSection.this, Settings.class));
                break;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Ang Hindi Mag-DL ng app mamalasin: " +getApplicationContext().getPackageName());
                startActivity(Intent.createChooser(intent, "Share with"));
                break;
            case R.id.rate:
                startActivity(new Intent(WomenSection.this, Profile.class));
                break;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WomenSection.this, ScreenActivity.class));
                Toast.makeText(this, "Successfully Logged Out", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    //Avoid Closing the screen when menu is back pressed
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void cart(View view) {
        startActivity(new Intent(WomenSection.this, BuyProd.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void settings(View view) {
        startActivity(new Intent(WomenSection.this, Settings.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenRecommended_viewAll(View view) {
        startActivity(new Intent(WomenSection.this, WomenRecommended.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenPopular_viewAll(View view) {
        startActivity(new Intent(WomenSection.this, WomenPopular.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod1(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_1.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod2(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_2.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod3(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_3.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod4(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_4.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod5(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_5.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod6(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_6.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod7(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_7.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod8(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_8.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod9(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_9.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod10(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_10.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod11(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_11.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod12(View view) {
        startActivity(new Intent(WomenSection.this, women_prod_12.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}