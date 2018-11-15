package com.scrumbums.donationboi.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

/**
 * A list of stores wow cool
 */
public class StoreListActivity extends AppCompatActivity {
    private Store[] stores;
    private ArrayAdapter adapter;
    private static final String TAG = "DONATION-BOI/StoreList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list);

        ListView listView = findViewById(R.id.mobile_list);
        stores = DatabaseAbstraction.getStoresArrayList();
        adapter = new ArrayAdapter<>(this, R.layout.csv_element_view, stores);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapter, v, position, id) -> {
            Store e = stores[position];
            Intent intent = new Intent(StoreListActivity.this, StoreViewActivity.class);
            intent.putExtra("storeId", e.getStoreId());
            startActivity(intent);
        });

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            DatabaseAbstraction.logout(getApplicationContext());
            startActivity(new Intent(StoreListActivity.this, MainActivity.class));
            finish();
        });

        Button searchBtn = findViewById(R.id.search_items);
        searchBtn.setOnClickListener(v -> {
            Intent addItemIntent = new Intent(StoreListActivity.this, ItemSearchActivity.class);
            addItemIntent.putExtra("storeId", 0);
            startActivity(addItemIntent);
        });

        Button mapBtn = findViewById(R.id.goto_map);
        mapBtn.setOnClickListener((v) -> {
            Intent mapIntent = new Intent(StoreListActivity.this, MapViewActivity.class);
            startActivity(mapIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
