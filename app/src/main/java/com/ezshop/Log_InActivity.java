package com.ezshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Log_InActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(".{8,}"  );  //password must have 8 characters


    EditText Email, Password;
    boolean passwordVisible;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(Log_InActivity.this, ShopActivity.class));
        }

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);

        //Hiding and showing of password
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
    public void login(View view) {
        String email = Email.getText().toString();
        String pass = Password.getText().toString();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Oops, Enter  a Valid Email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass) || !PASSWORD_PATTERN.matcher(pass).matches()) {
            Toast.makeText(this, "Invalid Password!", Toast.LENGTH_LONG).show();
            return;
        }
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(Log_InActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Log_InActivity.this, "Logged In Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Log_InActivity.this, ShopActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        }else{
                            Toast.makeText(Log_InActivity.this, "Log In Failure"+task.getException(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
    public void signup(View view) {
        startActivity(new Intent(Log_InActivity.this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
    @Override
    public void onBackPressed() {

        //Exit Dialogue
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Log_InActivity.this);
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