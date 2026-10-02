package com.ybkuanysh.lab3.sro3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static final Sensor[] sensors = {
            new CO2Sensor(600), new CO2Sensor(900), new CO2Sensor(1200),
            new TemperatureSensor(22), new TemperatureSensor(26), new TemperatureSensor(30),
            new HumiditySensor(45), new HumiditySensor(65), new HumiditySensor(80)
    };

    public static void main(String[] args) {
        for (Sensor s : sensors) {
            Status status = s.getStatus();
            System.out.println(s.getName() + ": " + s.readValue() + " " + s.getUnit());
            System.out.println("Status: " + status.consoleColor + status + "\u001B[0m");
            System.out.println();
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(8);
        root.setPadding(new Insets(20));
        for (Sensor s : sensors) {
            Label label = new Label(s.getName() + ": " + s.readValue() + " " + s.getUnit()
                    + " — " + s.getStatus());
            label.setTextFill(s.getStatus().guiColor);
            root.getChildren().add(label);
        }

        stage.setTitle("Мониторинг воздуха");
        stage.setScene(new Scene(root, 350, 320));
        stage.show();
    }
}
