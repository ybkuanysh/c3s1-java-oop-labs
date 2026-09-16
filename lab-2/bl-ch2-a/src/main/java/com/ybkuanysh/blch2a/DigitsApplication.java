package com.ybkuanysh.blch2a;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Задание bl-ch2-a: среди n введённых чисел найти количество чисел
 *  только с чётными цифрами и количество чисел с равным числом
 *  чётных и нечётных цифр. Число обрабатывается как массив символов. */
public class DigitsApplication extends Application {
    /** Формат вывода даты и времени. */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    /** Дата и время получения задания. */
    private static final LocalDateTime RECEIVED = LocalDateTime.of(2026, 9, 10, 10, 0);

    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        input.setPromptText("Числа через пробел");

        Label result = new Label();

        Button button = new Button("Посчитать");
        button.setOnAction(e -> result.setText(count(input.getText())));

        /** Подпись: фамилия разработчика, дата получения и дата сдачи задания. */
        Label footer = new Label("Разработчик: Ябеков"
                + "\nЗадание получено: " + RECEIVED.format(FORMAT)
                + "\nЗадание сдано: " + LocalDateTime.now().format(FORMAT));

        VBox root = new VBox(10, new Label("Введите n чисел"), input, button, result, footer);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Чётные и нечётные цифры");
        stage.setScene(new Scene(root, 380, 280));
        stage.show();
    }

    /** Подсчитывает числа только с чётными цифрами и числа,
     *  у которых чётных и нечётных цифр одинаково. */
    private String count(String text) {
        if (text.isBlank()) {
            return "Введите хотя бы одно число";
        }

        int onlyEven = 0;
        int equalCount = 0;

        for (String part : text.trim().split("\\s+")) {
            long number;
            try {
                number = Long.parseLong(part);
            } catch (NumberFormatException e) {
                return "Введите целые числа через пробел";
            }

            /** Число как массив символов (знак минус отбрасывается). */
            char[] digits = String.valueOf(Math.abs(number)).toCharArray();

            int even = 0;
            int odd = 0;
            for (char digit : digits) {
                if ((digit - '0') % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }

            if (odd == 0) {
                onlyEven++;
            }
            if (even == odd) {
                equalCount++;
            }
        }

        return "Только чётные цифры: " + onlyEven
                + "\nРавно чётных и нечётных цифр: " + equalCount;
    }
}
