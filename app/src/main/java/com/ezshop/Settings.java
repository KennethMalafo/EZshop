package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.InetSocketAddress;
import java.util.Objects;

public class Settings extends AppCompatActivity {

    private TextView UserName, Email , Country, Cp_Num, DoB, Gender;
    private FirebaseAuth firebaseAuth;
    private String userName, email , country, cp_num, dob, gender;
    private SwipeRefreshLayout swipeContainer;
    private ImageView Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        swipeUptoRefresh();

        UserName = findViewById(R.id.txtUserName);
        Email = findViewById(R.id.txtEmail);
        Country = findViewById(R.id.CountryTxt);
        Cp_Num = findViewById(R.id.CpNum);
        DoB = findViewById(R.id.txtDob);
        Gender = findViewById(R.id.Gender);

        //On Click Listener for Uploading Profile Pic
        Profile = findViewById(R.id.edit_profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, UploadProfilePic.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            Toast.makeText(Settings.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
        } else {
            showUserProfile(firebaseUser);
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        //Extracting User Reference from Database for "User_Info"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("UserInfo");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null) {
                    email = firebaseUser.getEmail();
                    userName = firebaseUser.getDisplayName();
                    country = readUserDetails.country;
                    cp_num = readUserDetails.cp_num;
                    dob = readUserDetails.dob;
                    gender = readUserDetails.gender;
                    //Display the Details of the Current User
                    UserName.setText(userName);
                    Email.setText(email);
                    Country.setText(country);
                    Cp_Num.setText(cp_num);
                    DoB.setText(dob);
                    Gender.setText(gender);
                    //Set User Profile Pic
                    Uri uri = firebaseUser.getPhotoUrl();
                    Picasso.get().load(uri).into(Profile);
                } else {
                    Toast.makeText(Settings.this, "No Uploaded Image!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Settings.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void update_acc(View view){
        startActivity(new Intent(Settings.this, UpdateProfile.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void log_out(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Settings.this);
        alertDialog.setTitle("Log Out");
        alertDialog.setMessage("Are you sure you want to Log Out your Account?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Settings.this, ScreenActivity.class));
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
    //Refresh
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
}