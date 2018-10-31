package com.scrumbums.donationboi.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.ArrayList;

public class ListViewCustom extends AppCompatActivity {
    private ArrayList<Store> stores;
    private ListView listView;
    private ArrayAdapter adapter;
    final String TAG = "LIST_VIEW_CUSTOM";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv_view);

        listView = findViewById(R.id.mobile_list);
        stores = DatabaseAbstraction.getStoresArrayList();

        //The adapter will change depending on the type of user that is logged in
        adapter = new ArrayAdapter<>(this, R.layout.csv_element_view, stores);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                Store e = stores.get(position);
                Intent intent = new Intent(ListViewCustom.this, StoreViewActivity.class);
                intent.putExtra("storeId", e.getStoreId());
                Bundle bundle = intent.getExtras();
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
