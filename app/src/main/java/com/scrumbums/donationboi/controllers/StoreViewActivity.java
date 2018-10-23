package com.scrumbums.donationboi.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreViewActivity extends Activity {

    private TextView name;
    private TextView location;
    private TextView phoneNumber;
    private TextView website;
    private List<Store.Item> inventoryView = new ArrayList<>();
    private ListView listView;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_view);
    }

    ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout. , inventoryView);
    listView = findViewById(R.id.);
    listView.setAdapter(adapter);
    listView.setOnItemClickListenter(new AdapterView.OnItemClick);


}
