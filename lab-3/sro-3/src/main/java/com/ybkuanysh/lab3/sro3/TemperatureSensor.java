package com.ybkuanysh.lab3.sro3;

public class TemperatureSensor extends Sensor {
    public TemperatureSensor(double value) {
        super("Temperature", value);
    }

    @Override
    public String getUnit() {
        return "°C";
    }

    @Override
    public Status getStatus() {
        return Status.of(readValue(), 24, 28);
    }
}
