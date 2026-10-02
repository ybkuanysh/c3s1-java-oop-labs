package com.ybkuanysh.lab3.sro3;

public class CO2Sensor extends Sensor {
    public CO2Sensor(double value) {
        super("CO2", value);
    }

    @Override
    public String getUnit() {
        return "ppm";
    }

    @Override
    public Status getStatus() {
        return Status.of(readValue(), 800, 1000);
    }
}
