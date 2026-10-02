package com.ybkuanysh.lab3.sro3;

public class HumiditySensor extends Sensor {
    public HumiditySensor(double value) {
        super("Humidity", value);
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public Status getStatus() {
        return Status.of(readValue(), 60, 70);
    }
}
