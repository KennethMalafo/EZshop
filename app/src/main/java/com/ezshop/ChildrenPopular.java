package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChildrenPopular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_popular);
    }
    public void childrenprod1(View view) {
        startActivity(new Intent(ChildrenPopular.this, children_prod_1.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod2(View view) {
        startActivity(new Intent(ChildrenPopular.this, children_prod_2.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod3(View view) {
        startActivity(new Intent(ChildrenPopular.this, children_prod_3.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod4(View view) {
        startActivity(new Intent(ChildrenPopular.this, children_prod_4.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod5(View view) {
        startActivity(new Intent(ChildrenPopular.this, children_prod_5.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod6(View view) {
        startActivity(new Intent(ChildrenPopular.this, children_prod_6.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}