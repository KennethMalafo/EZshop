package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WomenPopular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_popular);
    }
    public void womenprod1(View view) {
        startActivity(new Intent(WomenPopular.this, women_prod_1.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod2(View view) {
        startActivity(new Intent(WomenPopular.this, women_prod_2.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod3(View view) {
        startActivity(new Intent(WomenPopular.this, women_prod_3.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod4(View view) {
        startActivity(new Intent(WomenPopular.this, women_prod_4.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod5(View view) {
        startActivity(new Intent(WomenPopular.this, women_prod_5.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void womenprod6(View view) {
        startActivity(new Intent(WomenPopular.this, women_prod_6.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}