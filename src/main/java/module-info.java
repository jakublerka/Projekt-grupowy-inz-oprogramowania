module com.example.maingui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.maingui to javafx.graphics;
    exports com.example.maingui;
}