package com.example.medicinelemonsoft;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        // Initialize views with standard findViewById
        TextView brandName = findViewById(R.id.brand_name);
        TextView manufacturer = findViewById(R.id.from);
        TextView strength = findViewById(R.id.strength);
        TextView price = findViewById(R.id.price);

        // Get medicine data
        if (getIntent() != null && getIntent().hasExtra("medicine")) {
            MedicineModel medicine = (MedicineModel) getIntent().getSerializableExtra("medicine");
            if (medicine != null) {
                brandName.setText(medicine.getBrand_name());
                manufacturer.setText("From: " + medicine.getFrom());
                strength.setText("Strength: " + medicine.getStrength());
                price.setText("Price: " + medicine.getPrice());
            }
        }

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}