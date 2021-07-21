package com.atik.nblik.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.atik.nblik.R;

public class Thankyou extends AppCompatActivity {
    private final int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Thankyou.this,MainActivity.class));
                        finish();
                    }
                }, SPLASH_TIME);
    }
}