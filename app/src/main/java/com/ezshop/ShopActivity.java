package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ShopActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        drawerLayout = findViewById(R.id.shop_layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        uri = getIntent().getData();
        if(uri!=null){
            List<String> parameters = uri.getPathSegments();
            String params = parameters.get(parameters.size() - 1);

        }
    }

    //Navigation uses
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(ShopActivity.this, Profile.class));
                break;
            case R.id.home:
                startActivity(new Intent(ShopActivity.this, ShopActivity.class));
                break;
            case R.id.shoppingcart:
                startActivity(new Intent(ShopActivity.this, BuyProd.class));
                break;
            case R.id.settings:
                startActivity(new Intent(ShopActivity.this, Settings.class));
                break;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://www.ezshop.com/shop/EZshop" /*+getApplicationContext().getPackageName()**/);
                startActivity(Intent.createChooser(intent, "Share with"));
                break;
            case R.id.rate:
                startActivity(new Intent(ShopActivity.this, UpdateProfile.class));
                break;
            case R.id.log_out:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShopActivity.this);
                alertDialog.setTitle("Log Out");
                alertDialog.setMessage("Are you sure you want to Log Out your Account?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ShopActivity.this, ScreenActivity.class));
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
        }
        return true;
    }

    //Avoid Closing the screen when menu is back pressed
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        //Exit Dialogue
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShopActivity.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to Exit EZshop?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    //Categories ID
    public void MenSec(View view) {
        startActivity(new Intent(ShopActivity.this, MenSection.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void WomenSec(View view) {
        startActivity(new Intent(ShopActivity.this, WomenSection.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void BabySec(View view) {
        startActivity(new Intent(ShopActivity.this, BabySection.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void AppliancesSec(View view) {
        startActivity(new Intent(ShopActivity.this, AppliancesSection.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void ChildSec(View view) {
        startActivity(new Intent(ShopActivity.this, ChildrenSection.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}