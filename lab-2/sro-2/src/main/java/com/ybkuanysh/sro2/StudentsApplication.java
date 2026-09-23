package com.ybkuanysh.sro2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** СРО-2, вариант 5: «Справочник студентов». Данные хранятся в ArrayList,
 *  доступны поиск по имени, фильтр по группе, сортировка и расчёт статистики. */
public class StudentsApplication extends Application {
    /** Значение фильтра, при котором показываются все группы. */
    private static final String ALL_GROUPS = "Все группы";

    /** Список студентов справочника. */
    private final List<Student> students = new ArrayList<>();

    private final TableView<Student> table = new TableView<>();
    private final TextField name = new TextField();
    private final TextField group = new TextField();
    private final TextField score = new TextField();
    private final TextField search = new TextField();
    private final ComboBox<String> filter = new ComboBox<>();
    private final Label stats = new Label();
    private final Label message = new Label();

    @Override
    public void start(Stage stage) {
        students.add(new Student("Amina", "B057-01", 85));
        students.add(new Student("Bob", "B057-01", 92));
        students.add(new Student("Tom", "B057-02", 76));
        students.add(new Student("Sara", "B057-02", 88));

        table.getColumns().addAll(
                column("№", student -> String.valueOf(table.getItems().indexOf(student) + 1), 40),
                column("Имя", student -> student.name, 140),
                column("Группа", student -> student.group, 120),
                column("Баллы", student -> String.valueOf(student.score), 80));

        /** По клику на строку её данные попадают в поля ввода. */
        table.getSelectionModel().selectedItemProperty().addListener((obs, old, student) -> {
            if (student != null) {
                name.setText(student.name);
                group.setText(student.group);
                score.setText(String.valueOf(student.score));
            }
        });

        filter.setValue(ALL_GROUPS);
        filter.setOnAction(e -> refresh());
        search.setPromptText("Имя студента");

        VBox editor = new VBox(10,
                new Label("Данные студента"),
                row("Имя:", name), row("Группа:", group), row("Баллы:", score),
                new HBox(10,
                        button("Добавить", "add"),
                        button("Обновить", "update"),
                        button("Удалить", "delete"),
                        button("Очистить", "clear")));

        VBox tools = new VBox(10,
                new Label("Поиск"), search, button("Найти", "search"),
                new Label("Фильтр"), filter,
                new Label("Сортировка"),
                new HBox(10, button("По имени", "sortName"), button("По баллу", "sortScore")));

        VBox root = new VBox(10,
                new HBox(20, editor, tools),
                new Label("Список студентов"), table,
                new HBox(20, stats, button("Выход", "exit")),
                message);
        root.setPadding(new Insets(15));

        refresh();

        stage.setTitle("Student Directory");
        stage.setScene(new Scene(root, 720, 560));
        stage.show();
    }

    /** Создаёт колонку таблицы с текстом, который считается по студенту. */
    private TableColumn<Student, String> column(String title, Formatter formatter, int width) {
        TableColumn<Student, String> column = new TableColumn<>(title);
        column.setPrefWidth(width);
        column.setCellValueFactory(cell -> new SimpleStringProperty(formatter.text(cell.getValue())));
        return column;
    }

