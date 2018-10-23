package com.scrumbums.donationboi.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import com.scrumbums.donationboi.R;
import android.widget.TextView;


public class ListElementPage extends Activity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.csv_element_view);


        tv = findViewById(R.id.csv_text);
        tv.setText(String.format("%s%n%s%n%s%n%s%n%s%n%s%n",intent.getStringExtra("Name"), intent.getStringExtra("Address"), intent.getStringExtra("Phone Number"),
                intent.getStringExtra("Longitude"), intent.getStringExtra("Latitude"), intent.getStringExtra("Type")));


    }

}
