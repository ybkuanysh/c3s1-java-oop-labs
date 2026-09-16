package com.ybkuanysh.blch1a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArgsApplication extends Application {
    @Override
    public void start(Stage stage) {
        List<String> args = new ArrayList<>(getParameters().getRaw());
        Collections.reverse(args);

        String text = args.isEmpty() ? "Аргументы не заданы" : String.join(" ", args);

        VBox root = new VBox(10, new Label("Аргументы в обратном порядке:"), new Label(text));
        root.setPadding(new Insets(20));

        stage.setTitle("Аргументы командной строки");
        stage.setScene(new Scene(root, 400, 120));
        stage.show();
    }
}
