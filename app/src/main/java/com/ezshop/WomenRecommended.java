package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WomenRecommended extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_recommended);
    }
    public void womenprod7(View view) {
        startActivity(new Intent(WomenRecommended.this, women_prod_7.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod8(View view) {
        startActivity(new Intent(WomenRecommended.this, women_prod_8.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod9(View view) {
        startActivity(new Intent(WomenRecommended.this, women_prod_9.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod10(View view) {
        startActivity(new Intent(WomenRecommended.this, women_prod_10.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod11(View view) {
        startActivity(new Intent(WomenRecommended.this, women_prod_11.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod12(View view) {
        startActivity(new Intent(WomenRecommended.this, women_prod_12.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}