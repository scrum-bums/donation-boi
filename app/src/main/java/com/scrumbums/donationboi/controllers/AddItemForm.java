package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Store;

public class AddItemForm extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        setContentView(R.layout.add_item_form);

        nameBox.findViewById(R.id.name_box);

        nameLabel.findViewById(R.id.name_label);
        nameLabel.setText(R.string.name_label_text);

        typeBox.findViewById(R.id.type_label);

        typeLabel.findViewById(R.id.type_label);
        typeLabel.setText(R.string.type_label_text);

        priceBox.findViewById(R.id.price_box);

        priceLable.findViewById(R.id.price_label);
        priceLable.setText(R.string.price_label_text);

        descripBox.findViewById(R.id.descrip_box);

        descripLabel.findViewById(R.id.descip_label);
        descripLabel.setText(R.string.descrip_label_text);

        mAddButton.findViewById(R.id.add_button);
        mAddButton.setText(R.string.add_button_text);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = nameBox.getText().toString();
                String itemType = typeBox.getText().toString();
                double itemPrice = Double.valueOf(priceBox.getText().toString());
                String itemDescription = descripBox.getText().toString();
                ((Store) intent.getParcelableExtra("StoreAdd")).addToInventory(itemName, itemDescription, itemPrice, itemType);
                finish();
            }
        });


        mCancelItemButton.findViewById(R.id.cancel_button);
        mCancelItemButton.setText(R.string.cancel_button_item);

        mCancelItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
