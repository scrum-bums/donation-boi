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
import com.scrumbums.donationboi.model.Item;
import com.scrumbums.donationboi.model.Store;

import java.util.ArrayList;
import java.util.List;


/**
 * This activity serves as the controller for viewing the the information of a store
 * Depending on
 */
public class StoreViewActivity extends AppCompatActivity {

    private TextView storeInfo;
    private List<Item> inventoryArray = new ArrayList<>();
    private ListView inventoryListView;
    private Button addItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.store_view);

        storeInfo = findViewById(R.id.store_info);
        storeInfo.setText(intent.getParcelableExtra("Store").toString());

        final Store store = (Store) (intent.getParcelableExtra("Store"));

        inventoryArray = store.getInventory();


        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.store_view_item, inventoryArray);
        inventoryListView = findViewById(R.id.inventory_list_view);
        inventoryListView.setAdapter(adapter);


        addItemBtn = findViewById(R.id.add_item_button);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addItemIntent = new Intent(StoreViewActivity.this, AddItemForm.class);
                addItemIntent.putExtra("store",store);
                startActivity(addItemIntent);
            }
        });
        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                Item i = inventoryArray.get(position);
                Intent intent = new Intent(StoreViewActivity.this, ItemView.class);
                intent.putExtra("item", i);
                startActivity(intent);

            }
        });
    }




}
