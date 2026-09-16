package com.ybkuanysh.lab1.task2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TriangleApplication extends Application {
    @Override
    public void start(Stage stage) {
        TextField fieldA = new TextField();
        fieldA.setPromptText("Катет a");

        TextField fieldB = new TextField();
        fieldB.setPromptText("Катет b");

        Label result = new Label();

        Button button = new Button("Вычислить");
        button.setOnAction(e -> result.setText(calculate(fieldA.getText(), fieldB.getText())));

        VBox root = new VBox(10, new Label("Прямоугольный треугольник"), fieldA, fieldB, button, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Треугольник");
        stage.setScene(new Scene(root, 320, 260));
        stage.show();
    }

    private String calculate(String textA, String textB) {
        double a;
        double b;
        try {
            a = Double.parseDouble(textA.trim());
            b = Double.parseDouble(textB.trim());
        } catch (NumberFormatException e) {
            return "Введите числа";
        }
        if (a <= 0 || b <= 0) {
            return "Катеты должны быть больше 0";
        }

        double c = Math.sqrt(a * a + b * b);
        double area = a * b / 2;
        double perimeter = a + b + c;

        return String.format("Гипотенуза = %.4f%nПлощадь = %.4f%nПериметр = %.4f", c, area, perimeter);
    }
}
