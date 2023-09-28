package com.ezshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class appliance_prod_3 extends AppCompatActivity {

    ImageView likes, share;
    TextView ProdName, Price;
    BottomNavigationView AddToCart, BuyNow;

    FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_prod3);

        /*reference = FirebaseDatabase.getInstance().getReference("AddToCart");
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();**/

        likes = (ImageView) findViewById(R.id.like);
        likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likes.setActivated(!likes.isActivated());
            }
        });

        share = findViewById(R.id.shareApp);
        share.setOnClickListener(v ->{
            ShareApp(appliance_prod_3.this);
        });

        ExpandableTextView textView=findViewById(R.id.expand_text_view);
        textView.setText(getString(R.string.dummyText));

        ProdName = findViewById(R.id.ProdName);
        Price = findViewById(R.id.Price);

        /*AddToCart = findViewById(R.id.AddToCart);
        BuyNow = findViewById(R.id.BuyNow);

        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });**/
    }

    /*private void addToCart() {
        String IDCart = reference.push().getKey();
        String saveCurrentTime, saveCurrentDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh: mm: ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        final HashMap<String,Object>cartmap = new HashMap<>();

        cartmap.put("Product Name", ProdName.getText().toString());
        cartmap.put("Product Price", Price.getText().toString());
        cartmap.put("Current Date", saveCurrentDate);
        cartmap.put("Current Time", saveCurrentTime);

       reference.child(IDCart).setValue(cartmap);
    }**/

    private void ShareApp(Context context){
        final String apppackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Look at this cool product: tapos link dito ng product namin" + apppackageName);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "Share with"));
    }
    public void Reviews(View view) {
        startActivity(new Intent(appliance_prod_3.this, ReviewsActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}