package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChildrenRecommended extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_recommended);
    }
    public void childrenprod7(View view) {
        startActivity(new Intent(ChildrenRecommended.this, children_prod_7.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod8(View view) {
        startActivity(new Intent(ChildrenRecommended.this, children_prod_8.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod9(View view) {
        startActivity(new Intent(ChildrenRecommended.this, children_prod_9.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod10(View view) {
        startActivity(new Intent(ChildrenRecommended.this, children_prod_10.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod11(View view) {
        startActivity(new Intent(ChildrenRecommended.this, children_prod_11.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void childrenprod12(View view) {
        startActivity(new Intent(ChildrenRecommended.this, children_prod_12.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}