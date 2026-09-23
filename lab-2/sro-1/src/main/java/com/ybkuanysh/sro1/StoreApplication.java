package com.ybkuanysh.sro1;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** СРО-1, вариант 2: «Магазин электроники». Товары хранятся в массиве,
 *  корзина — массив количеств, где индекс совпадает с индексом товара. */
public class StoreApplication extends Application {
    /** Массив товаров магазина. */
    private final Product[] products = {
            new Product("Laptop", 450000, 5),
            new Product("Mouse", 7000, 10),
            new Product("Keyboard", 12000, 8),
            new Product("Monitor", 90000, 4),
            new Product("Headphones", 25000, 6)
    };

    /** Корзина: количество каждого товара по его индексу в массиве products. */
    private final int[] cart = new int[products.length];

    private final TableView<Integer> goodsTable = new TableView<>();
    private final TableView<Integer> cartTable = new TableView<>();
    private final Spinner<Integer> amount = new Spinner<>(1, 99, 1);
    private final Label total = new Label();
    private final Label message = new Label();

    @Override
    public void start(Stage stage) {
        goodsTable.getColumns().addAll(
                column("№", i -> String.valueOf(i + 1), 40),
                column("Название", i -> products[i].name, 120),
                column("Цена", i -> String.valueOf(products[i].price), 90),
                column("В наличии", i -> String.valueOf(available(i)), 90));

        cartTable.getColumns().addAll(
                column("Товар", i -> products[i].name, 120),
                column("Кол-во", i -> String.valueOf(cart[i]), 80),
                column("Сумма", i -> String.valueOf(cart[i] * products[i].price), 100));

        VBox goods = new VBox(10,
                new Label("Товары"), goodsTable,
                new HBox(10, new Label("Количество:"), amount),
                button("Добавить в корзину", "add"));

        VBox basket = new VBox(10,
                new Label("Корзина"), cartTable, total,
                new HBox(10,
                        button("Купить", "buy"),
                        button("Удалить", "remove"),
                        button("Очистить корзину", "clear")));

        HBox tables = new HBox(20, goods, basket);
        VBox root = new VBox(10, tables, message);
        root.setPadding(new Insets(15));

        refresh();

        stage.setTitle("Electronics Store");
        stage.setScene(new Scene(root, 760, 480));
        stage.show();
    }

    /** Создаёт колонку таблицы, текст которой считается по индексу товара. */
    private TableColumn<Integer, String> column(String title, Formatter formatter, int width) {
        TableColumn<Integer, String> column = new TableColumn<>(title);
        column.setPrefWidth(width);
        column.setCellValueFactory(row -> new SimpleStringProperty(formatter.text(row.getValue())));
        return column;
    }

    /** Создаёт кнопку, которая передаёт своё действие в общий обработчик. */
    private Button button(String title, String command) {
        Button button = new Button(title);
        button.setOnAction(e -> action(command));
        return button;
    }

    /** Общий обработчик действий пользователя. */
    private void action(String command) {
        switch (command) {
            case "add" -> add();
            case "remove" -> remove();
            case "buy" -> buy();
            case "clear" -> clear();
            default -> message.setText("Неизвестное действие");
        }
        refresh();
    }

    /** Добавляет выбранный товар в корзину с проверкой доступного количества. */
    private void add() {
        Integer index = goodsTable.getSelectionModel().getSelectedItem();
        int count = amount.getValue();

        if (index == null) {
            message.setText("Выберите товар в списке");
        } else if (count > available(index)) {
            message.setText("Недостаточно товара: доступно " + available(index));
        } else {
            cart[index] += count;
            message.setText("Добавлено в корзину: " + products[index].name);
        }
    }

    /** Удаляет выбранный товар из корзины. */
    private void remove() {
        Integer index = cartTable.getSelectionModel().getSelectedItem();

        if (index == null) {
            message.setText("Выберите товар в корзине");
        } else {
            cart[index] = 0;
            message.setText("Удалено из корзины: " + products[index].name);
        }
    }

    /** Оформляет покупку: списывает товары со склада и очищает корзину. */
    private void buy() {
        int sum = total();

        if (sum == 0) {
            message.setText("Корзина пуста");
            return;
        }

        int i = 0;
        while (i < cart.length) {
            products[i].stock -= cart[i];
            cart[i] = 0;
            i++;
        }
        message.setText("Покупка оформлена на сумму " + sum);
    }

    /** Очищает корзину без покупки. */
    private void clear() {
        for (int i = 0; i < cart.length; i++) {
            cart[i] = 0;
        }
        message.setText("Корзина очищена");
    }

    /** Доступное количество товара: остаток на складе минус уже отложенное в корзину. */
    private int available(int index) {
        return products[index].stock - cart[index];
    }

    /** Общая сумма покупки. */
    private int total() {
        int sum = 0;
        for (int i = 0; i < cart.length; i++) {
            sum += cart[i] * products[i].price;
        }
        return sum;
    }

    /** Перерисовывает обе таблицы и итоговую сумму. */
    private void refresh() {
        goodsTable.setItems(FXCollections.observableArrayList(indexes(false)));

        cartTable.setItems(FXCollections.observableArrayList(indexes(true)));

        total.setText("Итого: " + total());
    }

    /** Список индексов товаров: всех либо только тех, что лежат в корзине. */
    private List<Integer> indexes(boolean onlyCart) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            if (!onlyCart || cart[i] > 0) {
                list.add(i);
            }
        }
        return list;
    }

    /** Товар магазина: название, цена и количество на складе. */
    private static class Product {
        private final String name;
        private final int price;
        private int stock;

        private Product(String name, int price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    /** Способ получить текст ячейки по индексу товара. */
    private interface Formatter {
        String text(int index);
    }
}
