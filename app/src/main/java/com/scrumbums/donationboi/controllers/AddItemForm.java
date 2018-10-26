package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Store;

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
    private TextView categoryBox;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        setContentView(R.layout.add_item_form);
        store = intent.getParcelableExtra("store");

        nameBox = findViewById(R.id.name_box);
        priceBox = findViewById(R.id.price_box);
        descripBox = findViewById(R.id.descrip_box);
        typeBox = findViewById(R.id.type_box);
        categoryBox = findViewById(R.id.category_box);

        mAddButton = findViewById(R.id.add_button);
        mAddButton.setText(R.string.add_button_text);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = nameBox.getText().toString();
                String itemType = typeBox.getText().toString();
                double itemPrice = Double.valueOf(priceBox.getText().toString());
                String itemDescription = descripBox.getText().toString();
                String itemCategory = categoryBox.getText().toString();
                store.addToInventory(itemName, itemDescription, itemPrice, itemType, itemCategory);
                Intent intent = new Intent(AddItemForm.this, StoreViewActivity.class);
                intent.putExtra("Store",store);
                startActivity(intent);
                
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
