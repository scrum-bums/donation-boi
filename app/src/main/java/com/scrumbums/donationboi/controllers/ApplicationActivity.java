package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.scrumbums.donationboi.R;

/**
 * Application activity for realm
 */
public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Button btn = findViewById(R.id.logout);

        btn.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(ApplicationActivity.this, MainActivity.class));
        });
    }
}