    /** Создаёт строку «подпись + поле ввода». */
    private HBox row(String title, TextField field) {
        Label label = new Label(title);
        label.setPrefWidth(60);
        return new HBox(10, label, field);
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
            case "update" -> update();
            case "delete" -> delete();
            case "clear" -> clear();
            case "search" -> message.setText(find(search.getText()));
            case "sortName" -> sort(Comparator.comparing(student -> student.name));
            case "sortScore" -> sort(Comparator.comparingInt(student -> student.score));
            case "exit" -> Platform.exit();
            default -> message.setText("Неизвестное действие");
        }
        refresh();
    }

    /** Добавляет нового студента из полей ввода. */
    private void add() {
        Student student = read();

        if (student == null) {
            return;
        }
        students.add(student);
        message.setText("Студент добавлен: " + student.name);
    }

    /** Заменяет данные выбранного студента на введённые. */
    private void update() {
        Student selected = table.getSelectionModel().getSelectedItem();
        Student student = read();

        if (selected == null) {
            message.setText("Выберите студента в таблице");
        } else if (student != null) {
            selected.name = student.name;
            selected.group = student.group;
            selected.score = student.score;
            message.setText("Данные обновлены: " + selected.name);
        }
    }

    /** Удаляет выбранного студента. */
    private void delete() {
        Student selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            message.setText("Выберите студента в таблице");
        } else {
            students.remove(selected);
            message.setText("Студент удалён: " + selected.name);
        }
    }

    /** Очищает поля ввода. */
    private void clear() {
        name.clear();
        group.clear();
        score.clear();
        table.getSelectionModel().clearSelection();
        message.setText("Поля очищены");
    }

    /** Ищет студента по имени перебором списка. */
    private String find(String text) {
        if (text.isBlank()) {
            return "Введите имя для поиска";
        }

        for (Student student : students) {
            if (student.name.equalsIgnoreCase(text.trim())) {
                table.getSelectionModel().select(student);
                return "Найден: " + student.name + ", " + student.group + ", " + student.score;
            }
        }
        return "Студент не найден";
    }

    /** Сортирует справочник указанным способом. */
    private void sort(Comparator<Student> comparator) {
        students.sort(comparator);
        message.setText("Список отсортирован");
    }

    /** Читает и проверяет данные из полей ввода. */
    private Student read() {
        if (name.getText().isBlank() || group.getText().isBlank()) {
            message.setText("Заполните имя и группу");
            return null;
        }

        int value;
        try {
            value = Integer.parseInt(score.getText().trim());
        } catch (NumberFormatException e) {
            message.setText("Баллы должны быть числом");
            return null;
        }

        if (value < 0 || value > 100) {
            message.setText("Баллы должны быть от 0 до 100");
            return null;
        }
        return new Student(name.getText().trim(), group.getText().trim(), value);
    }

    /** Перерисовывает таблицу, список групп и статистику. */
    private void refresh() {
        updateGroups();
        table.setItems(FXCollections.observableArrayList(visible()));
        stats.setText("Всего студентов: " + students.size()
                + "    Средний балл: " + average()
                + "    Максимум: " + best(true)
                + "    Минимум: " + best(false));
    }

    /** Отбирает студентов выбранной группы. */
    private List<Student> visible() {
        List<Student> list = new ArrayList<>();
        for (Student student : students) {
            if (ALL_GROUPS.equals(filter.getValue()) || student.group.equals(filter.getValue())) {
                list.add(student);
            }
        }
        return list;
    }

    /** Обновляет список групп в фильтре. */
    private void updateGroups() {
        List<String> groups = new ArrayList<>();
        groups.add(ALL_GROUPS);
        for (Student student : students) {
            if (!groups.contains(student.group)) {
                groups.add(student.group);
            }
        }

        if (!groups.equals(filter.getItems())) {
            String current = filter.getValue();
            filter.getItems().setAll(groups);
            filter.setValue(groups.contains(current) ? current : ALL_GROUPS);
        }
    }

    /** Считает средний балл всех студентов. */
    private String average() {
        if (students.isEmpty()) {
            return "0";
        }

        int sum = 0;
        for (Student student : students) {
            sum += student.score;
        }
        return String.format("%.1f", (double) sum / students.size());
    }

    /** Находит студента с максимальным или минимальным баллом. */
    private String best(boolean maximum) {
        if (students.isEmpty()) {
            return "—";
        }

        Student result = students.get(0);
        for (Student student : students) {
            if (maximum && student.score > result.score) {
                result = student;
            } else if (!maximum && student.score < result.score) {
                result = student;
            }
        }
        return result.name + " (" + result.score + ")";
    }

    /** Студент: имя, группа и баллы. */
    private static class Student {
        private String name;
        private String group;
        private int score;

        private Student(String name, String group, int score) {
            this.name = name;
            this.group = group;
            this.score = score;
        }
    }

    /** Способ получить текст ячейки по студенту. */
    private interface Formatter {
        String text(Student student);
    }
}
