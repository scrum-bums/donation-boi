package com.scrumbums.donationboi.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.Location;
import com.scrumbums.donationboi.model.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ListViewCustom extends AppCompatActivity {
    private List<ListElement> locationSamples = new ArrayList<>();
    private ListView listView;
    final String TAG = "MainActivity";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv_view);
        readLocationData();

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.store_view, locationSamples);
        listView = findViewById(R.id.mobile_list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                ListElement e = locationSamples.get(position);
                Intent intent = new Intent(ListViewCustom.this, StoreViewActivity.class);
                intent.putExtra("Name", e.getName());
                intent.putExtra("Address", e.getStreetAddress());
                intent.putExtra("Phone Number", e.getPhoneNumber());
                intent.putExtra("Longitude", e.getLongitude());
                intent.putExtra("Latitude", e.getLatitude());
                intent.putExtra("Type", e.getLocationType());
                intent.putExtra("Store", e.getStoreObject());

                Bundle bund = intent.getExtras();
                startActivity(intent);

            }
        });
    }

    private void readLocationData() {
        InputStream is = getResources().openRawResource(R.raw.location_data);
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line = "";
        try {
            br.readLine();
            while ((line = br.readLine()) != null) {
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
                listElement.setStoreObject();

                locationSamples.add(listElement);


            }
        } catch (IOException e) {
            Log.e(TAG, "error reading assets", e);
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
        private Store storeObject;

        private ListElement(Object[] args) {
        }
        private ListElement() {
            this(new Object[0]);
        }

        public String toString(){
            return getName() + ", " + getStreetAddress();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getZipCode() {
            return zipCode;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }

        public String getLocationType() {
            return locationType;
        }

        public void setLocationType(String locationType) {
            this.locationType = locationType;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public Store getStoreObject() {
            return storeObject;
        }

        public void setStoreObject(Store storeObject) {
            this.storeObject = storeObject;
        }

        public void setStoreObject() {
            this.storeObject = createStore();
        }


        private Store createStore() {
            return new Store (name, new Location(streetAddress, state, city, zipCode, latitude, longitude), phoneNumber, website);
        }
    }

}
