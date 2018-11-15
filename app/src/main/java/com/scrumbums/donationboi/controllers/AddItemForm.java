package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.Converters;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

/**
 * a form to add items to a store
 */
public class AddItemForm extends AppCompatActivity {

    private EditText nameBox;
    private EditText typeBox;
    private EditText priceBox;
    private EditText descriptionBox;
    private Spinner categorySpinner;
    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        setContentView(R.layout.add_item_form);
        store = DatabaseAbstraction.getStore(intent.getIntExtra("storeId", 0));

        nameBox = findViewById(R.id.name_box);
        priceBox = findViewById(R.id.price_box);
        descriptionBox = findViewById(R.id.descrip_box);
        typeBox = findViewById(R.id.type_box);

        categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, new String[] {
                "Clothing",
                "Hat",
                "Kitchen",
                "Electronics",
                "Household",
                "Other"
        });
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        Button mAddButton = findViewById(R.id.add_button);
        mAddButton.setText(R.string.add_button_text);

        mAddButton.setOnClickListener(v -> {
            Editable name = nameBox.getText();
            String itemName = name.toString();
            Editable type = typeBox.getText();
            String itemType = type.toString();
            Editable price = priceBox.getText();
            double itemPrice = Double.valueOf(price.toString());
            Editable description = descriptionBox.getText();
            String itemDescription = description.toString();
            Object selectedItem = categorySpinner.getSelectedItem();
            Categories cat = Converters.stringToCategories(selectedItem.toString());

            store.addToInventory(itemName, itemDescription, itemPrice, itemType, cat);
            finish();
        });


        Button mCancelItemButton = findViewById(R.id.cancel_button);

        mCancelItemButton.setOnClickListener(v -> finish());


    }

}
