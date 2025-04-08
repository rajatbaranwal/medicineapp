package com.example.medicinelemonsoft;

import java.io.Serializable;

public class MedicineModel implements Serializable {
    int id;
    String brand_name, from, strength, price;

    public MedicineModel(int id, String brand_name, String from, String strength, String price) {
        this.id = id;
        this.brand_name = brand_name;
        this.from = from;
        this.strength = strength;
        this.price = price;
    }

    // Getters and setters...
    public int getId() { return id; }
    public String getBrand_name() { return brand_name; }
    public String getFrom() { return from; }
    public String getStrength() { return strength; }
    public String getPrice() { return price; }
    // ... rest of your getters and setters
}