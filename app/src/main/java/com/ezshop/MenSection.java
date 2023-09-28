package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MenSection extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_section);

        viewPager = (ViewPager) findViewById(R.id.menviewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        drawerLayout = findViewById(R.id.shop_layout);
        navigationView = findViewById(R.id.MenSec_navigation);
        toolbar = findViewById(R.id.MenSec_toolbar);

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
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(MenSection.this, Profile.class));
                break;
            case R.id.home:
                startActivity(new Intent(MenSection.this, ScreenActivity.class));
                break;
            case R.id.shoppingcart:
                startActivity(new Intent(MenSection.this, BuyProd.class));
                break;
            case R.id.settings:
                startActivity(new Intent(MenSection.this, Settings.class));
                break;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Ang Hindi Mag-DL ng app ay isang hotdog: " + getApplicationContext().getPackageName());
                startActivity(Intent.createChooser(intent, "Share with"));
                break;
            case R.id.rate:
                startActivity(new Intent(MenSection.this, Profile.class));
                break;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenSection.this, ScreenActivity.class));
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
        startActivity(new Intent(MenSection.this, BuyProd.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void settings(View view) {
        startActivity(new Intent(MenSection.this, Settings.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void recommended_viewAll(View view) {
        startActivity(new Intent(MenSection.this, MenRecommended.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void popular_viewAll(View view) {
        startActivity(new Intent(MenSection.this, MenPopular.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod1(View view) {
        startActivity(new Intent(MenSection.this, prod_1.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod2(View view) {
        startActivity(new Intent(MenSection.this, prod_2.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod3(View view) {
        startActivity(new Intent(MenSection.this, prod_3.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod4(View view) {
        startActivity(new Intent(MenSection.this, prod_4.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod5(View view) {
        startActivity(new Intent(MenSection.this, prod_5.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod6(View view) {
        startActivity(new Intent(MenSection.this, prod_6.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod7(View view) {
        startActivity(new Intent(MenSection.this, prod_7.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod8(View view) {
        startActivity(new Intent(MenSection.this, prod_8.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod9(View view) {
        startActivity(new Intent(MenSection.this, prod_9.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod10(View view) {
        startActivity(new Intent(MenSection.this, prod_10.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod11(View view) {
        startActivity(new Intent(MenSection.this, prod_11.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod12(View view) {
        startActivity(new Intent(MenSection.this, prod_12.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}