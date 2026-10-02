package com.ybkuanysh.lab3.sro3;

public abstract class Sensor {
    protected String name;
    protected double value;

    public Sensor(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double readValue() {
        return value;
    }

    public abstract String getUnit();

    public abstract Status getStatus();
}
