package com.ybkuanysh.lab3.sro3;

import javafx.scene.paint.Color;

public enum Status {
    NORMAL("\u001B[32m", Color.GREEN),
    ELEVATED("\u001B[33m", Color.ORANGE),
    WARNING("\u001B[31m", Color.RED);

    public final String consoleColor;
    public final Color guiColor;

    Status(String consoleColor, Color guiColor) {
        this.consoleColor = consoleColor;
        this.guiColor = guiColor;
    }

    public static Status of(double value, double normalMax, double elevatedMax) {
        if (value <= normalMax) {
            return NORMAL;
        } else if (value <= elevatedMax) {
            return ELEVATED;
        } else {
            return WARNING;
        }
    }
}
