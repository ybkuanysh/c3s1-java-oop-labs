package com.ybkuanysh.sro2;

public class Car {
    private String model;
    private String vendor;
    private int seatsCount;
    private int horsePower;

    public Car(String model, String vendor, int seatsCount, int horsePower) {
        this.model = model;
        this.vendor = vendor;
        this.seatsCount = seatsCount;
        this.horsePower = horsePower;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
