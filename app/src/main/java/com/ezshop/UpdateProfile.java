package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateProfile extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"
                    + ".{8,}");  //must have 8 characters
    private EditText UpdateUserName, UpdateDoB, UpdateCpNum, UpdateGender;
    private String userName, country, cp_num, dob, gender;
    private SwipeRefreshLayout swipeContainer;
    private FirebaseAuth authProfile;
    private CountryCodePicker countryPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        swipeUptoRefresh();

        countryPicker = findViewById(R.id.update_country);

        UpdateUserName = findViewById(R.id.update_username);
        UpdateCpNum = findViewById(R.id.update_cp_num);
        UpdateDoB = findViewById(R.id.update_DoB);
        UpdateGender = findViewById(R.id.update_gender);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        //show Profile
        if (firebaseUser != null) {
            showProfile(firebaseUser);
        }

        //upload profile pic
        Button buttonUploadProfilePic = findViewById(R.id.upload);
        buttonUploadProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProfile.this, UploadProfilePic.class);
                startActivity(intent);
            }
        });
        //Setting Up Date Picker on EditText
        UpdateDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extracting saved data into different variables by creating an array delimited by "/"
                String textSADoB[] = dob.split("/");

                int month = Integer.parseInt(textSADoB[0]);
                int day = Integer.parseInt(textSADoB[1]) - 1;//to take care of day index that is starting from 0
                int year = Integer.parseInt(textSADoB[2]);

                DatePickerDialog picker;

                //date picker dialog
                picker = new DatePickerDialog(UpdateProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Date format: Month/Day/Year
                        UpdateDoB.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        //Update profile
        Button buttonUpdateProfile = findViewById(R.id.update);
        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile(firebaseUser);
            }
        });
    }

    private void updateProfile(FirebaseUser firebaseUser) {

        //Validate Mobile Number
        /*String mobileRegEx = "^(09|\\+639)\\d{9}$"; //validates cp num in the ph
        Matcher mobileMatcher;
        Pattern mobilePattern = Pattern.compile(mobileRegEx);
        mobileMatcher = mobilePattern.matcher(cp_num);**/

        if (TextUtils.isEmpty(cp_num) /*|| !mobileMatcher.find()**/) {
            Toast.makeText(this, "Oops, Enter Your Mobile Number!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Oops, Enter Your Date of Birth!", Toast.LENGTH_LONG).show();
            return;
        }
        if ((TextUtils.isEmpty(gender) || !gender.equals("M") && !gender.equals("F")
                && !gender.equals("m") && !gender.equals("f") && !gender.equals("MALE")
                && !gender.equals("FEMALE") && !gender.equals("Male") && !gender.equals("Female")
                && !gender.equals("male") && !gender.equals("female"))) {
            Toast.makeText(this, "Oops, Select Your Gender!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Oops, Enter UserName!", Toast.LENGTH_LONG).show();
            return;
        } else {
            //Obtain the data entered by user
            userName = UpdateUserName.getText().toString();
            country = countryPicker.getSelectedCountryEnglishName();
            cp_num = UpdateCpNum.getText().toString();
            dob = UpdateDoB.getText().toString();
            gender = UpdateGender.getText().toString();

            //Enter User Data into the Firebase Realtime Database, Setting Up Dependencies
            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(country, cp_num, dob, gender);

            //Extract User reference from Database for "User_Info"
            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("UserInfo");

            String userId = firebaseUser.getUid();
            referenceProfile.child(userId).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //Setting new User Name
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();
                        firebaseUser.updateProfile(profileUpdates);

                        Toast.makeText(UpdateProfile.this, "Changes are Successful!", Toast.LENGTH_SHORT).show();

                        //Stop User from returning to UpdateProfileActivity on pressing back button and close activty
                        Intent intent = new Intent(UpdateProfile.this, Profile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private void showProfile(FirebaseUser firebaseUser) {
        String userIDofRegistered = firebaseUser.getUid();

        //Extracting User Reference from Database for "UserInfo"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("UserInfo");

        referenceProfile.child(userIDofRegistered).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null) {
                    userName = firebaseUser.getDisplayName();
                    cp_num = readUserDetails.cp_num;
                    dob = readUserDetails.dob;
                    gender = readUserDetails.gender;

                    UpdateUserName.setText(userName);
                    UpdateCpNum.setText(cp_num);
                    UpdateDoB.setText(dob);
                    UpdateGender.setText(gender);
                } else {
                    Toast.makeText(UpdateProfile.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateProfile.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
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