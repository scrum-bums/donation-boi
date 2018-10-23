package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreViewActivity extends AppCompatActivity {

    private TextView storeInfo;
    private List<Store.Item> inventoryArray = new ArrayList<>();
    private ListView inventoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.store_view);

        storeInfo = findViewById(R.id.store_info);
        storeInfo.setText(intent.getParcelableExtra("Store").toString());

        inventoryArray = ((Store) (intent.getParcelableExtra("Store"))).getInventory();

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.item_view, inventoryArray);
        inventoryListView = findViewById(R.id.inventory_scroll);
        inventoryListView.setAdapter(adapter);

        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                Store.Item i = inventoryArray.get(position);
                Intent intent = new Intent(StoreViewActivity.this, ItemView.class);
                intent.putExtra("Name", i.getName());
                intent.putExtra("Type", i.getType());
                intent.putExtra("Price", i.getPrice());
                intent.putExtra("Description", i.getDescription());
                Bundle bund = intent.getExtras();
                startActivity(intent);

            }
        });
    }




}
