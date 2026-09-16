package com.ybkuanysh.lab1.task1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SeriesSumApplication extends Application {
    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        input.setPromptText("Введите N");

        Label result = new Label();

        Button button = new Button("Вычислить");
        button.setOnAction(e -> result.setText(calculate(input.getText())));

        VBox root = new VBox(10, new Label("Сумма ряда 1 + 1/2 + ... + 1/N"), input, button, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Сумма ряда");
        stage.setScene(new Scene(root, 320, 200));
        stage.show();
    }

    private String calculate(String text) {
        int n;
        try {
            n = Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return "Введите целое число";
        }
        if (n < 1) {
            return "N должно быть больше 0";
        }

        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return String.format("Сумма = %.4f", sum);
    }
}
