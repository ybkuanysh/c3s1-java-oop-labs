module com.ybkuanysh.laboratoryworks {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ybkuanysh.laboratoryworks to javafx.fxml;
    exports com.ybkuanysh.laboratoryworks;
}