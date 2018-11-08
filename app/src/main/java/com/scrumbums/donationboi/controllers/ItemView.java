package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.Locale;

public class ItemView extends AppCompatActivity {
    private TextView nameView;
    private TextView typeView;
    private TextView priceView;
    private TextView descripView;
    private TextView shortView;
    private TextView categoryView;
    private TextView timestampView;
    private TextView storeNameView;


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
            item = new Item("Item not found", store);
        }

        nameView = findViewById(R.id.item_name);
        nameView.setText("Name: " + item.getName());

        typeView = findViewById(R.id.item_type);
        typeView.setText("Type: " + item.getType());

        priceView = findViewById(R.id.item_price);
        priceView.setText("Price: " + (String.format(Locale.ENGLISH,"$%.2f",item.getPrice())));

        descripView = findViewById(R.id.item_descrip);
        descripView.setText("Description: " + item.getDescription());

        shortView = findViewById(R.id.item_short);
        String shortDescription = item.getDescription().length() > 31
                ? item.getDescription().substring(0,30) + "..." : item.getDescription();
        shortView.setText("Short Description: " + shortDescription + "\n");

        categoryView = findViewById(R.id.item_category);
        categoryView.setText("Category: " + item.getCategory().toString());

        timestampView = findViewById(R.id.item_timestamp);
        timestampView.setText("Donation Time: " + item.getTimestamp());

        storeNameView = findViewById(R.id.store_name);
        storeNameView.setText(item.getStore().getName());


    }

}
