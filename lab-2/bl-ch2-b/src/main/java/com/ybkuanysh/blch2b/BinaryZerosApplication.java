package com.ybkuanysh.blch2b;

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

/** Задание bl-ch2-b: сколько значащих нулей в двоичной записи числа 129?
 *  Значащие нули - это нули двоичной записи без ведущих нулей. */
public class BinaryZerosApplication extends Application {
    /** Формат вывода даты и времени. */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    /** Дата и время получения задания. */
    private static final LocalDateTime RECEIVED = LocalDateTime.of(2026, 9, 10, 10, 0);

    @Override
    public void start(Stage stage) {
        TextField input = new TextField("129");

        Label result = new Label();

        Button button = new Button("Посчитать");
        button.setOnAction(e -> result.setText(count(input.getText())));

        /** Подпись: фамилия разработчика, дата получения и дата сдачи задания. */
        Label footer = new Label("Разработчик: Ябеков"
                + "\nЗадание получено: " + RECEIVED.format(FORMAT)
                + "\nЗадание сдано: " + LocalDateTime.now().format(FORMAT));

        VBox root = new VBox(10, new Label("Введите число"), input, button, result, footer);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Значащие нули в двоичной записи");
        stage.setScene(new Scene(root, 380, 260));
        stage.show();
    }

    /** Считает нули в двоичной записи введённого числа. */
    private String count(String text) {
        long number;
        try {
            number = Long.parseLong(text.trim());
        } catch (NumberFormatException e) {
            return "Введите целое число";
        }

        if (number < 0) {
            return "Введите неотрицательное число";
        }

        /** Двоичная запись без ведущих нулей. */
        String binary = Long.toBinaryString(number);

        int zeros = 0;
        for (char bit : binary.toCharArray()) {
            if (bit == '0') {
                zeros++;
            }
        }

        return "Двоичная запись: " + binary
                + "\nЗначащих нулей: " + zeros;
    }
}
