package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BabyPopular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_popular);
    }
    public void babyprod1(View view) {
        startActivity(new Intent(BabyPopular.this, baby_prod_1.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void babyprod2(View view) {
        startActivity(new Intent(BabyPopular.this, baby_prod_2.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void babyprod3(View view) {
        startActivity(new Intent(BabyPopular.this, baby_prod_3.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void babyprod4(View view) {
        startActivity(new Intent(BabyPopular.this, baby_prod_4.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void babyprod5(View view) {
        startActivity(new Intent(BabyPopular.this, baby_prod_5.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void babyprod6(View view) {
        startActivity(new Intent(BabyPopular.this, baby_prod_6.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}