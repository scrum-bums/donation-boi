package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.ArrayList;


/**
 * This activity serves as the controller for viewing the the information of a store
 * Depending on
 */
public class StoreViewActivity extends AppCompatActivity {

    private ArrayList<Item> inventoryArray = new ArrayList<>();
    private ListView inventoryListView;
    private Store store;
    private int storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.store_view);

        storeId = intent.getIntExtra("storeId", 0);
        store = DatabaseAbstraction.getStore(storeId);

        TextView storeInfo = findViewById(R.id.store_info);
        storeInfo.setText(store.toString());

        inventoryArray = store.getInventoryArrayList();

        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.store_view_item, inventoryArray);
        inventoryListView = findViewById(R.id.inventory_list_view);
        inventoryListView.setAdapter(adapter);


        Button addItemBtn = findViewById(R.id.add_item_button);
        Button searchBtn = findViewById(R.id.search_button);

        SharedPreferences prefs
                = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean canAddItems = prefs.getBoolean("canAddItems",false);
        if (!canAddItems) {
            addItemBtn.setVisibility(View.GONE);
        }

        makeListeners(addItemBtn, searchBtn);
    }

    private void makeListeners(Button addItemBtn, Button searchBtn) {
        addItemBtn.setOnClickListener(v -> {
            Intent addItemIntent = new Intent(StoreViewActivity.this, AddItemForm.class);
            addItemIntent.putExtra("storeId", storeId);
            startActivity(addItemIntent);
        });

        searchBtn.setOnClickListener(v -> {
            Intent addItemIntent = new Intent(StoreViewActivity.this, ItemSearchActivity.class);
            addItemIntent.putExtra("storeId", storeId);
            startActivity(addItemIntent);
        });

        inventoryListView.setOnItemClickListener((adapter1, v, position, id) -> {
            Item i = inventoryArray.get(position);
            Intent intent1 = new Intent(StoreViewActivity.this, ItemView.class);
            intent1.putExtra("storeId", store.getStoreId());
            intent1.putExtra("itemId", i.getItemId());
            startActivity(intent1);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        inventoryArray = store.getInventoryArrayList();
        ArrayAdapter<Item> newAdapter = new ArrayAdapter<>(this,
                R.layout.store_view_item, inventoryArray);

        inventoryListView.setAdapter(newAdapter);
        inventoryListView.refreshDrawableState();

    }




}
