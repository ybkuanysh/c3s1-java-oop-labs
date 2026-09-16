package com.ybkuanysh.blch1b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DivisibleApplication extends Application {
    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        input.setPromptText("Числа через пробел");

        Label result = new Label();

        Button button = new Button("Найти");
        button.setOnAction(e -> result.setText(find(input.getText())));

        VBox root = new VBox(10, new Label("Числа, которые делятся на 3 или на 9"), input, button, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Делимость на 3 или 9");
        stage.setScene(new Scene(root, 360, 200));
        stage.show();
    }

    private String find(String text) {
        List<String> found = new ArrayList<>();
        for (String part : text.trim().split("\\s+")) {
            int number;
            try {
                number = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                return "Введите целые числа через пробел";
            }
            if (number % 3 == 0 || number % 9 == 0) {
                found.add(String.valueOf(number));
            }
        }

        if (found.isEmpty()) {
            return "Таких чисел нет";
        }
        return "Результат: " + String.join(" ", found);
    }
}
