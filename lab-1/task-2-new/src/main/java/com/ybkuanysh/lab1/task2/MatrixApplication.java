package com.ybkuanysh.lab1.task2;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MatrixApplication extends Application {
    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        input.setPromptText("Введите N");

        Label result = new Label();
        result.setFont(Font.font("Monospaced"));

        Button button = new Button("Заполнить");
        button.setOnAction(e -> {
            result.setText(calculate(input.getText()));
            demo();
        });

        VBox root = new VBox(10, new Label("Диагонали матрицы N x N"), input, button, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Матрица");
        stage.setScene(new Scene(root, 360, 400));
        stage.show();
    }

    private String calculate(String text) {
        int n;
        try {
            n = Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return "Введите целое число";
        }
        if (n < 1 || n > 10) {
            return "N должно быть от 1 до 10";
        }

        int[][] m = new int[n][n];
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = random.nextInt(10);
                sb.append(m[i][j]).append(" ");
            }
            sb.append("\n");
        }

        StringBuilder main = new StringBuilder(), side = new StringBuilder();
        int mainSum = 0, sideSum = 0;
        for (int i = 0; i < n; i++) {
            main.append(m[i][i]).append(" ");
            side.append(m[i][n - 1 - i]).append(" ");
            mainSum += m[i][i];
            sideSum += m[i][n - 1 - i];
        }
        return sb + "\nГлавная: " + main + "= " + mainSum + "\nПобочная: " + side + "= " + sideSum;
    }

    private void demo() {
        int a = 7, b = 3;
        double d = 2.5;
        char c = 'A';
        boolean flag = true;
        System.out.println("a + b = " + (a + b) + ", a - b = " + (a - b) + ", a * b = " + (a * b));
        System.out.println("a / b = " + (a / b) + ", a % b = " + (a % b) + ", a / d = " + (a / d));
        System.out.println("a > b: " + (a > b) + ", a == b: " + (a == b) + ", a != b: " + (a != b));
        System.out.println("char + 1 = " + (char) (c + 1) + ", !flag = " + !flag);

        String parity = switch (a % 2) {
            case 0 -> "чётное";
            default -> "нечётное";
        };
        System.out.println("a — " + parity);

        int i = 1, sum = 0;
        while (i <= 5) {
            sum += i;
            i++;
        }
        System.out.println("while: сумма 1..5 = " + sum);

        int k = 3;
        do {
            System.out.print(k + " ");
            k--;
        } while (k > 0);
        System.out.println("<- do-while");

        int[] arr = new int[5];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = j * j;
        }
        System.out.print("Одномерный массив: ");
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();

        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Двумерный массив:");
        for (int[] row : grid) {
            for (int x : row) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
