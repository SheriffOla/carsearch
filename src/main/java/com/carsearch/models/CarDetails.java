package com.carsearch.models;

public class CarDetails {
    String variant_Reg;
    String make;
    String model;
    String year;

    public void setVariant_Reg(String reg){
        variant_Reg = reg;
    }

    public void setMake(String make){
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVariant_Reg() {
        return variant_Reg;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }
}
