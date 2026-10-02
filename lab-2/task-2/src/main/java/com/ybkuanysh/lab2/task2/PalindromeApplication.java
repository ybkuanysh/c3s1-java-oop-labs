package com.ybkuanysh.lab2.task2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PalindromeApplication extends Application {
    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        input.setPromptText("Например: А роза упала на лапу Азора");

        Label result = new Label();

        Button button = new Button("Проверить");
        button.setOnAction(e -> {
            result.setText(check(input.getText()));
            demo(input.getText());
        });

        VBox root = new VBox(10, new Label("Проверка на палиндром"), input, button, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Палиндром");
        stage.setScene(new Scene(root, 360, 200));
        stage.show();
    }

    private String check(String text) {
        String clean = text.replaceAll("[^\\p{L}]", "").toLowerCase();
        String reversed = new StringBuilder(clean).reverse().toString();
        return switch (clean.length()) {
            case 0 -> "Нет букв";
            case 1 -> "Одна буква — палиндром";
            default -> clean.equals(reversed) ? "Палиндром" : "Не палиндром";
        };
    }

    private void demo(String text) {
        String t = text.trim();
        System.out.println("length: " + t.length());
        if (!t.isEmpty()) {
            System.out.println("charAt(0): " + t.charAt(0));
            System.out.println("substring(0, half): " + t.substring(0, t.length() / 2));
        }
        System.out.println("indexOf(' '): " + t.indexOf(' '));
        System.out.println("split: " + String.join(" | ", t.split("\\s+")));
        System.out.println("replace: " + t.replace(" ", ""));

        int n = 10_000;
        long start = System.nanoTime();
        String s = "";
        for (int i = 0; i < n; i++) s += "a";
        long concat = System.nanoTime() - start;
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        long builder = System.nanoTime() - start;
        System.out.printf("Конкатенация: %d мкс, StringBuilder: %d мкс%n", concat / 1000, builder / 1000);

        int len = Integer.parseInt(String.valueOf(t.length()));
        Integer boxed = len;
        int unboxed = boxed;
        System.out.println("parseInt/valueOf: " + len + ", " + Integer.valueOf(unboxed).equals(boxed));

        Object[] values = {t, boxed, t.isEmpty()};
        for (Object o : values) {
            if (o instanceof String str) System.out.println("String длиной " + str.length());
            else if (o instanceof Integer i) System.out.println("Integer * 2 = " + i * 2);
            else if (o instanceof Boolean b) System.out.println("Boolean: " + b);
        }

        String report = """
                --- Итог ---
                Текст: %s
                Результат: %s
                """.formatted(t, check(t));
        System.out.println(report);
    }

    int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
}
