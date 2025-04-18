package com.example.medicinelemonsoft;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    EditText edSearch;
    ArrayList<MedicineModel> arrayList = new ArrayList<>();
    MedicineSqlite medicineSqlite;
    MedicineAdepter adepter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();

        // Setup toolbar and navigation


        // Initialize database and adapter
        medicineSqlite = new MedicineSqlite(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup button click listener
        setupButtonClickListener();

        // Load initial data
        loadData("");

        // Setup search text listener
        setupSearchListener();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerView);
        edSearch = findViewById(R.id.edSearch);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        myButton = findViewById(R.id.myButton);

        if (myButton == null) {
            Log.e(TAG, "Button not found in layout!");
            Toast.makeText(this, "Button initialization failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void setupButtonClickListener() {
        myButton.setOnClickListener(v -> {
            Log.d(TAG, "Button clicked event received");

            String searchText = edSearch.getText().toString().trim();
            if (searchText.isEmpty()) {
                Toast.makeText(this, "Showing all medicines", Toast.LENGTH_SHORT).show();
                loadData("");
            } else {
                Toast.makeText(this, "Searching for: " + searchText, Toast.LENGTH_SHORT).show();
                loadData(searchText);
            }

            // Hide keyboard
            hideKeyboard(v);
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setupSearchListener() {
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadData(edSearch.getText().toString());
            }
        });
    }

    public void loadData(String key) {
        Log.d(TAG, "Loading data for key: " + key);
        arrayList.clear();
        Cursor cursor = key.isEmpty() ? medicineSqlite.loadData() : medicineSqlite.searchData(key);

        if (cursor != null) {
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String brand_name = cursor.getString(3);
                        String from = cursor.getString(4);
                        String strength = cursor.getString(5);
                        String price = cursor.getString(6);

                        arrayList.add(new MedicineModel(id, brand_name, from, strength, "Unit price: " + price + " BDT"));
                    }
                }
            } finally {
                cursor.close();
            }
        }

        adepter = new MedicineAdepter(arrayList, this);
        recyclerView.setAdapter(adepter);
    }

    // ... [Rest of your existing methods (setUpToolbar, onBackPressed etc.) remain unchanged] ...
}