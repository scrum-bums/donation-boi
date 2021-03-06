package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.entities.Item;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Activity to search all items or items in a store
 */
public class ItemSearchActivity extends AppCompatActivity {

    private static final int DURATION = 3000;
    private Spinner categorySpinner;
    private EditText searchBar;
    private ListView results;
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
            for (Store s : DatabaseAbstraction.get().getStoresArrayList()) {
                inventoryArray.addAll(s.getInventoryArrayList());
            }
        } else {
            inventoryArray = DatabaseAbstraction.get().getItemsByStoreId(storeId);
        }
        filteredArray = inventoryArray;
        setContentView(R.layout.item_search);

        notFound = Snackbar.make(findViewById(R.id.search_constraint_layout),
                "No Items Found", DURATION);
        Button searchButton = findViewById(R.id.search_button);
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


        ListAdapter adapter1 = new ArrayAdapter<>(this, R.layout.store_view_item, inventoryArray);

        results.setAdapter(adapter1);

        //sets filteredArray to a filtered version based on search terms.
        setListeners(searchButton);

    }

    private void setListeners(Button searchButton) {
        searchButton.setOnClickListener(v -> {
            filteredArray = inventoryArray.stream()
                    .filter(item -> (item.getName()
                            .contains(searchBar.getText())
                            || fuzzyContains(item.getName(), searchBar.getText()))
                            && ("All".equals(categorySpinner.getSelectedItem().toString())
                            || (Categories.stringToCategories(categorySpinner
                            .getSelectedItem().toString()) == item.getCategory())))
                    .collect(Collectors.toList());
            results.setAdapter(new ArrayAdapter<>(this, R.layout.store_view_item, filteredArray));
            if(filteredArray.isEmpty()) {
                notFound.show();
            }
        });

        results.setOnItemClickListener((adapter, v, position, id) -> {
            Item i = filteredArray.get(position);
            Intent intent1 = new Intent(ItemSearchActivity.this, ItemView.class);
            intent1.putExtra("storeId", i.getStoreId());
            intent1.putExtra("itemId", i.getItemId());
            startActivity(intent1);

        });
    }

    private static final double FUZZYTHRESH = 0.7;

    /**
     * Do a fuzzy match search of two Strings.
     * @param actual The value gotten.
     * @param expected The expected value.
     * @return If the given string matches more than a given percent of the expected String.
     */
    private static boolean fuzzyContains(CharSequence actual, CharSequence expected) {
        double matching = 0;
        int expIndex = 0;
        for (int i = 0; i < actual.length() && expIndex < expected.length(); i++) {
            char act = actual.charAt(i);
            char exp = expected.charAt(expIndex);
            if (Character.isAlphabetic(act)) {
                act = Character.toLowerCase(act);
            }
            if (Character.isAlphabetic(exp)) {
                exp = Character.toLowerCase(exp);
            }
            if (act == exp) {
                matching++;
                expIndex++;
            }
        }
        return matching / expected.length() > FUZZYTHRESH;
    }

}
