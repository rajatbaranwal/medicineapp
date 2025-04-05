package com.example.medicinelemonsoft;

public class MedicineModel {

    int id;
    String brand_name,from,strength,price;


    public MedicineModel(int id, String brand_name, String from, String strength, String price) {
        this.id = id;
        this.brand_name = brand_name;
        this.from = from;
        this.strength = strength;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
