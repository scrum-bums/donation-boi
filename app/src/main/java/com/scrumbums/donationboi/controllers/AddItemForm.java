package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Categories;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.Converters;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

public class AddItemForm extends AppCompatActivity {

    private EditText nameBox;
    private TextView nameLabel;
    private EditText typeBox;
    private TextView typeLabel;
    private EditText priceBox;
    private TextView priceLable;
    private EditText descripBox;
    private TextView descripLabel;
    private Button mAddButton;
    private Button mCancelItemButton;
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
        descripBox = findViewById(R.id.descrip_box);
        typeBox = findViewById(R.id.type_box);

        categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, new String[] {
                "Clothing",
                "Hat",
                "Kitchen",
                "Electronics",
                "Household",
                "Other"
        });
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        mAddButton = findViewById(R.id.add_button);
        mAddButton.setText(R.string.add_button_text);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = nameBox.getText().toString();
                String itemType = typeBox.getText().toString();
                double itemPrice = Double.valueOf(priceBox.getText().toString());
                String itemDescription = descripBox.getText().toString();
                Categories cat = Converters.stringToCategories(categorySpinner
                        .getSelectedItem().toString());

                store.addToInventory(itemName, itemDescription, itemPrice, itemType, cat);
                finish();
                
            }
        });


        mCancelItemButton = findViewById(R.id.cancel_button);

        mCancelItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
