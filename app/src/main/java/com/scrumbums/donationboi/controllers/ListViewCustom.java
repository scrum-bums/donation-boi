package com.scrumbums.donationboi.controllers;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class ListViewCustom extends AppCompatActivity {
    private List<ListElement> locationSamples = new ArrayList<>();
    private ListView listView;
//    private TextView csvElementView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv_view);
        readLocationData();

//        csvElementView = findViewById(R.id.csv_text);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.csv_element_view, locationSamples);
        listView = findViewById(R.id.mobile_list);

        listView.setAdapter(adapter);
    }

    private void readLocationData() {

        InputStream is = getResources().openRawResource(R.raw.location_data);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line = "";
        try {
            br.readLine();
            while ((line = br.readLine()) != null) {
//                Log.d(MainActivity.TAG, line);
                String[] tokens = line.split(",");
                ListElement listElement = new ListElement();
                listElement.setName(tokens[1]);
                listElement.setLatitude(Float.parseFloat(tokens[2]));
                listElement.setLongitude(Float.parseFloat(tokens[3]));
                listElement.setStreetAddress(tokens[4]);
                listElement.setCity(tokens[5]);
                listElement.setState(tokens[6]);
                listElement.setZipCode(Integer.parseInt(tokens[7]));
                listElement.setLocationType(tokens[8]);
                listElement.setPhoneNumber(tokens[9]);
                listElement.setWebsite(tokens[10]);
                locationSamples.add(listElement);

                Log.d("MainActivity", "Just Created: " + listElement);

            }
        } catch (IOException e) {
            Log.e("MainActivity", "error reading assets", e);
        }


    }

    class ListElement {

        private String name;
        private float latitude;
        private float longitude;
        private String streetAddress;
        private String city;
        private String state;
        private int zipCode;
        private String locationType;
        private String phoneNumber;
        private String website;

        private ListElement(Object[] args) {
        }
        private ListElement() {
            this(new Object[0]);
        }

        private void setName(String nam) {
            name = nam;
        }
        public String getName() {
            return name;
        }
        private void setLatitude(float lat) {
            latitude = lat;
        }
        public float getLatitude() {
            return latitude;
        }
        private void setLongitude(float longi) {
            longitude = longi;
        }
        public float getLongitude() {
            return longitude;
        }
        private void setStreetAddress(String str) {
            streetAddress = str;
        }
        public String getStreetAddress() {
            return streetAddress;
        }
        private void setCity(String cit) {
            city = cit;
        }
        public String getCity() {
            return city;
        }
        private void setState(String st) {
            state = st;
        }
        public String getState() {
            return state;
        }
        private void setZipCode(int zippy) {
            zipCode = zippy;
        }
        public int getZipCode() {
            return zipCode;
        }
        private void setLocationType(String ty) {
            locationType = ty;
        }
        public String getLocationType() {
            return locationType;
        }
        private void setPhoneNumber(String phone) {
            phoneNumber = phone;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }
        private void setWebsite(String web) {
            website = web;
        }
        public String getWebsite() {
            return website;
        }

        public String toString(){
            return name + ", " + streetAddress;
        }
    }

}
