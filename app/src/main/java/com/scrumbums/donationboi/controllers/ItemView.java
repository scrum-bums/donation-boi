package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Item;
import com.scrumbums.donationboi.model.Store;

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
        final Item item = intent.getParcelableExtra("item");


        nameView = findViewById(R.id.item_name);
        nameView.setText(item.getName());

        typeView= findViewById(R.id.item_type);
        typeView.setText(item.getType());

        priceView = findViewById(R.id.item_price);
        priceView.setText(item.getPrice() + "");

        descripView = findViewById(R.id.item_descrip);
        descripView.setText(item.getDescription());

    }

}
