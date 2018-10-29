package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Location;
import com.scrumbums.donationboi.model.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button btn;
    final String TAG = "DONATION-BOI/MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        btn = findViewById(R.id.button_register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        readLocationData();
    }

    private void readLocationData() {
        InputStream inputStream = getResources().openRawResource(R.raw.location_data);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line = "";
        try {
            bufferedReader.readLine(); // Skip over the first line of the CSV that's the column names
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                String name = (tokens[1]);
                float latitude = Float.parseFloat(tokens[2]);
                float longitude = Float.parseFloat(tokens[3]);
                String streetAddress = tokens[4];
                String city = tokens[5];
                String state = tokens[6];
                int zipCode = Integer.parseInt(tokens[7]);
                String locationType = tokens[8];
                String phoneNumber  = tokens[9];
                String website = tokens[10];
                Store store = new Store(name, new Location(streetAddress, state, city, zipCode,
                                            latitude, longitude), phoneNumber, website, locationType);
                DatabaseAbstraction.addStore(store.getStoreId(), store);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
