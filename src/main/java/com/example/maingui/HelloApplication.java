package com.example.maingui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SQL generator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("SQL generator");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0,0,2,1);

        Label raport = new Label("Raport:");
        grid.add(raport, 0,1);

        ObservableList<String> raporty =
                FXCollections.observableArrayList(
                        "Raport 1",
                        "Raport 2",
                        "Raport 3",
                        "Raport 4"
                );
        final ComboBox comboBox = new ComboBox(raporty);
        grid.add(comboBox, 0,2);



        Label data = new Label("Data:");
        grid.add(data, 0, 4);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 0,5);

        Label ID = new Label("ID:");
        grid.add(ID, 8, 4);
        TextField idInput = new TextField("");
        grid.add(idInput, 8,5);

        Label kraj = new Label("Kraj:");
        grid.add(kraj, 16, 4);
        TextField krajInput = new TextField("");
        grid.add(krajInput, 16,5);

        Label miasto = new Label("Miasto:");
        grid.add(miasto, 24, 4);
        TextField miastoInput = new TextField("");
        grid.add(miastoInput, 24, 5);

        Label appWeb = new Label("App/Web:");
        grid.add(appWeb, 32, 4);
        ObservableList<String> appWebLista =
                FXCollections.observableArrayList(
                        "App",
                        "Web"
                );
        ComboBox appWebChoice = new ComboBox(appWebLista);
        grid.add(appWebChoice, 32,5);

        TextArea sqlOutput = new TextArea("test");
        sqlOutput.setEditable(false);
        grid.add(sqlOutput, 0, 15, 32, 1);

        Scene scene = new Scene(grid, 1366,768);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}