package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.Converters;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ItemSearchActivity extends AppCompatActivity {

    private Button searchButton;
    private Spinner categorySpinner;
    private EditText searchBar;
    private ListView results;
    private ArrayAdapter adapter;
    private List<Item> inventoryArray;
    private List<Item> filteredArray;
    private Snackbar notFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();

        int storeId = intent.getIntExtra("storeId", 0);
        if (storeId == 0) {
            inventoryArray = new ArrayList<>();
            for (Store s : DatabaseAbstraction.getStoresArray()) {
                inventoryArray.addAll(s.getInventoryArrayList());
            }
        } else {
            inventoryArray = DatabaseAbstraction.getStore(storeId).getInventoryArrayList();
        }
        filteredArray = inventoryArray;
        setContentView(R.layout.item_search);

        notFound = Snackbar.make(findViewById(R.id.search_constraint_layout), "No Items Found", 3000);
        searchButton = findViewById(R.id.search_button);
        categorySpinner = findViewById(R.id.category_spinner);
        searchBar = findViewById(R.id.search_bar);
        results = findViewById(R.id.inventory_list_view);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, new String[]{
                "All",
                "Clothing",
                "Hat",
                "Kitchen",
                "Electronics",
                "Household",
                "Other"
        });

        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);


        adapter = new ArrayAdapter<>(this, R.layout.store_view_item, inventoryArray);

        results.setAdapter(adapter);

        //sets filteredArray to a filtered version based on search terms.
        searchButton.setOnClickListener(v -> {
            filteredArray = inventoryArray.stream()
                    .filter(item -> item.getName()
                            .contains(searchBar.getText())
                            && (categorySpinner.getSelectedItem().toString().equals("All")
                            || Converters.stringToCategories(categorySpinner
                            .getSelectedItem().toString()) == item.getCategory()))
                    .collect(Collectors.toList());
            results.setAdapter(new ArrayAdapter<>(this, R.layout.store_view_item, filteredArray));
            if(filteredArray.size() == 0) {
                notFound.show();
            }
        });

        results.setOnItemClickListener((adapter, v, position, id) -> {
            Item i = filteredArray.get(position);
            Intent intent1 = new Intent(ItemSearchActivity.this, ItemView.class);
            intent1.putExtra("storeId", i.getStore().getStoreId());
            intent1.putExtra("itemId", i.getItemId());
            startActivity(intent1);

        });

    }

}
