package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private TextView name, email;
    private FirebaseAuth firebaseAuth;
    private String userName, Email;
    private ImageButton Profile;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.shop_layout);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        swipeUptoRefresh();

        name = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);

        //On Click Listener for Uploading Profile Pic
        Profile = findViewById(R.id.profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, UploadProfilePic.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            Toast.makeText(Profile.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
        } else {
            showUserProfile(firebaseUser);
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        //Extracting User Reference from Database for "UserInfo"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("UserInfo");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null) {
                    userName = firebaseUser.getDisplayName();
                    Email = firebaseUser.getEmail();
                    //Display UserName and Email of the Current User
                    name.setText(userName);
                    email.setText(Email);
                    //Set User Profile Pic
                    Uri uri = firebaseUser.getPhotoUrl();
                    Picasso.get().load(uri).into(Profile);
                } else {
                    Toast.makeText(Profile.this, "No Uploaded Image!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cart(View view) {
        startActivity(new Intent(Profile.this, BuyProd.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void settings(View view) {
        startActivity(new Intent(Profile.this, Settings.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void userLikes(View view) {
        startActivity(new Intent(Profile.this, UserLikes.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void log_out(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Profile.this);
        alertDialog.setTitle("Log Out");
        alertDialog.setMessage("Are you sure you want to Log Out your Account?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, ScreenActivity.class));
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

    private void swipeUptoRefresh() {
        //Look Up for a container
        swipeContainer = findViewById(R.id.SwipeContainer);

        //Set up refresh Listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Code to refresh goes here so make sure that you call swipeContainer.setRefreshing(false) once refreshed
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 2 seconds)
                        swipeContainer.setRefreshing(false);
                    }
                }, 2000); // Delay in millis
            }
        });
        //Configure refresh colors
        swipeContainer.setColorSchemeResources(android.R.color.black, android.R.color.black,
                android.R.color.black, android.R.color.black);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(Profile.this, Profile.class));
                break;
            case R.id.home:
                startActivity(new Intent(Profile.this, ShopActivity.class));
                break;
            case R.id.shoppingcart:
                startActivity(new Intent(Profile.this, BuyProd.class));
                break;
            case R.id.settings:
                startActivity(new Intent(Profile.this, Settings.class));
                break;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://www.ezshop.com/shop/EZshop" /*+getApplicationContext().getPackageName()**/);
                startActivity(Intent.createChooser(intent, "Share with"));
                break;
            case R.id.rate:
                startActivity(new Intent(Profile.this, UpdateProfile.class));
                break;
            case R.id.log_out:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Profile.this);
                alertDialog.setTitle("Log Out");
                alertDialog.setMessage("Are you sure you want to Log Out your Account?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Profile.this, ScreenActivity.class));
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
}
