package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

/**
 * view to show info on an item
 */
public class ItemView extends AppCompatActivity {


    private static final int LEN = 31;
    private static final int LEN1 = 31;

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
        nameView.setText(getString(R.string.item_name_filled, item.getName()));

        TextView typeView = findViewById(R.id.item_type);
        typeView.setText(getString(R.string.item_type_filled, item.getType()));

        TextView priceView = findViewById(R.id.item_price);
        priceView.setText(getString(R.string.item_price_filled, item.getPrice()));

        TextView descriptionView = findViewById(R.id.item_descrip);
        descriptionView.setText(getString(R.string.item_desc_filled, item.getDescription()));

        TextView shortView = findViewById(R.id.item_short);
        String shortDescription = (item.getDescription().length() > LEN)
                ? (item.getDescription().substring(0, LEN1) + "...") : item.getDescription();
        shortView.setText(getString(R.string.item_desc_short_filled, shortDescription));

        TextView categoryView = findViewById(R.id.item_category);
        categoryView.setText(getString(R.string.item_category_filled, item.getCategory()
                .toString()));

        TextView timestampView = findViewById(R.id.item_timestamp);
        timestampView.setText(getString(R.string.item_donation_time_filled, item.getTimestamp()));

        TextView storeNameView = findViewById(R.id.store_name);
        storeNameView.setText(item.getStore().getName());


    }

}
