package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;

public class ItemView extends Activity {
    private TextView contentInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.item_view);

        contentInfo.findViewById(R.layout.item_info);

    }

}
