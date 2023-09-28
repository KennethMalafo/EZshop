package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenRecommended extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_recommended);
    }
    public void prod7(View view) {
        startActivity(new Intent(MenRecommended.this, prod_7.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod8(View view) {
        startActivity(new Intent(MenRecommended.this, prod_8.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod9(View view) {
        startActivity(new Intent(MenRecommended.this, prod_9.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod10(View view) {
        startActivity(new Intent(MenRecommended.this, prod_10.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod11(View view) {
        startActivity(new Intent(MenRecommended.this, prod_11.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void prod12(View view) {
        startActivity(new Intent(MenRecommended.this, prod_12.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}