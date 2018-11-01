package com.scrumbums.donationboi.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.AppDatabase;
import com.scrumbums.donationboi.model.Item;
import com.scrumbums.donationboi.model.entities.Store;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StoreListActivity extends AppCompatActivity {
    private ArrayList<Store> stores;
    private ListView listView;
    private Button loadSampleData;
    private ArrayAdapter adapter;
    final String TAG = "DONATION-BOI/StoreList";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv_view);

        listView = findViewById(R.id.mobile_list);
        Disposable getStores = DatabaseAbstraction.getStoresArrayList(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        storesList -> {
                            runOnUiThread(() -> {
                                Log.i(TAG, storesList.toString());
                                adapter = new ArrayAdapter<>(this, R.layout.csv_element_view, storesList);
                                listView.setAdapter(adapter);
                            });
                        },
                        error -> {
                            error.printStackTrace();
                        }
                );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                Store e = stores.get(position);
                Intent intent = new Intent(StoreListActivity.this, StoreViewActivity.class);
                intent.putExtra("storeId", e.getStoreId());
                Bundle bundle = intent.getExtras();
                startActivity(intent);

            }
        });
        loadSampleData = findViewById(R.id.load_sample_data);
        loadSampleData.setOnClickListener(v -> {
            Disposable c = Completable.fromAction(() -> {
                HashMap<String, List> sampleData = Store.populateData(getApplicationContext());
                AppDatabase.getDatabase(getApplicationContext()).storeDao().insertStoresAndLocations(sampleData.get("locations"), sampleData.get("stores"));

            }).subscribeOn(Schedulers.io()).subscribe(
                    ()-> {
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                    },
                    e -> e.printStackTrace()
            );

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
