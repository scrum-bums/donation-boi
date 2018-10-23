package com.scrumbums.donationboi.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;

import com.scrumbums.donationboi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.app.Activity;
import android.widget.TextView;

import com.scrumbums.donationboi.R;

public class ListElementPage extends Activity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.csv_element_view);


        tv = findViewById(R.id.csv_text);
        tv.setText(String.format("%s%n%s%n%s%n%s%n%s%n%s%n",intent.getStringExtra("Name"), intent.getStringExtra("Address"), intent.getStringExtra("Phone Number"),
                intent.getStringExtra("Longitude"), intent.getStringExtra("Latitude"), intent.getStringExtra("Type")));


    }

}
