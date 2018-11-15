package com.scrumbums.donationboi.controllers;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.Locale;

/**
 * Controller for the map view for stores.
 * @author jdierberger3
 * @version 1.0
 */
public class MapViewActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Store[] stores = DatabaseAbstraction.getStoresArray();

        for (Store store : stores) {
            // Add a marker in Sydney and move the camera
            LatLng pin = new LatLng(store.getLocation().getLatitude(),
                    store.getLocation().getLongitude());
            String snippet = String.format(Locale.ENGLISH, "%s, %s", store.getPhoneNumber(), store.getWebsite());
            mMap.addMarker(new MarkerOptions().position(pin).title(
                    store.getName()).snippet(snippet));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pin));

        }
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

    }
}
