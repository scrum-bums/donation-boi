package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.scrumbums.donationboi.R;

/**
 * main application activity
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DONATION-BOI/MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If the user is signed in, skip to the main screen
        SharedPreferences prefs
                = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean loggedIn = prefs.getBoolean("loggedIn",false);

        if (loggedIn) {
            startActivity(new Intent(MainActivity.this, StoreListActivity.class));
            finish();
        }

        Button btn = findViewById(R.id.login);


        btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        btn = findViewById(R.id.button_register);

        btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
                RegistrationActivity.class)));
    }





}
