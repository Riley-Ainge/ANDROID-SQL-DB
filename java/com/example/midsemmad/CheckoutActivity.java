package com.example.midsemmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckoutActivity extends AppCompatActivity {

    Button back;
    Button pay;
    Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        FragmentManager fm = getSupportFragmentManager();
        CheckoutFragment frag = new CheckoutFragment();
        fm.beginTransaction().replace(R.id.CheckoutFrag,frag).commit();
        back = findViewById(R.id.Back);
        pay = findViewById(R.id.LogBtn);
        checkout = findViewById(R.id.CheckoutBtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, RestaurantsActivity.class);
                startActivity(intent);
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                LogFragment frag = new LogFragment();
                fm.beginTransaction().replace(R.id.CheckoutFrag,frag).commit();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                CheckoutFragment frag = new CheckoutFragment();
                fm.beginTransaction().replace(R.id.CheckoutFrag,frag).commit();
            }
        });

    }
}