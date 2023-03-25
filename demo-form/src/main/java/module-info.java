module com.example.demoform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demoform to javafx.fxml;
    exports com.example.demoform;
}