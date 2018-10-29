package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Item;
import com.scrumbums.donationboi.model.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

public class ItemView extends AppCompatActivity {
    private TextView nameView;
    private TextView typeView;
    private TextView priceView;
    private TextView descripView;
    private TextView categoryView;
    private TextView timestampView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.item_view);
        final int storeId = intent.getIntExtra("storeId",0);
        final int itemId = intent.getIntExtra("itemId", 0);
        Store store = DatabaseAbstraction.getStore(storeId);
        Item item = store.getInventoryItem(itemId);
        //TODO: show error if invalid store/item

        if (item == null) {
            item = new Item("Item not found");
        }

        nameView = findViewById(R.id.item_name);
        nameView.setText(item.getName());

        typeView= findViewById(R.id.item_type);
        typeView.setText(item.getType());

        priceView = findViewById(R.id.item_price);
        priceView.setText(item.getPrice() + "");

        descripView = findViewById(R.id.item_descrip);
        descripView.setText(item.getDescription());

        categoryView = findViewById(R.id.item_category);
        categoryView.setText(item.getCategory().toString());

        timestampView = findViewById(R.id.item_timestamp);
        timestampView.setText(item.getTimestamp().toString());


    }

}
