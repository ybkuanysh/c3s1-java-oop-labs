package com.ybkuanysh.lab1.task3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScalingApplication extends Application {
    private final TextField x1 = field("x1");
    private final TextField y1 = field("y1");
    private final TextField x2 = field("x2");
    private final TextField y2 = field("y2");
    private final TextField x3 = field("x3");
    private final TextField y3 = field("y3");
    private final TextField sx = field("Sx");
    private final TextField sy = field("Sy");

    private final Label result = new Label();

    @Override
    public void start(Stage stage) {
        Button button = new Button("Масштабировать");
        button.setOnAction(e -> result.setText(calculate()));

        VBox root = new VBox(10,
                new Label("Масштабирование треугольника"),
                row("A", x1, y1),
                row("B", x2, y2),
                row("C", x3, y3),
                row("S", sx, sy),
                button,
                result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Масштабирование");
        stage.setScene(new Scene(root, 320, 320));
        stage.show();
    }

    private static TextField field(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setPrefWidth(80);
        return textField;
    }

    private static HBox row(String name, TextField first, TextField second) {
        HBox box = new HBox(10, new Label(name), first, second);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private String calculate() {
        double[] values = new double[8];
        TextField[] fields = {x1, y1, x2, y2, x3, y3, sx, sy};
        for (int i = 0; i < fields.length; i++) {
            try {
                values[i] = Double.parseDouble(fields[i].getText().trim());
            } catch (NumberFormatException e) {
                return "Заполните все поля числами";
            }
        }

        double scaleX = values[6];
        double scaleY = values[7];

        return String.format("A' = (%.2f; %.2f)%nB' = (%.2f; %.2f)%nC' = (%.2f; %.2f)",
                values[0] * scaleX, values[1] * scaleY,
                values[2] * scaleX, values[3] * scaleY,
                values[4] * scaleX, values[5] * scaleY);
    }
}
