package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AppliancesPopular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliances_popular);
    }
    public void appliancesprod1(View view) {
        startActivity(new Intent(AppliancesPopular.this, baby_prod_1.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void appliancesprod2(View view) {
        startActivity(new Intent(AppliancesPopular.this, baby_prod_2.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void appliancesprod3(View view) {
        startActivity(new Intent(AppliancesPopular.this, baby_prod_3.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void appliancesprod4(View view) {
        startActivity(new Intent(AppliancesPopular.this, baby_prod_4.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void appliancesprod5(View view) {
        startActivity(new Intent(AppliancesPopular.this, baby_prod_5.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void appliancesprod6(View view) {
        startActivity(new Intent(AppliancesPopular.this, baby_prod_6.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}