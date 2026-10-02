package com.ybkuanysh.lab3.task2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RectangleApplication extends Application {
    @Override
    public void start(Stage stage) {
        TextField first = new TextField();
        first.setPromptText("Прямоугольник A: ширина высота");

        TextField second = new TextField();
        second.setPromptText("Прямоугольник B: ширина высота");

        Label result = new Label();

        Button button = new Button("Вычислить");
        button.setOnAction(e -> result.setText(calculate(first.getText(), second.getText())));

        VBox root = new VBox(10, new Label("Прямоугольники"), first, second, button, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Прямоугольник");
        stage.setScene(new Scene(root, 360, 320));
        stage.show();
    }

    private String calculate(String textA, String textB) {
        Rectangle a, b;
        try {
            a = parse(textA);
            b = parse(textB);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return "Введите два числа через пробел";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        String cmp = switch (a.compareTo(b)) {
            case 1 -> "A больше B";
            case -1 -> "A меньше B";
            default -> "A равен B";
        };
        return String.format("A: %s, S = %.2f, P = %.2f%nB: %s, S = %.2f, P = %.2f%nA x2: %s%n%s",
                a, a.area(), a.perimeter(), b, b.area(), b.perimeter(), a.scale(2), cmp);
    }

    private Rectangle parse(String text) {
        String[] parts = text.trim().split("\\s+");
        return new Rectangle(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }
}
