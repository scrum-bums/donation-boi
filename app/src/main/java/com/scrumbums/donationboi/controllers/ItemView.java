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

/**
 * view to show info on an item
 */
public class ItemView extends AppCompatActivity {


    public static final int LEN = 31;
    public static final int LEN1 = 31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.item_view);
        final int storeId = intent.getIntExtra("storeId",0);
        final int itemId = intent.getIntExtra("itemId", 0);
        Store store = DatabaseAbstraction.getStore(storeId);
        Item item = store.getInventoryItem(itemId);

        if (item == null) {
            item = new Item("Item not found", store);
        }

        TextView nameView = findViewById(R.id.item_name);
        nameView.setText("Name: " + item.getName());

        TextView typeView = findViewById(R.id.item_type);
        typeView.setText("Type: " + item.getType());

        TextView priceView = findViewById(R.id.item_price);
        priceView.setText("Price: " + (String.format(Locale.ENGLISH,"$%.2f",item.getPrice())));

        TextView descriptionView = findViewById(R.id.item_descrip);
        descriptionView.setText("Description: " + item.getDescription());

        TextView shortView = findViewById(R.id.item_short);
        String shortDescription = (item.getDescription().length() > LEN)
                ? (item.getDescription().substring(0, LEN1) + "...") : item.getDescription();
        shortView.setText("Short Description: " + shortDescription + "\n");

        TextView categoryView = findViewById(R.id.item_category);
        categoryView.setText("Category: " + item.getCategory().toString());

        TextView timestampView = findViewById(R.id.item_timestamp);
        timestampView.setText("Donation Time: " + item.getTimestamp());

        TextView storeNameView = findViewById(R.id.store_name);
        storeNameView.setText(item.getStore().getName());


    }

}
