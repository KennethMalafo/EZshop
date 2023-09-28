package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.hbb20.CountryCodePicker;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +  ".{8,}" );  //must have 8 characters
    private DatePickerDialog picker;
    EditText UserName, Email, Password, UserNum, DoB, Gender;
    boolean passwordVisible;
    private FirebaseAuth auth;
    private CountryCodePicker codePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        codePicker=findViewById(R.id.country_code);

        UserNum = findViewById(R.id.UserNum);
        DoB = findViewById(R.id.DoB);
        UserName = findViewById(R.id.UserName);
        Gender = findViewById(R.id.gender);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);

        //Setting Up Date Picker on EditText
        DoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);

                //date picker dialog
                picker = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Date format: Month/Day/Year
                        DoB.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        //visibility of password icon
        Password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    int selection = Password.getSelectionEnd();
                    if (motionEvent.getRawX() >= Password.getRight() - Password.getCompoundDrawables()[Right].getBounds().width()) {
                        selection = Password.getSelectionEnd();
                        if (passwordVisible) {
                            Password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
                            //hidepass
                            Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }
                    } else {
                        Password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);
                        //showpass
                        Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordVisible = true;
                    }
                }
                return false;
            }
        });
    }

    //sign up btn
    public void signup(View view) {

        String gender = Gender.getText().toString();
        String country = codePicker.getSelectedCountryEnglishName();
        String usernum = UserNum.getText().toString();
        String dob = DoB.getText().toString();
        String username = UserName.getText().toString();
        String email = Email.getText().toString();
        String pass = Password.getText().toString();


        //Validate Mobile Number
        /*String mobileRegEx = "^(09|\\+639)\\d{9}$"; //validates cp num in the ph
        Matcher mobileMatcher;
        Pattern mobilePattern = Pattern.compile(mobileRegEx);
        mobileMatcher = mobilePattern.matcher(usernum);**/

        if (TextUtils.isEmpty(usernum) /*|| !mobileMatcher.find()**/) {
            Toast.makeText(this, "Oops, Enter Your Mobile Number!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Oops, Enter Your Date of Birth!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(gender) || !gender.equals("M") && !gender.equals("F")
                && !gender.equals("m")&& !gender.equals("f")&& !gender.equals("MALE")
                && !gender.equals("FEMALE")&& !gender.equals("Male")&& !gender.equals("Female")
                && !gender.equals("male")&& !gender.equals("female")) {
            Toast.makeText(this, "Oops, Enter Your Gender!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Oops, Enter UserName!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Oops, Enter  a Valid Email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass) || !PASSWORD_PATTERN.matcher(pass).matches()) {
            Toast.makeText(this, "Password too weak/short", Toast.LENGTH_LONG).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            //Update User's Display Name
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                            firebaseUser.updateProfile(profileChangeRequest);

                            //Dito mailalagay ung data na kakailanganin para sa database
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(country, usernum, dob, gender);

                            //Extracting User Reference from Database for "User Info"
                            DatabaseReference referenceInfo = FirebaseDatabase.getInstance().getReference("UserInfo");
                            referenceInfo.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        //email verification link
                                        firebaseUser.sendEmailVerification();
                                        Toast.makeText(RegisterActivity.this, "Successfully Registered. You can now Verify your Email", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, Log_InActivity.class));
                                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration Failed!"+task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
        public void login (View view){
            startActivity(new Intent(RegisterActivity.this, Log_InActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }

    @Override
    public void onBackPressed() {

        //Exit Dialogue
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
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
}