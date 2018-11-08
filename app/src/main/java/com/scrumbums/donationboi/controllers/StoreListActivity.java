package com.scrumbums.donationboi.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

public class StoreListActivity extends AppCompatActivity {
    private Store[] stores;
    private ListView listView;
    private Button logout;
    private Button searchBtn;
    private Button mapBtn;
    private ArrayAdapter adapter;
    final String TAG = "DONATION-BOI/StoreList";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_list);

        listView = findViewById(R.id.mobile_list);
        stores = DatabaseAbstraction.getStoresArrayList();
        adapter = new ArrayAdapter<>(this, R.layout.csv_element_view, stores);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                Store e = stores[position];
                Intent intent = new Intent(StoreListActivity.this, StoreViewActivity.class);
                intent.putExtra("storeId", e.getStoreId());
                Bundle bundle = intent.getExtras();
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            DatabaseAbstraction.logout(getApplicationContext());
            startActivity(new Intent(StoreListActivity.this, MainActivity.class));
            finish();
        });

        searchBtn = findViewById(R.id.search_items);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addItemIntent = new Intent(StoreListActivity.this, ItemSearchActivity.class);
                addItemIntent.putExtra("storeId", 0);
                startActivity(addItemIntent);
            }
        });

        mapBtn = findViewById(R.id.goto_map);
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
