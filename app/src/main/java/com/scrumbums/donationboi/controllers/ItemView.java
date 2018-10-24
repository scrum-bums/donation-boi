package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;

public class ItemView extends AppCompatActivity {
    private TextView nameView;
    private TextView typeView;
    private TextView priceView;
    private TextView descripView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.item_view);

        nameView.findViewById(R.id.item_name);
        nameView.setText(intent.getStringExtra("Name"));

        typeView.findViewById(R.id.item_type);
        typeView.setText(intent.getStringExtra("Type"));

        priceView.findViewById(R.id.item_price);
        priceView.setText(intent.getStringExtra("Price"));

        descripView.findViewById(R.id.item_descrip);
        descripView.setText(intent.getStringExtra("Description"));

    }

}
